package dao;

import modelo.Huesped;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HuespedDAO {
    private Connection con;

    public HuespedDAO(Connection con) {
        this.con = con;
    }

    public void guardar(Huesped huesped) {
        try {
            String query = "INSERT INTO " +
                    "hotel_alura.huesped(Nombre, Apellido, FechaNacimiento, Nacionalidad, Telefono, reservaId) " +
                    "VALUES(?, ?, ?, ?, ?, ?)";
            final PreparedStatement statement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            try (statement) {
                ejecutarRegistro(huesped, statement);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void ejecutarRegistro(Huesped huesped, PreparedStatement statement) throws SQLException {
        statement.setString(1, huesped.getNombre());
        statement.setString(2, huesped.getApellido());
        statement.setString(3, huesped.getFechaNacimiento());
        statement.setString(4, huesped.getNacionalidad());
        statement.setString(5, huesped.getTelefono());
        statement.setInt(6, huesped.getNumeroReserva());

        statement.execute();

        final ResultSet resultSet = statement.getGeneratedKeys();
        try (resultSet) {
            while (resultSet.next()) {
                huesped.setHuespedId(resultSet.getInt(1));
            }
        }
    }

    public List<Huesped> listarHuespedes() {
        ArrayList<Huesped> listaHuespedes = new ArrayList<>();
        try {
            String query = "SELECT * FROM hotel_alura.huesped";

            final PreparedStatement statement = con.prepareStatement(query);
            try (statement) {
                ejecutarRegistroHuesped(listaHuespedes, statement);
            }

            return listaHuespedes;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaHuespedes;
    }

    private void ejecutarRegistroHuesped(ArrayList<Huesped> listaHuespedes, PreparedStatement statement) throws SQLException {
        statement.execute();

        final ResultSet resultSet = statement.getResultSet();
        try (resultSet) {
            while (resultSet.next()) {
                Huesped huesped = new Huesped(resultSet.getInt("huespedId"), resultSet.getString("Nombre"),
                        resultSet.getString("Apellido"),
                        resultSet.getString("FechaNacimiento"),
                        resultSet.getString("Nacionalidad"),
                        resultSet.getString("Telefono"),
                        resultSet.getInt("reservaId"));

                listaHuespedes.add(huesped);
            }
        }
    }

    public List<Huesped> listarHuespedes(String busqueda) {
        ArrayList<Huesped> listaHuespedes = new ArrayList<>();
        try {
            String query = "SELECT * FROM hotel_alura.huesped WHERE Apellido=(?)";

            final PreparedStatement statement = con.prepareStatement(query);
            try (statement) {
                statement.setString(1, busqueda);
                ejecutarRegistroHuesped(listaHuespedes, statement);
            }

            return listaHuespedes;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaHuespedes;
    }

    public Integer modificar(Integer id, String nombre, String apellido, String fechaNacimiento, String nacionalidad, String telefono, Integer idReserva) {
        try {
            String query = "UPDATE hotel_alura.huesped SET Nombre = ?, Apellido = ?, FechaNacimiento = ?," +
                    " Nacionalidad = ?, Telefono = ?, reservaId = ? WHERE huespedId = ?";

            final PreparedStatement statement = con.prepareStatement(query);
            try (statement) {
                statement.setString(1, nombre);
                statement.setString(2, apellido);
                statement.setString(3, fechaNacimiento);
                statement.setString(4, nacionalidad);
                statement.setString(5, telefono);
                statement.setInt(6, id);
                statement.setInt(7, id);

                statement.execute();

                return statement.getUpdateCount();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Integer eliminar(Integer id) {try {
        String query = "DELETE FROM hotel_alura.huesped WHERE huespedId = ?";

        final PreparedStatement statement = this.con.prepareStatement(query);
        try (statement) {
            statement.setInt(1, id);
            statement.execute();

            return statement.getUpdateCount();
        }
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
    }
}
