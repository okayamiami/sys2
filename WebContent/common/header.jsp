<%-- ヘッダー --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel='stylesheet' href='../../bussystem/css/style.css'>

<title>登園・バス管理システム</title>
</head>


<div class ="header">
<h1>登園・バス管理システム</h1>
<c:if test="${user.isAuthenticated()}">
<span>${user.getUser_name()}様</span>
<a href="Syslogout.action">ログアウト</a>
</c:if>
</div>