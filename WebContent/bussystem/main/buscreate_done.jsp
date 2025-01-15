<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<body>
<c:import url="/common/header.jsp" />

<div class="main">
<c:import url="/common/navi.jsp" />
<div class="con">

	<h2>バス新規作成</h2>

	<!-- エラーメッセージの表示 -->
	<c:if test="${not empty error}">
    	<p style="color: red; font-weight: bold;">${error}</p>
    </c:if>

    <p>バス新規作成が完了しました</p>
    <a href="NewRegistMenu.action">新規作成機能選択に戻る</a>
  </div>
  </div>
</body>
<c:import url="/common/footer.jsp" />
</html>
