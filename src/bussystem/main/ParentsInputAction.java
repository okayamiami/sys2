package bussystem.main;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.ManageUser;
import bean.ParentsUser;
import dao.ParentsUserDao;
import tool.Action;

public class ParentsInputAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        Map<String, String> errors = new HashMap<>();  // エラーメッセージ格納用マップ


        // パラメーターの取得
        String parents_id = req.getParameter("parents_id");// フォームから取得
        HttpSession session = req.getSession(true);  // セッションを取得
        //String facility_id = (String) session.getAttribute("facility_id"); // セッションから取得
    	ManageUser MU = (ManageUser) session.getAttribute("user");
		String facility_id = MU.getFacility_id();

        // 入力チェック: parents_id が入力されているか、facility_id がセッションに存在するかを確認
        if (parents_id == null) {
        	System.out.println("エラーです");
            errors.put("inputError", "保護者IDを入力してください");
            req.setAttribute("errors", errors);
            req.getRequestDispatcher("parentsinput.jsp").forward(req, res);
            return;  // 処理終了
        }

        // Daoをインスタンス化
        ParentsUserDao PD = new ParentsUserDao();

        // データ取得
        //ParentsUser PU = (ParentsUser) session.getAttribute("user");//ログインユーザーの
        ParentsUser PU = PD.getParentsUserInfo(parents_id, facility_id);

        // 結果が取得できたかどうかをチェック
        if (PU != null) {
            // リクエストにデータを保存
            req.setAttribute("user", PU);
            req.setAttribute("parents_id", parents_id);

            // 結果ページにフォワード
            req.getRequestDispatcher("parentsinfo.jsp").forward(req, res);
        } else {
            // データが見つからない場合のエラーハンドリング
            req.setAttribute("errors", errors);
            req.getRequestDispatcher("parentsinput.jsp").forward(req, res);
        }
    }
}