package bussystem.main;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.OptionalInt;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.zxing.WriterException;

import bean.Child;
import bean.ManageUser;
import bean.ParentsUser;
import dao.ChildDao;
import dao.GetDao;
import qr.QRCodeGenerator;
import tool.Action;

public class ChildAddExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

    	//Daoインスタンス化
    	ChildDao CD = new ChildDao();

    	// セッションを取得
    	HttpSession session = req.getSession();
        String user_type = (String) session.getAttribute("user_type");
        String facilityId = null;
        String parentsId = null;


        // ユーザー情報をセッションから取得
        Object user = session.getAttribute("user");
        if ("P".equals(user_type)) {
            ParentsUser parentsUser = (ParentsUser) user;
            facilityId = parentsUser.getFacility_id();
            parentsId = parentsUser.getParents_id();
        } else if ("M".equals(user_type)) {
            ManageUser manageUser = (ManageUser) user;
            facilityId = manageUser.getFacility_id();
            parentsId = req.getParameter("parents_id");
        }


        // 子供リストを取得
        List<Child> child_list = CD.getChildListinfo(facilityId);

        LocalDateTime nowDate = LocalDateTime.now();
        int year = nowDate.getYear() % 100;  					// 2桁の年を取得
	    String formattedYear = String.format("%02d", year); 	// ゼロ埋めして2桁にする

	 // child_idの最大値を計算（後ろ5桁のみを取得して数値化）
	    OptionalInt maxChildId = child_list.stream()
	        .map(Child::getChild_id)        // Childオブジェクトからchild_idを取得
	        .map(String::trim)               // 空白を削除
	        .map(id -> id.length() > 5 ? id.substring(id.length() - 5) : id) // 後ろ5桁を取得
	        .mapToInt(Integer::parseInt)     // Stringをintに変換
	        .max();


        // 次のchild_idを決定
        int nextNumber;
        if (maxChildId.isPresent()) {
            nextNumber = maxChildId.getAsInt() + 1;
        } else {
            System.out.println("子供テーブルにchild_idが存在しません。");
            nextNumber = 1;
        }

        String childId = formattedYear + String.format("%05d", nextNumber);
        System.out.println(childId);
        //String childId = req.getParameter("child_id");
        String childName = req.getParameter("child_name");
        String classId = req.getParameter("class_id");
        String isAttend = req.getParameter("is_attend");
        String class_name = req.getParameter("class_name");
        String qrImagePath = "";

        //入力チェック
        if (childId == null || childId.isEmpty() ||
            childName == null || childName.isEmpty() ||
            class_name == null || class_name.isEmpty() ||
            isAttend == null || isAttend.isEmpty()) {
            req.setAttribute("message", "すべての項目を入力してください。");
          //本当はchildinfo.jsp　なぜ　ー＞情報がなくなるため
            req.getRequestDispatcher("menu.jsp").forward(req, res);
            return;
        }
        //DAO　クラス名からクラスコードを入手
        ChildDao ChildDao = new ChildDao();
        ChildDao.getClassCdByName(class_name, facilityId);
        classId = ChildDao.getClassCdByName(class_name, facilityId);

        // 子供情報の作成
        Child child = new Child();
        child.setChild_id(childId);
        child.setChild_name(childName);
        child.setParents_id(parentsId);
        child.setClass_id(classId);
        child.setIs_attend(Boolean.parseBoolean(isAttend));
        child.setFacility_id(facilityId);
        //child.setClass_name(class_name);

        try {
            // データベースに子供情報を登録
            ChildDao childDao = new ChildDao();
            GetDao gDao = new GetDao();
            childDao.saveChildinfo(child);
            gDao.saveGetInfoForAllBuses(child);

            String child_id = child.getChild_id();
            String facility_id = child.getFacility_id();
            try {
                ServletContext context = req.getServletContext(); // サーブレットコンテキストを取得
                QRCodeGenerator qrg = new QRCodeGenerator();
                qrImagePath = qrg.generateQRCode(child_id, facility_id, context);
                System.out.println("QRコードの生成が成功しました。");

            } catch (WriterException | IOException e) {
                e.printStackTrace();
                System.out.println("QRコード生成中にエラーが発生しました。");
            }
            // 完了画面で表示する
            req.setAttribute("parents_id", parentsId);
            req.setAttribute("child_id", childId);
            req.setAttribute("class_name", class_name);
            req.setAttribute("child_name", childName);

            // 登録完了メッセージを設定して遷移
            req.setAttribute("message", "以下の情報で新規子供情報の登録が完了しました。");
            req.getRequestDispatcher("childaddexecute.jsp").forward(req, res);
        } catch (Exception e) {
            e.printStackTrace(); // エラーをログに出力
            req.setAttribute("message", "登録中にエラーが発生しました。再度お試しください。");
            System.out.println("失敗");
            //本当はchildinfo.jsp　なぜ　ー＞情報がなくなるため
            req.getRequestDispatcher("menu.jsp").forward(req, res);
        }
    }
}
