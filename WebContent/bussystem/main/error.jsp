<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>エラー</title>
</head>
<body>
    <h2 class="title">エラーが発生しました</h2>

    <!-- errorMessage属性が存在する場合に表示 -->
    <c:if test="${not empty errorMessage}">
        <div>
            <strong style="color:red;">${errorMessage}</strong>
        </div>
    </c:if>

    <br>
    <a href="Menu.action">メニュー画面に戻る</a>
</body>
</html>

