<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<c:import url="/common/header.jsp" />

<div class="main">
<div class ="con">

    <!-- パンくずリスト -->
	<nav aria-label="breadcrumb">
	  <ol class="breadcrumb">
	    <li class="breadcrumb-item"><a href="Menu.action">メニュー</a></li>
	    <li class="breadcrumb-item"><a href="InfoMenu.action">お知らせ機能選択</a></li>
	    <li class="breadcrumb-item"><a href="InfoList.action">お知らせ一覧</a></li>
	    <li class="breadcrumb-item"><span>お知らせ詳細</span></li>							<!-- 削除完了されている記事 -->
	    <li class="breadcrumb-item"><span>お知らせ削除確認</span></li>						<!-- 削除完了されている記事 -->
	    <li class="breadcrumb-item active" aria-current="page">お知らせ削除確認 </li>
	  </ol>
	</nav>


<h2 class="title">更新結果</h2>

    <p>${delete_message}</p>
    </div>
    </div>

<c:import url="/common/footer.jsp" />
