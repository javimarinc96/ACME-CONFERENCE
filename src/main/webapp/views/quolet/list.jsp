<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<display:table name="quolets" id="row" pagesize="5" requestURI="${requestURI}" class="displaytag">
	
	<spring:message code="quolet.moment.pattern" var="momentPattern" />
	
	<spring:message code="quolet.ticker" var="ticker" />
	<display:column property="ticker" title="${ticker}" />
	
	<spring:message code="quolet.moment" var="moment" />
	<display:column property="moment" title="${moment}" format="${momentPattern}" />
	
	<spring:message code="quolet.body" var="body" />
	<display:column property="body" title="${body}" />
	

	<security:authorize access="hasRole('ADMIN')">
		<display:column titleKey="quolet.show">
			<input type="submit" name="show" value="<spring:message code="quolet.show" />"
				onclick="javascript: relativeRedir('quolet/administrator/show.do?quoletId=${row.id}');" />
		</display:column>
	</security:authorize>
	
	<security:authorize access="hasRole('ADMIN')">
		<display:column titleKey="quolet.edit">
		<jstl:if test="${row.draftMode eq true}">
		<input type="submit" name="edit" value="<spring:message code="quolet.edit" />"
				onclick="javascript: relativeRedir('quolet/administrator/edit.do?quoletId=${row.id}');" />
				</jstl:if>
			<jstl:if test="${row.draftMode eq false}">
			<spring:message code="quolet.no.draft" />
		 </jstl:if>
		</display:column>
	</security:authorize>
	
	<security:authorize access="hasRole('ADMIN')">
		<display:column titleKey="quolet.delete">
		<jstl:if test="${row.draftMode eq true}">
			<input type="submit" name="delete" value="<spring:message code="quolet.delete" />"
				onclick="javascript: relativeRedir('quolet/administrator/delete.do?quoletId=${row.id}');" />
				</jstl:if>
					<jstl:if test="${row.draftMode eq false}">
			<spring:message code="quolet.no.draft" />
		 </jstl:if>
		</display:column>
	</security:authorize>
	
	
</display:table>

<br/>

<security:authorize access="hasRole('ADMIN')">
	<input type="submit" name="create"
		value="<spring:message code="quolet.create" />"
		onclick="javascript: relativeRedir('quolet/administrator/create.do');" />
</security:authorize>