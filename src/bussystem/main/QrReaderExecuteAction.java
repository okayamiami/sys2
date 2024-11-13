package bussystem.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.GetDao;
import tool.Action;

public class QrReaderExecuteAction extends Action {

    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        // QRコードで読み取ったデータを取得
        String qrData = req.getParameter("qrData");

        // QRコードのデータからfacility_idとchild_idを分割
        String[] qrValues = qrData.split(",");
        String child_id = qrValues[0];
        String facility_id = qrValues[1];


        // JSPから取得したbus_idを取得
        String bus_id = req.getParameter("bus_id");

        // GetDaoインスタンスを作成し、changeGetメソッドを実行
        GetDao dao = new GetDao();
        boolean isUpdated = dao.changeGet(bus_id, child_id, facility_id);

        if (isUpdated) {
            req.setAttribute("message", "出席情報が更新されました。");
        } else {
            req.setAttribute("message", "出席情報の更新に失敗しました。");
        }

        // 結果をJSPに渡して表示する
        req.getRequestDispatcher("qrresult.jsp").forward(req, res);
    }
}
