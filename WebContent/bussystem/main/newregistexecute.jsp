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
    <li class="breadcrumb-item"><a href="NewRegistMenu.action">新規登録メニュー</a></li>
    <li class="breadcrumb-item"><a href="NewRegist.action">アカウント新規登録</a></li>
    <li class="breadcrumb-item active" aria-current="page">アカウント新規登録完了</li>
  </ol>
</nav>

<h2 class="title">アカウント新規情報登録完了</h2>
<p>アカウントの新規登録が完了しました</p>

<table class="table table-hover">
<tr>
	<th>ユーザーID</th>
	<th>パスワード</th>
	<th>区分</th>
</tr>
<tr>
	<td>${perfect_id}</td>
	<td>${password}</td>
	<td>
	<c:choose>
		<c:when test="${user_status=='M'}">
			管理者
		</c:when>
		<c:when test="${user_status=='T'}">
			先生
		</c:when>
		<c:otherwise>
			保護者
		</c:otherwise>
	</c:choose>
	</td>
<tr>
</table>

</div>
</div>


<c:import url="/common/footer.jsp" />