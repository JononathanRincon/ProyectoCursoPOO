/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_a.BasesDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

/**
 *
 * @author jonat
 */
public class BasesDatos {
    private final String URL = "jdbc:mysql://localhost/soluciones_integradas?useTimezone=true&serverTimezone=UTC";
	private final String USUARIO = "root";
	private final String PASSWORD = "12345678";
	
	
	public Connection conexion = null;
	
	public Connection conectar() throws SQLException{
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
		conexion = DriverManager.getConnection(URL,USUARIO, PASSWORD );
               // JOptionPane.showMessageDialog(null, "conexion ace");			
            } catch (Exception e) {
		e.printStackTrace();
		JOptionPane.showMessageDialog(null, e);			
            }		
            return conexion;		
	}
	
	
	public void CerrrConexion() {
            try {
                conexion.close();
            } catch (Exception e) {
		System.out.println("error en el cierre de la conexion ");
            }
	}
	
	/*if(conexion == null) {
		JOptionPane.showMessageDialog(null, "la conexion se ejecuto exitosamente");				
	}*/
}
