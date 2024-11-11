<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>欠席連絡</title>
</head>
<body>
    <c:if test="${not empty error}">
        <div class="alert alert-danger">
            ${error}
        </div>
    </c:if>
    <table class="table table-hover">
        <tr>
            <th>欠席日</th>
            <th>クラス</th>
            <th>名前</th>
            <th>欠席理由</th>
        </tr>
        <c:forEach var="absence" items="${absenceList}">
            <tr>
                <td>${absence.absence_date}</td>
                <td>${absence.facility_id}</td>
                <td>${absence.child_id}</td>
                <td>${absence.absence_main}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>