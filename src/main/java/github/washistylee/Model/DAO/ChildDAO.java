package github.washistylee.Model.DAO;

import github.washistylee.Model.Connection.ConnectionDB;
import github.washistylee.Model.Entitys.*;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChildDAO implements DAO<Child, String> {
    private final static String INSERT = "INSERT INTO Niños (Nombre, Apellidos, Edad, Clase, Enfermedades, Observacion, ID_Cuidador, ID_Profesor) " +
            "VALUES (?,?,?,?,?,?,?,?)";
    private final static String FINDBYID = "SELECT n.ID, n.Nombre, n.Apellidos, n.Edad, n.Clase, n.Enfermedades, n.Observacion, n.ID_Cuidador, n.ID_Profesor FROM Niños AS n WHERE n.ID = ?";
    private final static String FINDALLBYTEACHER = "SELECT n.Clase, n.Nombre, n.ID, n.Apellidos, n.ID_Cuidador, n.Observacion, n.Enfermedades, n.Edad FROM Niños AS n WHERE ID_Profesor = ?";
    private final static String FINDALLBYMINDER = "SELECT n.Clase, n.Nombre, n.ID, n.Apellidos, n.ID_Profesor, n.Observacion, n.Enfermedades, n.Edad FROM Niños AS n WHERE ID_Cuidador = ?";
    private final static String DELETE = "DELETE FROM Niños  WHERE ID = ?";
    private final static String UPDATE = "UPDATE Niños SET Nombre=? , Apellidos=? , Edad = ? , Clase=? , Observacion=? , ID_Profesor=?,  Enfermedades=? WHERE ID=?";

    TeacherDAO tdao = new TeacherDAO();
    MinderDAO mdao = new MinderDAO();

    /**
     * Saves the child to the database.
     * @param child The child object to be saved.
     * @return The saved child object.
     */
    @Override
    public Child save(Child child) {
        Child childaux = child;

        try (PreparedStatement pst = ConnectionDB.getConnection().prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
            pst.setString(1, child.getName());
            pst.setString(2, child.getSurname());
            pst.setFloat(3, child.getAge());
            pst.setString(4, child.getClassroom());
            pst.setString(5, child.getDiseasesString());
            pst.setString(6, child.getObservation());
            pst.setString(7, child.getMinder().getEmail());
            pst.setString(8, child.getTeacher().getEmail());


            pst.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return childaux;
    }

    /**
     * Finds a child by their ID.
     * @param key The ID of the child to find.
     * @return The child object if found, otherwise null.
     */
    public Child findById(Integer key) {
        Child childaux = null;
        TeacherDAO tdao = new TeacherDAO();
        MinderDAO mdao = new MinderDAO();
        if (key != null) {
            Connection conn = ConnectionDB.getConnection();
            try (PreparedStatement pst = conn.prepareStatement(FINDBYID)) {
                pst.setInt(1, key);
                ResultSet res = pst.executeQuery();
                if (res.next()) {
                    childaux = new Child();
                    childaux.setId(res.getInt("ID"));
                    childaux.setName(res.getString("Nombre"));
                    childaux.setSurname(res.getString("Apellidos"));
                    childaux.setObservation(res.getString("Observacion"));
                    String diseasesString = res.getString("Enfermedades");
                    String[] diseasesArray = diseasesString.split(",");
                    List<String> diseasesList = Arrays.asList(diseasesArray);
                    childaux.setDiseases(diseasesList);
                    childaux.setAge(res.getInt("Edad"));
                    childaux.setClassroom(res.getString("Clase"));
                    childaux.setTeacher(tdao.findByMail(res.getString("ID_Profesor")));
                    childaux.setMinder(mdao.findByMail(res.getString("ID_Cuidador")));
                }
                res.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return childaux;
    }

    /**
     * Deletes a child from the database.
     * @param child The child object to delete.
     * @return The child object if deletion is successful, otherwise null.
     */
    @Override
    public Child delete(Child child) {
        if (child != null || child.getId() > 0) {
            try (PreparedStatement pst = ConnectionDB.getConnection().prepareStatement(DELETE)) {
                pst.setInt(1, child.getId());
                pst.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return child;
    }

    @Override
    public Child findByName(String key) {
        return null;
    }

    @Override
    public List<Child> findAll() {
        return null;
    }

    /**
     * Updates the information of a child in the database.
     * @param child The child object with updated information.
     * @return The updated child object.
     */
    public Child update(Child child) {
        ChildDAO cdao = new ChildDAO();
        try (PreparedStatement pst = ConnectionDB.getConnection().prepareStatement(UPDATE)) {
            Child lastChild = cdao.findById(child.getId());

            String nameToUpdate;
            if (child.getName() == null || child.getName().isEmpty()) {
                nameToUpdate = lastChild.getName();
            } else {
                nameToUpdate = child.getName();
            }
            pst.setString(1, nameToUpdate);

            String surnameToUpdate;
            if (child.getSurname() == null || child.getSurname().isEmpty()) {
                surnameToUpdate = lastChild.getSurname();
            } else {
                surnameToUpdate = child.getSurname();
            }
            pst.setString(2, surnameToUpdate);

            int ageToUpdate;
            if (child.getAge() <= 0) {
                ageToUpdate = lastChild.getAge();
            } else {
                ageToUpdate = child.getAge();
            }
            pst.setInt(3, ageToUpdate);

            String classroomToUpdate;
            if (child.getClassroom() == null || child.getClassroom().isEmpty()) {
                classroomToUpdate = lastChild.getClassroom();
            } else {
                classroomToUpdate = child.getClassroom();
            }
            pst.setString(4, classroomToUpdate);

            String observationToUpdate;
            if (child.getObservation() == null || child.getObservation().isEmpty()) {
                observationToUpdate = lastChild.getObservation();
            } else {
                observationToUpdate = child.getObservation();
            }
            pst.setString(5, observationToUpdate);

            String teacherEmailToUpdate;
            if (child.getTeacher().getEmail() == null || child.getTeacher().getEmail().isEmpty()) {
                teacherEmailToUpdate = lastChild.getTeacher().getEmail();
            } else {
                teacherEmailToUpdate = child.getTeacher().getEmail();
            }
            pst.setString(6, teacherEmailToUpdate);

            String diseasesStringToUpdate;
            if (child.getDiseases() == null || child.getDiseasesString().isEmpty()) {
                diseasesStringToUpdate = lastChild.getDiseasesString();
            } else {
                diseasesStringToUpdate = child.getDiseasesString();
            }
            pst.setString(7, diseasesStringToUpdate);

            pst.setInt(8, child.getId());

            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return child;
    }
    /**
     * Finds all children associated with a teacher's email address.
     * @param key The email address of the teacher.
     * @return An ArrayList containing all children associated with the specified teacher.
     */

    public ArrayList<Child> findChildByTeacherMail(String key) {
        Child childaux;
        MinderDAO mdao = new MinderDAO();
        ArrayList <Child> teacherChilds = new ArrayList<>();
        if (key != null) {
            try (PreparedStatement pst = ConnectionDB.getConnection().prepareStatement(FINDALLBYTEACHER)) {
                pst.setString(1, key);
                ResultSet res = pst.executeQuery();
                while (res.next()) {
                    childaux = new Child();
                    childaux.setId(res.getInt("ID"));
                    childaux.setSurname(res.getString("Apellidos"));
                    childaux.setName(res.getString("Nombre"));
                    childaux.setMinder(mdao.findByMail(res.getString("ID_Cuidador")));
                    childaux.setObservation(res.getString("Observacion"));
                    childaux.setTeacher((Teacher)Sesion.getInstancia().getUsuarioIniciado());
                    childaux.setClassroom(res.getString("Clase"));
                    String diseasesString = res.getString("Enfermedades");
                    String[] diseasesArray = diseasesString.split(",");
                    List<String> diseasesList = Arrays.asList(diseasesArray);
                    childaux.setDiseases(diseasesList);
                    childaux.setAge(res.getInt("Edad"));
                    teacherChilds.add(childaux);
                }
                res.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return teacherChilds;
    }

    /**
     * Finds all children associated with a minder's email address.
     * @param key The email address of the minder.
     * @return An ArrayList containing all children associated with the specified minder.
     */
    public ArrayList<Child> findChildByMinderMail(String key) {
        Child childaux;
        ScheduleDAO sdao = new ScheduleDAO();
        TeacherDAO tdao = new TeacherDAO();
        ArrayList <Child> minderChilds = new ArrayList<>();
        if (key != null) {
            Connection conn =ConnectionDB.getConnection();
            try (PreparedStatement pst = conn.prepareStatement(FINDALLBYMINDER)) {
                pst.setString(1, key);
                ResultSet res = pst.executeQuery();
                while (res.next()) {
                    childaux = new Child();
                    childaux.setId(res.getInt("ID"));
                    childaux.setSurname(res.getString("Apellidos"));
                    childaux.setName(res.getString("Nombre"));
                    childaux.setTeacher(tdao.findByMail(res.getString("ID_Profesor")));
                    childaux.setObservation(res.getString("Observacion"));
                    childaux.setClassroom(res.getString("Clase"));
                    childaux.setMinder((Minder) Sesion.getInstancia().getUsuarioIniciado());
                    String diseasesString = res.getString("Enfermedades");
                    String[] diseasesArray = diseasesString.split(",");
                    List<String> diseasesList = Arrays.asList(diseasesArray);
                    childaux.setDiseases(diseasesList);
                    childaux.setAge(res.getInt("Edad"));
                    childaux.setSchedule(sdao.findAllSchedulesByChild(childaux));
                    minderChilds.add(childaux);
                }
                res.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return minderChilds;
    }

    @Override
    public void close() throws IOException {

    }

    public static ChildDAO build() {
        return new ChildDAO();
    }

}
