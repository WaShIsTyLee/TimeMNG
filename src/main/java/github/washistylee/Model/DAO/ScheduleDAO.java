package github.washistylee.Model.DAO;

import github.washistylee.Model.Connection.ConnectionDB;
import github.washistylee.Model.Entitys.Schedule;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class ScheduleDAO implements DAO<Schedule, String> {

    private final static String INSERT = "INSERT INTO Horario (Actividades, Horas, Dias, Mes, ID, ID_Niños) VALUES (?,?,?,?,?,?)";
    private final static String DELETE = "DELETE FROM Horario  WHERE  ID_Niños = ?";
    private final static String FINDBYCHILD = "SELECT * FROM Horario WHERE ID_Niños = ?";
    private final static String UPDATE = "UPDATE Horario SET Actividades = ?, Horas = ?, Dias = ?, Mes = ? WHERE ID_Niños = ?";


    @Override
    public Schedule save(Schedule schedule) {
        Schedule scheduleaux = schedule;
        try (PreparedStatement pst = ConnectionDB.getConnection().prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
            pst.setString(1, scheduleaux.getActivitiesAsString());
            pst.setTime(2, java.sql.Time.valueOf(schedule.getHour()));
            pst.setDate(3, java.sql.Date.valueOf(schedule.getDay()));
            pst.setString(4, scheduleaux.getMonth().name());
            pst.setInt(5,scheduleaux.getID());
            pst.setInt(6,scheduleaux.getChild().getId());
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return scheduleaux;
    }


    @Override
    public Schedule delete(Schedule entity)  {
        if (entity != null || entity.getChild().getId() < 0) {
            try (PreparedStatement pst = ConnectionDB.getConnection().prepareStatement(DELETE)) {
                pst.setInt(1, entity.getChild().getId());
                pst.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return entity;
    }

    @Override
    public Schedule findByName(String key) {
        return null;
    }


    public Schedule findByNameI(Integer key) {
        Schedule schedule = new Schedule();
        if (schedule != null && schedule.getChild().getId() < 0) {

            try (PreparedStatement pst = ConnectionDB.getConnection().prepareStatement(FINDBYCHILD)) {
                pst.setInt(1, key);
                pst.executeUpdate();

            } catch (SQLException e) {

            }
        }

        return schedule;
    }


    @Override
    public List<Schedule> findAll() {
        return null;
    }

    @Override
    public void close() throws IOException {

    }
}
