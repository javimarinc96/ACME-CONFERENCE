<%--
 * header.jsp
 *
 * Copyright (C) 2019 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<div>
	<a href="#"><img src="images/acme-conference.png" alt="Acme Conference., Inc." /></a>
</div>

<div>
	<ul id="jMenu">
		<!-- Do not forget the "fNiv" class for the first level links !! -->
		
		
		<security:authorize access="hasRole('ADMIN')">
			<li><a class="fNiv"><spring:message	code="master.page.administrator" /></a>
				<ul>
					<li class="arrow"></li>
					<!-- <li><a href="quolet/administrator/list.do"><spring:message code="master.page.quolet.list" /></a></li>  -->
					<li><a href="administrator/create.do"><spring:message code="master.page.administrator.create" /></a></li>
					<li><a href="conference/administrator/list.do"><spring:message code="master.page.conference.list" /></a></li>
					<li><a href="category/administrator/list.do"><spring:message code="master.page.category.list" /></a></li>
					<li><a href="conference/administrator/create.do"><spring:message code="master.page.conference.create" /></a></li>
					<li><a href="administrator/dashboard.do"><spring:message code="master.page.administrator.dashboard" /></a></li>
					<li><a href="topic/administrator/list.do"><spring:message code="master.page.topic.list" /></a></li>
					<li><a href="customization/administrator/edit.do"><spring:message code="master.page.customization" /></a></li>
				</ul>
			</li>
		</security:authorize>
		
		<security:authorize access="hasRole('AUTHOR')">
			<li><a class="fNiv"><spring:message	code="master.page.author" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="submission/author/list.do"><spring:message code="master.page.submission.list" /></a></li>
					<li><a href="registration/author/list.do"><spring:message code="master.page.registration.list" /></a></li>
					<li><a href="finder/author/edit.do"><spring:message code="master.page.finder.edit" /></a></li>
				</ul>
			</li>
		</security:authorize>
		
		<security:authorize access="hasRole('REVIEWER')">
			<li><a class="fNiv"><spring:message	code="master.page.reviewer" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="report/reviewer/list.do"><spring:message code="master.page.report.list" /></a></li>
				</ul>
			</li>
		</security:authorize>
		
		
		<security:authorize access="isAnonymous()">
			<li><a class="fNiv" href="security/login.do"><spring:message code="master.page.login" /></a></li>
			<li>
			<a class="fNiv"><spring:message	code="master.page.register" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="author/create.do"><spring:message code="master.page.author.create" /></a></li>
					<li><a href="reviewer/create.do"><spring:message code="master.page.reviewer.create" /></a></li>
				</ul>
			</li>
			<li>
			<a class="fNiv"><spring:message	code="master.page.conferences" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="conference/administrator/listForthcoming.do"><spring:message code="master.page.forthcoming" /></a></li>
					<li><a href="conference/administrator/listRunning.do"><spring:message code="master.page.running" /></a></li>
					<li><a href="conference/administrator/listPast.do"><spring:message code="master.page.past" /></a></li>
				</ul>
			</li>
		</security:authorize>
		
		<security:authorize access="isAuthenticated()">
			<li>
			<a class="fNiv"><spring:message	code="master.page.conferences" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="conference/administrator/listForthcoming.do"><spring:message code="master.page.forthcoming" /></a></li>
					<li><a href="conference/administrator/listRunning.do"><spring:message code="master.page.running" /></a></li>
					<li><a href="conference/administrator/listPast.do"><spring:message code="master.page.past" /></a></li>
				</ul>
			</li>
			<li>
			<a class="fNiv"><spring:message	code="master.page.messages" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="message/actor/create.do"><spring:message code="master.page.message.create" /></a></li>
					<li><a href="message/actor/list.do"><spring:message code="master.page.message.list" /></a></li>
					<security:authorize access="hasRole('ADMIN')">
						<li><a href="message/actor/broadcast.do"><spring:message code="master.page.broadcast" /></a></li>
						<li><a href="message/actor/broadcastAuthors.do"><spring:message code="master.page.broadcastAuthors" /></a></li>
						<li><a href="message/actor/broadcastRegisteredAuthors.do"><spring:message code="master.page.broadcastRegisteredAuthors" /></a></li>
						<li><a href="message/actor/broadcastSubmissionAuthors.do"><spring:message code="master.page.broadcastSubmissionAuthors" /></a></li>
					</security:authorize>
				</ul>
			</li>
			<li>
				<a class="fNiv"> 
					<spring:message code="master.page.profile" /> 
			        (<security:authentication property="principal.username" />)
				</a>
				<ul>
					<li class="arrow"></li>
					<security:authorize access="hasRole('ADMIN')">
					<li><a href="administrator/edit.do"><spring:message code="master.page.administrator.edit" /></a></li>
					</security:authorize>
					<security:authorize access="hasRole('AUTHOR')">
					<li><a href="author/edit.do"><spring:message code="master.page.author.edit" /></a></li>
					</security:authorize>
					<security:authorize access="hasRole('REVIEWER')">
					<li><a href="reviewer/edit.do"><spring:message code="master.page.reviewer.edit" /></a></li>
					</security:authorize>
					<li><a href="j_spring_security_logout"><spring:message code="master.page.logout" /> </a></li>
				</ul>
			</li>
		</security:authorize>
	</ul>
</div>

<div>
	<a href="?language=en">en</a> | <a href="?language=es">es</a>
</div>

