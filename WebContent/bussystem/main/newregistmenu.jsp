<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<c:import url="/common/header.jsp" />

<div class="main">

<div class ="con">

<!-- パンくずリスト -->
<nav aria-label="breadcrumb">
  <ol class="breadcrumb">
    <li class="breadcrumb-item"><a href="Menu.action">メニュー</a></li>
    <li class="breadcrumb-item active" aria-current="page">新規登録メニュー</li>
  </ol>
</nav>

<h2 class="title">新規登録メニュー</h2>

<p>新規登録する項目を選択してください</p>

<div class="menu-links">
<a href="NewRegist.action" class="menu-links-sub">
    <img src="${pageContext.request.contextPath}/images/ユーザー情報.png" alt="アカウント新規登録" width="50">
    <span>アカウント新規登録</span>
</a>
<a href="BusCreate.action" class="menu-links-sub">
    <img src="${pageContext.request.contextPath}/images/新規バス.png" alt="バス新規登録" width="50">
    <span>バス新規登録</span>
</a>

</div>


</div>
</div>

<c:import url="/common/footer.jsp" />

