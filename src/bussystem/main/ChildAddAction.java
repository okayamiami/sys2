package bussystem.main;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.ManageUser;
import bean.ParentsUser;
import tool.Action;

public class ChildAddAction extends Action{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//Beanのインスタンス化
		 // Beanをインスタンス化
        ParentsUser PU = new ParentsUser();
        ManageUser MU = new ManageUser();


		//管理者の場合は保護者ID入力画面。保護者の場合はログインユーザーで子供情報を表示する
		Map<String, String> errors = new HashMap<>();

		// sessionの有効化
		HttpSession session = req.getSession(true);

	    // 引数設定 外部
        String user_id = null;
        String facility_id = null;

		// ログインユーザーを一時的に取得
		String user_type = (String) session.getAttribute("user_type");

		 if ("P".equals(user_type)) {
            PU = (ParentsUser) session.getAttribute("user");
            user_id = PU.getParents_id();
            facility_id = PU.getFacility_id();
            System.out.println(PU);
        } else if ("M".equals(user_type)) {
            MU = (ManageUser) session.getAttribute("user");
            user_id = req.getParameter("parents_id"); // 管理者の場合、保護者IDをリクエストから取得
            System.out.println(user_id);
            facility_id = MU.getFacility_id();
        } else {
            errors.put("kome", "セッションに不正なユーザー情報が格納されています。");
        }

		 if("P".equals(user_type)){
			 //保護者が新規登録を押した場合
			 req.setAttribute("parents_id", user_id);
			 req.setAttribute("user", PU);
			 req.getRequestDispatcher("childadd.jsp").forward(req, res);
		 } else if ("M".equals(user_type)) {
			//管理者が新規登録を押した場合
			 req.setAttribute("parents_id", user_id);
			 req.setAttribute("user", MU);
			 req.getRequestDispatcher("childadd.jsp").forward(req, res);
		 }


	}

}
