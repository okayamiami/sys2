<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>登録完了</title>
</head>
<body>

<c:import url="/common/header.jsp" />
<div class="main">
<c:import url="/common/navi.jsp" />
<div class="con">
<h2>お知らせ削除確認</h2>

<!-- 取得した情報を表示 -->
<!-- 詳細情報を表示 -->
        <table border="1">
            <tr>
                <td>日付</td>
                <td><fmt:formatDate value="${info_set.info_date}" pattern="yyyy年MM月dd日 HH:mm" /></td>
            </tr>
            <tr>
                <td>タイトル</td>
                <td>${info_set.info_title}</td>
            </tr>
            <tr>
                <td>本文</td>
                <td>${info_set.info_main}</td>
            </tr>
            <tr>
                <td>ジャンル</td>
                <td>${info_set.info_genre}</td>
            </tr>
        </table>

<!-- 削除確認メッセージ -->
<p>本当にこのお知らせ情報を削除してもよろしいですか？</p>

<!-- 削除確認ボタン -->
<form action="InfoDeleteExecute.action" method="post">
    <input type="hidden" name="info_id" value="${info_set.info_id}" />
    <input type="submit" value="削除する" />
</form>

<!-- 戻るボタン -->
<form action="InfoContent.action" method="post">
<input type="hidden" name="info_id" value="${info_set.info_id}" />
    <input type="submit" value="キャンセル" />
</form>
</div>
</div>
</body>
<c:import url="/common/footer.jsp" />
</html>
