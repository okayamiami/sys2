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
        <form action="NewInfoExecute.action" method="post">
            <table>
                <tr>
                    <th>ID</th>
                    <th>名前</th>
                    <th>パスワード</th>
                </tr>

                <c:if test="${not empty user}">
                    <tr>
                        <td>
                    <input type="hidden" name="user_id" value="${user.user_id}"/>
                    ${user.user_id} <!-- IDは表示する -->
                		</td>
                        <td>
                            <input type="text" name="user_name" value="${user.user_name}"/>
                        </td>
                        <td>
                            <input type="text" name="user_pass" value="${user.user_pass}"/>
                        </td>
                    </tr>
                </c:if>
            </table>

            <button type="submit">保存</button>
        </form>
    </div>
</div>

</body>
<c:import url="/common/footer.jsp" />
</html>