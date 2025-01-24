<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<c:import url="/common/header.jsp" />

<div class="main">

    <div class="con">
    <!-- パンくずリスト -->
	<nav aria-label="breadcrumb">
	  <ol class="breadcrumb">
	    <li class="breadcrumb-item"><a href="Menu.action">メニュー</a></li>
	    <li class="breadcrumb-item"><a href="ManageUser.action">ユーザー情報</a></li>
	    <li class="breadcrumb-item"><span>ユーザー情報編集</span></li>
	  </ol>
	</nav>

    <h2 class="title">ユーザー情報編集</h2>


        <form action="ManageEditExecute.action" method="post">
        	<button type="submit" class="button-send">保存</button>
            <table>
                <c:if test="${not empty muinfo}">
                    <tr>
                        <th>ユーザーID</th>
                        <td>
                            <input type="hidden" name="user_id" value="${muinfo.user_id}" />
                            ${muinfo.user_id} <!-- IDは表示のみ -->
                        </td>
                    </tr>
                    <tr>

                    <th>名前</th>
                    <td>
                        <input type="text" name="user_name" value="${fn:trim(muinfo.user_name)}"
                                   required minlength="1" maxlength="10"
                                   placeholder="名前を入力"
                                   title="名前に数字や記号を含めないでください。"
                                   pattern="^[\p{L}]+$" />
                    </td>

                    </tr>
                    <tr>
                        <th>パスワード</th>
                        <td>
                           <input type="text" name="user_pass"
                                   required minlength="8" maxlength="20"
                                   placeholder="パスワードを入力"
                                   title="大文字を1文字以上含む英数字8～20文字で入力してください。"
                                   pattern="(?=.*[A-Z])(?=.*\d)[A-Za-z\d]{8,20}"
                                   value="${fn:trim(muinfo.user_pass)}" />
                        </td>
                    </tr>
                    <tr>
                        <th>施設ID</th>
                        <td>
                            <input type="hidden" name="facility_id" value="${muinfo.facility_id}" />
                            ${muinfo.facility_id} <!-- IDは表示のみ -->
                        </td>
                    </tr>
 				</c:if>
            </table>




           </form>





    </div>
</div>


<c:import url="/common/footer.jsp" />


