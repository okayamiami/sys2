package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.Absence;

public class AbsenceDao extends Dao{

	private String baseSql = "select * from Absence where facility_id=? ";

	// 欠席情報のリスト取得（報告ID作成用）
	public List<Absence> getAbsenceInfo(String facility_id) throws Exception {
		// 戻り値用のリストを作成
		// new演算子とArrayListで空のListを用意
		List<Absence> list = new ArrayList<>();

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
				Absence abs = new Absence();
				// 戻り値用
				abs.setAbsence_id(rSet.getString("absence_id"));
				abs.setAbsence_main(rSet.getString("absence_main"));
				abs.setChild_id(rSet.getString("child_id"));
				abs.setAbsence_date(rSet.getString("absence_date"));
				abs.setFacility_id(rSet.getString("facility_id"));
				abs.setAbs_is_attend(rSet.getBoolean("abs_is_attend"));
				list.add(abs);


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

	// 該当日の対象の子供の欠席情報取得  例）2024/11/6 A幼稚園　αくんの欠席情報の取得
	public Absence getAbschildinfo(String facility_id,String child_id,String absence_date)throws Exception{
		Absence abs = new Absence();
		Connection connection = getConnection();
		PreparedStatement st = null;


		String sql2 = "and child_id = ? and  absence_date=? ";

		try{
			st = connection.prepareStatement(baseSql+sql2);
			st.setString(1,facility_id);
			st.setString(2,child_id);
			st.setString(3, absence_date);
			ResultSet rSet = st.executeQuery();

			if(rSet.next()){
				abs.setAbsence_id(rSet.getString("absence_id"));
				abs.setAbsence_main(rSet.getString("absence_main"));
				abs.setChild_id(rSet.getString("child_id"));
				abs.setAbsence_date(rSet.getString("absence_date"));
				abs.setFacility_id(rSet.getString("facility_id"));
				abs.setAbs_is_attend(rSet.getBoolean("abs_is_attend"));
			}else{
				abs=null;
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
		return abs;
	}


	// 欠席IDから欠席情報を取得する
	public Absence getAbschildinfobyAbsenceId(String facility_id,String absence_id)throws Exception{
		Absence abs = new Absence();
		Connection connection = getConnection();
		PreparedStatement st = null;


		String sql2 = "and absence_id=? ";

		try{
			st = connection.prepareStatement(baseSql+sql2);
			st.setString(1,facility_id);
			st.setString(2,absence_id);
			ResultSet rSet = st.executeQuery();

			if(rSet.next()){
				abs.setAbsence_id(rSet.getString("absence_id"));
				abs.setAbsence_main(rSet.getString("absence_main"));
				abs.setChild_id(rSet.getString("child_id"));
				abs.setAbsence_date(rSet.getString("absence_date"));
				abs.setFacility_id(rSet.getString("facility_id"));
				abs.setAbs_is_attend(rSet.getBoolean("abs_is_attend"));
			}else{
				abs=null;
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
		return abs;
	}


	// 欠席連絡更新
	public boolean saveAbsenceInfo(Absence absence) throws Exception {
		//コネクションを確立
		Connection connection = getConnection();
		//プリペアードステートメント
		PreparedStatement statement = null;
		//実行件数
		int count = 0;

		try{
			//データベースから欠席情報
			Absence abssave = getAbschildinfo (absence.getFacility_id(), absence.getChild_id(), absence.getAbsence_date());
			if (abssave == null) {


				//該当の日に対象の子の欠席情報が存在しなかった場合
				//プリペアードステートメンにINSERT文をセット
				statement = connection.prepareStatement(
						"insert into Absence (absence_id, absence_main, child_id, absence_date, facility_id) values(?, ?, ?, ?, ?) ");
				//プリペアードステートメントに値をバインド
				statement.setString(1, absence.getAbsence_id());
				statement.setString(2, absence.getAbsence_main());
				statement.setString(3, absence.getChild_id());
				statement.setString(4, absence.getAbsence_date());
				statement.setString(5, absence.getFacility_id());

			} else {
				//該当の日に対象の子の欠席情報が存在した場合（欠席情報更新）

				//プリペアードステートメントにUPDATE文をセット
				statement = connection
						.prepareStatement("update Absence set absence_main=?, child_id=?, absence_date=?, abs_is_attend=? where absence_id=? and facility_id=?  ");
				//プリペアードステートメントに値をバインド
				statement.setString(1, absence.getAbsence_main());
				statement.setString(2, absence.getChild_id());
				statement.setString(3, absence.getAbsence_date());
				statement.setBoolean(4, absence.getAbs_is_attend());
				statement.setString(5, absence.getAbsence_id());
				statement.setString(6, absence.getFacility_id());

				System.out.println(statement);

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


	// 欠席報告
	public boolean saveAbsenceReport(Absence absence) throws Exception {
		//コネクションを確立
		Connection connection = getConnection();
		//プリペアードステートメント
		PreparedStatement statement = null;
		//実行件数
		int count = 0;

		try{
			//データベースから欠席情報
			Absence abssave = getAbschildinfo (absence.getFacility_id(), absence.getChild_id(), absence.getAbsence_date());
			if (abssave == null) {


				//該当の日に対象の子の欠席情報が存在しなかった場合
				//プリペアードステートメンにINSERT文をセット
				statement = connection.prepareStatement(
						"insert into Absence (absence_id, absence_main, child_id, absence_date, facility_id) values(?, ?, ?, ?, ?) ");
				//プリペアードステートメントに値をバインド
				statement.setString(1, absence.getAbsence_id());
				statement.setString(2, absence.getAbsence_main());
				statement.setString(3, absence.getChild_id());
				statement.setString(4, absence.getAbsence_date());
				statement.setString(5, absence.getFacility_id());

			} else {
				//該当の日に対象の子の欠席情報が存在した場合（欠席情報更新）

				//プリペアードステートメントにUPDATE文をセット
				statement = connection
						.prepareStatement("update Absence set absence_main=?, child_id=?, absence_date=?, abs_is_attend=? where child_id=? and absence_date=? and facility_id=?  ");
				//プリペアードステートメントに値をバインド
				statement.setString(1, absence.getAbsence_main());
				statement.setString(2, absence.getChild_id());
				statement.setString(3, absence.getAbsence_date());
				statement.setBoolean(4, absence.getAbs_is_attend());
				statement.setString(5, absence.getAbsence_id());
				statement.setString(6, absence.getFacility_id());

				System.out.println(statement);

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



	// 欠席報告
	public boolean DeleteAbsenceInfo(String facility_id, String absence_id) throws Exception {
		//コネクションを確立
		Connection connection = getConnection();
		//プリペアードステートメント
		PreparedStatement statement = null;
		//実行件数
		int count = 0;

		try{

			//プリペアードステートメンにINSERT文をセット
			statement = connection.prepareStatement(
					"delete from Absence where absence_id = ? and facility_id = ? ");

			//プリペアードステートメントに値をバインド
			statement.setString(1, absence_id);
			statement.setString(2, facility_id);

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



	/** ここから先 欠席連絡表示用（マップ使用） */

	String conectSql ="SELECT a.*, c.child_name, c.class_id " +
	            "FROM Absence a " +
	            "JOIN Child c ON a.child_id = c.child_id AND a.facility_id = c.facility_id " +
	            "WHERE a.facility_id=? ";



	// 欠席情報全件取得（連絡表示用）
	public List<Map<String, Object>> getAbsenceInfo2(String facility_id) throws Exception {
	    // 戻り値用のリストを作成
	    List<Map<String, Object>> list = new ArrayList<>();

	    // データベースへのコネクションを確立
	    Connection connection = getConnection();

	    // プリペアードステートメント
	    PreparedStatement statement = null;

	    // 欠席ID順にソート
	    String sort = " ORDER BY absence_id DESC ";


	    try {
	        // プリペアードステートメントにSQL文をセット
	        statement = connection.prepareStatement(conectSql + sort);

	        // プレースホルダー（？の部分）に値を設定
	        statement.setString(1, facility_id);

	        // SQL文を実行
	        ResultSet rSet = statement.executeQuery();

	        // リザルトセット（結果）を全件走査
	        while (rSet.next()) {
	            // Map に欠席情報を格納
	            Map<String, Object> map = new HashMap<>();
	            map.put("absence_id", rSet.getString("absence_id"));
	            map.put("absence_main", rSet.getString("absence_main"));
	            map.put("child_id", rSet.getString("child_id"));
	            map.put("absence_date", rSet.getString("absence_date"));
	            map.put("facility_id", rSet.getString("facility_id"));
	            map.put("abs_is_attend", rSet.getBoolean("abs_is_attend"));
	            map.put("child_name", rSet.getString("child_name")); // 子供の名前を追加
	            map.put("class_id", rSet.getString("class_id"));     // クラスIDを追加

	            // リストに追加
	            list.add(map);

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

	// 欠席情報全件取得（連絡表示用）
	public List<Map<String, Object>> getAbsenceInfo3(String facility_id, String parents_id, String date) throws Exception {
	    // 戻り値用のリストを作成
	    List<Map<String, Object>> list = new ArrayList<>();

	    // データベースへのコネクションを確立
	    Connection connection = getConnection();

	    // プリペアードステートメント
	    PreparedStatement statement = null;

	    // 欠席ID順にソート
	    String sql2 = " and parents_id=? and absence_date =?";


	    // 欠席ID順にソート
	    String sort = " ORDER BY absence_id DESC ";


	    try {
	        // プリペアードステートメントにSQL文をセット
	        statement = connection.prepareStatement(conectSql + sql2 + sort);

	        // プレースホルダー（？の部分）に値を設定
	        statement.setString(1, facility_id);
	        statement.setString(2, parents_id);
	        statement.setString(3, date);

	        // SQL文を実行
	        ResultSet rSet = statement.executeQuery();

	        // リザルトセット（結果）を全件走査
	        while (rSet.next()) {
	            // Map に欠席情報を格納
	            Map<String, Object> map = new HashMap<>();
	            map.put("absence_id", rSet.getString("absence_id"));
	            map.put("absence_main", rSet.getString("absence_main"));
	            map.put("child_id", rSet.getString("child_id"));
	            map.put("absence_date", rSet.getString("absence_date"));
	            map.put("facility_id", rSet.getString("facility_id"));
	            map.put("abs_is_attend", rSet.getBoolean("abs_is_attend"));
	            map.put("child_name", rSet.getString("child_name")); // 子供の名前を追加
	            map.put("class_id", rSet.getString("class_id"));     // クラスIDを追加

	            // リストに追加
	            list.add(map);

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


	 // クラスを指定して欠席の一覧を取得する
	public List<Map<String, Object>> filterbyClassId(String class_id, String facility_id) throws Exception {
	    List<Map<String, Object>> list = new ArrayList<>();
	    Connection connection = getConnection();
	    PreparedStatement statement = null;
	    ResultSet rSet = null;

	    String condition = "and class_id=?  ";
	 // 欠席ID順にソート
	    String sort = " ORDER BY absence_id DESC ";

	    try {
	        statement = connection.prepareStatement(conectSql + condition + sort);

	        statement.setString(1, facility_id);
	        statement.setString(2, class_id);

	        rSet = statement.executeQuery();

	        while (rSet.next()) {
	            // Map に値を格納
	            Map<String, Object> map = new HashMap<>();
	            map.put("absence_id", rSet.getString("absence_id"));
	            map.put("absence_main", rSet.getString("absence_main"));
	            map.put("child_id", rSet.getString("child_id"));
	            map.put("absence_date", rSet.getString("absence_date"));
	            map.put("facility_id", rSet.getString("facility_id"));
	            map.put("abs_is_attend", rSet.getBoolean("abs_is_attend"));
	            map.put("child_name", rSet.getString("child_name")); // 子供の名前を追加
	            map.put("class_id", rSet.getString("class_id"));     // クラスIDを追加

	            list.add(map);


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

	    return list;
	}



	 // 欠席日を指定して欠席の一覧を取得する

	public List<Map<String, Object>> filterbyAbsence_date(String absence_date, String facility_id) throws Exception {

		// リストを初期化
	    List<Map<String, Object>> list = new ArrayList<>();
		Connection connection = getConnection();
		PreparedStatement statement = null;
		ResultSet rSet = null;


	    String condition = "and absence_date=?  ";
	    // 欠席ID順にソート
	    String sort = " ORDER BY absence_id DESC ";

	    try {
	        statement = connection.prepareStatement(conectSql + condition + sort);

	        statement.setString(1, facility_id);
	        statement.setString(2, absence_date);

	        rSet = statement.executeQuery();


	        while (rSet.next()) {
	            // Map に値を格納
	            Map<String, Object> map = new HashMap<>();
	            map.put("absence_id", rSet.getString("absence_id"));
	            map.put("absence_main", rSet.getString("absence_main"));
	            map.put("child_id", rSet.getString("child_id"));
	            map.put("absence_date", rSet.getString("absence_date"));
	            map.put("facility_id", rSet.getString("facility_id"));
	            map.put("abs_is_attend", rSet.getBoolean("abs_is_attend"));
	            map.put("child_name", rSet.getString("child_name")); // 子供の名前を追加
	            map.put("class_id", rSet.getString("class_id"));     // クラスIDを追加

	            list.add(map);
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



	 // 名前を指定して欠席の一覧を取得する
	public List<Map<String, Object>> filterbyChildName(String child_name, String facility_id) throws Exception {

		// リストを初期化
	    List<Map<String, Object>> list = new ArrayList<>();
		Connection connection = getConnection();
		PreparedStatement statement = null;
		ResultSet rSet = null;


	    String condition = "and child_name=?  ";
	    // 欠席ID順にソート
	    String sort = " ORDER BY absence_id DESC ";

	    try {
	        statement = connection.prepareStatement(conectSql + condition + sort);

	        statement.setString(1, facility_id);
	        statement.setString(2, child_name);

	        rSet = statement.executeQuery();

	        while (rSet.next()) {
	            // Map に値を格納
	            Map<String, Object> map = new HashMap<>();
	            map.put("absence_id", rSet.getString("absence_id"));
	            map.put("absence_main", rSet.getString("absence_main"));
	            map.put("child_id", rSet.getString("child_id"));
	            map.put("absence_date", rSet.getString("absence_date"));
	            map.put("facility_id", rSet.getString("facility_id"));
	            map.put("abs_is_attend", rSet.getBoolean("abs_is_attend"));
	            map.put("child_name", rSet.getString("child_name")); // 子供の名前を追加
	            map.put("class_id", rSet.getString("class_id"));     // クラスIDを追加

	            list.add(map);
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


	// ページング対応の欠席情報取得メソッド
    public List<Map<String, Object>> getAbsenceInfoWithPaging(String facility_id, int offset, int limit) throws Exception {
        List<Map<String, Object>> list = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement statement = null;

        String sql = conectSql + " ORDER BY absence_id DESC LIMIT ? OFFSET ?";

        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, facility_id);
            statement.setInt(2, limit);
            statement.setInt(3, offset);

            ResultSet rSet = statement.executeQuery();

            while (rSet.next()) {
                Map<String, Object> map = new HashMap<>();
                map.put("absence_id", rSet.getString("absence_id"));
                map.put("absence_main", rSet.getString("absence_main"));
                map.put("child_id", rSet.getString("child_id"));
                map.put("absence_date", rSet.getString("absence_date"));
                map.put("facility_id", rSet.getString("facility_id"));
                map.put("abs_is_attend", rSet.getBoolean("abs_is_attend"));
                map.put("child_name", rSet.getString("child_name"));
                map.put("class_id", rSet.getString("class_id"));

                list.add(map);
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

        return list;
    }


 // 総レコード数を取得するメソッド
    public int getTotalRecords(String facility_id) throws Exception {
        Connection connection = getConnection();
        PreparedStatement statement = null;
        int totalRecords = 0;

        String sql = "SELECT COUNT(*) FROM Absence WHERE facility_id = ?";

        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, facility_id);

            ResultSet rSet = statement.executeQuery();
            if (rSet.next()) {
                totalRecords = rSet.getInt(1);
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

        return totalRecords;
    }

}
