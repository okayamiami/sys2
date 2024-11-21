package bussystem.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.GetDao;
import tool.Action;

public class QrReaderExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
    	System.out.println("111111111111111111111111");
        try {
            // QRコードで読み取ったデータを取得
            String qrData = req.getParameter("qrData");
            System.out.println("222222222222222222222222222222");
            // QRコードデータがnullまたは空の場合、エラーメッセージを設定してリダイレクト
            if (qrData == null || qrData.isEmpty()) {
                req.setAttribute("errorMessage", "QRコードのデータが無効です。");
                req.getRequestDispatcher("error.jsp").forward(req, res);
                return;
            }
            System.out.println("3333333333333333333333333333333");

            // QRコードのデータからfacility_idとchild_idを分割
            String[] qrValues = qrData.split(",");
            if (qrValues.length != 2) {
                req.setAttribute("errorMessage", "QRコードのデータ形式が正しくありません。");
                req.getRequestDispatcher("error.jsp").forward(req, res);
                return;
            }
            System.out.println("4444444444444444444444444444444");

            String child_id = qrValues[0];
            System.out.println("5555555555555555555555555");
            String facility_id = qrValues[1];
            System.out.println("6666666666666666666666666");

            // JSPから取得したbus_idを取得
            String bus_id = req.getParameter("bus_id");
            System.out.println("7777777777777777777777777");

            // bus_idがnullまたは空の場合、エラーメッセージを設定してリダイレクト
            if (bus_id == null || bus_id.isEmpty()) {
                req.setAttribute("errorMessage", "バスIDが無効です。");
                req.getRequestDispatcher("error.jsp").forward(req, res);
                return;
            }
            System.out.println("888888888888888888888888888");

            // GetDaoインスタンスを作成し、changeGetメソッドを実行
            GetDao dao = new GetDao();
            boolean isUpdated = dao.changeGet(bus_id, child_id, facility_id);
            System.out.println("99999999999999999999");

            if (isUpdated) {
                // 成功メッセージを設定
                req.setAttribute("message", "出席情報が更新されました。");
                req.getRequestDispatcher("qrresult.jsp").forward(req, res);
            } else {
                // 失敗メッセージを設定
                req.setAttribute("errorMessage", "出席情報の更新に失敗しました。");
                req.getRequestDispatcher("error.jsp").forward(req, res);
            }
        } catch (Exception e) {
            // エラー発生時の例外処理
            e.printStackTrace();
            req.setAttribute("errorMessage", "システムエラーが発生しました。もう一度お試しください。");
            req.getRequestDispatcher("error.jsp").forward(req, res);
        }
    }
}

