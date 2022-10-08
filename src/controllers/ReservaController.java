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

    public List<Reserva> listar(int busqueda) {
        return this.reservaDAO.listarReservas(busqueda);
    }

    public List<Reserva> listar() {
        return this.reservaDAO.listarReservas();
    }

    public Integer modificar(Integer id, String fechaEntrada, String fechaSalida, Double valor, String formaPago) {
        return this.reservaDAO.modificar(id, fechaEntrada, fechaSalida, valor, formaPago);
    }

    public Integer eliminar(Integer id) {
        return this.reservaDAO.eliminar(id);
    }
}
