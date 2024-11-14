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

        String infoTitle = req.getParameter("infoTitle");
        String infoMain = req.getParameter("infoMain");
        String infoGenre = req.getParameter("infoGenre");

        try {
            InformationDao infoDao = new InformationDao();
            boolean isSaved = infoDao.saveInfo(infoTitle, infoMain, facilityId, infoGenre);

            if (isSaved) {
                // 保存が成功した場合
                req.setAttribute("message", "お知らせ情報が正常に登録されました。");
                req.getRequestDispatcher("infocreateerror.jsp").forward(req, res);
            } else {
                // 保存が失敗した場合
                req.setAttribute("message", "お知らせ情報の登録に失敗しました。");
                req.getRequestDispatcher("infocreateerror.jsp").forward(req, res);
            }
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("message", "エラーが発生しました: " + e.getMessage());
            req.getRequestDispatcher("infocreateerror.jsp").forward(req, res);
        }
    }
}
