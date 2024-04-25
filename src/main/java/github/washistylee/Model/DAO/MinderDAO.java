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

public  class MinderDAO implements DAO<Minder, String> {

    private final static String INSERT = "INSERT INTO Cuidador (Nombre, Apellidos, Horas, Email, Contraseña) VALUES (?,?,?,?,?)";
    private final static String FINDBYEMAIL = "SELECT c.Nombre, c.Email, c.Apellidos, c.Contraseña, c.Horas FROM Cuidador AS c WHERE c.Email = ?";
    private final static String DELETE = "DELETE  FROM Cuidador  WHERE Email = ?";
    private final static String VERIFYCREDENTIALS = "SELECT c.Email, c.Contraseña FROM Cuidador AS c WHERE c.Email = ?";

    private final static String UPDATE = "UPDATE Cuidador SET Nombre=? , Apellidos=? , Horas=? , Email=? , Contraseña=? WHERE Email=?";
    @Override
    public Minder save(Minder minder) {
        Minder minderaux = minder;
        if (minder != null && minder.getEmail() != null) {
            minderaux = findByMail(minderaux.getEmail());
            try (PreparedStatement pst = ConnectionDB.getConnection().prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
                pst.setString(1, minder.getName());
                pst.setString(2, minder.getSurname());
                pst.setString(3, minder.getHours());
                pst.setString(4, minder.getEmail());
                pst.setString(5, minder.getPassword());
                pst.executeUpdate();

                if (minder.getMyChilds() != null) {
                    for (Child c : minder.getMyChilds()) {
                        ChildDAO.build().save(c);
                    }
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return minderaux;
    }
    public Minder findByMail(String key) {

        Minder minderaux = new Minder();
        if (key != null) {
            try (PreparedStatement pst = ConnectionDB.getConnection().prepareStatement(FINDBYEMAIL)) {
                pst.setString(1, key);
                ResultSet res = pst.executeQuery();
                if (res.next()) {
                    minderaux.setName(res.getString("Nombre"));
                    minderaux.setEmail(res.getString("Email"));
                    minderaux.setSurname(res.getString("Apellidos"));
                    minderaux.setPassword(res.getString("Contraseña"));
                    minderaux.setHours(res.getString("Horas"));


                }
                res.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return minderaux;
    }

    public Minder update(Minder minder) {
        Minder minderaux = minder;
        try (PreparedStatement pst = ConnectionDB.getConnection().prepareStatement(UPDATE)) {
            pst.setString(1, minder.getName());
            pst.setString(2, minder.getSurname());
            pst.setString(3, minder.getHours());
            pst.setString(4, minder.getEmail());
            if (minder.getPassword() == null) {
                //TOCAR ESTO PARA QUE SE HAGA BUCLE AL METER CONTRASEÑA A NULO
                System.out.println("CABRON NO METAS CONTRASÑEA VACIA");
            } else {
                pst.setString(5, minder.getPassword());
            }
            pst.setString(6,minder.getEmail());
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return minderaux;
    }


    @Override
    public Minder delete(Minder entity) {
        if (entity != null || entity.getEmail() != null) {
            try (PreparedStatement pst = ConnectionDB.getConnection().prepareStatement(DELETE)) {
                pst.setString(1, entity.getEmail());
                pst.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return entity;
    }

    @Override
    public Minder findByName(String key) {
        return null;
    }

    @Override
    public List<Minder> findAll() {
        return null;
    }

    public Minder verifyCredentialDAO(String key) {
        Minder minderaux = new Minder();
        if (key != null) {
            try (PreparedStatement pst = ConnectionDB.getConnection().prepareStatement(VERIFYCREDENTIALS)) {
                pst.setString(1, key);
                ResultSet res = pst.executeQuery();
                if (res.next()) {
                    minderaux.setEmail(res.getString("Email"));
                    minderaux.setPassword(res.getString("Contraseña"));
                }
                res.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return minderaux;
    }


    @Override
    public void close() throws IOException {

    }
}
