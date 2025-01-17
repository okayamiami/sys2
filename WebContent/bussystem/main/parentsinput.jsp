<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<c:import url="/common/header.jsp" />

<div class="main">
    <div class="con">

	<!-- パンくずリスト -->
	<nav aria-label="breadcrumb">
	  <ol class="breadcrumb">
	    <li class="breadcrumb-item"><a href="Menu.action">メニュー</a></li>
	    <li class="breadcrumb-item active" aria-current="page">保護者ID入力</li>
	  </ol>
	</nav>

    	 <h2 class="title">保護者ID入力</h2>
    	 <p>保護者IDを入力してください</p>

        <!-- 保護者ID入力フォーム -->
        <form action="ParentsInput.action" method="post">
        	<div style="color: red;">${ errors.get("errorMessage") }</div>
            <label for="parents_id">保護者ID:</label>
            <input type="text" id="parents_id" name="parents_id" required/>


		          <button type="submit" class="button-send">検索</button>
        </form>

    </div>
</div>

<c:import url="/common/footer.jsp" />
