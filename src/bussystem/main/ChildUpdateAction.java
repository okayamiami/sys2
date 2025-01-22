package bussystem.main;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Child;
import bean.ManageUser;
import bean.ParentsUser;
import dao.ChildDao;
import dao.ClassCdDao;
import tool.Action;

public class ChildUpdateAction extends Action {

    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

    	// 子供一覧　削除・非表示

        Map<String, String> errors = new HashMap<>();

        ParentsUser PU = new ParentsUser();
        ManageUser MU = new ManageUser();

        ChildDao CD = new ChildDao();
        ClassCdDao CC = new ClassCdDao();

        // sessionの有効化
        HttpSession session = req.getSession(true);

        // ログインユーザーを一時的に取得
        String user_type = (String) session.getAttribute("user_type");


        String child_id = req.getParameter("child_id");			// 削除または非表示にする子供ID
        String facility_id = null;

        if ("P".equals(user_type)) {
            PU = (ParentsUser) session.getAttribute("user");
            facility_id = PU.getFacility_id();
        } else if ("M".equals(user_type)) {
            MU = (ManageUser) session.getAttribute("user");
            facility_id = MU.getFacility_id();
        } else {
            errors.put("kome", "セッションに不正なユーザー情報が格納されています。");
        }

        // 変更する子供情報取得
        Child child = CD.getChildinfo(facility_id, child_id) ;


    }
}
