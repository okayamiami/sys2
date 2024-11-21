package bussystem.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.Action;

public class QrReaderAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        // Androidから送信された bus_id, facility_id, child_id を取得
        String busId = req.getParameter("bus_id");

        // 取得したパラメータが不足していないか確認
        if (busId == null) {
            req.setAttribute("errorMessage", "必要な情報が不足しています。");
            req.getRequestDispatcher("qrreader.jsp").forward(req, res);
            return;
        }
        req.setAttribute("bus_id", busId);


        // 結果をqrreader.jspにフォワード
        req.getRequestDispatcher("qrreader.jsp").forward(req, res);
    }
}

