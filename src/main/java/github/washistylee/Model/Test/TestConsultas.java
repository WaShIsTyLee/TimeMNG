package github.washistylee.Model.Test;

import github.washistylee.Model.DAO.ChildDAO;
import github.washistylee.Model.DAO.MinderDAO;
import github.washistylee.Model.DAO.TeacherDAO;
import github.washistylee.Model.Entitys.Child;
import github.washistylee.Model.Entitys.Minder;
import github.washistylee.Model.Entitys.Teacher;

import java.util.ArrayList;

public class TestConsultas {
    public static void main(String[] args) {
        TeacherDAO teacherDAO = new TeacherDAO();
        MinderDAO minderDAO = new MinderDAO();
        ChildDAO childDAO = new ChildDAO();

        ArrayList <Child> ch = childDAO.findChildByMinderMail("juanelsoldado16@gmail.com");
        System.out.println(ch);

    }

}
