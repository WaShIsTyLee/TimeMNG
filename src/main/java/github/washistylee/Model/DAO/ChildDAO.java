package github.washistylee.Model.DAO;

import github.washistylee.Model.Connection.ConnectionDB;
import github.washistylee.Model.Entitys.Child;
import github.washistylee.Model.Entitys.Sesion;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class ChildDAO implements DAO<Child, String> {
    private final static String INSERT = "INSERT INTO Niños (Nombre, Apellidos, Edad, Clase, Enfermedades, Observacion, ID_Cuidador, ID_Profesor) " +
            "VALUES (?,?,?,?,?,?,?,?)";
    private final static String FINDBYID = "SELECT  n.ID FROM Niños AS n WHERE n.ID = ?";
    private final static String FINDBYNAME = "SELECT n.Nombre, n.Apellidos FROM Niños AS n WHERE n.Nombre=?";
    private final static String DELETE = "DELETE FROM Niños  WHERE ID = ?";
    private final static String UPDATE = "UPDATE Niños SET Nombre=? , Apellidos=? , Clase=? , Observacion=? , Edad=?,  Enfermedades=? WHERE ID=?";

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



    public Child findById(Integer key) {

        Child childaux = new Child();
        if (key != null) {
            try (PreparedStatement pst = ConnectionDB.getConnection().prepareStatement(FINDBYID)) {
                pst.setInt(1, key);
                ResultSet res = pst.executeQuery();
                if (res.next()) {
                    childaux.setId(res.getInt("ID"));


                }
                res.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return childaux;
    }

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

        Child result = new Child();
        if (key == null) return result;

        try (PreparedStatement pst = ConnectionDB.getConnection().prepareStatement(FINDBYNAME)) {
            pst.setString(1, key);
            ResultSet res = pst.executeQuery();
            if (res.next()) {
                result.setName(res.getString("Nombre"));
                result.setSurname(res.getString("Apellidos"));

            }
            res.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public Child update(Child child) {
        Child childaux = child;
        try (PreparedStatement pst = ConnectionDB.getConnection().prepareStatement(UPDATE)) {
            pst.setString(1, child.getName());
            pst.setString(2, child.getSurname());
            pst.setString(3, child.getClassroom());
            pst.setString(4, child.getObservation());
            pst.setInt(5, child.getAge());
            //   pst.setString(6, child.getDiseasesAsString());
            pst.setInt(7, child.getId());
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return childaux;
    }

    @Override
    public List<Child> findAll() {
        return null;
    }

    @Override
    public void close() throws IOException {

    }

    public static ChildDAO build() {
        return new ChildDAO();
    }
}
