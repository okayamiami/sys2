<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>

<c:import url="/common/header.jsp" />

<div class="main">
    <c:import url="/common/navi.jsp" />

    <div class="con">
        <h2>保護者情報</h2>
        <form action="ChildUpdateExecute.action" method="post">
            <table>
                <tr>
                  	<th>保護者ID</th>
                	<th>クラス名</th>
                	<th>子供の名前</th>
                	<th>操作</th>
                </tr>

                <c:if test="${not empty CI}">
                    <tr>
                        <td>
                    <input type="hidden" name="parents_id" value="${CI.parents_id}"/>
                    ${CI.parents_id} <!-- IDは表示する -->
                		</td>
                        <td>
                            <input type="text" name="class_set.class_name" value="${class_set.class_name}"/>
                        </td>
                        <td>
                            <input type="text" name="CI.child_name" pattern="\d+" value="${CI.child_name}"/>
                        </td>

                    </tr>
                </c:if>
            </table>

            <button type="submit">保存</button>
            <input type="reset" value="入力内容をキャンセル"/>
        </form>
    </div>
</div>

</body>
<c:import url="/common/footer.jsp" />
</html>

