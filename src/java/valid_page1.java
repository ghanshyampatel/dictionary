/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 
 */
public class valid_page1 extends HttpServlet {

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
        String word_var=request.getParameter("word1");
        try (PrintWriter out = response.getWriter()) {
            out.println("<link rel="+"stylesheet"+"type="+"text/css"+"href="+"login.css>");
            try{  
                    Class.forName("com.mysql.jdbc.Driver");  
                    Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/dictionory","root","tiger");  
                    Statement stmt=con.createStatement();  
                    ResultSet rs=stmt.executeQuery("select * from words where word=\""+word_var+"\"");  
                    if(rs.next())  {  
                        RequestDispatcher requestdispatcher=request.getRequestDispatcher("/index.html");    
                        requestdispatcher.include(request,response);
                        out.println("<div class=\"login-screen\"><center><h2>Word : "+rs.getString(1)+"<br>Meaning : "+rs.getString(2)+"</h2></center></div>");    
                    }
                    else{
                         RequestDispatcher requestdispatcher=request.getRequestDispatcher("/index.html");    
                         requestdispatcher.include(request,response);
                         out.println("<div class=\"login-screen\"><center><h2>Sorry,word not found in dictionary... <br> Please enter a valid word</h2></center></div>");
                    }
                    con.close(); 
                    
                }catch(Exception e)
                {
                    out.println(e);
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

}
