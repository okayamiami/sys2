<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <link rel='stylesheet' href=${pageContext.request.contextPath}/bussystem/css/style22.css>
  <title>登園・バス管理システム</title>
</head>
<body>

<div class="header">
    <div class="header-content">
        <img class="logo-image" src="${pageContext.request.contextPath}/images/logout_head.png" alt="ホーム">
        <div class="text-content">
            <h1>登園・バス管理システム</h1>
            <h2>ログアウト</h2>
        </div>
    </div>

    <div class="links">
        <a href="Login.action">
            <img src="${pageContext.request.contextPath}/images/login.png" alt="ログイン">
        </a>
    </div>

    <div id="h_line">
        <img src="${pageContext.request.contextPath}/images/drop_line.png" alt="しずく線">
    </div>

</div>
<div class="main">
<div class="con">
	<h2>ログアウトしました</h2>
	<br>
	<a href="/sys2/bussystem/Login.action" class="login-btn">ログイン</a>
</div>
</div>

</body>

<div id="footer-container">
  <div id="h_line">
    <img src="${pageContext.request.contextPath}/images/drop_line.png" alt="しずく線">
  </div>
  <div class="foo">
    <p>2024©</p>
    <p>大原学園teamD</p>
  </div>
</div>

</html>