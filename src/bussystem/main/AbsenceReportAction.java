package bussystem.main;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Child;
import bean.ManageUser;
import bean.ParentsUser;
import dao.ChildDao;
import tool.Action;

// 欠席報告入力ページへの値受け渡し
public class AbsenceReportAction extends Action {

	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {


		// ローカル変数の宣言 1
		HttpSession session = req.getSession(true); // セッションを取得
		ChildDao cDao = new ChildDao(); // 子供Dao

		String user_type = (String) session.getAttribute("user_type");
		List<Child> list = new ArrayList<>();
		List<String> cNamelist = new ArrayList<>();



		if ("P".equals(user_type)) {
			ParentsUser pu = (ParentsUser) session.getAttribute("user"); // ログインユーザーを取得
			// リクエストパラメータ―の取得 2
			String facility_id = pu.getFacility_id();
			String parents_id = pu.getParents_id();
			// DBからデータ取得 3
			list = cDao.getAttendChildrenByParentId(parents_id, facility_id); // 保護者IDで子供情報を取得

			// 施設でしぼった子供の名前のみリスト
			for (Child c : list) {
				cNamelist.add(c.getChild_name());
			}

			session.setAttribute("user", pu);

		} else if ("T".equals(user_type) || "M".equals(user_type)) {
			ManageUser mu = (ManageUser) session.getAttribute("user"); // ログインユーザーを取得
			// リクエストパラメータ―の取得 2
			String facility_id = mu.getFacility_id();
			// DBからデータ取得 3
			list = cDao.getAttendChildListinfo(facility_id); // 子供情報一覧取得

			// 施設でしぼった子供の名前のみリスト
			for (Child c : list) {
				cNamelist.add(c.getChild_name());
			}

			session.setAttribute("user", mu);
		}

		// レスポンス値をセット 6
		req.setAttribute("list", list); // 子供情報すべてのリスト
		req.setAttribute("cNamelist", cNamelist); // 子供の名前のみのリスト
		// JSPへフォワード 7
		req.getRequestDispatcher("absence_report.jsp").forward(req, res);
	}
}