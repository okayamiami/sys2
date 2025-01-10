<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>メニュー</title>

</head>

<body>
<c:import url="/common/header.jsp" />
<div class="main">
<div class="conmenu">
		<form action = "ｃ" method="post">
		<%
		session.getAttribute("user_type");
		session.getAttribute("user_id");
		%>

<c:choose>
    <c:when test="${user_type == 'M'}">
        <h2>管理者</h2>
			<div class="menu-section">
			    <h4>情報関係</h4>
			    <div class="menu-links">
			        <a href="NewRegistMenu.action">新規登録</a>
			        <a href="Parents.action">保護者情報</a>
			        <a href="ChildInfo.action">子供情報</a>
			        <c:if test="${user_id == 'M0000001'}">
			            <a href="FacilityInfo.action">施設情報</a>
			        </c:if>
			    </div>
			</div>

			<div class="menu-section">
			    <h4>子供管理</h4>
			    <div class="menu-links">
			        <a href="ChildList.action">名簿情報一覧</a>
			        <a href="AbsenceSelect.action">欠席機能</a>
			        <a href="QrMenu.action">QR機能</a>
			    </div>
			</div>

			<div class="menu-section">
			    <h4>お知らせ</h4>
			    <div class="menu-links">
			        <a href="InfoMenu.action">お知らせ機能</a>
			    </div>
			</div>
	</c:when>
    <c:when test="${user_type == 'T'}">
        <h2>先生</h2>
        <div class="menu-links">
            <a href="ChildList.action">名簿情報一覧</a>
            <a href="AbsenceSelect.action">欠席機能</a>
            <a href="InfoMenu.action">お知らせ機能</a>
            <a href="QrMenu.action">QR機能</a>
        </div>
    </c:when>
    <c:when test="${user_type == 'P'}">
        <h2>保護者</h2>
        <div class="menu-links">
            <a href="Parents.action">保護者情報</a>
            <a href="ChildInfo.action">子供情報</a>
            <a href="AbsenceReport.action">欠席報告</a>
            <a href="InfoList.action">お知らせ一覧</a>
        </div>
    </c:when>
</c:choose>

		</form>
</div>
</div>

</body>
<c:import url="/common/footer.jsp" />
</html>