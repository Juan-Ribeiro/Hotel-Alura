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

    public List<Huesped> listarHuespedes(String busqueda) {
        return this.huespedDAO.listarHuespedes(busqueda);
    }

    public List<Huesped> listarHuespedes() {
        return this.huespedDAO.listarHuespedes();
    }
}
