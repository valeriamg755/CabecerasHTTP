package controllers.student;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import repositories.impls.normal.StudentRepositoryImpl;
import services.StudentService;
import services.impl.StudentServiceImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "studentController", value = "/student-form")
public class StudentController extends HttpServlet {

    public StudentRepositoryImpl studentRepository;
    public StudentService service;

    private String message;

    public void init() {
        message = "Students";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        Connection conn = (Connection) request.getAttribute("conn");
        studentRepository = new StudentRepositoryImpl(conn);
        service = new StudentServiceImpl(conn);

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>Students</h1>");
        out.println(service.list());
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        Connection conn = (Connection) req.getAttribute("conn");
        studentRepository = new StudentRepositoryImpl(conn);
        service = new StudentServiceImpl(conn);
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String semester = req.getParameter("semester");
        String career = req.getParameter("career");

        List<String> errores = getErrors(name, email, career, semester);
        Map<String, String> errorsmap = getErrors2(name, email, career, semester);

        if (errorsmap.isEmpty()) {
            service.update(StudentDto.builder()
                    .name(name)
                    .email(email)
                    .semester(semester)
                    .build());
            System.out.println(service.list());

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
                out.println("            <li>Email: " + email + "</li>");
                out.println("            <li>Semester: " + semester + "</li>");
                out.println("        </ul>");
                out.println("    </body>");
                out.println("</html>");
            }
        } else {

            req.setAttribute("errors", errores);
            req.setAttribute("errorsmap", errorsmap);
            getServletContext().getRequestDispatcher("/student.jsp").forward(req, resp);
        }
    }

    private Map<String, String> getErrors2(String name, String email, String semester, String career) {
        Map<String, String> errors = new HashMap<>();
        if (name == null || name.isBlank()) {
            errors.put("name", "El nombre es requerido");
        }
        if (email == null || email.isBlank()) {
            errors.put("email", "El email es requerido");
        }
        if (semester == null || semester.isBlank()) {
            errors.put("semester", "El semestre es requerido");
        }

        return errors;
    }

    private List<String> getErrors(String name, String email, String career, String semester) {
        List<String> errors = new ArrayList<String>();
        if (name == null || name.isBlank()) {
            errors.add("El nombre es requerido");
        }
        if (email == null || email.isBlank()) {
            errors.add("El email es requerido");
        }
        if (semester == null || semester.isBlank()) {
            errors.add("El semestre es requerido");
        }
        return errors;
    }

    public void destroy() {
    }
}