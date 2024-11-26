<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>欠席報告</title>
</head>
<body>
<c:import url="/common/header.jsp" />

<div class="main">
<c:import url="/common/navi.jsp" />
<div class="con">
	<form action="AbsenceSelect.action" method="get">
        <button type="submit">戻る</button>
    </form>

    <h2>欠席報告</h2>

    <!-- エラーメッセージの表示 -->
	<c:if test="${not empty error}">
            <p style="color: red; font-weight: bold;">${error}</p>
    </c:if>

    <form action="AbsenceReportExecute.action" method="post">
        <label>名前(子供)</label>
        <select name="child_name">
		<option value="0">--------</option>
	        <c:forEach var="name" items="${cNamelist}">
	            <option value="${name}">${name}</option>
	        </c:forEach>        </select>

        <p></p>

        <label>欠席理由</label>
	        <input type="text" name="abs_main" style="width:400px;height:25px;"
	            placeholder="30文字以内で欠席理由を入力してください" maxlength="30"  />

        <input type="submit" value="送信">
    </form>

   </div>
   </div>
</body>
<c:import url="/common/footer.jsp" />
</html>
