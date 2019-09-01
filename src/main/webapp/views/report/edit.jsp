<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<form:form action="report/reviewer/save.do" modelAttribute="report" method="post">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="reviewer" />
	
	<form:label path="originalityScore">
		<spring:message code="report.originalityScore"/>
    	</form:label>
	<form:input path="originalityScore"/>
    	<form:errors cssClass="error" path="originalityScore" />
    <br>

	<form:label path="readabilityScore">
		<spring:message code="report.readabilityScore"/>
    	</form:label>
	<form:input path="readabilityScore"/>
    	<form:errors cssClass="error" path="readabilityScore" />
    <br>
    
    <form:label path="qualityScore">
		<spring:message code="report.qualityScore"/>
    </form:label>
	<form:input path="qualityScore"/>
    	<form:errors cssClass="error" path="qualityScore" />
    <br>
    
    <form:label path="decision">
		<spring:message code="report.decision"/>
    	</form:label>
	<form:input path="decision"/>
    	<form:errors cssClass="error" path="decision" />
    <br>
    
     <form:label path="comments">
		<spring:message code="report.comments"/>
    	</form:label>
	<form:input path="comments"/>
    	<form:errors cssClass="error" path="comments" />
    <br>
    
    <form:label path="submission">
	<spring:message code="report.submission" />:
	</form:label>
	<form:select path="submission" >
	<form:options items="${submissions}" itemLabel="ticker" />
	</form:select>	
	<br>
    
	<input type="submit" name="save"
		value="<spring:message code="report.save"/>" />

	<input type="button" name="cancel"
		value="<spring:message code="report.cancel" />"
		onclick="javascript: relativeRedir('report/reviewer/list.do');" />


</form:form>