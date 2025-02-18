<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<c:import url="/common/header.jsp" />

<div class="main">
<div class ="con">

    	<!-- アカウント区分ごとの表示 -->
       	<c:choose>
	    <c:when test="${user_type == 'M'}">
	    <!-- 管理者パンくずリスト -->
		<nav aria-label="breadcrumb">
		  <ol class="breadcrumb">
		    <li class="breadcrumb-item"><a href="Menu.action">メニュー</a></li>
		    <li class="breadcrumb-item"><a href="ChildInfo.action">保護者ID入力（子供情報）</a></li>
		    <li class="breadcrumb-item"><a href="ParentsIDInput.action?parents_id=${parents_id}">子供情報一覧</a></li>
		    <li class="breadcrumb-item"><span>新規子供情報入力</span></li>
		    <li class="breadcrumb-item active" aria-current="page">新規子供情報登録完了</li>
		  </ol>
		</nav>
	    </c:when>

	    <c:when test="${user_type == 'P'}">
	    <!-- 保護者パンくずリスト -->
	    <nav aria-label="breadcrumb">
		  <ol class="breadcrumb">
		    <li class="breadcrumb-item"><a href="Menu.action">メニュー</a></li>
		    <li class="breadcrumb-item"><a href="ChildInfo.action">子供情報一覧</a></li>
		    <li class="breadcrumb-item"><span>新規子供情報入力</span></li>
		    <li class="breadcrumb-item active" aria-current="page">新規子供情報登録完了</li>
		  </ol>
		</nav>

	    </c:when>
	</c:choose>


    <h2 class="title">新規子供情報登録完了</h2>
    <p>${message}</p>

	    <table class="table table-hover">
	   	    <tr>
	                  <th>子供ID</th>
	                  <th>クラス名</th>
	                  <th>名前（子供）</th>
	       </tr>
	    	<tr>
					<input type="hidden" id="parents_id" name="parents_id" value="${parents_id}" />
                    <td>${child_id}</td>
                    <td>${class_name}</td>
                    <td>${child_name}</td>
           </tr>
        </table>


    </div>
    </div>
<c:import url="/common/footer.jsp" />