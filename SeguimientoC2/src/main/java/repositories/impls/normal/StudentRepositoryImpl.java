package repositories.impls.normal;

import domain.mapping.dto.StudentDto;
import domain.mapping.mappers.StudentMapper;
import domain.models.Student;
import repositories.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentRepositoryImpl implements Repository<StudentDto> {
    private Connection conn;

    public StudentRepositoryImpl(Connection conn) {
        this.conn = conn;
    }

    private Student createStudent(ResultSet rs) throws SQLException {
        Student student = new Student();
        student.setIdStudent(rs.getLong("idStu"));
        student.setName(rs.getString("name"));
        student.setEmail(rs.getString("email"));
        student.setSemester(rs.getString("semester"));
        return student;
    }

    @Override
    public List<StudentDto> list() {
        List<Student> studentList = new ArrayList<>();

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * from student")) {
            while (rs.next()) {
                Student ps = createStudent(rs);
                studentList.add(ps);
            }
        } catch (SQLException e) {
            throw new ServiceJDBCException("Unable to list info");
        }
        return StudentMapper.mapFrom(studentList);
    }


    @Override
    public StudentDto byId(Long id) {
        Student student = null;
        try (PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM student WHERE idStudent=?")) {
            pstmt.setLong(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    student = createStudent(rs);
                }
            }
        } catch (SQLException e) {
            throw new ServiceJDBCException("Unable to find info");
        }
        return StudentMapper.mapFrom(student);
    }

    @Override
    public void update(StudentDto student) {
        String sql;
        if (student.idStudent() != null && student.idStudent() > 0) {
            sql = "UPDATE student SET name=?, email=?, semester=? WHERE idStudent=?";
        } else {
            sql = "INSERT INTO student (name, email, semester) VALUES(?,?,?)";
        }
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, student.name());
            pstmt.setString(2, student.email());
            pstmt.setString(3, student.semester());

            if (student.idStudent() != null && student.idStudent() > 0) {
                pstmt.setLong(4, student.idStudent());
            }
            pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throw new ServiceJDBCException("Unable to save info");
        }
    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement pstmt = conn.prepareStatement("DELETE FROM student WHERE idStudent = ?")) {
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new ServiceJDBCException("Unable to delete info");
        }
    }
}