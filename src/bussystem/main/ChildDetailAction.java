package bussystem.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Child;
import bean.ChildDetail;
import bean.ClassCd;
import bean.ManageUser;
import dao.ChildDao;
import dao.ChildDetailDao;
import dao.ClassCdDao;
import tool.Action;

public class ChildDetailAction extends Action {


	// 子供詳細画面情報

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		//ローカル変数の宣言 1
		HttpSession session = req.getSession();//セッション
		ManageUser mu = (ManageUser) session.getAttribute("user");		// ログインユーザーを取得（管理者or先生）
		String facility_id = mu.getFacility_id();

		String child_id = "";												// 子供ID
		String child_name = "";												// 名前（子供）
		String class_id = "";												// クラスID
		String parents_id = "";												// 保護者ID
		String parents_name = "";											// 名前（保護者）
		String parents_address = "";										// 住所
		String parents_tel = "";											// 電話番号
		String parents_mail1 = "";											// メールアドレス１
		String parents_mail2 = "";											// メールアドレス２
		String parents_mail3 = "";											// メールアドレス３


		ChildDao cDao = new ChildDao();										// 子供
		ChildDetailDao cdDao = new ChildDetailDao();						// 子供詳細情報
		ClassCdDao ccDao = new ClassCdDao();								// クラス選択用



		//リクエストパラメータ―の取得 2（絞り込み部分）
		String select_child_id = req.getParameter("child_id");				// 選択された子供ID



		//DBからデータ取得 3

		// 子供情報・保護者ID取得
		Child child = cDao.getChildinfo(facility_id, select_child_id);
		if (child == null) {
		    req.setAttribute("error", "子供情報の取得に失敗しました。");
		    req.getRequestDispatcher("childdetail.jsp").forward(req, res);
		    return;
		}
		String select_parents_id = child.getParents_id();					// 選択された子供の保護者ID


		// 子供詳細情報取得
		ChildDetail cdetail = cdDao.getChildDetailinfo(facility_id, select_parents_id, select_child_id);
		if (cdetail == null) {
		    req.setAttribute("error", "子供詳細情報の取得に失敗しました。");
		    req.getRequestDispatcher("childdetail.jsp").forward(req, res);
		    return;
		}

		child_id = cdetail.getChild_id();											// 子供ID
		child_name = cdetail.getChild_name();										// 名前（子供）
		class_id = cdetail.getClass_id();											// クラスID
		parents_id = cdetail.getParents_id();										// 保護者ID
		parents_name = cdetail.getParents_name();									// 名前（保護者）
		parents_address = cdetail.getParents_address();								// 住所
		parents_tel = cdetail.getParents_tel();										// 電話番号
		parents_mail1 = cdetail.getParents_mail1();									// メールアドレス１
		parents_mail2 = cdetail.getParents_mail2();									// メールアドレス２
		parents_mail3 = cdetail.getParents_mail3();									// メールアドレス３

		// クラス
		List<ClassCd> classlist = ccDao.getClassCdinfo(facility_id);				// クラス情報一覧リスト
		if (classlist == null || classlist.isEmpty()) {
		    req.setAttribute("error", "クラス情報の取得に失敗しました。");
		    req.getRequestDispatcher("childdetail.jsp").forward(req, res);
		    return;
		}


		//ビジネスロジック 4
		//なし

		//DBへデータ保存 5
		//なし



		//レスポンス値をセット 6



		// リクエストにをセット
		req.setAttribute("child_detail", cdetail);  // 使わないかも！！

		req.setAttribute("classlist", classlist);			// クラス名のリスト

		req.setAttribute("child_id", child_id);  					// 子供ID
		req.setAttribute("child_name", child_name);  				// 名前（子供）
		req.setAttribute("class_id", class_id);  					// クラスID
		req.setAttribute("parents_id", parents_id);  				// 保護者ID
		req.setAttribute("parents_name", parents_name);  			// 名前（保護者）
		req.setAttribute("parents_address", parents_address);  		// 住所
		req.setAttribute("parents_tel", parents_tel);  				// 電話番号
		req.setAttribute("parents_mail1", parents_mail1);  			// メールアドレス１
		req.setAttribute("parents_mail2", parents_mail2);  			// メールアドレス２
		req.setAttribute("parents_mail3", parents_mail3);  			// メールアドレス３



		//JSPへフォワード 7
		req.getRequestDispatcher("childdetail.jsp").forward(req, res);
	}

}





