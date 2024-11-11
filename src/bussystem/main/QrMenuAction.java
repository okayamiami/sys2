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
		System.out.println("99999999999999");
		HttpSession session = req.getSession(true);// セッションを取得
		ManageUser mu = (ManageUser) session.getAttribute("user");// ログインユーザーを取得
		String type = (String) session.getAttribute("user_type");
		System.out.println("8888888888888");
		//JSPへフォワード 7
		if (type.equals("M")){
			System.out.println("7777777777777");
			req.getRequestDispatcher("mqrmenu.jsp").forward(req, res);
			//jspがおかしいっぽい
		}
		else{
			System.out.println("66666666666666");
			req.getRequestDispatcher("tqrmenu.jsp").forward(req, res);
		}
	}

}
