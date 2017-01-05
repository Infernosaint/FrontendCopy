package com.what.frontend_customer;

import backendMock.DummyCustomerBackend;
import com.google.gson.Gson;
import control.FerryMaster;
import dtos_for_website.DepartureDetailsDTO;
import generalstuff.DepartureDetail;
import generalstuff.LineIdentifier;
import interfaces.CustomerInterface;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "DeparturesDetails", urlPatterns = {"/DeparturesDetails"})
public class DeparturesDetails extends HttpServlet {

    CustomerInterface mock = new FerryMaster();
    Gson gson = new Gson();

//  request to get departure details
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        Long dateMillis = Long.parseLong(request.getParameter("date"));
        Date date;
        try {
            date = new Date(dateMillis);
            LineIdentifier lineId = new LineIdentifier(Integer.parseInt(request.getParameter("lineId")));
            Collection<DepartureDetailsDTO> departuresForWebsite = new ArrayList<>();

            for (DepartureDetail d : mock.getDepartures(lineId, date)) {
                departuresForWebsite.add(new DepartureDetailsDTO(d.getPricePerPerson(), d.getPricePerCar(), d.getPricePerLorry(), d.getPricePerHeavy(), d.getPricePerResident(),
                        d.getDepartureTime().getTime(), d.getRemainingPeople(), d.getRemainingCars(), d.getRemainingLorries(), d.getRemainingHeavy(), d.getLineSummary(), d.getFerrySummary(), d.getId()));
            }

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(gson.toJson(departuresForWebsite));
        } catch (IOException ex) {
            Logger.getLogger(DeparturesDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
