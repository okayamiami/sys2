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

public class ParentsAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//保護者情報をクリック時、ログインアカウントによって内容を変える

		String url = "";
		Map<String, String> errors = new HashMap<>();

		// sessionの有効化
		HttpSession session = req.getSession(true);

		// Daoをインスタンス化
		ParentsUserDao PD = new ParentsUserDao();



		// ログインユーザーを一時的に取得
		String user_type = (String) session.getAttribute("user_type");

		// userの型に応じて処理
		if ("P".equals(user_type)) {
			// 保護者ユーザーの場合
			ParentsUser PU = (ParentsUser) session.getAttribute("user");
			String user_id = PU.getParents_id();
			String facility_id = PU.getFacility_id();

			// 保護者の情報を取得
			ParentsUser PU2 = PD.getParentsUserInfo(user_id, facility_id);
			req.setAttribute("userinfo", PU);
			req.setAttribute("user", PU2);
			req.getRequestDispatcher("parentsinfo.jsp").forward(req, res);

		} else if ("M".equals(user_type)) {
			// 管理者ユーザーの場合
			ManageUser MU = (ManageUser) session.getAttribute("user");
			String facility_id = MU.getFacility_id();
			req.setAttribute("facility_id", facility_id);
			// 保護者ID入力ページへ
			req.getRequestDispatcher("parentsinput.jsp").forward(req, res);

		} else {
			// 予期しない場合のエラーハンドリング
			errors.put("kome", "情報取得に失敗しました。");
			req.setAttribute("errors", errors);
			req.getRequestDispatcher("errorPage.jsp").forward(req, res);
		}
	}
}
