<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<form:form action="submission/author/saveCamera.do" modelAttribute="submission" method="post">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="author" />
	<form:hidden path="moment" />
	<form:hidden path="ticker" />
	<form:hidden path="status" />
	<form:hidden path="conference" />
	<form:hidden path="paper" />

	
	<form:label path="cameraReady.title">
		<spring:message code="submission.title"/>
    	</form:label>
	<form:input path="cameraReady.title"/>
    	<form:errors cssClass="error" path="cameraReady.title" />
    <br>

	<form:label path="cameraReady.authors">
		<spring:message code="submission.authors"/>
    	</form:label>
	<form:input path="cameraReady.authors"/>
    	<form:errors cssClass="error" path="cameraReady.authors" />
    <br>
    
    <form:label path="cameraReady.summary">
		<spring:message code="submission.summary"/>
    	</form:label>
	<form:input path="cameraReady.summary"/>
    	<form:errors cssClass="error" path="cameraReady.summary" />
    <br>
    
    <form:label path="cameraReady.document">
		<spring:message code="submission.document"/>
    	</form:label>
	<form:input path="cameraReady.document"/>
    	<form:errors cssClass="error" path="cameraReady.document" />
    <br>
    
	<input type="submit" name="save"
		value="<spring:message code="submission.save"/>" />

	<input type="button" name="cancel"
		value="<spring:message code="submission.cancel" />"
		onclick="javascript: relativeRedir('submission/author/list.do');" />


</form:form>