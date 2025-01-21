<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<c:import url="/common/header.jsp" />
<div class="main">
<div class="con">

    <!-- パンくずリスト -->
	<nav aria-label="breadcrumb">
	  <ol class="breadcrumb">
	    <li class="breadcrumb-item"><a href="Menu.action">メニュー</a></li>
	    <li class="breadcrumb-item"><a href="FacilityInfoMenu.action">施設・バス情報</a></li>
	    <li class="breadcrumb-item"><a href="BusInfo.action">バス情報</a></li>
	    <li class="breadcrumb-item active" aria-current="page">バス削除確認</li>
	  </ol>
	</nav>

<h2 class="title">バス削除削除確認</h2>

<!-- 削除確認メッセージ -->
<p>本当にこのバスを削除してもよろしいですか？</p>

<!-- 削除確認ボタン -->
<form action="BusDeleteExecute.action" method="post">
    <input type="hidden" name="bus_id" value="${bus_set.bus_id}" />
    <button type="submit" class="button-send">削除</button>
</form>


<!-- 取得した情報を表示 -->
<!-- 詳細情報を表示 -->
<c:if test="${not empty bus_set}">
    <table>
        <tr>
            <th>施設ID</th>
            <th>バスID</th>
            <th>名前</th>
        </tr>
        <tr>
            <td>${bus_set.facility_id}</td>
            <td>${bus_set.bus_id}</td>
            <td>${bus_set.bus_name}</td>
        </tr>
    </table>
</c:if>

<c:if test="${empty bus_set}">
    <p>バスが存在しません。</p>
</c:if>

</div>
</div>
<c:import url="/common/footer.jsp" />
