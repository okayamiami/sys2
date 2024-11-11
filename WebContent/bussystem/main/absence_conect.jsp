<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>欠席連絡</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }
        table, th, td {
            border: 1px solid black;
        }
        th, td {
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
    <form action="AbsenceSelect.action" method="get">
        <button type="submit">戻る</button>
    </form>
    <c:if test="${not empty error}">
        <div class="alert alert-danger">
            ${error}
        </div>
    </c:if>
    <form action="AbsenceConectAction" method="get">
        <label for="absence_date">欠席日:</label>
        <select id="absence_date" name="absence_date">
            <option value="">選択してください</option>
            <c:forEach var="date" items="${absenceDates}">
                <option value="${date}">${date}</option>
            </c:forEach>
        </select>
        <label for="class_name">クラス:</label>
        <select id="class_name" name="class_name">
            <option value="">選択してください</option>
            <c:forEach var="className" items="${classNames}">
                <option value="${className}">${className}</option>
            </c:forEach>
        </select>
        <label for="child_name">名前:</label>
        <select id="child_name" name="child_name">
            <option value="">選択してください</option>
            <c:forEach var="childName" items="${childNames}">
                <option value="${childName}">${childName}</option>
            </c:forEach>
        </select>
        <button type="submit">検索</button>
    </form>
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
                <td>${absence.class_name}</td>
                <td>${absence.child_name}</td>
                <td>${absence.absence_main}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>