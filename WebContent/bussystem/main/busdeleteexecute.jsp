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
	    <li class="breadcrumb-item"><a href="FacilityInfoMenu.action">施設・バス情報</a></li>
	    <li class="breadcrumb-item"><a href="BusInfo.action">バス情報</a></li>
	    <li class="breadcrumb-item"><a href="BusDelete.action">バス削除確認</a></li>
	    <li class="breadcrumb-item active" aria-current="page">バス削除完了</li>
	  </ol>
	</nav>


<h2 class="title">更新結果</h2>

    <p>${delete_message}</p>
    </div>
    </div>

<c:import url="/common/footer.jsp" />