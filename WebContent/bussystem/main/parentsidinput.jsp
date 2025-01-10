<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
</head>
<body>

<c:import url="/common/header.jsp" />

<div class="main">
    <c:import url="/common/navi.jsp" />

    <div class="con">
        <!-- 保護者ID入力フォーム -->
        <h2>子供情報</h2>
        <form action="ParentsIDInput.action" method="post">

        <!-- 入力されたIDが一致しないときのエラー文 -->
        <div style="color: red;">${ errors.get("errorMessage") }</div>


        	<!-- 保護者IDを入力 -->
            <label for="parents_id">保護者ID:</label>
            <input type="text" id="parents_id" name="parents_id"/>
               <div class="button-save">
		          <button type="submit">検索</button>
		       </div>

             <input type="hidden" id="parents_name" name="parents_name" value="${parents_name}" />
        </form>
        <a href="menu.jsp" class="menu-link">メニューに戻る</a>
    </div>
</div>

</body>
</html>
