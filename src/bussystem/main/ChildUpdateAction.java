package bussystem.main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Child;
import bean.ClassCd;
import bean.ManageUser;
import bean.ParentsUser;
import dao.ChildDao;
import dao.ClassCdDao;
import tool.Action;

public class ChildUpdateAction extends Action {

    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        // 編集を押したときに来る場所

        Map<String, String> errors = new HashMap<>();

        // Beanをインスタンス化
        ParentsUser PU = new ParentsUser();
        ManageUser MU = new ManageUser();

        // Daoをインスタンス化
        ChildDao CD = new ChildDao();
        ClassCdDao CC = new ClassCdDao();

        // sessionの有効化
        HttpSession session = req.getSession(true);

        // ログインユーザーを一時的に取得
        String user_type = (String) session.getAttribute("user_type");

        // 引数設定 外部
        String user_id = null;
        String facility_id = null;

        if ("P".equals(user_type)) {
            PU = (ParentsUser) session.getAttribute("user");
            user_id = PU.getParents_id();
            facility_id = PU.getFacility_id();
        } else if ("M".equals(user_type)) {
            MU = (ManageUser) session.getAttribute("user");
            user_id = req.getParameter("parents_id"); // 管理者の場合、保護者IDをリクエストから取得
            facility_id = MU.getFacility_id();
        } else {
            errors.put("kome", "セッションに不正なユーザー情報が格納されています。");
        }


        // "P"の場合（保護者の場合）
        if ("P".equals(user_type)) {
            // 子供のIDをリクエストパラメータから取得
            String child_id = req.getParameter("child_id"); // 子供IDをリクエストから取得

            // child_idが存在すれば、その情報を取得
            if (child_id != null) {
                Child childInfo = CD.getChildinfo(facility_id, child_id); // getChildinfoを使用
                req.setAttribute("childInfo", childInfo); // 取得した子供情報をリクエストに設定
            }

            // 子供情報を取得 -> ログインユーザーの子供情報取得
            List<Child> CI = CD.getChildrenByParentId(user_id, facility_id);

            // 全クラス情報取得 -> クラス名の取得
            List<ClassCd> class_set = CC.getClassCdinfo(facility_id);

            // 取得した情報をリクエストに設定
            req.setAttribute("CI", CI);
            req.setAttribute("class_set", class_set);

            // JSPに転送
            req.getRequestDispatcher("childupdate.jsp").forward(req, res);

        } else if ("M".equals(user_type)) {
            // 管理者の場合もほとんど同じ
        	// 子供のIDをリクエストパラメータから取得
            String child_id = req.getParameter("child_id"); // 子供IDをリクエストから取得

            // child_idが存在すれば、その情報を取得
            if (child_id != null) {
                Child childInfo = CD.getChildinfo(facility_id, child_id); // getChildinfoを使用
                req.setAttribute("childInfo", childInfo); // 取得した子供情報をリクエストに設定
            }

            // 子供情報を取得 -> ログインユーザーの子供情報取得
            List<Child> CI = CD.getChildrenByParentId(user_id, facility_id);

            // 全クラス情報取得 -> クラス名の取得
            List<ClassCd> class_set = CC.getClassCdinfo(facility_id);

            // 取得した情報をリクエストに設定
            req.setAttribute("CI", CI);
            req.setAttribute("class_set", class_set);
            req.setAttribute("parents_id", user_id);
            req.setAttribute("user_type", user_type);
            // JSPに転送
            req.getRequestDispatcher("childupdate.jsp").forward(req, res);
        } else {
            errors.put("kome", "情報取得に失敗しました。");
            req.setAttribute("errors", errors);
            req.getRequestDispatcher("error.jsp").forward(req, res);
        }
    }}
