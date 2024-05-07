package github.washistylee.Model.Test;

import github.washistylee.Model.DAO.ChildDAO;
import github.washistylee.Model.DAO.ScheduleDAO;
import github.washistylee.Model.Entitys.*;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

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
        child.setId(51);
        activi.add("correr");
        child.setDiseases(enfermedad);
        LocalDate currentDate = LocalDate.now(); // Esto obtiene la fecha actual.
        Date time = new Date(); // Esto crea un objeto Date con la fecha y hora actuales.
        Time currentTime = Time.valueOf(LocalTime.now()); // Esto obtiene la hora actual.

        Date date = Date.from(currentDate.atStartOfDay(ZoneId.systemDefault()).toInstant()); // Convierte LocalDate a Date.
        child.setAge(12);
       // CDAO.save(child);
        //Schedule sd = new Schedule(Month.ENERO, date, currentTime, activi, child, child.getId());
        //sdao.save(sd);

        // sdao.delete(sd);

    }


}
