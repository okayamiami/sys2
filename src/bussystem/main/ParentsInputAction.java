package bussystem.main;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.ParentsUser;
import dao.ParentsUserDao;
import tool.Action;

public class ParentsInputAction extends Action{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String url = "";
		Map<String, String> errors = new HashMap<>();

		// Beanをインスタンス化
		ParentsUser PU = new ParentsUser();

		// Daoをインスタンス化
		ParentsUserDao PD = new ParentsUserDao();

		// sessionの有効化
		HttpSession session = req.getSession(true);

		//パラメーターの取得
		String pd = req.getParameter("parents_id");

		if(pd != null){
			//PU = PD.filter(facility_id);
		}else{
			// 予期しない場合のエラーハンドリング
			errors.put("kome", "情報取得に失敗しました。");
			req.setAttribute("errors", errors);
			req.getRequestDispatcher("errorPage.jsp").forward(req, res);
		}


	}
}
