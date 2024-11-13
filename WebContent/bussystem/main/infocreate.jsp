<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>

<!-- ヘッダーをインポート -->
<c:import url="/common/header.jsp" />

<div class="main">
    <!-- ナビゲーションをインポート -->
    <c:import url="/common/navi.jsp" />

    <div class="con">
        <h2>お知らせ作成</h2>

        <form action="InfoCreateExecute.action" method="post">
            <!-- ジャンルの選択 -->
            <label>ジャンル</label>
            <select name="info_genre">
                <option value="">---</option>
                <c:forEach var="info" items="${info_set}">
                    <option value="${info.info_genre}">${info.info_genre}</option>
                </c:forEach>
            </select>
            <div>${errors.get("info_genre")}</div>

            <!-- タイトルの入力 -->
            <label>タイトル</label>
            <input type="text" name="title" value="${title}" required>
            <div>${errors.get("title")}</div>

            <!-- 本文の入力 -->
            <label>本文</label>
            <textarea name="content" rows="5" cols="40" required>${content}</textarea>
            <div>${errors.get("content")}</div>

            <!-- 送信ボタンと戻るボタン -->
            <div>
                <button type="submit">送信</button>
                <button type="button" onclick="history.back()">戻る</button>
            </div>
        </form>
    </div>
</div>

<!-- フッターをインポート -->
<c:import url="/common/footer.jsp" />

</body>
</html>

