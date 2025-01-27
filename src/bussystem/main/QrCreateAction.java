package bussystem.main;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Child;
import bean.ClassCd;
import bean.ManageUser;
import dao.ChildDao;
import dao.ClassCdDao;
import tool.Action;

public class QrCreateAction extends Action {
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//ローカル変数の宣言 1
		HttpSession session = req.getSession(true);// セッションを取得
		ManageUser mu = (ManageUser) session.getAttribute("user");// ログインユーザーを取得
		ChildDao cDao = new ChildDao();
		ClassCdDao cdDao = new ClassCdDao();
		//リクエストパラメータ―の取得 2
		//なし

		//DBからデータ取得 3
		List<Child> clist = cDao.getChildListinfo(mu.getFacility_id());
		List<ClassCd> cdlist = cdDao.getClassCdinfo(mu.getFacility_id());

		List<Child> attendingChildren = clist.stream()
			    .filter(Child::is_attend) // is_attend が true のみ通過
			    .collect(Collectors.toList());

		//ビジネスロジック 4
		//なし
		//DBへデータ保存 5
		//なし
		//レスポンス値をセット 6
		req.setAttribute("child_set", attendingChildren);
		req.setAttribute("class_set", cdlist);
		//JSPへフォワード 7
		req.getRequestDispatcher("qrcreate.jsp").forward(req, res);
	}

}
