package bussystem.main;

import java.util.ArrayList;
import java.util.Collections;
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

public class ChildInfoAction extends Action{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//管理者の場合は保護者ID入力画面。保護者の場合はログインユーザーで子供情報を表示する

		Map<String, String> errors = new HashMap<>();

		// sessionの有効化
		HttpSession session = req.getSession(true);

		// Daoをインスタンス化
		ChildDao CD = new ChildDao();
		ClassCdDao CC = new ClassCdDao();



		// ログインユーザーを一時的に取得
		String user_type = (String) session.getAttribute("user_type");

		// userの型に応じて処理
		if ("P".equals(user_type)) {
			// 保護者ユーザーの場合
			ParentsUser PU = (ParentsUser) session.getAttribute("user");
			String user_id = PU.getParents_id();
			String facility_id = PU.getFacility_id();

			//子供の情報を取得
		    List<Child> CI = new ArrayList<>();
			CI = CD.getChildrenByParentId(user_id, facility_id);

			//全クラス情報取得
			List<ClassCd> class_set = CC.getClassCdinfo(facility_id);

			// 子供情報を child_id の小さい順にソートし「CI」に入れる
			Collections.sort(CI, (child1, child2) -> child1.getChild_id().compareTo(child2.getChild_id()));


			//リクエストに情報をセット
			req.setAttribute("class_set", class_set);
			req.setAttribute("userCI", CI);
			req.setAttribute("user", PU);
			req.setAttribute("parents_id", user_id);

			req.setAttribute("user_type", user_type);	// パンくずの表示判断用

			req.getRequestDispatcher("childinfo.jsp").forward(req, res);

		} else if ("M".equals(user_type)) {
			// 管理者ユーザーの場合
			ManageUser MU = (ManageUser) session.getAttribute("user");

			//ログインユーザーから施設ID入手
			String facility_id = MU.getFacility_id();
			req.setAttribute("facility_id", facility_id);

			// 保護者ID入力ページへ
			req.getRequestDispatcher("parentsidinput.jsp").forward(req, res);
		} else {
			// 予期しない場合のエラーハンドリング
			req.getRequestDispatcher("menu.jsp").forward(req, res);
		}

	}

}
