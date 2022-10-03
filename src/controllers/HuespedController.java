package controllers;

import dao.HuespedDAO;
import factory.ConnectionFactory;
import modelo.Huesped;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

public class HuespedController {
    private HuespedDAO huespedDAO;
    public HuespedController() {
        this.huespedDAO = new HuespedDAO(new ConnectionFactory().recuperaConexion());
    }

    public void guardar(Huesped huesped) {
        this.huespedDAO.guardar(huesped);
    }

    public boolean validarHuesped(String nombre, String apellido, Date fechaNacimiento,
                                         String nacionalidad, String telefono, int numeroReserva) {
        return nombre != null
                && apellido != null
                && validarFechaNacimiento(fechaNacimiento)
                && nacionalidad != null
                && telefono != null
                && numeroReserva > 0;
    }

    private boolean validarFechaNacimiento(Date fechaNacimiento) {
        boolean esFechaValida = false;
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-MM-yyyy");

        try {
            Date fechaMinima = formatoFecha.parse("01-01-1900");
            Period periodo = Period.between(fechaNacimiento.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), LocalDate.now());
            if (periodo.getYears() > 18 && fechaNacimiento.compareTo(fechaMinima) > 0) {
                esFechaValida = true;
            } else {
                JOptionPane.showMessageDialog(null,
                        "Fecha de nacimiento inválida." +
                                " Debe ser mayor de 18 años de edad y haber nacido después del año 1900.");

            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        return esFechaValida;
    }
}
