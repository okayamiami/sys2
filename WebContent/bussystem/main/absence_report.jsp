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
	<%-- エラーのところの処理わからない --%>
    <form action="AbsenceReportExecute.action" method="post">
        <label>名前(子供)</label>
        <select name="child_name">
		<option value="0">--------</option>
	        <c:forEach var="name" items="${cNamelist}">
	            <option value="${name}">${name}</option>
	        </c:forEach>        </select>
        <%-- <div>${errors.get("")}</div> --%>

        <label>欠席理由</label>
        <%-- ☆★☆CSSで理由記入欄を大きく表示する☆★☆ --%>
        <input type="text" name="abs_main"
            placeholder="欠席理由を入力してください" maxlength="30"  />
        <%-- <div>${errors.get("no")}</div> --%>

        <input type="submit" value="送信">
    </form>
    <%--<a href="？？？.action">戻る</a>--%>
</body>
</html>
