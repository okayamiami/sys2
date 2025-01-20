package bussystem.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Facility;
import dao.FacilityDao;
import tool.Action;

public class FacilityInfoEditAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        HttpSession session = req.getSession(false); // セッションの確認（nullの場合は新しいセッションを作らない）
        if (session == null || session.getAttribute("user") == null) {
            res.sendRedirect("login.jsp"); // セッションがない場合はログイン画面へリダイレクト
            return;
        }

        String facility_id = req.getParameter("facility_id"); // リクエストパラメータから施設IDを取得
        if (facility_id == null || facility_id.isEmpty()) {
            req.setAttribute("error", "施設IDが指定されていません。");
            req.getRequestDispatcher("error.jsp").forward(req, res); // エラーページにフォワード
            return;
        }

        FacilityDao fcdao = new FacilityDao();

        try {
            Facility fc = fcdao.getFacilityInfo(facility_id);
            if (fc == null) {
                req.setAttribute("error", "指定された施設情報が見つかりませんでした。");
                req.getRequestDispatcher("error.jsp").forward(req, res);
                return;
            }

            req.setAttribute("fc", fc); // 施設情報をリクエストスコープにセット
            req.getRequestDispatcher("facilityinfoedit.jsp").forward(req, res); // 編集ページへフォワード

        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "システムエラーが発生しました。管理者にお問い合わせください。");
            req.getRequestDispatcher("error.jsp").forward(req, res); // システムエラーをエラーページに表示
        }
    }
}
