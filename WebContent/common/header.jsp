<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div>
    <h1>得点管理システム</h1>
</div>

<c:if test="${user != null}">
    <div>
        <c:choose>
            <!-- ParentsUserの場合 -->
            <c:when test="${user is bean.ParentsUser}">
                <span>${user.parents_name}様</span>
            </c:when>
            <!-- ManageUserの場合 -->
            <c:when test="${user is bean.ManageUser}">
                <span>${user.user_name}様</span>
            </c:when>
        </c:choose>
        <a href="Syslogout.action">ログアウト</a>
    </div>
</c:if>
