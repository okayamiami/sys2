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
	    <li class="breadcrumb-item active" aria-current="page">施設・バス情報</li>
	  </ol>
	</nav>

<h2 class="title">施設情報選択</h2>
<div class="menu-links">
<a href="FacilityInfo.action" class="menu-links-sub">
    <img src="${pageContext.request.contextPath}/images/施設・バス.png" alt="施設情報" width="50">
    <span>施設情報</span>
</a>

<a href="BusInfo.action" class="menu-links-sub">
    <img src="${pageContext.request.contextPath}/images/新規バス.png" alt="バス情報" width="50">
    <span>バス情報</span>
</a>

</div>
</div>
</div>

<c:import url="/common/footer.jsp" />