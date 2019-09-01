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


<display:table name="reports" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">
	
	<spring:message code="report.originalityScore" var="originalityHeader"/>
	<display:column property="originalityScore" title="${originalityHeader}" />
	
	<spring:message code="report.qualityScore" var="qualityHeader"/>
	<display:column property="qualityScore" title="${qualityHeader}" />
	
	<spring:message code="report.readabilityScore" var="readabilityScoreHeader"/>
	<display:column property="readabilityScore" title="${readabilityScoreHeader}" />
	
	<spring:message code="report.decision" var="decisionHeader"/>
	<display:column property="decision" title="${decisionHeader}" />
	
	<spring:message code="report.comments" var="commentsHeader"/>
	<display:column property="comments" title="${commentsHeader}" />
	
	<display:column titleKey="report.show">
		<input type="submit" name="show" value="<spring:message code="report.show" />"
			onclick="javascript: relativeRedir('report/reviewer/show.do?reportId=${row.id}');" />
	</display:column>
	
	<security:authorize access="hasRole('REVIEWER')">
	<display:column titleKey="report.delete">
		<input type="submit" name="delete" value="<spring:message code="report.delete" />"
			onclick="javascript: relativeRedir('report/reviewer/delete.do?reportId=${row.id}');" />
	</display:column>
	</security:authorize>
	

</display:table>


<security:authorize access="hasRole('REVIEWER')">
	<input type="submit" name="create" value="<spring:message code="report.create" />"
		onclick="javascript: relativeRedir('report/reviewer/create.do');" />
</security:authorize>

<security:authorize access="hasRole('AUTHOR')">
	<input type="submit" name="cancel" value="<spring:message code="report.cancel" />"
		onclick="javascript: relativeRedir('submission/author/list.do');" />
</security:authorize>