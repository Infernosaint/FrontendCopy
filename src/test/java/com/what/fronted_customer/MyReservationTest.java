/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.what.fronted_customer;

import com.what.frontend_customer.MyReservation;
import interfaces.CustomerInterface;
import static org.mockito.Mockito.*;
import backendMock.DummyCustomerBackend;
import generalstuff.ReservationDetail;
import generalstuff.ReservationIdentifier;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

/**
 *
 * @author Tomoe
 */
public class MyReservationTest {

//    private MyReservation servlet;
//    private HttpServletRequest request;
//    private HttpServletResponse response;
//    private StringWriter response_writer;
//    private CustomerInterface mockbackend;
//
//    @Before
//    public void setUp() throws IOException {
//        mockbackend = mock(DummyCustomerBackend.class);
//        servlet = new MyReservation(mockbackend);
//        request = mock(HttpServletRequest.class);
//        response = mock(HttpServletResponse.class);
//        response_writer = new StringWriter();
//        when(mockbackend.getReservation((ReservationIdentifier) anyObject())).thenReturn(new ReservationDetail(new Date(), null, "testName", 10, 0, 0, 0, 0, 1000, 1));
//        when(response.getWriter()).thenReturn(new PrintWriter(response_writer));
//    }
//
//    @Test
//    public void testDoGet_seeReservation() throws ServletException, IOException {
//        when(request.getParameter("reservationno")).thenReturn("1000");
//        servlet.doGet(request, response);
//        verify(request, atLeast(1)).getParameter("reservationno");
//        verify(request, atLeast(1)).setAttribute("reservationno", "1000");
//        verify(mockbackend, atLeast(1)).getReservation((ReservationIdentifier) anyObject());
//        verify(request, atLeast(1)).setAttribute("hidden", "");
//    }
//
//    @Test
//    public void testDoGet() throws ServletException, IOException {
//        when(request.getParameter("reservationno")).thenReturn(null);
//        servlet.doGet(request, response);
//        verify(request, atLeast(1)).getParameter("reservationno");
//        verify(request, never()).setAttribute("reservationno", "1000");
//        verify(mockbackend, never()).getReservation((ReservationIdentifier) anyObject());
//        verify(request, atLeast(1)).setAttribute("hidden", "hidden");
//    }
//
//    @Test
//    public void testDoGet_noMachedReservation() throws ServletException, IOException {
//        when(request.getParameter("reservationno")).thenReturn("1000");
//        when(mockbackend.getReservation((ReservationIdentifier) anyObject())).thenReturn(null);
//        servlet.doGet(request, response);
//        verify(request, atLeast(1)).getParameter("reservationno");
//        verify(request, atLeast(1)).setAttribute("reservationno", "1000");
//        verify(mockbackend, atLeast(1)).getReservation((ReservationIdentifier) anyObject());
//        verify(request, atLeast(1)).setAttribute("hidden", "hidden");
//        verify(request, atLeast(1)).setAttribute("errorBgColor", "red");
//        verify(request, atLeast(1)).setAttribute("error", "The reservation number does not exist");
//    }
//    
//     @Test
//    public void testDoPost_deleteReservation_success() throws ServletException, IOException {
//        when(request.getParameter("reservationno")).thenReturn("1000");
//        when(mockbackend.deleteReservation((ReservationIdentifier) anyObject())).thenReturn(true);
//        servlet.doPost(request, response);
//        verify(request, atLeast(1)).getParameter("reservationno");
//        verify(request, never()).setAttribute("reservationno", "1000");
//        verify(mockbackend, atLeast(1)).deleteReservation((ReservationIdentifier) anyObject());
//        verify(request, atLeast(1)).setAttribute("hidden", "hidden");
//        verify(request, atLeast(1)).setAttribute("success", "Your reservation has been deleted successfully.");
//
//    }
//     @Test
//    public void testDoPost_deleteReservation_fail() throws ServletException, IOException {
//        when(request.getParameter("reservationno")).thenReturn("1000");
//        when(mockbackend.deleteReservation((ReservationIdentifier) anyObject())).thenReturn(false);
//        servlet.doPost(request, response);
//        verify(request, atLeast(1)).getParameter("reservationno");
//        verify(request, never()).setAttribute("reservationno", "1000");
//        verify(mockbackend, atLeast(1)).deleteReservation((ReservationIdentifier) anyObject());
//        verify(request, atLeast(1)).setAttribute("hidden", "");
//        verify(request, atLeast(1)).setAttribute("error", "System Error. Please contact Cluster I Ferries");
//
//    }
//     @Test
//    public void testDoPost_noReservationNo() throws ServletException, IOException {
//        when(request.getParameter("reservationno")).thenReturn(null);
//        servlet.doPost(request, response);
//        verify(request, atLeast(1)).getParameter("reservationno");
//        verify(request, never()).setAttribute("reservationno", "1000");
//        verify(mockbackend, never()).deleteReservation((ReservationIdentifier) anyObject());
//        verify(request, atLeast(1)).setAttribute("hidden", "hidden");
//        verify(request, atLeast(1)).setAttribute("error", "Error. The reservation number is required");
//
//    }

    
}
