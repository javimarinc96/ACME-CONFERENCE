<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<form:form action="topic/administrator/save.do" modelAttribute="topic" method="post">

	<form:hidden path="id" />
	<form:hidden path="version" />
	
	
    <form:label path="spanishName">
		<spring:message code="topic.spanishName"/>
    	</form:label>
	<form:input path="spanishName"/>
    	<form:errors cssClass="error" path="spanishName" />
    <br>
    
     <form:label path="englishName">
		<spring:message code="topic.englishName"/>
    	</form:label>
	<form:input path="englishName"/>
    	<form:errors cssClass="error" path="englishName" />
    <br>
    
    
	<input type="submit" name="save"
		value="<spring:message code="topic.save"/>" />

	<input type="button" name="cancel"
		value="<spring:message code="topic.cancel" />"
		onclick="javascript: relativeRedir('topic/administrator/list.do');" />


</form:form>