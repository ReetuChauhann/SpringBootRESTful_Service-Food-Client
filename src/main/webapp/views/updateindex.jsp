<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h2 style="background: orange; color: black;">Update Food Here</h2>
<c:if test="${sts=='Success'}">
<c:out value="Updated Successfully"></c:out>
</c:if>
<c:if test="${sts=='Failed'}">
<c:out value="Updated Successfully"></c:out>
</c:if>
<form action="Updatefood" method="post">
 <select name="fid">
 <c:forEach items="${ids}" var="id">
    <option>${id}</option>
 </c:forEach>
 
 </select><hr>
			
			Food Name: <input type="text" name="name" placeholder="Enter the food name" required/><br> <br>
			Food Price: <input type="number" name="price" placeholder="Enter the price" required /><br> <br>
			
			<button>Update</button>
		</form>

</body>
</html>