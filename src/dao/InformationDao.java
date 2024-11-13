package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
				info.setInfo_date(rSet.getString("info_date"));
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
				infomain.setInfo_date(rSet.getString("info_date"));
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

	        // 現在の日付と時刻を文字列で取得
	        String currentDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

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
	        statement.setString(6, currentDate);

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

	// 新しい info_id を生成するメソッド
	private String generateNewInfoId(Connection connection) throws SQLException {
	    String newInfoId = "00001";  // 初期値
	    String query = "SELECT info_id FROM Information";

	    try (PreparedStatement statement = connection.prepareStatement(query);
	         ResultSet resultSet = statement.executeQuery()) {

	        int maxId = 0;

	        // ResultSetからすべてのinfo_idを取得し、最大のものを探す
	        while (resultSet.next()) {
	            int currentId = Integer.parseInt(resultSet.getString("info_id"));
	            if (currentId > maxId) {
	                maxId = currentId;
	            }
	        }

	        // 最大IDに1を加え、5桁のゼロパディングで新しいIDを生成
	        newInfoId = String.format("%05d", maxId + 1);
	    }
	    return newInfoId;
	}

}
