package bussystem.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.ManageUser;
import bean.ParentsUser;
import dao.ManageUserDao;
import dao.ParentsUserDao;
import tool.Action;

public class NewInfoExecuteAction extends Action{
	@Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		HttpSession session = req.getSession(); // セッションを取得

        String type = (String) session.getAttribute("user_type");

        String facilityId = null;

        Object user = session.getAttribute("user");

        if("M".equals(type)||"T".equals(type)){

        	ManageUserDao mdao = new ManageUserDao();

        	ManageUser mu = new ManageUser();

        	ManageUser manageUser = (ManageUser) user;

            facilityId = manageUser.getFacility_id();

            String user_id = req.getParameter("user_id");

            String user_name = req.getParameter("user_name");

            String user_pass = req.getParameter("user_pass");

            mu.setUser_id(user_id);
            mu.setUser_name(user_name);
            mu.setUser_pass(user_pass);
            mu.setFacility_id(facilityId);

            try {
                // 保護者情報を保存

                mdao.saveManageUserInfo(mu);
                // ユーザー情報をリクエストに設定し、メッセージを表示

                req.getRequestDispatcher("menu.jsp").forward(req, res);

            } catch (Exception e) {
                e.printStackTrace();
                req.setAttribute("message", "エラーが発生しました。再度お試しください。");
                req.getRequestDispatcher("error.jsp").forward(req, res);
            }
        }else{
        	ParentsUserDao pdao = new ParentsUserDao();
        	ParentsUser pu = new ParentsUser();
        	ParentsUser parentsUser = (ParentsUser) user;
            facilityId = parentsUser.getFacility_id();
            String parents_id = req.getParameter("parents_id");
            String parents_name = req.getParameter("parents_name");
            String parents_address = req.getParameter("parents_address");
            String parents_tel = req.getParameter("parents_tel");
            String parents_pass = req.getParameter("parents_pass");
            String parents_mail1 = req.getParameter("parents_mail1");
            String parents_mail2 = req.getParameter("parents_mail2");
            String parents_mail3 = req.getParameter("parents_mail3");
            pu.setParents_id(parents_id);
            pu.setParents_name(parents_name);
            pu.setParents_address(parents_address);
            pu.setParents_tel(parents_tel);
            pu.setParents_pass(parents_pass);
            pu.setParents_mail1(parents_mail1);
            pu.setParents_mail2(parents_mail2);
            pu.setParents_mail3(parents_mail3);
            pu.setFacility_id(facilityId);
            try {
                // 保護者情報を保存
                pdao.saveParentsUserInfo(pu);
                // ユーザー情報をリクエストに設定し、メッセージを表示
                req.getRequestDispatcher("menu.jsp").forward(req, res);

            } catch (Exception e) {
                e.printStackTrace();
                req.setAttribute("message", "エラーが発生しました。再度お試しください。");
                req.getRequestDispatcher("error.jsp").forward(req, res);
//             	ここまで作った
            }
        }
	}
}
