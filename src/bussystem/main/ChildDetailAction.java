package bussystem.main;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Child;
import bean.ChildDetail;
import bean.ManageUser;
import dao.ChildDao;
import dao.ChildDetailDao;
import tool.Action;

public class ChildDetailAction extends Action {


	// 子供詳細画面情報

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		//ローカル変数の宣言 1
		HttpSession session = req.getSession();//セッション
		ManageUser mu = (ManageUser) session.getAttribute("user");		// ログインユーザーを取得（管理者or先生）
		String facility_id = mu.getFacility_id();

		ChildDao cDao = new ChildDao();										// 子供
		ChildDetailDao cdDao = new ChildDetailDao();						// 子供詳細情報


		Map<String, String> errors = new HashMap<>();						// エラーメッセージ



		//リクエストパラメータ―の取得 2（絞り込み部分）
		String child_id = req.getParameter("child_id");				// 子供ID



		//DBからデータ取得 3

		// 子供情報取得
		Child child = cDao.getChildinfo(facility_id, child_id);
		String parents_id = child.getParents_id();
		// 子供詳細情報取得
		ChildDetail cdetail = cdDao.getChildDetailinfo(facility_id, parents_id, child_id);


		//ビジネスロジック 4
		//なし

		//DBへデータ保存 5
		//なし



		//レスポンス値をセット 6



		// リクエストにをセット
		req.setAttribute("child_detail", cdetail);


		//JSPへフォワード 7
		req.getRequestDispatcher("childdetail.jsp").forward(req, res);
	}

}





