package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.Facility;


public class FacilityDao extends Dao{

	public Facility getFacilityInfo(String facility_id) throws Exception {

		//先生インスタンス初期化
		Facility fc = new Facility();

		//データベースへのコネクション
		Connection connection =getConnection();

		//プリペアードステートメント
		PreparedStatement statement=null;

		try{
			//prepareにsql文セット
			statement=connection.prepareStatement("select * from facility where facility_id=? ");
			//バインド
			statement.setString(1, facility_id);
			//プリペアードステートメント実行
			ResultSet rSet=statement.executeQuery();

			if(rSet.next()){
				fc.setFacility_id(rSet.getString("facility_id"));
				fc.setFacility_name(rSet.getString("facility_name"));
				fc.setFacility_address(rSet.getString("facility_address"));
				fc.setFacility_tel(rSet.getInt("facility_tel"));
				fc.setFacility_mail(rSet.getString("facility_mail"));
				fc.setPlan_is_attend(rSet.getBoolean("plan_is_attend"));
			}else{
				//リザルトセットが存在しない場合
				fc=null;
			}
		}catch(Exception e){
			throw e;
		}finally{
			//プリペア閉じる
			if(statement !=null){
				try{
					statement.close();
				}catch(SQLException sqle){
					throw sqle;
				}
			}
			if(connection !=null){
				try{
					connection.close();
				}catch(SQLException sqle){
					throw sqle;
				}
			}
		}
		return fc;
	}

	public boolean saveFacilityInfo(Facility fc) throws Exception {
		//コネクションを確立
		Connection connection = getConnection();
		//プリペアードステートメント
		PreparedStatement statement = null;
		//実行件数
		int count = 0;

		try{
			//プリペアードステートメントにUPDATE文をセット
			statement = connection
					.prepareStatement("update Facility set facility_id=?, facility_name=?, facility_address=?, facility_tel=?, facility_mail=?, plan_is_attend=? where facility_id=?");
			//プリペアードステートメントに値をバインド
			statement.setString(1, fc.getFacility_id());
			statement.setString(2, fc.getFacility_name());
			statement.setString(3, fc.getFacility_address());
			statement.setInt(4, fc.getFacility_tel());
			statement.setString(5, fc.getFacility_mail());
			statement.setBoolean(6, fc.getPlan_is_attend());
			statement.setString(7, fc.getFacility_id());
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

}
