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

        <!-- エラーメッセージの表示 -->
        <c:if test="${not empty error}">
            <p style="color: red; font-weight: bold;">${error}</p>
        </c:if>

        <!-- 情報表示 -->
        <c:if test="${empty error}">
            <table>
                <tr><th>子供ID</th><td>${child_id}</td></tr>
                <tr><th>名前（子供）</th><td>${child_name}</td></tr>
                <tr><th>クラス</th>
                    <td>
                        <c:forEach var="classItem" items="${classlist}">
                            <c:if test="${class_id eq classItem.class_id}">
                                ${classItem.class_name}
                            </c:if>
                        </c:forEach>
                    </td>
                </tr>
                <tr><th>保護者ID</th><td>${parents_id}</td></tr>
                <tr><th>名前（保護者）</th><td>${parents_name}</td></tr>
                <tr><th>住所</th><td>${parents_address}</td></tr>
                <tr><th>電話番号</th><td>${parents_tel}</td></tr>
                <tr><th>メールアドレス１</th><td>${parents_mail1}</td></tr>
                <tr><th>メールアドレス２</th><td>${parents_mail2}</td></tr>
                <tr><th>メールアドレス３</th><td>${parents_mail3}</td></tr>
            </table>
        </c:if>

        <a href="ChildList.action">名簿一覧画面に戻る</a>
    </div>
</div>

</body>
<c:import url="/common/footer.jsp" />
</html>