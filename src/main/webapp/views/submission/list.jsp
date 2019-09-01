<%--
 * action-2.jsp
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
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<display:table name="submissions" id="row" requestURI="${requestURI}"
	pagesize="5" class="displaytag">

	<spring:message code="submission.ticker" var="tickerHeader" />
	<display:column property="ticker" title="${tickerHeader}" />

	<spring:message code="submission.moment" var="momentHeader" />
	<display:column property="moment" title="${momentHeader}" />

	<spring:message code="submission.status" var="statusHeader" />
	<display:column property="status" title="${statusHeader}" />

	<spring:message code="submission.title" var="titleHeader" />
	<display:column property="paper.title" title="${titleHeader}" />

	<spring:message code="submission.authors" var="authorsHeader" />
	<display:column property="paper.authors" title="${authorsHeader}" />

	<spring:message code="submission.summary" var="summaryHeader" />
	<display:column property="paper.summary" title="${summaryHeader}" />

	<spring:message code="submission.document" var="documentHeader" />
	<display:column property="paper.document" title="${documentHeader}" />

	<display:column titleKey="submission.show">
		<input type="submit" name="show"
			value="<spring:message code="submission.show" />"
			onclick="javascript: relativeRedir('submission/author/show.do?submissionId=${row.id}');" />
	</display:column>

	<security:authorize access="hasRole('AUTHOR')">
		<display:column titleKey="submission.reports">
			<jstl:if
				test="${row.status eq 'ACCEPTED' or row.status eq 'REJECTED'}">
				<input type="submit" name="reports"
					value="<spring:message code="submission.reports" />"
					onclick="javascript: relativeRedir('report/reviewer/listBySubmission.do?submissionId=${row.id}');" />
			</jstl:if>
			<jstl:if test="${row.status eq 'UNDER-REVIEW'}">
				<spring:message code="submission.no.reports" />
			</jstl:if>
		</display:column>
	</security:authorize>

	<security:authorize access="hasRole('AUTHOR')">
		<display:column titleKey="submission.camera">
			<jstl:if test="${row.status eq 'ACCEPTED' and row.conference.cameraDeadline gt d and row.cameraReady == null}">
				<input type="submit" name="camera"
					value="<spring:message code="submission.camera" />"
					onclick="javascript: relativeRedir('submission/author/cameraReady.do?submissionId=${row.id}');" />
			</jstl:if>
			<jstl:if test="${row.status eq 'REJECTED' or row.status eq 'UNDER-REVIEW' or row.conference.cameraDeadline lt d and row.cameraReady == null}">
				<spring:message code="submission.no.camera" />
			</jstl:if>
			<jstl:if test="${row.cameraReady != null}">
				<spring:message code="submission.no.camera2" />
			</jstl:if>
		</display:column>
	</security:authorize>

</display:table>


<security:authorize access="hasRole('AUTHOR')">
	<input type="submit" name="create"
		value="<spring:message code="submission.create" />"
		onclick="javascript: relativeRedir('submission/author/create.do');" />
</security:authorize>