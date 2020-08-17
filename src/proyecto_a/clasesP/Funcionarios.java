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
public class Funcionarios extends Personas{
    private String contrasena;
    private String Rol;

    public Funcionarios(String contrasena, String Rol) {
        this.contrasena = contrasena;
        this.Rol = Rol;
    }

        public Funcionarios(Integer Identificacion, String NombreCompleto, String FechaNacimiento, String Telefono, String contrasena, String Rol ) {
        super(Identificacion, NombreCompleto, FechaNacimiento, Telefono);
        this.contrasena = contrasena;
        this.Rol = Rol;
    }

    public String getRol() {
        return Rol;
    }

    public void setRol(String Rol) {
        this.Rol = Rol;
    }

    public Funcionarios(String contrasena) {
        this.contrasena = contrasena;
    }

    
    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
    
}
