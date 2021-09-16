/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControllerServlet;

import DAO.ClassDAO;
import DAO.RegisterDAO;
import DAO.ScoreDAO;
import DAO.StudentDAO;
import DAO.SubjectDAO;
import DAO.UserDAO;
import DTO.Student;
import DTO.Subject;
import DTO.Class;
import DTO.Register_Class;
import DTO.Score;
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
@WebServlet(name = "UserServlet", urlPatterns = {"/UserServlet"})
public class UserServlet extends HttpServlet {

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
        request.getSession().getAttribute("name");
        String action = request.getParameter("action");
        PrintWriter out=response.getWriter();  
        HttpSession session = request.getSession();
        String username=(String)session.getAttribute("name");
        request.setAttribute("name", username);
        
        try{
            if (action == null){
                RequestDispatcher rd= request.getRequestDispatcher("header.jsp");
                rd.forward(request, response);
            }
            
            else if(action.equals("logdb")){ //xac nhan va log in user
                String un = request.getParameter("username");
                String p = request.getParameter("password");

                if (un.equals("Admin")){//neu la admin
                    if(p.equals("Admin")){
                        session.setAttribute("name", un);
                        request.setAttribute("name", un);
                        request.getRequestDispatcher("header.jsp").forward(request, response);
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
                    request.getRequestDispatcher("header.jsp").forward(request, response);
                }
                else {//khong co
                    RequestDispatcher rd1=request.getRequestDispatcher("login.jsp");
                    rd1.include(request, response);
                    out.print("<p>Wrong Username or Password, please try again</p>");
                }
            }
            
            else if(action.equals("logout")){ // dang xuat
                request.removeAttribute("name");
                session.invalidate();
                RequestDispatcher rd=request.getRequestDispatcher("header.jsp");
                rd.forward(request, response);
            }
            
            else if (action.equals("info")){
                StudentDAO sDAO = new StudentDAO();
                Student s=sDAO.getStudentById( (String) request.getAttribute("name"));
                
                request.setAttribute("Student", s);
                
                RequestDispatcher rd = request.getRequestDispatcher("info.jsp");
                rd.forward(request, response);
            }
            
            else if (action.equals("viewClass")){
                StudentDAO sDAO = new StudentDAO();
                Student s=sDAO.getStudentById( (String) request.getAttribute("name"));
                
                ClassDAO cDAO = new ClassDAO();
                Class c= cDAO.getClassById(s.getClasses().getId());
                request.setAttribute("class", c);
                
                SubjectDAO suDAO = new SubjectDAO();
                ArrayList<Subject> su=suDAO.getSubjectByClassId(c.getId());
                request.setAttribute("subject", su);
                
                RequestDispatcher rd = request.getRequestDispatcher("viewClass.jsp");
                rd.forward(request, response);
            }
            
            else if (action.equals("viewClassMate")){
                ClassDAO cDAO = new ClassDAO();
                Class c=cDAO.getClassById((String) request.getParameter("cid"));
                request.setAttribute("ClassName", c.getName());
                
                StudentDAO sDAO = new StudentDAO();
                Student s=sDAO.getStudentById( (String) request.getAttribute("name"));
                ArrayList<Student> sList = sDAO.getStudentByClassId(s.getClasses().getId());
                request.setAttribute("classMate", sList);
                
                RequestDispatcher rd = request.getRequestDispatcher("viewClassMate.jsp");
                rd.forward(request, response);
            }
            
            else if (action.equals("viewGrade")){
                StudentDAO sDAO = new StudentDAO();
                Student s=sDAO.getStudentById( (String) request.getAttribute("name"));
                
                ScoreDAO scDAO= new ScoreDAO();
                ArrayList<Score> sc= scDAO.getScoreByStudentId(s.getId());
                request.setAttribute("score", sc);
                
                RequestDispatcher rd = request.getRequestDispatcher("viewScore.jsp");
                rd.forward(request, response);
            }
            
            else if (action.equals("registerClass")){
                ArrayList<Subject> sub = new ArrayList<>();
                SubjectDAO sDao = new SubjectDAO();
                 sub = sDao.getAllSubject();
                StudentDAO sDAO = new StudentDAO();
                Student s=sDAO.getStudentById( (String) request.getAttribute("name"));             
                RegisterDAO rDAO = new RegisterDAO();
                ArrayList<Register_Class> r = rDAO.getRegisterByStudentId(s.getId());
                request.setAttribute("subjectList", sub);
                request.setAttribute("registerClass", r);
                
                RequestDispatcher rd = request.getRequestDispatcher("viewRegister.jsp");
                rd.forward(request, response);
            }
            
            else if (action.equals("register")){
                Register_Class r = new Register_Class();
                SubjectDAO suDAO=new SubjectDAO();
                ArrayList<Subject> suList=suDAO.getAllSubject();
                String clid =request.getParameter("clid");
                String clName =request.getParameter("clName");
                request.setAttribute("subjectIDFromImg", clid);
                request.setAttribute("subjectNameFromImg", clName);
                request.setAttribute("rObject", r);
                request.setAttribute("suList", suList);
                request.setAttribute("msg", "Register a class");
                request.setAttribute("action", "Create_Register");
                request.setAttribute("error", "");
                
                RequestDispatcher rd = request.getRequestDispatcher("registerForm.jsp");
                rd.forward(request, response);
            }
            
            else if (action.equals("Create_Register")){
                StudentDAO sDAO = new StudentDAO();
                Student s=sDAO.getStudentById( (String) request.getAttribute("name"));
                
                String suid = request.getParameter("subject");
                String se = request.getParameter("semester");
                
                if (suid==null){
                request.setAttribute("ERROR", "You not suppose to create like this");
                RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
                rd.forward(request, response);
                }
                
                RegisterDAO rDAO = new RegisterDAO();
                Register_Class rSearch= rDAO.getRegisterById(s.getId(), suid);
                
                if (rSearch!=null){
                    Register_Class r = new Register_Class();
                    SubjectDAO suDAO=new SubjectDAO();
                    ArrayList<Subject> suList=suDAO.getAllSubject();
                
                    request.setAttribute("rObject", r);
                    request.setAttribute("suList", suList);
                    request.setAttribute("msg", "Register a class");
                    request.setAttribute("action", "Create_Register");
                    request.setAttribute("error", "Already have the class registered");
                    
                    RequestDispatcher rd = request.getRequestDispatcher("registerForm.jsp");
                    rd.forward(request, response);
                }
                
                ScoreDAO scDAO = new ScoreDAO();
                Score sc = scDAO.getScoreById(s.getId(), suid);
                
                if (sc==null){
                    SubjectDAO suDAO= new SubjectDAO();
                    Subject su= suDAO.getSubjectById(suid);
                    
                    Register_Class r = new Register_Class(s, su, se);
                    rDAO.CreateRegister(r);
                            
                    RequestDispatcher rd = request.getRequestDispatcher("UserServlet?action=registerClass");
                    rd.forward(request, response);
                } else if (sc.getStatus().equals("Pass")){
                    Register_Class r = new Register_Class();
                    SubjectDAO suDAO=new SubjectDAO();
                    ArrayList<Subject> suList=suDAO.getAllSubject();
                    
                    request.setAttribute("rObject", r);
                    request.setAttribute("suList", suList);
                    request.setAttribute("msg", "Register a class");
                    request.setAttribute("action", "Create_Register");
                    request.setAttribute("error", "You already pass the class");

                    RequestDispatcher rd = request.getRequestDispatcher("registerForm.jsp");
                    rd.forward(request, response);
                } else {
                    SubjectDAO suDAO= new SubjectDAO();
                    Subject su= suDAO.getSubjectById(suid);
                    
                    Register_Class r = new Register_Class(s, su, se);
                    rDAO.CreateRegister(r);
                            
                    RequestDispatcher rd = request.getRequestDispatcher("UserServlet?action=registerClass");
                    rd.forward(request, response);
                }
            }
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
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
