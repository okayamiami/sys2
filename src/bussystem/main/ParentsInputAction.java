package bussystem.main;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.ParentsUser;
import dao.ParentsUserDao;
import tool.Action;

public class ParentsInputAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        Map<String, String> errors = new HashMap<>();  // エラーメッセージ格納用マップ


        // パラメーターの取得
        String user_id = req.getParameter("parents_id");  // フォームから取得
        HttpSession session = req.getSession(true);  // セッションを取得
        String facility_id = (String) session.getAttribute("facility_id"); // セッションから取得

        // 入力チェック: parents_id が入力されているか、facility_id がセッションに存在するかを確認
        if (user_id == null || user_id.isEmpty() || facility_id == null || facility_id.isEmpty()) {
        	System.out.println("エラーです");
            errors.put("inputError", "保護者IDを入力してください");
            req.setAttribute("errors", errors);
            req.getRequestDispatcher("parentsinput.jsp").forward(req, res);
            return;  // 処理終了
        }

        // Daoをインスタンス化
        ParentsUserDao PD = new ParentsUserDao();

        // データ取得
        ParentsUser PU = PD.getParentsUserInfo(user_id, facility_id);

        // 結果が取得できたかどうかをチェック
        if (PU != null) {
            // セッションにデータを保存
            session.setAttribute("user", PU);

            // 結果ページにフォワード
            req.getRequestDispatcher("parentsinfo.jsp").forward(req, res);
        } else {
            // データが見つからない場合のエラーハンドリング
            errors.put("notFound", "指定された保護者IDまたは施設IDに一致する情報が見つかりませんでした。");
            req.setAttribute("errors", errors);
            req.getRequestDispatcher("parentsinput.jsp").forward(req, res);
        }
    }
}