package bussystem.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Absence;
import bean.Child;
import bean.ManageUser;
import dao.AbsenceDao;
import dao.ChildDao;
import tool.Action;

public class AbsenceInfoEditAction extends Action{
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		//ローカル変数の宣言 1
		HttpSession session = req.getSession(true);				// セッションを取得
		ChildDao cDao = new ChildDao(); 							// 子供Daoを初期化
		AbsenceDao aDao = new AbsenceDao(); 						// 欠席Daoを初期化
		String facility_id = "";
		String absence_id = "";										// 選択された欠席ID


		ManageUser mu = (ManageUser) session.getAttribute("user"); // ログインユーザーを取得
		facility_id = mu.getFacility_id();

		absence_id = req.getParameter("absenceId");


		// DBからデータ取得 3
		Absence abs = aDao.getAbschildinfobyAbsenceId(facility_id,absence_id);		// 選択された欠席情報を取得する

		List<Child> chiidlist = cDao.getChildListinfo(facility_id);					// 子供情報のリスト


		req.setAttribute("abs",abs );
		req.setAttribute("chiidlist",chiidlist );

		req.getRequestDispatcher("absenceinfoedit.jsp").forward(req, res);


	}

}
