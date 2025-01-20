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
        <a class="home-icon" href="Menu.action">
            <img src="${pageContext.request.contextPath}/images/kids.png" alt="ホーム">登園・バス管理システム
        </a>
    </h1>
    <!-- 日付表示 -->
    <div id="dateContainer">
        <span id="currentDate"></span>

    </div>
    <div class="links">
        <!-- 各ボタン間に間隔ができます -->
        <a href="Menu.action">
        	<img src="${pageContext.request.contextPath}/images/home_g.png" alt="メニュー">
        </a>
        <a href="Syslogout.action">
            <img src="${pageContext.request.contextPath}/images/logout.png" alt="ログアウト">
        </a>
    </div>
<script>
document.addEventListener("DOMContentLoaded", function() {
  var today = new Date();
  var days = ['日曜日', '月曜日', '火曜日', '水曜日', '木曜日', '金曜日', '土曜日'];
  var date = today.getFullYear() + '年' + (today.getMonth() + 1) + '月' + today.getDate() + '日' + ' (' + days[today.getDay()] + ')';
  document.getElementById('currentDate').innerText = date;
});
</script>
</div>

  <div id="h_line">
    <img src="${pageContext.request.contextPath}/images/drop_line.png" alt="しずく線">
  </div>


<body>

<div class="main">

<div class ="con">
      <p>情報を更新しました</p>
      <a href="../Login.action" class="button-send">ログイン</a>

    </div>
</div>


<c:import url="/common/footer.jsp" />
