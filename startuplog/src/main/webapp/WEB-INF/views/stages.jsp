<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
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
<script type="text/javascript">

        (function ($) {
            $.fn.showInfoDiv = function ()
            {
                return this.each(function ()
                {
                    $('#selected-stages .table > div[id^=' + $(this).attr("for") + ']').show();
                });
            };
        }(jQuery));

        $(document).ready(function ()
        {
            $("#stages-schematic .suggestion").showInfoDiv();
            $("#stages-schematic .label").click(function ()
            {
                $("#selected-stages .table > div").hide();
                $(this).showInfoDiv();
            });
        });
    </script>
</head>
<body>
    <div class="info">
        <p><b>Anhand der von Ihnen gew&auml;hlten Kriterien befinden Sie sich in der/den gr&uuml;n markierten Entwicklungsphase(n).</b></p>
        <p>Sollten Sie sich nach <b>Pr&uuml;fung der rechts aufgelisteten Merkmale</b> in einer <b>anderen Entwicklungs- phase</b> sehen, dann k&ouml;nnen Sie durch Anklicken der entsprechenden Entwicklungs- phase in der obigen Grafik diese ausw&auml;hlen.</p>
        <p>Mit dem Klick auf den jeweiligen <b>"w&auml;hlen"-Button</b>, gelangen Sie zur Modul-&Uuml;bersicht.</p>
        <p>Hinweis: Die "Startup Phase" stellt vier Wahlm&ouml;glichkeiten zur Verf&uuml;gung.</p>
    </div>
    <div class="container">
        <div id="stages" class="form">
            <div id="stages-schematic" class="boundary">
                <div id="arrow-tail"><span class="arrow-cutout"></span></div>
                <div id="arrow-head"><span class="arrow-cutout"></span></div>
                <ul class="hiddenbulletpoints">
                    <li>
                        <input type="radio" id="Pre-Seed_Stage" name="selection" class="hidden"/>
                        <label for="Pre-Seed_Stage" class="label ${stage0}">
                            <strong>Pre-Seed Stage</strong>
                            <span>Generation of ideas</span>
                        </label>
                    </li>
                    <li>
                        <input type="radio" id="Seed_Stage" name="selection" class="hidden" />
                        <label for="Seed_Stage" class="label ${stage1}">
                            <strong>Seed Stage</strong>
                            <span>Business planning</span>
                        </label>
                    </li>
                    <li>
                        <input type="radio" id="Startup_Stage" name="selection" class="hidden" />
                        <label for="Startup_Stage" class="label ${stage2}">
                            <strong>Startup Stage</strong>
                            <span>Product development of the initial product</span>
                        </label>
                    </li>
                    <li>
                        <input type="radio" id="First_Stage" name="selection" class="hidden" />
                        <label for="First_Stage" class="label ${stage3}">
                            <strong>First Stage</strong>
                            <span>Start of production and market launch</span>
                        </label>
                    </li>
                    <li>
                        <input type="radio" id="Second_Stage" name="selection" class="hidden" />
                        <label for="Second_Stage" class="label ${stage4}">
                            <strong>Second Stage</strong>
                            <span>Growth phase</span>
                        </label>
                    </li>
                    <li>
                        <input type="radio" id="Third_Stage" name="selection" class="hidden" />
                        <label for="Third_Stage" class="label ${stage5}">
                            <strong>Third Stage</strong>
                            <span>Growth of competition</span>
                        </label>
                    </li>
                    <li>
                        <input type="radio" id="Fourth_Stage" name="selection" class="hidden" />
                        <label for="Fourth_Stage" class="label ${stage6}">
                            <strong>Fourth Stage</strong>
                            <span>Stabilization phase</span>
                        </label>
                    </li>
                </ul>
            </div>
            <form id="selected-stages" action="/function" method="POST">
                <div class="table">
                    <div id="Pre-Seed_Stage_info" class="table-row">
                        <div class="table-cell label">
                            <strong>Pre-Seed Stage</strong>
                            <span>Generation of ideas</span>
                        </div>
                        <div class="table-cell characteristics">
                            <ul>
                                <li>Only ideas exist<u>s</u></li>
                                <li>No capital / asset available</li>
                                <li>No company founding</li>
                                <li>No market knowledge</li>
                                <li>Only founder / founding team</li>
                            </ul>
                        </div>
                        <div class="table-cell navigation">

                            <input type="submit" class="form_button" name="pre-seed" value="choose" />
                        </div>
                    </div>
                    <div id="Seed_Stage_info" class="table-row">
                        <div class="table-cell label">
                            <strong>Seed Stage</strong>
                            <span>Business planning</span>
                        </div>
                        <div class="table-cell characteristics">
                            <ul>
                                <li>
                                    Business planning
                                    <ul>
                                        <li>Analysis of the company</li>
                                        <li>Analysis of the market</li>
                                        <li>Creation of a marketing concept</li>
                                    </ul>
                                </li>
                                <li>Creation of first networks</li>
                                <li>Determination of the benefits of the product</li>
                            </ul>
                        </div>
                        <div class="table-cell navigation">
                            <input type="submit" class="form_button" name="seed" value="choose" />
                        </div>
                    </div>
                    <div id="Startup_Stage-Conception_info" class="table-row">
                        <div class="table-cell label">
                            <strong>Startup Stage:<br />Conception</strong>
                            <span>Definition of requirements and product properties</span>
                        </div>
                        <div class="table-cell characteristics">
                            <ul>
                                <li>Identifiers of list of requirements</li>
                                <li>Identifiers of function structure</li>
                                <li>Determination of product properties</li>
                                <li>Determination of mechanisms of action</li>
                            </ul>
                        </div>
                        <div class="table-cell navigation">
                            <input type="submit" class="form_button" name="startup1" value="choose" />
                        </div>
                    </div>
                    <div id="Startup_Stage-Construction_info" class="table-row">
                        <div class="table-cell label">
                            <strong>Startup Stage:<br />Construction</strong>
                            <span>Shaping and material determination</span>
                        </div>
                        <div class="table-cell characteristics">
                            <ul>
                                <li>Material determination of the configuration elements</li>
                                <li>Shaping of the surface and configuration elements</li>
                                <li>Dimensioning of the configuration elements</li>
                                <li>Harmonization of the cut surfaces between the configuration elements</li>
                            </ul>
                        </div>
                        <div class="table-cell navigation">
                            <input type="submit" class="form_button" name="startup2" value="choose" />
                        </div>
                    </div>
                    <div id="Startup_Stage-Elaboration_info" class="table-row">
                        <div class="table-cell label">
                            <strong>Startup Stage:<br />Elaboration</strong>
                            <span>Fine adjustment and determination of manufacturing procedures</span>
                        </div>
                        <div class="table-cell characteristics">
                            <ul>
                                <li>Fine adjustment of the configuration elements</li>
                                <li>Design and elaboration of tools</li>
                                <li>Determination of manufacturing procedures</li>
                                <li>Procurement planning of purchased parts</li>
                                <li>Determination of production and procurement strategy</li>
                            </ul>
                        </div>
                        <div class="table-cell navigation">
                            <input type="submit" class="form_button" name="startup3" value="choose" />
                        </div>
                    </div>
                    <div id="Startup_Stage-Prototyping_info" class="table-row">
                        <div class="table-cell label">
                            <strong>Startup Stage:<br />Prototyping</strong>
                            <span>Production and testing of prototypes</span>
                        </div>
                        <div class="table-cell characteristics">
                            <ul>
                                <li>Production of prototype (Inhouse)</li>
                                <li>Prototype testing (Inhouse)</li>
                                <li>Build up prototype at the user/customer</li>
                                <li>Prototype testing at the user/customer</li>
                                <li>Capture of modifications / adaptions of user/customer</li>
                            </ul>
                        </div>
                        <div class="table-cell navigation">
                            <input type="submit" class="form_button" name="startup4" value="choose" />
                        </div>
                    </div>
                    <div id="First_Stage_info" class="table-row">
                        <div class="table-cell label">
                            <strong>First Stage</strong>
                            <span>Start of production and market launch</span>
                        </div>
                        <div class="table-cell characteristics">
                            <ul>
                                <li><b>Market launch</b></li>
                                <li>Start of serial production</li>
                                <li>Production ramp-up</li>
                            </ul>
                        </div>
                        <div class="table-cell navigation">
                            <input type="submit" class="form_button" name="first" value="choose" />
                        </div>
                    </div>
                    <div id="Second_Stage_info" class="table-row">
                        <div class="table-cell label">
                            <strong>Second Stage</strong>
                            <span>Start of production and market launch</span>
                        </div>
                        <div class="table-cell characteristics">
                            <ul>
                                <li><b>Growth phase</b></li>
                                <li>Optimization of the production processes, manufacturing procedures and cycle time</li>
                                <li>Cost optimization</li>
                                <li>Creation and expansion of distribution channels and networks</li>
                            </ul>
                        </div>
                        <div class="table-cell navigation">
                            <input type="submit" class="form_button" name="second" value="choose" />
                        </div>
                    </div>
                    <div id="Third_Stage_info" class="table-row">
                        <div class="table-cell label">
                            <strong>Third Stage</strong>
                            <span>Growth of competition</span>
                        </div>
                        <div class="table-cell characteristics">
                            <ul>
                                <li><b>Growth of competition</b></li>
                                <li>Rapid expansion</li>
                                <li>Strategic exhaustion of market potentials</li>
                                <li>Extension of capacities</li>
                            </ul>
                        </div>
                        <div class="table-cell navigation">
                            <input type="submit" class="form_button" name="third" value="choose" />
                        </div>
                    </div>
                    <div id="Fourth_Stage_info" class="table-row">
                        <div class="table-cell label">
                            <strong>Fourth Stage</strong>
                            <span>Stabilization phase</span>
                        </div>
                        <div class="table-cell characteristics">
                            <ul>
                                <li><b>Only low or slow growth</b></li>
                                <li>Stable turnovers, revenue and profits</li>
                                <li>Standardized steady-state production and logistic processes</li>
                            </ul>
                        </div>
                        <div class="table-cell navigation">
                            <input type="submit" class="form_button" name="forth" value="choose" />
                        </div>
                    </div>
                </div>
            </form>
            <div style="clear:both;"></div>
        </div>
    </div>
</body>
</html>