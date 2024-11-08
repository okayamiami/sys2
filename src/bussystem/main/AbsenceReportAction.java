package bussystem.main;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Child;
import bean.ParentsUser;
import dao.ChildDao;
import tool.Action;



// 欠席報告入力ページへの値受け渡し
public class AbsenceReportAction extends Action {

	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

	System.out.println("ここまで来てる？");

	//ローカル変数の宣言 1
	HttpSession session = req.getSession(true);// セッションを取得
	ChildDao cDao = new ChildDao(); // 子供Dao
	ParentsUser pu = (ParentsUser) session.getAttribute("user");// ログインユーザーを取得

	//リクエストパラメータ―の取得 2
	String facility_id=pu.getFacility_id();

	System.out.println(facility_id);

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
