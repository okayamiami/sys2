package bussystem.main;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.ManageUser;
import dao.ManageUserDao;
import tool.Action;

public class ManageEditAction extends Action{
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		try{
			// sessionの有効化
			HttpSession session = req.getSession(true);
			ManageUser MU = new ManageUser();
			// Daoをインスタンス化
			ManageUserDao mudao = new ManageUserDao();
			String user_id = req.getParameter("user_id");
			String facility_id = req.getParameter("facility_id");

			ManageUser muinfo = mudao.getManageUserInfo(user_id, facility_id);
			if (muinfo == null) {
	            req.setAttribute("error", "ユーザー情報が見つかりません。");
	            req.getRequestDispatcher("error.jsp").forward(req, res);
	            return;
	        }
			req.setAttribute("muinfo", muinfo);

			req.getRequestDispatcher("manageedit.jsp").forward(req, res);
		}catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("errors", "システムエラーが発生しました。");
            req.getRequestDispatcher("error.jsp").forward(req, res);
        }
	}

}
