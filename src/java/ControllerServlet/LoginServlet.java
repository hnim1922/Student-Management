/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControllerServlet;

import DAO.StudentDAO;
import DAO.UserDAO;
import DTO.Student;
import DTO.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {
    private static final String SUCCESS = "header.jsp";
    private static final String ERROR = "login.jsp";
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
            throws ServletException, IOException{
        HttpSession session = request.getSession();
        response.setContentType("text/html;charset=UTF-8");
        String url=ERROR;
        try{
            String un = request.getParameter("username");
            String p = request.getParameter("password");
            
            if (un.equals("Admin")){//neu la admin
                if(p.equals("Admin")){
                    url=SUCCESS;
                    session.setAttribute("name", un);
                    request.setAttribute("ERRORNOTLOGIN", "");
                }
            }

            boolean IsAUser = false; //xac thuc co hay khong
            UserDAO ud= new UserDAO();
            ArrayList<User> UserList= new ArrayList<>();
            UserList=ud.getUser();
            for (User u : UserList) {
                if (un.equals(u.getUsername())){
                    if(p.equals(u.getPassword())){
                        IsAUser = true;
                    }
                }
            }
            if (IsAUser){//neu co trong db
                StudentDAO sDAO= new StudentDAO();
                Student s= sDAO.getStudentByEmail(un);
                session.setAttribute("name", s.getId());
                request.setAttribute("name", s.getId());
                url=SUCCESS;
                request.setAttribute("ERRORNOTLOGIN", "");
            }
            else {//khong co
                request.setAttribute("ERROR", "Invalid username or password");
            }
        } catch (Exception e) {
            log ("ERROR at LoginController: " + e.getMessage());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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
