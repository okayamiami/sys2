package bussystem.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Bus;
import bean.ManageUser;
import dao.BusDao;
import tool.Action;

public class QrReaderSelectBusAction extends Action{
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//ローカル変数の宣言 1
		HttpSession session = req.getSession(true);// セッションを取得
		ManageUser mu = (ManageUser) session.getAttribute("user");// ログインユーザーを取得
		BusDao bDao = new BusDao();
		//リクエストパラメータ―の取得 2
		//なし

		//DBからデータ取得 3
		System.out.println("aaa");
		List<Bus> blist = bDao.getBus(mu.getFacility_id());
		System.out.println("bbb");
		//ビジネスロジック 4
		//なし
		//DBへデータ保存 5
		//なし
		//レスポンス値をセット 6
		req.setAttribute("bus_set", blist);
		//JSPへフォワード 7
		req.getRequestDispatcher("qrreaderbusselect.jsp").forward(req, res);
	}
}
