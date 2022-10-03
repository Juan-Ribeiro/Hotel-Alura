package modelo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Huesped {
    private int huespedId;
    private String nombre;
    private String apellido;
    private String fechaNacimiento;
    private String nacionalidad;
    private String telefono;
    private int numeroReserva;

    public Huesped(String nombre, String apellido, Date fechaNacimiento, String nacionalidad, String telefono, int numeroReserva) {
        setNombre(nombre);
        setApellido(apellido);
        setFechaNacimiento(fechaNacimiento);
        setNacionalidad(nacionalidad);
        setTelefono(telefono);
        setNumeroReserva(numeroReserva);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    private void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = new SimpleDateFormat("dd/MM/yyyy").format(fechaNacimiento);
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getNumeroReserva() {
        return numeroReserva;
    }

    public void setNumeroReserva(int numeroReserva) {
        this.numeroReserva = numeroReserva;
    }

    public void setHuespedId(int huespedId) {
        this.huespedId = huespedId;
    }

    public int getHuespedId() {
        return huespedId;
    }
}
