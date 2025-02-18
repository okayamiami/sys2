<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<c:import url="/common/header.jsp" />

<div class="main">
    <div class="con">

    <!-- パンくずリスト -->
	<nav aria-label="breadcrumb">
	  <ol class="breadcrumb">
	    <li class="breadcrumb-item"><a href="Menu.action">メニュー</a></li>
	    <li class="breadcrumb-item"><a href="Parents.action">保護者情報</a></li>
	    <li class="breadcrumb-item active" aria-current="page">保護者情報編集</li>
	  </ol>
	</nav>

        <h2 class="title">保護者情報編集</h2>
        <form action="ParentsEditExecute.action" method="post">
			<button type="submit" class="button-send">保存</button>

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
                                   pattern="^[\p{L}]+$"
                                   maxlength="10"
                                   required/>
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
                                   minlength="8" maxlength="20"
                                   pattern="(?=.*[A-Z])(?=.*\d)[A-Za-z\d]+"
                                   title="大文字を1文字以上含む英数字8～20文字で入力してください"
                                   placeholder="パスワードを入力"
                                   value="${userinfo.parents_pass}"
                                   required/>
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
        </form>
    </div>
</div>


<c:import url="/common/footer.jsp" />

