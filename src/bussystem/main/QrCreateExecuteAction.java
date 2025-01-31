// QrCreateExecuteAction.java
package bussystem.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.Action;

public class QrCreateExecuteAction extends Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        // リクエストパラメータからchild_idとfacility_idを取得
        String child_name = req.getParameter("child_name");
        String child_id = req.getParameter("child_id");
        String facility_id = req.getParameter("facility_id");

        // QRコードを生成



        // レスポンス値をセット
        req.setAttribute("child_name", child_name);
        req.setAttribute("child_id", child_id);
        req.setAttribute("facility_id", facility_id);
        // QRコード画像のパスをJSPに渡す

        // JSPへフォワード
        req.getRequestDispatcher("qrcreatedone.jsp").forward(req, res);
    }
}
