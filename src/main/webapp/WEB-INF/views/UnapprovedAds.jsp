<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>UnApproved Advertisements List</title>
</head>
<body>
<table class="table table-striped">
<tr>
<th>Advertisement Id</th>
<th>Length</th>
<th>Height</th>
<th>Type</th>
<th>Status</th>
<th>Price</th>
<th>Location</th>
<th>Booked By</th>
<th>Subscribed From</th>
<th>Subscribed Till</th>
<th>Approval Required</th>
</tr>
<c:forEach var="ad" items="${advList}">
		<tr>
           <td> <c:out value=" ${ad.advertisementId}"/><br></td>
           <td> <c:out value=" ${ad.length}"/><br></td>
           <td> <c:out value=" ${ad.width}"/><br></td>
           <td> <c:out value=" ${ad.type}"/><br></td>
           <td> <c:out value=" ${ad.status}"/><br></td>
           <td> <c:out value=" ${ad.price}"/><br></td>
           <td> <c:out value=" ${ad.location}"/><br></td>
           <td> <c:out value=" ${ad.owner}"/><br></td>
           <td> <c:out value=" ${ad.availableFrom}"/><br></td>
           <td> <c:out value=" ${ad.availableTill}"/><br></td>
           <td><a href="/controller/activateAds?advertid=${ad.advertisementId}">Approve Ads</a></td>
       </tr>
</c:forEach> 
</table>

</body>
</html>