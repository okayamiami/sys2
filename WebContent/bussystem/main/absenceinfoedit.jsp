<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<c:import url="/common/header.jsp" />

<div class="main">
<div class="con">

	<h2 class="title">欠席連絡更新</h2>
	<p>欠席理由の変更または欠席状態の変更をして保存ボタンを押してください</p>
<form action="AbsenceInfoEditExecute.action" method="post">
	<button type="submit" class="button-send">保存</button>
    <table>
    <c:if test="${not empty abs}">
        <tr><th>欠席ID</th><td>
		            <input type="hidden" name="absence_id" value="${abs.absence_id}"/>
		            ${abs.absence_id} <!-- IDは表示する -->
        		</td></tr>
        <tr><th>欠席理由</th><td>
                    <input type="text" name="absence_main"  value="${abs.absence_main}" maxlength="30"
                    style="width:400px;height:25px;" />
                </td></tr>
        <tr><th>子供</th><td>
            				<c:forEach var="ChildItem" items="${chiidlist}">
						        <!-- eq を使用して比較 -->
						        <c:if test="${ChildItem.child_id eq abs.child_id}">
						            <input type="hidden" name="absence_name" value="${abs.child_id}"/>
		            				${ChildItem.child_name}<!-- 子どもの名前を表示する -->
						        </c:if>
						    </c:forEach>
                </td></tr>
        <tr><th>欠席日</th><td>
        			<input type="hidden" name="absence_date" value="${abs.absence_date}"/>
		            ${abs.absence_date} <!-- 欠席日は表示する -->
                </td></tr>

        <tr><th>欠席</th><td>
				<%-- abs_is_attendがtrueの場合checkedを追記 --%>
				<input type="checkbox" name="abs_is_attend"
					<c:if test="${abs.abs_is_attend}">checked</c:if>>
	            </td></tr>

	            <input type="hidden" name="facility_id" value="${abs.facility_id}"/>


         </c:if>
    </table>


</form>

</div>
</div>

<c:import url="/common/footer.jsp" />
