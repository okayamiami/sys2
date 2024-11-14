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
        <h2>お知らせ一覧</h2>

        <!-- お知らせの一覧を表示 -->
        <c:forEach var="info" items="${ilist_set}">
            <table border="1">
                <tr>
                    <td>日付</td>
                    <td>${info.info_date}</td>
                </tr>
                <tr>
                    <td>タイトル</td>
                    <!-- タイトルをクリック可能にする -->
                    <td><a href="InfoContent.action?info_id=${info.info_id}">${info.info_title}</a></td>
                </tr>
                <tr>
                    <td>ジャンル</td>
                    <td>${info.info_genre}</td>
                </tr>
            </table>
            <br>
        </c:forEach>

        <!-- 戻るボタン -->
        <div>
            <button type="button" onclick="history.back()">戻る</button>
        </div>
    </div>
</div>

<!-- フッターをインポート -->
<c:import url="/common/footer.jsp" />

</body>
</html>


