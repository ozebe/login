/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import control.ConnectionFactory;
import control.INI;
import control.Usuario;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author wesley.santos
 */
public class LoginView extends javax.swing.JFrame {

    private static Connection connection;
    private static ConnectionFactory fabrica = new ConnectionFactory();
    //pega o caminho da base no arquivo .ini
    public static INI db;
    public static INI user;
    public static INI password;
    private Usuario userLogado;

    private final String CHAVE_PRINCIPAL = "db-config";
    private final String CHAVE_LOCAL_BASE = "local";
    private final String CHAVE_USUARIO = "user";
    private final String CHAVE_SENHA = "password";

    /**
     * Creates new form LoginView
     */
    public LoginView() {
        db = new INI(CHAVE_PRINCIPAL, CHAVE_LOCAL_BASE);
        user = new INI(CHAVE_PRINCIPAL, CHAVE_USUARIO);
        password = new INI(CHAVE_PRINCIPAL, CHAVE_SENHA);
        userLogado = null;
        initComponents();
        // verificaLogin();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        usuarioField = new javax.swing.JTextField();
        senhaField = new javax.swing.JPasswordField();
        loginBtn = new javax.swing.JButton();
        imgLogin = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Login");
        setMinimumSize(null);
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Usuário:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Senha:");

        usuarioField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                usuarioFieldKeyPressed(evt);
            }
        });

        senhaField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                senhaFieldKeyPressed(evt);
            }
        });

        loginBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/btn.png"))); // NOI18N
        loginBtn.setText("Login");
        loginBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginBtnActionPerformed(evt);
            }
        });

        imgLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/login.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addComponent(loginBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(usuarioField, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(26, 26, 26)
                                .addComponent(senhaField, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(imgLogin)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel1))
                            .addComponent(usuarioField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel2))
                            .addComponent(senhaField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(loginBtn))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(imgLogin)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void loginBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginBtnActionPerformed
        try {
            verificaLogin();
        } catch (ClassNotFoundException | SQLException | IOException | NoSuchAlgorithmException ex) {
            JOptionPane.showMessageDialog(null, "Não foi possível carregar!\n" + ex, "Erro", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(LoginView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_loginBtnActionPerformed

    private void usuarioFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_usuarioFieldKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            senhaField.requestFocus();
        }
    }//GEN-LAST:event_usuarioFieldKeyPressed

    private void senhaFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_senhaFieldKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            try {
                verificaLogin();
            } catch (ClassNotFoundException | SQLException | IOException | NoSuchAlgorithmException ex) {
                JOptionPane.showMessageDialog(null, "Não foi possível carregar!\n" + ex, "Erro", JOptionPane.ERROR_MESSAGE);
                Logger.getLogger(LoginView.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_senhaFieldKeyPressed

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
            java.util.logging.Logger.getLogger(LoginView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                final LoginView telaLogin = new LoginView();
                telaLogin.setLocationRelativeTo(null);
                telaLogin.setVisible(true);
                telaLogin.requestFocus();
            }
        });
    }

    private void verificaLogin() throws ClassNotFoundException, SQLException, IOException, NoSuchAlgorithmException {
        connection = fabrica.getConnection(db.getDir(), user.getDir(), password.getDir());

        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM usuarios WHERE usuarios.usuario = ?");
        System.out.println("" + usuarioField.getText());
        stmt.setString(1, usuarioField.getText());
        ResultSet resultSet = stmt.executeQuery();
        Usuario u = new Usuario();
        while (resultSet.next()) {
            u.setAtivo(resultSet.getBoolean("ativo"));
            u.setBloqueado(resultSet.getBoolean("bloqueado"));
            u.setCriado(formataTimestamp(resultSet.getTimestamp("criado")));
            u.setEditado(formataTimestamp(resultSet.getTimestamp("editado")));
            u.setId(resultSet.getInt("id"));
            u.setNivel(resultSet.getInt("nivel"));
            u.setNome(resultSet.getString("nome"));
            u.setSenha(resultSet.getString("senha"));
            u.setTentativas(resultSet.getInt("tentativas"));
            u.setUsuario(resultSet.getString("usuario"));
        }
        //verifica se a senha do usuário carregado anteriormente é igual a do senhaForm
        String senha = new String(senhaField.getPassword()).trim();
        if (MD5(senha).equals(u.getSenha())) {
            if (u.isBloqueado()) {
                JOptionPane.showMessageDialog(null, "Usuário encontra-se bloqueado!\n", "Erro", JOptionPane.ERROR_MESSAGE);
            } else if (u.isAtivo()) {
                String sql = "UPDATE usuarios\n"
                        + "SET tentativas = 0 \n"
                        + "WHERE  usuarios.usuario = ?";
                PreparedStatement pstmt = connection.prepareStatement(sql);
                pstmt.setString(1, usuarioField.getText());
                pstmt.executeUpdate();
                resultSet.close();
                pstmt.close();
                stmt.close();
                connection.close();
                this.userLogado = u;
                System.out.println("logado!");

                final MainView main = new MainView(this.userLogado);
                main.setLocationRelativeTo(null);
                main.setVisible(true);
                this.dispose();

            } else if (!u.isAtivo()) {
                JOptionPane.showMessageDialog(null, "Usuário encontra-se inativo!\n", "Erro", JOptionPane.ERROR_MESSAGE);

            } else {
                JOptionPane.showMessageDialog(null, "Usuário não encontrado!\n", "Erro", JOptionPane.ERROR_MESSAGE);
            }

        } else {
            //verifica se ta bloqueado
            if (u.isBloqueado()) {
                JOptionPane.showMessageDialog(null, "Usuário encontra-se bloqueado!\n", "Erro", JOptionPane.ERROR_MESSAGE);

            } else if (u.isAtivo()) {
                PreparedStatement stmt2 = connection.prepareStatement("UPDATE usuarios SET "
                        + "tentativas = 1+(SELECT tentativas FROM usuarios WHERE usuarios.usuario = ?) WHERE usuarios.usuario = ?");
                System.out.println("" + usuarioField.getText());
                stmt2.setString(1, usuarioField.getText());
                stmt2.setString(2, usuarioField.getText());
                stmt2.executeUpdate();
                stmt2.close();
                connection.close();
                JOptionPane.showMessageDialog(null, "Usuário ou senha incorreta!\n", "Erro", JOptionPane.ERROR_MESSAGE);
            } else if (!u.isAtivo()) {
                JOptionPane.showMessageDialog(null, "Usuário encontra-se inativo!\n", "Erro", JOptionPane.ERROR_MESSAGE);

            } else {
                JOptionPane.showMessageDialog(null, "Usuário não encontrado!\n", "Erro", JOptionPane.ERROR_MESSAGE);
            }

        }
    }

    private static String formataTimestamp(Timestamp t) {
        if (t != null) {
            SimpleDateFormat formatador = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            return formatador.format(t);
        } else {
            return "";
        }

    }

    private static String MD5(String s) throws NoSuchAlgorithmException {
        MessageDigest m = MessageDigest.getInstance("MD5");
        m.update(s.getBytes(), 0, s.length());
        return new BigInteger(1, m.digest()).toString(16);
    }

    public Usuario getUserLogado() {
        return userLogado;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel imgLogin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JButton loginBtn;
    private javax.swing.JPasswordField senhaField;
    private javax.swing.JTextField usuarioField;
    // End of variables declaration//GEN-END:variables
}
