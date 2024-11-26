package bussystem.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Bus;
import bean.BusChildInfo;
import bean.Child;
import bean.ClassCd;
import bean.ManageUser;
import dao.BusChildDao;
import dao.BusDao;
import dao.ChildDao;
import dao.ClassCdDao;
import tool.Action;

public class GetListInfoAction extends Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        // フォームからのパラメータを取得
    	HttpSession session = req.getSession();//セッション
		ManageUser mu = (ManageUser) session.getAttribute("user");		// ログインユーザーを取得（管理者or先生）
		String facility_id = mu.getFacility_id();

		String bus_id = req.getParameter("bus_id"); // バスID
        String child_id = req.getParameter("child_id"); // 子供ID
        String class_id = req.getParameter("class_id"); // クラスID

        // DAOの準備
        ChildDao childDao = new ChildDao();
        BusDao busDao = new BusDao();
        BusChildDao busChildDao = new BusChildDao();
        ClassCdDao classCdDao = new ClassCdDao();

        try {
            // バスと子供情報を取得
            List<BusChildInfo> busChildInfoList = busChildDao.getFilteredBusChildInfo(facility_id, bus_id, child_id, class_id);
            req.setAttribute("busChildInfoList", busChildInfoList);

            // バスリストを取得
            List<Bus> busList = busDao.getBus(facility_id);
            req.setAttribute("busList", busList);

         // 子供リストを取得
            List<Child> childList = childDao.getChildListinfo(facility_id);
            req.setAttribute("childList", childList);

         // クラスIDリストを取得
            List<ClassCd> classList = classCdDao.getClassCdinfo(facility_id);
            req.setAttribute("classList", classList);

        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "データ取得中にエラーが発生しました。");
        }

        req.getRequestDispatcher("getlistinfo.jsp").forward(req, res);
    }
}

