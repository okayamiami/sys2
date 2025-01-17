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
	    <li class="breadcrumb-item"><a href="ChildList.action">名簿情報一覧</a></li>
	    <li class="breadcrumb-item active" aria-current="page">子供詳細情報</li>
	  </ol>
	</nav>


        <h2 class="title">子供詳細情報</h2>

        <!-- エラーメッセージの表示 -->
        <c:if test="${not empty error}">
            <p style="color: red; font-weight: bold;">${error}</p>
        </c:if>

        <!-- 情報表示 -->
        <c:if test="${empty error}">
            <table>
                <tr><th>子供ID</th><td>${child_id}</td></tr>
                <tr><th>名前（子供）</th><td>${child_name}</td></tr>
                <tr><th>クラス</th>
                    <td>
                        <c:forEach var="classItem" items="${classlist}">
                            <c:if test="${class_id eq classItem.class_id}">
                                ${classItem.class_name}
                            </c:if>
                        </c:forEach>
                    </td>
                </tr>
                <tr><th>保護者ID</th><td>${parents_id}</td></tr>
                <tr><th>名前（保護者）</th><td>${parents_name}</td></tr>
                <tr><th>住所</th><td>${parents_address}</td></tr>
                <tr><th>電話番号</th><td>${parents_tel}</td></tr>
                <tr><th>メールアドレス１</th><td>${parents_mail1}</td></tr>
                <tr><th>メールアドレス２</th><td>${parents_mail2}</td></tr>
                <tr><th>メールアドレス３</th><td>${parents_mail3}</td></tr>
            </table>
        </c:if>


    </div>
</div>

<c:import url="/common/footer.jsp" />
