<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<form:form action="section/administrator/save.do?tutorialId=${tutorialId}" modelAttribute="section" method="post">

	<form:hidden path="id" />
	<form:hidden path="version" />
	
	
    <form:label path="title">
		<spring:message code="section.title"/>
    	</form:label>
	<form:input path="title"/>
    	<form:errors cssClass="error" path="title" />
    <br>
    
     <form:label path="summary">
		<spring:message code="section.summary"/>
    	</form:label>
	<form:input path="summary"/>
    	<form:errors cssClass="error" path="summary" />
    <br>
    
     <form:label path="pictures">
		<spring:message code="section.pictures"/>
    	</form:label>
	<form:input path="pictures"/>
    	<form:errors cssClass="error" path="pictures" />
    <br>
    
    
	<input type="submit" name="save"
		value="<spring:message code="section.save"/>" />

	<input type="button" name="cancel"
		value="<spring:message code="section.cancel" />"
		onclick="javascript: relativeRedir('tutorial/administrator/show.do?tutorialId=${tutorialId}');" />


</form:form>