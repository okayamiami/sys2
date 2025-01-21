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

	private String baseSql = "SELECT * FROM Bus WHERE facility_id = ? ";
	private String sql1 = "AND bus_is_attend = true ";
	private String sql2 = "AND bus_id = ? ";

	// バスの情報リスト（削除されているものは含まない）
	public List<Bus> getBus(String facility_id) throws Exception {
	    // 戻り値用のリスト
	    List<Bus> list = new ArrayList<>();
	    Connection connection = getConnection();
	    PreparedStatement st = null;

	    try {
	        st = connection.prepareStatement(baseSql + sql1);
	        st.setString(1, facility_id); // facility_id をバインド
	        ResultSet rSet = st.executeQuery();

	        while (rSet.next()) {
	            Bus bus = new Bus();
	            bus.setBus_id(rSet.getString("bus_id"));
	            bus.setBus_name(rSet.getString("bus_name"));
	            bus.setFacility_id(rSet.getString("facility_id"));
	            list.add(bus);
	        }
	    } catch (Exception e) {
	        throw e;
	    } finally {
	        // PreparedStatement を閉じる
	        if (st != null) {
	            try {
	                st.close();
	            } catch (SQLException sqle) {
	                throw sqle;
	            }
	        }

	        // Connection を閉じる
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


	// バスの単体情報取得
	public Bus getBusinfo(String facility_id, String bus_id) throws Exception {
	    Bus bus = null; // 初期値を null に設定
	    Connection connection = getConnection();
	    PreparedStatement st = null;

	    try {
	        st = connection.prepareStatement(baseSql + sql1 + sql2);
	        st.setString(1, facility_id);
	        st.setString(2, bus_id);
	        ResultSet rSet = st.executeQuery();

	        if (rSet.next()) {
	            bus = new Bus(); // バス情報がある場合にインスタンスを作成
	            bus.setBus_id(rSet.getString("bus_id"));
	            bus.setBus_name(rSet.getString("bus_name"));
	            bus.setFacility_id(rSet.getString("facility_id"));
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
	    return bus;
	}

	// バスの情報リスト（削除されているものも含む）
		public List<Bus> getAllBus(String facility_id) throws Exception {
		    // 戻り値用のリスト
		    List<Bus> list = new ArrayList<>();
		    Connection connection = getConnection();
		    PreparedStatement st = null;

		    try {
		        st = connection.prepareStatement(baseSql);
		        st.setString(1, facility_id); // facility_id をバインド
		        ResultSet rSet = st.executeQuery();

		        while (rSet.next()) {
		            Bus bus = new Bus();
		            bus.setBus_id(rSet.getString("bus_id"));
		            bus.setBus_name(rSet.getString("bus_name"));
		            bus.setFacility_id(rSet.getString("facility_id"));
		            list.add(bus);
		        }
		    } catch (Exception e) {
		        throw e;
		    } finally {
		        // PreparedStatement を閉じる
		        if (st != null) {
		            try {
		                st.close();
		            } catch (SQLException sqle) {
		                throw sqle;
		            }
		        }

		        // Connection を閉じる
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


	// バス情報新規登録
	public boolean saveBus(Bus bus) throws Exception {
	    // コネクションを確立
	    Connection connection = getConnection();
	    // プリペアードステートメント
	    PreparedStatement statement = null;
	    // 実行件数
	    int count = 0;

	    try {
	        // データベースからバス情報を取得
	        Bus bus_single = getBusinfo(bus.getFacility_id(), bus.getBus_id());
	        if (bus_single == null) {
	            // バス情報が存在しなかった場合
	            // プリペアードステートメントに INSERT 文をセット
	            statement = connection.prepareStatement(
	                "INSERT INTO Bus (bus_id, bus_name, facility_id, bus_is_attend) VALUES (?, ?, ?, true)"
	            );
	            // プリペアードステートメントに値をバインド
	            statement.setString(1, bus.getBus_id());
	            statement.setString(2, bus.getBus_name());
	            statement.setString(3, bus.getFacility_id());
	        } else {
	            // バスが存在した場合
	            // プリペアードステートメントに UPDATE 文をセット
	            statement = connection.prepareStatement(
	                "UPDATE Bus SET bus_name = ? WHERE bus_id = ? AND facility_id = ? AND bus_is_attend = true"
	            );
	            // プリペアードステートメントに値をバインド
	            statement.setString(1, bus.getBus_name());
	            statement.setString(2, bus.getBus_id());
	            statement.setString(3, bus.getFacility_id());
	        }

	        // プリペアードステートメントを実行
	        count = statement.executeUpdate();

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

	        if (connection != null) {
	            try {
	                connection.close();
	            } catch (SQLException sqle) {
	                throw sqle;
	            }
	        }
	    }

	    // 実行件数が 1 以上の場合
	    return count > 0;
	}


	public boolean setBusAsNotAttend(String facilityId, String busId) throws Exception {
	    // コネクションを確立
	    Connection connection = getConnection();
	    // プリペアードステートメント
	    PreparedStatement statement = null;
	    // 実行件数
	    int count = 0;

	    try {
	        // データベースからバス情報を取得
	        Bus busSingle = getBusinfo(facilityId, busId);
	        if (busSingle != null) {
	            // バス情報が存在する場合のみフラグを更新
	            statement = connection.prepareStatement(
	                    "UPDATE Bus SET bus_is_attend = false WHERE bus_id = ? AND facility_id = ?");
	            // プリペアードステートメントに値をバインド
	            statement.setString(1, busId);
	            statement.setString(2, facilityId);

	            // プリペアードステートメントを実行
	            count = statement.executeUpdate();
	        }
	    } catch (Exception e) {
	        throw e; // 例外を再スロー
	    } finally {
	        // ステートメントを閉じる
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

	    // 実行件数が1以上ならtrue、そうでなければfalseを返す
	    return count > 0;
	}


}
