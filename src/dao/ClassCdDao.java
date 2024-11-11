package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.ClassCd;

public class ClassCdDao extends Dao {

	private String baseSql = "select * from ClassCd WHERE facility_id = ? ";

	public List<ClassCd> getClassCdinfo(String facility_id) throws Exception {
		List<ClassCd> list = new ArrayList<>();

		try (Connection connection = getConnection();
			 PreparedStatement statement = connection.prepareStatement(baseSql)) {

			statement.setString(1, facility_id);
			ResultSet rSet = statement.executeQuery();

			while (rSet.next()) {
				ClassCd classcd = new ClassCd();
				classcd.setClass_id(rSet.getString("class_id"));
				classcd.setClass_name(rSet.getString("class_name"));
				classcd.setFacility_id(rSet.getString("facility_id"));
				list.add(classcd);
			}
		} catch (SQLException e) {
			throw new Exception("Error fetching class info", e);
		}

		return list;
	}

	public ClassCd getClassCdinfoById(String classId) throws Exception {
		ClassCd classCd = null;
		String sql = "select * from ClassCd where class_id = ?";

		try (Connection connection = getConnection();
			 PreparedStatement statement = connection.prepareStatement(sql)) {

			statement.setString(1, classId);
			ResultSet rSet = statement.executeQuery();

			if (rSet.next()) {
				classCd = new ClassCd();
				classCd.setClass_id(rSet.getString("class_id"));
				classCd.setClass_name(rSet.getString("class_name"));
				classCd.setFacility_id(rSet.getString("facility_id"));
			}
		} catch (SQLException e) {
			throw new Exception("Error fetching class info by ID", e);
		}

		return classCd;
	}
}