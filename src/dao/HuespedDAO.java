package dao;

import modelo.Huesped;

import java.sql.*;

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
}
