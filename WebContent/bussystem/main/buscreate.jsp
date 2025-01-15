<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<c:import url="/common/header.jsp" />

<div class="main">

<div class="con">

    <h2 class="title">バス新規作成</h2>

    <p>新規登録するバス名を入力してください</p>

	<!-- エラーメッセージの表示 -->
	<c:if test="${not empty error}">
            <p style="color: red; font-weight: bold;">${error}</p>
    </c:if>

    <form action="BusCreateExecute.action" method="post">


        <label>バス名</label>
	        <input type="text" name="bus_name" maxlength="7" style="width:200px;height:25px;"
	            placeholder="7文字以内で入力してください"  />


        <input type="submit" value="送信" class="button-send">
    </form>

   </div>
   </div>
</body>
<c:import url="/common/footer.jsp" />
</html>
