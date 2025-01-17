package bussystem;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.ManageUser;
import bean.ParentsUser;
import dao.FacilityDao;
import dao.ManageUserDao;
import dao.ParentsUserDao;
import tool.Action;

public class LoginExecuteAction extends Action{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

	    HttpSession session = req.getSession(true);
	    String url = "";
	    Map<String, String> errors = new HashMap<>();

	    ManageUser MU = new ManageUser();
	    ManageUserDao MUDao = new ManageUserDao();
	    ParentsUser PU = new ParentsUser();
	    ParentsUserDao PUDao = new ParentsUserDao();
	    String user_type = null;
	    FacilityDao FDDao = new FacilityDao();
	    Boolean FP;

	    String user_id = req.getParameter("user_id");
	    String user_pass = req.getParameter("user_pass");
	    String facility_id = req.getParameter("facility_id");

	    if(user_id.contains("M") || user_id.contains("T")){
	        if(user_id.contains("M")){
	            user_type = ("M");
	        } else if (user_id.contains("T")){
	            user_type = ("T");
	        }
	        MU = MUDao.login(user_id, user_pass, facility_id);
	        FP = FDDao.getFacilityInfo(facility_id).getFacility_plan();
	        if(MU == null){
	            errors.put("kome", "ログインに失敗しました。IDまたはパスワードが違います。");
	            req.setAttribute("errors", errors);
	            req.getRequestDispatcher("login.jsp").forward(req, res);
	        } else {
	            MU.setAuthenticated(true);
	            // セッションに "user" として ManageUser インスタンスを格納
	            session.setAttribute("user", MU);
	            session.setAttribute("user_id", MU.getUser_id());
	            session.setAttribute("user_type", user_type);
	            session.setAttribute("fc_plan", FP);
	            if(MU.getUser_name()==null){
	            	url = "main/NewInfo.action";
	            }else{
	            	url = "main/Menu.action";
	            }
	            res.sendRedirect(url);
	        }
	    } else if(user_id.contains("P")) {
	        user_type = ("P");
	        PU = PUDao.parentsLogin(user_id, user_pass, facility_id);
	        FP = FDDao.getFacilityInfo(facility_id).getFacility_plan();
	        if(PU == null){
	            errors.put("kome", "ログインに失敗しました。IDまたはパスワードが違います。");
	            req.setAttribute("errors", errors);
	            req.getRequestDispatcher("login.jsp").forward(req, res);
	        } else {
	            PU.setAuthenticated(true);
	            session.setAttribute("user", PU);
	            session.setAttribute("user_type", user_type);
	            session.setAttribute("fc_plan", FP);
	            if(PU.getParents_name()==null){
	            	url = "main/NewInfo.action";
	            }else{
	            	url = "main/Menu.action";
	            }
	            res.sendRedirect(url);
	        }
	    }
	}
}
