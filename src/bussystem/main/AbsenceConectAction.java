package bussystem.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Absence;
import bean.Child;
import bean.ClassCd;
import bean.ManageUser;
import dao.AbsenceDao;
import dao.ChildDao;
import dao.ClassCdDao;
import tool.Action;

public class AbsenceConectAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		HttpSession session = req.getSession(true);
		AbsenceDao ADao = new AbsenceDao();
		ChildDao CDao = new ChildDao();
		ClassCdDao ClassDao = new ClassCdDao();
		ManageUser MU = (ManageUser) session.getAttribute("user");
		List<Absence> absenceList = null;
		List<Map<String, String>> detailedAbsenceList = new ArrayList<>();

		try {
			absenceList = ADao.getAbsenceInfo(MU.getFacility_id());

			Set<String> absenceDates = new HashSet<>();
			Set<String> classNames = new HashSet<>();
			Set<String> childNames = new HashSet<>();

			for (Absence absence : absenceList) {
				Map<String, String> absenceDetails = new HashMap<>();
				absenceDetails.put("absence_date", absence.getAbsence_date());
				absenceDates.add(absence.getAbsence_date());

				// 子供情報を取得
				Child child = CDao.getChildinfo(MU.getFacility_id(), absence.getChild_id());
				absenceDetails.put("child_name", child.getChild_name());
				childNames.add(child.getChild_name());

				// クラス情報を取得
				ClassCd classCd = ClassDao.getClassCdinfoById(child.getClass_id());
				absenceDetails.put("class_name", classCd.getClass_name());
				classNames.add(classCd.getClass_name());

				absenceDetails.put("absence_main", absence.getAbsence_main());

				detailedAbsenceList.add(absenceDetails);
			}

			// 検索条件を取得
			String absenceDate = req.getParameter("absence_date");
			String className = req.getParameter("class_name");
			String childName = req.getParameter("child_name");

			// 検索条件に基づいてフィルタリング
			if (absenceDate != null && !absenceDate.isEmpty()) {
				detailedAbsenceList = detailedAbsenceList.stream()
					.filter(m -> m.get("absence_date").equals(absenceDate))
					.collect(Collectors.toList());
			}
			if (className != null && !className.isEmpty()) {
				detailedAbsenceList = detailedAbsenceList.stream()
					.filter(m -> m.get("class_name").equalsIgnoreCase(className))
					.collect(Collectors.toList());
			}
			if (childName != null && !childName.isEmpty()) {
				detailedAbsenceList = detailedAbsenceList.stream()
					.filter(m -> m.get("child_name").equalsIgnoreCase(childName))
					.collect(Collectors.toList());
			}

			req.setAttribute("absenceDates", absenceDates);
			req.setAttribute("classNames", classNames);
			req.setAttribute("childNames", childNames);
			req.setAttribute("absenceList", detailedAbsenceList);
			req.getRequestDispatcher("absence_conect.jsp").forward(req, res);
		} catch (Exception e) {
			req.setAttribute("error", "欠席情報の取得中にエラーが発生しました。");
			req.getRequestDispatcher("error.jsp").forward(req, res);
		}
	}
}