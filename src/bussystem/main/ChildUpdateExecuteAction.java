package bussystem.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Child;
import bean.ClassCd;
import bean.ManageUser;
import bean.ParentsUser;
import dao.ChildDao;
import dao.ClassCdDao;
import tool.Action;

public class ChildUpdateExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        HttpSession session = req.getSession(); // セッションを取得
        String user_type = (String) session.getAttribute("user_type");
        String facilityId = null;
        String parents_id = null;

        // ユーザー情報をセッションから取得
        Object user = session.getAttribute("user");
        ChildDao CD = new ChildDao();
        ClassCdDao CC = new ClassCdDao();

        if ("P".equals(user_type)) {
            ParentsUser parentsUser = (ParentsUser) user;
            facilityId = parentsUser.getFacility_id();
            parents_id = parentsUser.getParents_id();
        } else if ("M".equals(user_type)) {
            ManageUser manageUser = (ManageUser) user;
            facilityId = manageUser.getFacility_id();
            parents_id = req.getParameter("parents_id");
        }

        // リクエストパラメータを取得
        String class_name = req.getParameter("class_name");
        String child_name = req.getParameter("child_name");
        String child_id = req.getParameter("child_id");

        if (child_id == null || child_id.isEmpty()) {
            req.setAttribute("message", "子供IDが指定されていません。");
            req.getRequestDispatcher("childupdate.jsp").forward(req, res);
            return;
        }

        // 子供の情報を取得
        List<Child> childrenList = CD.getChildrenByParentId(parents_id, facilityId);
        List<ClassCd> classList = CC.getClassCdinfo(facilityId);

        // 指定されたchild_idに該当する子供情報を検索
        Child targetChild = null;
        for (Child child : childrenList) {
            if (child.getChild_id().equals(child_id)) {
                targetChild = child;
                break;
            }
        }

        if (targetChild != null) {
            // クラス名からクラスIDを取得
            String classId = null;
            for (ClassCd classCd : classList) {
                if (classCd.getClass_name().equals(class_name)) {
                    classId = classCd.getClass_id();
                    break;
                }
            }

            if (classId == null) {
                req.setAttribute("message", "指定されたクラス名に対応するクラスIDが見つかりません。");
                req.getRequestDispatcher("parentsedit.jsp").forward(req, res);
                return;
            }

            // 子供情報を更新
            targetChild.setChild_name(child_name);
            targetChild.setClass_id(classId);

            try {
                // 子供情報を保存
                CD.saveChildinfo(targetChild);

                req.setAttribute("parents_id", parents_id);
                req.setAttribute("class_name", class_name);
                req.setAttribute("child_name", child_name);

                // 完了画面に表示
                req.setAttribute("message", "以下の情報で子供情報の変更が完了しました。");
                req.getRequestDispatcher("childupdateexecute.jsp").forward(req, res);
            } catch (Exception e) {
                e.printStackTrace(); // エラーログの出力
                req.setAttribute("message", "エラーが発生しました。再度お試しください。");
                req.getRequestDispatcher("parentsedit.jsp").forward(req, res);
            }
        } else {
            req.setAttribute("message", "指定された子供情報が見つかりませんでした。");
            req.getRequestDispatcher("parentsedit.jsp").forward(req, res);
        }
    }
}
