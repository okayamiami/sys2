package bussystem.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.Action;

public class FacilityInfoMenuAction extends Action{
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//ローカル変数の宣言 1
		//JSPへフォワード 7

			req.getRequestDispatcher("facilityinfomenu.jsp").forward(req, res);

	}

}
