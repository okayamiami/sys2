package bussystem.main;



import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Absence;
import bean.ManageUser;
import dao.AbsenceDao;
import tool.Action;



// 欠席報告入力後
public class AbsenceInfoEditExecuteAction extends Action {

	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {


	//ローカル変数の宣言 1
	HttpSession session = req.getSession(true);				// セッションを取得
	AbsenceDao aDao = new AbsenceDao(); 						// 欠席Daoを初期化
	String absence_id = "";
	String abs_main = ""; 										// 欠席理由
	String child_id = "";										// 子供ID
	String absence_date = "";									// 欠席日
	boolean abs_is_attend = false;							// 欠席フラグ (欠席にチェックが入っていなかったとき)
	String facility_id = "";									// 施設ID

	Map<String, String> errors = new HashMap<>();				//エラーメッセージ


//	try{

		ManageUser mu = (ManageUser) session.getAttribute("user"); // ログインユーザーを取得

		facility_id = mu.getFacility_id();


		//リクエストパラメータ―の取得 2
		absence_id = req.getParameter("absence_id");
		abs_main = req.getParameter("absence_main");
		absence_date = req.getParameter("absence_date");
		String isAttendStr = req.getParameter("abs_is_attend");
		// 欠席フラグにチェックが入っていた場合
		if (isAttendStr != null) {
			// 欠席フラグを立てる
			abs_is_attend = true;
		}

		Absence abs = aDao.getAbschildinfobyAbsenceId(facility_id,absence_id);		// 更新する欠席情報を取得する
		child_id = abs.getChild_id();


		System.out.println(abs_is_attend);

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

//	} catch (Exception e) {
//
//
//		req.setAttribute("error", "欠席情報更新中にエラーが発生しました");
//
//		/**後でここ周辺修正する*/
//		req.getRequestDispatcher("error.jsp").forward(req, res);
//		return; // エラー時に処理を終了する
//	}


	//レスポンス値をセット 6
	// 無し

	//JSPへフォワード 7
	req.getRequestDispatcher("absenceinfoedit_done.jsp").forward(req, res);



	}

}
