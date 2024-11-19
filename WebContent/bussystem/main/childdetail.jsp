<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>子供詳細</title>
</head>
<body>
<c:import url="/common/header.jsp" />
<div class="main">
<c:import url="/common/navi.jsp" />
<div class="con">
	<h2>子供詳細情報</h2>

	<table class="table table-hover">

			<tr><th>子供ID</th><td>${cdetail.child_id}</td></tr>
			<tr><th>名前（子供）</th><td>${cdetail.child_name}</td></tr>

			<tr><th>保護者ID</th><td>${cdetail.parents_id}</td></tr>
			<tr><th>名前（保護者）</th><td>${cdetail.parents_name}</td></tr>
			<tr><th>住所</th><td>${cdetail.parents_address}</td></tr>
			<tr><th>電話番号</th><td>${cdetail.parents_tel}</td></tr>
			<tr><th>メールアドレス１</th><td>${cdetail.parents_mail1}</td></tr>
			<tr><th>メールアドレス２</th><td>${cdetail.parents_mail2}</td></tr>
			<tr><th>メールアドレス３</th><td>${cdetail.parents_mail3}</td></tr>
	</table>

</div>
</div>
</body>
<c:import url="/common/footer.jsp" />
</html>