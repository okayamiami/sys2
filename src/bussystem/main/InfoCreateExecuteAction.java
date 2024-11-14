package bussystem.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.ManageUser;
import dao.InformationDao;
import tool.Action;

public class InfoCreateExecuteAction extends Action {

    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
    	HttpSession session = req.getSession();//セッション
    	ManageUser mu = (ManageUser) session.getAttribute("user");		// ログインユーザーを取得（管理者or先生）
    	String facilityId = mu.getFacility_id();

    	String infoTitle = req.getParameter("title"); // JSPでは name="title"
    	String infoMain = req.getParameter("content"); // JSPでは name="content"
    	String infoGenre = req.getParameter("info_genre"); // JSPでは name="info_genre"


        try {
            InformationDao infoDao = new InformationDao();
            infoDao.saveInfo(infoTitle, infoMain, facilityId, infoGenre);
                req.setAttribute("message", "お知らせ情報が正常に登録されました。");
                req.getRequestDispatcher("infocreateexecute.jsp").forward(req, res);

        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("お知らせ情報登録中にエラーが発生しました。");
        }
    }
}
