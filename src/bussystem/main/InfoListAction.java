package bussystem.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Information;
import bean.ManageUser;
import dao.InformationDao;
import tool.Action;

public class InfoListAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		//ローカル変数の宣言 1
		HttpSession session = req.getSession();//セッション
		ManageUser mu = (ManageUser) session.getAttribute("user");		// ログインユーザーを取得（管理者or先生）
		InformationDao iDao = new InformationDao();

		List<Information> ilist = iDao.getInfoList(mu.getFacility_id());

		//リクエストパラメータ―の取得 2（絞り込み部分）


		//DBからデータ取得 3


		//ビジネスロジック 4

		//DBへデータ保存 5
		//なし

		//レスポンス値をセット 6
		req.setAttribute("ilist_set", ilist);

		//JSPへフォワード 7
		req.getRequestDispatcher("infolist.jsp").forward(req, res);
	}

}





