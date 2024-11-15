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
        <form action="ParentsEditExecute.action" method="post">
            <table>
                <tr>
                    <th>ID</th>
                    <th>名前(保護者)</th>
                    <th>住所</th>
                    <th>電話番号</th>
                    <th>メールアドレス１</th>
                    <th>メールアドレス２</th>
                    <th>メールアドレス３</th>
                </tr>

                <c:if test="${not empty user}">
                    <tr>
                        <td>
                    <input type="hidden" name="parents_id" value="${user.parents_id}"/>
                    ${user.parents_id} <!-- IDは表示する -->
                		</td>
                        <td>
                            <input type="text" name="parents_name" value="${user.parents_name}"/>
                        </td>
                        <td>
                            <input type="text" name="parents_address" value="${user.parents_address}"/>
                        </td>
                        <td>
                            <input type="text" name="parents_tel" value="${user.parents_tel}"/>
                        </td>
                        <td>
                            <input type="email" name="parents_mail1" value="${user.parents_mail1}"/>
                        </td>
                        <td>
                            <input type="email" name="parents_mail2" value="${user.parents_mail2}"/>
                        </td>
                        <td>
                            <input type="email" name="parents_mail3" value="${user.parents_mail3}"/>
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

