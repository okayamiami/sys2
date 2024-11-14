package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Information;

public class InformationDao extends Dao{

	private String baseSql = "select * from Information where facility_id=? ";

	// お知らせ全件表示
	public List<Information> getInfoList(String facility_id) throws Exception {
		// 戻り値用のリストを作成
		// new演算子とArrayListで空のListを用意
		List<Information> list = new ArrayList<>();

		// データベースへのコネクションを確立
		// データベースに接続された！！
		Connection connection = getConnection();

		// プリペアードステートメント
		PreparedStatement statement = null;

		try {

			// プリペアードステートメントにSQL文をセット
			statement = connection.prepareStatement(baseSql);

			//プレースホルダー（？の部分）に値を設定
			statement.setString(1,facility_id);

			// プリペアードステートメントを実行
			// SQL文を実行する
			// 結果はリザルトセット型となる
			ResultSet rSet = statement.executeQuery();

			// リザルトセット（結果）を全件走査
			while (rSet.next()){
				Information info = new Information();
				// 戻り値用のlistにクラスIDを追加
				info.setInfo_id(rSet.getString("info_id"));
				info.setInfo_title(rSet.getString("info_title"));
				info.setInfo_main(rSet.getString("info_main"));
				info.setFacility_id(rSet.getString("facility_id"));
				info.setInfo_genre(rSet.getString("info_genre"));
				info.setInfo_date(rSet.getTimestamp("info_date"));
				list.add(info);

			}
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


	//お知らせ1件詳細情報取得
	public Information getInfoMain(String facility_id,String info_id)throws Exception{
		Information infomain = new Information();
		Connection connection = getConnection();
		PreparedStatement st = null;

		String sql2 = "and info_id = ? ";
		try{
			st = connection.prepareStatement(baseSql+sql2);
			st.setString(1,facility_id);
			st.setString(2,info_id);
			ResultSet rSet = st.executeQuery();

			if(rSet.next()){
				infomain.setInfo_id(rSet.getString("info_id"));
				infomain.setInfo_title(rSet.getString("info_title"));
				infomain.setInfo_main(rSet.getString("info_main"));
				infomain.setFacility_id(rSet.getString("facility_id"));
				infomain.setInfo_genre(rSet.getString("info_genre"));
				infomain.setInfo_date(rSet.getTimestamp("info_date"));
			}else{
				infomain=null;
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
		return infomain;
	}


	public boolean saveInfo(String infoTitle, String infoMain, String facilityId, String infoGenre) throws Exception {
	    Connection connection = null;
	    PreparedStatement statement = null;
	    int count = 0;

	    try {
	        // コネクションを確立
	        connection = getConnection();

	        // 現在の日付と時刻を java.sql.Timestamp 型で取得（秒まで）
	        java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(System.currentTimeMillis());

	        // 新しい info_id を生成
	        String newInfoId = generateNewInfoId(connection);

	        // プリペアードステートメントに INSERT 文をセット
	        statement = connection.prepareStatement(
	            "INSERT INTO Information (info_id, info_title, info_main, facility_id, info_genre, info_date) VALUES (?, ?, ?, ?, ?, ?)"
	        );

	        // プリペアードステートメントに値をバインド
	        statement.setString(1, newInfoId);
	        statement.setString(2, infoTitle);
	        statement.setString(3, infoMain);
	        statement.setString(4, facilityId);
	        statement.setString(5, infoGenre);
	        statement.setTimestamp(6, sqlTimestamp);  // java.sql.Timestamp を使用

	        // プリペアードステートメントを実行
	        count = statement.executeUpdate();

	    } catch (Exception e) {
	        throw e;
	    } finally {
	        // ステートメントとコネクションのクローズ
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

	    // 実行件数が1以上ある場合は成功
	    return count > 0;
	}


	 public String generateNewInfoId(Connection connection) throws SQLException {
	        String prefix = "24Y";
	        int numericPart = 1; // デフォルトの数値部分

	        String query = "SELECT MAX(info_id) AS max_id FROM Information WHERE info_id LIKE ?";
	        try (PreparedStatement statement = connection.prepareStatement(query)) {
	            statement.setString(1, prefix + "%");
	            try (ResultSet resultSet = statement.executeQuery()) {
	                if (resultSet.next()) {
	                    String currentMaxId = resultSet.getString("max_id");
	                    if (currentMaxId != null && currentMaxId.startsWith(prefix)) {
	                        String numberPart = currentMaxId.substring(prefix.length());
	                        try {
	                            numericPart = Integer.parseInt(numberPart) + 1;
	                        } catch (NumberFormatException e) {
	                            numericPart = 1;
	                        }
	                    }
	                }
	            }
	        }

	        // 新しいIDを生成し、ゼロパディング（例: "24Y01"）
	        return String.format("%s%02d", prefix, numericPart);
	    }

}
