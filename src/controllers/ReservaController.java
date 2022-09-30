package controllers;

import dao.ReservaDAO;
import factory.ConnectionFactory;
import modelo.Reserva;

public class ReservaController {
    private ReservaDAO reservaDAO;

    public ReservaController() {
        this.reservaDAO = new ReservaDAO(new ConnectionFactory().recuperaConexion());
    }

    public void guardar(Reserva reserva) {
        this.reservaDAO.guardar(reserva);
    }
}
