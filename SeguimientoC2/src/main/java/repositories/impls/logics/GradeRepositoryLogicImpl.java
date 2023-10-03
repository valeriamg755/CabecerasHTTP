package repositories.impls.logics;

import domain.mapping.dto.GradeDto;
import domain.mapping.mappers.GradeMapper;
import domain.models.Grade;
import domain.models.Student;
import domain.models.Subject;
import domain.models.Teacher;
import repositories.Repository;

import java.sql.Connection;
import java.util.List;

public class GradeRepositoryLogicImpl implements Repository<GradeDto> {

    private List<Grade> grades;

    public GradeRepositoryLogicImpl(Connection conn){
        Teacher t1 = new Teacher(1L, "Violet","violet@cue.edu.com");
        Teacher t2 = new Teacher(2L, "Xaden","xaden@cue.edu.com");

        Subject sub1 = new Subject(1L,"Historia","Violet");
        Subject sub2 = new Subject(2L,"Physics", "Xaden");
        Subject sub3 = new Subject(3L,"Chemistry", "Liam");

        Student s1 = new Student(1L,"Kim Dokja", "kd@cue.edu.com", "III");
        Student s2 = new Student(2L,"Yoo Joonghyuk", "yjh@cue.edu.com", "II");
        Student s3 = new Student(3L,"Han Sooyoung", "hsy@cue.edu.c", "I");

        Grade g1 = new Grade(1L,s1,sub1,5.0);
        Grade g2 = new Grade(2L,s2,sub2,4.5);
    }
    @Override
    public List<GradeDto> list() {
        return GradeMapper.mapFrom(grades);
    }

    @Override
    public GradeDto byId(Long id) {
        return null;
    }

    @Override
    public void save(GradeDto gradesDto) {
        GradeMapper.mapFrom(grades);
    }

    @Override
    public void delete(Long id) {
        GradeMapper.mapFrom(grades);
    }
}
