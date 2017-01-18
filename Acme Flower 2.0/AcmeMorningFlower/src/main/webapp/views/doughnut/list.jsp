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

<display:table name="doughnuts" id="row" requestURI="${requestURI}" pagesize="5" class="displaydoughnut">
	
	<display:column title="actions">
			<jstl:if test="${row.deleted==false }">
				<a href="doughnut/administrator/cancell.do?doughnutId=${row.id}"><spring:message code="doughnut.cancell"/></a>
				<br/>
			</jstl:if>
			<jstl:if test="${row.deleted==true }">
				<a href="doughnut/administrator/uncancell.do?doughnutId=${row.id}"><spring:message code="doughnut.uncancell"/></a>
				<br/>
			</jstl:if>
			<a href="doughnut/administrator/edit.do?doughnutId=${row.id}"><spring:message code="doughnut.edit"/></a>
			<br/>
	</display:column>

	<spring:message code="doughnut.name" var="name"></spring:message>
	<display:column property="name" title="${name}" sortable="false"></display:column>
	
	<spring:message code="doughnut.ingredients" var="ingredients"></spring:message>
	<display:column property="ingredients" title="${ingredients}" sortable="false"></display:column>
		
	<spring:message code="doughnut.calories" var="calories"></spring:message>
	<display:column property="calories" title="${calories}" sortable="false"></display:column>
	
	<spring:message code="doughnut.deleted" var="deleted"></spring:message>
	<display:column property="deleted" title="${deleted}" sortable="false"></display:column>
	
</display:table>
	
<input type="button" name="create" value="<spring:message code="doughnut.create"/>" 
	onclick="javascript: window.location.replace('doughnut/administrator/create.do')"/>


