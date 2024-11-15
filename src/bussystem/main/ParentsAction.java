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

		String url = "";
		Map<String, String> errors = new HashMap<>();

		// Beanをインスタンス化
		ParentsUser PU = new ParentsUser();
		ManageUser MU = new ManageUser();

		// Daoをインスタンス化
		ParentsUserDao PD = new ParentsUserDao();

		// sessionの有効化
		HttpSession session = req.getSession(true);

		// ログインユーザーを一時的に取得
		String user_type = (String) session.getAttribute("user_type");

		// sessionから"user"属性を取得し、型をチェック
		Object userObj = session.getAttribute("user");

		// userの型に応じて処理
		if (userObj instanceof ParentsUser) {
			// 保護者ユーザーの場合
			PU = (ParentsUser) userObj;
			String user_id = PU.getParents_id();
			String facility_id = PU.getFacility_id();

			// 保護者の情報を取得
			PU = PD.getParentsUserInfo(user_id, facility_id);
			session.setAttribute("user", PU);
			req.getRequestDispatcher("parentsinfo.jsp").forward(req, res);

		} else if (userObj instanceof ManageUser) {
			// 管理者ユーザーの場合
			MU = (ManageUser) userObj;
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
