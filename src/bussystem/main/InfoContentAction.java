package bussystem.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Information;
import bean.ManageUser;
import dao.InformationDao;
import tool.Action;

public class InfoContentAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		// ローカル変数の宣言 1
		HttpSession session = req.getSession(); // セッション
		ManageUser mu = (ManageUser) session.getAttribute("user"); // ログインユーザーを取得（管理者or先生）
		String infoId = req.getParameter("info_id"); //info_idを取得
		InformationDao iDao = new InformationDao();

		// DBからお知らせリストを取得
		Information info_set = iDao.getInfoContent(mu.getFacility_id(), infoId);

		if (info_set == null) {
            // info_id に該当する情報が存在しない場合
            req.setAttribute("delete_message", "対象のお知らせは既に削除されています。");
            req.getRequestDispatcher("infodeleteexecute.jsp").forward(req, res);
            return;
        }


		// リクエスト属性にセット
		req.setAttribute("info_set", info_set);

		// JSPへフォワード
		req.getRequestDispatcher("infocontent.jsp").forward(req, res);
	}
}


