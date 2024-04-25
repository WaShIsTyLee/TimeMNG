package github.washistylee.Model.Test;

import github.washistylee.Model.DAO.MinderDAO;
import github.washistylee.Model.DAO.TeacherDAO;
import github.washistylee.Model.Entitys.Minder;
import github.washistylee.Model.Entitys.Teacher;

public class TestConsultas {
    public static void main(String[] args) {
        TeacherDAO teacherDAO = new TeacherDAO();
        MinderDAO minderDAO = new MinderDAO();
        Teacher teacher = new Teacher();
        Minder minder = new Minder();

        teacher = teacherDAO.verifyCredentialDAO("programacion@gmail.com");
        minder=minderDAO.verifyCredentialDAO("juansan@gmail.com");
        System.out.println(teacher);
        System.out.println(minder);
    }

}
