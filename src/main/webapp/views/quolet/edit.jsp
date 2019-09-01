<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>


<form:form action="quolet/administrator/save.do" modelAttribute="quolet" method="post">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="moment" />
	<form:hidden path="ticker" />
	<form:hidden path="administrator" />
	<jstl:if test='${quolet.id != 0}'>
	<form:hidden path="conference" />
	</jstl:if>
	
	
	<acme:textbox code="quolet.body" path="body" />
	<br/>
	
	<acme:textbox code="quolet.photo" path="photo" />
	<br/>

	<jstl:if test='${quolet.draftMode == true}'>
		<form:label path="draftMode">
		<spring:message code="conference.draftMode"/>
    	</form:label>
	<form:checkbox path="draftMode"/>
    	<form:errors cssClass="error" path="draftMode" />
	<br/>	
	<br/>		
	</jstl:if>
	
	<jstl:if test='${quolet.id == 0}'>
	
	<form:label path="conference">
	<spring:message code="quolet.conference" />:
	</form:label>
	
	<form:select path="conference" >
	<form:options items="${conferences}" itemLabel="title" />
	</form:select>	
	
	</jstl:if>

	<br />	
	<br />	
	
	<input type="submit" name="save"
		value="<spring:message code="quolet.save"/>" />

	<input type="button" name="cancel"
		value="<spring:message code="quolet.cancel" />"
		onclick="javascript: relativeRedir('quolet/administrator/list.do');" />


</form:form>