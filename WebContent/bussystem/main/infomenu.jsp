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
<a href="InfoList.action">お知らせ一覧</a>

<a href="InfoCreate.action">お知らせ作成</a>
</div>

</div>
</div>
<c:import url="/common/footer.jsp" />

