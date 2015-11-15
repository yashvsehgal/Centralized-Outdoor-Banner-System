<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<table class="table table-striped">
<tr>
<th>Advertisement Id</th>
<th>Length</th>
<th>Height</th>
<th>Type</th>
<th>Booked From</th>
<th>Booked Till</th>
</tr>
<c:forEach var="ad" items="${adList}">
       <tr>
           <td> <c:out value=" ${ad.advertisementId}"/><br></td>
           <td> <c:out value=" ${ad.length}"/><br></td>
           <td> <c:out value=" ${ad.width}"/><br></td>
           <td> <c:out value=" ${ad.type}"/><br></td>
           <td> <c:out value=" ${ad.availableFrom}"/><br></td>
           <td> <c:out value=" ${ad.availableTill}"/><br></td>
       </tr>
</c:forEach> 
</table>

</body>
</html>