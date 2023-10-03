package repositories.impls.normal;

import connection.ConnectionDB;
import domain.mapping.dto.GradeDto;
import domain.mapping.mappers.GradeMapper;
import domain.models.Grade;
import domain.models.Student;
import domain.models.Subject;
import domain.models.Teacher;
import repositories.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GradeRepositoryImpl implements Repository<GradeDto> {
    private Connection conn;

    public GradesRepositoryImpl(Connection conn) {
        this.conn = conn;
    }

    private Connection getConnection() throws SQLException, ClassNotFoundException {
        return ConnectionDB.getInstance();
    }


    private Grade buildObject(ResultSet resultSet) throws SQLException {
        Grade grades = new Grade();
        grades.setIdGrade(resultSet.getLong("idGrades"));

        Student student = new Student();
        student.setIdStudent(resultSet.getLong("idStudent"));
        student.setName(resultSet.getString("name"));
        student.setEmail(resultSet.getString("email"));
        student.setSemester(resultSet.getString("semester"));
        grades.setStudent(student);

        Subject subject = new Subject();
        subject.setIdSubject(resultSet.getLong("idSubject"));
        subject.setName(resultSet.getString("name"));

        Teacher teacher = new Teacher();
        teacher.setIdTeacher(resultSet.getLong("idTeacher"));
        teacher.setName(resultSet.getString("name"));
        teacher.setEmail(resultSet.getString("email"));
        subject.setTeacher(String.valueOf(teacher));

        grades.setSubject(subject);

        return grades;
    }

    @Override
    public List<GradeDto> list() {
        List<Grade> gradesList = new ArrayList<>();
        try (Statement statement = getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT student.id_student ,student.name, student.email," +
                     "student.semester, subject.name, teachers.name, teachers.email, grades.corte FROM" +
                     " grades INNER JOIN student on grades.id_student=student.id_student INNER JOIN subject on " +
                     "grades.idSub=subject.idSubject inner join teachers on " +
                     "subject.idTeacher=teachers.idTea;")) {
            while (resultSet.next()) {
                Grade grades = buildObject(resultSet);
                gradesList.add(grades);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return GradesMapper.mapFrom(gradesList);
    }

    @Override
    public GradeDto byId(Long id) {
        Grade grades = null;
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement("SELECT student.idStu ,student.name, student.email, " +
                        "student.semester, subject.name, teacher.name, teacher.email, grades.corte FROM grades " +
                        "INNER JOIN student on grades.idStu=student.idStudent INNER JOIN subject on " +
                        "grades.idSub=subject.idSubject inner join teacher on " +
                        "subject.idTeacher=teachers.idTea WHERE grades.idGra = ?")) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                grades = buildObject(resultSet);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return GradeMapper.mapFrom(grades);
    }


    @Override
    public void update(GradeDto grades) {
        String sql;
        if (grades.idGrade() != null && grades.idGrade() > 0) {
            sql = "UPDATE grade SET idStu=?, idSub=? , grade=?  WHERE idGrade=?";
        } else {
            sql = "INSERT INTO grade (idStudent, idSubject, grade) VALUES(?,?,?)";
        }
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setLong(1, grades.student().getIdStudent());
            stmt.setLong(2, grades.subject().getIdSubject());

            if (grades.idGrade() != null && grades.idGrade() > 0) {
                stmt.setLong(3, grades.idGrade());
            }
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement stmt = getConnection().prepareStatement("DELETE FROM grade WHERE idGrade =?")) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}