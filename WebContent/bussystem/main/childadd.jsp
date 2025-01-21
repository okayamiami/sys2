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
		    <li class="breadcrumb-item"><a href="ChildInfo.action">保護者ID入力（子供情報）</a></li>
		    <li class="breadcrumb-item"><a href="ParentsIDInput.action?parents_id=${parents_id}">子供情報一覧</a></li>
		    <li class="breadcrumb-item active" aria-current="page">新規子供情報入力</li>
		  </ol>
		</nav>
	    </c:when>

	    <c:when test="${user_type == 'P'}">
	    <!-- 保護者パンくずリスト -->
	    <nav aria-label="breadcrumb">
		  <ol class="breadcrumb">
		    <li class="breadcrumb-item"><a href="Menu.action">メニュー</a></li>
		    <li class="breadcrumb-item"><a href="ChildInfo.action">子供情報一覧</a></li>
		    <li class="breadcrumb-item active" aria-current="page">新規子供情報入力</li>
		  </ol>
		</nav>

	    </c:when>
	</c:choose>


        <h2 class="title">新規子供情報入力</h2>

        <form action="ChildAddExecute.action" method="post">
            <div class="form-group">
                <label for="child_id">子供ID:</label>
                <!-- 自動生成された child_id を表示 -->
                <input type="hidden" id="child_id" name="child_id" value="${child_id}" readonly>
                <span>${child_id}</span>
			            </div>
			<div class="form-group">
			    <label for="child_name">子供の名前:</label>
			<input type="text" id="child_name" name="child_name" required
		       pattern="^[\p{L}]+$"
		       title="数字を含まない名前を入力してください"
		       maxlength="10">

			</div>

            <div class="form-group">
                <input type="hidden" id="parents_id" name="parents_id" value="${parents_id}" />
            </div>
            <div class="form-group">
                <label for="parents_name">保護者の名前:</label>
                <!-- 保護者名を読み取り専用で表示 -->
                <span>${parents_name}</span>
                <!-- 保護者名をhiddenフィールドで送信 -->
                <input type="hidden" id="parents_name" name="parents_name" value="${parents_name}" />
            </div>

           <div class="form-group">
                <label for="class_name">クラス名:</label>
              <select id="class_name" name="class_name">
				    <c:forEach var="className" items="${class_set}">
				        <option value="${className}">${className}</option>
				    </c:forEach>
				</select>

            </div>

            <div class="form-group">
                <input type="hidden" id="is_attend" name="is_attend" value="true" />
            </div>

            <div class="form-group">
                <input type="hidden" id="facility_id" name="facility_id" value="${facility_id}" />
            </div>

		    <button type="submit" class="button-send">登録</button>

        </form>

    </div>
</div>

<c:import url="/common/footer.jsp" />
