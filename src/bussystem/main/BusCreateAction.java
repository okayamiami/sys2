package bussystem.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.Action;

// バス情報入力画面
public class BusCreateAction extends Action {

	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {


		// JSPへフォワード 7
		req.getRequestDispatcher("buscreate.jsp").forward(req, res);
	}
}