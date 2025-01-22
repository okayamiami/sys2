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
import tool.Action;

public class ChildIsAttendAction extends Action {

    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        // 編集を押したときに来る場所

        Map<String, String> errors = new HashMap<>();

        ParentsUser PU = new ParentsUser();
        ManageUser MU = new ManageUser();

        ChildDao CD = new ChildDao();

        // sessionの有効化
        HttpSession session = req.getSession(true);

        // ログインユーザーを一時的に取得
        String user_type = (String) session.getAttribute("user_type");


        try{

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

	        System.out.println(facility_id);

	        Child child = CD.getChildinfo(facility_id, child_id) ;		// 変更する子供情報取得


	        req.setAttribute("child", child);
	        req.setAttribute("facility_id", facility_id);
	        req.setAttribute("child_id", child.getChild_id());
	        req.setAttribute("child_name", child.getChild_name());


	        System.out.println(child.getChild_id());
	        System.out.println(child.getChild_name());


	        //JSPへフォワード 7
			req.getRequestDispatcher("child_is_attend.jsp").forward(req, res);

		} catch (Exception e) {
			req.setAttribute("error", "情報更新中にエラーが発生しました。");
			req.getRequestDispatcher("error.jsp").forward(req, res);
			return;

		}

	}


}
