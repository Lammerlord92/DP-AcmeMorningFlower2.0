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


<form:form action="${requestURI }" modelAttribute="flower">
	<form:hidden path="id"/>
	<form:hidden path="version"/>
	<form:hidden path="deleted"/>
	<acme:textbox code="flower.vulgarName" path="vulgarName"/>
	<acme:textbox code="flower.scientificName" path="scientificName"/>	
	<acme:textbox code="flower.unitPrice" path="unitPrice"/>
	<acme:textbox code="flower.url" path="url"/>		
	<acme:select items="${tags}" itemLabel="keywords" code="flower.tag.keywords" path="tag"/>	

	<acme:submit name="save" code="flower.save"/>

	<input type="button" name="return"
		value="<spring:message code="flower.return"/>"
		onClick="javascript: window.location.replace('${returnURI}');"
	/>
</form:form>