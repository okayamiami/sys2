package bussystem.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Information; // Information クラスのインポート
import bean.ManageUser;
import dao.InformationDao; // InformationDao のインポート
import tool.Action;

public class InfoDeleteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        // ローカル変数の宣言
        HttpSession session = req.getSession(); // セッション
        ManageUser mu = (ManageUser) session.getAttribute("user"); // ログインユーザーを取得（管理者 or 先生）
        String facility_id = mu.getFacility_id(); // ログインユーザーの施設IDを取得

        // フォームから取得する info_id
        String info_id = req.getParameter("info_id"); // info_id をリクエストパラメータから取得

        // info_id が null または空でないか確認
        if (info_id == null || info_id.isEmpty()) {
            req.setAttribute("errorMessage", "削除対象のお知らせIDが無効です。");
            req.getRequestDispatcher("error.jsp").forward(req, res);
            return;
        }

        // DAO インスタンスを作成
        InformationDao iDao = new InformationDao(); // connection は情報取得時に必要なら指定

        // 情報を取得
        Information info_set = iDao.getInfoContent(facility_id, info_id);

        if (info_set != null) {
            req.setAttribute("info_set", info_set);
            req.getRequestDispatcher("infodelete.jsp").forward(req, res);
            return;
        }else {
        	req.setAttribute("delete_message", "対象のお知らせは既に削除されています。");
            req.getRequestDispatcher("infodeleteexecute.jsp").forward(req, res);
            return;
        }

    }
}
