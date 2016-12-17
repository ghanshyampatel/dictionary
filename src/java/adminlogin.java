/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
/**
 *
 * @author Ghanshyam Patel
 */
public class adminlogin extends HttpServlet {

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
            throws ServletException, IOException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");
        String word=request.getParameter("admin_word");
        String meaning=request.getParameter("admin_meaning");
        try (PrintWriter out = response.getWriter()) {
          try
            {
                Class.forName("com.mysql.jdbc.Driver");  
                Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/dictionory","root","tiger");
                String query="insert into words values(?,?)";
                PreparedStatement pst =con.prepareStatement(query );  

                pst.setString(1,word);  
                pst.setString(2,meaning);  
               
                int i = pst.executeUpdate();
                String msg=" ";
                 if(i>0)
                 {  
                 RequestDispatcher requestdispatcher=request.getRequestDispatcher("/adminlogin.html");    
                 requestdispatcher.include(request,response);
                 msg="Record has been inserted";
                 out.println("<font size='6' color=blue><center>" + msg + "</font>");
                }  
                 else{  
                 RequestDispatcher requestdispatcher=request.getRequestDispatcher("/adminlogin.html");    
                 requestdispatcher.include(request,response);
                 msg="Failed has been inserted";
                 out.println("<font size='6' color=blue><center>" + msg + "</font>");
                } 
                pst.close();
            }
            catch(Exception e)
            {
              out.println(e);  
            }
            /*out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet adminlogin</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet adminlogin at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");*/
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(adminlogin.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(adminlogin.class.getName()).log(Level.SEVERE, null, ex);
        }
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

}
