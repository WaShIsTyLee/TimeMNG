package github.washistylee.Model.Test;

import github.washistylee.Model.DAO.ChildDAO;
import github.washistylee.Model.DAO.MinderDAO;
import github.washistylee.Model.DAO.TeacherDAO;
import github.washistylee.Model.Entitys.Child;
import github.washistylee.Model.Entitys.Minder;
import github.washistylee.Model.Entitys.Teacher;

public class TestDELETE {
    public static void main(String[] args) {


        Teacher teacher = new Teacher();
        Minder minder = new Minder();
        MinderDAO md = new MinderDAO();
        TeacherDAO td = new TeacherDAO();
        minder.setEmail("juansan@gmail.com");
        Child child = new Child();
        ChildDAO CDAO = new ChildDAO();
        minder.setEmail("juansan@gmail.com");
        teacher.setEmail("programacion@gmail.com");
        child.setMinder(minder);
        child.setTeacher(teacher);
        child.setId(5);
        CDAO.delete(child);
        td.delete(teacher);
        md.delete(minder);






    }

}
