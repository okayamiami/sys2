package bussystem.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Child;
import bean.ClassCd;
import bean.ManageUser;
import dao.ChildDao;
import dao.ClassCdDao;
import tool.Action;

public class ParentsIDInputAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        Map<String, String> errors = new HashMap<>();


        // パラメーターの取得
        String parents_id = req.getParameter("parents_id");// フォームから取得
        HttpSession session = req.getSession(true);  // セッションを取得
    	ManageUser MU = (ManageUser) session.getAttribute("user");//ログインユーザーは管理者
		String facility_id = MU.getFacility_id();//ファシリティIDゲット


	    if (parents_id == null || parents_id.trim().isEmpty()) {
	        req.setAttribute("errorMessage", "保護者IDを入力してください。");
	        req.getRequestDispatcher("/parentsInput.jsp").forward(req, res);
	        return;
	    }


			//Beanをインスタンス化しクラス名を取得祈願　　引数null

			ClassCd Cd = new ClassCd();
			ChildDao CD = new ChildDao();
			ClassCdDao CC = new ClassCdDao();

			//子供の情報を取得
		    List<Child> CI = new ArrayList<>();
			CI = CD.getChildrenByParentId(parents_id, facility_id);
			//全クラス情報取得
			List<ClassCd> class_set = CC.getClassCdinfo(facility_id);

	        // リクエストにデータを保存
			req.setAttribute("user", MU);
			req.setAttribute("parents_id", parents_id);
			req.setAttribute("class_set", class_set);
			req.setAttribute("userCI", CI);

			// 結果ページにフォワード
			req.getRequestDispatcher("childinfo.jsp").forward(req, res);
    }
}