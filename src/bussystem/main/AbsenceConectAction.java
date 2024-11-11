package bussystem.main;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Absence;
import bean.ManageUser;
import dao.AbsenceDao;
import dao.ChildDao;
import dao.ClassCdDao;
import tool.Action;

public class AbsenceConectAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//ローカル変数の宣言 1
		HttpSession session = req.getSession(true);// セッションを取得
		ChildDao CDao = new ChildDao();//子供Dao
		AbsenceDao ADao = new AbsenceDao(); //欠席Dao
		ClassCdDao ClassDao = new ClassCdDao();//クラスDao
		ManageUser MU = (ManageUser) session.getAttribute("user");// ログインユーザーを取得
		LocalDate todaysDate = LocalDate.now();// LcalDateインスタンスを取得
		List<Absence> absenceList = null;// 欠席リスト

		absenceList = ADao.getAbsenceInfo(MU.getFacility_id());

		// リクエストに学生リストをセット
		req.setAttribute("absenceList", absenceList);

		//JSPへフォワード 7
		req.getRequestDispatcher("absence_conect.jsp").forward(req, res);




	}

}
