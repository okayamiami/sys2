<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<body>
<c:import url="/common/header.jsp" />

<div class="main">
<div class="con">

	<h2 class="title">バス新規作成完了</h2>

	<!-- エラーメッセージの表示 -->
	<c:if test="${not empty error}">
    	<p style="color: red; font-weight: bold;">${error}</p>
    </c:if>

    <p>下記情報でバス新規登録が完了しました</p>

    <table class="table table-hover">
		<tr>
			<th>バスID</th>
			<th>バス名</th>
		</tr>
		<tr>
			<td>${bus_id}</td>
			<td>${bus_name}</td>
		<tr>
	</table>


  </div>
  </div>
</body>
<c:import url="/common/footer.jsp" />
</html>
