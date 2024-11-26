package bussystem.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.OptionalInt;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Child;
import bean.ClassCd;
import bean.ManageUser;
import bean.ParentsUser;
import dao.ChildDao;
import dao.ClassCdDao;
import dao.ParentsUserDao;
import tool.Action;

public class ChildAddAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        // Beanをインスタンス化
        ParentsUser PU = new ParentsUser();
        ManageUser MU = new ManageUser();

        // DAOのインスタンス化
        ChildDao CD = new ChildDao();
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

        // 空のリストを作成
        List<String> uniqueClassIds = new ArrayList<>();

        // ログインユーザーを取得
        String user_type = (String) session.getAttribute("user_type");

        // ユーザータイプが保護者の場合
        if ("P".equals(user_type)) {
            // 保護者情報を取得
            PU = (ParentsUser) session.getAttribute("user");
            user_id = PU.getParents_id();
            facility_id = PU.getFacility_id();
            parents_name = PU.getParents_name();

            // 子供リストを取得
            List<Child> child_list = CD.getChildListinfo(facility_id);
            System.out.println(child_list);

            // child_idの最大値を計算
            OptionalInt maxChildId = child_list.stream()
                .map(Child::getChild_id)       // Childオブジェクトからchild_idを取得
                .map(childId -> childId.trim()) // 空白を削除
                .mapToInt(Integer::parseInt)   // Stringをintに変換
                .max();

            // 次のchild_idを決定
            int nextNumber;
            if (maxChildId.isPresent()) {
                nextNumber = maxChildId.getAsInt() + 1;
            } else {
                System.out.println("子供テーブルにchild_idが存在しません。");
                nextNumber = 1;
            }

            // クラス情報を取得
            List<ClassCd> CID = CCD.getClassCdinfo(facility_id);
            if (CID != null && !CID.isEmpty()) {
                uniqueClassIds = CID.stream()
                    .map(ClassCd::getClass_id)  // ClassCdのclass_idを抽出
                    .distinct()                 // 重複を削除
                    .collect(Collectors.toList()); // Listに変換
            }

            System.out.println(nextNumber);
            System.out.println(PU + "保護者ユーザーログイン");

            // 保護者が新規登録を押した場合の処理
            req.setAttribute("child_id", nextNumber);
            req.setAttribute("class_set", uniqueClassIds);
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
                uniqueClassIds = CID.stream()
                    .map(ClassCd::getClass_id)  // ClassCdのclass_idを抽出
                    .distinct()                 // 重複を削除
                    .collect(Collectors.toList()); // Listに変換
            }

            // 子供リストを取得
            List<Child> child_list = CD.getChildListinfo(facility_id);
            System.out.println(child_list);

            // child_idの最大値を計算
            OptionalInt maxChildId = child_list.stream()
                .map(Child::getChild_id)       // Childオブジェクトからchild_idを取得
                .map(childId -> childId.trim()) // 空白を削除
                .mapToInt(Integer::parseInt)   // Stringをintに変換
                .max();

            // 次のchild_idを決定
            int nextNumber;
            if (maxChildId.isPresent()) {
                nextNumber = maxChildId.getAsInt() + 1;
            } else {
                System.out.println("子供テーブルにchild_idが存在しません。");
                nextNumber = 1;
            }

            System.out.println(nextNumber);
            System.out.println(MU + "管理ユーザーログイン");

            // 管理者が新規登録を押した場合の処理
            req.setAttribute("child_id", nextNumber);
            req.setAttribute("class_set", uniqueClassIds);
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
