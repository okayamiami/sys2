<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="/common/header.jsp" />
<div class="main">
<c:import url="/common/navi.jsp" />
<div class="con">
<form action="FacilityInfoEditExecute.action" method="post">
    <table>
        <tr>
            <th>施設ID</th>
            <th>施設名</th>
            <th>住所</th>
            <th>電話番号</th>
            <th>メールアドレス</th>
            <th>プラン切り替え</th>
        </tr>

        <c:if test="${not empty fc}">
            <tr>
                <td>
		            <input type="hidden" name="facility_id" value="${fc.facility_id}"/>
		            ${fc.facility_id} <!-- IDは表示する -->
        		</td>
                <td>
                    <input type="text" name="facility_name" pattern="^[^0-9]*$"  value="${fc.facility_name}" required title="数字を含めないでください"/>
                </td>
                <td>
                    <input type="text" name="facility_address"
           					pattern="^[\u4E00-\u9FFF\u3040-\u309F\u30A0-\u30FF0-9\s\-ー,、]*$"
           					value="${fc.facility_address}"
           					required
           					title="住所は漢字、ひらがな、カタカナ、数字、一部の記号（-、ー、,）を使用してください"/>
                </td>
                <td>
                    <input type="tel" name="facility_tel"
							pattern="^\d{10,11}$"
							value="${fc.facility_tel}"
							required
							title="10～11桁の数字で入力してください（例: 09012345678）"/>
                </td>
                <td>
                	<input type="email" name="facility_mail" value="${fc.facility_mail}" required/>
                </td>
                <td>
                	<!-- 隠しフィールドに反転後の値をデフォルト設定 -->
                	<input type="hidden" name="facility_plan" value="${fc.facility_plan ? 'false' : 'true'}" />

                <!-- チェックボックスをデフォルトで外す -->
                	<input type="checkbox" id="facility_plan_toggle"
                    onclick="document.getElementsByName('facility_plan')[0].value = this.checked ? '${fc.facility_plan ? 'false' : 'true'}' : '${fc.facility_plan ? 'true' : 'false'}';">
                </td>
            </tr>
        </c:if>
    </table>

    <button type="submit">保存</button>
</form>

</div>
</div>
<c:import url="/common/footer.jsp" />
</html>