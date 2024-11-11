package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Absence;
import bean.Child;
import bean.ClassCd;

public class AbsenceDao extends Dao {

	private String baseSql = "select * from Absence where facility_id=? ";

	public List<Absence> getAbsenceInfo(String facilityId) throws Exception {
		List<Absence> list = new ArrayList<>();

		try (Connection connection = getConnection();
			 PreparedStatement statement = connection.prepareStatement(baseSql)) {

			statement.setString(1, facilityId);
			ResultSet rSet = statement.executeQuery();

			while (rSet.next()) {
				Absence abs = new Absence();
				abs.setAbsence_id(rSet.getString("absence_id"));
				abs.setAbsence_main(rSet.getString("absence_main"));
				abs.setChild_id(rSet.getString("child_id"));
				abs.setAbsence_date(rSet.getString("absence_date"));
				abs.setFacility_id(rSet.getString("facility_id"));
				abs.setAbs_is_attend(rSet.getBoolean("abs_is_attend"));

				// 子供情報を取得
				ChildDao childDao = new ChildDao();
				Child child = childDao.getChildinfo(facilityId, abs.getChild_id());
				abs.setChild(child);

				// クラス情報を取得
				ClassCdDao classCdDao = new ClassCdDao();
				ClassCd classCd = classCdDao.getClassCdinfoById(child.getClass_id());
				abs.setClassCd(classCd);

				list.add(abs);
			}
		} catch (SQLException e) {
			throw new Exception("Error fetching absence info", e);
		}

		return list;
	}
}