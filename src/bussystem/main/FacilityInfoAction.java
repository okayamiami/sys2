package bussystem.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Facility;
import bean.ManageUser;
import dao.FacilityDao;
import tool.Action;

public class FacilityInfoAction extends Action{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		HttpSession session = req.getSession(true);
		ManageUser mu = (ManageUser) session.getAttribute("user");	// ログインユーザーを取得
		FacilityDao fcdao = new FacilityDao();
		String fcid;
		Facility fc = new Facility();
		fcid = mu.getFacility_id();
		fc = fcdao.getFacilityInfo(fcid);
		req.setAttribute("fc",fc );
		req.getRequestDispatcher("fcilityinfo.jsp").forward(req, res);

	}

}
