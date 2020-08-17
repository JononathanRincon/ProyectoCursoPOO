package proyecto_a.BasesDatos;

import com.mysql.cj.xdevapi.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import proyecto_a.Interfaz.Principal;
import proyecto_a.clasesP.Administrador;
import proyecto_a.clasesP.Empleados;
import proyecto_a.clasesP.Funcionarios;
import proyecto_a.clasesP.envios;
import proyecto_a.clasesP.usuarios;

/**
 * @author jonathan Rincon
 */
public class FuncionesSQL {
       
    Connection con = null; 
    BasesDatos conBD = new BasesDatos();
    
    public void UpdateEmpleado (Empleados emple) throws SQLException{
        
        Connection con = null;
        con = conBD.conectar();
        try {
            PreparedStatement Ppt = con.prepareStatement("UPDATE empleados SET "
                    + "nombre_completo = ?, fecha_nacimiento = ?, telefono = ?, direccion = ?, contrasena = ?" +
                    " WHERE identificacion = ?");
            
            
            Ppt.setString(1, emple.getNombreCompleto());
            Ppt.setString(2, emple.getFechaNacimiento());
            Ppt.setString(3, emple.getTelefono());
            Ppt.setString(4, emple.getDireccion());
            Ppt.setString(5, emple.getContrasena());
            Ppt.setInt(6, emple.getIdentificacion());
                        
            Ppt.executeUpdate();            
            Ppt.close();
            
            JOptionPane.showMessageDialog(null,"Datos actualizados ");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"error en la actualizacion: "+ e);
        }
    }
    
    public void UpdateFuncionarios (Funcionarios funcionario) throws SQLException{
        try {
            con = conBD.conectar();
            PreparedStatement Prepared = con.prepareStatement("UPDATE funcionarios SET "
                + "nombre_completo = ?, fecha_nacimiento = ?, telefono = ?, contrasena = ?, rol = ?"+
                " WHERE identificacion = ?");
            
            Prepared.setString(1, funcionario.getNombreCompleto());
            Prepared.setString(2, funcionario.getFechaNacimiento());
            Prepared.setString(3, funcionario.getTelefono());
            Prepared.setString(4, funcionario.getContrasena());
            Prepared.setString(5, funcionario.getRol());
            Prepared.setInt(6, funcionario.getIdentificacion());
        
            Prepared.executeUpdate();
            JOptionPane.showMessageDialog(null, "Se ha Actualizado exitosamente");
            Prepared.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"error en la actualizacion del funcionario"+ e);
        }
    }
    public void UpdateAdminitrador (Administrador admin) throws SQLException{
        con = conBD.conectar();
        try {
            PreparedStatement Ppt = con.prepareStatement("UPDATE administrador SET nombre_completo = ?, "
                    + "fecha_nacimiento = ?, contrasena = ?, telefono = ?, ciudad = ?" +
                    " WHERE cedula = ?");
            
            Ppt.setString(1, admin.getNombreCompleto());            
            Ppt.setString(2, admin.getFechaNacimiento());
            Ppt.setString(3, admin.getContrasena());
            Ppt.setString(4, admin.getTelefono());
            Ppt.setString(5, admin.getCiudad());
            Ppt.setInt(6, admin.getIdentificacion());            
            Ppt.executeUpdate();
            
            
            JOptionPane.showMessageDialog(null,"Datos Actualizados");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"error en la actualizacion: "+ e);
        }
    }
    
    public void UpdateCliente (usuarios usuario) throws SQLException{
        try {
            con = conBD.conectar();
            PreparedStatement ppt = con.prepareStatement("UPDATE usuarios SET "
               +"nombre_completo = ?, fecha_nacimiento = ?, telefono = ?, direccion = ?, ciudad = ? "
               +"WHERE identificacion = ?;");
            
            ppt.setString(1, usuario.getNombreCompleto());
            ppt.setString(2, usuario.getFechaNacimiento());
            ppt.setString(3, usuario.getTelefono());
            ppt.setString(4, usuario.getDireccion());
            ppt.setString(5, usuario.getCiudad());
            ppt.setInt(6, usuario.getIdentificacion());
            ppt.executeUpdate();
            JOptionPane.showMessageDialog(null,"Datos Actualizados");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"error en la actualizacion: "+ e);
        }
    }
    public void insercionFuncionarios (Funcionarios funcionario) throws SQLException{
        try {
            con = conBD.conectar();
            PreparedStatement Prepared = con.prepareStatement("INSERT INTO funcionarios "
                + "(identificacion, nombre_completo, fecha_nacimiento, telefono, contrasena, rol)"+
                "Values (?,?,?,?,?,?)");
            Prepared.setInt(1, funcionario.getIdentificacion());
            Prepared.setString(2, funcionario.getNombreCompleto());
            Prepared.setString(3, funcionario.getFechaNacimiento());
            Prepared.setString(4, funcionario.getTelefono());
            Prepared.setString(5, funcionario.getContrasena());
            Prepared.setString(6, funcionario.getRol());
        
            Prepared.executeUpdate();
            JOptionPane.showMessageDialog(null, "Se ha guardado exitosamente");
            Prepared.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"error a"+ e);
        }
    }
           
    public void InsercionUsiario(usuarios usuario) throws SQLException{
        //Administrador adminIns = new Administrador();
        
        con = conBD.conectar();
        try {
            PreparedStatement Ppt = con.prepareStatement("INSERT INTO usuarios (identificacion, "
                    + "nombre_completo, fecha_nacimiento, telefono, direccion, ciudad)" +
                    "VALUES (?,?,?,?,?,?)");
            
            Ppt.setInt(1, usuario.getIdentificacion());
            Ppt.setString(2, usuario.getNombreCompleto());
            Ppt.setString(3, usuario.getFechaNacimiento());
            Ppt.setString(4, usuario.getTelefono());
            Ppt.setString(5, usuario.getDireccion());
            Ppt.setString(6, usuario.getCiudad());
                        
            Ppt.executeUpdate();            
            Ppt.close();
            
            JOptionPane.showMessageDialog(null,"Datos Guardados");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"error e"+ e);
        }
    }
    
    public void InsercionEmple(Empleados emple) throws SQLException{
        
        Connection con = null;
        con = conBD.conectar();
        try {
            PreparedStatement Ppt = con.prepareStatement("INSERT INTO empleados (identificacion, "
                    + "nombre_completo, fecha_nacimiento, telefono, direccion, contrasena)" +
                    "VALUES (?,?,?,?,?,?)");
            
            Ppt.setInt(1, emple.getIdentificacion());
            Ppt.setString(2, emple.getNombreCompleto());
            Ppt.setString(3, emple.getFechaNacimiento());
            Ppt.setString(4, emple.getTelefono());
            Ppt.setString(5, emple.getDireccion());
            Ppt.setString(6, emple.getContrasena());
                        
            Ppt.executeUpdate();            
            Ppt.close();
            
            JOptionPane.showMessageDialog(null,"Datos Guardados");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"error w"+ e);
        }
    }
        
    public void InsercionAdmin(Administrador admin) throws SQLException{
        //Administrador adminIns = new Administrador();
        Connection con = null;
        con = conBD.conectar();
        try {
            PreparedStatement Ppt = con.prepareStatement("INSERT INTO administrador (nombre_completo, "
                    + "cedula, fecha_nacimiento, contrasena, telefono, ciudad)" +
                    "VALUES (?,?,?,?,?,?)");
            
            Ppt.setString(1, admin.getNombreCompleto());
            Ppt.setInt(2, admin.getIdentificacion());
            Ppt.setString(3, admin.getFechaNacimiento());
            Ppt.setString(4, admin.getContrasena());
            Ppt.setString(5, admin.getTelefono());
            Ppt.setString(6, admin.getCiudad());
                        
            Ppt.executeUpdate();
            
            
            JOptionPane.showMessageDialog(null,"Datos Guardados");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"error: "+ e);
        }      
    }
    public void registrarEnvios(envios envio) throws SQLException{
        //Administrador adminIns = new Administrador();
        Connection con = null;
        con = conBD.conectar();
        try {
            PreparedStatement Ppt = con.prepareStatement("INSERT INTO envios (idenvios, "
                    + "ciudad_origen, ciudad_destino, fecha_hora_envio, detalles_envio, identificacion)" +
                    "VALUES (?,?,?,?,?,?)");
            
            Ppt.setInt(1, envio.getCodigoEnvio());
            Ppt.setString(2, envio.getCiudadOrigen());
            Ppt.setString(3, envio.getCiudadDestino());
            Ppt.setString(4, envio.getFechayHora());
            Ppt.setString(5, envio.getDetallesEnvio());
            Ppt.setInt(6, envio.getIdentificacion());
                        
            Ppt.executeUpdate();
            
            
            JOptionPane.showMessageDialog(null,"Datos Guardados");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"error: "+ e);
        }      
    }
    
    
    public ArrayList<Administrador> ConsultarAdmin() throws SQLException{
        ArrayList<Administrador> ConsAdm = new ArrayList<Administrador>();
        con = conBD.conectar();
        PreparedStatement ps = con.prepareStatement("SELECT * FROM administrador");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {            
            Administrador a = new Administrador(rs.getString("nombre_completo"), rs.getInt("Cedula"), rs.getString("fecha_nacimiento"),
                    rs.getString("contrasena"), rs.getString("telefono"), rs.getString("ciudad"));
            ConsAdm.add(a);
        }
        rs.close();
        return ConsAdm;
    }
    
    public ArrayList<Empleados> ConsultarEmpleados(Integer Identificacion) throws SQLException{
        ArrayList<Empleados> ConsEmp = new ArrayList<Empleados>();
        con = conBD.conectar();
        PreparedStatement ps = null;
        if (Identificacion.equals(0)) {
            ps = con.prepareStatement("SELECT * FROM empleados");
        }
        else{
            ps = con.prepareStatement("SELECT * FROM empleados WHERE identificacion = '"+ Identificacion+"';");
        }
        
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {            
            Empleados a = new Empleados(rs.getInt("identificacion"), rs.getString("nombre_completo"), 
                    rs.getString("fecha_nacimiento"), rs.getString("telefono"), rs.getString("direccion"),
                    rs.getString("contrasena"));
            ConsEmp.add(a);
        }
        rs.close();
        return ConsEmp;
    }

    public ArrayList<Funcionarios> ConsultarFuncionarios(Integer Identificacion) throws SQLException{
        ArrayList<Funcionarios> Consfunc = new ArrayList<Funcionarios>();
        con = conBD.conectar();
        PreparedStatement ps = null;
        if (Identificacion.equals(0)) {
            ps = con.prepareStatement("SELECT * FROM funcionarios");
        }else{
            ps = con.prepareStatement("SELECT * FROM funcionarios WHERE identificacion = '"+Identificacion+"';");
        }
        
        
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {            
            Funcionarios a = new Funcionarios(rs.getInt("identificacion"), rs.getString("nombre_completo"), 
                    rs.getString("fecha_nacimiento"), rs.getString("telefono"), rs.getString("contrasena"),
                    rs.getString("rol"));
            Consfunc.add(a);
        }
        rs.close();
        return Consfunc;
    }
    
    public ArrayList<usuarios> ConsultarClientes(String Cliente) throws SQLException {
        ArrayList<usuarios> consulClientes = new ArrayList<usuarios>();
        con = conBD.conectar();
        PreparedStatement ps = null;
        if (Cliente.equals("")) {
            ps = con.prepareStatement("Select * from usuarios");
        }
        else{
            ps = con.prepareStatement("Select * from usuarios WHERE ciudad = '"+ Cliente+"';");
        }        
        ResultSet rs = ps.executeQuery();        
        while (rs.next()) {            
            usuarios a = new usuarios(rs.getInt("identificacion"), rs.getString("nombre_completo"), rs.getString("fecha_nacimiento"), 
                                        rs.getString("telefono"), rs.getString("direccion"), rs.getString("ciudad"));
            consulClientes.add(a);
        }
        rs.close();
        return  consulClientes;
                
    }
    public ArrayList<usuarios> ConsultarClientesIdentificacion(Integer Identificacion) throws SQLException {
        ArrayList<usuarios> consulClientes = new ArrayList<usuarios>();
        con = conBD.conectar();
        PreparedStatement ps = null;
        ps = con.prepareStatement("Select * from usuarios WHERE identificacion = '"+ Identificacion+"';");
              
        ResultSet rs = ps.executeQuery();
        
        while (rs.next()) {            
            usuarios a = new usuarios(rs.getInt("identificacion"), rs.getString("nombre_completo"), rs.getString("fecha_nacimiento"), 
                                        rs.getString("telefono"), rs.getString("direccion"), rs.getString("ciudad"));
            consulClientes.add(a);
        }
        rs.close();
        return  consulClientes;               
    }
}
