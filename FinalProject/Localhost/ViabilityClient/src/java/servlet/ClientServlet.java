/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceRef;
import webservices.Segnalazione;
import webservices.WebServiceManager;
import webservices.WebServiceManager_Service;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 *
 * @author Riccardo Nocita <riccardo.noc@gmail.com>
 */
@WebServlet(name = "ClientServlet", urlPatterns = {"/ClientServlet"})
public class ClientServlet extends HttpServlet {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/WebServiceManager/WebServiceManager.wsdl")
    private WebServiceManager_Service service;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String type = request.getParameter("type-signal");
        String nameSurname = request.getParameter("name-surname-field");
        String age = request.getParameter("age-field");
        String district = request.getParameter("district-id-field");
        String priority = request.getParameter("priority-signal");
        
        String id = request.getParameter("id-field");


        if (checkParameters(type, nameSurname, age, district, priority)) {
            Segnalazione signal = new Segnalazione();
            signal.setAge(Integer.valueOf(age));
            signal.setIddistrict(Long.valueOf(district));
            signal.setNamesurname(nameSurname);
            signal.setPriority(Integer.valueOf(priority));
            if(id != null && !id.equalsIgnoreCase("")) signal.setId(Long.valueOf(id));
            setCurrentTime(signal);
            
            doOperation(type, signal);

            response.sendRedirect("index.html");
        } else {
            try (PrintWriter out = response.getWriter()) {
                /* TODO output your page here. You may use following sample code. */
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Viability Servlet</title>");
                out.println("</head>");
                out.println("<body style='background: #dadada;'>");
                out.println("<div style=' margin-top: 40px; text-align: center; border-style:inset; margin-top:auto; margin-right:auto; margin-left:auto; width:900px; height:100px;'><h1> Fields shoul be NOT empty. Please retry again.</h1></div>");
                out.println("</body>");
                out.println("</html>");
                response.setHeader("Refresh", "4; URL=/ViabilityClient/");
            }

        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private boolean checkParameters(String type, String nameSurname, String age, String district, String priority) {
        return (type != null && !type.equalsIgnoreCase("")
                && nameSurname != null && !nameSurname.equalsIgnoreCase("")
                && age != null && !age.equalsIgnoreCase("")
                && district != null && !district.equalsIgnoreCase("")
                && priority != null && !priority.equalsIgnoreCase(""));
    }

    private void doOperation(String operation, Segnalazione entity) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.

        webservices.WebServiceManager port = service.getWebServiceManagerPort();

        switch (operation) {
            case "INSERT": {
                port.create(entity);
                System.err.println("[SERVLET] INSERT operation sent.");
                break;
            }
            case "MODIFY": {
                port.edit(entity);
                System.err.println("[SERVLET] MODIFY operation sent.");
                break;
            }
            case "DELETE": {
                port.remove(entity);
                System.err.println("[SERVLET] DELETE operation sent.");
                break;
            }
            default: {
                System.err.println("Oggetto sconosciuto");
                break;
            }
        }

    }

    private void setCurrentTime(Segnalazione signal) {
        try {
            GregorianCalendar gregorianCalendar = new GregorianCalendar();
            DatatypeFactory datatypeFactory = DatatypeFactory.newInstance();
            XMLGregorianCalendar now = datatypeFactory.newXMLGregorianCalendar(gregorianCalendar);
            signal.setTmstampedit(now);
        } catch (DatatypeConfigurationException ex) {
            ex.printStackTrace();
        }
    }

}
