package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.BusChildInfo;

public class BusChildDao extends Dao{
	public List<BusChildInfo> getFilteredBusChildInfo(String facility_id, String bus_id, String child_id, String class_id) throws Exception {
	    List<BusChildInfo> list = new ArrayList<>();
	    Connection connection = getConnection();
	    PreparedStatement st = null;

	    // SQL文の動的構築
	    StringBuilder sql = new StringBuilder(
	        "SELECT DISTINCT c.child_id, c.child_name, c.class_id, b.bus_id, b.bus_name, " +
	        "cl.class_name " + // 修正：classCdのエイリアスを使用
	        "FROM Get g " +
	        "LEFT JOIN Child c ON g.child_id = c.child_id " +
	        "LEFT JOIN Bus b ON g.bus_id = b.bus_id " +
	        "LEFT JOIN classCd cl ON c.class_id = cl.class_id " + // 修正：classCdにエイリアスを使用
	        "WHERE g.get_is_attend = true " +
	        "AND c.is_attend = true " +
	        "AND c.facility_id = ? " +  // c.facility_id に施設IDを設定
	        "AND b.facility_id = ? " +  // b.facility_id にも施設IDを設定
	        "AND g.facility_id = ? " +  // g.facility_id にも施設IDを設定
	        "AND cl.facility_id = ? " // cl.facility_id にも施設IDを設定（修正）
	    );

	    // 他の条件を追加
	    if (bus_id != null && !bus_id.isEmpty()) {
	        sql.append("AND b.bus_id = ? ");
	    }
	    if (child_id != null && !child_id.isEmpty()) {
	        sql.append("AND c.child_id = ? ");
	    }
	    if (class_id != null && !class_id.isEmpty()) {
	        sql.append("AND c.class_id = ? ");
	    }

	    try {
	        st = connection.prepareStatement(sql.toString());

	        // 1番目の施設IDを設定 (c.facility_id)
	        st.setString(1, facility_id);

	        // 2番目の施設IDを設定 (b.facility_id)
	        st.setString(2, facility_id);

	        // 3番目の施設IDを設定 (g.facility_id)
	        st.setString(3, facility_id);

	        // 4番目の施設IDを設定 (classCd.facility_id)
	        st.setString(4, facility_id);

	        int index = 5; // 次のパラメータのインデックス
	        if (bus_id != null && !bus_id.isEmpty()) {
	            st.setString(index++, bus_id);
	        }
	        if (child_id != null && !child_id.isEmpty()) {
	            st.setString(index++, child_id);
	        }
	        if (class_id != null && !class_id.isEmpty()) {
	            st.setString(index++, class_id);
	        }

	        ResultSet rs = st.executeQuery();

	        while (rs.next()) {
	            BusChildInfo info = new BusChildInfo();
	            info.setChild_id(rs.getString("child_id"));
	            info.setChild_name(rs.getString("child_name"));
	            info.setClass_id(rs.getString("class_id"));
	            info.setBus_id(rs.getString("bus_id"));
	            info.setBus_name(rs.getString("bus_name"));
	            info.setClass_name(rs.getString("class_name")); // class_nameをセット
	            list.add(info);
	        }
	    } finally {
	        if (st != null) st.close();
	        if (connection != null) connection.close();
	    }
	    return list;
	}




}
