/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_a.clasesP;

import java.sql.Date;
/**
 *
 * @author jonat
 */
public class Personas {

    
    public Personas() {
    }
    private Integer Identificacion;
    private String NombreCompleto;
    private String FechaNacimiento;
    private String Telefono;
    private String Direccion;

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }
    
    public Personas(Integer Identificacion){  
        this.Identificacion = Identificacion;
    }
    
    public Personas(Integer Identificacion, String NombreCompleto, String FechaNacimiento, String Telefono) {
        this.Identificacion = Identificacion;
        this.NombreCompleto = NombreCompleto;
        this.FechaNacimiento = FechaNacimiento;
        this.Telefono = Telefono;
    }
    
    public Personas(Integer Identificacion, String NombreCompleto, String FechaNacimiento, String Telefono, String Direccion) {
        this.Identificacion = Identificacion;
        this.NombreCompleto = NombreCompleto;
        this.FechaNacimiento = FechaNacimiento;
        this.Telefono = Telefono;
        this.Direccion = Direccion;
    }
   

    public Integer getIdentificacion() {
        return Identificacion;
    }

    public void setIdentificacion(Integer Identificacion) {
        this.Identificacion = Identificacion;
    }

    public String getNombreCompleto() {
        return NombreCompleto;
    }

    public void setNombreCompleto(String NombreCompleto) {
        this.NombreCompleto = NombreCompleto;
    }

    public String getFechaNacimiento() {
        return FechaNacimiento;
    }

    public void setFechaNacimiento(String FechaNacimiento) {
        this.FechaNacimiento = FechaNacimiento;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }
}
