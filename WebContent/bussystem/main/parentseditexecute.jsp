<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>更新結果</title>
</head>
<body>

<c:import url="/common/header.jsp" />

<div class="main">
<c:import url="/common/navi.jsp" />

<div class="con">

    <h2>保護者情報</h2>
    <p>${message}</p>

    <c:choose>
        <c:when test="${user_type == 'M'}">
        	<a href="ParentsInput.action?parents_id=${parents_id}">保護者情報の確認</a>
        </c:when>

        <c:when test="${user_type == 'P'}">
            <a href="Parents.action">保護者情報の確認</a>
        </c:when>

    </c:choose>
    <a href="menu.jsp">メニューに戻る</a>

</div>
</div>

</body>
</html>
