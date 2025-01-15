<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<c:import url="/common/header.jsp" />

<div class="main">

<div class="con">

    <h2 class="title">保護者情報更新完了</h2>
    <p>${message}</p>
		<c:choose>


		    <c:when test="${user_type == 'P'}">
		        <form action="Parents.action" method="get">
		            <div class="button-save">
		                <button type="submit">保護者情報の確認</button>
		            </div>
		        </form>
		    </c:when>
		</c:choose>



	</div>
	</div>
<c:import url="/common/footer.jsp" />
