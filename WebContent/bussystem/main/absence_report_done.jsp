<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<c:import url="/common/header.jsp" />

<div class="main">

<div class="con">


	<h2 class="title">欠席報告</h2>

	<!-- エラーメッセージの表示 -->
	<c:if test="${not empty error}">
    	<p style="color: red; font-weight: bold;">${error}</p>
    </c:if>

    <p>欠席報告が完了しました</p>
    <p></p>


  </div>
  </div>

<c:import url="/common/footer.jsp" />

