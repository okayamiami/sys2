package bussystem.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Child;
import bean.ManageUser;
import bean.ParentsUser;
import dao.ChildDao;
import dao.GetDao;
import tool.Action;

public class ChildAddExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

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

        // リクエストパラメータを取得
        String parents_id = req.getParameter("parents_id");
        String childId = req.getParameter("child_id");
        String childName = req.getParameter("child_name");
        String classId = req.getParameter("class_id");
        String isAttend = req.getParameter("is_attend");
        String class_name = req.getParameter("class_name");


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

            // 完了画面で表示する
            req.setAttribute("parents_id", parents_id);
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
