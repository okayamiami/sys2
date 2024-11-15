package bussystem.main;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.ParentsUser;
import dao.ParentsUserDao;
import tool.Action;

public class ParentsAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		String url = "";
		Map<String, String> errors = new HashMap<>();


		//Beanをインスタンス化
		ParentsUser PU = new ParentsUser();
		//ManageUser MU = new ManageUser();

		//Daoをインスタンス化
		ParentsUserDao PD = new ParentsUserDao();
		//ManageUserDao MD = new ManageUserDao();

		//sessionの有効化
		HttpSession session = req.getSession(true);

		//ログインユーザーの取得


		//引数設定
		String user_id = req.getParameter("user_id");
		String facility_id = req.getParameter("facility_id");

		//Daoのメソッドを使用
		PU = PD.getParentsUserInfo(user_id, facility_id);
		if(PU == null){
			
		}else if(PU == null){
			errors.put("kome", "情報取得に失敗しました。");
		}



		req.getRequestDispatcher("parentsinfo.jsp").forward(req, res);
	}
}
