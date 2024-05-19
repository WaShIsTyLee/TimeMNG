package github.washistylee.Model.DAO;

import github.washistylee.Model.Connection.ConnectionDB;
import github.washistylee.Model.Entitys.Child;
import github.washistylee.Model.Entitys.Month;
import github.washistylee.Model.Entitys.Schedule;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ScheduleDAO implements DAO<Schedule, String> {

    private final static String INSERT = "INSERT INTO Horario (Actividades, Horas, Dias, Mes, ID_Niños) VALUES (?,?,?,?,?)";
    private final static String DELETE = "DELETE FROM Horario  WHERE  ID_Niños = ?";
    private final static String FINDBYCHILD = "SELECT ID_Niños, Mes, Horas, Dias, Actividades  FROM Horario WHERE ID_Niños = ?";
    private final static String FINDALLBYCHILD = "SELECT ID_Niños, Mes, Horas, Dias, Actividades, ID  FROM Horario WHERE ID_Niños = ?";

    private final static String FINDBYID = "SELECT ID_Niños, Mes, Horas, Dias, Actividades FROM Horario WHERE ID = ?";
    private final static String UPDATE = "UPDATE Horario SET Actividades = ?, Horas = ?, Dias = ?, Mes = ? WHERE ID = ?";

    /**
     * Saves a schedule in the database.
     *
     * @param schedule The schedule to be saved.
     * @return The saved schedule.
     */
    @Override
    public Schedule save(Schedule schedule) {
        Schedule scheduleaux = schedule;
        try (PreparedStatement pst = ConnectionDB.getConnection().prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
            pst.setString(1, scheduleaux.getActivitiesAsString());
            pst.setString(2, scheduleaux.getHour());
            pst.setString(3, scheduleaux.getDay());
            pst.setString(4, scheduleaux.getMonth().name());
            pst.setInt(5, scheduleaux.getChild().getId());
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return scheduleaux;
    }

    /**
     * Deletes a schedule from the database.
     *
     * @param entity The schedule to be deleted.
     * @return The deleted schedule.
     */
    @Override
    public Schedule delete(Schedule entity) {
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

    @Override
    public List<Schedule> findAll() {
        return null;
    }

    /**
     * Retrieves the schedule associated with a given child from the database.
     *
     * @param key The child whose schedule is to be retrieved.
     * @return The schedule associated with the child, or null if not found.
     */
    public Schedule findAllByChild(Child key) {
        Schedule schedule = null;

        if (key != null && key.getId() > 0) {
            Connection conn = ConnectionDB.getConnection();
            try (
                    PreparedStatement pst = conn.prepareStatement(FINDBYCHILD)) {
                pst.setInt(1, key.getId());
                try (ResultSet res = pst.executeQuery()) {
                    if (res.next()) {
                        schedule = new Schedule();
                        ChildDAO cdao = new ChildDAO();
                        Child child = cdao.findById(key.getId());
                        schedule.setChild(child);
                        String scheduleMonth = res.getString("Mes");
                        Month monthEn = Month.valueOf(scheduleMonth.toUpperCase());
                        schedule.setMonth(monthEn);
                        schedule.setHour(res.getString("Horas"));
                        schedule.setDay(res.getString("Dias"));
                        String activitiesString = res.getString("Actividades");
                        List<String> activitiesList = new ArrayList<>(Arrays.asList(activitiesString.split(",")));
                        schedule.setActivitys(activitiesList);

                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return schedule;
    }

    /**
     * Retrieves all schedules associated with a given child from the database.
     *
     * @param key The child whose schedules are to be retrieved.
     * @return An ArrayList of schedules associated with the child.
     */
    public ArrayList<Schedule> findAllSchedulesByChild(Child key) {
        ArrayList<Schedule> schedules = new ArrayList<>();

        if (key != null && key.getId() > 0) {
            Connection conn = ConnectionDB.getConnection();
            try (
                    PreparedStatement pst = conn.prepareStatement(FINDALLBYCHILD)) {
                pst.setInt(1, key.getId());
                try (ResultSet res = pst.executeQuery()) {
                    while (res.next()) {
                        Schedule schedule = new Schedule();
                        ChildDAO cdao = new ChildDAO();
                        schedule.setChild(cdao.findById(key.getId()));
                        String scheduleMonth = res.getString("Mes");
                        Month monthEn = Month.valueOf(scheduleMonth.toUpperCase());
                        schedule.setMonth(monthEn);
                        schedule.setHour(res.getString("Horas"));
                        schedule.setDay(res.getString("Dias"));
                        String activitiesString = res.getString("Actividades");
                        List<String> activitiesList = new ArrayList<>(Arrays.asList(activitiesString.split(",")));
                        schedule.setActivitys(activitiesList);
                        schedule.setID(res.getInt("ID"));
                        schedules.add(schedule);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return schedules;
    }

    /**
     * Updates a schedule in the database.
     *
     * @param schedule The schedule with updated information.
     * @return The updated schedule.
     */
    public Schedule update(Schedule schedule) {
        ScheduleDAO sdao = new ScheduleDAO();
        Connection conn = ConnectionDB.getConnection();

        try (PreparedStatement pst = conn.prepareStatement(UPDATE)) {
            Schedule lastSchedule = sdao.findAllByChild(schedule.getChild());

            String activitiesToUpdate;
            if (schedule.getActivitys() == null || schedule.getActivitys().isEmpty()) {

                activitiesToUpdate = lastSchedule.getActivitiesAsString();
            } else {
                activitiesToUpdate = schedule.getActivitiesAsString();
            }
            pst.setString(1, activitiesToUpdate);

            String hoursToUpdate;
            if (schedule.getHour() == null || schedule.getHour().isEmpty()) {
                hoursToUpdate = lastSchedule.getHour();
            } else {
                hoursToUpdate = schedule.getHour();
            }
            pst.setString(2, hoursToUpdate);

            String daysToUpdate;
            if (schedule.getDay() == null || schedule.getDay().isEmpty()) {
                daysToUpdate = lastSchedule.getDay();
            } else {
                daysToUpdate = schedule.getDay();
            }
            pst.setString(3, daysToUpdate);

            Month monthtoUpdate;
            if (schedule.getMonth() == null) {
                monthtoUpdate = lastSchedule.getMonth();
            } else {
                monthtoUpdate = schedule.getMonth();
            }
            String month = monthtoUpdate.toString().toUpperCase();
            pst.setString(4, month);

            pst.setInt(5, schedule.getID());

            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return schedule;
    }


    @Override
    public void close() throws IOException {

    }

}
