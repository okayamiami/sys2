package bussystem.main;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.OptionalInt;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.ManageUser;
import bean.ParentsUser;
import dao.ManageUserDao;
import dao.ParentsUserDao;
import tool.Action;

public class NewRegistExecuteAction extends Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        // ローカル変数の宣言 1
        HttpSession session = req.getSession();
        String user_status = "";
        String perfect_id = "";
        ParentsUserDao puDao = new ParentsUserDao();
        ManageUserDao muDao = new ManageUserDao();
        Map<String, String> errors = new HashMap<>();
        ManageUser mu = (ManageUser) session.getAttribute("user");
        String password = generateRandomPassword(16);

        if (mu == null || mu.getFacility_id() == null) {
            errors.put("facility_id_error", "施設IDが取得できませんでした。");
            req.setAttribute("errors", errors);
            req.getRequestDispatcher("error.jsp").forward(req, res);
            return;
        }

        String facility_id = mu.getFacility_id();
        LocalDate todaysDate = LocalDate.now();
        int year = todaysDate.getYear() % 100;  // 2桁の年を取得
        String formattedYear = String.format("%02d", year);  // ゼロ埋めして2桁にする

        // リクエストパラメータの取得 2
        user_status = req.getParameter("user_status");

        // ビジネスロジック 4
        if ("M".equals(user_status)) {
            List<ManageUser> list = muDao.filter(facility_id);
            List<String> sList = new ArrayList<>();

            for (ManageUser m : list) {
                sList.add(m.getUser_id());
            }

            OptionalInt maxNum = sList.stream()
                    .filter(s -> s.startsWith("M"))
                    .map(s -> Integer.parseInt(s.substring(3)))
                    .mapToInt(Integer::intValue)
                    .max();

            int nextNumber = maxNum.orElse(0) + 1;
            perfect_id = user_status + formattedYear + String.format("%05d", nextNumber);

            // DBへデータ保存 5
            muDao.newSaveManageUserInfo(perfect_id, password, facility_id);
        }else if ("T".equals(user_status)) {
            List<ManageUser> list = muDao.filter(facility_id);
            List<String> sList = new ArrayList<>();

            for (ManageUser m : list) {
                sList.add(m.getUser_id());
            }

            OptionalInt maxNum = sList.stream()
                    .filter(s -> s.startsWith("T"))
                    .map(s -> Integer.parseInt(s.substring(3)))
                    .mapToInt(Integer::intValue)
                    .max();

            int nextNumber = maxNum.orElse(0) + 1;
            perfect_id = user_status + formattedYear + String.format("%05d", nextNumber);

            // DBへデータ保存 5
            muDao.newSaveManageUserInfo(perfect_id, password, facility_id);

        } else if ("P".equals(user_status)) {
            List<ParentsUser> list = puDao.filter(facility_id);
            List<String> sList = new ArrayList<>();
            for (ParentsUser p : list) {
                sList.add(p.getParents_id());
            }

            OptionalInt maxNum = sList.stream()
                    .filter(s -> s.startsWith("P"))
                    .map(s -> Integer.parseInt(s.substring(3)))
                    .mapToInt(Integer::intValue)
                    .max();

            int nextNumber = maxNum.orElse(0) + 1;
            perfect_id = user_status + formattedYear + String.format("%05d", nextNumber);

            // DBへデータ保存 5
            puDao.newSaveParentsUserInfo(perfect_id, password, facility_id);
        }

        // レスポンス値をセット 6
        req.setAttribute("user_status", user_status);
        session.setAttribute("perfect_id", perfect_id);
        session.setAttribute("password", password);

        // JSPへフォワード 7
        req.getRequestDispatcher("newregistexecute.jsp").forward(req, res);
    }

    public static String generateRandomPassword(int length) {
        // 使用する文字セットを定義
        String upperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCase = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";

        // 全ての文字セットを結合
        String allCharacters = upperCase + lowerCase + numbers;

        // SecureRandomインスタンスを作成
        SecureRandom random = new SecureRandom();

        // パスワード生成用のStringBuilder
        StringBuilder password = new StringBuilder();

        // 残りの文字をランダムに追加
        for (int i = 0; i < length; i++) {
            password.append(allCharacters.charAt(random.nextInt(allCharacters.length())));
        }

        return password.toString();
    }

    private static String shuffleString(String input, SecureRandom random) {
        char[] characters = input.toCharArray();
        for (int i = characters.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            char temp = characters[i];
            characters[i] = characters[j];
            characters[j] = temp;
        }
        return new String(characters);
    }
}
