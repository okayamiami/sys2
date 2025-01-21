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
		    <li class="breadcrumb-item"><a href="AbsenceConect.action">欠席連絡</a></li>
		    <li class="breadcrumb-item active" aria-current="page">欠席情報削除確認</li>
		  </ol>
		</nav>



	<h2 class="title">欠席情報削除確認</h2>

	<!-- エラーメッセージの表示 -->
	<c:if test="${not empty error}">
    	<p style="color: red; font-weight: bold;">${error}</p>
    </c:if>

    <p>本当に削除してもよろしいですか？</p>
		<form action="AbsenceInfoDeleteExecute.action" method="get">
	        <input type="hidden" name="absenceId" value="${absence_id}">
	        <input type="hidden" name="facilityId" value="${facility_id}">
	        <button type="submit" class="button-send">削除</button>
	    </form>

		<table class="table table-hover">
              <tr><th>欠席ID</th><td>${absence_id}</td></tr>
              <tr><th>名前</th><td>${child_name}</td></tr>
              <tr><th>欠席理由</th><td>${abs_main}</td></tr>
              <tr><th>欠席日</th><td>${abs_date}</td></tr>
        </table>



  </div>
  </div>

<c:import url="/common/footer.jsp" />

