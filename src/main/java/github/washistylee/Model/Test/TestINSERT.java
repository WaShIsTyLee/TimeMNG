
package github.washistylee.Model.Test;

import github.washistylee.Model.DAO.ChildDAO;
import github.washistylee.Model.DAO.MinderDAO;
import github.washistylee.Model.DAO.TeacherDAO;
import github.washistylee.Model.Entitys.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class TestINSERT {
    public static void main(String[] args) {
        ArrayList<String> enfermedades = new ArrayList<>();
        enfermedades.add("Diabetes tipo 1");
        enfermedades.add("Tiroides");
        //LocalDate date = LocalDate.of(2024, Month.ENERO, 15);
        // LocalDateTime dateTime = date.atTime(10, 30);


        Child child = new Child();


        ArrayList<Child> childs = new ArrayList<>();
        ArrayList<String> diseases = new ArrayList<>();
        diseases.add("HOLA");

        Teacher teacher = new Teacher("Carlos", "Serrano", "programacion@gmail.com", "1",childs, "Programacion");
        TeacherDAO tdao = new TeacherDAO();
        tdao.save(teacher);


        Minder md = new Minder("Mari Corpus", "Solano", "juansan@gmail.com", "1", "6.30", childs);
        MinderDAO mdao = new MinderDAO();
        mdao.save(md);
        child.setMinder(md);
        child.setTeacher(teacher);
        child.setDiseases(diseases);

        ChildDAO cdao = new ChildDAO();
        cdao.save(child);




        System.out.println(tdao.findByMail(teacher.getEmail()));
    }
}

