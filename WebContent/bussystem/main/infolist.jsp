<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<!-- ヘッダーをインポート -->
<c:import url="/common/header.jsp" />

<div class="main">
    <div class="con">

    <!-- パンくずリスト -->
    <c:choose>
	<c:when test="${user_type == 'M' || user_type == 'T'}">
	<nav aria-label="breadcrumb">
	  <ol class="breadcrumb">
	    <li class="breadcrumb-item"><a href="Menu.action">メニュー</a></li>
	    <li class="breadcrumb-item"><a href="InfoMenu.action">お知らせ機能選択</a></li>
	    <li class="breadcrumb-item active" aria-current="page">お知らせ一覧</li>
	  </ol>
	</nav>
	</c:when>

	    <c:when test="${user_type == 'P'}">
	    <nav aria-label="breadcrumb">
	  <ol class="breadcrumb">
	    <li class="breadcrumb-item"><a href="Menu.action">メニュー</a></li>
	    <li class="breadcrumb-item active" aria-current="page">お知らせ一覧</li>
	  </ol>
	</nav>
	    </c:when>
	</c:choose>


        <h2 class="title">お知らせ一覧</h2>
        <c:if test="${not empty delete_message}">
		        <div>
		            <strong style="color:black;">${delete_message}</strong>
		        </div>
		</c:if>
        <!-- お知らせの一覧を表示 -->
        <c:if test="${empty ilist_set}">
        	<div>
		            <strong style="color:black;">現在閲覧可能なお知らせはありません。</strong>
		    </div>
        </c:if>
         <c:if test="${not empty ilist_set}">
	        <c:forEach var="info" items="${ilist_set}">
	            <table border="1">
	                <tr>
	                    <td>日付</td>
	                    <!-- Timestamp型の日付をフォーマットして表示 -->
	                    <td>
	                        <fmt:formatDate value="${info.info_date}" pattern="yyyy年MM月dd日 HH:mm" />
	                    </td>
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
        </c:if>

    </div>
</div>

<!-- フッターをインポート -->
<c:import url="/common/footer.jsp" />


