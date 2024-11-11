package bussystem.main;





import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
public class AbsenceReportExcuteAction extends Action {

	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {


	//ローカル変数の宣言 1
	HttpSession session = req.getSession(true);				// セッションを取得
	ChildDao cDao = new ChildDao(); 							// 子供Daoを初期化
	AbsenceDao aDao = new AbsenceDao(); 						// 欠席Daoを初期化
	String child_name = "";										// 欠席報告で選択された子供情報
	String abs_main = ""; 										// 欠席理由
	String perfect_id = "";										// 欠席DBに登録するための欠席ID
	String user_status = "";									// ユーザーの種類判別用
	boolean abs_is_attend = true;								// 欠席報告が登録時点でtrue状態
	Integer next_num ;
	ParentsUser pu = (ParentsUser) session.getAttribute("user");//（保護者）ログインユーザーを取得
	ManageUser mu = (ManageUser) session.getAttribute("user");	//（管理者）ログインユーザーを取得
	Map<String, String> errors = new HashMap<>();				// エラーメッセージ


	// 現在日時を取得
	LocalDateTime nowDate = LocalDateTime.now();
	System.out.println(nowDate); // 2020-12-20T13:32:48.293
	// 表示形式を指定（年月日）
	DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("yyyy/MM/dd");
	String absence_date = dtf1.format(nowDate);




	// ログインしているユーザー種別を判別
    if (pu != null ) {
        user_status = "P";		// 保護者
    } else if (mu != null){
    	user_status = "M";		// 管理者
    }



	String facility_id = pu.getFacility_id();					// 保護者から施設ID取得


	//リクエストパラメータ―の取得 2
	child_name = req.getParameter("child_name");		// 選択した子供の名前
	abs_main = req.getParameter("abs_main");    		// 入力した欠席理由

	/*名前　野原しんのすけ
	 * 理由　風邪のため*/


	//DBからデータ取得 3
	//選択された子供の名前から子供IDを取得
	Child child_id = cDao.getChildIdinfo(facility_id, child_name);



	//ビジネスロジック 4

	// 欠席ID作成
	List<Absence> abs_list = aDao.getAbsenceInfo(facility_id);

	// abs_idの数字部分の最大値を取得
	OptionalInt maxAbsId = abs_list.stream()
	        .map(Absence::getAbsence_id) // Absenceオブジェクトからabs_idを取得
	        .map(s -> s.substring(1)) // 先頭の文字を除去して数字部分を抽出
	        .mapToInt(Integer::parseInt) // Stringをintに変換
	        .max();

	if (maxAbsId.isPresent()) {
	    System.out.println("最大のabs_idの数字部分は: " + maxAbsId.getAsInt());
	    
	} else {
	    System.out.println("欠席テーブルにabs_idが存在しません。");
	}
	
	
	int nextNumber = maxAbsId.getAsInt()+ 1;

    // DBへデータ保存 5
    muDao.newSaveManageUserInfo(perfect_id, perfect_id, facility_id);




	// 施設でしぼった子供の名前のみリスト
	for(Child c :list){
		cNamelist.add(c.getChild_name());
	}


	//DBへデータ保存 5
	//なし

	//レスポンス値をセット 6
	req.setAttribute("list",list ); //子供情報すべてのリスト
	req.setAttribute("cNamelist",cNamelist ); // 子供の名前のみのリスト
	session.setAttribute("user", pu);

	//JSPへフォワード 7
	req.getRequestDispatcher("absence_report.jsp").forward(req, res);



	}

}
