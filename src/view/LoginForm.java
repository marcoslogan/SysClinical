/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import com.sun.glass.events.KeyEvent;
import model.Funcionario;
import controller.FuncionarioController;
import controller.MedicoController;
import controller.EnfermeiroController;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import model.Enfermeiro;
import model.Medico;
import utils.ErroConectarException;
import utils.ErroDAOException;
import utils.ErroNegocioException;

/**
 *
 * @author MARCOS
 */
public class LoginForm extends javax.swing.JFrame {

    private FuncionarioController controlFuncionario;
    private MedicoController controlMedico;
    private EnfermeiroController controlEnfermeiro;

    /**
     * Creates new form LoginForm
     */
    public LoginForm() {
        initComponents();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        controlFuncionario = FuncionarioController.getCurrentInstance();
        controlMedico = MedicoController.getInstance();
        controlEnfermeiro = EnfermeiroController.getInstance();
    }

    private void limpar() {
        jTLogin.setText("");
        jPSenha.setText("");
        jTLogin.requestFocus();
    }

    private void logar() {
        if (jTLogin.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "O Campo Login é obrigatório portanto não pode ficar vazio.", "Campo Obrigatório", JOptionPane.WARNING_MESSAGE);
            jTLogin.requestFocus();
        } else {
            String senha = new String(jPSenha.getPassword());
            if (senha.trim().equals("")) {
                JOptionPane.showMessageDialog(rootPane, "O Campo Senha é obrigatório portanto não pode ficar vazio.", "Campo Obrigatório", JOptionPane.WARNING_MESSAGE);
                jPSenha.requestFocus();
            } else {
                String tipoUsuario = jComboTipoUsuario.getSelectedItem().toString();
                switch (tipoUsuario) {
                    case "Atendente":
                    case "Administrador":
                        try {
                            Funcionario funcionario = controlFuncionario.logarFuncionario(jTLogin.getText().trim(), senha.trim());
                            switch (funcionario.getFuncao()) {
                                case "Administrador":
                                    if (tipoUsuario.equals("Administrador")) {
                                        FrmAdmin form = new FrmAdmin(funcionario);
                                        form.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                                        this.dispose();
                                    } else {
                                        JOptionPane.showMessageDialog(rootPane, "Prezado Usuário você não tem nível de acesso suficiente para acessar esta parte do sistema!", "Dados Inválidos!", JOptionPane.WARNING_MESSAGE);
                                        limpar();
                                    }
                                    break;
                                case "Atendente":
                                    FrmAtendente formAtendente = new FrmAtendente(funcionario);
                                    formAtendente.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                                    this.dispose();
                                    break;
                                default:
                                    JOptionPane.showMessageDialog(rootPane, "Não foi encontrado nenhuma função, portanto não tem acesso a nenhum módulo do sistema.");
                                    System.exit(0);
                                    break;
                            }
                        } catch (ErroDAOException | ErroConectarException | ErroNegocioException | SQLException ex) {
                            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), (ex instanceof ErroDAOException ? "Erro de Query SQL!" : (ex instanceof ErroNegocioException ? "Erro de Validação de Dados!" : "Erro ao conectar com o banco de dados!")), JOptionPane.ERROR_MESSAGE);
                            limpar();
                        }
                        break;
                    case "Médico":
                        try {
                            Medico medico = controlMedico.logarMedico(jTLogin.getText().trim(), senha.trim());
                            if (medico != null) {
                                FrmMedico formMedico = new FrmMedico(medico);
                                formMedico.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                                this.dispose();
                            }
                        } catch (ErroDAOException | ErroConectarException | ErroNegocioException | SQLException ex) {
                            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), (ex instanceof ErroDAOException ? "Erro de Query SQL!" : (ex instanceof ErroNegocioException ? "Erro de Validação de Dados!" : "Erro ao conectar com o banco de dados!")), JOptionPane.ERROR_MESSAGE);
                            limpar();
                        }

                        break;
                    case "Enfermeiro":
                        try {
                            Enfermeiro enfermeiro = controlEnfermeiro.logarEnfermeiro(jTLogin.getText().trim(), senha.trim());
                            if (enfermeiro != null) {
                                FrmEnfermeiro formEnfermeiro = new FrmEnfermeiro();
                                formEnfermeiro.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                                this.dispose();
                            }
                        } catch (ErroDAOException | ErroConectarException | ErroNegocioException | SQLException ex) {
                            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), (ex instanceof ErroDAOException ? "Erro de Query SQL!" : (ex instanceof ErroNegocioException ? "Erro de Validação de Dados!" : "Erro ao conectar com o banco de dados!")), JOptionPane.ERROR_MESSAGE);
                            limpar();
                        }

                        break;
                }
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTLogin = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jPSenha = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jComboTipoUsuario = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setOpacity(0.9F);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));

        jPanel2.setBackground(new java.awt.Color(235, 174, 34));

        jLabel1.setFont(new java.awt.Font("Open Sans SemiBold", 3, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Área de Acesso");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(202, 202, 202)
                .addComponent(jLabel1)
                .addContainerGap(221, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel1)
                .addContainerGap(39, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Lucida Handwriting", 2, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(235, 174, 34));
        jLabel2.setText("Login");

        jTLogin.setBackground(new java.awt.Color(51, 51, 51));
        jTLogin.setFont(new java.awt.Font("Lucida Calligraphy", 2, 13)); // NOI18N
        jTLogin.setForeground(new java.awt.Color(235, 174, 34));

        jLabel3.setFont(new java.awt.Font("Lucida Handwriting", 2, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(235, 174, 34));
        jLabel3.setText("Senha");

        jPSenha.setBackground(new java.awt.Color(51, 51, 51));
        jPSenha.setFont(new java.awt.Font("Lucida Calligraphy", 2, 13)); // NOI18N
        jPSenha.setForeground(new java.awt.Color(235, 174, 34));
        jPSenha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPSenhaKeyPressed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(204, 151, 28));
        jButton1.setFont(new java.awt.Font("Lucida Handwriting", 2, 12)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/if_Login_73221.png"))); // NOI18N
        jButton1.setText("Logar");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(204, 151, 28));
        jButton2.setFont(new java.awt.Font("Lucida Handwriting", 2, 12)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/if_Cancel_27836.png"))); // NOI18N
        jButton2.setText("Cancelar");
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Lucida Handwriting", 2, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(235, 174, 34));
        jLabel4.setText("Tipo de Usuário");

        jComboTipoUsuario.setBackground(new java.awt.Color(51, 51, 51));
        jComboTipoUsuario.setFont(new java.awt.Font("Lucida Calligraphy", 2, 13)); // NOI18N
        jComboTipoUsuario.setForeground(new java.awt.Color(235, 174, 34));
        jComboTipoUsuario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Administrador", "Atendente", "Médico", "Enfermeiro" }));
        jComboTipoUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboTipoUsuarioActionPerformed(evt);
            }
        });

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/login_user.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(237, 237, 237)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(2, 2, 2)
                            .addComponent(jComboTipoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jPSenha)
                                .addComponent(jTLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(12, 12, 12)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jPSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jComboTipoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton1, jButton2});

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 790, 670));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jComboTipoUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboTipoUsuarioActionPerformed
        // TODO add your handling code here:
        if (jComboTipoUsuario.isPopupVisible()) {
            String senha = new String(jPSenha.getPassword());
            if(!jTLogin.getText().trim().equals("") && !senha.trim().equals("")){
                jTLogin.setText("");
                jPSenha.setText("");
            }
            jTLogin.requestFocus();
        }
    }//GEN-LAST:event_jComboTipoUsuarioActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        logar();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jPSenhaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPSenhaKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            logar();
        }
    }//GEN-LAST:event_jPSenhaKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboTipoUsuario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPasswordField jPSenha;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField jTLogin;
    // End of variables declaration//GEN-END:variables
}
