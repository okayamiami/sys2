package bussystem.main;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Child;
import bean.ManageUser;
import dao.ChildDao;
import dao.ParentsUserDao;
import tool.Action;

public class ChildDetailedAction extends Action {


	// 子供詳細画面情報

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		//ローカル変数の宣言 1
		HttpSession session = req.getSession();//セッション
		ManageUser mu = (ManageUser) session.getAttribute("user");		// ログインユーザーを取得（管理者or先生）
		String facility_id = mu.getFacility_id();

		ChildDao cDao = new ChildDao();										// 子供
		ParentsUserDao pDao = new ParentsUserDao();							// 保護者


		Map<String, String> errors = new HashMap<>();// エラーメッセージ



		//リクエストパラメータ―の取得 2（絞り込み部分）
		String child_id = req.getParameter("child_id");				// 子供ID


		//DBからデータ取得 3
		Child child = cDao.getChildinfo(facility_id, child_id);			// 子供情報取得


		//ビジネスロジック 4
		//なし

		//DBへデータ保存 5
		//なし



		//レスポンス値をセット 6



		// 子供IDをセット
		req.setAttribute("f1", child_id);
		// 子供の名前をセット（予定）
		req.setAttribute("f2", child_name);
		// クラスをセット（予定）
				req.setAttribute("f3", class_cd);
		// 欠席フラグが送信されていた場合
		if (absIsAttendStr != null) {
			// リクエストに欠席フラグをセット
			req.setAttribute("f4", absIsAttendStr);
		}


		// リクエストにをセット
		req.setAttribute("childs", childs);
		req.setAttribute("child_id_set", childIdlist);
		req.setAttribute("child_name_set", childNamelist);
		req.setAttribute("class_name_set", classNamelist);
		req.setAttribute("class_set", classlist);



		//JSPへフォワード 7
		req.getRequestDispatcher("childlist.jsp").forward(req, res);
	}

}





