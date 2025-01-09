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
                <c:if test="${not empty userinfo}">
                    <tr>
                        <th>ID</th>
                        <td>
                            <input type="hidden" name="parents_id" value="${userinfo.parents_id}" />
                            ${userinfo.parents_id} <!-- IDは表示のみ -->
                        </td>
                    </tr>
                    <tr>
                        <th>名前</th>
                        <td>
                            <input type="text" name="parents_name" value="${userinfo.parents_name}"
                                   required minlength="1" placeholder="名前を入力"
                                   id="parents_name" title="名前に数字を含めないでください。"
                                   pattern="^[^\d０-９]+$"
                                   maxlength="10" />
                        </td>
                    </tr>
                    <script>
                        document.getElementById('parents_name').addEventListener('input', function (e) {
                            // 入力された値から半角・全角数字を除外する
                            this.value = this.value.replace(/[\d０-９]/g, '');
                        });
                    </script>
                    <tr>
                        <th>パスワード</th>
                        <td>
                            <input type="text" name="parents_pass"
                                   minlength="6" maxlength="20"
                                   pattern="(?=.*[A-Z])(?=.*\d)[A-Za-z\d]+"
                                   title="大文字を1文字以上含む英数字6～20文字で入力してください"
                                   placeholder="パスワードを入力"
                                   value="${userinfo.parents_pass}" />
                            <p style="color: red;">${message}</p>
                        </td>
                    </tr>
                    <tr>
                        <th>住所</th>
                        <td>
                            <input type="text" name="parents_address" value="${userinfo.parents_address}"
                                   maxlength="30" placeholder="住所を入力" required />
                        </td>
                    </tr>
                    <tr>
                        <th>電話番号</th>
                        <td>
                            <input type="tel" name="parents_tel"
                                   pattern="^\d{10,11}$"
                                   value="${userinfo.parents_tel}"
                                   required
                                   title="10～11桁の数字で入力してください（例: 09012345678）"
                                   required />
                        </td>
                    </tr>
                    <tr>
                        <th>メールアドレス１</th>
                        <td>
                            <input type="email" name="parents_mail1" value="${userinfo.parents_mail1}"
                                   maxlength="30" placeholder="メールアドレスを入力" required />
                        </td>
                    </tr>
                    <tr>
                        <th>メールアドレス２</th>
                        <td>
                            <input type="email" name="parents_mail2" value="${userinfo.parents_mail2}"
                                   maxlength="30" placeholder="メールアドレスを入力" />
                        </td>
                    </tr>
                    <tr>
                        <th>メールアドレス３</th>
                        <td>
                            <input type="email" name="parents_mail3" value="${userinfo.parents_mail3}"
                                   maxlength="30" placeholder="メールアドレスを入力" />
                        </td>
                    </tr>
                </c:if>
            </table>
            <div class="form-actions" style="text-align: right; margin-top: 20px;">
                <button type="submit" style="background-color: #4CAF50; color: white; border: none; padding: 10px 20px; cursor: pointer;">
                    保存
                </button>
                <a href="menu.jsp" class="button" style="background-color: #f44336; color: white; border: none; padding: 10px 20px; text-decoration: none; cursor: pointer;">
                    戻る
                </a>
            </div>
        </form>

        <c:choose>
            <c:when test="${user_type == 'M'}">
                <!-- 管理者向けのフォーム -->
                <form action="ParentsInput.action" method="get">
                    <input type="hidden" name="parents_id" value="${parents_id}">
                    <button type="submit" style="margin-top: 20px;">保護者情報の確認</button>
                </form>
            </c:when>

            <c:when test="${user_type == 'P'}">
                <!-- 保護者向けのリンク -->
                <a href="Parents.action" style="margin-top: 20px;">
                    <button type="button">保護者情報の確認</button>
                </a>
            </c:when>
        </c:choose>
    </div>
</div>

</body>
<c:import url="/common/footer.jsp" />
</html>
