package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.ChildDetail;


public class ChildDetailDao extends Dao{

	/**
	 * baseSql:String 共通SQL文 プライベート
	 */

	// 施設IDと保護者IDで条件
	private String baseSql =
			"SELECT child.child_id, child.child_name, child.class_id, child.parents_id, child.facility_id, parentsuser.parents_id, parentsuser.parents_name, parentsuser.parents_address, parentsuser.parents_tel, parentsuser.parents_mail1, parentsuser.parents_mail2, parentsuser.parents_mail3 "
			+ "FROM child left outer join parentsuser ON child.facility_id = parentsuser.facility_id and child.parents_id = parentsuser.parents_id "
			+ "where child.facility_id=? and child.parents_id=? ";



	/** 子供詳細情報一覧表示 */
	public ChildDetail getChildDetailinfo(String facility_id, String parents_id, String child_id) throws Exception {

		ChildDetail cdetail = new ChildDetail();
	    Connection connection = getConnection();
	    PreparedStatement st = null;

	    // 子供IDで条件文（親に対して2人以上子供がいる可能性があるため）
	    String condition = "and child_id = ? ";


	    try {
	        // 子供の情報取得
	        st = connection.prepareStatement(baseSql + condition );
	        st.setString(1, facility_id);
	        st.setString(2, parents_id);
	        st.setString(3, child_id);

	        ResultSet rSet = st.executeQuery();

			if(rSet.next()){
				cdetail.setChild_id(rSet.getString("child_id"));
				cdetail.setChild_name(rSet.getString("child_name"));
				cdetail.setClass_id(rSet.getString("class_id"));
				cdetail.setParents_id(rSet.getString("parents_id"));
				cdetail.setParents_name(rSet.getString("parents_name"));
				cdetail.setParents_address(rSet.getString("parents_address"));
				cdetail.setParents_tel(rSet.getString("parents_tel"));
				cdetail.setParents_mail1(rSet.getString("parents_mail1"));
				cdetail.setParents_mail2(rSet.getString("parents_mail2"));
				cdetail.setParents_mail3(rSet.getString("parents_mail3"));
				cdetail.setFacility_id(rSet.getString("facility_id"));
			}else{
				cdetail=null;
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

	    return cdetail;
	}
}


