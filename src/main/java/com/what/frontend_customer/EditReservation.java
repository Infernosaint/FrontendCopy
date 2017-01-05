package com.what.frontend_customer;

import backendMock.DummyCustomerBackend;
import com.google.common.collect.Iterables;
import control.FerryMaster;
import generalstuff.DepartureIdentifier;
import generalstuff.DepartureSummary;
import generalstuff.LineDetail;
import generalstuff.LineIdentifier;
import generalstuff.LineSummary;
import generalstuff.ReservationDetail;
import generalstuff.ReservationIdentifier;
import interfaces.CustomerInterface;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet( name = "editReservation", urlPatterns = { "/editReservation" } )
public class EditReservation extends HttpServlet {

    CustomerInterface mock = new FerryMaster();

    //See reservation
    @Override
    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        String reservationNo;
        try {
            reservationNo = request.getParameter( "reservationno" );

            if ( reservationNo != null ) {
                int intResNo = Integer.parseInt( reservationNo );
                ReservationDetail detail = mock.getReservation( new ReservationIdentifier( intResNo ) );

                if ( detail == null ) {
                    request.setAttribute( "error", "The reservation number does not exist" );
                    request.setAttribute( "errorBgColor", "red" );
                    request.setAttribute( "hidden", "hidden" );
                } else {
                    request.setAttribute( "detail", detail );
                    request.setAttribute( "hidden", "" );
                }
            } else {
                request.setAttribute( "hidden", "hidden" );
            }
            request.setAttribute( "reservationno", reservationNo );

            request.getRequestDispatcher( "editReservation.jsp" ).forward( request, response );

        } catch ( Exception e ) {
            out.println( "<h2>" + e.getMessage() + "</h2>" );
            out.print( "<hr/><pre>" );
            e.printStackTrace( out );
            out.println( "</pre>" );
        }
    }

    @Override
    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String reservationNo, numberOfPeople, numberOfResidents, departurePort,
                destinationPort, departureDate, numberOfCars, numberOfLorries, numberOfHeavyMachineries;
        DepartureSummary departureSummary = null;
        DepartureIdentifier depIdentifier = new DepartureIdentifier( 0 );
        try {

            reservationNo = request.getParameter( "reservationno" );
            numberOfPeople = request.getParameter( "numberOfPeople" );
            numberOfResidents = request.getParameter( "numberOfResidents" );
            departurePort = request.getParameter( "departurePort" );
            destinationPort = request.getParameter( "destinationPort" );
            numberOfCars = request.getParameter( "numberOfCars" );
            numberOfLorries = request.getParameter( "numberOfLorries" );
            numberOfHeavyMachineries = request.getParameter( "numberOfHeavyMachineries" );

//            departureDate = request.getParameter( "departureDate" );
//            departureDate= "Sun Nov 20 00:23:39 CET 2016";
            departureDate = "Sun Nov 20 00:23:39 CET 2016";

            DateFormat format = new SimpleDateFormat( "EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH );
            Date depDate = format.parse( departureDate );
//            Date lala= Calendar.
            if ( reservationNo == null ) {
                request.setAttribute( "hidden", "hidden" );
                request.setAttribute( "error", "Error. The reservation number is required" );
            } else {

                int intResNo = Integer.parseInt( reservationNo );
                int intNumberOfPeople = Integer.parseInt( numberOfPeople );
                int intNumberOfResidents = Integer.parseInt( numberOfResidents );
                boolean car = false;
//              
                for ( LineSummary lineSummary : mock.getLines() ) {
//                    for (int i=0; i< mock.getDepartures( lineSummary, depDate ).size(); i++){
//                        if(lineSummary.getDeparturePort().equals( departurePort )
//                            &&lineSummary.getDestinationPort().equals( destinationPort))
//                            depIdentifier= Iterables.get(mock.getDepartures( lineSummary, depDate ),0);

                    if ( lineSummary.getDeparturePort().equals( departurePort )
                            && lineSummary.getDestinationPort().equals( destinationPort ) ) {
//                        departureSummary = Iterables.get( mock.getDepartures( lineSummary, depDate ) , 0 );
//                        depIdentifier = new DepartureIdentifier( departureSummary.getId() );
                        depIdentifier = new DepartureIdentifier( 0 ); //should be replaced by the previous 2 lines
                    }
                }

                if ( Integer.parseInt( numberOfCars ) > 0 || Integer.parseInt( numberOfLorries ) > 0
                        || Integer.parseInt( numberOfHeavyMachineries ) > 0 ) {
                    car = true;
                }

                mock.updateReservation( new ReservationIdentifier( intResNo ), depIdentifier,
                                        intNumberOfPeople, intNumberOfResidents, Integer.parseInt(numberOfCars), Integer.parseInt(numberOfHeavyMachineries), Integer.parseInt(numberOfLorries), "Mads" );
                request.setAttribute( "hidden", "hidden" );
                request.setAttribute( "success", "Your reservation has successfully updated." );
            }
            request.getRequestDispatcher( "editReservation.jsp" ).forward( request, response );

        } catch ( Exception e ) {
            out.println( "<h2>" + e.getMessage() + "</h2>" );
            out.print( "<hr/><pre>" );
            e.printStackTrace( out );
            out.println( "</pre>" );
        }
    }
}
