<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<c:import url="/common/header.jsp" />

<div class="main">


<div class ="con">


    <h2 class="title">子供情報</h2>
    <p>${message}</p>

	    <table class="table table-hover">
	   	    <tr>
	                  <th>保護者ID</th>
	                  <th>クラス名</th>
	                  <th>名前（子供）</th>
	       </tr>
	    	<tr>
                    <td>${parents_id}</td>
                    <td>${class_name}</td>
                    <td>${child_name}</td>
           </tr>
        </table>



    </div>
    </div>
<c:import url="/common/footer.jsp" />