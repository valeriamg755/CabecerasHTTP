package CabecerasHTTP.repositories.impls;

import CabecerasHTTP.domain.model.Student;
import CabecerasHTTP.repositories.Repository;
import CabecerasHTTP.domain.mapping.dto.StudentDto;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentRepositoryJDBCimpl implements Repository<Student> {
    private Connection conn;
    public StudentRepositoryJdbcImpl(Connection conn) {
        this.conn = conn;
    }
    @Override
    public List<StudentDto> list() throws SQLException {
        List<Integer> students = new ArrayList<>();
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT s.* order by s.id ASC")) {
            while (rs.next()) {
                Student ps= getClass(rs);
                students.add(s);
            }
        }
        return mapper.mapFrom(students);
    }
//demas metodos
}
