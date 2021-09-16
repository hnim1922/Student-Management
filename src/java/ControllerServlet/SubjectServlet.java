/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControllerServlet;

import DAO.ClassDAO;
import DAO.SubjectDAO;
import DTO.Subject;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "SubjectServlet", urlPatterns = {"/SubjectServlet"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 10,
        maxFileSize = 1024 * 1024 * 50,
        maxRequestSize = 1024 * 1024 * 100
)
public class SubjectServlet extends HttpServlet {
private static final String UPLOAD_DIR = "image";
    private String uploadFile(HttpServletRequest request) throws IOException, ServletException {
        String fileName = "";
        try {
            Part filePart = request.getPart("Photo");
            fileName = (String) getFileName(filePart);
 
            String applicationPath = request.getServletContext().getRealPath("");
            String basePath = applicationPath + File.separator + UPLOAD_DIR + File.separator;
            InputStream inputStream = null;
            OutputStream outputStream = null;
            try {
                File outputFilePath = new File(basePath + fileName);
                inputStream = filePart.getInputStream();
                outputStream = new FileOutputStream(outputFilePath);
                int read = 0;
                final byte[] bytes = new byte[1024];
                while ((read = inputStream.read(bytes)) != -1) {
                    outputStream.write(bytes, 0, read);
                }
            } catch (Exception e) {
                e.printStackTrace();
                fileName = "";
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            }
 
        } catch (Exception e) {
            e.printStackTrace();
            fileName = "";
        }
        return fileName;
    }
    private String getFileName(Part part) {       
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
 
        return null;
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getSession().getAttribute("name");
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        String username=(String)session.getAttribute("name");
        request.setAttribute("name", username);
        
        try{
            if (action == null){ //open the book list with add
                ArrayList<Subject> SubjectList = new ArrayList<>();
                
                SubjectDAO dao = new SubjectDAO();
                SubjectList = dao.getAllSubject();
                
                request.setAttribute("data", SubjectList);
                
                RequestDispatcher rd = request.getRequestDispatcher("Admin/subject/subjectList.jsp");
                rd.forward(request, response);
            }
            else if(action.equals("setAvailableSubjectForm")){
                ArrayList<Subject> SubjectList = new ArrayList<>();                
                SubjectDAO dao = new SubjectDAO();
                SubjectList = dao.getAllSubject();          
                request.setAttribute("data1",SubjectList);
                request.setAttribute("msg","Set Available Subject");
               request.getRequestDispatcher("Admin/subject/subjectAvaForm.jsp").forward(request, response);
            }         
             else if(action.equals("setAvailable")){
                    String cla = request.getParameter("sub");
                    String id = cla.split("-")[0].trim();
                    String name = cla.split("-")[1].trim();
                    String ava = request.getParameter("ava");
                    String url = uploadFile(request); // Upload file vào thư mục, return image name              
                    SubjectDAO suDAO = new SubjectDAO();
                    Subject su = new Subject(id, name, Boolean.parseBoolean(ava),url);
                    suDAO.setAvailableSubject(su);
                    response.sendRedirect("SubjectServlet");
                
            }
            else if (action.equals("addsubjectform")){
                Subject su = new Subject();          
                request.setAttribute("suObject", su);
                request.setAttribute("msg", "add new Subject");
                request.setAttribute("action", "Create_Subject");
                
                RequestDispatcher rd = request.getRequestDispatcher("Admin/subject/subjectForm.jsp");
                rd.forward(request, response);
            }
            
            else if (action.equals("updatesubjectform")){
                String id = request.getParameter("suid");
                
                SubjectDAO suDAO = new SubjectDAO();
                
                Subject su = suDAO.getSubjectById(id);
                
                request.setAttribute("suObject", su);
                request.setAttribute("msg", "update Subject");
                request.setAttribute("action", "Update_Subject");
                
                RequestDispatcher rd = request.getRequestDispatcher("Admin/subject/subjectForm.jsp");
                rd.forward(request, response);
            }
            
            else if (action.equals("deletesubjectconfirm")) { // xac nhan xoa
                String id = request.getParameter("suid");
                
                request.setAttribute("su", id);
                RequestDispatcher rd = request.getRequestDispatcher("Admin/subject/deletesubjectconfirm.jsp");
                rd.forward(request, response);
            }
            
            else if (action.equals("deletesubject")) { //xoa
                String id = request.getParameter("suid");
                
                SubjectDAO dao = new SubjectDAO();
                
                dao.deleteSubject(id);
                response.sendRedirect("SubjectServlet");
            } 
            
            else if (action.equals("Create_Subject")) { //tao class trong db
                String id = request.getParameter("id");
                String n = request.getParameter("name");
                boolean valid = true;
                
                if (id==null){
                request.setAttribute("ERROR", "You not suppose to create like this");
                RequestDispatcher rd = request.getRequestDispatcher("Admin/subject/error.jsp");
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
                SubjectDAO suDAO = new SubjectDAO();
                if (suDAO.getSubjectById(id)!=null){
                    request.setAttribute("errorID", "ID already existed");
                    valid=false;
                }
                if(valid){
                    Subject su = new Subject(id, n);

                    suDAO.CreateSubject(su);

                    response.sendRedirect("SubjectServlet");
                }
                else {
                    request.getRequestDispatcher("SubjectServlet?action=addsubjectform").forward(request, response);
                }
                
            }
            
            else if (action.equals("Update_Subject")) { //update class trong db
                String id = request.getParameter("id");
                String n = request.getParameter("name");
                boolean valid = true;
                
                if (id==null){
                request.setAttribute("ERROR", "You not suppose to update like this");
                RequestDispatcher rd = request.getRequestDispatcher("Admin/subject/error.jsp");
                rd.forward(request, response);
                }
                
                if (n.trim().isEmpty()){
                    request.setAttribute("errorName", "why bother making it empty");
                    valid=false;
                }
                if (valid){
                    SubjectDAO suDAO = new SubjectDAO();
                
                    Subject su = new Subject(id, n);

                    suDAO.UpdateSubject(su);

                    response.sendRedirect("SubjectServlet");
                }
                else{
                    request.getRequestDispatcher("SubjectServlet?action=updatesubjectform&suid=" + id).forward(request, response);
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
