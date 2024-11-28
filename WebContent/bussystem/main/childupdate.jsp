<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>

<!-- ヘッダーのインクルード -->
<c:import url="/common/header.jsp" />

<div class="main">
    <!-- ナビゲーションのインクルード -->
    <c:import url="/common/navi.jsp" />

    <div class="con">
        <h2>子供情報</h2>

        <!-- フォームの開始 -->
        <form action="ChildUpdateExecute.action" method="post">
            <table>
                <tr>
                    <th>保護者ID</th>
                    <th>クラス名</th>
                    <th>子供の名前</th>
                </tr>

                <!-- childInfoが存在すれば、その情報を表示 -->
                <c:if test="${not empty childInfo}">
                    <tr>
                        <!-- 保護者ID -->
                        <td>
                            <input type="hidden" name="parents_id" value="${childInfo.parents_id}" />
                            ${childInfo.parents_id} <!-- IDを表示 -->
                        </td>

                        <!-- クラス情報をプルダウン形式で表示 -->
                        <td>
                            <select name="class_name">
                                <c:forEach var="classOption" items="${class_set}">
                                    <option value="${classOption.class_name}">
                                        ${classOption.class_name}
                                    </option>
                                </c:forEach>
                            </select>
                        </td>

                        <!-- 子供の名前 -->
                        <td>
                             <input type="text" id="child_name" name="child_name" required
					           pattern="^[^\d０-９]+$" value="${childInfo.child_name}"
					           title="数字を含まない名前を入力してください"> <!-- child_nameを送信 -->
                        	<input type="hidden" name="child_id" value="${childInfo.child_id}" /> <!-- child_idを送信 -->
                        </td>
                    </tr>
                </c:if>
            </table>

            <!-- 保存ボタンとリセットボタン -->
            <button type="submit">保存</button>
            <input type="reset" value="入力内容をキャンセル"/>
        </form>
        <!-- フォームの終了 -->
    </div>
</div>

<!-- フッターのインクルード -->
<c:import url="/common/footer.jsp" />

</body>
</html>
