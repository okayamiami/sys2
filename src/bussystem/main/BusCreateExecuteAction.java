package bussystem.main;



import java.util.List;
import java.util.OptionalInt;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Bus;
import bean.ManageUser;
import dao.BusDao;
import dao.GetDao;
import tool.Action;



// 欠席報告入力後
public class BusCreateExecuteAction extends Action {

	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {


	//ローカル変数の宣言 1
	HttpSession session = req.getSession(true);				// セッションを取得
	BusDao bDao = new BusDao(); 								// バスDaoを初期化
	GetDao gDao = new GetDao();									// 乗降Daoを初期化

	String facility_id = "";
	String bus_name = ""; 										// バス名
	String perfect_id = "";										// バスDBに登録するためのバスID

	int nextNumber ;


	//リクエストパラメータ―の取得 2
	bus_name = req.getParameter("bus_name");		// 入力されたバス名

	// 欠席理由欄が空文字で帰ってきたとき
	if ( bus_name.trim().isEmpty()){
		req.setAttribute("error", "バス名が入力がされていません");

		req.getRequestDispatcher("buscreate.jsp").forward(req, res);
	}


//	try{

		ManageUser mu = (ManageUser) session.getAttribute("user"); // ログインユーザーを取得
		// リクエストパラメータ―の取得 2
		facility_id = mu.getFacility_id();



		//DBからデータ取得 3
		// 無し


		//ビジネスロジック 4

		// バスID作成
		List<Bus> bus_list = bDao.getBus(facility_id);

		// abs_idの数字部分の最大値を取得
		OptionalInt maxBusId = bus_list.stream()
		        .map(Bus::getBus_id) 				// 	Busオブジェクトからbus_idを取得
		        .map(s -> s.substring(1).trim()) 	// 先頭の文字を除去して数字部分を抽出
		        .mapToInt(Integer::parseInt) 		// Stringをintに変換
		        .max();

		// IDの数字の最大値を取得
		if (maxBusId.isPresent()) {
		    nextNumber = maxBusId.getAsInt()+ 1;
		} else {
		    System.out.println("バステーブルにbus_idが存在しません。");
		    nextNumber = 1;
		}

		//欠席ID（完成形）
		perfect_id = "B" + String.format("%02d", nextNumber);


		// DBへデータ保存 5

		// バスインスタンスを初期化
		Bus bus = new Bus();
		// インスタンスに値をセット
		bus.setBus_id(perfect_id);			// バスID
		bus.setBus_name(bus_name);			// バス名
		bus.setFacility_id(facility_id);	// 施設ID


		// バス情報を保存
		bDao.saveBus(bus);

		// 新規作成分の乗降をDBに登録する
		gDao.saveGetInfoForNewBus(bus);

//	} catch (Exception e) {
//		req.setAttribute("error", "バス情報登録中にエラーが発生しました");
//		req.getRequestDispatcher("buscreate.jsp").forward(req, res);
//		return;
//	}


	//レスポンス値をセット 6
	// 無し

	//JSPへフォワード 7
	req.getRequestDispatcher("absence_report_done.jsp").forward(req, res);



	}

}
