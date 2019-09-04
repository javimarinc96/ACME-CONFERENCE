<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<form:form action="comment/save.do" modelAttribute="comment" method="post">

	<form:hidden path="id" />
	<form:hidden path="moment" />
	<form:hidden path="author" />
	<form:hidden path="conference" />
	<form:hidden path="activity" />
	<form:hidden path="version" />
	
	
    <form:label path="title">
		<spring:message code="comment.title"/>
    	</form:label>
	<form:input path="title"/>
    	<form:errors cssClass="error" path="title" />
    <br>
    
    <form:label path="text">
		<spring:message code="comment.text"/>
    	</form:label>
	<form:input path="text"/>
    	<form:errors cssClass="error" path="text" />
    <br>
    
    
	<input type="submit" name="save"
		value="<spring:message code="comment.save"/>" />

	<jstl:if test="${comment.activity != null}">
	<input type="button" name="cancel"
		value="<spring:message code="comment.cancel" />"
		onclick="javascript: relativeRedir('comment/listActivity.do?activityId=${comment.activity.id}');" />
	</jstl:if>
	<jstl:if test="${comment.activity == null}">
	<input type="button" name="cancel"
		value="<spring:message code="comment.cancel" />"
		onclick="javascript: relativeRedir('comment/listConference.do?conferenceId=${comment.conference.id}');" />
	</jstl:if>

</form:form>