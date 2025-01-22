<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="/common/header.jsp" />
<div class="main">
<div class="con">
<nav aria-label="breadcrumb">
	  <ol class="breadcrumb">
	    <li class="breadcrumb-item"><a href="Menu.action">メニュー</a></li>
	    <li class="breadcrumb-item"><a href="ManageUser.action">ユーザー情報</a></li>
	    <li class="breadcrumb-item"><span>ユーザー情報編集</span></li>
	    <li class="breadcrumb-item active" aria-current="page">ユーザー情報更新完了</li>
	  </ol>
	</nav>
<h2 class="title">ユーザー情報変更完了</h2>
<p>以下情報にユーザー情報を変更しました</p>

	<table class="table table-hover">
             <tr><th>ユーザーID</th><td>${user_id}</td></tr>
             <tr><th>名前</th><td>${user_name}</td></tr>
             <tr><th>パスワード</th><td>${user_pass}</td></tr>
             <tr><th>施設ID</th><td>${facility_id}</td></tr>

    </table>


</div>
</div>

<c:import url="/common/footer.jsp" />