package bussystem.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Child;
import bean.ClassCd;
import bean.ManageUser;
import bean.ParentsUser;
import dao.ChildDao;
import dao.ClassCdDao;
import tool.Action;

public class ChildUpdateAction extends Action{

	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//編集を押したときに来る場所

		Map<String, String> errors = new HashMap<>();


		//Beanをインスタンス化
		ParentsUser PU = new ParentsUser();
		ManageUser MU = new ManageUser();

		//Daoをインスタンス化
		//ParentsUserDao PD = new ParentsUserDao();
		//ManageUserDao MD = new ManageUserDao();
		ChildDao CD = new ChildDao();
		ClassCdDao CC = new ClassCdDao();

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
			//PU = PD.getParentsUserInfo(user_id, facility_id);
			//req.setAttribute("user", PU);
			req.getRequestDispatcher("parentsedit.jsp").forward(req, res);

		}else if("P".equals(user_type)){
			//ログインユーザーが保護者の時、ログインした保護者の情報を取得
			//PU = PD.getParentsUserInfo(user_id, facility_id);

			//ログインユーザーが保護者の時、ログインユーザーをもとに子供情報取得。クラス情報も取得
			//子供の情報を取得
		    List<Child> CI = new ArrayList<>();
			CI = CD.getChildrenByParentId(user_id, facility_id);
			//全クラス情報取得
			List<ClassCd> class_set = CC.getClassCdinfo(facility_id);


			req.setAttribute("CI", CI);
			req.setAttribute("class_set", class_set);
			req.getRequestDispatcher("childupdate.jsp").forward(req, res);

		}else{
			errors.put("kome", "情報取得に失敗しました。");
		}



		//req.getRequestDispatcher("parentsinfo.jsp").forward(req, res);
	}
}
