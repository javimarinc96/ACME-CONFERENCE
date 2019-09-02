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
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

	
	<!-- Attributes -->
	<jstl:if test="${pageContext.response.locale == 'en'}">
	<b><spring:message code="category.name"/></b>: <jstl:out value="${category.name}"/>
    <br/>
    </jstl:if>
    
    <jstl:if test="${pageContext.response.locale == 'es'}">
    <b><spring:message code="category.nombre"/></b>: <jstl:out value="${category.nombre}"/>
    <br/>
    </jstl:if>
    
    <b><spring:message code="category.parent"/></b>: <jstl:out value="${category.parent.name}"/>
    <br/>
    
<!-- Cancel -->

<security:authorize access="hasRole('ADMIN')">	
				<button type="button" onclick="javascript: relativeRedir('category/administrator/list.do')">
				<spring:message code="category.cancel" />
				</button>
</security:authorize>

</html>
	