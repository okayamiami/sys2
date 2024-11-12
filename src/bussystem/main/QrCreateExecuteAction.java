// QrCreateExecuteAction.java
package bussystem.main;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.zxing.WriterException;

import bean.ManageUser;
import qr.QRCodeGenerator;
import tool.Action;

public class QrCreateExecuteAction extends Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        // セッションを取得してログインユーザーを取得
        HttpSession session = req.getSession(true);
        ManageUser mu = (ManageUser) session.getAttribute("user");

        // リクエストパラメータからchild_idとfacility_idを取得
        String child_name = req.getParameter("child_name");
        String child_id = req.getParameter("child_id");
        String facility_id = req.getParameter("facility_id");
        System.out.println(facility_id);

        // QRコードを生成
        String qrImagePath = "";
        try {
            ServletContext context = req.getServletContext(); // サーブレットコンテキストを取得
            QRCodeGenerator qrg = new QRCodeGenerator();
            qrImagePath = qrg.generateQRCode(child_id, facility_id, context);
            System.out.println("QRコードの生成が成功しました。");

        } catch (WriterException | IOException e) {
            e.printStackTrace();
            System.out.println("QRコード生成中にエラーが発生しました。");
        }

        // レスポンス値をセット
        req.setAttribute("child_name", child_name);
        req.setAttribute("qrImagePath", qrImagePath); // QRコード画像のパスをJSPに渡す

        // JSPへフォワード
        req.getRequestDispatcher("qrcreatedone.jsp").forward(req, res);
    }
}
