/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_a.clasesP;

import java.sql.Date;
import java.text.DateFormat;
/**
 *
 * @author jonat
 */
public class envios extends usuarios{
    private Integer CodigoEnvio;
    private String CiudadOrigen;
    private String CiudadDestino;
    private String detallesEnvio;
    private String fechayHora;

    public envios(Integer CodigoEnvio, String CiudadOrigen, String CiudadDestino, String fechayHora, String detallesEnvio, Integer Identificacion) {
        super(Identificacion);
        this.CodigoEnvio = CodigoEnvio;
        this.CiudadOrigen = CiudadOrigen;
        this.CiudadDestino = CiudadDestino;
        this.detallesEnvio = detallesEnvio;
        this.fechayHora = fechayHora;
    }

    public Integer getCodigoEnvio() {
        return CodigoEnvio;
    }

    public void setCodigoEnvio(Integer CodigoEnvio) {
        this.CodigoEnvio = CodigoEnvio;
    }

    public String getCiudadOrigen() {
        return CiudadOrigen;
    }

    public void setCiudadOrigen(String CiudadOrigen) {
        this.CiudadOrigen = CiudadOrigen;
    }

    public String getCiudadDestino() {
        return CiudadDestino;
    }

    public void setCiudadDestino(String CiudadDestino) {
        this.CiudadDestino = CiudadDestino;
    }

    public String getDetallesEnvio() {
        return detallesEnvio;
    }

    public void setDetallesEnvio(String detallesEnvio) {
        this.detallesEnvio = detallesEnvio;
    }

    public String getFechayHora() {
        return fechayHora;
    }

    public void setFechayHora(String fechayHora) {
        this.fechayHora = fechayHora;
    }
    
    
    
    
}
