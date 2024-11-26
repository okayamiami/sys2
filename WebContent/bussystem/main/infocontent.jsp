<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>

<!-- ヘッダーをインポート -->
<c:import url="/common/header.jsp" />

<div class="main">
    <!-- ナビゲーションをインポート -->
    <c:import url="/common/navi.jsp" />

    <div class="con">
        <h2>お知らせ詳細</h2>

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

        <!-- 戻るボタン -->
        <a href="InfoList.action">お知らせ一覧に戻る</a>
    </div>
</div>

<!-- フッターをインポート -->
<c:import url="/common/footer.jsp" />

</body>
</html>
