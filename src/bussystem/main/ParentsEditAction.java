package bussystem.main;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.ManageUser;
import bean.ParentsUser;
import dao.ParentsUserDao;
import tool.Action;

public class ParentsEditAction extends Action {
	//saveParentsUserInfo
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//編集を押したときに来る場所

		Map<String, String> errors = new HashMap<>();


		//Beanをインスタンス化
		ParentsUser PU = new ParentsUser();
		ManageUser MU = new ManageUser();

		//Daoをインスタンス化
		ParentsUserDao PD = new ParentsUserDao();
		//ManageUserDao MD = new ManageUserDao();

		//sessionの有効化
		HttpSession session = req.getSession(true);

		//ログインユーザーを一時的に取得
		String user_type = (String) session.getAttribute("user_type");

		//引数設定 外部
		String user_id = null;
		String facility_id = null;


		if ("P".equals(user_type)) {
		    PU = (ParentsUser) session.getAttribute("user");
		    user_id = PU.getParents_id();
			facility_id = PU.getFacility_id();
		} else if ("M".equals(user_type)) {
		    MU = (ManageUser) session.getAttribute("user");
		    user_id = req.getParameter("parents_id");
		    facility_id = MU.getFacility_id();
		} else {
		    errors.put("kome", "セッションに不正なユーザー情報が格納されています。");
		}


		//PU = PD.getParentsUserInfo(user_id, facility_id);


		//if文でログインユーザーを分ける
		if("M".equals(user_type)){
			//ログインユーザーが管理者の時
			PU = PD.getParentsUserInfo(user_id, facility_id);
			req.setAttribute("user", MU);
			req.setAttribute("userinfo", PU);
			req.setAttribute("user_type", user_type);
			req.setAttribute("parents_id", user_id);
			req.getRequestDispatcher("parentsedit.jsp").forward(req, res);

		}else if("P".equals(user_type)){
			System.out.println(user_id);
			System.out.println(facility_id);
			//ログインユーザーが保護者の時、ログインした保護者の情報を取得
			ParentsUser PU2 = PD.getParentsUserInfo(user_id, facility_id);
			System.out.println(PU2);
			req.setAttribute("user", PU);
			req.setAttribute("userinfo", PU2);
			req.setAttribute("user_type", user_type);
			req.setAttribute("parents_id", user_id);
			req.getRequestDispatcher("parentseditp.jsp").forward(req, res);

		}else{
			errors.put("kome", "情報取得に失敗しました。");
		}



		//req.getRequestDispatcher("parentsinfo.jsp").forward(req, res);
	}
}
