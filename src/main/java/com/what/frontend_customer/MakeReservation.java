package com.what.frontend_customer;

import backendMock.DummyCustomerBackend;
import com.google.gson.Gson;
import control.FerryMaster;
import generalstuff.DepartureIdentifier;
import generalstuff.LineSummary;
import generalstuff.ReservationDetail;
import generalstuff.ReservationIdentifier;
import generalstuff.ReservationSummary;
import interfaces.CustomerInterface;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "MakeReservation", urlPatterns = {"/MakeReservation"})
public class MakeReservation extends HttpServlet {

    CustomerInterface mock = new FerryMaster();
    Gson gson = new Gson();

//  request to get Lines and render the page
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        Collection<LineSummary> lines;
        lines = mock.getLines();

        request.setAttribute("hideElements", "none");
        request.setAttribute("lines", lines);
        request.setAttribute("linesDetails", gson.toJson(lines));
        try {
            request.getRequestDispatcher("MakeReservation.jsp").forward(request, response);
        } catch (ServletException | IOException ex1) {
            Logger.getLogger(MakeReservation.class.getName()).log(Level.SEVERE, null, ex1);
        }
    }

//  request to create new Reservation
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ReservationDetail rd;
        
        int residentsNb = Integer.parseInt(request.getParameter("residentsNbInput"));
        int nonResidentsNb = Integer.parseInt(request.getParameter("nonResidentsNbInput"));
        int smallCarsNb = Integer.parseInt(request.getParameter("smallCarsNbInput"));
        int heavyMachineryNb = Integer.parseInt(request.getParameter("heavyMachineryNbInput"));
        int lorriesNb = Integer.parseInt(request.getParameter("lorriesNbInput"));
        String customerName = request.getParameter("customerName");
        DepartureIdentifier di = new DepartureIdentifier(Integer.parseInt(request.getParameter("departureId")));
        Boolean hasSmallCar = true;
        if (smallCarsNb == 0) {
            hasSmallCar = false;
        }

        rd = mock.saveReservation(di, nonResidentsNb, residentsNb, smallCarsNb, heavyMachineryNb, lorriesNb, customerName);
        if (rd instanceof ReservationDetail) {
            System.out.println(rd.getId());
//            request.setAttribute("success", "Your reservation has been successfully saved!");
//            request.setAttribute("hideElements", "");
//            request.setAttribute("reservationId", Integer.toString(rd.getId()));
//            request.setAttribute("price", "over 9000!");
            request.setAttribute("detail", rd);
            request.setAttribute("hidden", "");
            request.setAttribute("reservationno", rd.getId());
            System.out.println(request);
            request.getRequestDispatcher("my.jsp").forward(request, response);
        } else {
            request.setAttribute("hideElements", "none");
            request.setAttribute("error", "Your reservation failed to save!");
            request.getRequestDispatcher("MakeReservation.jsp").forward(request, response);
        }
    }
}
