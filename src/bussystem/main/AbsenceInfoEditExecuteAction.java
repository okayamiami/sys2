package bussystem.main;



import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Absence;
import bean.Child;
import bean.ManageUser;
import bean.ParentsUser;
import dao.AbsenceDao;
import dao.ChildDao;
import tool.Action;



// 欠席報告入力後
public class AbsenceInfoEditExecuteAction extends Action {

	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {


	//ローカル変数の宣言 1
	HttpSession session = req.getSession(true);				// セッションを取得
	AbsenceDao aDao = new AbsenceDao(); 						// 欠席Daoを初期化
	ChildDao cDao = new ChildDao();
	String child_name = "";
	String absence_id = "";
	String abs_main = ""; 										// 欠席理由
	String child_id = "";										// 子供ID
	String absence_date = "";									// 欠席日
	boolean abs_is_attend = true;							// 欠席フラグ (出席状態)
	String facility_id = "";									// 施設ID

	Map<String, String> errors = new HashMap<>();				//エラーメッセージ


	//ログインユーザーを一時的に取得
	String user_type = (String) session.getAttribute("user_type");

	//リクエストパラメータ―の取得 2
	absence_id = req.getParameter("absence_id");
	abs_main = req.getParameter("absence_main");
	absence_date = req.getParameter("absence_date");


	try{

	if ("M".equals(user_type) || "T".equals(user_type)) {
		ManageUser mu = (ManageUser) session.getAttribute("user"); // ログインユーザーを取得

		facility_id = mu.getFacility_id();


		Absence abs = aDao.getAbschildinfobyAbsenceId(facility_id,absence_id);		// 更新する欠席情報を取得する
		child_id = abs.getChild_id();



		if (abs != null) {
			abs.setAbsence_id(absence_id);						// 欠席ID
			abs.setAbsence_main(abs_main);						// 欠席内容
			abs.setChild_id(child_id);							// 子供ID
			abs.setAbsence_date(absence_date);					// 欠席報告日
			abs.setFacility_id(facility_id);					// 施設ID
			abs.setAbs_is_attend(abs_is_attend);				// 出欠席フラグ
			// 欠席情報を更新
			aDao.saveAbsenceInfo(abs);

		}

		Child child = cDao.getChildinfo(facility_id, child_id);
		child_name = child.getChild_name();


		// 完了画面に表示する
		req.setAttribute("absence_id", absence_id);			// 欠席ID
		req.setAttribute("abs_main", abs_main);				// 欠席内容
		req.setAttribute("absence_date", absence_date);		// 欠席日
		req.setAttribute("abs_is_attend", abs_is_attend);	// 出欠席フラグ
		req.setAttribute("child_name", child_name);			// 子供名前

		req.getRequestDispatcher("absenceinfoedit_done.jsp").forward(req, res);



	} else if ("P".equals(user_type)) {
		ParentsUser pu = (ParentsUser) session.getAttribute("user");
		facility_id = pu.getFacility_id();


		// DBからデータ取得 3

		Absence abs = aDao.getAbschildinfobyAbsenceId(facility_id,absence_id);		// 更新する欠席情報を取得する


		child_id = abs.getChild_id();


		if (abs != null) {
			abs.setAbsence_id(absence_id);						// 欠席ID
			abs.setAbsence_main(abs_main);						// 欠席内容
			abs.setChild_id(child_id);							// 子供ID
			abs.setAbsence_date(absence_date);					// 欠席報告日
			abs.setFacility_id(facility_id);					// 施設ID
			abs.setAbs_is_attend(abs_is_attend);				// 出欠席フラグ
			// 欠席情報を更新
			aDao.saveAbsenceInfo(abs);

		}


		Child child = cDao.getChildinfo(facility_id, child_id);
		child_name = child.getChild_name();

		// 完了画面に表示する
		req.setAttribute("absence_id", absence_id);			// 欠席ID
		req.setAttribute("abs_main", abs_main);				// 欠席内容
		req.setAttribute("absence_date", absence_date);		// 欠席日
		req.setAttribute("abs_is_attend", abs_is_attend);	// 出欠席フラグ
		req.setAttribute("child_name", child_name);			// 子供名前

		req.getRequestDispatcher("absenceinfoedit_done.jsp").forward(req, res);


	} else {
	    errors.put("kome", "セッションに不正なユーザー情報が格納されています。");


	}





	} catch (Exception e) {


		req.setAttribute("error", "欠席情報更新中にエラーが発生しました");


		req.getRequestDispatcher("error.jsp").forward(req, res);
		return; // エラー時に処理を終了する
	}

	}

}
