package bussystem.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Facility;
import dao.FacilityDao;
import tool.Action;

public class FacilityInfoEditExecuteAction extends Action{
	@Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		Facility fc = new Facility();
		FacilityDao fcdao = new FacilityDao();
		String facility_id = req.getParameter("facility_id");
		String facility_name = req.getParameter("facility_name");
		String facility_address = req.getParameter("facility_address");
		String facility_tel = req.getParameter("facility_tel");
		String facility_mail = req.getParameter("facility_mail");
		String facilityplanparam = req.getParameter("facility_plan");
		Boolean facility_plan = Boolean.parseBoolean(facilityplanparam);


		fc.setFacility_id(facility_id);
		fc.setFacility_name(facility_name);
		fc.setFacility_address(facility_address);
		fc.setFacility_tel(facility_tel);
		fc.setFacility_mail(facility_mail);
		fc.setFacility_plan(facility_plan);
		try{
			fcdao.saveFacilityInfo(fc);
			req.getRequestDispatcher("facilitydone.jsp").forward(req, res);
		}catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("message", "エラーが発生しました。再度お試しください。");
            req.getRequestDispatcher("error.jsp").forward(req, res);
		}

	}
}
