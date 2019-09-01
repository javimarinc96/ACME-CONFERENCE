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

	<b><spring:message code="submission.ticker"></spring:message>:</b><jstl:out value="${submission.ticker}"></jstl:out>
	<br />
	
	<b><spring:message code="submission.moment"></spring:message>:</b><jstl:out value="${submission.moment}"></jstl:out>
	<br />
	
	<b><spring:message code="submission.title"></spring:message>:</b><jstl:out value="${submission.paper.title}"></jstl:out>
	<br />
	
	<b><spring:message code="submission.authors"></spring:message>:</b><jstl:out value="${submission.paper.authors}"></jstl:out>
	<br />
	
	<b><spring:message code="submission.summary"></spring:message>:</b><jstl:out value="${submission.paper.summary}"></jstl:out>
	<br />
	
	<b><spring:message code="submission.document"></spring:message>:</b><jstl:out value="${submission.paper.document}"></jstl:out>
	<br />
	
	<b><spring:message code="submission.status"></spring:message>:</b><jstl:out value="${submission.status}"></jstl:out>
	<br />
	
	<b><spring:message code="submission.author"></spring:message>:</b><jstl:out value="${submission.author.name}"></jstl:out>
	<br />
	
	<b><spring:message code="submission.conference"></spring:message>:</b><jstl:out value="${submission.conference.title}"></jstl:out>
	<br />
	
	</fieldset>

	<br />
	
<!-- Cancel -->

<security:authorize access="hasRole('AUTHOR')">	
				<button type="button" onclick="javascript: relativeRedir('submission/author/list.do')">
				<spring:message code="submission.cancel" />
				</button>
</security:authorize>