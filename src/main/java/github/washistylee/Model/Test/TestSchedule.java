package github.washistylee.Model.Test;

import github.washistylee.Model.DAO.ChildDAO;
import github.washistylee.Model.DAO.ScheduleDAO;
import github.washistylee.Model.Entitys.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class TestSchedule {
    public static void main(String[] args) {

        ScheduleDAO sdao = new ScheduleDAO();
        ArrayList<String> activi = new ArrayList<>();
        ArrayList<String> enfermedad = new ArrayList<>();
        Minder minder = new Minder();
        Teacher teacher = new Teacher();
        Child child = new Child();
        child.setTeacher(teacher);
        child.setMinder(minder);
        ChildDAO CDAO = new ChildDAO();
        child.setId(7);
        activi.add("correr");
        child.setDiseases(enfermedad);

        child.setAge(12);
        CDAO.save(child);
        Schedule sd = new Schedule(Month.ENERO, LocalDate.ofEpochDay(1), LocalTime.MIN,activi, child,child.getId());


            sdao.delete(sd);

    }


}
