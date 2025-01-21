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

// バス作成アクション
public class BusCreateExecuteAction extends Action {

    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        // ローカル変数の宣言
        HttpSession session = req.getSession(true); // セッションを取得
        BusDao bDao = new BusDao();                 // バスDaoを初期化
        GetDao gDao = new GetDao();                 // 乗降Daoを初期化

        String facility_id = "";
        String bus_name = "";                       // バス名
        String perfect_id = "";                     // バスID
        int nextNumber;

        // リクエストパラメータの取得
        bus_name = req.getParameter("bus_name");

        // 空文字チェック
        if (bus_name.trim().isEmpty()) {
            req.setAttribute("error", "バス名が入力されていません");
            req.getRequestDispatcher("buscreate.jsp").forward(req, res);
            return;
        }

        try {
            // ログインユーザーから施設IDを取得
            ManageUser mu = (ManageUser) session.getAttribute("user");
            facility_id = mu.getFacility_id();

            // DBから既存のバス情報を取得
            List<Bus> bus_list = bDao.getAllBus(facility_id);

            // bus_id の最大値を取得
            OptionalInt maxBusId = bus_list.stream()
                    .map(Bus::getBus_id)                // Busオブジェクトからbus_idを取得
                    .map(s -> s.substring(1).trim())   // 先頭の文字を除去して数字部分を抽出
                    .mapToInt(Integer::parseInt)       // Stringをintに変換
                    .max();

            // 次のID番号を設定
            nextNumber = maxBusId.isPresent() ? maxBusId.getAsInt() + 1 : 1;

            // 完成形バスID
            perfect_id = "B" + String.format("%04d", nextNumber);

            // バスインスタンスを初期化
            Bus bus = new Bus();
            bus.setBus_id(perfect_id);         // バスID
            bus.setBus_name(bus_name);         // バス名
            bus.setFacility_id(facility_id);   // 施設ID

            System.out.println(perfect_id);
            // バス情報を保存
            boolean isSaved = bDao.saveBus(bus);

            if (!isSaved) {
                throw new Exception("バス情報の保存に失敗しました");
            }

            // 乗降情報をDBに登録
            gDao.saveGetInfoForNewBus(bus);

        } catch (Exception e) {
            req.setAttribute("error", "バス情報登録中にエラーが発生しました: " + e.getMessage());
            req.getRequestDispatcher("buscreate.jsp").forward(req, res);
            return;
        }

        // JSPへフォワード
        req.setAttribute("bus_id", perfect_id);
        req.setAttribute("bus_name", bus_name);
        req.getRequestDispatcher("buscreate_done.jsp").forward(req, res);
    }
}

