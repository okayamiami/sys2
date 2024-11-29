<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>バス登録完了画面</title>
</head>
<body>
<c:import url="/common/header.jsp" />

<div class="main">
<c:import url="/common/navi.jsp" />
<div class="con">

	<form action="Menu.action" method="get"> <%--決まったら正しいことろに飛ばす --%>
        <button type="submit">戻る</button>
    </form>


	<h2>バス新規作成</h2>

	<!-- エラーメッセージの表示 -->
	<c:if test="${not empty error}">
    	<p style="color: red; font-weight: bold;">${error}</p>
    </c:if>

    <p>バス新規作成が完了しました</p>

  </div>
  </div>
</body>
<c:import url="/common/footer.jsp" />
</html>