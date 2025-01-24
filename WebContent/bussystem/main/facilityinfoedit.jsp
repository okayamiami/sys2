<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:import url="/common/header.jsp" />
<div class="main">
<div class="con">

    <!-- パンくずリスト -->
	<nav aria-label="breadcrumb">
	  <ol class="breadcrumb">
	    <li class="breadcrumb-item"><a href="Menu.action">メニュー</a></li>
	    <li class="breadcrumb-item"><a href="FacilityInfo.action">施設情報</a></li>
	    <li class="breadcrumb-item active" aria-current="page">施設情報編集</li>
	  </ol>
	</nav>


<h2 class="title">施設情報編集</h2>
<p>変更箇所の入力をして変更ボタンを押してください</p>
<form action="FacilityInfoEditExecute.action" method="post">
	<button type="submit" class="button-send">保存</button>
    <table>
    <c:if test="${not empty fc}">
        <tr><th>施設ID</th><td>
		            <input type="hidden" name="facility_id" value="${fc.facility_id}"/>
		            ${fc.facility_id} <!-- IDは表示する -->
        		</td></tr>
        <tr><th>施設名</th><td>
                    <input type="text" name="facility_name" pattern="^[\p{L}\d０-９]+$"  value="${fc.facility_name}" required title="記号を含めないでください"/>
                </td></tr>
        <tr><th>住所</th><td>
                    <input type="text" name="facility_address"
					    pattern="^[\u4E00-\u9FFF\u3040-\u309F\u30A0-\u30FF0-9 ー\-、,]+$"
					    value="${fc.facility_address}"
					    required
					    title="住所は漢字、ひらがな、カタカナ、数字、一部の記号（-、ー、,、スペース）を使用してください"/>
                </td></tr>
        <tr><th>電話番号</th><td>
                    <input type="tel" name="facility_tel"
							pattern="^\d{10,11}$"
							value="${fc.facility_tel}"
							required
							title="10～11桁の数字で入力してください（例: 09012345678）"/>
                </td></tr>
        <tr><th>メールアドレス</th><td>
                	<input type="email" name="facility_mail" value="${fc.facility_mail}" required/>
                </td></tr>
        <tr>
                        <th>アプリパスワード</th>
                        <td>
						    <input type="text" name="facility_app_password"
						           required minlength="6" maxlength="20"
						           placeholder="アプリパスワードを入力"
						           title="英数字6～20文字で入力してください。"
						           pattern="[A-Za-z\d]{6,20}"
						           value="${fn:trim(fc.facility_app_password)}" />
						</td>
                    </tr>
        <tr><th>プラン</th><td>
				    <!-- 初期値をhiddenにセット -->
				    <input type="hidden" name="facility_plan" id="facility_plan_hidden" value="${fc.facility_plan}" />

				    <!-- 現在のプランを表示 -->
				    現在のプラン: <c:if test="${fc.facility_plan}">S</c:if><c:if test="${not fc.facility_plan}">L</c:if>
				</td></tr>
         </c:if>
    </table>


</form>
</div>
</div>
<c:import url="/common/footer.jsp" />
