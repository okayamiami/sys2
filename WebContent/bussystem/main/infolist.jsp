<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<!-- ヘッダーをインポート -->
<c:import url="/common/header.jsp" />

<div class="main">

    <div class="con">
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


