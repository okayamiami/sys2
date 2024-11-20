package bussystem.main;

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

public class ChildListAction extends Action {


	// 在籍無しの場合は非表示設定(11/15時点)


	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		//ローカル変数の宣言 1
		HttpSession session = req.getSession();//セッション
		ManageUser mu = (ManageUser) session.getAttribute("user");		// ログインユーザーを取得（管理者or先生）
		String facility_id = mu.getFacility_id();

		String child_id ="";												// 選択された子供ID
		String child_name ="";												// 選択された名前
		String class_cd = "";												// 選択されたクラス
		String absIsAttendStr="";											// 選択された出欠席フラグ

		boolean absIsAttend = false;										// 出欠席フラグ
		boolean IsAttend = true;											// 在籍フラグ   在籍中の子のみ表示

		ChildAbsDao caDao = new ChildAbsDao();								// 子供一覧表示用Dao
		ChildDao cDao = new ChildDao();										// 子供名前選択用
		ClassCdDao ccDao = new ClassCdDao();								// クラス選択用

		List<ChildAbs> childs = null;										// 子供一覧用リスト

		Map<String, String> errors = new HashMap<>();// エラーメッセージ


		try{
			//リクエストパラメータ―の取得 2（絞り込み部分）
			child_id = req.getParameter("f1");			// 子供ID
			child_name = req.getParameter("f2");		// 子供名前
			class_cd = req.getParameter("f3");			// クラス名
			absIsAttendStr = req.getParameter("f4");	// 出欠席フラグ 選択されていたら欠席の子のみ表示


			//欠席フラグが選択されたとき(欠席の子表示)
			if (absIsAttendStr != null) {
				// 欠席フラグを立てる
				absIsAttend = true;
			}



			//DBからデータ取得 3

			// 子供
			List<String> childIdlist = new ArrayList<>();					// 子供IDのみリスト
			List<String> childNamelist = new ArrayList<>();				// 子供の名前のみリスト


			List<Child> childlist = cDao.getChildListinfo(facility_id);		// 子供情報一覧リスト


			for (Child c : childlist) {		// 子供IDリスト
				childIdlist.add(c.getChild_id());
			}
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
			ClassCd classID = ccDao.getClassIdinfobyName(facility_id, class_cd);


			String class_id = classID.getClass_id();


			// 絞り込み条件
			if (child_id != null && !child_id.equals("0") && child_name.equals("0")&& class_cd.equals("0")) {
			    // 子供IDのみ指定
			    childs = caDao.filterbyChildId(child_id, facility_id, IsAttend, absIsAttend);
			} else if (child_name != null && !child_name.equals("0") && child_id.equals("0") && class_cd.equals("0")) {
			    // 子供の名前のみ指定
			    childs = caDao.filterbyChildName(child_name, facility_id, IsAttend, absIsAttend);
			} else if (class_cd != null && !class_cd.equals("0") && child_id.equals("0") && child_name.equals("0")) {
			    // クラスのみ選択
			    childs = caDao.filterbyClassCd(class_id, facility_id, IsAttend, absIsAttend);
			} else if (child_id == null && child_name == null && class_cd == null || child_id.equals("0") && child_name.equals("0")&&class_cd.equals("0")) {
				// 1つも選択されていないとき（施設の全員表示）
				childs = caDao.getChildListAbsinfo(facility_id, IsAttend, absIsAttend);
			}else {
			    // 選択条件が複数あったとき
			    errors.put("f1", "欠席以外の項目が複数選択されています");
			    req.setAttribute("errors", errors);
			    // 施設の（在籍中の）子供全員表示
			    childs = caDao.getChildListAbsinfo(facility_id, IsAttend, absIsAttend);
			}




			//ビジネスロジック 4
			//なし

			//DBへデータ保存 5
			//なし



			//レスポンス値をセット 6

			// 子供IDをセット
			req.setAttribute("f1", child_id);
			// 子供の名前をセット
			req.setAttribute("f2", child_name);
			// クラスをセット
					req.setAttribute("f3", class_cd);
			// 欠席フラグが送信されていた場合
			if (absIsAttendStr != null) {
				// リクエストに欠席フラグをセット
				req.setAttribute("f4", absIsAttendStr);
			}


			// リクエストにをセット
			req.setAttribute("childs", childs);
			req.setAttribute("child_id_set", childIdlist);
			req.setAttribute("child_name_set", childNamelist);
			req.setAttribute("class_name_set", classNamelist);
			req.setAttribute("class_set", classlist);

		} catch (Exception e) {
			req.setAttribute("error", "子供一覧情報の取得中にエラーが発生しました。");
			req.getRequestDispatcher("childlist.jsp").forward(req, res);
		}


		//JSPへフォワード 7
		req.getRequestDispatcher("childlist.jsp").forward(req, res);
	}

}


