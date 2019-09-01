
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

<jsp:useBean id="now" class="java.util.Date" />

<h2> <spring:message code="submission.under"/> </h2>
<display:table name="underReview" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">
	
	<spring:message code="submission.ticker" var="tickerHeader"/>
	<display:column property="ticker" title="${tickerHeader}"  />
	
	<spring:message code="submission.moment" var="momentHeader"/>
	<display:column property="moment" title="${momentHeader}" />
	
	<spring:message code="submission.status" var="statusHeader"/>
	<display:column property="status" title="${statusHeader}" />
	
	<spring:message code="submission.title" var="titleHeader"/>
	<display:column property="paper.title" title="${titleHeader}" />
	
	<spring:message code="submission.authors" var="authorsHeader"/>
	<display:column property="paper.authors" title="${authorsHeader}" />
	
	<spring:message code="submission.summary" var="summaryHeader"/>
	<display:column property="paper.summary" title="${summaryHeader}" />
	
	<spring:message code="submission.document" var="documentHeader"/>
	<display:column property="paper.document" title="${documentHeader}" />
	
	<spring:message code="submission.conference" var="conferenceHeader"/>
	<display:column property="conference.title" title="${conferenceHeader}" />
	
	<security:authorize access="hasRole('ADMIN')">
		<display:column titleKey="submission.assign">
		<jstl:if test="${!row.assignment eq true and row.conference.notificationDeadline gt now}">
		<input type="submit" name="assign" value="<spring:message code="submission.assign" />"
			onclick="javascript: relativeRedir('submission/author/assignReviewers.do?submissionId=${row.id}');" />
			</jstl:if>
		<jstl:if test="${row.assignment eq true or row.conference.notificationDeadline lt now}">
			<spring:message code="submission.no.assign" />
			</jstl:if>
				</display:column>
	</security:authorize>
	
	<security:authorize access="hasRole('ADMIN')">
		<display:column titleKey="submission.decission">
		<jstl:if test="${b eq true and row.conference.notificationDeadline gt now}">
		<input type="submit" name="decission" value="<spring:message code="submission.decission" />"
			onclick="javascript: relativeRedir('submission/author/decission.do?submissionId=${row.id}');" />
			</jstl:if>
		<jstl:if test="${b eq false or row.conference.notificationDeadline lt now}">
			<spring:message code="submission.no.decision" />
			</jstl:if>
				</display:column>
	</security:authorize>

</display:table>

<h2> <spring:message code="submission.accepted"/> </h2>
<display:table name="accepted" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">

	<spring:message code="submission.ticker" var="tickerHeader"/>
	<display:column property="ticker" title="${tickerHeader}"  />
	
	<spring:message code="submission.moment" var="momentHeader"/>
	<display:column property="moment" title="${momentHeader}" />
	
	<spring:message code="submission.status" var="statusHeader"/>
	<display:column property="status" title="${statusHeader}" />
	
	<spring:message code="submission.title" var="titleHeader"/>
	<display:column property="paper.title" title="${titleHeader}" />
	
	<spring:message code="submission.authors" var="authorsHeader"/>
	<display:column property="paper.authors" title="${authorsHeader}" />
	
	<spring:message code="submission.summary" var="summaryHeader"/>
	<display:column property="paper.summary" title="${summaryHeader}" />
	
	<spring:message code="submission.document" var="documentHeader"/>
	<display:column property="paper.document" title="${documentHeader}" />
	
	<spring:message code="submission.conference" var="conferenceHeader"/>
	<display:column property="conference.title" title="${conferenceHeader}" />
	
</display:table>


<h2> <spring:message code="submission.rejected"/> </h2>
<display:table name="rejected" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">

	<spring:message code="submission.ticker" var="tickerHeader"/>
	<display:column property="ticker" title="${tickerHeader}"  />
	
	<spring:message code="submission.moment" var="momentHeader"/>
	<display:column property="moment" title="${momentHeader}" />
	
	<spring:message code="submission.status" var="statusHeader"/>
	<display:column property="status" title="${statusHeader}" />
	
	<spring:message code="submission.title" var="titleHeader"/>
	<display:column property="paper.title" title="${titleHeader}" />
	
	<spring:message code="submission.authors" var="authorsHeader"/>
	<display:column property="paper.authors" title="${authorsHeader}" />
	
	<spring:message code="submission.summary" var="summaryHeader"/>
	<display:column property="paper.summary" title="${summaryHeader}" />
	
	<spring:message code="submission.document" var="documentHeader"/>
	<display:column property="paper.document" title="${documentHeader}" />
	
	<spring:message code="submission.conference" var="conferenceHeader"/>
	<display:column property="conference.title" title="${conferenceHeader}" />
	
</display:table>

<br/>
<br/>

<security:authorize access="hasRole('ADMIN')">
	<input type="submit" name="cancel"
		value="<spring:message code="submission.cancel" />"
		onclick="javascript: relativeRedir('conference/administrator/list.do');" />
</security:authorize>