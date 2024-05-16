

package github.washistylee.Model.Test;

import github.washistylee.Model.DAO.ChildDAO;
import github.washistylee.Model.DAO.MinderDAO;
import github.washistylee.Model.DAO.TeacherDAO;
import github.washistylee.Model.Entitys.Child;
import github.washistylee.Model.Entitys.Minder;
import github.washistylee.Model.Entitys.Teacher;

import java.util.ArrayList;

public class TestUPDATE {
    public static void main(String[] args) {
        ArrayList<String> enfermedades = new ArrayList<>();
        enfermedades.add("ASMA");

        ArrayList<Child> childs = new ArrayList<>();

        Teacher teacher = new Teacher("RAFA", "PEREZ", "programacion@gmail.com", "1", childs, "Programacion");
        Teacher teacher1 = new Teacher("CARLOS", "PEREZ", "programacion@gmail.com", "1", childs, "Programacion");

        TeacherDAO tdao = new TeacherDAO();
        tdao.save(teacher1);



    }
}
