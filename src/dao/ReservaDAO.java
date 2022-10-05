package dao;

import modelo.Reserva;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class ReservaDAO {
    private Connection con;

    public ReservaDAO(Connection con) {
        this.con = con;
    }

    public void guardar(Reserva reserva) {
        try {
            String query = "INSERT INTO hotel_alura.reserva(FechaEntrada, FechaSalida, Valor, FormaPago) VALUES(?, ?, ?, ?)";
            final PreparedStatement statement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            ejecutarRegistro(reserva, statement);

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

    public Connection getCon() {
        return this.con;
    }

    public List<Reserva> listarReservas() {
        ArrayList<Reserva> listaReservas = new ArrayList<>();

        try {
            String query = "SELECT * FROM hotel_alura.reserva";

            final PreparedStatement statement = con.prepareStatement(query);
            try (statement) {
                ejecutarRegistroReserva(listaReservas, statement);
            }
        } catch (SQLException | ParseException e) {
            throw new RuntimeException(e);
        }
        return listaReservas;
    }

    public List<Reserva> listarReservas(int busqueda) {
        ArrayList<Reserva> listaReservas = new ArrayList<>();
        try {
            String query = "SELECT * FROM hotel_alura.reserva WHERE reservaId = ?";

            final PreparedStatement statement = con.prepareStatement(query);
            try (statement) {
                statement.setInt(1, busqueda);
                ejecutarRegistroReserva(listaReservas, statement);
            }
        } catch (SQLException | ParseException e) {
            throw new RuntimeException(e);
        }
        return listaReservas;
    }

    private void ejecutarRegistroReserva(ArrayList<Reserva> listaReservas, PreparedStatement statement) throws SQLException, ParseException {
        statement.execute();

        final ResultSet resultSet = statement.getResultSet();
        try (resultSet) {
            while (resultSet.next()) {
                SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                Date fechaEntrada = formato.parse(resultSet.getString("FechaEntrada"));
                Date fechaSalida = formato.parse(resultSet.getString("FechaSalida"));
                String formaPago = resultSet.getString("FormaPago");
                Reserva reserva = new Reserva(fechaEntrada, fechaSalida, formaPago);
                reserva.setId(resultSet.getInt("ReservaId"));
                listaReservas.add(reserva);
            }
        }
    }
}
