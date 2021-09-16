/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControllerServlet;

import DAO.ClassDAO;
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
@WebServlet(name = "StudentServlet", urlPatterns = {"/StudentServlet"})
public class StudentServlet extends HttpServlet {

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
            if(action == null){
                ArrayList<Student> StudentList = new ArrayList<>();
                
                StudentDAO dao = new StudentDAO();
                StudentList = dao.getAllStudent();
                
                request.setAttribute("data", StudentList);
                
                RequestDispatcher rd = request.getRequestDispatcher("Admin/student/studentList.jsp");
                rd.forward(request, response);
            }
            else if(action.equals("Search_Student")){
                String searchedId=request.getParameter("search");
                ArrayList<Student> StudentList = new ArrayList<>();
                
                StudentDAO dao = new StudentDAO();
                StudentList = dao.getAllStudent();
                boolean founded = false;
                if (searchedId!=""){
                    ArrayList<Student> StudentListSearch = new ArrayList<>();
                    for (Student std: StudentList){
                        if (searchedId.equals(std.getId())){
                            StudentListSearch.add(std);
                            founded=true;
                        }
                    }
                    if(!founded){
                    request.setAttribute("IDNOTFOUND","Can not found ID:"+searchedId);
                      request.setAttribute("data",StudentList);
                    }else{
                    request.setAttribute("data",StudentListSearch);
                    }
                }
                else {
                    request.setAttribute("data", StudentList);
                }
                
                RequestDispatcher rd = request.getRequestDispatcher("Admin/student/studentList.jsp");
                rd.forward(request, response);
            }
            
            else if (action.equals("addstudentform")) { // Hiển thị form để tạo mới student
                Student s = new Student();
                request.setAttribute("sObject", s);
                
                User u = new User();
                request.setAttribute("uObject", u);
                
                request.setAttribute("msg", "add new Student");
                request.setAttribute("action", "Create_Student");
                
                RequestDispatcher rd = request.getRequestDispatcher("Admin/student/studentform.jsp");
                rd.forward(request, response);
            } 
            
            else if (action.equals("updatestudentform")) { // cap nhat student
                String id = request.getParameter("sid");
                
                StudentDAO dao = new StudentDAO();
                Student s = dao.getStudentById(id);
                
                UserDAO uDAO = new UserDAO();
                User u = uDAO.getUserByEmail(s.getEmail());
                
                request.setAttribute("sObject", s);
                request.setAttribute("uObject", u);
                request.setAttribute("msg", "Update Student (Id: " + id + ")");
                request.setAttribute("action", "Update_Student");
                
                RequestDispatcher rd = request.getRequestDispatcher("Admin/student/studentform.jsp");
                rd.forward(request, response);
            } 
            
            else if (action.equals("deletestudentconfirm")) { // xac nhan xoa
                String id = request.getParameter("sid");
                
                request.setAttribute("s", id);
                RequestDispatcher rd = request.getRequestDispatcher("Admin/student/deletestudentconfirm.jsp");
                rd.forward(request, response);
            }
            
            else if (action.equals("deletestudent")) { //xoa
                String id = request.getParameter("sid");
                
                StudentDAO dao = new StudentDAO();
                Student s= dao.getStudentById(id);
                
                UserDAO uDAO = new UserDAO();
                
                uDAO.deleteUser(s.getEmail());
                dao.deleteStudent(id);
                
                response.sendRedirect("StudentServlet");
            } 
            
