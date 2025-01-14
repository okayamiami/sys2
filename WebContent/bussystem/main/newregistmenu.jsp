<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>

<c:import url="/common/header.jsp" />

<div class="main">

<div class ="con">

<h2 class="title">お知らせ機能選択</h2>

<div class="menu-links">
<a href="NewRegist.action">アカウント新規作成</a>

<a href="BusCreate.action">バス新規作成</a>

</div>

<a href="Menu.action" class="menu-link">
    <img src="../../common/image/home.png" class="icon" alt="メニューアイコン">
    メニュー画面に戻る
</a>


</div>
</div>
</body>
<c:import url="/common/footer.jsp" />
</html>
