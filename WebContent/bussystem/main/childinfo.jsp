<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


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
		    <li class="breadcrumb-item active" aria-current="page">子供情報一覧</li>
		  </ol>
		</nav>
	    </c:when>

	    <c:when test="${user_type == 'P'}">
	    <!-- 保護者パンくずリスト -->
	    <nav aria-label="breadcrumb">
		  <ol class="breadcrumb">
		    <li class="breadcrumb-item"><a href="Menu.action">メニュー</a></li>
		    <li class="breadcrumb-item active" aria-current="page">子供情報一覧</li>
		  </ol>
		</nav>

	    </c:when>
	</c:choose>



        <!-- 子供情報テーブル -->
        <h2 class="title">子供情報一覧</h2>
        <p>クラス・名前に変更がある場合は変更ボタンから修正してください</p>

        <!-- 追加ボタン -->
        <c:if test="${not empty parents_id}">
            <form action="ChildAdd.action" method="post" style="display:inline;">
                <input type="hidden" name="parents_id" value="${parents_id}" />
		        <button type="submit" class="button-send">新規子供情報登録</button>
            </form>
        </c:if>

        <table>
            <tr>
                <th>保護者ID</th>
                <th>クラス名</th>
                <th>子供の名前</th>
                <th>-</th>
            </tr>
            <c:forEach var="child" items="${userCI}">
                <tr>
                	<!-- 保護者IDの表示 -->
                    <td>${child.parents_id}</td>

					<!-- クラスの名前を表示 -->
                    <td>
                        <!-- クラスIDに対応するクラス名を表示 -->
                        <c:forEach var="classCd" items="${class_set}">
                            <c:if test="${classCd.class_id == child.class_id}">
                                ${classCd.class_name}
                            </c:if>
                        </c:forEach>
                    </td>

					<!-- 子供の名前表示 -->
                    <td>${child.child_name}</td>

					<!-- 編集ボタン -->
                    <td>
                        <form action="ChildUpdate.action" method="post" style="display:inline;">
                            <input type="hidden" name="parents_id" value="${child.parents_id}" />
                            <!-- 子供のchild_idを送信する隠しフィールド -->
                            <input type="hidden" name="child_id" value="${child.child_id}" />
		                		<button type="submit" class="button-send">編集</button>
                        </form>
                    </td>

                </tr>
            </c:forEach>
        </table>

    </div>
</div>


<c:import url="/common/footer.jsp" />
