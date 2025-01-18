<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
			    <li class="breadcrumb-item"><a href="Parents.action">保護者ID入力</a></li>
			    <li class="breadcrumb-item"><a href="ParentsInput.action?parents_id=${parents_id}">保護者情報</a></li>
			    <li class="breadcrumb-item"><span>保護者情報編集</span></li>
			    <li class="breadcrumb-item active" aria-current="page">保護者情報更新完了</li>
			  </ol>
			</nav>
		    </c:when>

		    <c:when test="${user_type == 'P'}">
		    <!-- 保護者パンくずリスト -->
		    <nav aria-label="breadcrumb">
			  <ol class="breadcrumb">
			    <li class="breadcrumb-item"><a href="Menu.action">メニュー</a></li>
			    <li class="breadcrumb-item"><span>保護者情報</span></li>					<!-- ここのリンク戻るときが様子おかしい -->
			    <li class="breadcrumb-item"><span>保護者情報編集</span></li>
			    <li class="breadcrumb-item active" aria-current="page">保護者情報更新完了</li>
			  </ol>
			</nav>

		    </c:when>
		</c:choose>


    <h2 class="title">保護者情報更新完了</h2>
    <p>${message}</p>

		<table class="table table-hover">
              <tr><th>保護者ID</th><td>${parents_id}</td></tr>
              <tr><th>保護者名</th><td>${parents_name}</td></tr>
              <tr><th>パスワード</th><td>${parents_pass}</td></tr>
              <tr><th>住所</th><td>${parents_address}</td></tr>
              <tr><th>電話番号</th><td>${parents_tel}</td></tr>
              <tr><th>メールアドレス1</th><td>${parents_mail1}</td></tr>
              <tr><th>メールアドレス2</th><td>${parents_mail2}</td></tr>
              <tr><th>メールアドレス3</th><td>${parents_mail3}</td></tr>
        </table>


	</div>
	</div>
<c:import url="/common/footer.jsp" />
