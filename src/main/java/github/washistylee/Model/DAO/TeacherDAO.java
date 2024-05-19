package github.washistylee.Model.DAO;

import github.washistylee.Model.Connection.ConnectionDB;
import github.washistylee.Model.Entitys.Child;
import github.washistylee.Model.Entitys.Minder;
import github.washistylee.Model.Entitys.Teacher;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class TeacherDAO implements DAO <Teacher, String> {
    private final static String INSERT = "INSERT INTO Profesor (Nombre, Apellidos, ClaseImpartida, Email, Contraseña) VALUES (?,?,?,?,?)";
    private final static String FINDBYEMAIL = "SELECT p.Nombre, p.Email, p.Apellidos, p.Contraseña, p.ClaseImpartida FROM Profesor AS p WHERE p.Email = ?";
    private final static String VERIFYTEACHERMAILIFEXIST = "SELECT p.Email FROM Profesor AS p WHERE p.Email = ?";
    private final static String VERIFYCREDENTIALS = "SELECT p.Email, p.Contraseña FROM Profesor AS p WHERE p.Email = ?";

    /**
     * Saves a teacher to the database.
     *
     * @param teacher The teacher to be saved.
     * @return The saved teacher object.
     */
    @Override
        public Teacher save(Teacher teacher) {
            Teacher teacheraux = teacher;
            if (teacher != null && teacher.getEmail() != null) {
                teacheraux = findByMail(teacheraux.getEmail());
                try (PreparedStatement pst = ConnectionDB.getConnection().prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
                    pst.setString(1, teacher.getName());
                    pst.setString(2, teacher.getSurname());
                    pst.setString(3, teacher.getSubject());
                    pst.setString(4, teacher.getEmail());
                    pst.setString(5, teacher.getPassword());
                    pst.executeUpdate();

                    if (teacher.getMyChilds() != null) {
                        for (Child c : teacher.getMyChilds()) {
                            ChildDAO.build().save(c);
                        }
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
            return teacheraux;
    }
    /**
     * Finds a teacher by their email in the database.
     *
     * @param key The email of the teacher to find.
     * @return The teacher object if found, otherwise returns null.
     */
    public Teacher findByMail(String key) {

        Teacher teacheraux = new Teacher();
        if (key != null) {
            try (PreparedStatement pst = ConnectionDB.getConnection().prepareStatement(FINDBYEMAIL)) {
                pst.setString(1, key);
                ResultSet res = pst.executeQuery();
                if (res.next()) {
                    teacheraux.setName(res.getString("Nombre"));
                    teacheraux.setEmail(res.getString("Email"));
                    teacheraux.setSurname(res.getString("Apellidos"));
                    teacheraux.setPassword(res.getString("Contraseña"));
                    teacheraux.setSubject(res.getString("ClaseImpartida"));
                }
                res.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return teacheraux;
    }

    /**
     * Verifies if a teacher email already exists in the database.
     *
     * @param key The email to verify.
     * @return The teacher object with the given email if it exists, otherwise returns null.
     */
    public Teacher verifyTeacherEmailIfExist(String key) {
        Teacher teacheraux = null;
        if (key != null) {
            try (PreparedStatement pst = ConnectionDB.getConnection().prepareStatement(VERIFYTEACHERMAILIFEXIST)) {
                pst.setString(1, key);
                try (ResultSet res = pst.executeQuery()) {
                    if (res.next()) {
                        teacheraux = new Teacher();
                        teacheraux.setEmail(res.getString("Email"));
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return teacheraux;
    }

    /**
     * Verifies teacher credentials by checking if the provided email and password match those stored in the database.
     *
     * @param key The email to verify.
     * @return The teacher object with the provided email and password if they match, otherwise returns a teacher object with empty fields.
     */
    public Teacher verifyCredentialDAO(String key) {
        Teacher teacheraux = new Teacher();
        if (key != null) {
            try (PreparedStatement pst = ConnectionDB.getConnection().prepareStatement(VERIFYCREDENTIALS)) {
                pst.setString(1, key);
                ResultSet res = pst.executeQuery();
                if (res.next()) {
                    teacheraux.setEmail(res.getString("Email"));
                    teacheraux.setPassword(res.getString("Contraseña"));
                }
                res.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return teacheraux;
    }



    @Override
    public Teacher delete(Teacher teacher) {
        return teacher;
    }

    @Override
    public Teacher findByName(String key) {
        return null;
    }

    @Override
    public List<Teacher> findAll() {
        return null;
    }

    @Override
    public void close() throws IOException {

    }
    public static TeacherDAO build() {
        return new TeacherDAO();
    }
}
