package bussystem.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Information;
import bean.ManageUser;
import bean.ParentsUser;
import dao.InformationDao;
import tool.Action;

public class InfoContentAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        // ローカル変数の宣言
        HttpSession session = req.getSession(); // セッション
        String type = (String) session.getAttribute("user_type"); // ユーザータイプを取得
        InformationDao iDao = new InformationDao();
        String infoId = req.getParameter("info_id"); // info_idを取得

        // ログインユーザーを取得
        Object user = session.getAttribute("user");
        if ("M".equals(type)) {
            // 管理者 or 先生の場合
            ManageUser mu = (ManageUser) user;
            // DBからお知らせ内容を取得
            Information info_set = iDao.getInfoContent(mu.getFacility_id(), infoId);
            if (info_set == null) {
                // info_id に該当する情報が存在しない場合
                req.setAttribute("delete_message", "対象のお知らせは既に削除されています。");
                req.getRequestDispatcher("infodeleteexecute.jsp").forward(req, res);
                return;
            }
            // リクエスト属性にセット
            req.setAttribute("user_type", type);
            req.setAttribute("info_set", info_set);

        } else if ("P".equals(type)) {
            // 保護者の場合
            ParentsUser pu = (ParentsUser) user;
            // DBからお知らせ内容を取得
            Information info_set = iDao.getInfoContent(pu.getFacility_id(), infoId);
            if (info_set == null) {
                // info_id に該当する情報が存在しない場合
                req.setAttribute("delete_message", "対象のお知らせは既に削除されています。");
                req.getRequestDispatcher("infodeleteexecute.jsp").forward(req, res);
                return;
            }
            // リクエスト属性にセット
            req.setAttribute("user_type", type);
            req.setAttribute("info_set", info_set);
        }

        // JSPへフォワード
        req.getRequestDispatcher("infocontent.jsp").forward(req, res);
    }
}


