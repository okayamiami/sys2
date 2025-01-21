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
		    <li class="breadcrumb-item active" aria-current="page">欠席報告</li>
		  </ol>
		</nav>
	    </c:when>

	    <c:when test="${user_type == 'P'}">
	    <!-- 保護者パンくずリスト -->
	    <nav aria-label="breadcrumb">
		  <ol class="breadcrumb">
		    <li class="breadcrumb-item"><a href="Menu.action">メニュー</a></li>
		    <li class="breadcrumb-item active" aria-current="page">欠席報告</li>
		  </ol>
		</nav>

	    </c:when>
	</c:choose>



	<%
		session.getAttribute("user_type");
		session.getAttribute("user_id");
	%>




    <h2 class="title">欠席報告</h2>
    <p>名前の選択と欠席理由の入力をしてください</p>

    <!-- エラーメッセージの表示 -->
	<c:if test="${not empty error}">
            <p style="color: red; font-weight: bold;">${error}</p>
    </c:if>

    <form action="AbsenceReportExecute.action" method="post">
        <label>名前(子供)</label>
        <select name="child_name">
		<option value="0">--------</option>
	        <c:forEach var="name" items="${cNamelist}">
	            <option value="${name}">${name}</option>
	        </c:forEach>        </select>

        <p></p>

        <label>欠席理由</label>
	        <input type="text" name="abs_main" style="width:400px;height:25px;"
	            placeholder="30文字以内で欠席理由を入力してください" maxlength="30"  />

        <input type="submit" class="button-send" value="送信">
    </form>

   </div>
   </div>

<c:import url="/common/footer.jsp" />

