<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<c:import url="/common/header.jsp" />

<div class="main">

    <div class="con">
    <nav aria-label="breadcrumb">
	  <ol class="breadcrumb">
	    <li class="breadcrumb-item"><a href="Menu.action">メニュー</a></li>
	    <li class="breadcrumb-item"><a href="ManageUser.action">ユーザー情報</a></li>
	</nav>
     <h2 class="title">ユーザー情報</h2>
        <p>情報に変更がある場合は「編集」ボタンから変更を行ってください</p>


   		<form action="ManageEdit.action" method="post">
            <input type="hidden" name="user_id" value="${muinfo.user_id}" />
			<input type="hidden" name="facility_id" value="${muinfo.facility_id}" />

           <button type="submit" class="button-send">編集</button>

        </form>


        <table>
            <c:if test="${empty error}">
            <br>
                <tr>
                    <th>ユーザーID</th>
                    <td>${muinfo.user_id}</td>
                </tr>
                <tr>
                    <th>名前</th>
                    <td>${muinfo.user_name}</td>
                </tr>
                <tr>
                    <th>パスワード</th>
                    <td>*******</td>
                </tr>
                <tr>
                    <th>施設ID</th>
                    <td>${muinfo.facility_id}</td>
                </tr>

            </c:if>
        </table>

    </div>
</div>

<c:import url="/common/footer.jsp" />