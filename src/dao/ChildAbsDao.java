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
			"SELECT child.child_id, child.child_name, child.parents_id, child.class_id, child.is_attend, child.facility_id,absence.abs_is_attend, absence.absence_date "
			+ "FROM child left outer join absence ON child.child_id = absence.child_id and child.facility_id = absence.facility_id  "
			+ "where child.facility_id=? ";


	// absIsAttend 欠席表示の時true 全員false

	/** 子供情報一覧表示のみ使用（欠席情報あり） */
	public List<ChildAbs> getChildListAbsinfo(String facility_id, boolean IsAttend, boolean AbsIsAttend) throws Exception {
		System.out.println("☆★☆４");
		System.out.println("☆★☆５");
		// 戻り値用のマップ（名前をキーにして重複を排除）
	    Map<String, ChildAbs> map = new HashMap<>();
	    Connection connection = getConnection();
	    PreparedStatement st = null;
	    PreparedStatement updateStmt = null; // 欠席フラグ更新用

	    // 本日の日付取得
	    LocalDateTime nowDate = LocalDateTime.now();
	    DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("yyyy/MM/dd");
	    String today_absence_date = dtf1.format(nowDate);

	    // SQL文の在籍フラグ条件
	    String conditionIsAttend = "";
	    if (IsAttend) {
	        conditionIsAttend = "and is_attend=true ";
	    }

	    // SQL文の欠席フラグ条件
	    String conditionAbsIsAttend = "";
	    if (AbsIsAttend) {
	        conditionAbsIsAttend = "and abs_is_attend=true ";
	    }

	    try {
	        // 子供の情報取得
	        st = connection.prepareStatement(baseSql + conditionIsAttend + conditionAbsIsAttend );
	        st.setString(1, facility_id);

	        ResultSet rSet = st.executeQuery();
	        while (rSet.next()) {
	            String childName = rSet.getString("child_name");
	            String absenceDate = rSet.getString("absence_date");

	            // 本日が欠席日であるかどうかを確認
	            boolean todayAbs = absenceDate != null && absenceDate.equals(today_absence_date);

	            // abs_is_attendの日付が本日でないものを'flase'に更新する
	            if (absenceDate != null && !absenceDate.equals(today_absence_date)) {
	                // 今日でない場合、abs_is_attendをfalseに更新
	                if (updateStmt == null) {
	                    updateStmt = connection.prepareStatement(
	                        "UPDATE absence SET abs_is_attend = false WHERE child_id = ? AND facility_id = ? AND absence_date = ?");
	                }
	                updateStmt.setString(1, rSet.getString("child_id"));
	                updateStmt.setString(2, rSet.getString("facility_id"));
	                updateStmt.setString(3, absenceDate);
	                updateStmt.addBatch(); // バッチ処理に追加
	            }

	            // Mapにデータを追加
	            ChildAbs existingChild = map.get(childName);
	            if (existingChild == null || todayAbs || existingChild.getAbsence_date() == null) {
	                ChildAbs childabs = new ChildAbs();
	                childabs.setChild_id(rSet.getString("child_id"));
	                childabs.setChild_name(childName);
	                childabs.setParents_id(rSet.getString("parents_id"));
	                childabs.setClass_id(rSet.getString("class_id"));
	                childabs.setIs_attend(rSet.getBoolean("is_attend"));
	                childabs.setFacility_id(rSet.getString("facility_id"));
	                childabs.setAbs_is_attend(todayAbs);
	                childabs.setAbsence_date(absenceDate);

	                map.put(childName, childabs); // マップに追加または更新
	            }

	        }

	        // バッチで更新を実行
	        if (updateStmt != null) {
	            updateStmt.executeBatch();
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
	        if (updateStmt != null) {
	            try {
	                updateStmt.close();
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




	/** 子供IDで指定して絞り込み */
	public List<ChildAbs> filterbyChildId(String child_id, String facility_id, boolean IsAttend, boolean AbsIsAttend) throws Exception {
		System.out.println("☆★☆１");
		// Mapを使用して名前をキーにし、重複を排除
	    Map<String, ChildAbs> map = new HashMap<>();
	    Connection connection = getConnection();
	    PreparedStatement statement = null;
	    PreparedStatement updateStmt = null; // 欠席フラグ更新用
	    ResultSet rSet = null;

	    // 本日の日付取得
	    LocalDateTime nowDate = LocalDateTime.now();
	    DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("yyyy/MM/dd");
	    String today_absence_date = dtf1.format(nowDate);

	    // SQL文の条件
	    String condition = "and child.child_id=?  ";


	    // SQL文の在籍フラグ条件
	    String conditionIsAttend = "";
	    if (IsAttend) {
	        conditionIsAttend = "and is_attend=true ";
	    }

	    // SQL文の欠席フラグ条件
	    String conditionAbsIsAttend = "";
	    if (AbsIsAttend) {
	        conditionAbsIsAttend = "and abs_is_attend=true ";
	    }



	    try {
	        // プリペアードステートメントにSQL文をセット
	        statement = connection.prepareStatement(baseSql + condition + conditionIsAttend + conditionAbsIsAttend );
	        statement.setString(1, facility_id);
	        statement.setString(2, child_id);


	        rSet = statement.executeQuery();

	        while (rSet.next()) {
	            String childName = rSet.getString("child_name");
	            String absenceDate = rSet.getString("absence_date");

	            // 本日が欠席日であるかどうかを確認
	            boolean todayAbs = absenceDate != null && absenceDate.equals(today_absence_date);

	         // abs_is_attendの日付が本日でないものを'flase'に更新する
	            if (absenceDate != null && !absenceDate.equals(today_absence_date)) {
	                // 今日でない場合、abs_is_attendをfalseに更新
	                if (updateStmt == null) {
	                    updateStmt = connection.prepareStatement(
	                        "UPDATE absence SET abs_is_attend = false WHERE child_id = ? AND facility_id = ? AND absence_date = ?");
	                }
	                updateStmt.setString(1, rSet.getString("child_id"));
	                updateStmt.setString(2, rSet.getString("facility_id"));
	                updateStmt.setString(3, absenceDate);
	                updateStmt.addBatch(); // バッチ処理に追加
	            }

	            // マップにデータを追加
	            ChildAbs existingChild = map.get(childName);

	            if (existingChild == null || todayAbs || existingChild.getAbsence_date() == null) {
	                ChildAbs childabs = new ChildAbs();
	                childabs.setChild_id(rSet.getString("child_id"));
	                childabs.setChild_name(childName);
	                childabs.setParents_id(rSet.getString("parents_id"));
	                childabs.setClass_id(rSet.getString("class_id"));
	                childabs.setIs_attend(rSet.getBoolean("is_attend"));
	                childabs.setFacility_id(rSet.getString("facility_id"));
	                childabs.setAbs_is_attend(todayAbs);
	                childabs.setAbsence_date(absenceDate);

	                map.put(childName, childabs); // マップに追加または更新
	            }
	        }

	        // バッチで更新を実行
	        if (updateStmt != null) {
	            updateStmt.executeBatch();  // バッチ処理を実行
	        }
	    } catch (Exception e) {
	        throw e;
	    } finally {
	        if (statement != null) {
	            try {
	                statement.close();
	            } catch (SQLException sqle) {
	                throw sqle;
	            }
	        }
	        if (updateStmt != null) {
	            try {
	                updateStmt.close();
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


	/** 子供の名前で指定して絞り込み */
	public List<ChildAbs> filterbyChildName(String child_name, String facility_id, boolean IsAttend, boolean AbsIsAttend) throws Exception {
		System.out.println("☆★☆２");
		// Mapを使用して名前をキーにし、重複を排除
	    Map<String, ChildAbs> map = new HashMap<>();
		Connection connection = getConnection();
		PreparedStatement statement = null;
		PreparedStatement updateStmt = null; 				// 欠席フラグ更新用
		ResultSet rSet = null;

	    // 本日の日付取得
	    LocalDateTime nowDate = LocalDateTime.now();
	    DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("yyyy/MM/dd");
	    String today_absence_date = dtf1.format(nowDate);


		// SQL文の条件
		String condition = "and child_name=?  ";

	    // SQL文の在籍フラグ条件
	    String conditionIsAttend = "";
	    if (IsAttend) {
	        conditionIsAttend = "and is_attend=true ";
	    }

	    // SQL文の欠席フラグ条件
	    String conditionAbsIsAttend = "";
	    if (AbsIsAttend) {
	        conditionAbsIsAttend = "and abs_is_attend=true ";
	    }


		try {
			// プリペアードステートメントにSQL文をセット
			statement = connection.prepareStatement(baseSql + condition + conditionIsAttend + conditionAbsIsAttend);

			statement.setString(1, facility_id);
			statement.setString(2, child_name);
			// プライベートステートメントを実行
			rSet = statement.executeQuery();

			while (rSet.next()) {
	            String childName = rSet.getString("child_name");
	            String absenceDate = rSet.getString("absence_date");

	            // 本日が欠席日であるかどうかを確認
	            boolean todayAbs = absenceDate != null && absenceDate.equals(today_absence_date);

	         // abs_is_attendの日付が本日でないものを'flase'に更新する
	            if (absenceDate != null && !absenceDate.equals(today_absence_date)) {
	                // 今日でない場合、abs_is_attendをfalseに更新
	                if (updateStmt == null) {
	                    updateStmt = connection.prepareStatement(
	                        "UPDATE absence SET abs_is_attend = false WHERE child_id = ? AND facility_id = ? AND absence_date = ?");
	                }
	                updateStmt.setString(1, rSet.getString("child_id"));
	                updateStmt.setString(2, rSet.getString("facility_id"));
	                updateStmt.setString(3, absenceDate);
	                updateStmt.addBatch(); // バッチ処理に追加
	            }


	            ChildAbs existingChild = map.get(childName);

	            if (existingChild == null || todayAbs || existingChild.getAbsence_date() == null) {
	                ChildAbs childabs = new ChildAbs();
	                childabs.setChild_id(rSet.getString("child_id"));
	                childabs.setChild_name(childName);
	                childabs.setParents_id(rSet.getString("parents_id"));
	                childabs.setClass_id(rSet.getString("class_id"));
	                childabs.setIs_attend(rSet.getBoolean("is_attend"));
	                childabs.setFacility_id(rSet.getString("facility_id"));
	                childabs.setAbs_is_attend(todayAbs);
	                childabs.setAbsence_date(absenceDate);

	                map.put(childName, childabs); // マップに追加または更新

	            }
	        }
            // バッチで更新を実行
            if (updateStmt != null) {
                updateStmt.executeBatch();  // バッチ処理を実行
            }

	    } catch (Exception e) {
	        throw e;
	    } finally {
	        if (statement != null) {
	            try {
	                statement.close();
	            } catch (SQLException sqle) {
	                throw sqle;
	            }
	        }
	        if (updateStmt != null) {
	            try {
	                updateStmt.close();
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



	/** クラスを指定して子供の一覧を取得する */
	public List<ChildAbs> filterbyClassCd(String class_id, String facility_id, boolean IsAttend, boolean AbsIsAttend) throws Exception {
		System.out.println("☆★☆３");
		// Mapを使用して名前をキーにし、重複を排除
	    Map<String, ChildAbs> map = new HashMap<>();
		Connection connection = getConnection();
		PreparedStatement statement = null;
		PreparedStatement updateStmt = null; 				// 欠席フラグ更新用
		ResultSet rSet = null;

	    // 本日の日付取得
	    LocalDateTime nowDate = LocalDateTime.now();
	    DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("yyyy/MM/dd");
	    String today_absence_date = dtf1.format(nowDate);


		// SQL文の条件
		String condition = "and class_id=?  ";

	    // SQL文の在籍フラグ条件
	    String conditionIsAttend = "";
	    if (IsAttend) {
	        conditionIsAttend = "and is_attend=true ";
	    }

	    // SQL文の欠席フラグ条件
	    String conditionAbsIsAttend = "";
	    if (AbsIsAttend) {
	        conditionAbsIsAttend = "and abs_is_attend=true ";
	    }



		try {
			// プリペアードステートメントにSQL文をセット
			statement = connection.prepareStatement(baseSql + condition + conditionIsAttend + conditionAbsIsAttend );

			statement.setString(1, facility_id);
			statement.setString(2, class_id);

			// プライベートステートメントを実行
			rSet = statement.executeQuery();


			while (rSet.next()) {
	            String childName = rSet.getString("child_name");
	            String absenceDate = rSet.getString("absence_date");

	            // 本日が欠席日であるかどうかを確認
	            boolean todayAbs = absenceDate != null && absenceDate.equals(today_absence_date);

	            // abs_is_attendの日付が本日でないものを'flase'に更新する
	            if (absenceDate != null && !absenceDate.equals(today_absence_date)) {
	                // 今日でない場合、abs_is_attendをfalseに更新
	                if (updateStmt == null) {
	                    updateStmt = connection.prepareStatement(
	                        "UPDATE absence SET abs_is_attend = false WHERE child_id = ? AND facility_id = ? AND absence_date = ?");
	                }
	                updateStmt.setString(1, rSet.getString("child_id"));
	                updateStmt.setString(2, rSet.getString("facility_id"));
	                updateStmt.setString(3, absenceDate);
	                updateStmt.addBatch(); // バッチ処理に追加
	            }


	            ChildAbs existingChild = map.get(childName);

	            if (existingChild == null || todayAbs || existingChild.getAbsence_date() == null) {
	                ChildAbs childabs = new ChildAbs();
	                childabs.setChild_id(rSet.getString("child_id"));
	                childabs.setChild_name(childName);
	                childabs.setParents_id(rSet.getString("parents_id"));
	                childabs.setClass_id(rSet.getString("class_id"));
	                childabs.setIs_attend(rSet.getBoolean("is_attend"));
	                childabs.setFacility_id(rSet.getString("facility_id"));
	                childabs.setAbs_is_attend(todayAbs);
	                childabs.setAbsence_date(absenceDate);

	                map.put(childName, childabs); // マップに追加または更新
	            }
	        }
	        // バッチで更新を実行
	        if (updateStmt != null) {
	            updateStmt.executeBatch();  // バッチ処理を実行
	        }
	    } catch (Exception e) {
	        throw e;
	    } finally {
	        if (statement != null) {
	            try {
	                statement.close();
	            } catch (SQLException sqle) {
	                throw sqle;
	            }
	        }
	        if (updateStmt != null) {
	            try {
	                updateStmt.close();
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


}


