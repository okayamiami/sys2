package bussystem.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.ClassCd;
import bean.ManageUser;
import bean.ParentsUser;
import dao.ClassCdDao;
import dao.ParentsUserDao;
import tool.Action;

public class ChildAddAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        // Beanをインスタンス化
        ParentsUser PU = new ParentsUser();
        ManageUser MU = new ManageUser();

        //Daoインスタンス化
        ParentsUserDao PD = new ParentsUserDao();
        ClassCdDao CCD = new ClassCdDao();

        // エラーメッセージを格納するマップ
        Map<String, String> errors = new HashMap<>();

        // セッションの有効化
        HttpSession session = req.getSession(true);

        // 引数設定 外部
        String user_id = null;
        String facility_id = null;
        String parents_name = null;

        // ログインユーザーを取得
        String user_type = (String) session.getAttribute("user_type");

        // ユーザータイプが保護者の場合
        if ("P".equals(user_type)) {
            // 保護者情報を取得
            PU = (ParentsUser) session.getAttribute("user");
            user_id = PU.getParents_id();
            facility_id = PU.getFacility_id();
            parents_name = PU.getParents_name();



            // クラス情報を取得
            List<ClassCd> CID = CCD.getClassCdinfo(facility_id);
            if (CID != null && !CID.isEmpty()) {
            }

          //クラスの名前を取得
            List<String> uniqueClassNames = new ArrayList<>(); // class_nameを格納するリスト
            if (CID != null && !CID.isEmpty()) {
                uniqueClassNames = CID.stream()
                    .map(ClassCd::getClass_name)  // ClassCdのclass_nameを抽出
                    .distinct()                  // 重複を削除
                    .collect(Collectors.toList()); // Listに変換
            }



            // 保護者が新規登録を押した場合の処理
            //req.setAttribute("child_id", nextNumber);
            req.setAttribute("class_set", uniqueClassNames);
            req.setAttribute("parents_name", parents_name);
            req.setAttribute("parents_id", user_id);
            req.setAttribute("user", PU);
            req.getRequestDispatcher("childadd.jsp").forward(req, res);

        // ユーザータイプが管理者の場合
        } else if ("M".equals(user_type)) {
            // 管理者情報を取得
            MU = (ManageUser) session.getAttribute("user");
            user_id = req.getParameter("parents_id"); // 管理者の場合、保護者IDをリクエストから取得
            facility_id = MU.getFacility_id();

            // 保護者の名前を取得
            ParentsUser PU1 = PD.getParentsUserInfo(user_id, facility_id);
            parents_name = PU1.getParents_name();

            // クラス情報を取得
            List<ClassCd> CID = CCD.getClassCdinfo(facility_id);
            if (CID != null && !CID.isEmpty()) {
            }

            //クラスの名前を取得
            List<String> uniqueClassNames = new ArrayList<>(); // class_nameを格納するリスト
            if (CID != null && !CID.isEmpty()) {
                uniqueClassNames = CID.stream()
                    .map(ClassCd::getClass_name)  // ClassCdのclass_nameを抽出
                    .distinct()                  // 重複を削除
                    .collect(Collectors.toList()); // Listに変換
            }



            // 管理者が新規登録を押した場合の処理　uniqueClassNames
            //req.setAttribute("child_id", nextNumber);
            //req.setAttribute("class_set", uniqueClassIds);
            req.setAttribute("class_set", uniqueClassNames);
            req.setAttribute("parents_name", parents_name);
            req.setAttribute("parents_id", user_id);
            req.setAttribute("user", MU);
            req.getRequestDispatcher("childadd.jsp").forward(req, res);

        // 不正なユーザータイプの場合
        } else {
            errors.put("kome", "セッションに不正なユーザー情報が格納されています。");
        }
    }
}
