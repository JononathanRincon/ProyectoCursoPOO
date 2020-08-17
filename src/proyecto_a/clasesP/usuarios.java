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
public class usuarios extends Personas{
    private String direccion;
    private String ciudad;

    public usuarios(String direccion, String ciudad) {
        this.direccion = direccion;
        this.ciudad = ciudad;
    }
    
    public usuarios(Integer Identificacion) {
        super(Identificacion);
        
    }
    
    public usuarios(Integer Identificacion, String NombreCompleto, String FechaNacimiento, 
            String Telefono, String direccion, String ciudad ) {
        super(Identificacion, NombreCompleto, FechaNacimiento, Telefono);
        this.direccion = direccion;
        this.ciudad = ciudad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
    
}
