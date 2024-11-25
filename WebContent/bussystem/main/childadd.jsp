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
                <input type="text" id="child_id" name="child_id" required>
            </div>
            <div class="form-group">
                <label for="child_name">子供の名前:</label>
                <input type="text" id="child_name" name="child_name" required>
            </div>
            <div class="form-group">
                <label for="parents_id">保護者ID:</label>
                <input type="hidden" id="parents_id" name="parents_id" value="${parents_id}" />
                <span>${parents_id}</span> <!-- 保護者IDを表示 -->
            </div>
            <div class="form-group">
                <label for="class_id">クラスID:</label>
                <input type="text" id="class_id" name="class_id" required>
            </div>
            <div class="form-group">
            
                <label for="is_attend">出席中:</label>
                <select id="is_attend" name="is_attend" required>
                    <option value="true">はい</option>
                    <option value="false">いいえ</option>
                </select>
                
                <input type="hidden" id="is_attend" name="is_attend" value="true" />
            </div>
            <div class="form-group">
                <label for="facility_id">施設ID:</label>
                <input type="text" id="facility_id" name="facility_id" required>
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
