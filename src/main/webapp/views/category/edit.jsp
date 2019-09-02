<%--
 * create.jsp
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


<form:form action="category/administrator/create.do" modelAttribute="category" method="post">

<!--Ocultos-->

    <form:hidden path="id" />
    <form:hidden path="version" />  

	<!--Name-->
	<form:label path="name">
		<spring:message code="category.name"/>
    	</form:label>
	<form:input path="name"/>
    	<form:errors cssClass="error" path="name" />
    <br/>
    
    <!--Nombre-->
	<form:label path="nombre">
		<spring:message code="category.nombre"/>
    	</form:label>
	<form:input path="nombre"/>
    	<form:errors cssClass="error" path="nombre" />
    <br/>

    <!--Parent Category-->
    <form:label path="parent">
        <spring:message code="category.parent"/>
        </form:label>
    <form:select id="categories" path="parent">
        <form:options items="${categories}" itemLabel="name" itemValue="id"/>
        <form:option value="0" label="------"/>
    </form:select>
    <form:errors cssClass="error" path="parent"/>
    <br/>
    	

	<input type="submit" name="save" value="<spring:message code="category.save"/>"/>
	
	<input type="button" name="cancel"
		value="<spring:message code="category.cancel" />"
		onclick="javascript: relativeRedir('category/administrator/list.do');" />
	<br/>

</form:form>