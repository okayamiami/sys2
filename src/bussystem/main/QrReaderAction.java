package bussystem.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.ManageUser;
import dao.BusDao;
import dao.GetDao;
import tool.Action;

public class QrReaderAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

    	HttpSession session = req.getSession(true);// セッションを取得
		ManageUser mu = (ManageUser) session.getAttribute("user");// ログインユーザーを取得
		BusDao bDao = new BusDao();
		GetDao gDao = new GetDao();


        // 送信された bus_id, facility_id, child_id を取得
        String busId = req.getParameter("bus_id");
        String busName = req.getParameter("bus_name");

        int countAttend = gDao.countAttendees(mu.getFacility_id(), busId);

        // 取得したパラメータが不足していないか確認
        if (busId == null) {
            req.setAttribute("errorMessage", "必要な情報が不足しています。");
            req.getRequestDispatcher("qrreader.jsp").forward(req, res);
            return;
        }
        req.setAttribute("bus_id", busId);
        req.setAttribute("bus_name", busName);
        req.setAttribute("countAttend", countAttend);


        // 結果をqrreader.jspにフォワード
        req.getRequestDispatcher("qrreader.jsp").forward(req, res);
    }
}

