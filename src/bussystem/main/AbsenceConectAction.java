package bussystem.main;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Absence;
import bean.Child;
import bean.ClassCd;
import bean.ManageUser;
import dao.AbsenceDao;
import dao.ChildDao;
import dao.ClassCdDao;
import tool.Action;

public class AbsenceConectAction extends Action {

	// 欠席連絡確認画面
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//ローカル変数の宣言 1
		HttpSession session = req.getSession();//セッション
		ManageUser mu = (ManageUser) session.getAttribute("user");		// ログインユーザーを取得（管理者or先生）
		String facility_id = mu.getFacility_id();

		String absence_date ="";											// 選択された欠席日
		String class_id ="";												// 選択されたクラスID
		String child_name = "";												// 選択された子供の名前


		AbsenceDao aDao = new AbsenceDao();									// 欠席情報取得用
		ChildDao cDao = new ChildDao();										// 子供名前選択用
		ClassCdDao ccDao = new ClassCdDao();								// クラス選択用

		List<Absence> abs = null;											// 欠席情報一覧用リスト


        LocalDate today = LocalDate.now();									// 本日の日付
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");	// 表示形式を指定

		Map<String, String> errors = new HashMap<>();						// エラーメッセージ


		try{

			//リクエストパラメータ―の取得 2（絞り込み部分）
			absence_date = req.getParameter("f1");			// 欠席日
			class_id = req.getParameter("f2");				// クラス
			child_name = req.getParameter("f3");			// 名前



			//DBからデータ取得 3

			// 子供
			List<String> childNamelist = new ArrayList<>();				// 子供の名前のみリスト


			List<Child> childlist = cDao.getChildListinfo(facility_id);		// 子供情報一覧リスト


			for (Child c : childlist) {		// 子供名リスト
				childNamelist.add(c.getChild_name());
			}



			// クラス
			List<String> classNamelist = new ArrayList<>();					// クラス名のみリスト

			List<ClassCd> classlist = ccDao.getClassCdinfo(facility_id);		// クラス情報一覧リスト


			for (ClassCd c : classlist) {	// クラス名リスト
				classNamelist.add(c.getClass_name());
			}


			// 選択されたクラス名をクラスIDに変換
			ClassCd classID = ccDao.getClassIdinfobyName(facility_id, class_id);

			String select_class_id = classID.getClass_id();


			// 絞り込み条件
			if (absence_date != null && !absence_date.equals("0") && class_id.equals("0")&& child_name.equals("0")) {
			    // 欠席日のみ指定
			    abs = aDao.filterbyAbsence_date(absence_date, facility_id);
			} else if (class_id != null && !class_id.equals("0") && absence_date.equals("0") && child_name.equals("0")) {
			    // クラスのみ指定
			    abs = aDao.filterbyClassId(select_class_id, facility_id);
			} else if (child_name != null && !child_name.equals("0") && absence_date.equals("0") && class_id.equals("0")) {
			    // 名前のみ選択
				abs = aDao.filterbyChildName(child_name, facility_id);
			} else if (absence_date == null && class_id == null && child_name == null || absence_date.equals("0") && class_id.equals("0")&&child_name.equals("0")) {
				// 1つも選択されていないとき（欠席の全表示）
				System.out.println("最初ここ通ってる？？");
				abs = aDao.getAbsenceInfo(facility_id);
			}else {
			    // 選択条件が複数あったとき
			    errors.put("f1", "項目が複数選択されています");
			    req.setAttribute("errors", errors);
			    // 施設の欠席全表示
				abs = aDao.getAbsenceInfo(facility_id);
			}




			//ビジネスロジック 4
	        // 本日から過去10日分日付リストを作成
	        List<String> dateList = new ArrayList<>();
	        for (int i = 0; i < 10; i++) { 				// 過去10日分（本日を含む）
	            LocalDate date = today.minusDays(i); 	// i日を減算
	            dateList.add(date.format(dtf));      	// 指定した形式でリストに追加
	        }


			//DBへデータ保存 5
			//なし



			//レスポンス値をセット 6

			req.setAttribute("f1", absence_date);
			req.setAttribute("f2", class_id);
			req.setAttribute("f3", child_name);


			// リクエストにをセット
			req.setAttribute("absence", abs);
			req.setAttribute("datelist", dateList);					// 欠席日（過去10日）のリスト
			req.setAttribute("class_name_set", classNamelist);		// クラスの名前リスト
			req.setAttribute("child_name_set", childNamelist);		// 子供の名前リスト
			req.setAttribute("class_set", classlist);				// jspでクラス名表示のためのクラス情報リスト
			req.setAttribute("child_set", childlist);				// jspで名前表示のための子供情報リスト



		} catch (Exception e) {
			req.setAttribute("error", "欠席情報の取得中にエラーが発生しました。");
			req.getRequestDispatcher("absence_conect.jsp").forward(req, res);
		}


		//JSPへフォワード 7
		req.getRequestDispatcher("absence_conect.jsp").forward(req, res);
	}
}