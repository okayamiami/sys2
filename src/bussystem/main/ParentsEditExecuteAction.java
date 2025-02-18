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

            req.setAttribute("parents_id", parents_id);

            // 完了画面で表示する
            req.setAttribute("parents_name", parents_name);
            req.setAttribute("parents_pass", parents_pass);
            req.setAttribute("parents_address", parents_address);
            req.setAttribute("parents_tel", parents_tel);
            req.setAttribute("parents_mail1", parents_mail1);
            req.setAttribute("parents_mail2", parents_mail2);
            req.setAttribute("parents_mail3", parents_mail3);

            req.setAttribute("message", "以下情報で保護者情報の変更が完了しました。");
            req.getRequestDispatcher("parentseditexecute.jsp").forward(req, res);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("保護者情報の登録中にエラーが発生しました。");
            req.setAttribute("message", "エラーが発生しました。再度お試しください。");
            req.getRequestDispatcher("parentsedit.jsp").forward(req, res);
        }
    }
}
