<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<!-- ヘッダーのインクルード -->
<c:import url="/common/header.jsp" />

<div class="main">
    <div class="con">


	    <!-- アカウント区分ごとの表示 -->
       	<c:choose>
	    <c:when test="${user_type == 'M'}">
	    <!-- 管理者パンくずリスト -->
		<nav aria-label="breadcrumb">
		  <ol class="breadcrumb">
		    <li class="breadcrumb-item"><a href="Menu.action">メニュー</a></li>
		    <li class="breadcrumb-item"><a href="ChildInfo.action">保護者ID入力（子供情報）</a></li>
		    <li class="breadcrumb-item"><a href="ParentsIDInput.action?parents_id=${childInfo.parents_id}">子供情報一覧</a></li>
		    <li class="breadcrumb-item active" aria-current="page">子供情報編集</li>
		  </ol>
		</nav>
	    </c:when>

	    <c:when test="${user_type == 'P'}">
	    <!-- 保護者パンくずリスト -->
	    <nav aria-label="breadcrumb">
		  <ol class="breadcrumb">
		    <li class="breadcrumb-item"><a href="Menu.action">メニュー</a></li>
		    <li class="breadcrumb-item"><a href="ChildInfo.action">子供情報一覧</a></li>
		    <li class="breadcrumb-item active" aria-current="page">子供情報編集</li>
		  </ol>
		</nav>

	    </c:when>
	</c:choose>



        <h2 class="title">子供情報編集</h2>
        <p>修正箇所を選択または入力し保存ボタンを押してください</p>

        <form action="ChildUpdateExecute.action" method="post">
            <!-- 保存ボタン -->
		    <button type="submit" class="button-send">保存</button>



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
					           pattern="^[\p{L}]+$" value="${childInfo.child_name}"
					           title="数字を含まない名前を入力してください"
					           maxlength="10"> <!-- child_nameを送信 -->
                        	<input type="hidden" name="child_id" value="${childInfo.child_id}" /> <!-- child_idを送信 -->
                        </td>
                    </tr>
                </c:if>
            </table>

</form>
    </div>
</div>

<!-- フッターのインクルード -->
<c:import url="/common/footer.jsp" />


