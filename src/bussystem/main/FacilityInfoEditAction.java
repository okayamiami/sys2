package bussystem.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Facility;
import dao.FacilityDao;
import tool.Action;

public class FacilityInfoEditAction extends Action{
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		HttpSession session = req.getSession();
		String facility_id;
		Facility fc;
		FacilityDao fcdao = new FacilityDao();
		facility_id = req.getParameter("facility_id");

		fc = fcdao.getFacilityInfo(facility_id);

		req.setAttribute("fc",fc );
		req.getRequestDispatcher("facilityinfoedit.jsp").forward(req, res);





	}

}
