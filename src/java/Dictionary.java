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
 * @author AxY-PC
 */
public class Dictionary extends HttpServlet {

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
        String var=request.getParameter("text");
        try (PrintWriter out = response.getWriter()) 
        {
            try
            {  
                    Class.forName("com.mysql.jdbc.Driver");  
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost/dictionary","root","Iamsherlocked@1");  
                    Statement stmt=con.createStatement();  
                    ResultSet rs=stmt.executeQuery("select * from words where word=\""+var+"\"");  
                    if(rs.next())
                    {
                        RequestDispatcher requestdispatcher=request.getRequestDispatcher("/Dictionary.html");    
                        requestdispatcher.include(request,response);
                        out.println("<br/><br/><center><h2>Word : "+rs.getString(1)+"<br>Meaning : "+rs.getString(2)+"</h2></center>");  
                         
                    }
                    else
                    {
                        RequestDispatcher requestdispatcher=request.getRequestDispatcher("/Dictionary.html");    
                        requestdispatcher.include(request,response); 
                        out.println("<br/><br/><center><h2>Sorry,word not found in dictionary... <br> Please enter a valid word</h2></center>");
                    }
                    con.close(); 
                    
                }catch(Exception e)
                {
                    out.println(e+" ");
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
