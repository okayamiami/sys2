package bussystem.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Facility;
import bean.ManageUser;
import dao.FacilityDao;
import tool.Action;

public class FacilityInfoAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        HttpSession session = req.getSession(false); // セッションの取得（存在しない場合はnullを返す）
        if (session == null || session.getAttribute("user") == null) {
            res.sendRedirect("login.jsp"); // ログインページへリダイレクト
            return;
        }

        ManageUser mu = (ManageUser) session.getAttribute("user"); // ログインユーザーを取得
        FacilityDao fcdao = new FacilityDao();
        String fcid = mu.getFacility_id();

        try {
            Facility fc = fcdao.getFacilityInfo(fcid);
            if (fc == null) {
                req.setAttribute("error", "施設情報が見つかりません。");
                req.getRequestDispatcher("error.jsp").forward(req, res);
                return;
            }
            req.setAttribute("fc", fc);
            req.getRequestDispatcher("facilityinfo.jsp").forward(req, res);
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "システムエラーが発生しました。");
            req.getRequestDispatcher("error.jsp").forward(req, res);
        }
    }
}
