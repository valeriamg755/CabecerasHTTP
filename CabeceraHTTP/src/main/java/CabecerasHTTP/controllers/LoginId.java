package CabecerasHTTP.controllers;

import CabecerasHTTP.domain.model.Student;
import CabecerasHTTP.repositories.impls.StudentRepositoryLogicImpl;
import CabecerasHTTP.services.StudentService;
import CabecerasHTTP.services.impl.StudentServiceImpl;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/loginId")
@WebFilter({"/private/login"}) //Solo controllers que necesitan login!!! Los que no son public!
public class LoginId extends HttpServlet implements Filter {

    private StudentRepositoryLogicImpl studentRepository;
    private StudentService service;

    public void StudentController() {
        studentRepository = new StudentRepositoryLogicImpl();
        service = new StudentServiceImpl(studentRepository);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        Student student = studentRepository.porId(id);
        if (student != null) {
            resp.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = resp.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println(" <head>");
                out.println(" <meta charset=\"UTF-8\">");
                out.println(" <title>Found successfully</title>");
                out.println(" </head>");
                out.println(" <body>");
                out.println(" <h1>Found successfully!</h1>");
                out.println(" <h3>Hello "+ id + " you have found successfully!</h3>");
                out.println(" </body>");
                out.println("</html>");
            }
        } else {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "You are not authorized to enter this page!");
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws
            IOException, ServletException {

    }
}

