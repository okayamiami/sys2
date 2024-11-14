package bussystem.main;

import java.util.Collections;
import java.util.Comparator;
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

		// ローカル変数の宣言 1
		HttpSession session = req.getSession(); // セッション
		ManageUser mu = (ManageUser) session.getAttribute("user"); // ログインユーザーを取得（管理者or先生）
		InformationDao iDao = new InformationDao();

		// DBからお知らせリストを取得
		List<Information> ilist = iDao.getInfoList(mu.getFacility_id());

		// お知らせリストを日付順（新しい順）にソート
		Collections.sort(ilist, new Comparator<Information>() {
			@Override
			public int compare(Information o1, Information o2) {
				// info_dateを比較して新しい日付が先に来るようにソート
				return o2.getInfo_date().compareTo(o1.getInfo_date());
			}
		});

		// リクエスト属性にセット
		req.setAttribute("ilist_set", ilist);

		// JSPへフォワード
		req.getRequestDispatcher("infolist.jsp").forward(req, res);
	}
}


