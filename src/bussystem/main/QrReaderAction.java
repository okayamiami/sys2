package bussystem.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.GetDao;
import tool.Action;

public class QrReaderAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        // Androidから送信された bus_id, facility_id, child_id を取得
        String busId = req.getParameter("bus_id");
        String facilityId = req.getParameter("facility_id");
        String childId = req.getParameter("child_id");

        // 取得したパラメータが不足していないか確認
        if (busId == null || facilityId == null || childId == null) {
            req.setAttribute("error", "必要な情報が不足しています。");
            req.getRequestDispatcher("qrreader.jsp").forward(req, res);
            return;
        }

        // 出欠状態を切り替える
        GetDao getDao = new GetDao();
        boolean isSuccess = getDao.changeGet(busId, childId, facilityId);

        if (isSuccess) {
            req.setAttribute("message", "出欠状態の更新に成功しました。");
        } else {
            req.setAttribute("error", "出欠状態の更新に失敗しました。");
        }

        // 結果をqrreader.jspにフォワード
        req.getRequestDispatcher("qrreader.jsp").forward(req, res);
    }
}

