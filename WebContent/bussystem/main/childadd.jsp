<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>

<c:import url="/common/header.jsp" />

<div class="main">
    <c:import url="/common/navi.jsp" />

    <div class="con">
        <h2>子供情報追加登録</h2>

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
			           pattern="^[^\d０-９]+$"
			           title="数字を含まない名前を入力してください">
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
                <label for="class_id">クラスID:</label>
                <select id="class_id" name="class_id">
                    <c:forEach var="classId" items="${class_set}">
                        <option value="${classId}">${classId}</option>
                    </c:forEach>
                </select>
            </div>

            <div class="form-group">
                <input type="hidden" id="is_attend" name="is_attend" value="true" />
            </div>

            <div class="form-group">
                <input type="hidden" id="facility_id" name="facility_id" value="${facility_id}" />
            </div>

            <div class="form-group">
                <button type="submit">登録</button>
            </div>
        </form>
    </div>
</div>

<c:import url="/common/footer.jsp" />

</body>
</html>
