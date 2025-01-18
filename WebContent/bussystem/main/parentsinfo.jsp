<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<c:import url="/common/header.jsp" />

<div class="main">

    <div class="con">

		<!-- アカウント区分ごとの表示 -->
    	<c:choose>
		    <c:when test="${user_type == 'M'}">
		    <!-- 管理者パンくずリスト -->
			<nav aria-label="breadcrumb">
			  <ol class="breadcrumb">
			    <li class="breadcrumb-item"><a href="Menu.action">メニュー</a></li>
			    <li class="breadcrumb-item"><a href="Parents.action">保護者ID入力</a></li>
			    <li class="breadcrumb-item active" aria-current="page">保護者情報</li>
			  </ol>
			</nav>
		    </c:when>

		    <c:when test="${user_type == 'P'}">
		    <!-- 保護者パンくずリスト -->
		    <nav aria-label="breadcrumb">
			  <ol class="breadcrumb">
			    <li class="breadcrumb-item"><a href="Menu.action">メニュー</a></li>
			    <li class="breadcrumb-item active" aria-current="page">保護者情報</li>
			  </ol>
			</nav>

		    </c:when>
		</c:choose>





        <!-- 保護者情報の表示 -->
        <h2 class="title">保護者情報</h2>
        <p>情報に変更がある場合は「編集」ボタンから変更を行ってください</p>


   		<form action="ParentsEdit.action" method="post">
            <input type="hidden" name="parents_id" value="${userinfo.parents_id}" />


           <button type="submit" class="button-send">編集</button>

        </form>


        <table>
            <c:if test="${empty error}">
            <br>
                <tr>
                    <th>保護者ID</th>
                    <td>${userinfo.parents_id}</td>
                </tr>
                <tr>
                    <th>名前（保護者）</th>
                    <td>${userinfo.parents_name}</td>
                </tr>
                <tr>
                    <th>パスワード</th>
                    <td>*******</td>
                </tr>
                <tr>
                    <th>住所</th>
                    <td>${userinfo.parents_address}</td>
                </tr>
                <tr>
                    <th>電話番号</th>
                    <td>${userinfo.parents_tel}</td>
                </tr>
                <tr>
                    <th>メールアドレス１</th>
                    <td>${userinfo.parents_mail1}</td>
                </tr>
                <tr>
                    <th>メールアドレス２</th>
                    <td>${userinfo.parents_mail2}</td>
                </tr>
                <tr>
                    <th>メールアドレス３</th>
                    <td>${userinfo.parents_mail3}</td>
                </tr>
            </c:if>
        </table>


    </div>
</div>

<c:import url="/common/footer.jsp" />


