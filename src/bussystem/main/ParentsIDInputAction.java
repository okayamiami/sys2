package bussystem.main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

public class ParentsIDInputAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        Map<String, String> errors = new HashMap<>();


        // パラメーターの取得
        String parents_id = req.getParameter("parents_id");// フォームから取得
        HttpSession session = req.getSession(true);  // セッションを取得
    	ManageUser MU = (ManageUser) session.getAttribute("user");//ログインユーザーは管理者
		String facility_id = MU.getFacility_id();//ファシリティIDゲット

		//Daoをインスタンス化
        ParentsUserDao PD = new ParentsUserDao();

        //Daoのメソッド
        boolean  TF = PD.getParentsUser(parents_id, facility_id);

      //保護者の名前取得
        ParentsUser PU  = PD.getParentsUserInfo(parents_id, facility_id);
        String parents_name = null;

	    if (TF == false) {
	        errors.put("errorMessage", "保護者IDが見つかりません");
	    }else if(TF == true){
	    	 parents_name = PU.getParents_name();
	    }

		//Beanをインスタンス化しクラス名を取得祈願　　引数null
		ChildDao CD = new ChildDao();
		ClassCdDao CC = new ClassCdDao();

		//子供の情報を取得
	    List<Child> CI = new ArrayList<>();
		CI = CD.getChildrenByParentId(parents_id, facility_id);
		//全クラス情報取得
		List<ClassCd> class_set = CC.getClassCdinfo(facility_id);
		 List<String> classCdList = new ArrayList<>();

		 for (ClassCd classCd : class_set) {
		     classCdList.add(classCd.getClass_id());  // getClass_cd()を取り出して新しいリストに追加
		 }

		// 子供情報を child_id の小さい順にソート
		Collections.sort(CI, (child1, child2) -> child1.getChild_id().compareTo(child2.getChild_id()));


	   if(!errors.isEmpty()){
            req.setAttribute("errors", errors);
            req.getRequestDispatcher("parentsidinput.jsp").forward(req, res);
            return;
        }



        // リクエストにデータを保存
	   	req.setAttribute("class_set", classCdList);
		req.setAttribute("parents_name", parents_name);
		req.setAttribute("user", MU);
		req.setAttribute("parents_id", parents_id);
		req.setAttribute("class_set", class_set);
		req.setAttribute("userCI", CI);

		// 結果ページにフォワード
		req.getRequestDispatcher("childinfo.jsp").forward(req, res);

    }
}