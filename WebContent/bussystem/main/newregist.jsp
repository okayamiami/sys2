<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:import url="/common/header.jsp" />

<div class="main">

<div class ="con">

<h2 class="title">アカウント新規登録</h2>

<p>ユーザー区分を選択してください<br>M：管理者　T：先生　P：保護者</p>

	<form action = "NewRegistExecute.action" method="post">
		<label>新規ユーザー区分</label>
		<select name="user_status">
			<option value="0">--------</option>
			<c:forEach var="user_status" items="${user_status}">
				<option value="${user_status}">${user_status}</option>
			</c:forEach>
		</select>
		<div>${errors.get("user_status")}</div>


          <button type="submit" class="button-send">登録</button>
	</form>


</div>
</div>

<c:import url="/common/footer.jsp" />
