<%--
 * action-1.jsp
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
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<form:form action="customization/administrator/edit.do" modelAttribute="customization">

	<form:hidden path="id" />
	<form:hidden path="version" />
	
	<form:label path="systemName">
		<spring:message code="customization.systemName" />:
	</form:label>
	<form:input path="systemName" />
	<form:errors cssClass="error" path="systemName" />
	<br />

	<form:label path="banner">
		<spring:message code="customization.banner" />:
	</form:label>
	<form:input path="banner" />
	<form:errors cssClass="error" path="banner" />
	<br />

	<form:label path="welcomeMessageEn">
		<spring:message code="customization.welcomeMessageEn" />:
	</form:label>
	<form:input path="welcomeMessageEn" />
	<form:errors cssClass="error" path="welcomeMessageEn" />
	<br />
	
	<form:label path="welcomeMessageEs">
		<spring:message code="customization.welcomeMessageEs" />:
	</form:label>
	<form:input path="welcomeMessageEs" />
	<form:errors cssClass="error" path="welcomeMessageEs" />
	<br />
	
	<form:label path="countryCode">
		<spring:message code="customization.countryCode" />:
	</form:label>
	<form:input path="countryCode" />
	<form:errors cssClass="error" path="countryCode" />
	<br />
	
	<form:label path="brandNames">
		<spring:message code="customization.brandNames" />:
	</form:label>
	<form:input path="brandNames" />
	<form:errors cssClass="error" path="brandNames" />
	<br />

	<input type="submit" name="save"
		value="<spring:message code="customization.save" />" />&nbsp; 
		
	<input type="button" name="cancel"
		value="<spring:message code="customization.cancel" />"
		onclick="javascript: relativeRedir('');" />
	<br />


</form:form>