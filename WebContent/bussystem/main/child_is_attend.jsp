<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">



<c:import url="/common/header.jsp" />

<div class="main">
    <div class="con">

		<!-- アカウント区分ごとの表示 -->
       	<c:choose>
	    <c:when test="${user_type == 'M'}">
	    <!-- 管理者パンくずリスト -->
		<nav aria-label="breadcrumb">
		  <ol class="breadcrumb">
		    <li class="breadcrumb-item"><a href="Menu.action">メニュー</a></li>
		    <li class="breadcrumb-item"><a href="ChildInfo.action">保護者ID入力（子供情報）</a></li>
		    <li class="breadcrumb-item"><a href="ParentsIDInput.action?parents_id=${parents_id}">子供情報一覧</a></li>
		    <li class="breadcrumb-item active" aria-current="page">在籍状況変更</li>
		  </ol>
		</nav>
	    </c:when>

	    <c:when test="${user_type == 'P'}">
	    <!-- 保護者パンくずリスト -->
	    <nav aria-label="breadcrumb">
		  <ol class="breadcrumb">
		    <li class="breadcrumb-item"><a href="Menu.action">メニュー</a></li>
		    <li class="breadcrumb-item"><a href="ChildInfo.action">子供情報一覧</a></li>
		    <li class="breadcrumb-item active" aria-current="page">子供情報削除確認</li>
		  </ol>
		</nav>

	    </c:when>
	</c:choose>


		<!-- アカウント区分ごとの表示 -->
       	<c:choose>
	    <c:when test="${user_type == 'M'}">
	    <!-- 管理者タイトル -->
	    <h2 class="title">在籍情報変更確認</h2>

	    	<!-- エラーメッセージの表示 -->
			<c:if test="${not empty error}">
		    	<p style="color: red; font-weight: bold;">${error}</p>
		    </c:if>

		    <p>本当に在籍情報を非表示に変更してもよろしいですか？</p>
				<form action="ChildIsAttendExecute.action" method="get">
			        <input type="hidden" name="child_id" value="${child_id}">
			        <input type="hidden" name="facility_id" value="${facility_id}">
			        <input type="hidden" name="parents_id" value="${parents_id}">
			        <button type="submit" class="button-send">非表示</button>
			    </form>
	    </c:when>

	    <c:when test="${user_type == 'P'}">
	    <!-- 保護者タイトル -->
		<h2 class="title">子供情報削除確認</h2>
			<!-- エラーメッセージの表示 -->
			<c:if test="${not empty error}">
		    	<p style="color: red; font-weight: bold;">${error}</p>
		    </c:if>

		    <p>本当に子供情報を削除してもよろしいですか？</p>
				<form action="ChildIsAttendExecute.action" method="get">
			        <input type="hidden" name="child_id" value="${child_id}">
			        <input type="hidden" name="facility_id" value="${facility_id}">
			        <button type="submit" class="button-send">削除</button>
			    </form>
	    </c:when>
	</c:choose>


		<table class="table table-hover">
              <tr><th>子供ID</th><td>${child_id}</td></tr>
              <tr><th>名前</th><td>${child_name}</td></tr>
        </table>


    </div>
</div>

<c:import url="/common/footer.jsp" />

