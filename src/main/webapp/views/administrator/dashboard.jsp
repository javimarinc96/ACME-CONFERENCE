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
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<spring:message code="administrator.dashboard.avg" var="avgHeader" />
<spring:message code="administrator.dashboard.min" var="minHeader" />
<spring:message code="administrator.dashboard.max" var="maxHeader" />
<spring:message code="administrator.dashboard.std" var="stdHeader" />

<spring:message code="administrator.dashboard.conference.fee"
	var="conferenceFeeHeader" />
<spring:message code="administrator.dashboard.conference.registrations"
	var="conferenceRegistrationsHeader" />
<spring:message code="administrator.dashboard.conference.submissions"
	var="conferenceSubmissionsHeader" />
<spring:message code="administrator.dashboard.conference.days"
	var="conferenceDaysHeader" />
<spring:message code="administrator.dashboard.conference.category"
	var="conferenceCategoryHeader" />
<spring:message code="administrator.dashboard.comments.conference"
	var="commentsConferenceHeader" />
<spring:message code="administrator.dashboard.comments.activity"
	var="commentsActivityHeader" />

<!--  Custom table style -->
<head>
<link rel="stylesheet" href="styles/tablas.css" type="text/css">
<link rel="stylesheet" href="styles/charts.css" type="text/css">
</head>


<!-- C level -->

<table>
	<caption>
		<jstl:out value="${conferenceFeeHeader}"></jstl:out>
	</caption>
	<tr>
		<th><jstl:out value="${avgHeader}"></jstl:out></th>
		<th><jstl:out value="${minHeader}"></jstl:out></th>
		<th><jstl:out value="${maxHeader}"></jstl:out></th>
		<th><jstl:out value="${stdHeader}"></jstl:out></th>
	</tr>

	<tr>
		<td><jstl:out value="${avgFeeConference}"></jstl:out></td>
		<td><jstl:out value="${minFeeConference}"></jstl:out></td>
		<td><jstl:out value="${maxFeeConference}"></jstl:out></td>
		<td><jstl:out value="${stddevFeeConference}"></jstl:out></td>
	</tr>
</table>
<br />


<table>
	<caption>
		<jstl:out value="${conferenceRegistrationsHeader}"></jstl:out>
	</caption>
	<tr>
		<th><jstl:out value="${avgHeader}"></jstl:out></th>
		<th><jstl:out value="${minHeader}"></jstl:out></th>
		<th><jstl:out value="${maxHeader}"></jstl:out></th>
		<th><jstl:out value="${stdHeader}"></jstl:out></th>
	</tr>

	<tr>
		<td><jstl:out value="${avgRegistrationsConference}"></jstl:out></td>
		<td><jstl:out value="${minRegistrationsConference}"></jstl:out></td>
		<td><jstl:out value="${maxRegistrationsConference}"></jstl:out></td>
		<td><jstl:out value="${stddevRegistrationsConference}"></jstl:out></td>
	</tr>
</table>
<br />


<table>
	<caption>
		<jstl:out value="${conferenceSubmissionsHeader}"></jstl:out>
	</caption>
	<tr>
		<th><jstl:out value="${avgHeader}"></jstl:out></th>
		<th><jstl:out value="${minHeader}"></jstl:out></th>
		<th><jstl:out value="${maxHeader}"></jstl:out></th>
		<th><jstl:out value="${stdHeader}"></jstl:out></th>
	</tr>

	<tr>
		<td><jstl:out value="${avgSubmissionsConference}"></jstl:out></td>
		<td><jstl:out value="${minSubmissionsConference}"></jstl:out></td>
		<td><jstl:out value="${maxSubmissionsConference}"></jstl:out></td>
		<td><jstl:out value="${stddevSubmissionsConference}"></jstl:out></td>
	</tr>
</table>
<br />


<table>
	<caption>
		<jstl:out value="${conferenceDaysHeader}"></jstl:out>
	</caption>
	<tr>
		<th><jstl:out value="${avgHeader}"></jstl:out></th>
		<th><jstl:out value="${minHeader}"></jstl:out></th>
		<th><jstl:out value="${maxHeader}"></jstl:out></th>
		<th><jstl:out value="${stdHeader}"></jstl:out></th>
	</tr>

	<tr>
		<td><jstl:out value="${avgDaysConference}"></jstl:out></td>
		<td><jstl:out value="${minDaysConference}"></jstl:out></td>
		<td><jstl:out value="${maxDaysConference}"></jstl:out></td>
		<td><jstl:out value="${stddevDaysConference}"></jstl:out></td>
	</tr>
</table>
<br />


<table>
	<caption>
		<jstl:out value="${conferenceCategoryHeader}"></jstl:out>
	</caption>
	<tr>
		<th><jstl:out value="${avgHeader}"></jstl:out></th>
		<th><jstl:out value="${minHeader}"></jstl:out></th>
		<th><jstl:out value="${maxHeader}"></jstl:out></th>
		<th><jstl:out value="${stdHeader}"></jstl:out></th>
	</tr>

	<tr>
		<td><jstl:out value="${avgConferenceCategory}"></jstl:out></td>
		<td><jstl:out value="${minConferenceCategory}"></jstl:out></td>
		<td><jstl:out value="${maxConferenceCategory}"></jstl:out></td>
		<td><jstl:out value="${stddevConferenceCategory}"></jstl:out></td>
	</tr>
</table>
<br />


<table>
	<caption>
		<jstl:out value="${commentsConferenceHeader}"></jstl:out>
	</caption>
	<tr>
		<th><jstl:out value="${avgHeader}"></jstl:out></th>
		<th><jstl:out value="${minHeader}"></jstl:out></th>
		<th><jstl:out value="${maxHeader}"></jstl:out></th>
		<th><jstl:out value="${stdHeader}"></jstl:out></th>
	</tr>

	<tr>
		<td><jstl:out value="${avgCommentsConference}"></jstl:out></td>
		<td><jstl:out value="${minCommentsConference}"></jstl:out></td>
		<td><jstl:out value="${maxCommentsConference}"></jstl:out></td>
		<td><jstl:out value="${stddevCommentsConference}"></jstl:out></td>
	</tr>
</table>
<br />



<table>
	<caption>
		<jstl:out value="${commentsActivityHeader}"></jstl:out>
	</caption>
	<tr>
		<th><jstl:out value="${avgHeader}"></jstl:out></th>
		<th><jstl:out value="${minHeader}"></jstl:out></th>
		<th><jstl:out value="${maxHeader}"></jstl:out></th>
		<th><jstl:out value="${stdHeader}"></jstl:out></th>
	</tr>

	<tr>
		<td><jstl:out value="${avgCommentsActivity}"></jstl:out></td>
		<td><jstl:out value="${minCommentsActivity}"></jstl:out></td>
		<td><jstl:out value="${maxCommentsActivity}"></jstl:out></td>
		<td><jstl:out value="${stddevCommentsActivity}"></jstl:out></td>
	</tr>
</table>
<br />