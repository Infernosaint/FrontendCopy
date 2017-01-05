package com.what.frontend_customer;

import backendMock.DummyCustomerBackend;
import control.FerryMaster;
import generalstuff.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import interfaces.CustomerInterface;

@WebServlet(name = "Reservation", urlPatterns = {"/myreservation"})
public class MyReservation extends HttpServlet {

    CustomerInterface mock;

    public MyReservation() {
        mock = new FerryMaster();
    }

    public MyReservation(CustomerInterface backend) {
        mock = backend;

    }

    //See reservation
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        String reservationNo;
        try {
            reservationNo = request.getParameter("reservationno");

            if (reservationNo != null) {
                int intResNo = Integer.parseInt(reservationNo);
                ReservationDetail detail = mock.getReservation(new ReservationIdentifier(intResNo));

                if (detail == null) {
                    request.setAttribute("error", "The reservation number does not exist");
                    request.setAttribute("errorBgColor", "red");
                    request.setAttribute("hidden", "hidden");
                }
                else {
                    request.setAttribute("detail", detail);
                    request.setAttribute("hidden", "");
                }
            }
            else {
                request.setAttribute("hidden", "hidden");
            }
            request.setAttribute("reservationno", reservationNo);

            request.getRequestDispatcher("my.jsp").forward(request, response);

        }
        catch (Exception e) {

            out.println("<h2>" + e.getMessage() + "</h2>");
            out.print("<hr/><pre>");
            e.printStackTrace(out);
            out.println("</pre>");
        }
    }

    //cancel reservation
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String reservationNo;
        try {

            reservationNo = request.getParameter("reservationno");
            if (reservationNo == null) {
                request.setAttribute("hidden", "hidden");
                request.setAttribute("error", "Error. The reservation number is required");
            }
            else {

                int intResNo = Integer.parseInt(reservationNo);
                if (mock.deleteReservation(new ReservationIdentifier(intResNo))) {
                    request.setAttribute("hidden", "hidden");
                    request.setAttribute("success", "Your reservation has been deleted successfully.");
                }
                else {
                    request.setAttribute("hidden", "");
                    request.setAttribute("error", "System Error. Please contact Cluster I Ferries");
                }
            }
            request.getRequestDispatcher("my.jsp").forward(request, response);

        }
        catch (Exception e) {
            out.println("<h2>" + e.getMessage() + "</h2>");
            out.print("<hr/><pre>");
            e.printStackTrace(out);
            out.println("</pre>");
        }
    }
}
