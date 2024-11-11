package bussystem.main;

import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.ManageUser;
import dao.AbsenceDao;
import dao.ClassCdDao;
import tool.Action;

public class AbsenceConectAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//ローカル変数の宣言 1
		HttpSession session = req.getSession(true);// セッションを取得
		AbsenceDao ADao = new AbsenceDao(); //欠席Dao
		ClassCdDao CDao = new ClassCdDao();
		ManageUser MU = (ManageUser) session.getAttribute("user");// ログインユーザーを取得
		LocalDate todaysDate = LocalDate.now();// LcalDateインスタンスを取得




	}

}
