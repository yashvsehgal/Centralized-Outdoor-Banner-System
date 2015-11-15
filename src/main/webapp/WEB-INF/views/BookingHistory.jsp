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
<th>Booking Id</th>
<th>FromBookedDate</th>
<th>TillBookedDate</th>
<th>Publisher Name</th>
</tr>
<c:forEach var="booking" items="${bookingHistoryList}" varStatus="status">
		<tr>
           <td> <c:out value=" ${booking.bookingId}"/><br></td>
           <td> <c:out value=" ${booking.fromBookedDate}"/><br></td>
           <td> <c:out value=" ${booking.tillBookedDate}"/><br></td>
           <td> <c:out value=" ${list[status.index]}"/><br></td>
       </tr>
</c:forEach> 
</table>

</body>
</html>