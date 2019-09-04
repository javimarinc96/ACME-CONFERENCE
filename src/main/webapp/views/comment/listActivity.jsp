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


<display:table name="comments" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">
	
	<spring:message code="comment.title" var="titleHeader"/>
	<display:column property="title" title="${titleHeader}" />
	
	<spring:message code="comment.author" var="authorHeader"/>
	<display:column property="author" title="${authorHeader}" />
	
	<spring:message code="comment.text" var="textHeader"/>
	<display:column property="text" title="${textHeader}" />
	
	<spring:message code="comment.moment" var="momentHeader"/>
	<display:column property="moment" title="${momentHeader}" />
	
	
</display:table>


	<input type="submit" name="create" value="<spring:message code="comment.create" />"
		onclick="javascript: relativeRedir('comment/createActivity.do?activityId=${activityId}');" />