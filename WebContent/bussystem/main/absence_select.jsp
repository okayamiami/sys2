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
	<form action="Menu.action" method="get">
        <button type="submit">戻る</button>
    </form>
	<h2>欠席機能選択</h2>
	<form action="AbsenceConect.action" method="post">
		<input type="submit" value="欠席連絡">
	</form>
	<form action="AbsenceReport.action" method="post">
		<input type="submit" value="欠席報告">
	</form>


	</div>
	</div>
</body>
<c:import url="/common/footer.jsp" />
</html>
