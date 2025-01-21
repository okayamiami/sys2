package bussystem.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Absence;
import bean.Child;
import dao.AbsenceDao;
import dao.ChildDao;
import tool.Action;

public class AbsenceInfoDeleteAction extends Action {

	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		//ローカル変数の宣言 1
		HttpSession session = req.getSession(true);				// セッションを取得
		AbsenceDao aDao = new AbsenceDao(); 						// 欠席Daoを初期化
		ChildDao cDao = new ChildDao();
		String facility_id = req.getParameter("facilityId");
		String absence_id = req.getParameter("absenceId");			// 選択された欠席ID


		// DBからデータ取得 3
		Absence abs = aDao.getAbschildinfobyAbsenceId(facility_id,absence_id);		// 削除する欠席情報を取得する

		// 子供の名前を取得
		String child_id = abs.getChild_id();
		Child child = cDao.getChildinfo(facility_id, child_id);

		String child_name = child.getChild_name();

        // JSP に渡すデータを設定
        req.setAttribute("absence_id", abs.getAbsence_id());
        req.setAttribute("child_name", child_name);
        req.setAttribute("abs_main", abs.getAbsence_main());
        req.setAttribute("abs_date", abs.getAbsence_date());

        req.setAttribute("facility_id", facility_id);

		req.getRequestDispatcher("absence_delete.jsp").forward(req, res);


	}


}
