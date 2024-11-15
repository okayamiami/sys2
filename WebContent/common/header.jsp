<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <link rel='stylesheet' href='../../bussystem/css/style.css'>
  <title>登園・バス管理システム</title>
</head>
<div class ="header">
<div>
  <h1>得点管理システム</h1>
</div>

<c:if test="${user.isAuthenticated()}">
  <div>
    <span>
      <c:choose>
        <c:when test="${user_type == 'M'}">
          ${user.getUser_name()}先生、ようこそ！
        </c:when>
        <c:when test="${user_type == 'P'}">
          ${user.getParents_name()}様、ようこそ！
        </c:when>
      </c:choose>
    </span>
    <a href="Syslogout.action">ログアウト</a>
  </div>
</c:if>
</div>

