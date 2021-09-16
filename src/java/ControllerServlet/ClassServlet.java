/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControllerServlet;

import DAO.ClassDAO;
import DAO.ClassSubjectDAO;
import DAO.StudentDAO;
import DAO.SubjectDAO;
import DAO.TeacherDAO;
import DTO.Class_Subject;
import DTO.Student;
import DTO.Subject;
import DTO.Teacher;
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
@WebServlet(name = "ClassServlet", urlPatterns = {"/ClassServlet"})
public class ClassServlet extends HttpServlet {

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
        HttpSession session = request.getSession();
        String username=(String)session.getAttribute("name");
        request.setAttribute("name", username);
        try{
            if(action == null){ //open the book list with add
                ArrayList<DTO.Class> ClassList = new ArrayList<>();
                
                ClassDAO dao = new ClassDAO();
                ClassList = dao.getAllClass();
                
                request.setAttribute("data", ClassList);
                
                RequestDispatcher rd = request.getRequestDispatcher("Admin/class/classList.jsp");
                rd.forward(request, response);
            }
            
            else if (action.equals("addclassform")){
                DTO.Class c = new DTO.Class();
                
                request.setAttribute("cObject", c);
                request.setAttribute("msg", "add new Class");
                request.setAttribute("action", "Create_Class");
                
                RequestDispatcher rd = request.getRequestDispatcher("Admin/class/classForm.jsp");
                rd.forward(request, response);
            }
            
            else if (action.equals("updateclassform")){
                String id = request.getParameter("cid");
                
                ClassDAO cDAO = new ClassDAO();
                
                DTO.Class c = cDAO.getClassById(id);
                
                request.setAttribute("cObject", c);
                request.setAttribute("msg", "update Class");
                request.setAttribute("action", "Update_Class");
                
                RequestDispatcher rd = request.getRequestDispatcher("Admin/class/classForm.jsp");
                rd.forward(request, response);
            }
            
            else if (action.equals("deleteclassconfirm")) { // xac nhan xoa
                String id = request.getParameter("cid");
                
                request.setAttribute("c", id);
                RequestDispatcher rd = request.getRequestDispatcher("Admin/class/deleteclassconfirm.jsp");
                rd.forward(request, response);
            }
            
            else if (action.equals("deleteclass")) { //xoa
                String id = request.getParameter("cid");
                
                StudentDAO stDAO=new StudentDAO();
                ArrayList<Student> s=stDAO.getStudentByClassId(id);
                if(!s.isEmpty()){
                    request.setAttribute("error", "Can't delete because there is student here");
                    RequestDispatcher rd = request.getRequestDispatcher("Admin/class/classerror.jsp");
                    rd.forward(request, response);
                }
                else{
                ClassDAO dao = new ClassDAO();
                ClassSubjectDAO csDAO = new ClassSubjectDAO();
                
                csDAO.DeleteClassSubjectByClassId(id);
                dao.deleteClass(id);
                response.sendRedirect("ClassServlet");
                }
            } 
            
            else if (action.equals("Create_Class")) { //tao class trong db
                String id = request.getParameter("id");
                String n = request.getParameter("name");
                String a = request.getParameter("attendant");
                boolean valid = true;
                
                if (id==null){
                request.setAttribute("ERROR", "You not suppose to create like this");
                RequestDispatcher rd = request.getRequestDispatcher("Admin/class/error.jsp");
                rd.forward(request, response);
                }
                
                if (id.trim().isEmpty()){
                    request.setAttribute("errorID", "ID is empty");
                    valid=false;
                }
                if (n.trim().isEmpty()){
                    request.setAttribute("errorName", "Name is empty");
                    valid=false;
                }
                if (a.trim().isEmpty()){
                    request.setAttribute("errorAttendant", "Attendant is empty");
                    valid=false;
                }
                ClassDAO cDAO = new ClassDAO();
                if(cDAO.getClassById(id)!=null){
                    request.setAttribute("errorID", "ID already existed");
                    valid=false;
                }
                if(valid){
                    DTO.Class c = new DTO.Class(id, n, a);

                    cDAO.CreateClass(c);

                    response.sendRedirect("ClassServlet");
                }
                else {
                    request.getRequestDispatcher("ClassServlet?action=addclassform").forward(request, response);
                }
            }
            
            else if (action.equals("Update_Class")) { //update class trong db
                String id = request.getParameter("id");
                String n = request.getParameter("name");
                String a = request.getParameter("attendant");
                boolean valid=true;
                
                if (id==null){
                request.setAttribute("ERROR", "You not suppose to update like this");
                RequestDispatcher rd = request.getRequestDispatcher("Admin/class/error.jsp");
                rd.forward(request, response);
                }
                
                if (n.trim().isEmpty()){
                    request.setAttribute("errorName", "Name is empty");
                    valid=false;
                }
                if (a.trim().isEmpty()){
                    request.setAttribute("errorAttendant", "Attendant is empty");
                    valid=false;
                }
                
                if (valid){
                    ClassDAO cDAO = new ClassDAO();
                
                    DTO.Class c = new DTO.Class(id, n, a);

                    cDAO.UpdateClass(c);

                    response.sendRedirect("ClassServlet");
                }
                else {
                    request.getRequestDispatcher("ClassServlet?action=updateclassform&cid=" + id).forward(request, response);
                }
                
            }
            
            else if (action.equals("addsubjectform")){
                String id = request.getParameter("cid");
                
                ClassDAO cDAO = new ClassDAO();
                SubjectDAO sDAO = new SubjectDAO();
                
                DTO.Class c = cDAO.getClassById(id);
                ArrayList<Subject> sList = sDAO.getAllSubject();
                
                TeacherDAO tDAO= new TeacherDAO();
                ArrayList<Teacher> tList = tDAO.getAllTeacher();
                
                request.setAttribute("cObject", c);
                request.setAttribute("sList", sList);
                request.setAttribute("tList", tList);
                request.setAttribute("msg", "add Class Subject");
                request.setAttribute("action", "Add_Class_Subject");
                
                RequestDispatcher rd = request.getRequestDispatcher("Admin/class/subjectform.jsp");
                rd.forward(request, response);
            }
            
            else if (action.equals("Add_Class_Subject")){
                String c = request.getParameter("class");
                String su = request.getParameter("subject");
                String se = request.getParameter("semester");
                String t = request.getParameter("teacher");
                boolean valid = true;
                
                if (c==null){
                request.setAttribute("ERROR", "You not suppose to create like this");
                RequestDispatcher rd = request.getRequestDispatcher("Admin/class/error.jsp");
                rd.forward(request, response);
                }
                
                if(se.trim().isEmpty()){
                    request.setAttribute("errorSemester", "Semester is empty");
                    valid=false;
                }
                ClassSubjectDAO csDAO = new ClassSubjectDAO();
                Class_Subject cs = csDAO.SearchClass(c, su);
                if(cs!=null){
                    request.setAttribute("errorExisted", "This class already have this subject");
                    valid=false;
                }
                
                if (valid){
                    csDAO.AddSubjectToClass(c, su, se, t);

                    response.sendRedirect("ClassServlet?action=classsubject&cid=" + c);
                }
                else {
                    request.getRequestDispatcher("ClassServlet?action=addsubjectform&cid=" + c).forward(request, response);
                }
                
            }
            
            else if (action.equals("classsubject")){
                String id = request.getParameter("cid");
                
                ClassSubjectDAO csDAO=new ClassSubjectDAO();
                ArrayList<Class_Subject> csList = csDAO.ViewClassSubjectC(id);
                
                ClassDAO cDAO = new ClassDAO();
                DTO.Class c = cDAO.getClassById(id);
                
                request.setAttribute("cObject", c);
                request.setAttribute("csList", csList);
                
                RequestDispatcher rd = request.getRequestDispatcher("Admin/class/viewClassSubject.jsp");
                rd.forward(request, response);
            }
            
            else if (action.equals("deletesubjectform")){
                String cid = request.getParameter("cid");
                String suid = request.getParameter("suid");
                
                ClassSubjectDAO csDAO=new ClassSubjectDAO();
                csDAO.DeleteClassSubject(cid, suid);
                
                RequestDispatcher rd = request.getRequestDispatcher("ClassServlet?action=classsubject&cid=" + cid);
                rd.forward(request, response);
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
