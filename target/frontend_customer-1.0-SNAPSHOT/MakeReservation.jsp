<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Make new reservation</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <link href="./style/ferrycss.css" rel="stylesheet">    
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
        <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
        <link rel="stylesheet" href="//code.jquery.com/ui/1.11.2/themes/smoothness/jquery-ui.css">
        <script src="./script/ferryscript.js"></script>
        <script src="./script/changedSelectedRoute.js"></script>
        <script src="./script/datePickerAction.js"></script>
        <script src="./script/injectDepartureDetails.js"></script>
        <script src="./script/changedDepartureHour.js"></script>
        <script type="text/javascript" id="linesDetailsJSON" data-info=${linesDetails}></script>
    </head>
    <body>
        <div id="JSONDATA"></div>
        <div id="background">
            <div class="container">
                <div class="col-md-12">
                    <h1>Make new reservation</h1>
                    <hr/>
                    <div  style="position: relative;z-index: 9999;" class="col-md-12" style="margin-top:15px" ${hidden} id="myreservation">
                        <form method="post" class="form-inline" id="reservationForm">
                            <table class="table" id="reservationTable" style="background-color: floralwhite">
                                <tbody>
                                    <tr id="reservationIDRow" style="display: ${hideElements}">
                                        <td>Reservation ID</td><td>${reservationId}</td>
                                    </tr>
                                    <tr id="routesRow">
                                        <td>Route</td>
                                        <td>
                                            <select id="routeSelectBox" required="required" onchange="changedSelectedRoute();">
                                                <option selected="selected">- Please choose -</option>
                                                <c:forEach var="line" items="${lines}">
                                                    <option>${line.getDeparturePort()} - ${line.getDestinationPort()}</option>
                                                </c:forEach>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr id="departureDateRow" style="display: ${hideElements}">
                                        <td>Departure date</td><td><input type="text" id="datepicker" placeholder="click to choose" readonly="true"></td>
                                    </tr>
                                    <tr id="residentPassengersRow" style="display: ${hideElements}">
                                        <td>Passengers(residents)</td><td><input style="border: none;" name="residentsNbInput" type="number" min="0" value="0"</td>
                                    </tr>
                                    <tr id="nonResidentPassengersRow" style="display: ${hideElements}">
                                        <td>Passengers(non-residents)</td><td><input style="border: none;" name="nonResidentsNbInput" type="number" min="0" value="0"></td>
                                    </tr>
                                    <tr id="smallCarsRow" style="display: ${hideElements}">
                                        <td>Small cars</td><td><input style="border: none;" name="smallCarsNbInput" type="number" min="0" value="0"</td>
                                    </tr>
                                    <tr id="heavyMachineryRow" style="display: ${hideElements}">
                                        <td>Heavy machinery</td><td><input style="border: none;" name="heavyMachineryNbInput" type="number" min="0" value="0"</td>
                                    </tr>
                                    <tr id="lorriesRow" style="display: ${hideElements}">
                                        <td>Lorries</td><td><input style="border: none;" name="lorriesNbInput" type="number" min="0" value="0"</td>
                                    </tr>
                                    <tr id="departureHoursRow" style="display: ${hideElements}">
                                        <td>Departure hour:</td>
                                        <td>
                                            <select id="departureHoursSelectBox" required="required" onchange="resetSaveButton();">
                                            </select>
                                        </td>   
                                    </tr>
                                    <tr id="priceRow" style="display: ${hideElements}">
                                        <td>Price</td><td>${price}</td>
                                    </tr>
                                    <tr id="customerNameRow" style="display: ${hideElements}">
                                        <td>Your name</td><td><input style="border: none;" name="customerName"</td>
                                    </tr>
                                </tbody>
                            </table>
                            <table class="table" id="bottomButtonsTable">
                                <tr>
                                    <td>
                                        <c:if test="${empty success}">
                                            <button id="saveReservationButton" disabled="disabled" type="submit" class="btn btn-info">Save reservation</button>
                                        </c:if>
                                    </td>
                                    <td>
                                        <a href="./" class="btn btn-info">Back to Home</a>
                                    </td>
                                </tr>
                            </table>
                        </form>
                        <c:if test="${not empty error}">
                            <span id="errorMsg" style="color: red; margin-top: 10px; display:block">${error}</span>
                        </c:if>
                        <c:if test="${not empty success}">
                            <span id="successMsg" style="color: green; margin-top: 10px; display:block">${success}</span>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
