package bussystem.main;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.ParentsUser;
import dao.ParentsUserDao;
import tool.Action;

public class ParentsEditAction extends Action {
	//saveParentsUserInfo
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

		//ログインユーザーを一時的に取得
		String user_type = (String) session.getAttribute("user_type");
		PU = (ParentsUser)session.getAttribute("user");

		//引数設定
		String user_id = PU.getParents_id();
		String facility_id = PU.getFacility_id();
		//PU = PD.getParentsUserInfo(user_id, facility_id);


		//if文でログインユーザーを分ける
		if("M".equals(user_type)){
			//ログインユーザーが管理者の時
			ParentsUser pu = (ParentsUser) session.getAttribute("user");
			//

		}else if("P".equals(user_type)){
			//ログインユーザーが保護者の時、ログインした保護者の情報を取得
			PU = PD.getParentsUserInfo(user_id, facility_id);
			session.setAttribute("user", PU);
			req.getRequestDispatcher("parentsedit.jsp").forward(req, res);

		}else{
			errors.put("kome", "情報取得に失敗しました。");
		}



		//req.getRequestDispatcher("parentsinfo.jsp").forward(req, res);
	}
}
