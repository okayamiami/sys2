package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Child;

public class ChildDao extends Dao{

	private String baseSql = "select * from Child WHERE facility_id = ? ";

	//子供個人の情報取得用
	public Child getChildinfo(String facility_id,String child_id)throws Exception{
		Child child = new Child();
		Connection connection = getConnection();
		PreparedStatement st = null;

		String sql2 = "and child_id = ? ";
		try{
			st = connection.prepareStatement(baseSql+sql2);
			st.setString(1,facility_id);
			st.setString(2,child_id);
			ResultSet rSet = st.executeQuery();


			if(rSet.next()){
				child.setChild_id(rSet.getString("child_id"));
				child.setChild_name(rSet.getString("child_name"));
				child.setParents_id(rSet.getString("parents_id"));
				child.setClass_id(rSet.getString("class_id"));
				child.setIs_attend(rSet.getBoolean("is_attend"));
				child.setFacility_id(rSet.getString("facility_id"));

			}else{
				child=null;
			}
		}catch(Exception e){
			throw e;
		} finally {
			//
			if(st != null) {
				try {
					st.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}

			if(connection != null) {
				try {
					connection.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}


		}
		return child;
	}

	//子供全員表示
	public List<Child> getChildListinfo(String facility_id)throws Exception{
		//戻り値用のリスト
		List<Child> list = new ArrayList<>();
		Connection connection = getConnection();
		PreparedStatement st = null;
		String sort = " ORDER BY child_id";

		try{
			st = connection.prepareStatement(baseSql+sort);
			st.setString(1,facility_id);
			ResultSet rSet = st.executeQuery();

			while(rSet.next()){
				Child child = new Child();
				child.setChild_id(rSet.getString("child_id"));
				child.setChild_name(rSet.getString("child_name"));
				child.setParents_id(rSet.getString("parents_id"));
				child.setClass_id(rSet.getString("class_id"));
				child.setIs_attend(rSet.getBoolean("is_attend"));
				child.setFacility_id(rSet.getString("facility_id"));
				list.add(child);
			}
		}catch(Exception e){
			throw e;
		} finally {
			//
			if(st != null) {
				try {
					st.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}

			if(connection != null) {
				try {
					connection.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}


		}
		return list;
	}

	// 保護者IDで子供情報を取得
	public List<Child> getChildrenByParentId(String parents_id, String facility_id) throws Exception {
		List<Child> list = new ArrayList<>();
		Connection connection = getConnection();
		PreparedStatement st = null;

		String sql = "select * from Child where parents_id = ? and facility_id=? and is_attend=true";
		try {
			st = connection.prepareStatement(sql);
			st.setString(1, parents_id);
			st.setString(2, facility_id);
			ResultSet rSet = st.executeQuery();

			while (rSet.next()) {
				Child child = new Child();
				child.setChild_id(rSet.getString("child_id"));
				child.setChild_name(rSet.getString("child_name"));
				child.setParents_id(rSet.getString("parents_id"));
				child.setClass_id(rSet.getString("class_id"));
				child.setIs_attend(rSet.getBoolean("is_attend"));
				child.setFacility_id(rSet.getString("facility_id"));
				list.add(child);
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
		return list;
	}


	// 保護者IDで(在籍中の)子供情報を取得 ： 欠席報告で利用
	public List<Child> getAttendChildrenByParentId(String parents_id, String facility_id) throws Exception {
		List<Child> list = new ArrayList<>();
		Connection connection = getConnection();
		PreparedStatement st = null;
		Boolean attend = true;

		String sql = "select * from Child where parents_id = ? and facility_id=? and is_attend=?";
		try {
			st = connection.prepareStatement(sql);
			st.setString(1, parents_id);
			st.setString(2, facility_id);
			st.setBoolean(3, attend);
			ResultSet rSet = st.executeQuery();

			while (rSet.next()) {
				Child child = new Child();
				child.setChild_id(rSet.getString("child_id"));
				child.setChild_name(rSet.getString("child_name"));
				child.setParents_id(rSet.getString("parents_id"));
				child.setClass_id(rSet.getString("class_id"));
				child.setIs_attend(rSet.getBoolean("is_attend"));
				child.setFacility_id(rSet.getString("facility_id"));
				list.add(child);
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
		return list;
	}

	// 施設の(在籍中の)子供情報を取得 ： 欠席報告で利用
	public List<Child> getAttendChildListinfo(String facility_id)throws Exception{
		//戻り値用のリスト
		List<Child> list = new ArrayList<>();
		Connection connection = getConnection();
		PreparedStatement st = null;
		Boolean attend = true;

		String sql2 = "and is_attend=? ";

		try{
			st = connection.prepareStatement(baseSql + sql2);
			st.setString(1,facility_id);
			st.setBoolean(2, attend);
			ResultSet rSet = st.executeQuery();

			while(rSet.next()){
				Child child = new Child();
				child.setChild_id(rSet.getString("child_id"));
				child.setChild_name(rSet.getString("child_name"));
				child.setParents_id(rSet.getString("parents_id"));
				child.setClass_id(rSet.getString("class_id"));
				child.setIs_attend(rSet.getBoolean("is_attend"));
				child.setFacility_id(rSet.getString("facility_id"));
				list.add(child);
			}
		}catch(Exception e){
			throw e;
		} finally {
			//
			if(st != null) {
				try {
					st.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}

			if(connection != null) {
				try {
					connection.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}


		}
		return list;
	}


	// 子供の名前から子供IDを取得
	public Child getChildIdinfo(String facility_id,String child_name)throws Exception{
		Child child = new Child();
		Connection connection = getConnection();
		PreparedStatement st = null;


		String sql2 = "and child_name = ? ";
		try{
			st = connection.prepareStatement(baseSql+sql2);
			st.setString(1,facility_id);
			st.setString(2,child_name);
			ResultSet rSet = st.executeQuery();

			if(rSet.next()){
				child.setChild_id(rSet.getString("child_id"));
			}

		}catch(Exception e){
			throw e;
		} finally {
			//
			if(st != null) {
				try {
					st.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}

			if(connection != null) {
				try {
					connection.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}


		}
		return child;
	}



	//子供情報のデータベース登録
	public boolean saveChildinfo(Child child) throws Exception{
				//コネクションを確立
				Connection connection = getConnection();
				//プリペアードステートメント
				PreparedStatement statement = null;
				//実行件数
				int count = 0;

				try{
					//データベースから子供を取得
					Child old = getChildinfo(child.getFacility_id(), child.getChild_id());
					if (old == null) {
						//子供が存在しなかった場合
						//プリペアードステートメンにINSERT文をセット
						statement = connection.prepareStatement(
								"insert into Child (child_id, child_name, parents_id, class_id, is_attend, facility_id) values(?, ?, ?, ?, ?, ?) ");
						//プリペアードステートメントに値をバインド
						statement.setString(1, child.getChild_id());
						statement.setString(2, child.getChild_name());
						statement.setString(3, child.getParents_id());
						statement.setString(4, child.getClass_id());
						statement.setBoolean(5, child.is_attend());
						statement.setString(6, child.getFacility_id());
					} else {
						//子供が存在した場合
						//プリペアードステートメントにUPDATE文をセット
						statement = connection
								.prepareStatement("update Child set child_name=?, parents_id=?, class_id=?, is_attend=?, facility_id=? where child_id=? and facility_id=?");
						//プリペアードステートメントに値をバインド
						statement.setString(1, child.getChild_name());
						statement.setString(2, child.getParents_id());
						statement.setString(3, child.getClass_id());
						statement.setBoolean(4, child.is_attend());
						statement.setString(5, child.getFacility_id());
						statement.setString(6, child.getChild_id());
						statement.setString(7, child.getFacility_id());
					}

					//プリペアードステートメントを実行
					count = statement.executeUpdate();

				} catch (Exception e) {
					throw e;
				} finally {
					//
					if(statement != null) {
						try {
							statement.close();
						} catch (SQLException sqle) {
							throw sqle;
						}
					}

					if(connection != null) {
						try {
							connection.close();
						} catch (SQLException sqle) {
							throw sqle;
						}
					}
				}

				if (count > 0) {
					//実行件数が1以上ある場合
					return true;
				} else {
					//実行件数が0件の場合
					return false;
				}

	}

	//子供情報非表示・削除
	public boolean deleteChildinfo(String facility_id, String child_id) throws Exception{
				//コネクションを確立
				Connection connection = getConnection();
				//プリペアードステートメント
				PreparedStatement statement = null;
				//実行件数
				int count = 0;

				try{

					//プリペアードステートメントにUPDATE文をセット
					statement = connection
							.prepareStatement("update Child set is_attend=false where child_id=? and facility_id=?");
					//プリペアードステートメントに値をバインド

					statement.setString(1, child_id);
					statement.setString(2, facility_id);

					System.out.println(statement);


					//プリペアードステートメントを実行
					count = statement.executeUpdate();

				} catch (Exception e) {
					throw e;
				} finally {
					//
					if(statement != null) {
						try {
							statement.close();
						} catch (SQLException sqle) {
							throw sqle;
						}
					}

					if(connection != null) {
						try {
							connection.close();
						} catch (SQLException sqle) {
							throw sqle;
						}
					}
				}

				if (count > 0) {
					//実行件数が1以上ある場合
					return true;
				} else {
					//実行件数が0件の場合
					return false;
				}

	}


	//クラス名と施設CDからクラスIDを入手(新規子供で使う)
	public String getClassCdByName(String className, String facilityId) throws Exception {
	    String classCd = null;
	    // コネクションを確立
	    try (Connection connection = getConnection()) {
	        // クエリの準備
	        String query = "SELECT class_id FROM ClassCd WHERE class_name = ? AND facility_id = ?";
	        try (PreparedStatement statement = connection.prepareStatement(query)) {
	            statement.setString(1, className);  // class_name
	            statement.setString(2, facilityId); // facility_id
	            try (ResultSet resultSet = statement.executeQuery()) {
	                if (resultSet.next()) {
	                    classCd = resultSet.getString("class_id");  // class_cd
	                }
	            }
	        }
	    } catch (SQLException e) {
	        throw new Exception("Error fetching class_cd by class_name", e);
	    }
	    return classCd;
	}


}
