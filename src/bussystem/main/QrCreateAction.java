package bussystem.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Child;
import bean.ManageUser;
import dao.ChildDao;
import tool.Action;

public class QrCreateAction extends Action {
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//ローカル変数の宣言 1
		HttpSession session = req.getSession(true);// セッションを取得
		ManageUser mu = (ManageUser) session.getAttribute("user");// ログインユーザーを取得
		ChildDao cDao = new ChildDao();
		//リクエストパラメータ―の取得 2
		//なし

		//DBからデータ取得 3
		List<Child> clist = cDao.getChildListinfo(mu.getFacility_id());
		//ビジネスロジック 4
		//なし
		//DBへデータ保存 5
		//なし
		//レスポンス値をセット 6
		req.setAttribute("child_set", clist);
		//JSPへフォワード 7
		req.getRequestDispatcher("qrcreate.jsp").forward(req, res);
	}

}
