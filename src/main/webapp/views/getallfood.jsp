<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Choose </title>
</head>
<body>
     <h1 style="color: black; background: orange;">Food Menu</h1>
     <c:forEach items="${food}" var="f">
 Food ID:      <c:out value="${f.fid}"></c:out><br>
 Food Name:    <c:out value="${f.name}"></c:out><br>
Food Price     <c:out value="${f.price}"></c:out><br>
  <img alt="" src="getimage?fid=${f.fid}" height="50px" width="50px">
  <br>
     
     <form action="updateimage" method="post" enctype="multipart/form-data">
     <input type="hidden" name="fid" value="${f.fid}" />
     <input type="file" name="image" required />
 Update image: <button>Update!</button>
     
     </form>
     <hr><hr>
     </c:forEach>
     

</body>
</html>