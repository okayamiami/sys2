package bussystem.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tool.Action;

public class SyslogoutAction extends Action{
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		HttpSession session=req.getSession();

		if(session.getAttribute("user")!=null) {
			session.removeAttribute("user");

			req.getRequestDispatcher("syslogout.jsp").forward(req, res);
		}
	}

}
