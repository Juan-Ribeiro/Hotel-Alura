package controllers;

import dao.HuespedDAO;
import factory.ConnectionFactory;
import modelo.Huesped;

import java.util.List;

public class HuespedController {
    private HuespedDAO huespedDAO;
    public HuespedController() {
        this.huespedDAO = new HuespedDAO(new ConnectionFactory().recuperaConexion());
    }

    public void guardar(Huesped huesped) {
        this.huespedDAO.guardar(huesped);
    }

    public List<Huesped> listar(String busqueda) {
        return this.huespedDAO.listarHuespedes(busqueda);
    }

    public List<Huesped> listar() {
        return this.huespedDAO.listarHuespedes();
    }

    public Integer modificar(Integer id, String nombre, String apellido, String fechaNacimiento, String nacionalidad, String telefono, Integer idReserva) {
        return this.huespedDAO.modificar(id, nombre, apellido, fechaNacimiento, nacionalidad, telefono, idReserva);
    }

    public Integer eliminar(Integer id) {
        return this.huespedDAO.eliminar(id);
    }
}
