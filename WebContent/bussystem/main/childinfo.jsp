<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>

<c:import url="/common/header.jsp" />

<div class="main">
    <c:import url="/common/navi.jsp" />

    <div class="con">
        <a href="menu.jsp">戻る</a>

        <!-- 子供情報テーブル -->
        <table>
            <tr>
                <th>保護者ID</th>
                <th>クラス名</th>
                <th>子供の名前</th>
                <th>操作</th>
            </tr>
            <c:forEach var="child" items="${userCI}">
                <tr>
                    <td>${child.parents_id}</td>
                    <td>
                        <!-- クラスIDに対応するクラス名を表示 -->
                        <c:forEach var="classCd" items="${class_set}">
                            <c:if test="${classCd.class_id == child.class_id}">
                                ${classCd.class_name}
                            </c:if>
                        </c:forEach>
                    </td>
                    <td>${child.child_name}</td>
                    <td>
                        <!-- 編集ボタン -->
                        <form action="ChildUpdate.action" method="post" style="display:inline;">
                            <input type="hidden" name="parents_id" value="${child.parents_id}" />
                            <!-- 子供のchild_idを送信する隠しフィールド -->
                            <input type="hidden" name="child_id" value="${child.child_id}" />
                            <button type="submit">更新</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>

        <!-- 追加ボタン -->
        <c:if test="${not empty parents_id}">
            <form action="ChildAdd.action" method="post" style="display:inline;">
                <input type="hidden" name="parents_id" value="${parents_id}" />
                <button type="submit">新規登録</button>
            </form>
        </c:if>

    </div>
</div>

</body>
<c:import url="/common/footer.jsp" />
</html>
