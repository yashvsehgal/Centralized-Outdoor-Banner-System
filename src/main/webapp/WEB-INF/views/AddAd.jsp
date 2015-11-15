<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
  <script src="//code.jquery.com/jquery-1.10.2.js"></script>
  <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
  
  
<title>Add Advertisement Page</title>
<style>
.error {
	color: #ff0000;
	font-style: italic;
	font-weight: bold;
}
</style>
<script type="text/javascript">

function checkInput() 
{
if(document.adForm.availableFrom.value >= document.adForm.availableTill.value)
{
  alert("Invalid date range provided !");
  return false;  
}else{
	return true;
}
}


</script>

</head>
<body>
	<form:form name="adForm" method="POST" commandName="advertisements" action="add" onsubmit="return checkInput()">
		<table>
			<tr>
				<td>Length:</td>
				<td><form:input type="text"  path="length" pattern="[0-9]{3,10}" title="Length should be min 100" /></td>
				<td><form:errors path="length" cssClass="error" /></td>
			</tr>
			<tr>
				<td>Width:</td>
				<td><form:input type="text" path="width" pattern="[0-9]{3,10}" title="Width should be min 100"/></td>
				<td><form:errors path="width" cssClass="error" /></td>
			</tr>
			<tr>
				<td>Location:</td>
				<td><form:input type="text" pattern="[a-zA-z]{3,15}" path="location" title="Enter a valid location" /></td>
				<td><form:errors path="location" cssClass="error" /></td>
			</tr>

			<tr>
				<td>Price(In USD):</td>
				<td><form:input path="price" type="text" pattern="[0-9]{3,10}" title="Price should be atleast $100"/></td>
				<td><form:errors path="price" cssClass="error" /></td>
			</tr>

<%-- 			<tr>
				<td>Owner:</td>
				<td><springForm:input path="bookedBy" /></td>
				<td><springForm:errors path="bookedBy" cssClass="error" /></td>
			</tr>
			<tr>
				<td>Booked By:</td>
				<td><springForm:input path="owner" /></td>
				<td><springForm:errors path="owner" cssClass="error" /></td>
			</tr> --%>
			<tr>
	
			<tr>
				<td>Type:</td>
				<td><form:select path="type">
						<form:option selected="selected" value="Banner" label="Banner" />
						<form:option value="Glowsign" label="Glowsign" />
					</form:select></td>
				<td><form:errors path="type" cssClass="error" /></td>
			</tr>
			<tr>
				<td>Available From:</td>
				<td><form:input type="text" id="datepicker" pattern="^((0?[13578]|10|12)(-|\/)(([1-9])|(0[1-9])|([12])([0-9]?)|(3[01]?))(-|\/)((19)([2-9])(\d{1})|(20)([01])(\d{1})|([8901])(\d{1}))|(0?[2469]|11)(-|\/)(([1-9])|(0[1-9])|([12])([0-9]?)|(3[0]?))(-|\/)((19)([2-9])(\d{1})|(20)([01])(\d{1})|([8901])(\d{1})))$" path="availableFrom" placeholder="MM/dd/yyyy" required="true"/></td>
						
				<td><form:errors path="availableFrom" cssClass="error"/></td>
			</tr>
			
			
			<tr>
				<td>AvailableTill:</td>
				<td><form:input path="availableTill" pattern="^((0?[13578]|10|12)(-|\/)(([1-9])|(0[1-9])|([12])([0-9]?)|(3[01]?))(-|\/)((19)([2-9])(\d{1})|(20)([01])(\d{1})|([8901])(\d{1}))|(0?[2469]|11)(-|\/)(([1-9])|(0[1-9])|([12])([0-9]?)|(3[0]?))(-|\/)((19)([2-9])(\d{1})|(20)([01])(\d{1})|([8901])(\d{1})))$"
						placeholder="MM/dd/yyyy" required="true"/></td>
				<td><form:errors path="availableTill" cssClass="error" /></td>
			</tr>
			<tr>
				<td colspan="3"><input type="submit" value="Add Advertisement" ></td>
			</tr>
		</table>
	</form:form>
</body>
</html>