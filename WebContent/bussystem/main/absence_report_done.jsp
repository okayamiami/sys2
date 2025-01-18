<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<c:import url="/common/header.jsp" />

<div class="main">
<div class="con">

		<!-- アカウント区分ごとの表示 -->
       	<c:choose>
	    <c:when test="${user_type == 'M' || user_type == 'T'}">
	    <!-- 管理者・先生パンくずリスト -->
		<nav aria-label="breadcrumb">
		  <ol class="breadcrumb">
		    <li class="breadcrumb-item"><a href="Menu.action">メニュー</a></li>
		    <li class="breadcrumb-item"><a href="AbsenceSelect.action">欠席機能選択</a></li>
		    <li class="breadcrumb-item"><span>欠席報告</span></li>
		    <li class="breadcrumb-item active" aria-current="page">欠席報告完了</li>
		  </ol>
		</nav>
	    </c:when>

	    <c:when test="${user_type == 'P'}">
	    <!-- 保護者パンくずリスト -->
	    <nav aria-label="breadcrumb">
		  <ol class="breadcrumb">
		    <li class="breadcrumb-item"><a href="Menu.action">メニュー</a></li>
		    <li class="breadcrumb-item"><span>欠席報告</span></li>
		    <li class="breadcrumb-item active" aria-current="page">欠席報告完了</li>
		  </ol>
		</nav>

	    </c:when>
	</c:choose>


	<h2 class="title">欠席報告完了</h2>

	<!-- エラーメッセージの表示 -->
	<c:if test="${not empty error}">
    	<p style="color: red; font-weight: bold;">${error}</p>
    </c:if>

    <p>下記情報で欠席報告の登録が完了しました</p>


		<table class="table table-hover">
              <tr><th>欠席ID</th><td>${abs_id}</td></tr>
              <tr><th>名前</th><td>${child_name}</td></tr>
              <tr><th>欠席理由</th><td>${abs_main}</td></tr>
              <tr><th>欠席日</th><td>${absence_date}</td></tr>
        </table>



  </div>
  </div>

<c:import url="/common/footer.jsp" />

