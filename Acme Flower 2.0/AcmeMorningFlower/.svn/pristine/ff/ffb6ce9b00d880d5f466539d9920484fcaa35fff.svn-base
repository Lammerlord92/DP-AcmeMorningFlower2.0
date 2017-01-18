<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<form:form action="${requestURI }" modelAttribute="flowerOrderForm">
	<form:hidden path="totalPrice"/>
	<form:hidden path="flower"/>
	<form:hidden path="sender.id"/>
	<form:hidden path="sender.version"/>
	<form:hidden path="recipient.id"/>
	<form:hidden path="recipient.version"/>
<fieldset>
	<legend align="left">
		<spring:message code="flowerOrder.creditCard"></spring:message>
	</legend>
	<acme:formOut code="flowerOrder.totalPrice" value="${flowerOrderForm.totalPrice}" path="totalPrice"/>	
	<acme:textbox code="flowerOrder.creditCard.holderName" path="creditCard.holderName"/>
	<acme:textbox code="flowerOrder.creditCard.brandName" path="creditCard.brandName"/>	
	<acme:textbox code="flowerOrder.creditCard.number" path="creditCard.number"/>	
	<acme:textbox code="flowerOrder.creditCard.expirationMonth" path="creditCard.expirationMonth"/>	
	<acme:textbox code="flowerOrder.creditCard.expirationYear" path="creditCard.expirationYear"/>		
	<acme:textbox code="flowerOrder.creditCard.cvvCode" path="creditCard.cvvCode"/>	
</fieldset>
<fieldset>
	<legend align="left">
		<spring:message code="flowerOrder.sender"></spring:message>
	</legend>
		
	<acme:textbox code="flowerOrder.sender.name" path="sender.name"/>
	<acme:textbox code="flowerOrder.sender.surname" path="sender.surname"/>	
	<acme:textbox code="flowerOrder.sender.birthdate" path="sender.birthdate"/>	
	<acme:textbox code="flowerOrder.sender.phoneNumber" path="sender.phoneNumber"/>	
	<acme:textbox code="flowerOrder.sender.emailAddress" path="sender.emailAddress"/>		
</fieldset>
<fieldset>
	<legend align="left">
		<spring:message code="flowerOrder.recipient"></spring:message>
	</legend>
		
	<acme:textbox code="flowerOrder.recipient.name" path="recipient.name"/>
	<acme:textbox code="flowerOrder.recipient.surname" path="recipient.surname"/>	
	<acme:textbox code="flowerOrder.recipient.birthdate" path="recipient.birthdate"/>	
	<acme:textbox code="flowerOrder.recipient.phoneNumber" path="recipient.phoneNumber"/>	
	<acme:textbox code="flowerOrder.recipient.emailAddress" path="recipient.emailAddress"/>		
</fieldset>

	<acme:textbox code="flowerOrder.address" path="address"/>
	
	<acme:submit name="save" code="flowerOrder.save"/>

	<input type="button" name="return"
		value="<spring:message code="flowerOrder.return"/>"
		onClick="javascript: window.location.replace('${returnURI}');"
	/>
</form:form>