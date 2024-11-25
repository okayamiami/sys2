package bussystem.main;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Child;
import bean.ClassCd;
import bean.ManageUser;
import dao.AbsenceDao;
import dao.ChildDao;
import dao.ClassCdDao;
import tool.Action;

public class AbsenceConectAction extends Action {

	// 欠席連絡確認画面
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
	    HttpSession session = req.getSession();
	    ManageUser mu = (ManageUser) session.getAttribute("user");
	    String facility_id = mu.getFacility_id();

	    // 初期値設定
	    String absence_date = req.getParameter("f1");
	    String class_id = req.getParameter("f2");
	    String child_name = req.getParameter("f3");
	    AbsenceDao aDao = new AbsenceDao();
	    ChildDao cDao = new ChildDao();
	    ClassCdDao ccDao = new ClassCdDao();

	    Map<String, String> errors = new HashMap<>();



	    try {
	        // クラス情報、子供情報の取得
	        List<Child> childlist = cDao.getChildListinfo(facility_id);
	        List<String> childNamelist = new ArrayList<>();
	        for (Child c : childlist) {
	            childNamelist.add(c.getChild_name());
	        }

	        List<ClassCd> classlist = ccDao.getClassCdinfo(facility_id);
	        List<String> classNamelist = new ArrayList<>();
	        for (ClassCd c : classlist) {
	            classNamelist.add(c.getClass_name());
	        }

	        // 絞り込み条件で欠席情報を取得
	        List<Map<String, Object>> absMapList;
	        if (absence_date != null && !absence_date.equals("0") && class_id.equals("0") && child_name.equals("0")) {
	            // 欠席日指定
	        	absMapList = aDao.filterbyAbsence_date(absence_date, facility_id);
	        } else if (class_id != null && !class_id.equals("0") && absence_date.equals("0") && child_name.equals("0")) {
	        	// クラス指定
	        	ClassCd classID = ccDao.getClassIdinfobyName(facility_id, class_id);
	            absMapList = aDao.filterbyClassId(classID.getClass_id(), facility_id);
	        } else if (child_name != null && !child_name.equals("0") && absence_date.equals("0") && class_id.equals("0")) {
	            // 子供の名前指定
	        	absMapList = aDao.filterbyChildName(child_name, facility_id);
	        } else if ((absence_date == null || absence_date.equals("0")) &&
	                   (class_id == null || class_id.equals("0")) &&
	                   (child_name == null || child_name.equals("0"))) {
	            // 指定なしの場合
	        	absMapList = aDao.getAbsenceInfo2(facility_id);
	        } else {
	        	// 上記以外の指定があった場合
	            errors.put("f1", "項目が複数選択されています");
	            req.setAttribute("errors", errors);
	            absMapList = aDao.getAbsenceInfo2(facility_id);
	        }

	        // ビジネスロジック
	        LocalDate today = LocalDate.now();
	        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
	        List<String> dateList = new ArrayList<>();
	        for (int i = 0; i < 10; i++) {
	            dateList.add(today.minusDays(i).format(dtf));
	        }

	        // JSP に渡すデータを設定
	        req.setAttribute("f1", absence_date);
	        req.setAttribute("f2", class_id);
	        req.setAttribute("f3", child_name);
	        req.setAttribute("abs", absMapList);
	        req.setAttribute("datelist", dateList);
	        req.setAttribute("class_name_set", classNamelist);
	        req.setAttribute("child_name_set", childNamelist);
	        req.setAttribute("class_set", classlist);

	        // 正常終了時のフォワード
	        req.getRequestDispatcher("absence_conect.jsp").forward(req, res);

	    } catch (Exception e) {
			req.setAttribute("error", "欠席情報の取得中にエラーが発生しました。");
			req.getRequestDispatcher("childlist.jsp").forward(req, res);
		}
	}
}