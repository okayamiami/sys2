<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:import url="/common/header.jsp" />

<div class="main">


<div class ="con">

<h2 class="title">新規情報登録</h2>
<p>新規ユーザーの登録が完了しました</p>

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