<%-- 子供一覧JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">




	<h2>名簿一覧</h2>

	<form method="get">
		<label> 子供ID </label>
		<select name="f1">
			<option value="0">--------</option>
			<c:forEach var="childId" items="${child_id_set}">
				<%-- 現在のchildIdと選択されていたf1が一致していた場合selectedを追記 --%>
				<option value="${childId}" <c:if test="${childId==f1}">selected</c:if>>${childId}</option>
			</c:forEach>
		</select>

		<label> 名前 </label>
		<select name="f2">
			<option value="0">--------</option>
			<c:forEach var="childName" items="${child_name_set}">
				<%-- 現在のchildNameと選択されていたf2が一致していた場合selectedを追記 --%>
				<option value="${childName}" <c:if test="${childName==f2}">selected</c:if>>${childName}</option>
			</c:forEach>
		</select>

		<label> クラス </label>
		<select name="f3">
			<option value="0">--------</option>
			<c:forEach var="className" items="${class_name_set}">
				<%-- 現在のclassNameと選択されていたf2が一致していた場合selectedを追記 --%>
				<option value="${className}" <c:if test="${className==f3}">selected</c:if>>${className}</option>
			</c:forEach>
		</select>


		<label>欠席
			<%-- パラメーターf4が存在している場合checkedを追記 --%>
			<input type="checkbox" name="f4" value="t"
			<c:if test="${!empty f4}">checked</c:if> />
		</label>

		<button>絞込み</button>

		<div>${errors.get("f1")}</div>
	</form>

	<c:choose>
		<c:when test="${childs.size()>0}">
			<div>検索結果：${childs.size()}件</div>

			<table class="table table-hover">
				<tr>
					<th>子供ID</th>
					<th>名前</th>
					<th>クラス</th>
					<th class="text-center">出欠状況</th>

				</tr>
				<c:forEach var="child" items="${childs}">
					<tr>
						<td>${child.child_id}</td>
						<td>${child.child_name}</td>
						<td>${child.class_id}</td>
						<td class="text-center">
							<%-- 欠席フラグがたっている場合「○」それ以外は「×」を表示 --%>
							<c:choose>
								<c:when test="${child.Abs_is_attend()}">
									○
								</c:when>
								<c:otherwise>
									×
								</c:otherwise>
							</c:choose>
						</td>
					</tr>
				</c:forEach>
			</table>
		</c:when>
		<c:otherwise>
			<div>子供情報が存在しませんでした</div>
		</c:otherwise>
	</c:choose>

</div>

</html>