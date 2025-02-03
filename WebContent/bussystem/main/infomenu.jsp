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
	    <li class="breadcrumb-item active" aria-current="page">お知らせ機能選択</li>
	  </ol>
	</nav>


<h2 class="title">お知らせ機能選択</h2>
<div class="menu-links">

		<a href="InfoList.action" class="menu-links-sub">
		    <img src="${pageContext.request.contextPath}/images/一覧お知らせ.png" alt="お知らせ一覧" width="50">
		    <span>お知らせ一覧</span>
		</a>

		<a href="InfoCreate.action" class="menu-links-sub">
		    <img src="${pageContext.request.contextPath}/images/新規作成.png" alt="お知らせ作成" width="50">
		    <span>お知らせ作成</span>
		</a>

</div>

</div>
</div>
<c:import url="/common/footer.jsp" />

