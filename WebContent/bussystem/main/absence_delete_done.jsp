<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<c:import url="/common/header.jsp" />

<div class="main">
<div class="con">

	    <!-- パンくずリスト -->
		<nav aria-label="breadcrumb">
		  <ol class="breadcrumb">
		    <li class="breadcrumb-item"><a href="Menu.action">メニュー</a></li>
		    <li class="breadcrumb-item"><a href="AbsenceSelect.action">欠席機能選択</a></li>
		    <li class="breadcrumb-item"><a href="AbsenceConect.action">欠席状況</a></li>
		    <li class="breadcrumb-item"><span>欠席情報削除確認</span></li>
		    <li class="breadcrumb-item active" aria-current="page">欠席情報削除完了</li>
		  </ol>
		</nav>


	<h2 class="title">欠席情報削除完了</h2>

	<!-- エラーメッセージの表示 -->
	<c:if test="${not empty error}">
    	<p style="color: red; font-weight: bold;">${error}</p>
    </c:if>

    <p>欠席情報の削除が完了しました</p>



  </div>
  </div>

<c:import url="/common/footer.jsp" />

