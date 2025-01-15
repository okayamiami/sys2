package bussystem.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.ManageUser;
import bean.ParentsUser;
import dao.ParentsUserDao;
import tool.Action;

public class ParentsEditExecuteAction extends Action {

    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        HttpSession session = req.getSession(); // セッションを取得
        String user_type = (String) session.getAttribute("user_type");
        String facilityId = null;

        // ユーザー情報をセッションから取得
        Object user = session.getAttribute("user");
        ParentsUserDao PD = new ParentsUserDao();
        ParentsUser PU = new ParentsUser();

        // ユーザータイプで分ける
        if ("P".equals(user_type)) {
            ParentsUser parentsUser = (ParentsUser) user;
            facilityId = parentsUser.getFacility_id();
        } else if ("M".equals(user_type)) {
            ManageUser manageUser = (ManageUser) user;
            facilityId = manageUser.getFacility_id();
        }

        // パラメータを取得
        String parents_id = req.getParameter("parents_id");
        String parents_name = req.getParameter("parents_name");
        String parents_pass = req.getParameter("parents_pass");
        String parents_address = req.getParameter("parents_address");
        String parents_tel = req.getParameter("parents_tel");
        String parents_mail1 = req.getParameter("parents_mail1");
        String parents_mail2 = req.getParameter("parents_mail2");
        String parents_mail3 = req.getParameter("parents_mail3");

        System.out.println(parents_name);
        if ("P".equals(user_type)) {
        // 現在のパスワードをデータベースから取得
        ParentsUser currentUser = PD.getParentsUserInfo(parents_id, facilityId);
        String currentPassword = currentUser.getParents_pass();

        // 入力されたパスワードが現在のパスワードと同じか確認
        if (parents_pass.equals(currentPassword)) {

            req.setAttribute("user", user);
            ParentsUser PU2 = PD.getParentsUserInfo(parents_id, facilityId);
			System.out.println(PU2);
			req.setAttribute("userinfo", PU2);
			req.setAttribute("message", "新しいパスワードは現在のパスワードと同じです。別のパスワードを入力してください。");
            req.getRequestDispatcher("parentseditp.jsp").forward(req, res); // 編集フォームに戻る
            return; // 処理を中断
        }
        }

        // 保護者情報を設定
        ParentsUser parentsUser = new ParentsUser();
        parentsUser.setParents_id(parents_id);
        parentsUser.setParents_name(parents_name);
        parentsUser.setParents_pass(parents_pass);
        parentsUser.setParents_address(parents_address);
        parentsUser.setParents_tel(parents_tel);
        parentsUser.setParents_mail1(parents_mail1);
        parentsUser.setParents_mail2(parents_mail2);
        parentsUser.setParents_mail3(parents_mail3);
        parentsUser.setFacility_id(facilityId);

        try {
            if ("P".equals(user_type)) {
	            // 保護者情報を保存
	            PD.saveParentsUserInfo(parentsUser);
	            parentsUser.setAuthenticated(true); // 保護者情報が正しく更新されるように認証状態を付与
	            session.removeAttribute("user");
	            session.setAttribute("user", parentsUser); // セッションに更新後のユーザー情報を保存ー＞header用に
            }else if("M".equals(user_type)){
            	PD.saveParentsUserInfo(parentsUser);
            }

            // ユーザー情報をリクエストに設定し、メッセージを表示
            req.setAttribute("user", user);
            req.setAttribute("user_type", user_type);
            req.setAttribute("parents_id", parents_id);
            req.setAttribute("message", "変更が完了しました。");
            req.getRequestDispatcher("parentseditexecute.jsp").forward(req, res);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("保護者情報の登録中にエラーが発生しました。");
            req.setAttribute("message", "エラーが発生しました。再度お試しください。");
            req.getRequestDispatcher("parentsedit.jsp").forward(req, res);
        }
    }
}
