<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Food Menu</title>
</head>
<body>
	
	<h1 style="color: black; background: orange;">Food WebPage</h1>
		
		<hr>
		<hr>
		<a href="/">HomePage</a><br> <br>

		<h2 style="background: orange; color: black;">Add Food Here WithImage Here</h2>
		<hr>
		<c:out value="${added}"></c:out>
		<form action="addfood" method="post" enctype="multipart/form-data">
			Food Id: <input type="number" name="fid" placeholder="Enter Id here" required/><br> <br>
			Food Name: <input type="text" name="name" placeholder="Enter the food name" required/><br> <br>
			Food Price: <input type="number" name="price" placeholder="Enter the price" required /><br> <br>
			Select Image: <input type="file" name="image" /><br>
			<button>ADD</button>
		</form>
		<hr>
		<hr>
		<a href="getallfood">Click here to view menu</a>
		<hr>
		<hr>
		<a href="/updatefood">Click here to Update Food</a><br> <br>
		<hr>
		<hr>
</body>
</html>