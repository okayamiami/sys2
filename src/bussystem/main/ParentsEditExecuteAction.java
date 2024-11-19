package bussystem.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.ParentsUser;
import dao.ParentsUserDao;
import tool.Action;

public class ParentsEditExecuteAction extends Action{

   public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
    	HttpSession session = req.getSession();//セッション
    	ParentsUser PU = (ParentsUser) session.getAttribute("user");		// ログインユーザーを取得（管理者or先生）
    	String facilityId = PU.getFacility_id();
    	ParentsUserDao PD = new ParentsUserDao();


    	// パラメータを取得
        String parents_id = req.getParameter("parents_id");
        String parents_name = req.getParameter("parents_name");
        String parents_address = req.getParameter("parents_address");
        String parents_tel = req.getParameter("parents_tel");
        String parents_mail1 = req.getParameter("parents_mail1");
        String parents_mail2 = req.getParameter("parents_mail2");
        String parents_mail3 = req.getParameter("parents_mail3");

        ParentsUser PUuser = new ParentsUser();
        PUuser.setParents_id(parents_id);
        PUuser.setParents_name(parents_name);
        PUuser.setParents_address(parents_address);
        PUuser.setParents_tel(parents_tel);
        PUuser.setParents_mail1(parents_mail1);
        PUuser.setParents_mail2(parents_mail2);
        PUuser.setParents_mail3(parents_mail3);
        PUuser.setFacility_id(facilityId);


        try {
        		PD.saveParentsUserInfo(PUuser);
        		session.setAttribute("user", PU);
                req.setAttribute("message", "変更が完了しました。");
                req.getRequestDispatcher("parentseditexecute.jsp").forward(req, res);
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("お知らせ情報登録中にエラーが発生しました。");
        }
    }
}
