package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.ChildAbs;


public class ChildAbsDao extends Dao{

	/**
	 * baseSql:String 共通SQL文 プライベート
	 */

	private String baseSql =
			"SELECT child.child_id, child.child_name, child.parents_id, child.class_id, child.is_attend, child.facility_id,absence.abs_is_attend, absence.absence_date FROM child left outer join absence ON child.child_id = absence.child_id and child.facility_id = absence.facility_id  where child.facility_id=? ";


	//子供情報一覧表示のみ使用（欠席情報あり）
	public List<ChildAbs> getChildListAbsinfo(String facility_id, boolean IsAttend) throws Exception {
	    // 戻り値用のマップ（名前をキーにして重複を排除）
	    Map<String, ChildAbs> map = new HashMap<>();
	    Connection connection = getConnection();
	    PreparedStatement st = null;

	    // 欠席報告日作成
	    LocalDateTime nowDate = LocalDateTime.now();
	    DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("yyyy/MM/dd");
	    String today_absence_date = dtf1.format(nowDate);

	    // SQL文の在籍フラグ条件
	    String conditionIsAttend = "";
	    if (IsAttend) {
	        conditionIsAttend = "and is_attend=true ";
	    }

	    try {
	        st = connection.prepareStatement(baseSql + conditionIsAttend);
	        st.setString(1, facility_id);

	        ResultSet rSet = st.executeQuery();
	        while (rSet.next()) {
	            String childName = rSet.getString("child_name");
	            String absenceDate = rSet.getString("absence_date");

	            // 本日が欠席日　:　true
	            // 欠席情報がない、または欠席日が今日でない： false
	            boolean todayAbs = absenceDate != null && absenceDate.equals(today_absence_date);


	            ChildAbs existingChild = map.get(childName);

	            if (existingChild == null || // まだマップにない場合
	                todayAbs ||              // 今日の日付がある場合
	                existingChild.getAbsence_date() == null) { // 既存データがnullの場合

	                ChildAbs childabs = new ChildAbs();
	                childabs.setChild_id(rSet.getString("child_id"));
	                childabs.setChild_name(childName);
	                childabs.setParents_id(rSet.getString("parents_id"));
	                childabs.setClass_id(rSet.getString("class_id"));
	                childabs.setIs_attend(rSet.getBoolean("is_attend"));
	                childabs.setFacility_id(rSet.getString("facility_id"));

	                childabs.setAbs_is_attend(todayAbs);

	                childabs.setAbsence_date(absenceDate);

	                System.out.println(childName);
	                System.out.println(todayAbs);


	                map.put(childName, childabs); // マップに追加または更新
	            }
	        }
	    } catch (Exception e) {
	        throw e;
	    } finally {
	        if (st != null) {
	            try {
	                st.close();
	            } catch (SQLException sqle) {
	                throw sqle;
	            }
	        }
	        if (connection != null) {
	            try {
	                connection.close();
	            } catch (SQLException sqle) {
	                throw sqle;
	            }
	        }
	    }

	    // Mapの値をListに変換して返す
	    return new ArrayList<>(map.values());
	}

	// filterで統一されていないのはパラメーターがString,Stringと同じ属性だと使用できないため

