<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>

<c:import url="/common/header.jsp" />
<div class="main">
<div class="con">

	<nav aria-label="breadcrumb">
	  <ol class="breadcrumb">
	    <li class="breadcrumb-item"><a href="Menu.action">メニュー</a></li>
	    <li class="breadcrumb-item"><a href="InfoMenu.action">お知らせ機能選択</a></li>
	    <li class="breadcrumb-item"><span>お知らせ作成</span></li>
	    <li class="breadcrumb-item active" aria-current="page">お知らせ情報 登録完了</li>
	  </ol>
	</nav>


<h2 class="title">お知らせ投稿完了</h2>

<p>${message}</p>

</div>
</div>
</body>
<c:import url="/common/footer.jsp" />
</html>
