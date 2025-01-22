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
    <li class="breadcrumb-item"><a href="NewRegistMenu.action">新規登録メニュー</a></li>
    <li class="breadcrumb-item active" aria-current="page">バス新規登録</li>
  </ol>
</nav>


    <h2 class="title">バス新規登録</h2>

    <p>新規登録するバス名を入力してください</p>

	<!-- エラーメッセージの表示 -->
	<c:if test="${not empty error}">
            <p style="color: red; font-weight: bold;">${error}</p>
    </c:if>

    <form action="BusCreateExecute.action" method="post">


        <label>バス名</label>
	        <input type="text" name="bus_name" maxlength="20" style="width:200px;height:25px;"
	            placeholder="20文字以内で入力してください"  />


        <input type="submit" value="登録" class="button-send">
    </form>

   </div>
   </div>

<c:import url="/common/footer.jsp" />

