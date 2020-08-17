
package proyecto_a.Interfaz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import proyecto_a.BasesDatos.BasesDatos;

/**
 *
 * @author jonat
 */
public class Login extends javax.swing.JFrame {

   
    public Login()  {
        initComponents();
        this.setLocationRelativeTo(null);
        
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        CbxUsuarios = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        Jpassword = new javax.swing.JPasswordField();
        BtnEntrar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        CbxRol = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\jonat\\OneDrive\\Documentos\\NetBeansProjects\\Proyecto_A\\src\\proyecto_a\\imagenes\\if_JD-06_2625478.png")); // NOI18N
        jLabel1.setToolTipText("");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 3, 16)); // NOI18N
        jLabel2.setText("EMPRESA DE TRANSPORTE");
        jLabel2.setToolTipText("");

        jLabel3.setFont(new java.awt.Font("Vivaldi", 3, 24)); // NOI18N
        jLabel3.setText("Soluciones Integradas S.A");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel4.setText("Usuario");

        CbxUsuarios.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        CbxUsuarios.setToolTipText("");
        CbxUsuarios.setActionCommand("CbxUsuario");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel5.setText("Contraseña");
        jLabel5.setToolTipText("");

        Jpassword.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        Jpassword.setToolTipText("");
        Jpassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                JpasswordKeyTyped(evt);
            }
        });

        BtnEntrar.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        BtnEntrar.setText("Entrar");
        BtnEntrar.setToolTipText("");
        BtnEntrar.setName("BtnEntrar"); // NOI18N
        BtnEntrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEntrarActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel6.setText("Rol");

        CbxRol.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        CbxRol.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Selecionar--", "Administrador", "Empleado" }));
        CbxRol.setToolTipText("");
        CbxRol.setActionCommand("CbxUsuario");
        CbxRol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CbxRolActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(63, 63, 63)
                                .addComponent(jLabel1))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(jLabel2))
                            .addComponent(jLabel3)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(103, 103, 103)
                        .addComponent(BtnEntrar))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(CbxUsuarios, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Jpassword, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
                            .addComponent(CbxRol, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(CbxRol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(CbxUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Jpassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(BtnEntrar)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnEntrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEntrarActionPerformed
        
        String usuario = CbxUsuarios.getSelectedItem().toString();
        String pass = Jpassword.getText();
        
        Connection Con = null;
        PreparedStatement ppt = null;
        BasesDatos con = new BasesDatos();
        try {
            Con = con.conectar();
            if (CbxRol.getSelectedItem().equals("Administrador")) {
                ppt = Con.prepareStatement("SELECT * FROM administrador WHERE nombre_completo = ? AND contrasena = ?");
                ppt.setString(1, usuario);
                ppt.setString(2, pass);
                ResultSet rs = ppt.executeQuery();
                boolean val = false;
                while (rs.next()) {                    
                    Principal Vp = new Principal();
                    Vp.setVisible(true);
                    val = true;
                    break;
                }
                if (val == false) {
                    JOptionPane.showMessageDialog(null, "Contraseña incorrecta");
                    Jpassword.setText("");
                }
            }
            if (CbxRol.getSelectedItem().equals("Empleado")) {
                ppt = Con.prepareStatement("SELECT * FROM EMPLEADOS WHERE nombre_completo = ? AND contrasena = ?");
                ppt.setString(1, usuario);
                ppt.setString(2, pass);
                ResultSet rs = ppt.executeQuery();
                boolean val = false;
                while (rs.next()) {                    
                    Principal Vp = new Principal();
                    Vp.setVisible(true);       
                    val = true;
                    break;
                }
                if (val == false) {
                    JOptionPane.showMessageDialog(null, "Contraseña incorrecta");
                    Jpassword.setText("");
                }
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Conexion a datos fallo");
        }
        
        
        
    }//GEN-LAST:event_BtnEntrarActionPerformed

    private void CbxRolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CbxRolActionPerformed
        Connection Con = null;
        PreparedStatement ppt = null;
        BasesDatos con = new BasesDatos();
        try {
            Con = con.conectar();
            if (CbxRol.getSelectedItem().equals("Administrador")) {
                CbxUsuarios.removeAllItems();
                ppt = Con.prepareStatement("SELECT nombre_completo FROM administrador");
                ResultSet rs = ppt.executeQuery();
                while (rs.next()) {                    
                    String a = rs.getString("nombre_completo");
                    CbxUsuarios.addItem(a);
                    CbxUsuarios.setSelectedIndex(-1);
                }               
            }
            if (CbxRol.getSelectedItem().equals("Empleado")) {
                CbxUsuarios.removeAllItems();
                ppt = Con.prepareStatement("SELECT nombre_completo FROM EMPLEADOS");
                ResultSet rs = ppt.executeQuery();
                while (rs.next()) {                    
                    String a = rs.getString("nombre_completo");
                    CbxUsuarios.addItem(a);
                    CbxUsuarios.setSelectedIndex(-1);
                }                
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Error en la conexion de la bases de datos");
        }
    }//GEN-LAST:event_CbxRolActionPerformed

    private void JpasswordKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JpasswordKeyTyped
        if ((int)evt.getKeyChar()>32 && (int)evt.getKeyChar()<=47
                ||(int)evt.getKeyChar()>=58 && (int)evt.getKeyChar()<=64
                ||(int)evt.getKeyChar()>=91 && (int)evt.getKeyChar()<=96
                ||(int)evt.getKeyChar()>=123 && (int)evt.getKeyChar()<=255){
            JOptionPane.showMessageDialog(null, "no se permite los caracteres $,%,&,/,*,/,-,ñ");
            Jpassword.setText("");
        }
    }//GEN-LAST:event_JpasswordKeyTyped

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnEntrar;
    private javax.swing.JComboBox<String> CbxRol;
    private javax.swing.JComboBox<String> CbxUsuarios;
    private javax.swing.JPasswordField Jpassword;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    // End of variables declaration//GEN-END:variables
}
