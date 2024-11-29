/* 継承クラス */
//テキストｐ235・236
//「dao」の「DAO.java」を継承

//「chapter14」の「Seach.java」と同じことをしている(やり方はこっちのほうが簡略化されてる)

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Bus;

public class BusDao extends Dao {

	private String baseSql = "select * from Bus WHERE facility_id = ? ";

	// バスの情報リスト
	public List<Bus> getBus(String facility_id) throws Exception {
		//戻り値用のリスト
		List<Bus> list = new ArrayList<>();
		Connection connection = getConnection();
		PreparedStatement st = null;

		try{
			st = connection.prepareStatement(baseSql);
			st.setString(1,facility_id);
			ResultSet rSet = st.executeQuery();

			while(rSet.next()){
				Bus bus = new Bus();
				bus.setBus_id(rSet.getString("bus_id"));
				bus.setBus_name(rSet.getString("bus_name"));
				bus.setFacility_id(rSet.getString("facility_id"));
				list.add(bus);
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


	// バスの単体情報取得
	public Bus getBusinfo(String facility_id,String bus_id)throws Exception{
		Bus bus = new Bus();
		Connection connection = getConnection();
		PreparedStatement st = null;

		String sql2 = "and bus_id = ? ";
		try{
			st = connection.prepareStatement(baseSql+sql2);
			st.setString(1,facility_id);
			st.setString(2,bus_id);
			ResultSet rSet = st.executeQuery();

			if(rSet.next()){
				bus.setBus_id(rSet.getString("bus_id"));
				bus.setBus_name(rSet.getString("bus_name"));
				bus.setFacility_id(rSet.getString("facility_id"));
			}else{
				bus=null;
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
		return bus;
	}


	// バス情報新規登録
	public boolean saveBus(Bus bus) throws Exception{
		//コネクションを確立
		Connection connection = getConnection();
		//プリペアードステートメント
		PreparedStatement statement = null;
		//実行件数
		int count = 0;

		try{
			//データベースからバス情報を取得
			Bus bus_single = getBusinfo(bus.getFacility_id(), bus.getBus_id());
			if (bus_single == null) {
				// バス情報が存在しなかった場合
				// プリペアードステートメンにINSERT文をセット
				statement = connection.prepareStatement(
						"insert into Bus (bus_id, bus_name, facility_id) values(?, ?, ?) ");
				//プリペアードステートメントに値をバインド
				statement.setString(1, bus.getBus_id());
				statement.setString(2, bus.getBus_name());
				statement.setString(3, bus.getFacility_id());
			} else {
				//子供が存在した場合
				//プリペアードステートメントにUPDATE文をセット
				statement = connection
						.prepareStatement("update Bus set bus_name=? where bus_id=? and facility_id=?");
				//プリペアードステートメントに値をバインド
				statement.setString(1, bus_single.getBus_name());
				statement.setString(2, bus_single.getBus_id());
				statement.setString(3, bus_single.getFacility_id());
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

}