	/**
	 * 子供一覧絞り込みで使用
	 * postFilterメソッド フィルター後のリストへの格納処理 プライベート
	 */
	private List<ChildAbs> postFilter(ResultSet rSet) throws Exception {
		// リストを初期化
		List<ChildAbs> list = new ArrayList<>();

		boolean today_abs = false;

		// 欠席報告日作成
		LocalDateTime nowDate = LocalDateTime.now();
		System.out.println(nowDate);
		// 表示形式を指定（年月日）
		DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		String today_absence_date = dtf1.format(nowDate);


		try {
			// リザルトセットを全件走査
			while(rSet.next()) {
				// 子供情報一覧を初期化
				ChildAbs childabs = new ChildAbs();
				// 学生インスタンスに検索結果をセット
				childabs.setChild_id(rSet.getString("child_id"));
				childabs.setChild_name(rSet.getString("child_name"));
				childabs.setParents_id(rSet.getString("parents_id"));
				childabs.setClass_id(rSet.getString("class_id"));
				childabs.setIs_attend(rSet.getBoolean("is_attend"));
				childabs.setFacility_id(rSet.getString("facility_id"));
				if (rSet.getString("absence_date") != null && rSet.getString("absence_date").equals(today_absence_date)){
					today_abs = true;
				}
				System.out.println(rSet.getString("absence_date"));
				System.out.println(today_absence_date);
				childabs.setAbs_is_attend(today_abs);
				childabs.setAbsence_date(rSet.getString("abs_is_attend"));
				list.add(childabs);
			}
		} catch (SQLException | NullPointerException e){
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * filterメソッド 子供IDを指定して子供の一覧を取得する
	 *
	 * @param child_id:String
	 *            子供ID
	 * @param facility_id:String
	 *            施設ID
	 * @return 一覧のリスト:List<ChildAbs> 存在しない場合は0件のリスト
	 * @throws Exception
	 */
	public List<ChildAbs> filterbyChildId(String child_id, String facility_id, boolean IsAttend) throws Exception {

		// リストを初期化
		List<ChildAbs> list = new ArrayList<>();
		// コネクションを確立
		Connection connection = getConnection();
		// プリペアードステートメント
		PreparedStatement statement = null;
		// リザルトセット
		ResultSet rSet = null;
		// SQL文の条件
		String condition = "and child.child_id=?  ";

		// SQL文の在籍フラグ条件
		String conditionIsAttend = "";
		// 在学中のみ表示
		if (IsAttend) {
		    conditionIsAttend = "and child.is_attend=true  ";
		}

		// SQL文のソート
		String order = "order by child.child_id asc";



		try {
			// プリペアードステートメントにSQL文をセット
			statement = connection.prepareStatement(baseSql + condition + conditionIsAttend + order);

			statement.setString(1, facility_id);
			statement.setString(2, child_id);

			// プライベートステートメントを実行
			rSet = statement.executeQuery();



			// リストへの格納処理を実行
			list = postFilter(rSet);
		} catch (Exception e) {
			throw e;
		} finally {
			// プリペアードステートメントを閉じる
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
			// コネクションを閉じる
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
		}
		return list;
	}

	/**
	 * filterメソッド 子供名前を指定して子供の一覧を取得する
	 *
	 * @param child_name:String
	 *            子供名前
	 * @param facility_id:String
	 *            施設ID
	 * @return 一覧のリスト:List<ChildAbs> 存在しない場合は0件のリスト
	 * @throws Exception
	 */
	public List<ChildAbs> filterbyChildName(String child_name, String facility_id, boolean IsAttend) throws Exception {

		// リストを初期化
		List<ChildAbs> list = new ArrayList<>();
		// コネクションを確立
		Connection connection = getConnection();
		// プリペアードステートメント
		PreparedStatement statement = null;
		// リザルトセット
		ResultSet rSet = null;
		// SQL文の条件
		String condition = "and child_name=?  ";

		// SQL文の在籍フラグ条件
		String conditionIsAttend = "";
		// 在学中のみ表示
		if (IsAttend) {
		    conditionIsAttend = "and is_attend=true  ";
		}




		try {
			// プリペアードステートメントにSQL文をセット
			statement = connection.prepareStatement(baseSql + condition + conditionIsAttend);

			statement.setString(1, facility_id);
			statement.setString(2, child_name);
			// プライベートステートメントを実行
			rSet = statement.executeQuery();

			// リストへの格納処理を実行
			list = postFilter(rSet);
		} catch (Exception e) {
			throw e;
		} finally {
			// プリペアードステートメントを閉じる
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
			// コネクションを閉じる
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
		}
		return list;
	}

	/**
	 * filterメソッド クラスを指定して子供の一覧を取得する
	 *
	 * @param clss_id:String
	 *            クラスID
	 * @param facility_id:String
	 *            施設ID
	 * @return 一覧のリスト:List<ChildAbs> 存在しない場合は0件のリスト
	 * @throws Exception
	 */
	public List<ChildAbs> filterbyClassCd(String class_id, String facility_id, boolean IsAttend) throws Exception {

		// リストを初期化
		List<ChildAbs> list = new ArrayList<>();
		// コネクションを確立
		Connection connection = getConnection();
		// プリペアードステートメント
		PreparedStatement statement = null;
		// リザルトセット
		ResultSet rSet = null;
		// SQL文の条件
		String condition = "and class_id=?  ";

		// SQL文の在籍フラグ条件
		String conditionIsAttend = "";
		// 在学中のみ表示
		if (IsAttend) {
		    conditionIsAttend = "and is_attend=true  ";
		}




		try {
			// プリペアードステートメントにSQL文をセット
			statement = connection.prepareStatement(baseSql + condition + conditionIsAttend);

			statement.setString(1, facility_id);
			statement.setString(2, class_id);
			// プライベートステートメントを実行
			rSet = statement.executeQuery();

			// リストへの格納処理を実行
			list = postFilter(rSet);
		} catch (Exception e) {
			throw e;
		} finally {
			// プリペアードステートメントを閉じる
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
			// コネクションを閉じる
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
		}
		return list;
	}



	/**
	 * filterメソッド クラスと出欠席フラグを指定して子供の一覧を取得する
	 *
	 * @param clss_id:String
	 *            クラスID
	 * @param absIsAttend:Boolean
	 *            出欠席フラグ
	 * @param facility_id:String
	 *            施設ID
	 * @return 一覧のリスト:List<ChildAbs> 存在しない場合は0件のリスト
	 * @throws Exception
	 */
	public List<ChildAbs> filterbyClassAbsAttend(String class_id, String facility_id, boolean absIsAttend, boolean IsAttend) throws Exception {

		// リストを初期化
		List<ChildAbs> list = new ArrayList<>();
		// コネクションを確立
		Connection connection = getConnection();
		// プリペアードステートメント
		PreparedStatement statement = null;
		// リザルトセット
		ResultSet rSet = null;
		// SQL文の条件
		String condition = "and class_id=? ";

		// SQL文の出欠席フラグ条件
		String conditionabsIsAttend = "";
		// 本日欠席のみ表示
		if (absIsAttend) {
		    conditionabsIsAttend = "and abs_is_attend=true  ";
		}

		// SQL文の在籍フラグ条件
		String conditionIsAttend = "";
		// 在籍中のみ表示
		if (IsAttend) {
		    conditionIsAttend = "and is_attend=true  ";
		}



		/**
		 * 修正必須
		 *
		 *
		 * とりあえず欠席情報がある子を欠席状態にする
		 * 後で今日の日付のみを反映させるようにとってくる
		 *
		 *
		 *
		 *
		 */

		try {
			// プリペアードステートメントにSQL文をセット
			statement = connection.prepareStatement(baseSql + conditionabsIsAttend + condition + conditionIsAttend);

			statement.setString(1, facility_id);
			statement.setString(2, class_id);
			// プライベートステートメントを実行
			rSet = statement.executeQuery();

			// リストへの格納処理を実行
			list = postFilter(rSet);
		} catch (Exception e) {
			throw e;
		} finally {
			// プリペアードステートメントを閉じる
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
			// コネクションを閉じる
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
		}
		return list;
	}




	/**
	 * filterメソッド 欠席フラグを指定して子供の一覧を取得する
	 *
	 * @param absIsAttend:Boolean
	 *            出欠席フラグ
	 * @param facility_id:String
	 *            施設ID
	 * @return 一覧のリスト:List<ChildAbs> 存在しない場合は0件のリスト
	 * @throws Exception
	 */
	public List<ChildAbs> filterbyAbsAttend(boolean absIsAttend, String facility_id, boolean IsAttend) throws Exception {

		// リストを初期化
		List<ChildAbs> list = new ArrayList<>();
		// コネクションを確立
		Connection connection = getConnection();
		// プリペアードステートメント
		PreparedStatement statement = null;
		// リザルトセット
		ResultSet rSet = null;

		// SQL文の欠席フラグ条件
		String conditionabsIsAttend = "";
		// 本日欠席のみ表示
		if (absIsAttend) {
		    conditionabsIsAttend = "and abs_is_attend=true  ";
		}

		// SQL文の在籍フラグ条件
		String conditionIsAttend = "";
		// 在籍中のみ表示
		if (IsAttend) {
		    conditionIsAttend = "and is_attend=true  ";
		}



		try {
			// プリペアードステートメントにSQL文をセット
			statement = connection.prepareStatement(baseSql + conditionabsIsAttend + conditionIsAttend);

			statement.setString(1, facility_id);
			// プライベートステートメントを実行
			rSet = statement.executeQuery();

			// リストへの格納処理を実行
			list = postFilter(rSet);
		} catch (Exception e) {
			throw e;
		} finally {
			// プリペアードステートメントを閉じる
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
			// コネクションを閉じる
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
		}
		return list;
	}





}
