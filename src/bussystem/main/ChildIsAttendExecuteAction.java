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

public class ChildIsAttendExecuteAction extends Action {
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        // 編集を押したときに来る場所

        Map<String, String> errors = new HashMap<>();

        ParentsUser PU = new ParentsUser();
        ManageUser MU = new ManageUser();

        ChildDao CD = new ChildDao();
        ClassCdDao CC = new ClassCdDao();

        // sessionの有効化
        HttpSession session = req.getSession(true);

        // ログインユーザーを一時的に取得
        String user_type = (String) session.getAttribute("user_type");


        try{

	        String child_id = req.getParameter("child_id");			// 削除または非表示にする子供ID
	        String facility_id = req.getParameter("facility_id");	;

	        Child child = CD.getChildIdinfo(facility_id, child_id) ;		// 変更する子供情報取得
	        CD.saveAbsChildinfo(child);

		} catch (Exception e) {
			req.setAttribute("error", "情報更新中にエラーが発生しました。");
			req.getRequestDispatcher("error.jsp").forward(req, res);
			return;

		}


		//JSPへフォワード 7
		req.getRequestDispatcher("child.jsp").forward(req, res);
	}


}
