/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_a.Interfaz;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.awt.Image;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import proyecto_a.BasesDatos.BasesDatos;
import proyecto_a.BasesDatos.FuncionesSQL;
import proyecto_a.clasesP.Administrador;
import proyecto_a.clasesP.Empleados;
import proyecto_a.clasesP.Funcionarios;
import proyecto_a.clasesP.envios;
import proyecto_a.clasesP.usuarios;

/**
 *
 * @author jonat
 */
public final class Principal extends javax.swing.JFrame {
    
        
    private ArrayList<Administrador> administ;
    private static final FuncionesSQL fun = new FuncionesSQL();
    private ArrayList<Empleados> emplea;
    private ArrayList<Funcionarios> funciona;
    private ArrayList<usuarios> client;
    private static final BasesDatos con = new BasesDatos();
    Connection conx;
    //pr
    /**
     * Creates new form Principal
     */
    public Principal() {
        initComponents();
        imagenes();
        try {
            mostrarTablaEnvios();
        } catch (Exception e) {
        }
        try {
            mostrarTabla4("");
        } catch (SQLException e) {
        }
        try {
            RealizarInformes();
        } catch (SQLException e) {
        }
        
        try {
            mostrarTabla("");
        } catch (SQLException e) {
        }
        
        
        try {
        mostrarTabla2(0);
        } catch (SQLException e) {
            System.out.println(e);}
        
        
        try {            
            mostrarTabla3(0);
        } catch (SQLException e) {}
    }
    
    void imagenes(){
        ImageIcon imagen = new ImageIcon("src/proyecto_a/imagenes/fondo.jpg");
        Icon icono = new ImageIcon(imagen.getImage().getScaledInstance( ImagenLb.getWidth(),ImagenLb.getHeight(), Image.SCALE_DEFAULT));
        ImagenLb.setIcon(icono);
        Icon icon = new ImageIcon(imagen.getImage().getScaledInstance( LbImagen.getWidth(),LbImagen.getHeight(), Image.SCALE_DEFAULT));
        LbImagen.setIcon(icon);
        Icon icon1 = new ImageIcon(imagen.getImage().getScaledInstance( LbImagen1.getWidth(),LbImagen1.getHeight(), Image.SCALE_DEFAULT));
        LbImagen1.setIcon(icon1);
        Icon icon2 = new ImageIcon(imagen.getImage().getScaledInstance( LbImagen2.getWidth(),LbImagen2.getHeight(), Image.SCALE_DEFAULT));
        LbImagen2.setIcon(icon2);
        Icon icon3 = new ImageIcon(imagen.getImage().getScaledInstance( LbImagen3.getWidth(),LbImagen3.getHeight(), Image.SCALE_DEFAULT));
        LbImagen3.setIcon(icon3);
        this.repaint();
    }
    
