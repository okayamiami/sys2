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
	    <li class="breadcrumb-item"><a href="QrMenu.action">QR機能選択</a></li>
	    <li class="breadcrumb-item"><a href="QrCreate.action">QRコード作成</a></li>
	    <li class="breadcrumb-item active" aria-current="page">QRコード画像</li>
	  </ol>
	</nav>


<h2 class="title">QRコード画像</h2>
<p>作成が完了しました</p>
<p>子供名:${child_name}</p>
<!-- QRコード画像を表示 -->
    <div>
        <img src="${qrImagePath}" alt="QR Code" width="500" height="500" />
    </div>

</div>
</div>


<c:import url="/common/footer.jsp" />
