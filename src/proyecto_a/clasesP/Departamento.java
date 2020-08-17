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
public class Departamento extends Empresa {
        
    public int CodigoDepartamento;
    private String NombreDepartamento;
    
    public Departamento(int codigoDepartamento) {
        this.CodigoDepartamento = codigoDepartamento;
    }
    
    public Departamento(int CodigoDepartamento, String NombreDepartamento, double nit) {
        this.CodigoDepartamento = CodigoDepartamento;
        this.NombreDepartamento = NombreDepartamento;
        this.Nit = nit;
    }

    
    
    public int getCodigoDepartamento() {
        return CodigoDepartamento;
    }

    public void setCodigoDepartamento(int CodigoDepartamento) {
        this.CodigoDepartamento = CodigoDepartamento;
    }

    public String getNombreDepartamento() {
        return NombreDepartamento;
    }

    public void setNombreDepartamento(String NombreDepartamento) {
        this.NombreDepartamento = NombreDepartamento;
    }

    public Double getNit() {
        return Nit;
    }

    public void setNit(Double Nit) {
        this.Nit = Nit;
    }
    
    
}
