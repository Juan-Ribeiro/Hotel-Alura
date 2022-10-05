package controllers;

import dao.ReservaDAO;
import factory.ConnectionFactory;
import modelo.Reserva;

import java.util.List;

public class ReservaController {
    private ReservaDAO reservaDAO;

    public ReservaController() {
        this.reservaDAO = new ReservaDAO(new ConnectionFactory().recuperaConexion());
    }

    public void guardar(Reserva reserva) {
        this.reservaDAO.guardar(reserva);
    }

    public List<Reserva> listarReservas(int busqueda) {
        return this.reservaDAO.listarReservas(busqueda);
    }

    public List<Reserva> listarReservas() {
        return this.reservaDAO.listarReservas();
    }
}
