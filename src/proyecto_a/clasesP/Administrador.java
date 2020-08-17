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
public class Administrador extends Personas{
    private String contrasena;
    private String Ciudad;

    public String getCiudad() {
        return Ciudad;
    }

    public void setCiudad(String Ciudad) {
        this.Ciudad = Ciudad;
    }
    public Administrador(){
        
    }
    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Administrador(String contrasena) {
        this.contrasena = contrasena;
    }

    public Administrador(String NombreCompleto, Integer Identificacion, String FechaNacimiento, String contrasena, String Telefono, String Ciudad) {
        super(Identificacion, NombreCompleto, FechaNacimiento, Telefono);
        this.contrasena = contrasena;
        this.Ciudad = Ciudad;
    }
    
}
