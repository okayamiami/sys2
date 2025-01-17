package bussystem.main;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Information;
import bean.ManageUser;
import bean.ParentsUser;
import dao.InformationDao;
import tool.Action;

public class InfoListAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        // ローカル変数の宣言
        HttpSession session = req.getSession(); // セッション
        InformationDao iDao = new InformationDao();
        String type = (String) session.getAttribute("user_type");

        List<Information> ilist = null;

        if (type.equals("M") || type.equals("T")) {
            // 管理者 or 先生の場合
            ManageUser mu = (ManageUser) session.getAttribute("user"); // ログインユーザーを取得
            ilist = iDao.getInfoList(mu.getFacility_id());
        } else {
            // 保護者の場合
            ParentsUser pu = (ParentsUser) session.getAttribute("user"); // ログインユーザーを取得
            ilist = iDao.getInfoList(pu.getFacility_id());
        }

        // お知らせリストを日付順（新しい順）にソート
        Collections.sort(ilist, new Comparator<Information>() {
            @Override
            public int compare(Information o1, Information o2) {
                // info_dateを比較して新しい日付が先に来るようにソート
                return o2.getInfo_date().compareTo(o1.getInfo_date());
            }
        });

        // リクエスト属性にセット
        req.setAttribute("ilist_set", ilist);

        // JSPへフォワード
        req.getRequestDispatcher("infolist.jsp").forward(req, res);
    }
}


