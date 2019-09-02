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



<!--Tabla-->

<display:table name="categories" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">
	
		
	<jstl:if test="${pageContext.response.locale == 'en'}">
	<display:column property="name" titleKey="category.name"/>
	</jstl:if>
	
	<jstl:if test="${pageContext.response.locale == 'es'}">
	<display:column property="nombre" titleKey="category.nombre"/>
	</jstl:if>
	
	
	<display:column  titleKey="category.show">
		<input type="submit" name="show" value="<spring:message code="category.show" />"
				onclick="javascript: relativeRedir('category/administrator/show.do?categoryId=${row.id}');" />
	</display:column>
	
	<security:authorize access="hasRole('ADMIN')">
	<display:column titleKey="category.edit">
		<jstl:if test="${row.name != 'CONFERENCE'}">
				<input type="submit" name="edit" value="<spring:message code="category.edit" />"
				onclick="javascript: relativeRedir('category/administrator/edit.do?categoryId=${row.id}');" />
		</jstl:if>
			<jstl:if test="${row.name == 'CONFERENCE'}">
				<spring:message code="category.no.edit" />
		</jstl:if>
		</display:column>
		
		<display:column  titleKey="category.delete">
		<jstl:if test="${row.name != 'CONFERENCE'}">
				<input type="submit" name="delete" value="<spring:message code="category.delete" />"
				onclick="javascript: relativeRedir('category/administrator/delete.do?categoryId=${row.id}');" />
				</jstl:if>
				<jstl:if test="${row.name == 'CONFERENCE'}">
				<spring:message code="category.no.edit" />
		</jstl:if>
		</display:column>
	</security:authorize>

</display:table>


<security:authorize access="hasRole('ADMIN')">
			<input type="submit" name="create" value="<spring:message code="category.create" />"
				onclick="javascript: relativeRedir('category/administrator/create.do');" />
</security:authorize>