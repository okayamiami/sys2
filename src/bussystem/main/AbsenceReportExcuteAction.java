package bussystem.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Child;
import bean.ParentsUser;
import dao.AbsenceDao;
import dao.ChildDao;
import tool.Action;



// 欠席報告入力後
public class AbsenceReportExcuteAction extends Action {

	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {


	//ローカル変数の宣言 1
	HttpSession session = req.getSession(true);// セッションを取得
	ChildDao cDao = new ChildDao(); // 子供Daoを初期化
	AbsenceDao aDao = new AbsenceDao(); // 欠席Daoを初期化
	String child_name = "";// 欠席報告で選択された子供情報のはこ
	String abs_main = ""; // 欠席理由のはこ
	ParentsUser pu = (ParentsUser) session.getAttribute("user");// ログインユーザーを取得

	Map<String, String> errors = new HashMap<>();// エラーメッセージ



	//リクエストパラメータ―の取得 2
	child_name = req.getParameter("child_name");// 選択した子供の名前
	abs_main = req.getParameter("abs_main");    // 入力した欠席理由






	/*ここから作成する！！！！！！！！！！*/







	//DBからデータ取得 3
	//なし
	//ビジネスロジック 4
	List<Child> list =cDao.getChildListinfo(facility_id); //子供情報一覧取得
	List<String> cNamelist = new ArrayList<>();


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
