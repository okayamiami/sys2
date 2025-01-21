package bussystem.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Bus;
import bean.ManageUser;
import dao.BusDao;
import tool.Action;

public class BusDeleteExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        // ローカル変数の宣言
        HttpSession session = req.getSession(); // セッション
        ManageUser mu = (ManageUser) session.getAttribute("user"); // ログインユーザーを取得（管理者 or 先生）
        String facility_id = mu.getFacility_id(); // ログインユーザーの施設IDを取得

        // フォームから取得する info_id
        String bus_id = req.getParameter("bus_id"); // info_id をリクエストパラメータから取得

        // info_id が null または空でないか確認
        if (bus_id == null || bus_id.isEmpty()) {
            req.setAttribute("errorMessage", "削除対象のバスIDが無効です。");
            req.getRequestDispatcher("error.jsp").forward(req, res);
            return;
        }

        // DAO インスタンスを作成
        BusDao bDao = new BusDao(); // connection は情報取得時に必要なら指定

        // 情報を取得
        Bus bus_set = bDao.getBusinfo(facility_id,bus_id);

        if (bus_set == null) {
            // bus_id に該当する情報が存在しない場合
            req.setAttribute("delete_message", "対象のバス情報は既に削除されています。");
            req.getRequestDispatcher("busdeleteexecute.jsp").forward(req, res);
            return;
        }

        // 削除処理を実行
        boolean isDeleted = bDao.setBusAsNotAttend(facility_id, bus_id);

        if (isDeleted) {
            // 削除成功
            req.setAttribute("delete_message", "バス情報が正常に削除されました。");
            req.getRequestDispatcher("busdeleteexecute.jsp").forward(req, res);
        } else {
            // 削除失敗
            req.setAttribute("errorMessage", "バス情報の削除に失敗しました。");
            req.getRequestDispatcher("error.jsp").forward(req, res);
        }
    }
}