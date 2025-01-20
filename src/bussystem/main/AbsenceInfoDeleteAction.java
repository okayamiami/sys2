package bussystem.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AbsenceDao;
import tool.Action;

public class AbsenceInfoDeleteAction extends Action {

	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		//ローカル変数の宣言 1
		HttpSession session = req.getSession(true);				// セッションを取得
		AbsenceDao aDao = new AbsenceDao(); 						// 欠席Daoを初期化
		String facility_id = req.getParameter("facilityId");
		String absence_id = req.getParameter("absenceId");										// 選択された欠席ID



		// DBからデータ取得 3
		aDao.DeleteAbsenceInfo(facility_id,absence_id);		// 欠席情報削除


		req.getRequestDispatcher("absence_delete_done.jsp").forward(req, res);


	}


}
