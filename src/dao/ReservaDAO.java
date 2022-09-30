package dao;

import modelo.Reserva;

import java.sql.*;

public class ReservaDAO {
    private Connection con;

    public ReservaDAO(Connection con) {
        this.con = con;
    }

    public void guardar(Reserva reserva) {
        try {
            String query = "INSERT INTO hotel_alura.reserva(FechaEntrada, FechaSalida, Valor, FormaPago) VALUES(?, ?, ?, ?)";
            final PreparedStatement statement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            try (statement) {
                ejecutarRegistro(reserva, statement);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void ejecutarRegistro(Reserva reserva, PreparedStatement statement) throws SQLException {
        statement.setString(1, reserva.getFechaEntrada());
        statement.setString(2, reserva.getFechaSalida());
        statement.setDouble(3, reserva.getValor());
        statement.setString(4, reserva.getFormaPago());

        statement.execute();

        final ResultSet resultSet = statement.getGeneratedKeys();
        try (resultSet) {
            while (resultSet.next()) {
                reserva.setId(resultSet.getInt(1));
            }
        }
    }
}
