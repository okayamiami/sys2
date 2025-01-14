<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <link rel='stylesheet' href='/sys2/bussystem/css/style.css'>
  <title>登園・バス管理システム</title>
</head>


  <div class="header">

    <h1>
        登園・バス管理システム
    </h1>

    <c:if test="${user.isAuthenticated()}">

       <span>
        <c:choose>
          <c:when test="${user_type == 'M'}">
            ${user.getUser_name()}先生、ようこそ！
          </c:when>
          <c:when test="${user_type == 'T'}">
            ${user.getUser_name()}先生、ようこそ！
          </c:when>
          <c:when test="${user_type == 'P'}">
            ${user.getParents_name()}様、ようこそ！
          </c:when>
        </c:choose>
      </span>

    <div class="links">
        <a href="Menu.action">
            <img src="../images/home.png" >
            メニュー画面に戻る
        </a>
        <a href="Syslogout.action">
            <img src="../images/home.png" >
            ログアウト
        </a>
    </div>
  </c:if>
</div>

<body>
