<%--
 * action-2.jsp
 *
 * Copyright (C) 2018 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>


<display:table name="registrations" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">
	
	<spring:message code="registration.holderName" var="holderHeader"/>
	<display:column property="creditCard.holderName" title="${holderHeader}" />
	
	<spring:message code="registration.brandName" var="brandHeader"/>
	<display:column property="creditCard.brandName" title="${brandHeader}" />
	
	<spring:message code="registration.number" var="numberHeader"/>
	<display:column property="creditCard.number" title="${numberHeader}" />
	
	<spring:message code="registration.expirationYear" var="expirationYearHeader"/>
	<display:column property="creditCard.expirationYear" title="${expirationYearHeader}" />
	
	<spring:message code="registration.expirationMonth" var="expirationMonthHeader"/>
	<display:column property="creditCard.expirationMonth" title="${expirationMonthHeader}" />
	
	<spring:message code="registration.cvvCode" var="cvvHeader"/>
	<display:column property="creditCard.cvvCode" title="${cvvHeader}" />
	
	<display:column titleKey="registration.show">
		<input type="submit" name="show" value="<spring:message code="registration.show" />"
			onclick="javascript: relativeRedir('registration/author/show.do?registrationId=${row.id}');" />
	</display:column>

</display:table>


<security:authorize access="hasRole('AUTHOR')">
	<input type="submit" name="create" value="<spring:message code="registration.create" />"
		onclick="javascript: relativeRedir('registration/author/create.do');" />
</security:authorize>