package bussystem.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Bus;
import bean.ManageUser;
import dao.BusDao;
import tool.Action;

public class BusInfoAction extends Action{
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res)throws Exception{
		HttpSession session = req.getSession(true);
		ManageUser mu = (ManageUser) session.getAttribute("user");	// ログインユーザーを取得
		BusDao bDao = new BusDao();
		List<Bus> busSet = bDao.getBus(mu.getFacility_id());

		req.setAttribute("bus_set",busSet);
		req.getRequestDispatcher("businfo.jsp").forward(req, res);

	}

}