    public void RealizarInformes() throws SQLException{
        DefaultTableModel Modelo6 = new DefaultTableModel();
        Modelo6.addColumn("Codigo Envio");
        Modelo6.addColumn("Ciudad Origen");
        Modelo6.addColumn("Ciudad Destino");
        Modelo6.addColumn("Fecha y hora del envio");
        Modelo6.addColumn("Detalles del Envio");
        Modelo6.addColumn("Identificacion Del Usuario");
        Modelo6.addColumn("Nombre Del Usuario");
        Modelo6.addColumn("Telefono del usuario");
        Modelo6.addColumn("Direccion Usuario");
        this.TbTablaInformes.setModel(Modelo6);
        String sql = "select e.idenvios, " +
                            "e.ciudad_origen, " +
                            "e.ciudad_destino, " +
                            "e.fecha_hora_envio, " +
                            "e.detalles_envio, " +
                            "e.identificacion, " +
                            "u.nombre_completo, " +
                            "u.telefono, " +
                            "u.direccion " +
                        "FROM envios e " +
                        "INNER JOIN usuarios u " +
                        "ON e.identificacion = u.identificacion";
               
        BasesDatos con = new BasesDatos();
        conx = con.conectar();
        String fila[] = new String[9];
        try {               
            Statement st = conx.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {                
                fila[0] = rs.getString(1);
                fila[1] = rs.getString(2);
                fila[2] = rs.getString(3);
                fila[3] = rs.getString(4);
                fila[4] = rs.getString(5);
                fila[5] = rs.getString(6);
                fila[6] = rs.getString(7);
                fila[7] = rs.getString(8);
                fila[8] = rs.getString(9);                
                Modelo6.addRow(fila);
            }                         
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error: jon " + e);
        }       
    }
    public void ConsultarCliente(){
        int fila = TbTabla.getSelectedRow();
        
        if (fila >= 0) {
            TxtIdenCli.setEnabled(false);
            TxtIdenCli.setText(TbTabla.getValueAt(fila, 0).toString());
            txtNomCli.setText(TbTabla.getValueAt(fila, 1).toString());
            TxtFechaUsua.setText(TbTabla.getValueAt(fila, 2).toString());
            TxtTelCli.setText(TbTabla.getValueAt(fila, 3).toString());
            TxtDireCli.setText(TbTabla.getValueAt(fila, 4).toString());
            CbxCiuCli.setSelectedItem(TbTabla.getValueAt(fila, 5).toString());           
            
        } else {
            JOptionPane.showMessageDialog(null, "Selecione una fila" );
        }
    }
    public void mostrarTabla(String Ciudad) throws SQLException{
        DefaultTableModel Modelo = new DefaultTableModel();
        Modelo.addColumn("Identificacion");
        Modelo.addColumn("Nombre Completo");
        Modelo.addColumn("Fecha Nacimiento");
        Modelo.addColumn("Telefono");
        Modelo.addColumn("Direccion");
        Modelo.addColumn("Ciudad");
        if (Ciudad.equals("")) {
            this.TbTabla.setModel(Modelo);
        }
        else{
            this.TbConsultasCiudad.setModel(Modelo);
        }
        try {
            client = fun.ConsultarClientes(Ciudad);
            while(Modelo.getRowCount()>0)Modelo.removeRow(0);
            int numCol = Modelo.getColumnCount();
            for (usuarios user : client) {
                Object[] fila = new Object[numCol];
                fila[0] = user.getIdentificacion();
                fila[1] = user.getNombreCompleto();
                fila[2] = user.getFechaNacimiento();
                fila[3] = user.getTelefono();
                fila[4] = user.getDireccion();
                fila[5] = user.getCiudad();                
                Modelo.addRow(fila);            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    public void ConsultarIdentificacionClien(Integer Identificacion) throws SQLException{
        DefaultTableModel Modelo = new DefaultTableModel();
        Modelo.addColumn("Identificacion");
        Modelo.addColumn("Nombre Completo");
        Modelo.addColumn("Fecha Nacimiento");
        Modelo.addColumn("Telefono");
        Modelo.addColumn("Direccion");
        Modelo.addColumn("Ciudad");
        this.TbConsultaIdentificacion.setModel(Modelo);
        
        try {
            client = fun.ConsultarClientesIdentificacion(Identificacion);
            while(Modelo.getRowCount()>0)Modelo.removeRow(0);
            int numCol = Modelo.getColumnCount();
            for (usuarios user : client) {
                Object[] fila = new Object[numCol];
                fila[0] = user.getIdentificacion();
                fila[1] = user.getNombreCompleto();
                fila[2] = user.getFechaNacimiento();
                fila[3] = user.getTelefono();
                fila[4] = user.getDireccion();
                fila[5] = user.getCiudad();                
                Modelo.addRow(fila);            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void SelecionarEmpleado(){
        int fila = TbTablaEmpleados.getSelectedRow();
        if (fila >= 0) {
            txtIdenE.setEnabled(false);
            txtIdenE.setText(TbTablaEmpleados.getValueAt(fila, 0).toString());
            TxtNombE.setText(TbTablaEmpleados.getValueAt(fila, 1).toString());
            txtFechaEmpleado.setText(TbTablaEmpleados.getValueAt(fila, 2).toString());
            TxtteleE.setText(TbTablaEmpleados.getValueAt(fila, 3).toString());
            TxtDireE.setText(TbTablaEmpleados.getValueAt(fila, 4).toString());
            TxtContrE.setText(TbTablaEmpleados.getValueAt(fila, 5).toString());           
            
        } else {
            JOptionPane.showMessageDialog(null, "Selecione una fila" );
        }
    }    
    public void mostrarTabla2(Integer Indentificacion) throws SQLException{
        DefaultTableModel Modelo2 = new DefaultTableModel();
        Modelo2.addColumn("Identificacion");
        Modelo2.addColumn("Nombre Completo");
        Modelo2.addColumn("Fecha Nacimiento");
        Modelo2.addColumn("Telefono");
        Modelo2.addColumn("Direccion");
        Modelo2.addColumn("Contraseña");
        if (Indentificacion.equals(0)) {
            this.TbTablaEmpleados.setModel(Modelo2);
        }
        else{
            this.TbConsultaIdentificacion.setModel(Modelo2);
        }
        
        try {
            emplea = fun.ConsultarEmpleados(Indentificacion);
            while(Modelo2.getRowCount()>0)Modelo2.removeRow(0);
            int numCol = Modelo2.getColumnCount();
            for (Empleados empleados : emplea) {
                Object[] fila = new Object[numCol];
                fila[0] = empleados.getIdentificacion();
                fila[1] = empleados.getNombreCompleto();
                fila[2] = empleados.getFechaNacimiento();
                fila[3] = empleados.getTelefono();
                fila[4] = empleados.getDireccion();
                fila[5] = empleados.getContrasena();
                //fila[6] = empleados;
                Modelo2.addRow(fila);
            }
            
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    public void EliminarFuncionario(){
        int fila = TbTablafuncionarios.getSelectedRow();
        if (fila >= 0) {
            try {
                conx = con.conectar();
                String identificacion = TbTablafuncionarios.getValueAt(fila, 0).toString();
                PreparedStatement ppt = conx.prepareStatement("DELETE FROM funcionarios WHERE identificacion = '"+ identificacion +"';");
                ppt.executeUpdate();
                
                JOptionPane.showMessageDialog(null,"Funcionario eliminado");
                mostrarTabla3(0);
            } catch (HeadlessException | SQLException e) {
                JOptionPane.showMessageDialog(null,"Funcionario no eliminado");
            }       
        }else{
            JOptionPane.showMessageDialog(null, "selecione la fila del funcionario que desea eliminar");
        }
                
    }
    
    public void mostrarTabla3(Integer Identificacion) throws SQLException{
        DefaultTableModel Modelo3 = new DefaultTableModel();
        Modelo3.addColumn("Identificacion");
        Modelo3.addColumn("Nombre Completo");
        Modelo3.addColumn("Fecha Nacimiento");
        Modelo3.addColumn("Telefono");
        Modelo3.addColumn("Contraseña");
        Modelo3.addColumn("Rol");
        if (Identificacion.equals(0)) {
            this.TbTablafuncionarios.setModel(Modelo3);
        }else{
            this.TbConsultaIdentificacion.setModel(Modelo3);                    
        }       
        try {
            funciona = fun.ConsultarFuncionarios(Identificacion);
            while (Modelo3.getRowCount()>0)Modelo3.removeRow(0);
            int numCol = Modelo3.getColumnCount();
            for (Funcionarios funcionario : funciona) {
                Object[] fila = new Object[numCol];
                fila[0] = funcionario.getIdentificacion();
                fila[1] = funcionario.getNombreCompleto();
                fila[2] = funcionario.getFechaNacimiento();
                fila[3] = funcionario.getTelefono();
                fila[4] = funcionario.getContrasena();
                fila[5] = funcionario.getRol();
                Modelo3.addRow(fila);
            }
        } catch (SQLException e) {
            System.out.println("error en la consulta de funcionarios");
        }
    }
    public void SelecionarAdministrador(){
        int fila = TbTablaAdministradores.getSelectedRow();
        if (fila >= 0) {
            lbId.setText(TbTablaAdministradores.getValueAt(fila, 0).toString());
            txtNomAdm.setText(TbTablaAdministradores.getValueAt(fila, 1).toString());
            TxtIdeAdm.setText(TbTablaAdministradores.getValueAt(fila, 2).toString());
            txtFechaAdministrador.setText(TbTablaAdministradores.getValueAt(fila, 3).toString());
            txtPasswAdm.setText(TbTablaAdministradores.getValueAt(fila, 4).toString());
            txtTeleAdm.setText(TbTablaAdministradores.getValueAt(fila, 5).toString());
            CbxCiudAdmin.setSelectedItem(TbTablaAdministradores.getValueAt(fila, 6).toString());           
            
        } else {
            JOptionPane.showMessageDialog(null, "Selecione una fila" );
        }
    }
    
    public void EliminarUsuario(){
        int fila = TbTabla.getSelectedRow();
        if (fila >= 0) {
            try {
                conx = con.conectar();
                String identificacion = TbTabla.getValueAt(fila, 0).toString();
                PreparedStatement ppt = conx.prepareStatement("DELETE FROM usuarios WHERE identificacion = '"+ identificacion +"';");
                ppt.executeUpdate();
                mostrarTabla("");
                JOptionPane.showMessageDialog(null,"Usuario eliminado");
            } catch (HeadlessException | SQLException e) {
                JOptionPane.showMessageDialog(null,"Usuario no eliminado");
            }       
        }else{
            JOptionPane.showMessageDialog(null, "selecione la fila del usuario que desea eliminar");
        }
                
    }
    
    public void EliminarEmpleado(){
        int fila = TbTablaEmpleados.getSelectedRow();
        if (fila >= 0) {
            try {
                conx = con.conectar();
                String identificacion = TbTablaEmpleados.getValueAt(fila, 0).toString();
                PreparedStatement ppt = conx.prepareStatement("DELETE FROM EMPLEADOS WHERE identificacion = '"+ identificacion +"';");
                ppt.executeUpdate();
                mostrarTabla2(0);
                JOptionPane.showMessageDialog(null,"Empleado eliminado");
            } catch (HeadlessException | SQLException e) {
                JOptionPane.showMessageDialog(null,"Empleado no eliminado");
            }       
        }else{
            JOptionPane.showMessageDialog(null, "selecione la fila del usuario que desea eliminar");
        }
                
    }
    
    public void EliminarAdministrador(){
        int fila = TbTablaAdministradores.getSelectedRow();
        if (fila >= 0) {
            try {
                conx = con.conectar();
                String id = TbTablaAdministradores.getValueAt(fila, 0).toString();
                PreparedStatement ppt = conx.prepareStatement("DELETE FROM administrador WHERE id_administrador = '"+ id +"';");
                ppt.executeUpdate();
                mostrarTabla4("");
                JOptionPane.showMessageDialog(null,"Administrador eliminado");
            } catch (HeadlessException | SQLException e) {
                JOptionPane.showMessageDialog(null,"Administrador no eliminado");
            }       
        }else{
            JOptionPane.showMessageDialog(null, "selecione la fila del Administrador que desea eliminar");
        }
                
    }
    public void ConsultarIdentificacionAdmin(Integer Identificacion) throws SQLException{
        DefaultTableModel Modelo4 = new DefaultTableModel();
        Modelo4.addColumn("Num");
        Modelo4.addColumn("Nombre Completo");
        Modelo4.addColumn("Identificacion");
        Modelo4.addColumn("Fecha Nacimiento");
        Modelo4.addColumn("Contraseña");
        Modelo4.addColumn("Telefono");
        Modelo4.addColumn("Ciudad");
        
        String sql = "";
        
        sql = "SELECT * FROM administrador WHERE cedula = '"+Identificacion+"';";
            this.TbConsultaIdentificacion.setModel(Modelo4);
        
        
        BasesDatos con = new BasesDatos();
        conx = con.conectar();
        String fila[] = new String[7];
        try {               
            Statement st = conx.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {                
                fila[0] = rs.getString(1);
                fila[1] = rs.getString(2);
                fila[2] = rs.getString(3);
                fila[3] = rs.getString(4);
                fila[4] = rs.getString(5);
                fila[5] = rs.getString(6);
                fila[6] = rs.getString(7);
                
                Modelo4.addRow(fila);
            }                         
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error: jon " + e);
        }       
    }
   
    
    public void mostrarTabla4(String Ciudad) throws SQLException{
        DefaultTableModel Modelo4 = new DefaultTableModel();
        Modelo4.addColumn("Num");
        Modelo4.addColumn("Nombre Completo");
        Modelo4.addColumn("Identificacion");
        Modelo4.addColumn("Fecha Nacimiento");
        Modelo4.addColumn("Contraseña");
        Modelo4.addColumn("Telefono");
        Modelo4.addColumn("Ciudad");
        
        String sql = "";
        if (Ciudad.equals("")) {
            sql = "SELECT * FROM administrador";
            this.TbTablaAdministradores.setModel(Modelo4);
        }
        else{
            sql = "SELECT * FROM administrador WHERE ciudad = '"+Ciudad+"';";
            this.TbConsultasCiudad.setModel(Modelo4);
        }
        
        BasesDatos con = new BasesDatos();
        conx = con.conectar();
        String fila[] = new String[7];
        try {               
            Statement st = conx.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {                
                fila[0] = rs.getString(1);
                fila[1] = rs.getString(2);
                fila[2] = rs.getString(3);
                fila[3] = rs.getString(4);
                fila[4] = rs.getString(5);
                fila[5] = rs.getString(6);
                fila[6] = rs.getString(7);
                
                Modelo4.addRow(fila);
            }                         
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error: jon " + e);
        }       
    }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        ImagenLb = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        BtnActualizarEmpleados = new javax.swing.JButton();
        BtnEliminarEmplea = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        TxtDireE = new javax.swing.JTextField();
        TxtteleE = new javax.swing.JTextField();
        LbImagen1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TbTablaEmpleados = new javax.swing.JTable();
        TxtNombE = new javax.swing.JTextField();
        txtIdenE = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        TxtContrE = new javax.swing.JPasswordField();
        DateEmpleados = new com.toedter.calendar.JDateChooser();
        BtnSelecionarEmpleados = new javax.swing.JButton();
        lbId = new javax.swing.JLabel();
        txtFechaEmpleado = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        TxtIdeAdm = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        txtNomAdm = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        txtTeleAdm = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        CbxCiudAdmin = new javax.swing.JComboBox<>();
        jButton10 = new javax.swing.JButton();
        BtnEliminarAdministradores = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        TbTablaAdministradores = new javax.swing.JTable();
        BtnActualizarAdministradores = new javax.swing.JButton();
        LbImagen3 = new javax.swing.JLabel();
        txtPasswAdm = new javax.swing.JPasswordField();
        DateAdmintradores = new com.toedter.calendar.JDateChooser();
        BtnSelecionarAdmin = new javax.swing.JButton();
        txtFechaAdministrador = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        TxtIdenCli = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        TxtDireCli = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        TxtTelCli = new javax.swing.JTextField();
        txtNomCli = new javax.swing.JTextField();
        CbxCiuCli = new javax.swing.JComboBox<>();
        BtnGuardarCliente = new javax.swing.JButton();
        BtnEliminarclientes = new javax.swing.JButton();
        BtnActualizarClientes = new javax.swing.JButton();
        LbImagen = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TbTabla = new javax.swing.JTable();
        DateClientes = new com.toedter.calendar.JDateChooser();
        BtnSelecionarClient = new javax.swing.JButton();
        TxtFechaUsua = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtIdeFun = new javax.swing.JTextField();
        txtNomFun = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txtTelFun = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        BtnEliminarFuncionario = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        TbTablafuncionarios = new javax.swing.JTable();
        LbImagen2 = new javax.swing.JLabel();
        BtnActualizarFuncionario = new javax.swing.JButton();
        txtPassFun = new javax.swing.JPasswordField();
        DateFuncionarios = new com.toedter.calendar.JDateChooser();
        CbxRolFunci = new javax.swing.JComboBox<>();
        BtnSelecionarFunci = new javax.swing.JButton();
        TxtFechaFuncionario = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel7 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        CbxRolConsultas = new javax.swing.JComboBox<>();
        jLabel27 = new javax.swing.JLabel();
        CbxCidadConsultas = new javax.swing.JComboBox<>();
        BtnConsultarXCiudad = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        TbConsultasCiudad = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        CbxRol2Consultas = new javax.swing.JComboBox<>();
        BtnConsultarXIdentificacion = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        TbConsultaIdentificacion = new javax.swing.JTable();
        TxtIdenConsultas = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        TxtFechaHora = new javax.swing.JTextField();
        CbxCiudadRegistrosEnvios = new javax.swing.JComboBox<>();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        TxtDetallesEnvio = new javax.swing.JTextField();
        jScrollPane7 = new javax.swing.JScrollPane();
        TbRegistrarEnvios = new javax.swing.JTable();
        BtnRegistrarEnvios = new javax.swing.JButton();
        TxtCiudadDestino = new javax.swing.JTextField();
        CbxsignarIdentificacion = new javax.swing.JComboBox<>();
        TxtCodigoEnvio = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        TbTablaInformes = new javax.swing.JTable();
        jLabel36 = new javax.swing.JLabel();

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyecto_a/imagenes/víos.png"))); // NOI18N

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jTabbedPane1.setPreferredSize(new java.awt.Dimension(1366, 700));

        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.setPreferredSize(new java.awt.Dimension(1360, 710));
        jPanel1.setRequestFocusEnabled(false);

        ImagenLb.setMaximumSize(new java.awt.Dimension(1360, 710));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ImagenLb, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1361, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ImagenLb, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 632, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Pagina Principal", jPanel1);

        jPanel3.setFont(new java.awt.Font("Times New Roman", 2, 18)); // NOI18N

        BtnActualizarEmpleados.setText("Actualizar");
        BtnActualizarEmpleados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnActualizarEmpleadosActionPerformed(evt);
            }
        });

        BtnEliminarEmplea.setText("Eliminar");
        BtnEliminarEmplea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEliminarEmpleaActionPerformed(evt);
            }
        });

        jButton6.setText("Guardar");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        TxtDireE.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        TxtteleE.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        TbTablaEmpleados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(TbTablaEmpleados);

        TxtNombE.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        txtIdenE.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Times New Roman", 2, 18)); // NOI18N
        jLabel8.setText("Identificacion");

        jLabel9.setFont(new java.awt.Font("Times New Roman", 2, 18)); // NOI18N
        jLabel9.setText("Nombre Completo");

        jLabel10.setFont(new java.awt.Font("Times New Roman", 2, 18)); // NOI18N
        jLabel10.setText("Fecha de nacimiento");

        jLabel11.setFont(new java.awt.Font("Times New Roman", 2, 18)); // NOI18N
        jLabel11.setText("Telefono");

        jLabel12.setFont(new java.awt.Font("Times New Roman", 2, 18)); // NOI18N
        jLabel12.setText("Direccion");

        jLabel13.setFont(new java.awt.Font("Times New Roman", 2, 18)); // NOI18N
        jLabel13.setText("Contraseña");

        TxtContrE.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        TxtContrE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtContrEActionPerformed(evt);
            }
        });

        BtnSelecionarEmpleados.setText("Selecionar");
        BtnSelecionarEmpleados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSelecionarEmpleadosActionPerformed(evt);
            }
        });

        lbId.setBackground(new java.awt.Color(255, 255, 255));
        lbId.setForeground(new java.awt.Color(248, 242, 242));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1287, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtFechaEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(DateEmpleados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(18, 18, 18))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(lbId)
                                        .addGap(164, 164, 164)))
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGap(14, 14, 14)
                                        .addComponent(BtnSelecionarEmpleados, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(BtnEliminarEmplea, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(BtnActualizarEmpleados, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel13)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(TxtNombE))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addGap(66, 66, 66)
                                        .addComponent(txtIdenE, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel11))
                                .addGap(23, 23, 23)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(TxtteleE)
                                    .addComponent(TxtDireE)
                                    .addComponent(TxtContrE, javax.swing.GroupLayout.DEFAULT_SIZE, 305, Short.MAX_VALUE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(LbImagen1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(30, 30, 30))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LbImagen1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(TxtteleE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel8)
                                        .addComponent(txtIdenE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel11)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(TxtDireE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(TxtNombE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel12))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel13)
                                    .addComponent(TxtContrE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtFechaEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(DateEmpleados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(BtnActualizarEmpleados, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(BtnEliminarEmplea, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(BtnSelecionarEmpleados, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addComponent(lbId)
                                .addGap(34, 34, 34)))))
                .addGap(27, 27, 27)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(53, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("Empleados", jPanel4);

        jLabel20.setFont(new java.awt.Font("Times New Roman", 2, 18)); // NOI18N
        jLabel20.setText("Identificacion");

        TxtIdeAdm.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel21.setFont(new java.awt.Font("Times New Roman", 2, 18)); // NOI18N
        jLabel21.setText("Nombre Completo");

        txtNomAdm.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel22.setFont(new java.awt.Font("Times New Roman", 2, 18)); // NOI18N
        jLabel22.setText("Fecha de nacimiento");

        jLabel23.setFont(new java.awt.Font("Times New Roman", 2, 18)); // NOI18N
        jLabel23.setText("Contraseña");

        jLabel24.setFont(new java.awt.Font("Times New Roman", 2, 18)); // NOI18N
        jLabel24.setText("Telefono");

        txtTeleAdm.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel25.setFont(new java.awt.Font("Times New Roman", 2, 18)); // NOI18N
        jLabel25.setText("Ciudad");

        CbxCiudAdmin.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        CbxCiudAdmin.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ibague", "Neiva", "Florencia" }));
        CbxCiudAdmin.setSelectedIndex(-1);
        CbxCiudAdmin.setToolTipText("");

        jButton10.setText("Guardar");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        BtnEliminarAdministradores.setText("Eliminar");
        BtnEliminarAdministradores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEliminarAdministradoresActionPerformed(evt);
            }
        });

        TbTablaAdministradores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane4.setViewportView(TbTablaAdministradores);

        BtnActualizarAdministradores.setText("Actualizar");
        BtnActualizarAdministradores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnActualizarAdministradoresActionPerformed(evt);
            }
        });

        txtPasswAdm.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N

        BtnSelecionarAdmin.setText("Selecionar");
        BtnSelecionarAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSelecionarAdminActionPerformed(evt);
            }
        });

        txtFechaAdministrador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFechaAdministradorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(BtnSelecionarAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(BtnEliminarAdministradores, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(BtnActualizarAdministradores, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtNomAdm, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel24))
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(jLabel20)
                                        .addGap(66, 66, 66)
                                        .addComponent(TxtIdeAdm, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel23)))
                                .addGap(23, 23, 23))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtFechaAdministrador, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33)
                                .addComponent(DateAdmintradores, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel25)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(CbxCiudAdmin, 0, 291, Short.MAX_VALUE)
                            .addComponent(txtTeleAdm)
                            .addComponent(txtPasswAdm))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LbImagen3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(30, 30, 30))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 1287, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(38, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(LbImagen3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel23)
                                .addComponent(txtPasswAdm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel20)
                                .addComponent(TxtIdeAdm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTeleAdm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel24)
                            .addComponent(txtNomAdm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel21))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(CbxCiudAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(DateAdmintradores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel22)
                                    .addComponent(jLabel25)
                                    .addComponent(txtFechaAdministrador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(BtnActualizarAdministradores, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BtnEliminarAdministradores, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BtnSelecionarAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(62, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("Administradores", jPanel6);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 2, 18)); // NOI18N
        jLabel1.setText("Identificacion");

        TxtIdenCli.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Times New Roman", 2, 18)); // NOI18N
        jLabel2.setText("Nombre Completo");

        TxtDireCli.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Times New Roman", 2, 18)); // NOI18N
        jLabel4.setText("Fecha de nacimiento");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 2, 18)); // NOI18N
        jLabel5.setText("Telefono");

        jLabel6.setFont(new java.awt.Font("Times New Roman", 2, 18)); // NOI18N
        jLabel6.setText("Direccion");

        jLabel7.setFont(new java.awt.Font("Times New Roman", 2, 18)); // NOI18N
        jLabel7.setText("Ciudad");

        TxtTelCli.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        txtNomCli.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        CbxCiuCli.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        CbxCiuCli.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Neiva", "Ibague", "Florencia" }));
        CbxCiuCli.setSelectedIndex(-1);
        CbxCiuCli.setToolTipText("");

        BtnGuardarCliente.setText("Guardar");
        BtnGuardarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGuardarClienteActionPerformed(evt);
            }
        });

        BtnEliminarclientes.setText("Eliminar");
        BtnEliminarclientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEliminarclientesActionPerformed(evt);
            }
        });

        BtnActualizarClientes.setText("Actualizar");
        BtnActualizarClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnActualizarClientesActionPerformed(evt);
            }
        });

        TbTabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(TbTabla);

        BtnSelecionarClient.setText("Selecionar");
        BtnSelecionarClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSelecionarClientActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TxtFechaUsua, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGap(267, 267, 267)
                                .addComponent(CbxCiuCli, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGap(186, 186, 186)
                                .addComponent(jLabel7))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addComponent(BtnGuardarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(BtnSelecionarClient, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(BtnEliminarclientes, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(BtnActualizarClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(66, 66, 66)
                                .addComponent(TxtIdenCli, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel5)
                                .addGap(19, 19, 19))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel9Layout.createSequentialGroup()
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtNomCli))
                                    .addGroup(jPanel9Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(DateClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(TxtTelCli, javax.swing.GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE)
                            .addComponent(TxtDireCli))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LbImagen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(30, 30, 30))
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1287, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(38, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(LbImagen, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel9Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel5)
                                        .addComponent(TxtTelCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel1)
                                        .addComponent(TxtIdenCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(TxtDireCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6)
                                    .addComponent(txtNomCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(CbxCiuCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel4)
                                        .addComponent(jLabel7)
                                        .addComponent(TxtFechaUsua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(DateClientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(BtnActualizarClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BtnEliminarclientes, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BtnSelecionarClient, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BtnGuardarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(55, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("Clientes", jPanel9);

        jLabel14.setFont(new java.awt.Font("Times New Roman", 2, 18)); // NOI18N
        jLabel14.setText("Identificacion");

        jLabel15.setFont(new java.awt.Font("Times New Roman", 2, 18)); // NOI18N
        jLabel15.setText("Nombre Completo");

        txtIdeFun.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        txtNomFun.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel16.setFont(new java.awt.Font("Times New Roman", 2, 18)); // NOI18N
        jLabel16.setText("Fecha de nacimiento");

        jLabel17.setFont(new java.awt.Font("Times New Roman", 2, 18)); // NOI18N
        jLabel17.setText("Contraseña");

        jLabel18.setFont(new java.awt.Font("Times New Roman", 2, 18)); // NOI18N
        jLabel18.setText("Telefono");

        jLabel19.setFont(new java.awt.Font("Times New Roman", 2, 18)); // NOI18N
        jLabel19.setText("Rol");

        txtTelFun.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jButton7.setText("Guardar");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        BtnEliminarFuncionario.setText("Eliminar");
        BtnEliminarFuncionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEliminarFuncionarioActionPerformed(evt);
            }
        });

        TbTablafuncionarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(TbTablafuncionarios);

        BtnActualizarFuncionario.setText("Actualizar");
        BtnActualizarFuncionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnActualizarFuncionarioActionPerformed(evt);
            }
        });

        CbxRolFunci.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cajero", "Bodeguero" }));
        CbxRolFunci.setSelectedIndex(-1);
        CbxRolFunci.setToolTipText("");

        BtnSelecionarFunci.setText("Selecionar");
        BtnSelecionarFunci.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSelecionarFunciActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 1287, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtIdeFun, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TxtFechaFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(DateFuncionarios, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtNomFun, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17)
                            .addComponent(jLabel18)
                            .addComponent(jLabel19))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(CbxRolFunci, 0, 310, Short.MAX_VALUE)
                                .addComponent(txtTelFun, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtPassFun, javax.swing.GroupLayout.Alignment.LEADING))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(BtnSelecionarFunci, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(13, 13, 13)
                                .addComponent(BtnActualizarFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(BtnEliminarFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(LbImagen2, javax.swing.GroupLayout.PREFERRED_SIZE, 387, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LbImagen2, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel14)
                                    .addComponent(txtIdeFun, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(7, 7, 7)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel15)
                                    .addComponent(txtNomFun, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel17)
                                    .addComponent(txtPassFun, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel18)
                                .addComponent(txtTelFun, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel19)
                                .addComponent(CbxRolFunci, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(DateFuncionarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel16)
                                    .addComponent(TxtFechaFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(BtnEliminarFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(BtnActualizarFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(BtnSelecionarFunci, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(74, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("Funcionarios", jPanel5);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jTabbedPane3)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jTabbedPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 670, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Registrar Usuarios", jPanel3);

        jLabel26.setFont(new java.awt.Font("Times New Roman", 2, 18)); // NOI18N
        jLabel26.setText("Rol");

        CbxRolConsultas.setFont(new java.awt.Font("Times New Roman", 2, 18)); // NOI18N
        CbxRolConsultas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-Selecionar-", "Administrador", "Cliente" }));

        jLabel27.setFont(new java.awt.Font("Times New Roman", 2, 18)); // NOI18N
        jLabel27.setText("Ingrese la ciudad");

        CbxCidadConsultas.setFont(new java.awt.Font("Times New Roman", 2, 18)); // NOI18N
        CbxCidadConsultas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-Selecionar-", "Ibague", "Neiva", "Florencia" }));

        BtnConsultarXCiudad.setFont(new java.awt.Font("Times New Roman", 2, 18)); // NOI18N
        BtnConsultarXCiudad.setText("Consultar");
        BtnConsultarXCiudad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnConsultarXCiudadActionPerformed(evt);
            }
        });

        TbConsultasCiudad.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane5.setViewportView(TbConsultasCiudad);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 1238, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel26)
                            .addComponent(jLabel27))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(CbxCidadConsultas, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CbxRolConsultas, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(37, 37, 37)
                        .addComponent(BtnConsultarXCiudad, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(96, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BtnConsultarXCiudad, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel26)
                            .addComponent(CbxRolConsultas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(16, 16, 16)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel27)
                            .addComponent(CbxCidadConsultas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 456, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(39, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Consultar X Ciudad", jPanel7);

        jLabel28.setFont(new java.awt.Font("Times New Roman", 2, 18)); // NOI18N
        jLabel28.setText("Rol");

        jLabel29.setFont(new java.awt.Font("Times New Roman", 2, 18)); // NOI18N
        jLabel29.setText("Indentificacion");

        CbxRol2Consultas.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        CbxRol2Consultas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-Selecionar-", "Administrador", "Cliente", "Empleado", "Funcionario" }));

        BtnConsultarXIdentificacion.setText("Consultar");
        BtnConsultarXIdentificacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnConsultarXIdentificacionActionPerformed(evt);
            }
        });

        TbConsultaIdentificacion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane6.setViewportView(TbConsultaIdentificacion);

        TxtIdenConsultas.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 1238, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel28)
                            .addComponent(jLabel29))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(CbxRol2Consultas, 0, 221, Short.MAX_VALUE)
                            .addComponent(TxtIdenConsultas))
                        .addGap(37, 37, 37)
                        .addComponent(BtnConsultarXIdentificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(101, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BtnConsultarXIdentificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel28)
                            .addComponent(CbxRol2Consultas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel29)
                            .addComponent(TxtIdenConsultas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 456, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(46, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Consultar X Identificacion", jPanel8);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1375, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 640, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Consultar", jPanel2);

        jLabel30.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jLabel30.setText("Ciudad Destino");

        jLabel31.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jLabel31.setText("Ciudad Origen");

        TxtFechaHora.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N

        CbxCiudadRegistrosEnvios.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        CbxCiudadRegistrosEnvios.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-seleciona-", "Ibague", "Florencia", "Neiva" }));
        CbxCiudadRegistrosEnvios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CbxCiudadRegistrosEnviosActionPerformed(evt);
            }
        });

        jLabel32.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jLabel32.setText("Detalles Envio");

        jLabel33.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jLabel33.setText("Fecha y Hora del Envio");

        jLabel34.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jLabel34.setText("Identificacion del usuario");

        TxtDetallesEnvio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtDetallesEnvioActionPerformed(evt);
            }
        });

        TbRegistrarEnvios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane7.setViewportView(TbRegistrarEnvios);

        BtnRegistrarEnvios.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        BtnRegistrarEnvios.setText("Registrar Envio");
        BtnRegistrarEnvios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnRegistrarEnviosActionPerformed(evt);
            }
        });

        TxtCiudadDestino.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N

        CbxsignarIdentificacion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-Seleciona-" }));

        TxtCodigoEnvio.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N

        jLabel35.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jLabel35.setText("Cod. Envio");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel10Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel31)
                                .addComponent(jLabel35))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(CbxCiudadRegistrosEnvios, 0, 241, Short.MAX_VALUE)
                                .addComponent(TxtCodigoEnvio))
                            .addGap(27, 27, 27)
                            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel30)
                                .addComponent(jLabel33))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(TxtCiudadDestino, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
                                .addComponent(TxtFechaHora))
                            .addGap(29, 29, 29)
                            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel34)
                                .addComponent(jLabel32))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(CbxsignarIdentificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(TxtDetallesEnvio)))
                        .addGroup(jPanel10Layout.createSequentialGroup()
                            .addGap(24, 24, 24)
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 1291, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(BtnRegistrarEnvios, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(46, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(TxtFechaHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel34)
                    .addComponent(CbxsignarIdentificacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtCodigoEnvio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel35))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel31)
                        .addComponent(CbxCiudadRegistrosEnvios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel30)
                        .addComponent(jLabel32)
                        .addComponent(TxtDetallesEnvio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(TxtCiudadDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(25, 25, 25)
                .addComponent(BtnRegistrarEnvios, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 432, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
        );

        jTabbedPane1.addTab("Registrar Envios", jPanel10);

        TbTablaInformes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane8.setViewportView(TbTablaInformes);

        jLabel36.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel36.setText("Cuadros de envios de la empresa Soluciones Integradas S.A");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane8, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(283, 283, 283)
                .addComponent(jLabel36)
                .addContainerGap(473, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap(33, Short.MAX_VALUE)
                .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 553, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab("Reportes de envios", jPanel11);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 660, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 14, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnRegistrarEnviosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnRegistrarEnviosActionPerformed
        int Cod = Integer.parseInt(TxtCodigoEnvio.getText());
        String CiuIni = CbxCiudadRegistrosEnvios.getSelectedItem().toString();
        String CiuLle = TxtCiudadDestino.getText();
        String fechaHo = TxtFechaHora.getText();
        String Deta = TxtDetallesEnvio.getText();
        int iden = Integer.parseInt(CbxsignarIdentificacion.getSelectedItem().toString());

        envios envio = new envios(Cod, CiuIni, CiuLle, fechaHo, Deta, iden);
        try {
            fun.registrarEnvios(envio);
            mostrarTablaEnvios();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error: "+e);
        }finally{
            TxtCodigoEnvio.setText("");
            CbxCiudadRegistrosEnvios.setSelectedIndex(0);
            TxtCiudadDestino.setText("");
            TxtFechaHora.setText("");
            TxtDetallesEnvio.setText("");
            CbxsignarIdentificacion.setSelectedItem(-1);
        }

    }//GEN-LAST:event_BtnRegistrarEnviosActionPerformed

    private void TxtDetallesEnvioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtDetallesEnvioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtDetallesEnvioActionPerformed

    private void CbxCiudadRegistrosEnviosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CbxCiudadRegistrosEnviosActionPerformed
        Connection Con = null;
        PreparedStatement ppt = null;
        BasesDatos con = new BasesDatos();
        try {
            Con = con.conectar();
            String Ciudad = CbxCiudadRegistrosEnvios.getSelectedItem().toString();
            if (CbxCiudadRegistrosEnvios.getSelectedItem().equals("Neiva")) {
                CbxsignarIdentificacion.removeAllItems();
                ppt = Con.prepareStatement("SELECT identificacion FROM usuarios WHERE ciudad = '"+Ciudad+"';");
                ResultSet rs = ppt.executeQuery();
                while (rs.next()) {
                    int a = rs.getInt("identificacion");
                    String e = String.valueOf(a);
                    CbxsignarIdentificacion.addItem(e);
                    CbxsignarIdentificacion.setSelectedIndex(-1);
                }
            }
            if (CbxCiudadRegistrosEnvios.getSelectedItem().equals("Ibague")) {
                CbxsignarIdentificacion.removeAllItems();
                ppt = Con.prepareStatement("SELECT identificacion FROM usuarios WHERE ciudad = '"+Ciudad+"';");
                ResultSet rs = ppt.executeQuery();
                while (rs.next()) {
                    int a = rs.getInt("identificacion");
                    String e = String.valueOf(a);
                    CbxsignarIdentificacion.addItem(e);
                    CbxsignarIdentificacion.setSelectedIndex(-1);
                }
            }else if (CbxCiudadRegistrosEnvios.getSelectedItem().equals("Florencia")) {
                CbxsignarIdentificacion.removeAllItems();
                ppt = Con.prepareStatement("SELECT identificacion FROM usuarios WHERE ciudad = '"+Ciudad+"';");
                ResultSet rs = ppt.executeQuery();
                while (rs.next()) {
                    int a = rs.getInt("identificacion");
                    String e = String.valueOf(a);
                    CbxsignarIdentificacion.addItem(e);
                    CbxsignarIdentificacion.setSelectedIndex(-1);
                }
            }
        }catch(SQLException e){

        }
    }//GEN-LAST:event_CbxCiudadRegistrosEnviosActionPerformed

    private void BtnConsultarXIdentificacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnConsultarXIdentificacionActionPerformed
        if (CbxRol2Consultas.getSelectedItem().toString().equals("Cliente")) {
            int identificacion = Integer.parseInt(TxtIdenConsultas.getText());

            try {
                ConsultarIdentificacionClien(identificacion);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "La Identificacion no Existe" );
            }
        }
        if (CbxRol2Consultas.getSelectedItem().toString().equals("Administrador")) {
            int identificacion = Integer.parseInt(TxtIdenConsultas.getText());

            try {
                ConsultarIdentificacionAdmin(identificacion);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "La Identificacion no Existe");
            }
        }
        if (CbxRol2Consultas.getSelectedItem().toString().equals("Empleado")) {
            int identificacion = Integer.parseInt(TxtIdenConsultas.getText());

            try {
                mostrarTabla2(identificacion);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "La Identificacion no Existe");
            }
        }
        if (CbxRol2Consultas.getSelectedItem().toString().equals("Funcionario")) {
            int identificacion = Integer.parseInt(TxtIdenConsultas.getText());

            try {
                mostrarTabla3(identificacion);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "La Identificacion no Existe");
            }
        }
    }//GEN-LAST:event_BtnConsultarXIdentificacionActionPerformed

    private void BtnConsultarXCiudadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnConsultarXCiudadActionPerformed
        if (CbxRolConsultas.getSelectedItem().equals("Administrador")) {
            try {
                mostrarTabla4(CbxCidadConsultas.getSelectedItem().toString());
            } catch (Exception e) {

            }

        }
        if (CbxRolConsultas.getSelectedItem().equals("Cliente")) {
            try {
                mostrarTabla(CbxCidadConsultas.getSelectedItem().toString());
            } catch (Exception e) {
            }
        }
    }//GEN-LAST:event_BtnConsultarXCiudadActionPerformed

    private void BtnSelecionarFunciActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSelecionarFunciActionPerformed
        try {
            txtIdeFun.setEnabled(false);
            ConsultarFuncionario();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"No se pudo realizar la consulta");
        }

    }//GEN-LAST:event_BtnSelecionarFunciActionPerformed

    private void BtnActualizarFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnActualizarFuncionarioActionPerformed
        try {

            String fecha = TxtFechaFuncionario.getText();
            int identificacion = Integer.parseInt(txtIdeFun.getText());
            String NombreCompleto = txtNomFun.getText();
            String Telefono = txtTelFun.getText();
            String Contrasena = txtPassFun.getText();
            String Rol = CbxRolFunci.getSelectedItem().toString();

            Funcionarios funcionario = new Funcionarios(identificacion, NombreCompleto, fecha, Telefono, Contrasena, Rol);

            fun.UpdateFuncionarios(funcionario);
            mostrarTabla3(0);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error:  "+ e);
        }finally{
            LimpiarCajasFuncionarios();
            TxtFechaFuncionario.setText("");
            txtIdeFun.setEnabled(true);
        }
    }//GEN-LAST:event_BtnActualizarFuncionarioActionPerformed

    private void BtnEliminarFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEliminarFuncionarioActionPerformed
        EliminarFuncionario();
    }//GEN-LAST:event_BtnEliminarFuncionarioActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        try {
            String dia = Integer.toString(DateFuncionarios.getCalendar().get(Calendar.DAY_OF_MONTH));
            String mes = Integer.toString(DateFuncionarios.getCalendar().get(Calendar.MONTH));
            String ano = Integer.toString(DateFuncionarios.getCalendar().get(Calendar.YEAR));
            String fecha = (dia+"/"+mes+"/"+ano);
            int identificacion = Integer.parseInt(txtIdeFun.getText());
            String NombreCompleto = txtNomFun.getText();
            String Telefono = txtTelFun.getText();
            String Contrasena = txtPassFun.getText();
            String Rol = CbxRolFunci.getSelectedItem().toString();

            Funcionarios funcionario = new Funcionarios(identificacion, NombreCompleto, fecha, Telefono, Contrasena, Rol);

            FuncionesSQL funcionFuncioInser = new FuncionesSQL();
            funcionFuncioInser.insercionFuncionarios(funcionario);
            mostrarTabla3(0);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "error:  "+ e);
        }finally{
            LimpiarCajasFuncionarios();
        }

    }//GEN-LAST:event_jButton7ActionPerformed

    private void BtnSelecionarClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSelecionarClientActionPerformed
        try {
            TxtIdenCli.setEnabled(false);
            ConsultarCliente();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "no se puedo selecionar tabla cliente");
        }

    }//GEN-LAST:event_BtnSelecionarClientActionPerformed

    private void BtnActualizarClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnActualizarClientesActionPerformed
        try {

            String Fecha = TxtFechaUsua.getText();

            int identificacion = Integer.parseInt(TxtIdenCli.getText());
            String NombreCompleto = txtNomCli.getText();
            String Telefono = TxtTelCli.getText();
            String Direccion = TxtDireCli.getText();
            String Ciudad = CbxCiuCli.getSelectedItem().toString();

            usuarios usuario = new usuarios(identificacion, NombreCompleto,
                Fecha, Telefono, Direccion, Ciudad);

            FuncionesSQL funActualizaCliente = new FuncionesSQL();
            funActualizaCliente.UpdateCliente(usuario);

            mostrarTabla("");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error en la toma de datos");
        }finally{
            limpiarCajasUsuario();
            TxtFechaUsua.setText("");
            TxtIdenCli.setEnabled(true);
        }
    }//GEN-LAST:event_BtnActualizarClientesActionPerformed

    private void BtnEliminarclientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEliminarclientesActionPerformed
        EliminarUsuario();
    }//GEN-LAST:event_BtnEliminarclientesActionPerformed

    private void BtnGuardarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGuardarClienteActionPerformed
        try {

            String dia = Integer.toString(DateClientes.getCalendar().get(Calendar.DAY_OF_MONTH));
            String Mes = Integer.toString(DateClientes.getCalendar().get(Calendar.MONTH));
            String Ano = Integer.toString(DateClientes.getCalendar().get(Calendar.YEAR));
            String Fecha = (dia + "/"+ Mes + "/" + Ano);

            int identificacion = Integer.parseInt(TxtIdenCli.getText());
            String NombreCompleto = txtNomCli.getText();
            String Telefono = TxtTelCli.getText();
            String Direccion = TxtDireCli.getText();
            String Ciudad = CbxCiuCli.getSelectedItem().toString();

            usuarios usuario = new usuarios(identificacion, NombreCompleto,
                Fecha, Telefono, Direccion, Ciudad);

            FuncionesSQL funInsAdmin = new FuncionesSQL();
            funInsAdmin.InsercionUsiario(usuario);
            mostrarTabla("");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"error p"+ e);
        }finally{
            limpiarCajasUsuario();
        }
    }//GEN-LAST:event_BtnGuardarClienteActionPerformed

    private void txtFechaAdministradorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFechaAdministradorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaAdministradorActionPerformed

    private void BtnSelecionarAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSelecionarAdminActionPerformed
        try {
            TxtIdeAdm.setEnabled(false);
            SelecionarAdministrador();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"no se pudo selecional la tabla de administradores");
        }
    }//GEN-LAST:event_BtnSelecionarAdminActionPerformed

    private void BtnActualizarAdministradoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnActualizarAdministradoresActionPerformed
        try{
            String Fecha = txtFechaAdministrador.getText();

            int identificacion = Integer.parseInt(TxtIdeAdm.getText());
            String NombreCompleto = txtNomAdm.getText();
            String Contrasena =txtPasswAdm.getText();
            String Telefono = txtTeleAdm.getText();
            String Ciudad = CbxCiudAdmin.getSelectedItem().toString();

            Administrador admin = new Administrador(NombreCompleto, identificacion,
                Fecha, Contrasena, Telefono, Ciudad);

            FuncionesSQL funUpdateAdmin = new FuncionesSQL();
            funUpdateAdmin.UpdateAdminitrador(admin);
            mostrarTabla4("");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"error o"+ e);
        }finally{
            limpiarcajasAdmin();
            TxtIdeAdm.setEnabled(true);
            txtFechaAdministrador.setText("");
        }
    }//GEN-LAST:event_BtnActualizarAdministradoresActionPerformed

    private void BtnEliminarAdministradoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEliminarAdministradoresActionPerformed
        EliminarAdministrador();
    }//GEN-LAST:event_BtnEliminarAdministradoresActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed

        try {

            String dia = Integer.toString(DateAdmintradores.getCalendar().get(Calendar.DAY_OF_MONTH));
            String Mes = Integer.toString(DateAdmintradores.getCalendar().get(Calendar.MONTH));
            String Ano = Integer.toString(DateAdmintradores.getCalendar().get(Calendar.YEAR));
            String Fecha = (dia + " / "+ Mes + " / " + Ano);

            int identificacion = Integer.parseInt(TxtIdeAdm.getText());
            String NombreCompleto = txtNomAdm.getText();
            String Contrasena =txtPasswAdm.getText();
            String Telefono = txtTeleAdm.getText();
            String Ciudad = CbxCiudAdmin.getSelectedItem().toString();

            Administrador admin = new Administrador(NombreCompleto, identificacion,
                Fecha, Contrasena, Telefono, Ciudad);

            FuncionesSQL funInsAdmin = new FuncionesSQL();
            funInsAdmin.InsercionAdmin(admin);
            mostrarTabla4("");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"error o"+ e);
        }finally{
            limpiarcajasAdmin();
        }
    }//GEN-LAST:event_jButton10ActionPerformed

    private void BtnSelecionarEmpleadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSelecionarEmpleadosActionPerformed
        try {
            txtIdenE.setEnabled(false);
            SelecionarEmpleado();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"No se pudo realizar la selecion:");
        }

    }//GEN-LAST:event_BtnSelecionarEmpleadosActionPerformed

    private void TxtContrEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtContrEActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtContrEActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        try {
            String dia = Integer.toString(DateEmpleados.getCalendar().get(Calendar.DAY_OF_MONTH));
            String Mes = Integer.toString(DateEmpleados.getCalendar().get(Calendar.MONTH));
            String Ano = Integer.toString(DateEmpleados.getCalendar().get(Calendar.YEAR));
            String Fecha = (dia + "/"+ Mes + "/" + Ano);

            int Identificacio = Integer.parseInt(txtIdenE.getText());
            String NombreCompleto = TxtNombE.getText();
            String Telefono = TxtteleE.getText();
            String Direccion = TxtDireE.getText();
            String password = TxtContrE.getText();

            Empleados emple = new Empleados (Identificacio, NombreCompleto, Fecha, Telefono, Direccion, password);

            FuncionesSQL funcInEm = new FuncionesSQL();
            funcInEm.InsercionEmple(emple);

            mostrarTabla2(0);
        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"error u"+ e);
        }finally{
            limpiarCajasEmpleados();
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void BtnEliminarEmpleaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEliminarEmpleaActionPerformed
        EliminarEmpleado();
    }//GEN-LAST:event_BtnEliminarEmpleaActionPerformed

    private void BtnActualizarEmpleadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnActualizarEmpleadosActionPerformed
        try {
            String Fecha = txtFechaEmpleado.getText();

            int Identificacio = Integer.parseInt(txtIdenE.getText());
            String NombreCompleto = TxtNombE.getText();
            String Telefono = TxtteleE.getText();
            String Direccion = TxtDireE.getText();
            String password = TxtContrE.getText();

            Empleados emple;
            emple = new Empleados (Identificacio, NombreCompleto, Fecha, Telefono, Direccion, password);

            fun.UpdateEmpleado(emple);

            mostrarTabla2(0);
        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"error u"+ e);
        }finally{
            limpiarCajasEmpleados();
            txtIdenE.setEnabled(true);
            txtFechaEmpleado.setText("");
        }
    }//GEN-LAST:event_BtnActualizarEmpleadosActionPerformed

    public void ConsultarFuncionario(){
        int fila = TbTablafuncionarios.getSelectedRow();
        if (fila >= 0) {
            txtIdeFun.setEnabled(false);
            txtIdeFun.setText(TbTablafuncionarios.getValueAt(fila, 0).toString());
            txtNomFun.setText(TbTablafuncionarios.getValueAt(fila, 1).toString());
            TxtFechaFuncionario.setText(TbTablafuncionarios.getValueAt(fila, 2).toString());
            txtTelFun.setText(TbTablafuncionarios.getValueAt(fila, 3).toString());
            txtPassFun.setText(TbTablafuncionarios.getValueAt(fila, 4).toString());
            CbxRolFunci.setSelectedItem(TbTablafuncionarios.getValueAt(fila, 5).toString());           
            
        } else {
            JOptionPane.showMessageDialog(null, "Selecione una fila" );
        }
    }
    
    public void LimpiarCajasFuncionarios(){
        txtIdeFun.setText("");
        txtTelFun.setText("");
        txtNomFun.setText("");
        txtPassFun.setText("");
        CbxRolFunci.setSelectedIndex(-1);
    }
        
    public void limpiarCajasEmpleados(){
        txtIdenE.setText("");
        TxtContrE.setText("");
        TxtNombE.setText("");
        TxtteleE.setText("");
        TxtDireE.setText("");          
    }
    
    public void limpiarcajasAdmin(){
        TxtIdeAdm.setText("");
            txtNomAdm.setText("");
            txtPasswAdm.setText("");
            CbxCiudAdmin.setSelectedIndex(-1);
            txtTeleAdm.setText("");
    }
    
    public void mostrarTablaEnvios() throws SQLException{
        DefaultTableModel Modelo5 = new DefaultTableModel();
        Modelo5.addColumn("Codigo Envio");
        Modelo5.addColumn("ciudad origen");
        Modelo5.addColumn("ciudad destino");
        Modelo5.addColumn("fecha y hora del envio");
        Modelo5.addColumn("detalles envio");
        Modelo5.addColumn("identificacion Cliente");
        
        this.TbRegistrarEnvios.setModel(Modelo5);
        
        String sql = "SELECT * FROM envios";
            
        BasesDatos con = new BasesDatos();
        conx = con.conectar();
        String fila[] = new String[7];
        try {               
            Statement st = conx.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {                
                fila[0] = rs.getString(1);
                fila[1] = rs.getString(2);
                fila[2] = rs.getString(3);
                fila[3] = rs.getString(4);
                fila[4] = rs.getString(5);
                fila[5] = rs.getString(6);
                
                
                Modelo5.addRow(fila);
            }                         
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error: en llenar la tabla " + e);
        }       
    }
    
    public void limpiarCajasUsuario(){
        TxtIdenCli.setText("");
            txtNomCli.setText("");
            TxtTelCli.setText("");
            CbxCiuCli.setSelectedIndex(-1);
            TxtDireCli.setText("");
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnActualizarAdministradores;
    private javax.swing.JButton BtnActualizarClientes;
    private javax.swing.JButton BtnActualizarEmpleados;
    private javax.swing.JButton BtnActualizarFuncionario;
    private javax.swing.JButton BtnConsultarXCiudad;
    private javax.swing.JButton BtnConsultarXIdentificacion;
    private javax.swing.JButton BtnEliminarAdministradores;
    private javax.swing.JButton BtnEliminarEmplea;
    private javax.swing.JButton BtnEliminarFuncionario;
    private javax.swing.JButton BtnEliminarclientes;
    private javax.swing.JButton BtnGuardarCliente;
    private javax.swing.JButton BtnRegistrarEnvios;
    private javax.swing.JButton BtnSelecionarAdmin;
    private javax.swing.JButton BtnSelecionarClient;
    private javax.swing.JButton BtnSelecionarEmpleados;
    private javax.swing.JButton BtnSelecionarFunci;
    private javax.swing.JComboBox<String> CbxCidadConsultas;
    public static javax.swing.JComboBox<String> CbxCiuCli;
    private javax.swing.JComboBox<String> CbxCiudAdmin;
    private javax.swing.JComboBox<String> CbxCiudadRegistrosEnvios;
    private javax.swing.JComboBox<String> CbxRol2Consultas;
    private javax.swing.JComboBox<String> CbxRolConsultas;
    private javax.swing.JComboBox<String> CbxRolFunci;
    private javax.swing.JComboBox<String> CbxsignarIdentificacion;
    private com.toedter.calendar.JDateChooser DateAdmintradores;
    private com.toedter.calendar.JDateChooser DateClientes;
    private com.toedter.calendar.JDateChooser DateEmpleados;
    private com.toedter.calendar.JDateChooser DateFuncionarios;
    private javax.swing.JLabel ImagenLb;
    private javax.swing.JLabel LbImagen;
    private javax.swing.JLabel LbImagen1;
    private javax.swing.JLabel LbImagen2;
    private javax.swing.JLabel LbImagen3;
    private javax.swing.JTable TbConsultaIdentificacion;
    private javax.swing.JTable TbConsultasCiudad;
    private javax.swing.JTable TbRegistrarEnvios;
    private javax.swing.JTable TbTabla;
    private javax.swing.JTable TbTablaAdministradores;
    private javax.swing.JTable TbTablaEmpleados;
    private javax.swing.JTable TbTablaInformes;
    private javax.swing.JTable TbTablafuncionarios;
    private javax.swing.JTextField TxtCiudadDestino;
    private javax.swing.JTextField TxtCodigoEnvio;
    private javax.swing.JPasswordField TxtContrE;
    private javax.swing.JTextField TxtDetallesEnvio;
    public static javax.swing.JTextField TxtDireCli;
    private javax.swing.JTextField TxtDireE;
    private javax.swing.JTextField TxtFechaFuncionario;
    private javax.swing.JTextField TxtFechaHora;
    public static javax.swing.JTextField TxtFechaUsua;
    private javax.swing.JTextField TxtIdeAdm;
    public static javax.swing.JTextField TxtIdenCli;
    private javax.swing.JTextField TxtIdenConsultas;
    private javax.swing.JTextField TxtNombE;
    public javax.swing.JTextField TxtTelCli;
    private javax.swing.JTextField TxtteleE;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JLabel lbId;
    private javax.swing.JTextField txtFechaAdministrador;
    private javax.swing.JTextField txtFechaEmpleado;
    private javax.swing.JTextField txtIdeFun;
    private javax.swing.JTextField txtIdenE;
    private javax.swing.JTextField txtNomAdm;
    public static javax.swing.JTextField txtNomCli;
    private javax.swing.JTextField txtNomFun;
    private javax.swing.JPasswordField txtPassFun;
    private javax.swing.JPasswordField txtPasswAdm;
    private javax.swing.JTextField txtTelFun;
    private javax.swing.JTextField txtTeleAdm;
    // End of variables declaration//GEN-END:variables
}
