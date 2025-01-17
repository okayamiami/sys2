<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <link rel='stylesheet' href=${pageContext.request.contextPath}/bussystem/css/style.css>
  <title>登園・バス管理システム</title>
</head>


  <div class="header">

<h1>
    <!-- ホームアイコンには特別なスタイルを適用しない -->
    <a class="home-icon" href="Menu.action">
        <img src="${pageContext.request.contextPath}/images/new.png" alt="ホーム">
    </a>

    登園・バス管理システム
</h1>
<!-- 日付表示 -->
<span id="currentDate"></span>

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
		    <!-- 各ボタン間に間隔ができます -->
		    <a href="Menu.action">
		        <img src="${pageContext.request.contextPath}/images/new.png" alt="メニュー">
		        メニュー画面に戻る
		    </a>
		    <a href="Syslogout.action">
		        <img src="${pageContext.request.contextPath}/images/home.png" alt="ログアウト">
		        ログアウト
		    </a>
		</div>


<script> document.addEventListener("DOMContentLoaded", function() { var today = new Date(); var days = ['日曜日', '月曜日', '火曜日', '水曜日', '木曜日', '金曜日', '土曜日']; var date = today.getFullYear() + '年' + (today.getMonth() + 1) + '月' + today.getDate() + '日' + ' (' + days[today.getDay()] + ')'; document.getElementById('currentDate').innerText = date; }); </script></c:if>


</div>

<body>
