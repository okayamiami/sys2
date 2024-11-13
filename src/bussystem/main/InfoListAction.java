package bussystem.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Child;
import bean.ChildAbs;
import bean.ClassCd;
import bean.ManageUser;
import dao.ChildAbsDao;
import dao.ChildDao;
import dao.ClassCdDao;
import tool.Action;

public class InfoListAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		//ローカル変数の宣言 1
		HttpSession session = req.getSession();//セッション
		ManageUser mu = (ManageUser) session.getAttribute("user");		// ログインユーザーを取得（管理者or先生）
		String facility_id = mu.getFacility_id();

		String child_id ="";						// 選択された子供ID
		String child_name ="";						// 選択された名前
		String class_cd = "";						// 選択されたクラス
		String absIsAttendStr="";					// 選択された出欠席フラグ
		boolean absIsAttend = false;				// 出欠席フラグ false:全員表示
		List<ChildAbs> childabs = null;			// 子供一覧用リスト
		LocalDate todaysDate = LocalDate.now();	// LcalDateインスタンスを取得
		int year = todaysDate.getYear();			// 本日の日付を取得
		List<ChildAbs> childs = null;				// 子供一覧用リスト

		ChildAbsDao caDao = new ChildAbsDao();		// 子供一覧表示用Dao
		ChildDao cDao = new ChildDao();				// 子供名前選択用
		ClassCdDao ccDao = new ClassCdDao();		// クラス選択用


		Map<String, String> errors = new HashMap<>();// エラーメッセージ



		//リクエストパラメータ―の取得 2（絞り込み部分）
		child_id = req.getParameter("f1");			// 子供ID
		child_name = req.getParameter("f2");		// 子供名前
		class_cd = req.getParameter("f3");			// クラス
		absIsAttendStr = req.getParameter("f4");	// 出欠席フラグ


		//DBからデータ取得 3

		// 子供
		List<Child> childlist = cDao.getChildListinfo(facility_id);		// 子供情報一覧リスト
		List<String> childIdlist = new ArrayList<>();				// 子供IDのみリスト
		List<String> childNamelist = new ArrayList<>();				// 子供の名前のみリスト

		for (Child c : childlist) {		// 子供IDリスト
			childNamelist.add(c.getChild_id());
		}
		for (Child c : childlist) {		// 子供名リスト
			childNamelist.add(c.getChild_name());
		}

		// クラス
		List<ClassCd> classlist = ccDao.getClassCdinfo(facility_id);		// クラス情報一覧リスト
		List<String> classNamelist = new ArrayList<>();					// クラス名のみリスト


		for (ClassCd c : classlist) {	// クラス名リスト
			classNamelist.add(c.getClass_name());
		}



		/**
		 *  ここから作成する 複数選択を拒否するように各選択肢を作成する
		 */
		if (!child_id.equals("0") && !child_name.equals("0") && !class_cd.equals("0") ) {
			// 子供IDのみ指定
			childs = sDao.filter(teacher.getSchool(), entYear, classNum, isAttend);
		} else if (entYear != 0 && classNum.equals("0")) {
			// 子供の名前のみ指定
			students = sDao.filter(teacher.getSchool(), entYear, isAttend);
		} else if (entYear == 0 && classNum == null || entYear == 0 && classNum.equals("0")) {
			// クラスのみ選択
			students = sDao.filter(teacher.getSchool(), isAttend);
		} else {
			errors.put("f1", "クラスを指定する場合は入学年度も指定してください");
			req.setAttribute("errors", errors);
			// 全学生情報を取得
			students = sDao.filter(teacher.getSchool(), isAttend);
		}

		//ビジネスロジック 4
		if (entYearStr != null) {
			// 数値に変換
			entYear = Integer.parseInt(entYearStr);
		}
		// リストを初期化
		List<Integer> entYearSet = new ArrayList<>();
		// 10年前から1年後まで年をリストに追加
		for (int i = year - 10; i < year + 1; i++) {
			entYearSet.add(i);
		}

		//DBへデータ保存 5
		//なし



		//レスポンス値をセット 6
		// リクエストに入学年度をセット
		req.setAttribute("f1", entYear);
		// リクエストにクラス番号をセット
		req.setAttribute("f2", classNum);
		// 在学フラグが送信されていた場合
		if (isAttendStr != null) {
			// リクエストに在学フラグをセット
			req.setAttribute("f3", isAttendStr);
		}
		// リクエストに学生リストをセット
		req.setAttribute("students", students);
		// リクエストにデータをセット
		req.setAttribute("class_num_set", list);
		req.setAttribute("ent_year_set", entYearSet);
		//JSPへフォワード 7
		req.getRequestDispatcher("student_list.jsp").forward(req, res);
	}

}





