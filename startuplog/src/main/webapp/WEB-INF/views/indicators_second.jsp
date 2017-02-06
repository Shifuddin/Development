<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- let's add tag srping:url -->
<spring:url value="/resources/css/styles.css" var="startupLogCSS" />
<spring:url value="/resources/js/jquery-3.1.1.min.js" var="startupLogJS" />
<link href="${startupLogCSS}" rel="stylesheet" />
<script src="${startupLogJS}"></script>
<title>StartupLog</title>
</head>
<body>

	<div class="container">
		<form id="survey-form" class="form" action="/stages" method="POST"
			enctype="multipart/form-data">
			<div id="indicators">
				<div class="boundary">
					<ul class="hiddenbulletpoints">
						<li>
							<div class="label head">Role of the founder</div>
						</li>
						<li class="choice"><input type="checkbox" id="survey_q1a1"
							class="hidden" name="rf_1" /> <label
							class="label checkmark highlight" for="survey_q1a1">
								Personal handling of all issues </label></li>
						<li class="choice"><input type="checkbox" id="survey_q1a2"
							class="hidden" name="rf_2" /> <label
							class="label checkmark highlight" for="survey_q1a2"> Take
								over of operational as well as strategic tasks/acitivities </label></li>
						<li class="choice"><input type="checkbox" id="survey_q1a3"
							class="hidden" name="rf_3" /> <label
							class="label checkmark highlight" for="survey_q1a3"> Take
								over of mainly strategic tasks/activities </label></li>
						<li class="choice"><input type="checkbox" id="survey_q1a4"
							class="hidden" name="rf_4" /> <label
							class="label checkmark highlight" for="survey_q1a4"> Take
								over of exclusively strategic tasks/activities </label></li>
						<li class="choice"><input type="checkbox" id="survey_q1a5"
							class="hidden" name="rf_5" /> <label
							class="label checkmark highlight" for="survey_q1a5">
								Delegation of strategic tasks/activities </label></li>
					</ul>
				</div>
				<div class="boundary">
					<ul class="hiddenbulletpoints">
						<li>
							<div class="label head">Standardization</div>
						</li>
						<li class="choice"><input type="checkbox" id="survey_q2a1"
							class="hidden" name="st_1" /> <label
							class="label checkmark highlight" for="survey_q2a1">
								Processes do not exist, mainly nonrecurring operations </label></li>
						<li class="choice"><input type="checkbox" id="survey_q2a2"
							class="hidden" name="st_2" /> <label
							class="label checkmark highlight" for="survey_q2a2">
								Recording, standardizing and formalization of processes </label></li>
						<li class="choice"><input type="checkbox" id="survey_q2a3"
							class="hidden" name="st_3" /> <label
							class="label checkmark highlight" for="survey_q2a3">
								Establishing standard processes </label></li>
						<li class="choice"><input type="checkbox" id="survey_q2a4"
							class="hidden" name="st_4" /> <label
							class="label checkmark highlight" for="survey_q2a4">
								Optimization of standard processes </label></li>
						<li class="choice"><input type="checkbox" id="survey_q2a5"
							class="hidden" name="st_5" /> <label
							class="label checkmark highlight" for="survey_q2a5"> High
								efficiency of core processes </label></li>
					</ul>
				</div>
				<div class="boundary">
					<ul class="hiddenbulletpoints">
						<li>
							<div class="label head">Organizational structures</div>
						</li>
						<li class="choice"><input type="checkbox" id="survey_q3a1"
							class="hidden" name="os_1" /> <label
							class="label checkmark highlight" for="survey_q3a1">
								Organizational structures do not exist </label></li>
						<li class="choice"><input type="checkbox" id="survey_q3a2"
							class="hidden" name="os_2" /> <label
							class="label checkmark highlight" for="survey_q3a2">
								Generating of first organizational structures and hierarchy
								levels </label></li>
						<li class="choice"><input type="checkbox" id="survey_q3a3"
							class="hidden" name="os_3" /> <label
							class="label checkmark highlight" for="survey_q3a3">
								Building of departments </label></li>
						<li class="choice"><input type="checkbox" id="survey_q3a4"
							class="hidden" name="os_4" /> <label
							class="label checkmark highlight" for="survey_q3a4">
								Permanent organizational structures </label></li>
					</ul>
				</div>
				<div class="boundary">
					<ul class="hiddenbulletpoints flat">
						<li>
							<div class="label head flat">Market uncertainty</div>
						</li>
						<li class="choice"><input type="checkbox" id="survey_q4a1"
							class="hidden" name="mu_1" /> <label
							class="label checkmark highlight" for="survey_q4a1">
								Extremely high </label></li>
						<li class="choice"><input type="checkbox" id="survey_q4a2"
							class="hidden" name="mu_2" checked /> <label
							class="label checkmark highlight" for="survey_q4a2"> Very
								high </label></li>
						<li class="choice"><input type="checkbox" id="survey_q4a3"
							class="hidden" name="mu_3" /> <label
							class="label checkmark highlight" for="survey_q4a3"> High
						</label></li>
						<li class="choice"><input type="checkbox" id="survey_q4a4"
							class="hidden" name="mu_4" /> <label
							class="label checkmark highlight" for="survey_q4a4"> Low
						</label></li>
						<li class="choice"><input type="checkbox" id="survey_q4a5"
							class="hidden" name="mu_5" /> <label
							class="label checkmark highlight" for="survey_q4a5"> Very
								low </label></li>
						<li class="choice"><input type="checkbox" id="survey_q4a6"
							class="hidden" name="mu_6" /> <label
							class="label checkmark highlight" for="survey_q4a6">
								Hardly still noticeable </label></li>
					</ul>
				</div>
				<div class="boundary">
					<ul class="hiddenbulletpoints">
						<li>
							<div class="label head">Technology Readiness Level</div>
						</li>
						<li class="choice"><input type="checkbox" id="survey_q5a1"
							class="hidden" name="tr_1" /> <label
							class="label checkmark highlight" for="survey_q5a1">
								Basic and technology research </label></li>
						<li class="choice"><input type="checkbox" id="survey_q5a2"
							class="hidden" name="tr_2" /> <label
							class="label checkmark highlight" for="survey_q5a2">
								Phrasing the technical concept </label></li>
						<li class="choice"><input type="checkbox" id="survey_q5a3"
							class="hidden" name="tr_3" /> <label
							class="label checkmark highlight" for="survey_q5a3">
								Studies for the technical feasibility </label></li>
						<li class="choice"><input type="checkbox" id="survey_q5a4"
							class="hidden" name="tr_4" /> <label
							class="label checkmark highlight" for="survey_q5a4">
								Analytical and experimental evidence of critical functions </label></li>
						<li class="choice"><input type="checkbox" id="survey_q5a5"
							class="hidden" name="tr_5" /> <label
							class="label checkmark highlight" for="survey_q5a5">
								Experimental setup in the laboratory and in the operational
								environment </label></li>
						<li class="choice"><input type="checkbox" id="survey_q5a6"
							class="hidden" name="tr_6" /> <label
							class="label checkmark highlight" for="survey_q5a6">
								Product launch and operations </label></li>
						<li class="choice"><input type="checkbox" id="survey_q5a7"
							class="hidden" name="tr_7" /> <label
							class="label checkmark highlight" for="survey_q5a7">
								Qualified product with the evidence of successful use in the
								market </label></li>
					</ul>
				</div>
			</div>
			<div style="clear: both"></div>
			<div id="next" class="table-row navigation">
				<button class="form_button">next</button>
			</div>
		</form>
	</div>
</body>
</html>