/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_a.clasesP;

/**
 *
 * @author jonat
 */
public class sedes extends Departamento{
    private Integer idSedes;
    private String nombreCiudad;
    private String direccion;
    private String telefono;

    public sedes(Integer idSedes, String nombreCiudad, String direccion, String telefono, int CodigoDepartamento) {
        super(CodigoDepartamento);
        this.idSedes = idSedes;
        this.nombreCiudad = nombreCiudad;
        this.direccion = direccion;
        this.telefono = telefono;        
    }

    public Integer getIdSedes() {
        return idSedes;
    }

    public void setIdSedes(Integer idSedes) {
        this.idSedes = idSedes;
    }

    public String getNombreCiudad() {
        return nombreCiudad;
    }

    public void setNombreCiudad(String nombreCiudad) {
        this.nombreCiudad = nombreCiudad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
