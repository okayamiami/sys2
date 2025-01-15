<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="/common/header.jsp" />
<div class="main">

<div class="con">

<h2 class="title">施設情報編集</h2>
<p>変更箇所の入力をして変更ボタンを押してください</p>
<form action="FacilityInfoEditExecute.action" method="post">
	<button type="submit" class="button-send">変更</button>
    <table>
    <c:if test="${not empty fc}">
        <tr><th>施設ID</th><td>
		            <input type="hidden" name="facility_id" value="${fc.facility_id}"/>
		            ${fc.facility_id} <!-- IDは表示する -->
        		</td></tr>
        <tr><th>施設名</th><td>
                    <input type="text" name="facility_name" pattern="^[^0-9]*$"  value="${fc.facility_name}" required title="数字を含めないでください"/>
                </td></tr>
        <tr><th>住所</th><td>
                    <input type="text" name="facility_address"
           					pattern="^[\u4E00-\u9FFF\u3040-\u309F\u30A0-\u30FF0-9\s\-ー,、]*$"
           					value="${fc.facility_address}"
           					required
           					title="住所は漢字、ひらがな、カタカナ、数字、一部の記号（-、ー、,）を使用してください"/>
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
        <tr><th>アプリパスワード</th><td>
	                <input type="password" id="facility_app_password" name="facility_app_password" value="${fc.facility_app_password}" maxlength="20" required title="20文字以内で入力してください"/>
	                <button type="button" onclick="togglePassword()">表示/非表示</button>
	            </td></tr>
        <tr><th>プラン選択</th><td>
				    <!-- 初期値をhiddenにセット -->
				    <input type="hidden" name="facility_plan" id="facility_plan_hidden" value="${fc.facility_plan}" />

				    <!-- ラジオボタンでプランを選択 -->
				    <label>
				        <input type="radio" name="facility_plan_radio" value="true"
				               <c:if test="${fc.facility_plan}">checked</c:if>
				               onclick="document.getElementById('facility_plan_hidden').value = 'true';">
				        S（スタンダード）
				    </label>
				    <label>
				        <input type="radio" name="facility_plan_radio" value="false"
				               <c:if test="${not fc.facility_plan}">checked</c:if>
				               onclick="document.getElementById('facility_plan_hidden').value = 'false';">
				        L（ライト）
				    </label>

				    <!-- 現在のプランを表示 -->
				    現在のプラン: <c:if test="${fc.facility_plan}">S</c:if><c:if test="${not fc.facility_plan}">L</c:if>
				</td></tr>
         </c:if>
    </table>
    <script>
    // パスワードの表示/非表示を切り替える関数
    function togglePassword() {
        var passwordField = document.getElementById("facility_app_password");
        var currentType = passwordField.type;

        // 現在のタイプがpasswordならtextに、textならpasswordに変更
        if (currentType === "password") {
            passwordField.type = "text";
        } else {
            passwordField.type = "password";
        }
    }
	</script>


</form>
</div>
</div>
<c:import url="/common/footer.jsp" />
</html>