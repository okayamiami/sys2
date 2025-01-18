<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- ヘッダーをインポート -->
<c:import url="/common/header.jsp" />

<div class="main">
    <div class="con">

    <!-- パンくずリスト -->
	<nav aria-label="breadcrumb">
	  <ol class="breadcrumb">
	    <li class="breadcrumb-item"><a href="Menu.action">メニュー</a></li>
	    <li class="breadcrumb-item"><a href="InfoMenu.action">お知らせ機能選択</a></li>
	    <li class="breadcrumb-item"><a href="InfoList.action">お知らせ一覧</a></li>
	    <li class="breadcrumb-item active" aria-current="page">お知らせ詳細</li>
	  </ol>
	</nav>

        <h2 class="title">お知らせ詳細</h2>

        <%-- リクエスト属性 user_type が "M" の場合にフォームを表示 --%>
		<c:if test="${requestScope.user_type eq 'M'}">
		    <form action="InfoDelete.action" method="post">
		        <input type="hidden" name="info_id" value="${info_set.info_id}" />
		        <button type="submit" class="button-send">削除</button>
		    </form>
		</c:if>


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
                <script>
				    window.onload = function() {
				        var content = document.getElementById("info_main");
				        if (content) {
				            content.innerHTML = content.innerHTML.replace(/\n/g, "<br>");
				        }
				    };
				</script>

				<td id="info_main">${info_set.info_main}</td>
            </tr>
            <tr>
                <td>ジャンル</td>
                <td>${info_set.info_genre}</td>
            </tr>
        </table>


    </div>
</div>

<!-- フッターをインポート -->
<c:import url="/common/footer.jsp" />