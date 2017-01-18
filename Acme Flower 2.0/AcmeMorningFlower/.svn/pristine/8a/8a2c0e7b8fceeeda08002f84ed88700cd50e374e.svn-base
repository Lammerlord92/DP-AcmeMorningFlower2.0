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

<display:table name="flowerOrders" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">
	
	<display:column title="actions">
		<jstl:if test="${row.status=='PENDING'}">
			<a href="order/administrator/deliver.do?orderId=${row.id}"><spring:message code="flowerOrder.deliver"/></a>
			<br/>
		</jstl:if>
	</display:column>
	<spring:message code="flowerOrder.ticker" var="ticker"></spring:message>
	<display:column property="ticker" title="${ticker}" sortable="false"></display:column>
	
	<spring:message code="flowerOrder.moment" var="moment"></spring:message>
	<display:column property="moment" title="${moment}" sortable="false"></display:column>

	<spring:message code="flowerOrder.address" var="address"></spring:message>
	<display:column property="address" title="${address}" sortable="false"></display:column>
	
	<spring:message code="flowerOrder.deliveryDate" var="deliveryDate"></spring:message>
	<display:column property="deliveryDate" title="${deliveryDate}" sortable="false"></display:column>
		
	<spring:message code="flowerOrder.totalPrice" var="totalPrice"></spring:message>
	<display:column property="totalPrice" title="${totalPrice}" sortable="true"></display:column>
	
	<spring:message code="flowerOrder.status" var="status"></spring:message>
	<display:column property="status" title="${status}" sortable="false"></display:column>
	
<%-- 	<security:authorize access="hasRole('ADMIN')">
		<spring:message code="flowerOrder.deleted" var="deleted"></spring:message>
		<display:column property="deleted" title="${deleted}" sortable="false"></display:column>
	</security:authorize> --%>
</display:table>

