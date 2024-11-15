package bussystem.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tool.Action;

public class ParentsEditAction extends Action {
	//saveParentsUserInfo
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		// セッションを取得
		HttpSession session = req.getSession(true);
		//ログインユーザーの取得
		String user_type = (String) session.getAttribute("user_type");
		
		//
	}
}
