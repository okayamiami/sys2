<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <link rel='stylesheet' href='../../bussystem/css/style.css'>
  <title>登園・バス管理システム</title>
</head>

  <div class="hamburger-menu">
    <input id="menu__toggle" type="checkbox" />
    <label class="menu__btn" for="menu__toggle">
      <span></span>
    </label>

    <ul class="menu__box">
      <li><a class="menu__item" href="Menu.action">メニュー画面</a></li>

      <c:choose>
        <c:when test="${user_type == 'M'}">
          <li><a class="menu__item" href="NewRegist.action">新規登録</a></li>
          <li><a class="menu__item" href="ChildList.action">名簿情報一覧</a></li>
          <li><a class="menu__item" href="Parents.action">保護者情報</a></li>
          <li><a class="menu__item" href="ChildInfo.action">子供情報</a></li>
          <li><a class="menu__item" href="AbsenceSelect.action">欠席機能</a></li>
          <li><a class="menu__item" href="InfoMenu.action">お知らせ機能</a></li>
          <li><a class="menu__item" href="QrMenu.action">QR機能</a></li>
          <c:if test="${user_id == 'M0000001'}">
            <li><a class="menu__item" href="FacilityInfo.action">施設情報</a></li>
          </c:if>
        </c:when>
        <c:when test="${user_type == 'T'}">
          <li><a class="menu__item" href="ChildList.action">名簿情報一覧</a></li>
          <li><a class="menu__item" href="AbsenceSelect.action">欠席機能</a></li>
          <li><a class="menu__item" href="InfoMenu.action">お知らせ機能</a></li>
          <li><a class="menu__item" href="QrMenu.action">QR機能</a></li>
        </c:when>
        <c:when test="${user_type == 'P'}">
          <li><a class="menu__item" href="Parents.action">保護者情報</a></li>
          <li><a class="menu__item" href="ChildInfo.action">子供情報</a></li>
          <li><a class="menu__item" href="AbsenceReport.action">欠席報告</a></li>
          <li><a class="menu__item" href="InfoList.action">お知らせ一覧</a></li>
        </c:when>
      </c:choose>
    </ul>
  </div>

  <div class="header">
    <div>
      <h1>登園・バス管理システム</h1>
    </div>
    <c:if test="${user.isAuthenticated()}">
      <div>
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
        <a href="Syslogout.action">ログアウト</a>
      </div>
    </c:if>
  </div>
<body>
