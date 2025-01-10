<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
</head>
<body>

<c:import url="/common/header.jsp" />

<div class="main">
<c:import url="/common/navi.jsp" />

<div class ="con">


    <h2>子供情報</h2>
    <p>${message}</p>
    		<c:choose>
		    <c:when test="${user_type == 'M'}">
		        <form action="ParentsIDInput.action" method="get">
		            <input type="hidden" name="parents_id" value="${parents_id}">
		             <div class="button-save">
		                <button type="submit">子供情報の確認</button>
		            </div>
		        </form>
		    </c:when>

		    <c:when test="${user_type == 'P'}">
		        <form action="ChildInfo.action" method="get">
		             <div class="button-save">
		                <button type="submit">子供情報の確認</button>
		            </div>
		        </form>
		    </c:when>
		</c:choose>
    <a href="menu.jsp" class="menu-link">メニューに戻る</a>
    </div>
    </div>
</body>
</html>