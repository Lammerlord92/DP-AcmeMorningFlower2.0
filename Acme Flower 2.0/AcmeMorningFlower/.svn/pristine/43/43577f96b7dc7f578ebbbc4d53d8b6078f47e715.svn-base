<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<fieldset>
	<legend align="left">
		<spring:message code="dashboard.fieldset1"></spring:message>
	</legend>
	
	<display:table name="bestSellingFlowers" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">
	
		<spring:message code="flower.vulgarName" var="vulgarName"></spring:message>
		<display:column property="vulgarName" title="${vulgarName}" sortable="false"></display:column>
	
		<spring:message code="flower.scientificName" var="scientificName"></spring:message>
		<display:column property="scientificName" title="${scientificName}" sortable="false"></display:column>
	
		<spring:message code="flower.unitPrice" var="unitPrice"></spring:message>
		<display:column property="unitPrice" title="${unitPrice}" sortable="true"></display:column>
		
		<spring:message code="flower.url" var="url"></spring:message>
		<display:column property="url" title="${url}" sortable="false"></display:column>
	
		<spring:message code="flower.tag.keywords" var="keywords"></spring:message>
		<display:column property="tag.keywords" title="${keywords}" sortable="false"></display:column>
	
	</display:table>
	
</fieldset>

<fieldset>
	<legend align="left">
		<spring:message code="dashboard.fieldset2"></spring:message>
	</legend>
	
	<display:table name="neverOrderedFlowers" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">
	
		<spring:message code="flower.vulgarName" var="vulgarName"></spring:message>
		<display:column property="vulgarName" title="${vulgarName}" sortable="false"></display:column>
	
		<spring:message code="flower.scientificName" var="scientificName"></spring:message>
		<display:column property="scientificName" title="${scientificName}" sortable="false"></display:column>
	
		<spring:message code="flower.unitPrice" var="unitPrice"></spring:message>
		<display:column property="unitPrice" title="${unitPrice}" sortable="true"></display:column>
		
		<spring:message code="flower.url" var="url"></spring:message>
		<display:column property="url" title="${url}" sortable="false"></display:column>
	
		<spring:message code="flower.tag.keywords" var="keywords"></spring:message>
		<display:column property="tag.keywords" title="${keywords}" sortable="false"></display:column>
	
	</display:table>
	
</fieldset>
<fieldset>
	<legend align="left">
		<spring:message code="dashboard.fieldset3"></spring:message>
	</legend>
	<table class="displaytag">
		<thead>
		<tr>
			<th><spring:message code="customer.emailAddress"/></th>
			<th><spring:message code="flowerOrderAvg"></spring:message></th>
		</tr>
		</thead>
		
		<tbody>
		<jstl:forEach items="${flowerOrderAvg}" var="avg">
			<tr>
				<jstl:forEach items="${avg}" var="aux">
					<td><jstl:out value="${aux}"/></td>
				</jstl:forEach>
			</tr>
		</jstl:forEach>
		</tbody>
	</table>
</fieldset>

<fieldset>
	<legend align="left">
		<spring:message code="dashboard.fieldset4"></spring:message>
	</legend>
	<table class="displaytag">
		<thead>
		<tr>
			<th><spring:message code="flower.vulgarName"/></th>
			<th><spring:message code="flowerOrderNumber"></spring:message></th>
		</tr>
		</thead>
		
		<tbody>
		<jstl:forEach items="${flowerOrderNumber}" var="avg">
			<tr>
				<jstl:forEach items="${avg}" var="aux">
					<td><jstl:out value="${aux}"/></td>
				</jstl:forEach>
			</tr>
		</jstl:forEach>
		</tbody>
	</table>
</fieldset>

