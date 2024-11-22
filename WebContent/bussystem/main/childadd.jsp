<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>子供新規登録</title>
</head>
<body>
    <h1>子供新規登録</h1>

    <!-- メッセージ表示 -->
    <c:if test="${not empty message}">
        <p style="color: red;">${message}</p>
    </c:if>

    <!-- 登録フォーム -->
    <form action="ChildAdd.action" method="post">
        <label for="child_id">子供ID:</label>
        <input type="text" id="child_id" name="child_id" required><br>

        <label for="child_name">子供の名前:</label>
        <input type="text" id="child_name" name="child_name" required><br>

        <label for="parents_id">保護者ID:</label>
        <input type="text" id="parents_id" name="parents_id" required><br>

        <label for="class_id">クラスID:</label>
        <input type="text" id="class_id" name="class_id" required><br>

        <label for="is_attend">出席中:</label>
        <select id="is_attend" name="is_attend" required>
            <option value="true">はい</option>
            <option value="false">いいえ</option>
        </select><br>

        <label for="facility_id">施設ID:</label>
        <input type="text" id="facility_id" name="facility_id" required><br>

        <button type="submit">登録</button>
    </form>
</body>
</html>
