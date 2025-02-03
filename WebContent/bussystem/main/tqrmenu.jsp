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
	    <li class="breadcrumb-item active" aria-current="page">QR機能選択</li>
	  </ol>
	</nav>


<h2 class="title">QR機能選択</h2>
<div class="menu-links">

		<a href="QrReaderSelectBus.action" class="menu-links-sub">
		    <img src="${pageContext.request.contextPath}/images/スマホ.png" alt="QR読み取り" width="50">
		    <span>QR読み取り</span>
		</a>

		<a href="GetListInfo.action" class="menu-links-sub">
		    <img src="${pageContext.request.contextPath}/images/乗降.png" alt="乗降状況" width="50">
		    <span>乗降状況</span>
		</a>

</div>
</div>
</div>
<c:import url="/common/footer.jsp" />