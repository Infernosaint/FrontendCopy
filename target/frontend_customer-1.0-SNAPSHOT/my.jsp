<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link href="./style/ferrycss.css" rel="stylesheet">    
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    <script src="./script/ferryscript.js"></script>
<html>
    <title>Your reservation</title>
</head>
<body>   <div id="background">
        <div class="container">
            <div class="col-md-12">
                <h1>Your reservation</h1>
                <hr/>
                <div class="col-md-12">
                    <form method="get" class="form-inline">
                        <div class ="form-group formOnImage">
                            <label for="reservationno" >Your reservation no: </label>
                            <input class="form-control" required id="reservationNoInput" value="${reservationno}" name="reservationno" placeholder="your reservation no"/>
                        </div>   
                        <span class="formOnImage">
                            <button type="submit" class="btn btn-info ">Show reservation</button>
                        </span>
                        <span id="errorMsg" style="color: red">${error}</span>                    
                    </form>
                    <span id="successMsg" style="color: green; margin-top: 10px; display:block">${success}</span>
                </div>
                <div  class="col-md-12 formOnImage" style="margin-top:15px" ${hidden} id="myreservation">
                    <table class="table" style="background-color: floralwhite">
                        <tbody>
                            <tr>
                                <td>Reservation no</td><td>${detail.id}</td>                 
                            </tr>
                            <tr>
                                <td>Name</td><td>${detail.customerName}</td>   
                            </tr>
                            <tr>
                                <td>From</td><td>${detail.departureSummary.lineSummary.departurePort}</td>   
                            </tr>
                            <tr>
                                <td>To</td><td>${detail.departureSummary.lineSummary.destinationPort}</td>   
                            </tr>
                            <tr>
                                <td>Departure date & time</td><td><fmt:formatDate pattern="dd-MMM-yyyy" value="${detail.departureSummary.departureTime}" /></td>   
                            </tr>

                            <tr>
                                <td>Travel duration</td><td>${detail.departureSummary.lineSummary.duration}</td>   
                            </tr>
                            <tr>
                                <td>Passenger(Non-resident)</td><td>${detail.numberOfPeople}</td>
                            </tr>
                            <tr>
                                <td>Passenger(Resident)</td><td>${detail.numberOfResidents}</td>   
                            </tr>
                            <tr>
                                <td>Car</td><td>${detail.numberOfCars}</td>   
                            </tr>
                            <tr>
                                <td>Lorry</td><td>${detail.numberOfLorries}</td>   
                            </tr>
                            <tr>
                                <td>Heavy machinery</td><td>${detail.numberOfHeavyMachinery}</td>   
                            </tr>
                            <tr>
                                <td>Total price</td><td>${detail.totalPrice}</td>   
                            </tr>
                        </tbody>
                    </table>
                </div>
                <div class="formOnImage col-md-12">
                    <div class="pull-right">
                        <span ${hidden}>
                            <a href="./editReservation?reservationno=${reservationno}" ><button class="btn btn-info" type="button">Edit booking <i class="fa fa-pencil" aria-hidden="true"></i></button></a>
                            <form method="post" style="display:inline-block">
                                <input name="reservationno" hidden value="${reservationno}">
                                <button type="submit"class="btn btn-danger">Delete reservation <i class="fa fa-ban" aria-hidden="true"></i></button>
                            </form>
                        </span>
                        <a href="./"><button class="btn btn-info" type="button">Back to Home <i class="fa fa-home" aria-hidden="true"></i></button></a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
