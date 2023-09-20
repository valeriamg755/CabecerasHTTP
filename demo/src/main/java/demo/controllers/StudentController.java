package demo.controllers;

import demo.domain.mapping.dtos.StudentDto;
import demo.domain.models.Student;
import demo.services.StudentService;
import demo.services.impls.StudentServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "studentController", value = "/student-form")
public class StudentController extends HttpServlet {

    private final StudentService service;

    public StudentController() {
        service = new StudentServiceImpl(); // Asegúrate de configurar la inyección de dependencias.
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>Students</h1>");

        List<StudentDto> students = service.listar();
        for (StudentDto student : students) {
            out.println("<p>Name: " + student.getName() + "</p>");
            out.println("<p>Semester: " + student.getSemester() + "</p>");
            out.println("<p>Email: " + student.getEmail() + "</p>");
        }

        out.println("</body></html>");
    }
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>Students</h1>");
        out.println(service.listar());
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        String name = req.getParameter("name");
        String semester = req.getParameter("semester");
        String email = req.getParameter("email");
        Student student = new Student(4L, name,semester,email);
        service.guardar(student);
        System.out.println(service.listar());

        try (PrintWriter out = resp.getWriter()) {

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("    <head>");
            out.println("        <meta charset=\"UTF-8\">");
            out.println("        <title>Resultado form</title>");
            out.println("    </head>");
            out.println("    <body>");
            out.println("        <h1>Resultado form!</h1>");
            out.println("        <ul>");
            out.println("            <li>Name: " + name + "</li>");
            out.println("            <li>Semester: " + semester + "</li>");
            out.println("            <li>Email: " + email + "</li>");
            out.println("        </ul>");
            out.println("    </body>");
            out.println("</html>");
        }
    }

    public void destroy() {
    }
}