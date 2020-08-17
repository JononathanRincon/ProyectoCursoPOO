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
public class Empleados extends Personas {

    public Empleados(String contrasena) {
        this.contrasena = contrasena;
    }

    public Empleados(int Identificacion, String NombreCompleto, String FechaNacimiento, String Telefono, 
            String Direccion, String contrasena ) {
        super(Identificacion, NombreCompleto, FechaNacimiento, Telefono, Direccion);
        this.contrasena = contrasena;
    }

      

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
    private String contrasena;
    
}
