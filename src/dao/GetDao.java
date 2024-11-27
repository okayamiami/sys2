package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Bus;
import bean.Child;
import bean.Facility;
import bean.Get;
import tool.EmailService;

public class GetDao extends Dao{

	private String baseSql = "select * from Get WHERE facility_id=? ";

	public Get getGetinfo(String bus_id, String child_id, String facility_id) throws Exception{
		Get get = new Get();
		Connection connection = getConnection();
		PreparedStatement st = null;
		String sql2 = "and bus_id=? ";
		String sql3 = "and child_id=? ";

		try{
			st = connection.prepareStatement(baseSql+sql2+sql3);
			st.setString(1,facility_id);
			st.setString(2,bus_id);
			st.setString(3,child_id);
			ResultSet rSet = st.executeQuery();

			if(rSet.next()){
				get.setBus_id(rSet.getString("bus_id"));
				get.setChild_id(rSet.getString("child_id"));
				get.setGet_is_attend(rSet.getBoolean("get_is_attend"));
				get.setFacility_id(rSet.getString("facility_id"));
			}else{
				get=null;
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
		return get;

	}

	public List<Get> getGetListinfo(String bus_id, String facility_id)throws Exception{
		//戻り値用のリスト
		List<Get> list = new ArrayList<>();
		Connection connection = getConnection();
		PreparedStatement st = null;

		try{
			st = connection.prepareStatement(baseSql);
			st.setString(1,facility_id);
			ResultSet rSet = st.executeQuery();


			//SQLで取得したGet情報を全てリストに詰め込む
			while(rSet.next()){
				Get get = new Get();
				get.setBus_id(rSet.getString("bus_id"));
				get.setChild_id(rSet.getString("child_id"));
				get.setGet_is_attend(rSet.getBoolean("get_is_attend"));
				get.setFacility_id(rSet.getString("facility_id"));
				list.add(get);
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


	public boolean changeGet(String bus_id, String child_id, String facility_id) throws Exception {
	    Connection connection = getConnection();
	    PreparedStatement statement = null;
	    int count = 0;

	    GetDao gDao = new GetDao();

	    try {
	        // データベースから学生情報を取得
	        Get get = gDao.getGetinfo(bus_id, child_id, facility_id);
	        if (get == null) {
	            throw new IllegalStateException("Get情報が見つかりません: bus_id=" + bus_id + ", child_id=" + child_id + ", facility_id=" + facility_id);
	        }

	        // SQL 文の準備
	        statement = connection.prepareStatement(
	            "UPDATE Get SET get_is_attend=? WHERE bus_id=? AND child_id=? AND facility_id=?");
	        statement.setBoolean(1, !get.isGet_is_attend()); // 現在の値を反転
	        statement.setString(2, get.getBus_id());
	        statement.setString(3, get.getChild_id());
	        statement.setString(4, get.getFacility_id());

	        // プリペアードステートメントを実行
	        count = statement.executeUpdate();

	        // 反転後の値が false なら処理を実行
	        if (count > 0 && !get.isGet_is_attend()) {
	            // Daoインスタンス化
	            FacilityDao fDao = new FacilityDao();
	            Facility facility = fDao.getFacilityInfo(facility_id);
	            if (facility != null) {
	                String facilityMail = facility.getFacility_mail();
	                ParentsUserDao puDao = new ParentsUserDao();
	                List<String> parentsMails = puDao.getParentsEmails(child_id);

	                // メールを送信
	                if (facilityMail != null && !parentsMails.isEmpty()) {
	                    for (String parentMail : parentsMails) {
	                        // nullまたは空白の場合はスキップ
	                        if (parentMail == null || parentMail.trim().isEmpty()) {
	                            continue; // continueで次の親メールに進む
	                        }

	                        // メールアドレス形式が不正な場合もスキップ
	                        if (!isValidEmail(parentMail)) {
	                            continue; // continueで次の親メールに進む
	                        }

	                        // 正常なメールアドレスの場合はメール送信
	                        EmailService es = new EmailService();
	                        es.sendEmail(facility_id, parentMail, "保護者の方へ", "お子さんは無事に登園完了しましたよ！^^");
	                    }
	                }
	            }
	        }

	    } catch (Exception e) {
	        // デバッグログ: エラー詳細
	        e.printStackTrace();
	        throw e;
	    } finally {
	        // リソースのクリーンアップ
	        if (statement != null) {
	            try {
	                statement.close();
	            } catch (SQLException sqle) {
	                sqle.printStackTrace();
	            }
	        }
	        if (connection != null) {
	            try {
	                connection.close();
	            } catch (SQLException sqle) {
	                sqle.printStackTrace();
	            }
	        }
	    }

	    return count > 0;
	}

	// メールアドレス形式を検証するメソッド
	public boolean isValidEmail(String email) {
	    String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
	    return email != null && email.matches(emailRegex);
	}


	// 施設に存在するすべてのバスに対して、Getテーブルに子供情報をデータベースに登録
	public boolean saveGetInfoForAllBuses(Child child) throws Exception {
	    // コネクションを確立
	    Connection connection = getConnection();
	    // プリペアードステートメント
	    PreparedStatement statement = null;
	    // 実行件数
	    int count = 0;
	    BusDao bDao = new BusDao();

	    try {
	        // 施設に存在するすべてのバスを取得
	        List<Bus> buses = bDao.getBus(child.getFacility_id());

	        for (Bus bus : buses) {
	            // Getテーブルへの挿入
	            statement = connection.prepareStatement(
	                    "insert into Get (bus_id, child_id, get_is_attend, facility_id) values(?, ?, ?, ?)");

	            statement.setString(1, bus.getBus_id()); // バスID
	            statement.setString(2, child.getChild_id()); // 子供ID
	            statement.setBoolean(3, false); // get_is_attend (初期値はfalse)
	            statement.setString(4, child.getFacility_id()); // 施設ID

	            // プリペアードステートメントを実行
	            count += statement.executeUpdate();
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

	        if (connection != null) {
	            try {
	                connection.close();
	            } catch (SQLException sqle) {
	                throw sqle;
	            }
	        }
	    }

	    // 実行件数が1以上であれば成功
	    return count > 0;
	}

}
