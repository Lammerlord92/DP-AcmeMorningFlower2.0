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


<jstl:if test="${haveOne}">
	<form:form action="${requestURI }" modelAttribute="flowerOrder">
		<form:hidden path="id"/>
		<form:hidden path="version"/>
		
		<form:hidden path="ticker"/>
		<form:hidden path="moment"/>
		<form:hidden path="address"/>
		<form:hidden path="deliveryDate"/>
		<form:hidden path="totalPrice"/>
		<form:hidden path="creditCard"/>
		<form:hidden path="status"/>
		<form:hidden path="sender"/>
		<form:hidden path="recipient"/>
	
		<acme:formOut code="flowerOrder.ticker" value="${flowerOrder.ticker}" path="ticker"/>
	
		<acme:formOut code="flowerOrder.deliveryDate" value="${flowerOrder.deliveryDate}" path="deliveryDate"/>
	
	<fieldset>
		<legend align="left">
			<spring:message code="flowerOrder.creditCard"></spring:message>
		</legend>
		<acme:formOut code="flowerOrder.totalPrice" value="${flowerOrder.totalPrice}" path="totalPrice"/>	
		<acme:formOut code="flowerOrder.creditCard.holderName" value="${flowerOrder.creditCard.holderName}" path="creditCard.holderName"/>
		<acme:formOut code="flowerOrder.creditCard.brandName" value="${flowerOrder.creditCard.brandName}" path="creditCard.brandName"/>	
		<acme:formOut code="flowerOrder.creditCard.number" value="${flowerOrder.creditCard.number}" path="creditCard.number"/>	
		<acme:formOut code="flowerOrder.creditCard.expirationMonth" value="${flowerOrder.creditCard.expirationMonth}" path="creditCard.expirationMonth"/>	
		<acme:formOut code="flowerOrder.creditCard.expirationYear" value="${flowerOrder.creditCard.expirationYear}" path="creditCard.expirationYear"/>		
		<acme:formOut code="flowerOrder.creditCard.cvvCode" value="${flowerOrder.creditCard.cvvCode}" path="creditCard.cvvCode"/>	
	</fieldset>
<fieldset>
	<legend align="left">
		<spring:message code="flowerOrder.sender"></spring:message>
	</legend>
		
	<acme:formOut code="flowerOrder.sender.name" value="${flowerOrder.sender.name}" path="sender.name"/>
	<acme:formOut code="flowerOrder.sender.surname" value="${flowerOrder.sender.surname}" path="sender.surname"/>	
	<acme:formOut code="flowerOrder.sender.birthdate" value="${flowerOrder.sender.birthdate}" path="sender.birthdate"/>	
	<acme:formOut code="flowerOrder.sender.phoneNumber" value="${flowerOrder.sender.phoneNumber}" path="sender.phoneNumber"/>	
	<acme:formOut code="flowerOrder.sender.emailAddress" value="${flowerOrder.sender.emailAddress}" path="sender.emailAddress"/>		
</fieldset>
<fieldset>
	<legend align="left">
		<spring:message code="flowerOrder.recipient"></spring:message>
	</legend>
		
	<acme:formOut code="flowerOrder.recipient.name" value="${flowerOrder.recipient.name}" path="recipient.name"/>
	<acme:formOut code="flowerOrder.recipient.surname" value="${flowerOrder.recipient.surname}" path="recipient.surname"/>	
	<acme:formOut code="flowerOrder.recipient.birthdate" value="${flowerOrder.recipient.birthdate}" path="recipient.birthdate"/>	
	<acme:formOut code="flowerOrder.recipient.phoneNumber" value="${flowerOrder.recipient.phoneNumber}" path="recipient.phoneNumber"/>	
	<acme:formOut code="flowerOrder.recipient.emailAddress" value="${flowerOrder.recipient.emailAddress}" path="recipient.emailAddress"/>		
</fieldset>

	<acme:formOut code="flowerOrder.address" value="${flowerOrder.address}" path="address"/>
	<acme:formOut code="flowerOrder.status" value="${flowerOrder.status}" path="status"/>
	<jstl:if test="${pending}">
		<acme:submit name="cancel" code="flowerOrder.cancel"/>
	</jstl:if>
	<security:authorize access="hasRole('nome')">
		<acme:textbox code="flowerOrder.status" path="status"/>
	</security:authorize>
</form:form> 
</jstl:if>
<br/>
<jstl:if test="${!haveOne}">
	<spring:message code="flowerOrder.introduceValid"/>
</jstl:if>
<input type="text" value="" id="tickerSearch"/>
<input type="button" id="buttonSearch" value="<spring:message code="flowerOrder.search"/>" />
	
<script type="text/javascript">
	$(document).ready(function(){
		$("#buttonSearch").click(function(){
			window.location.replace('order/search.do?ticker=' + $("#tickerSearch").val());
		});
		$("#buttonSearch").onsubmit(function(){
			window.location.replace('forder/search.do?ticker=' + $("#tickerSearch").val());
		});
	});
</script>

