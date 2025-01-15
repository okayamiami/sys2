<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>欠席機能選択</title>
</head>
<body>
<c:import url="/common/header.jsp" />

<div class="main">
<c:import url="/common/navi.jsp" />
<div class="con">

	<h2>欠席機能選択</h2>

<div class="menu-container">
    <div class="menu-select">
        <a href="AbsenceConect.action" class="button-qr">
            <img src="../../common/image/bus.png" alt="欠席連絡アイコン" class="button-icon">欠席連絡
        </a>
    </div>
    <div class="menu-select">
        <a href="AbsenceReport.action" class="button-qr">
            <img src="../../common/image/home.png" alt="欠席報告アイコン" class="button-icon">欠席報告
        </a>
    </div>
</div>

		<a href="Menu.action" class="menu-link">
		    <img src="../../common/image/arrow.png" class="icon" alt="メニューアイコン">
		    メニュー画面に戻る
		</a>

	</div>
	</div>
</body>
<c:import url="/common/footer.jsp" />
</html>
