<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>


<!-- ヘッダーのインポート -->
<c:import url="/common/header.jsp" />

<div class="main">
    <!-- ナビゲーションのインポート -->


    <div class="con">
        <h2 class="title">乗降状況</h2>

        <!-- エラーメッセージの表示 -->
        <c:if test="${not empty error}">
            <p style="color: red;">${error}</p>
        </c:if>

        <!-- 検索フォーム -->
        <form method="GET" action="GetListInfo.action">
            <label for="bus_id">バス</label>
            <select name="bus_id" id="bus_id">
                <option value="">-----</option>
                <c:forEach var="bus" items="${busList}">
                    <option value="${bus.bus_id}"
                        <c:if test="${param.bus_id != null && param.bus_id == bus.bus_id}">selected</c:if>>
                        ${bus.bus_name}
                    </option>
                </c:forEach>
            </select>

            <label for="child_id">名前</label>
            <select name="child_id" id="child_id">
                <option value="">-----</option>
                <c:forEach var="child" items="${childList}">
                    <option value="${child.child_id}"
                        <c:if test="${param.child_id != null && param.child_id == child.child_id}">selected</c:if>>
                        ${child.child_name}
                    </option>
                </c:forEach>
            </select>

            <label for="class_id">クラス</label>
            <select name="class_id" id="class_id">
                <option value="">-----</option>
                <c:forEach var="classCd" items="${classList}">
				    <option value="${classCd.class_id}"
				        ${param.class_id != null && param.class_id == classCd.class_id ? 'selected' : ''}>
				        ${classCd.class_name}  <!-- クラス名を表示 -->
				    </option>
				</c:forEach>


            </select>

            <button type="submit" class="button-send">検索</button>
        </form>

        <!-- 検索結果 -->
        <h2>検索結果</h2>
        <table border="1">
            <thead>
                <tr>
                    <th>バス名</th>
                    <th>子供の名前</th>
                    <th>クラス名</th>
                </tr>
            </thead>
            <tbody>
                <!-- 検索結果がない場合 -->
                <c:if test="${empty busChildInfoList}">
                    <tr>
                        <td colspan="3">検索結果がありません。</td>
                    </tr>
                </c:if>

                <!-- 検索結果の表示 -->
                <c:forEach var="info" items="${busChildInfoList}">
                    <tr>
                        <td>${info.bus_name}</td>
                        <td>${info.child_name}</td>
                        <td>${info.class_name}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

    </div>
</div>
<c:import url="/common/footer.jsp" />



