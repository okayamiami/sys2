<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>エラー</title>
</head>
<body>
    <h2>エラーが発生しました</h2>

    <!-- エラーメッセージの表示 -->
    <c:if test="${not empty error}">
        <div>
            <strong style="color:red;">${error}</strong>
        </div>
    </c:if>

    <br>
    <a href="qrreader.jsp">QRコード読み取りページに戻る</a>
</body>
</html>

</body>
</html>
