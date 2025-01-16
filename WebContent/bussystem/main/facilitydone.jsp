<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="/common/header.jsp" />
<div class="main">

<div class="con">
<h2 class="title">施設情報変更完了</h2>
<p>以下情報に施設情報を変更しました</p>

	<table class="table table-hover">
             <tr><th>施設ID</th><td>${facility_id}</td></tr>
             <tr><th>施設名</th><td>${facility_name}</td></tr>
             <tr><th>住所</th><td>${facility_address}</td></tr>
             <tr><th>電話番号</th><td>${facility_tel}</td></tr>
             <tr><th>メールアドレス</th><td>${facility_mail}</td></tr>
             <tr><th>パスワード</th><td>${facility_app_password}</td></tr>
             <tr>
             	<th>プラン情報</th>
             	<td>
             		<c:choose>
				            <c:when test="${facility_plan == true}">
				                S（スタンダード）
				            </c:when>
				            <c:otherwise>
				                L（ライト）
				            </c:otherwise>
				     </c:choose>
             	</td>
             </tr>

    </table>


</div>
</div>

<c:import url="/common/footer.jsp" />
