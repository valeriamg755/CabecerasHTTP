package controllers.student;

import com.fasterxml.jackson.databind.ObjectMapper;
import domain.models.Student;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import repositories.impls.logics.StudentRepositoryLogicImpl;
import services.StudentService;
import services.impl.StudentServiceImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet({"/student.json"})
@WebFilter({"/public/students"})
public class StudentJson extends HttpServlet implements Filter {

    public StudentRepositoryLogicImpl student;
    public StudentService service;

    public StudentJson() {
        student = new StudentRepositoryLogicImpl();
        service = new StudentServiceImpl(student);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws
            ServletException, IOException {
        ServletInputStream jsonStream = req.getInputStream();
        ObjectMapper mapper = new ObjectMapper();
        Student student = mapper.readValue(jsonStream, Student.class);
        resp.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = resp.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println(" <head>");
            out.println(" <meta charset=\"UTF-8\">");
            out.println(" <title>Product detail from json</title>");
            out.println(" </head>");
            out.println(" <body>");
            out.println(" <h1>Product detail from json!</h1>");
            out.println("<ul>");
            out.println("<li>Id: " + student.getId() + "</li>");
            out.println("<li>Name: " + student.getName() + "</li>");
            out.println("<li>Email: " + student.getEmail() + "</li>");
            out.println("<li>Semester: " + student.getSemester() + "</li>");
            out.println("</ul>");
            out.println(" </body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        List<Student> students = service.list();
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(students);
        resp.setContentType("application/json");
        resp.getWriter().write(json);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws
            IOException, ServletException {

    }
}
