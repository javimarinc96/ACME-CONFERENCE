<%--
 * action-1.jsp
 *
 * Copyright (C) 2018 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>


	<fieldset>

	<b><spring:message code="registration.brandName"></spring:message>:</b><jstl:out value="${registration.creditCard.brandName}"></jstl:out>
	<br />
	
	<b><spring:message code="registration.holderName"></spring:message>:</b><jstl:out value="${registration.creditCard.holderName}"></jstl:out>
	<br />
	
	<b><spring:message code="registration.number"></spring:message>:</b><jstl:out value="${registration.creditCard.number}"></jstl:out>
	<br />
	
	<b><spring:message code="registration.cvvCode"></spring:message>:</b><jstl:out value="${registration.creditCard.cvvCode}"></jstl:out>
	<br />
	
	<b><spring:message code="registration.expirationYear"></spring:message>:</b><jstl:out value="${registration.creditCard.expirationYear}"></jstl:out>
	<br />
	
	<b><spring:message code="registration.expirationMonth"></spring:message>:</b><jstl:out value="${registration.creditCard.expirationMonth}"></jstl:out>
	<br />
	
	<b><spring:message code="registration.author"></spring:message>:</b><jstl:out value="${registration.author.name}"></jstl:out>
	<br />
	
	<b><spring:message code="registration.conference"></spring:message>:</b><jstl:out value="${registration.conference.title}"></jstl:out>
	<br />
	
	</fieldset>

	<br />
	
<!-- Cancel -->

<security:authorize access="hasRole('AUTHOR')">	
				<button type="button" onclick="javascript: relativeRedir('registration/author/list.do')">
				<spring:message code="registration.cancel" />
				</button>
</security:authorize>