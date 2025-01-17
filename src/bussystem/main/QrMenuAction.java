package bussystem.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.ManageUser;
import tool.Action;

public class QrMenuAction extends Action{
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//ローカル変数の宣言 1
		HttpSession session = req.getSession(true);// セッションを取得
		ManageUser mu = (ManageUser) session.getAttribute("user");// ログインユーザーを取得
		String type = (String) session.getAttribute("user_type");
		//JSPへフォワード 7
		if (type.equals("M")){
			req.getRequestDispatcher("mqrmenu.jsp").forward(req, res);

		}
		else{
			req.getRequestDispatcher("tqrmenu.jsp").forward(req, res);
		}
	}

}
