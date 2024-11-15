<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>メニュー</title>

</head>
<c:import url="/common/header.jsp" />
<body>
		<form action = "Menu.action" method="post">
		<%
		session.getAttribute("user_type");
		%>

			<c:choose>
				<c:when test="${user_type == 'M'}">
					<h2>管理者</h2>
					<a href="NewRegist.action">新規登録</a>
					<a href="ChildList.action">名簿情報一覧</a>
					<a href="Parentsinfo.action">保護者情報</a>
					<a href="子供情報">子供情報</a>
					<a href="AbsenceSelect.action">欠席機能</a>
					<a href="InfoMenu.action">お知らせ機能</a>
					<a href="QrMenu.action">QR機能</a>
				</c:when>
				<c:when test="${user_type == 'T'}">
					<h2>先生</h2>
					<a href="ChildList.action">名簿情報一覧</a>
					<a href="AbsenceSelect.action">欠席機能</a>
					<a href="InfoMenu.action">お知らせ機能</a>
					<a href="QrMenu.action">QR機能</a>
				</c:when>
				<c:when test="${user_type == 'P'}">
					<h2>保護者</h2>
					<a href="Parentsinfo.action">保護者情報</a>
					<a href="子供情報">子供情報</a>
					<a href="AbsenceReport.action">欠席報告</a>
					<a href="InfoList.action">お知らせ一覧</a>
				</c:when>
			</c:choose>

		</form>

</body>
</html>