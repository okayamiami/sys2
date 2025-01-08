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

	<div class="menu-links-qr">

		<a href="AbsenceConect.action">欠席連絡</a>

		<a href="AbsenceReport.action">欠席報告</a>
	</div>

		<a href="Menu.action" class="menu-link">
  			<img src="../../common/image/home.png" class="icon">メニュー画面に戻る
		</a>

	</div>
	</div>
</body>
<c:import url="/common/footer.jsp" />
</html>
