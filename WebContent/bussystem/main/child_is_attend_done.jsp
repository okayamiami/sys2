<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
		    <li class="breadcrumb-item"><a href="AbsenceConect.action">子供情報一覧</a></li>
		    <li class="breadcrumb-item"><span>在籍状況変更</span></li>
		    <li class="breadcrumb-item active" aria-current="page">在籍状況変更完了</li>
		  </ol>
		</nav>

		<h2 class="title">在籍状況変更完了</h2>

		<!-- エラーメッセージの表示 -->
		<c:if test="${not empty error}">
	    	<p style="color: red; font-weight: bold;">${error}</p>
	    </c:if>

	    <p>在籍情報の更新が完了しました</p>
	    </c:when>

	    <c:when test="${user_type == 'P'}">
	    <!-- 保護者パンくずリスト -->
	    <nav aria-label="breadcrumb">
		  <ol class="breadcrumb">
		    <li class="breadcrumb-item"><a href="Menu.action">メニュー</a></li>
		    <li class="breadcrumb-item"><a href="AbsenceConect.action">子供情報一覧</a></li>
		    <li class="breadcrumb-item"><span>在籍状況変更</span></li>
		    <li class="breadcrumb-item active" aria-current="page">子供情報削除完了</li>
		  </ol>
		</nav>

		<h2 class="title">子供情報削除完了</h2>

		<!-- エラーメッセージの表示 -->
		<c:if test="${not empty error}">
	    	<p style="color: red; font-weight: bold;">${error}</p>
	    </c:if>

	    <p>子供情報の削除が完了しました</p>


	    </c:when>
	</c:choose>






  </div>
  </div>

<c:import url="/common/footer.jsp" />

