package bussystem.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.ManageUser;
import bean.ParentsUser;
import tool.Action;

public class NewInfoAction extends Action{
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		HttpSession session = req.getSession(true);				// セッションを取得
		String type = (String) session.getAttribute("user_type");
		if ("M".equals(type)||"T".equals(type)){
			ManageUser mu = (ManageUser) session.getAttribute("user");	// ログインユーザーを取得
			req.setAttribute("user_status",type );
			req.getRequestDispatcher("newmuinfo.jsp").forward(req, res);
		}else{
			ParentsUser pu = (ParentsUser) session.getAttribute("user");	// ログインユーザーを取得
			req.setAttribute("user_status",type );
			req.getRequestDispatcher("newpuinfo.jsp").forward(req, res);
		}


	}
}
