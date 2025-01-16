<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<c:import url="/common/header.jsp" />

<div class="main">

<div class="con">


	<h2 class="title">欠席連絡更新</h2>

	<!-- エラーメッセージの表示 -->
	<c:if test="${not empty error}">
    	<p style="color: red; font-weight: bold;">${error}</p>
    </c:if>

    <p>下記情報で欠席連絡更新が完了しました</p>


		<table class="table table-hover">
              <tr><th>欠席ID</th><td>${absence_id}</td></tr>
              <tr><th>名前</th><td>${child_name}</td></tr>
              <tr><th>欠席理由</th><td>${abs_main}</td></tr>
              <tr><th>欠席日</th><td>${absence_date}</td></tr>
			  <tr>
				    <th>欠席状況</th>
				    <td>
				        <c:choose>
				            <c:when test="${abs_is_attend == true}">
				                欠席
				            </c:when>
				            <c:otherwise>
				                出席
				            </c:otherwise>
				        </c:choose>
				    </td>
			  </tr>
        </table>


  </div>
  </div>

<c:import url="/common/footer.jsp" />
