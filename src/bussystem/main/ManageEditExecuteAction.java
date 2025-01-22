package bussystem.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.ManageUser;
import dao.ManageUserDao;
import tool.Action;

public class ManageEditExecuteAction extends Action{
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		HttpSession session = req.getSession(); // セッションを取得
		ManageUser mu = new ManageUser();
		ManageUserDao mudao = new ManageUserDao();
		String user_id = req.getParameter("user_id");
		String user_name = req.getParameter("user_name");
		String user_pass = req.getParameter("user_pass");
		String facility_id = req.getParameter("facility_id");
		mu.setUser_id(user_id);
		mu.setUser_name(user_name);
		mu.setUser_pass(user_pass);
		mu.setFacility_id(facility_id);
		try{
			mudao.saveManageUserInfo(mu);
			mu.setAuthenticated(true); // 保護者情報が正しく更新されるように認証状態を付与
            session.removeAttribute("user");
            session.setAttribute("user", mu);

			// 完了画面に表示
            req.setAttribute("user_id", user_id);
            req.setAttribute("user_name", user_name);
            req.setAttribute("user_pass", user_pass);
            req.setAttribute("facility_id", facility_id);

			req.getRequestDispatcher("managedone.jsp").forward(req, res);
		}catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("message", "エラーが発生しました。再度お試しください。");
            req.getRequestDispatcher("error.jsp").forward(req, res);
		}

	}

}
