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

<display:table name="currencies" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">
	<display:column title="actions">
		<jstl:if test="${name!=EUR}">
			<a href="currency/administrator/currencies.do?currencyId=${row.id}"><spring:message code="flower.edit"/></a>
			<br/>
		</jstl:if>
	</display:column>
	<spring:message code="currency.changeValue" var="changeValue"></spring:message>
	<display:column property="changeValue" title="${changeValue}" sortable="true"></display:column>
	
	<spring:message code="currency.name" var="name"></spring:message>
	<display:column property="name" title="${name}" sortable="false"></display:column>
	
</display:table>

<form:form action="${requestURI }" modelAttribute="currency">
	<form:hidden path="id"/>
	<form:hidden path="version"/>
	
	<acme:textbox code="currency.changeValue" path="changeValue"/>	
	<acme:textbox code="currency.name" path="name"/>
	
	<acme:submit name="save" code="currency.save"/>

</form:form>