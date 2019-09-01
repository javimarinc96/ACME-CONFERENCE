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

	<b><spring:message code="report.qualityScore"></spring:message>:</b><jstl:out value="${report.qualityScore}"></jstl:out>
	<br />
	
	<b><spring:message code="report.readabilityScore"></spring:message>:</b><jstl:out value="${report.readabilityScore}"></jstl:out>
	<br />
	
	<b><spring:message code="report.originalityScore"></spring:message>:</b><jstl:out value="${report.originalityScore}"></jstl:out>
	<br />
	
	<b><spring:message code="report.comments"></spring:message>:</b><jstl:out value="${report.comments}"></jstl:out>
	<br />
	
	<b><spring:message code="report.decision"></spring:message>:</b><jstl:out value="${report.decision}"></jstl:out>
	<br />
	
	<b><spring:message code="report.reviewer"></spring:message>:</b><jstl:out value="${report.reviewer.name}"></jstl:out>
	<br />
	
	<b><spring:message code="report.submission"></spring:message>:</b><jstl:out value="${report.submission.ticker}"></jstl:out>
	<br />
	
	</fieldset>

	<br />
	
<!-- Cancel -->

<security:authorize access="hasRole('REVIEWER')">	
				<button type="button" onclick="javascript: relativeRedir('report/reviewer/list.do')">
				<spring:message code="report.cancel" />
				</button>
</security:authorize>