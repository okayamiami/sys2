package bussystem.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ChildDao;
import dao.GetDao;
import tool.Action;

public class QrReaderExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        try {
            // QRコードで読み取ったデータを取得
            String qrData = req.getParameter("qrData");

            // QRコードデータがnullまたは空の場合、エラーメッセージを設定してリダイレクト
            if (qrData == null || qrData.isEmpty()) {
                req.setAttribute("errorMessage", "QRコードのデータが無効です。");
                req.getRequestDispatcher("error.jsp").forward(req, res);
                return;
            }

            // QRコードのデータからfacility_idとchild_idを分割
            String[] qrValues = qrData.split(",");
            if (qrValues.length != 2) {
                req.setAttribute("errorMessage", "QRコードのデータ形式が正しくありません。");
                req.getRequestDispatcher("error.jsp").forward(req, res);
                return;
            }

            String child_id = qrValues[0];
            String facility_id = qrValues[1];

            // JSPから取得したbus_idを取得
            String bus_id = req.getParameter("bus_id");

            // bus_idがnullまたは空の場合、エラーメッセージを設定してリダイレクト
            if (bus_id == null || bus_id.isEmpty()) {
                req.setAttribute("errorMessage", "バスIDが無効です。");
                req.getRequestDispatcher("error.jsp").forward(req, res);
                return;
            }

            // GetDaoインスタンスを作成し、changeGetメソッドを実行
            GetDao gDao = new GetDao();
            boolean isUpdated = gDao.changeGet(bus_id, child_id, facility_id);

            if (isUpdated) {
                // 更新成功
            	ChildDao cDao = new ChildDao();
            	//子供の名前を遷移先に表示させたい
            	String childName = cDao.getChildinfo(facility_id, child_id).getChild_name();
            	//乗車公社の状況をセットしたい
            	Boolean gettingStatus = gDao.getGetinfo(bus_id, child_id, facility_id).isGet_is_attend();

            	if(gettingStatus == true){
            		req.setAttribute("getting_status", "は乗車しました。");
            	}else if(gettingStatus == false){
            		req.setAttribute("getting_status", "は降車しました。");
            	}else{
            		req.setAttribute("errorMessage", "出席情報の取得に失敗しました。");
                    req.getRequestDispatcher("error.jsp").forward(req, res);
            	}

            	req.setAttribute("child_name", childName);
            	req.setAttribute("bus_id", bus_id);
                req.getRequestDispatcher("qrreader.jsp").forward(req, res);
            } else {
                // 更新失敗
                req.setAttribute("errorMessage", "出席情報の更新に失敗しました。");
                req.getRequestDispatcher("error.jsp").forward(req, res);
            }
        } catch (Exception e) {
            // エラー発生時の例外処理
        	e.printStackTrace(); // スタックトレースをサーバーログに出力
            System.out.println("エラークラス: " + e.getClass().getName()); // エラークラスを出力
            req.setAttribute("errorMessage", "エラークラス: " + e.getClass().getName());
            req.getRequestDispatcher("error.jsp").forward(req, res);
        }
    }
}