            else if (action.equals("Update_Student")) { //cap nhat vao db
                String id = request.getParameter("id");
                String ln = request.getParameter("lastName");
                String fn = request.getParameter("firstName");
                String m = request.getParameter("major");
                String ss = request.getParameter("semester");
                String e = request.getParameter("email");
                String cid=request.getParameter("classId");
                String pass=request.getParameter("password");
                boolean valid=true;
                
                if (id==null){
                request.setAttribute("ERROR", "You not suppose to update like this");
                RequestDispatcher rd = request.getRequestDispatcher("Admin/student/error.jsp");
                rd.forward(request, response);
                }

                if (ln.trim().isEmpty()){
                    request.setAttribute("errorLn", "Last name is empty");
                    valid=false;
                }
                if (fn.trim().isEmpty()){
                    request.setAttribute("errorFn", "First name is empty");
                    valid=false;
                }
                if (m.trim().isEmpty()){
                    request.setAttribute("errorMajor", "Major is empty");
                    valid=false;
                }
                if (ss.trim().isEmpty()){
                    request.setAttribute("errorSemester", "Semester is empty");
                    valid=false;
                }
                if (pass.trim().isEmpty()){
                    request.setAttribute("errorPass", "Password is empty");
                    valid=false;
                }
                
                ClassDAO cDAO = new ClassDAO();
                DTO.Class c= cDAO.getClassById(cid);
                if (cid.trim().isEmpty()){
                    request.setAttribute("errorClass", "Class ID is empty");
                    valid=false;
                }
                else if (c==null){
                    request.setAttribute("errorClass", "Class ID does not existed");
                    valid=false;
                }
                if(valid){
                    StudentDAO sDAO= new StudentDAO();
                    Student s= new Student(id, ln, fn, m, ss, c, e);
                    sDAO.UpdateStudent(s);

                    UserDAO uDAO = new UserDAO();
                    User u = new User(e, pass);
                    uDAO.UpdateUser(u);

                    response.sendRedirect("StudentServlet");
                }
                else {
                    request.getRequestDispatcher("StudentServlet?action=updatestudentform&sid=" + id).forward(request, response);
                }
                
            } 
            
            else if (action.equals("Create_Student")) { //tao student trong db
                String id = request.getParameter("id");
                String ln = request.getParameter("lastName");
                String fn = request.getParameter("firstName");
                String m = request.getParameter("major");
                String ss = request.getParameter("semester");
                String e = request.getParameter("email");
                String cid=request.getParameter("classId");
                String pass=request.getParameter("password");
                boolean valid=true;
                
                if (id==null){
                request.setAttribute("ERROR", "You not suppose to create like this");
                RequestDispatcher rd = request.getRequestDispatcher("Admin/student/error.jsp");
                rd.forward(request, response);
                }

                if (ln.trim().isEmpty()){
                    request.setAttribute("errorLn", "Last name is empty");
                    valid=false;
                }
                if (fn.trim().isEmpty()){
                    request.setAttribute("errorFn", "First name is empty");
                    valid=false;
                }
                if (m.trim().isEmpty()){
                    request.setAttribute("errorMajor", "Major is empty");
                    valid=false;
                }
                if (ss.trim().isEmpty()){
                    request.setAttribute("errorSemester", "Semester is empty");
                    valid=false;
                }
                if (e.trim().isEmpty()){
                    request.setAttribute("errorEmail", "Email is empty");
                    valid=false;
                }
                if (pass.trim().isEmpty()){
                    request.setAttribute("errorPass", "Password is empty");
                    valid=false;
                }
                
                ClassDAO cDAO = new ClassDAO();
                DTO.Class c= cDAO.getClassById(cid);
                if (cid.trim().isEmpty()){
                    request.setAttribute("errorClass", "Class ID is empty");
                    valid=false;
                }
                else if (c==null){
                    request.setAttribute("errorClass", "Class ID does not existed");
                    valid=false;
                }
                StudentDAO sDAO = new StudentDAO();
                if (id.trim().isEmpty()){
                    request.setAttribute("errorID", "Student ID is empty");
                    valid=false;
                }
                else if (sDAO.getStudentById(id)!=null){
                    request.setAttribute("errorID", "Student ID existed");
                    valid=false;
                }
                if (valid){
                    Student s= new Student(id, ln, fn, m, ss, c, e);
                    sDAO.CreateStudent(s);

                    UserDAO uDAO = new UserDAO();
                    User u = new User(e, pass);
                    uDAO.createUser(u);

                    response.sendRedirect("StudentServlet");
                } else {
                    Student s= new Student(id, ln, fn, m, ss, c, e);
                    request.setAttribute("sObject", s);
                    request.getRequestDispatcher("StudentServlet?action=addstudentform").forward(request, response);
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
