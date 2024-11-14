package bussystem.main;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Information;
import bean.ManageUser;
import dao.InformationDao;
import tool.Action;

public class InfoCreateAction extends Action {
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//ローカル変数の宣言 1
		HttpSession session = req.getSession(true);// セッションを取得
		ManageUser mu = (ManageUser) session.getAttribute("user");// ログインユーザーを取得
		InformationDao iDao = new InformationDao();

		//リクエストパラメータ―の取得 2
		//なし

		//DBからデータ取得 3
		List<Information> ilist = iDao.getInfoList(mu.getFacility_id());
		List<Information> distinctilist = ilist.stream()
        .collect(Collectors.toMap(
                Information::getInfo_genre,  // キー：ジャンル
                info -> info,                // 値：Information オブジェクト
                (existing, replacement) -> existing  // 重複した場合は最初の要素を保持
        ))
        .values()  // Mapの値 (Information) のコレクションを取得
        .stream()
        .collect(Collectors.toList());  // コレクションをリストに変換
		//ビジネスロジック 4
		//なし
		//DBへデータ保存 5
		//なし
		//レスポンス値をセット 6
		req.setAttribute("info_set", distinctilist);

		//JSPへフォワード 7
		req.getRequestDispatcher("infocreate.jsp").forward(req, res);
	}

}
