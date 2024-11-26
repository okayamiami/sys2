package bussystem.main;



import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;

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
public class AbsenceReportExecuteAction extends Action {

	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {


	//ローカル変数の宣言 1
	HttpSession session = req.getSession(true);				// セッションを取得
	ChildDao cDao = new ChildDao(); 							// 子供Daoを初期化
	AbsenceDao aDao = new AbsenceDao(); 						// 欠席Daoを初期化
	String facility_id = "";
	String child_name = "";										// 欠席報告で選択された子供情報
	String abs_main = ""; 										// 欠席理由
	String perfect_id = "";										// 欠席DBに登録するための欠席ID
	boolean abs_is_attend = true;								// 欠席報告が登録時点でtrue状態
	int nextNumber ;

	String user_type = (String) session.getAttribute("user_type");


	// 欠席報告日作成
	LocalDateTime nowDate = LocalDateTime.now();
	System.out.println(nowDate);
	// 表示形式を指定（年月日）
	DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("yyyy/MM/dd");
	String absence_date = dtf1.format(nowDate);


	// エラーで欠席報告入力画面に戻るときに必要になるリスト
	List<Child> list = new ArrayList<>();
	List<String> cNamelist = new ArrayList<>();

	if ("P".equals(user_type)) {
		ParentsUser pu = (ParentsUser) session.getAttribute("user"); // ログインユーザーを取得
		// リクエストパラメータ―の取得 2
		facility_id = pu.getFacility_id();
		String parents_id = pu.getParents_id();
		// DBからデータ取得 3
		list = cDao.getChildrenByParentId(parents_id, facility_id); // 保護者IDで子供情報を取得

		// 施設でしぼった子供の名前のみリスト
		for (Child c : list) {
			cNamelist.add(c.getChild_name());
		}

		session.setAttribute("user", pu);

	} else if ("T".equals(user_type) || "M".equals(user_type)) {
		ManageUser mu = (ManageUser) session.getAttribute("user"); // ログインユーザーを取得
		// リクエストパラメータ―の取得 2
		facility_id = mu.getFacility_id();
		// DBからデータ取得 3
		list = cDao.getChildListinfo(facility_id); // 子供情報一覧取得

		// 施設でしぼった子供の名前のみリスト
		for (Child c : list) {
			cNamelist.add(c.getChild_name());
		}

		session.setAttribute("user", mu);
	}





	//リクエストパラメータ―の取得 2
	child_name = req.getParameter("child_name");		// 選択した子供の名前
	abs_main = req.getParameter("abs_main");    		// 入力した欠席理由

	System.out.println("abs_mainの値は:" + abs_main);

	// 欠席理由欄が空文字で帰ってきたとき
	if ( abs_main.trim().isEmpty()){
		req.setAttribute("error", "子供の選択がされていないまたは<br>欠席理由が入力がされていません");

		req.setAttribute("list", list); 			// 子供情報すべてのリスト
		req.setAttribute("cNamelist", cNamelist); 	// 子供の名前のみのリスト
		req.getRequestDispatcher("absence_report.jsp").forward(req, res);
	}


	try{
		// 欠席IDの年度部分を作成
	    int year = nowDate.getYear() % 100;  					// 2桁の年を取得
	    String formattedYear = String.format("%02d", year); 	// ゼロ埋めして2桁にする

		if ("P".equals(user_type)) {
			ParentsUser pu = (ParentsUser) session.getAttribute("user"); // ログインユーザーを取得
			// リクエストパラメータ―の取得 2
			facility_id = pu.getFacility_id();
		} else if ("M".equals(user_type)) {
			ManageUser mu = (ManageUser) session.getAttribute("user"); // ログインユーザーを取得
			// リクエストパラメータ―の取得 2
			facility_id = mu.getFacility_id();
		}


		//DBからデータ取得 3
		//選択された子供の名前から子供IDを取得
		Child child_id = cDao.getChildIdinfo(facility_id, child_name);



		//ビジネスロジック 4

		// 欠席ID作成
		List<Absence> abs_list = aDao.getAbsenceInfo(facility_id);

		// abs_idの数字部分の最大値を取得
		OptionalInt maxAbsId = abs_list.stream()
		        .map(Absence::getAbsence_id) 		// Absenceオブジェクトからabs_idを取得
		        .map(s -> s.substring(3)) 			// 先頭の文字を除去して数字部分を抽出
		        .mapToInt(Integer::parseInt) 		// Stringをintに変換
		        .max();

		// IDの数字の最大値を取得
		if (maxAbsId.isPresent()) {
		    nextNumber = maxAbsId.getAsInt()+ 1;
		} else {
		    System.out.println("欠席テーブルにabs_idが存在しません。");
		    nextNumber = 1;
		}

		//欠席ID（完成形）
		perfect_id = "A" + formattedYear + String.format("%04d", nextNumber);


		// DBへデータ保存 5

		// 欠席インスタンスを初期化
		Absence abs = new Absence();
		// インスタンスに値をセット
		abs.setAbsence_id(perfect_id);						// 欠席ID
		abs.setAbsence_main(abs_main);						// 欠席内容
		abs.setChild_id(child_id.getChild_id());			// 子供ID
		abs.setAbsence_date(absence_date);					// 欠席報告日
		abs.setFacility_id(facility_id);					// 施設ID
		abs.setAbs_is_attend(abs_is_attend);				// 出欠席フラグ

		// 欠席情報を保存
		aDao.saveAbsenceInfo(abs);

	} catch (Exception e) {
		req.setAttribute("error", "本日の欠席登録がすでにされているか<br>子供の選択がされていません");

		req.setAttribute("list", list); 			// 子供情報すべてのリスト
		req.setAttribute("cNamelist", cNamelist); 	// 子供の名前のみのリスト
		req.getRequestDispatcher("absence_report.jsp").forward(req, res);
	}


	//レスポンス値をセット 6
	// 無し

	//JSPへフォワード 7
	req.getRequestDispatcher("absence_report_done.jsp").forward(req, res);



	}

}
