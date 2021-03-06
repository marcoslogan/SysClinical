/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Contato;
import model.Endereco;
import model.Paciente;
import model.Funcionario;
import controller.PacienteController;
import static controller.PacienteController.getInstance;
import java.sql.SQLException;
import utils.ErroConectarException;
import utils.ErroDAOException;
import utils.ErroNegocioException;

/**
 *
 * @author MARCOS
 */
public class FrmCadPaciente extends javax.swing.JFrame {

    private Paciente paciente;
    private Funcionario funcionario;
    private String[] messages;
    private PacienteController controlPaciente;

    public FrmCadPaciente(Funcionario func) {
        initComponents();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.funcionario = func;
        controlPaciente = getInstance();
    }

    private void preencher() {
        paciente = new Paciente();
        paciente.setNome(jTNome.getText().trim());
        Endereco endereco = new Endereco();
        endereco.setRua(jTRua.getText().trim());
        endereco.setCidade(jTCidade.getText().trim());
        endereco.setBairro(jTBairro.getText().trim());
        endereco.setCep(jFCEP.getText().trim());
        endereco.setComplemento(jTComplemento.getText().trim());
        endereco.setNumeroResidencia(jTNumeroResidencia.getText().trim());
        endereco.setUf(jComboUF.getSelectedItem().toString());
        paciente.setEndereco(endereco);
        paciente.setRg(jTRG.getText().trim());
        paciente.setCpf(jFCPF.getText().trim());
        paciente.setLogin(jTLogin.getText().trim());
        paciente.setSenha(new String(jPSenha.getPassword()));
        paciente.setAltura(Double.parseDouble(jTAltura.getText().trim()));
        paciente.setPeso(Double.parseDouble(jTPeso.getText().trim()));
        paciente.setGrupoSanguineo(jTGrupo.getText().trim());
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        try {
            paciente.setDataCadastro(format.parse(format.format(new Date())));
            paciente.setDataNascimento(format.parse(jFDataNascimento.getText().trim()));
        } catch (ParseException ex) {
            Logger.getLogger(FrmCadPaciente.class.getName()).log(Level.SEVERE, null, ex);
        }
        paciente.setSexo(jComboSexo.getSelectedItem().toString());
        paciente.setIdade(Age.calculateAge(jFDataNascimento.getText().trim(), "dd/MM/yyyy"));
        Contato contato = new Contato();
        contato.setTelefone(jFTelefone.getText().trim());
        contato.setCelular(jFCelular.getText().trim());
        contato.setEmail(jTEmail.getText().trim());
        paciente.setContato(contato);
    }

    private void limpar() {
        jTNome.setText("");
        jTRua.setText("");
        jTCidade.setText("");
        jTBairro.setText("");
        jFCEP.setValue(null);
        jTNumeroResidencia.setText("");
        jComboUF.setSelectedItem(0);
        jTComplemento.setText("");
        jTRG.setText("");
        jFCPF.setValue(null);
        jTLogin.setText("");
        jPSenha.setText("");
        jTAltura.setText("");
        jTPeso.setText("");
        jComboSexo.setSelectedItem(0);
        jTGrupo.setText("");
        jFTelefone.setValue(null);
        jFCelular.setValue(null);
        jTEmail.setText("");
        jTNome.requestFocus();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTNome = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTRua = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTCidade = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTBairro = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jFCEP = new javax.swing.JFormattedTextField();
        jLabel8 = new javax.swing.JLabel();
        jTNumeroResidencia = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jComboUF = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jTRG = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jFCPF = new javax.swing.JFormattedTextField();
        jLabel12 = new javax.swing.JLabel();
        jTLogin = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jPSenha = new javax.swing.JPasswordField();
        jLabel14 = new javax.swing.JLabel();
        jTAltura = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jTPeso = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jComboSexo = new javax.swing.JComboBox<>();
        jTGrupo = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jFTelefone = new javax.swing.JFormattedTextField();
        jFCelular = new javax.swing.JFormattedTextField();
        jLabel20 = new javax.swing.JLabel();
        jTEmail = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        jTComplemento = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jFDataNascimento = new javax.swing.JFormattedTextField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setOpacity(0.9F);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Lucida Handwriting", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Dados Cadastrais do Paciente");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 30, 430, -1));

        jLabel3.setFont(new java.awt.Font("Open Sans", 2, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Nome");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 50, -1));

        jTNome.setFont(new java.awt.Font("Lucida Calligraphy", 2, 13)); // NOI18N
        getContentPane().add(jTNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 90, 570, -1));

        jLabel4.setFont(new java.awt.Font("Lucida Handwriting", 2, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Rua");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 40, -1));

        jTRua.setFont(new java.awt.Font("Lucida Calligraphy", 2, 13)); // NOI18N
        getContentPane().add(jTRua, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 120, 570, -1));

        jLabel5.setFont(new java.awt.Font("Lucida Handwriting", 2, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Cidade");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, -1, -1));

        jTCidade.setFont(new java.awt.Font("Lucida Calligraphy", 2, 13)); // NOI18N
        getContentPane().add(jTCidade, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 150, 250, -1));

        jLabel6.setFont(new java.awt.Font("Lucida Handwriting", 2, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Bairro");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 150, 70, -1));

        jTBairro.setFont(new java.awt.Font("Lucida Calligraphy", 2, 13)); // NOI18N
        getContentPane().add(jTBairro, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 150, 250, -1));

        jLabel7.setFont(new java.awt.Font("Lucida Handwriting", 2, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("CEP");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 40, -1));

        try {
            jFCEP.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#####-###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFCEP.setFont(new java.awt.Font("Lucida Calligraphy", 2, 13)); // NOI18N
        getContentPane().add(jFCEP, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 180, 130, -1));

        jLabel8.setFont(new java.awt.Font("Lucida Handwriting", 2, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Nº de Residência");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 180, 130, -1));

        jTNumeroResidencia.setFont(new java.awt.Font("Lucida Calligraphy", 2, 13)); // NOI18N
        getContentPane().add(jTNumeroResidencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 180, 50, -1));

        jLabel9.setFont(new java.awt.Font("Lucida Handwriting", 2, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("UF");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 180, 30, -1));

        jComboUF.setFont(new java.awt.Font("Lucida Calligraphy", 2, 13)); // NOI18N
        jComboUF.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Acre", "Alagoas", "Amapá", "Bahia", "Ceará", "Distrito Federal", "Espírito Santo", "Goiás", "Maranhão", "Mato Grosso", "Mato Grosso do Sul", "Minas Gerais", "Pará", "Paraná", "Pernambuco", "Piauí", "Rio de Janeiro", "Rio Grande do Norte", "Rio Grande do Sul", "Rondônia", "Roraima", "Santa Catarina", "Sergipe", "Tocantins" }));
        getContentPane().add(jComboUF, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 180, 210, -1));

        jLabel10.setFont(new java.awt.Font("Lucida Handwriting", 2, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("RG");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, 30, -1));

        jTRG.setFont(new java.awt.Font("Lucida Calligraphy", 2, 13)); // NOI18N
        getContentPane().add(jTRG, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 240, 130, -1));

        jLabel11.setFont(new java.awt.Font("Lucida Handwriting", 2, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("CPF");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 240, 40, -1));

        try {
            jFCPF.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFCPF.setFont(new java.awt.Font("Lucida Calligraphy", 2, 13)); // NOI18N
        getContentPane().add(jFCPF, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 240, 140, -1));

        jLabel12.setFont(new java.awt.Font("Lucida Handwriting", 2, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Login");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 240, 60, -1));

        jTLogin.setFont(new java.awt.Font("Lucida Calligraphy", 2, 13)); // NOI18N
        getContentPane().add(jTLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 240, 180, -1));

        jLabel13.setFont(new java.awt.Font("Lucida Handwriting", 2, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Senha");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, 60, -1));

        jPSenha.setFont(new java.awt.Font("Lucida Calligraphy", 2, 13)); // NOI18N
        getContentPane().add(jPSenha, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 270, 130, -1));

        jLabel14.setFont(new java.awt.Font("Lucida Handwriting", 2, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Altura");
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 270, 60, -1));

        jTAltura.setFont(new java.awt.Font("Lucida Calligraphy", 2, 13)); // NOI18N
        getContentPane().add(jTAltura, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 270, 80, -1));

        jLabel15.setFont(new java.awt.Font("Lucida Handwriting", 2, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Peso");
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 270, 40, -1));

        jTPeso.setFont(new java.awt.Font("Lucida Calligraphy", 2, 13)); // NOI18N
        getContentPane().add(jTPeso, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 270, 70, -1));

        jLabel16.setFont(new java.awt.Font("Lucida Handwriting", 2, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Grupo Sanguíneo");
        getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, -1, -1));

        jLabel17.setFont(new java.awt.Font("Lucida Handwriting", 2, 12)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Sexo");
        getContentPane().add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 270, 40, -1));

        jComboSexo.setFont(new java.awt.Font("Lucida Calligraphy", 2, 13)); // NOI18N
        jComboSexo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Feminino", "Masculino", "Trans", "Homossexual", "Outros" }));
        getContentPane().add(jComboSexo, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 270, 130, -1));

        jTGrupo.setFont(new java.awt.Font("Lucida Calligraphy", 2, 13)); // NOI18N
        getContentPane().add(jTGrupo, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 300, 130, -1));

        jLabel18.setFont(new java.awt.Font("Lucida Handwriting", 2, 12)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Telefone");
        getContentPane().add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 300, 80, -1));

        jLabel19.setFont(new java.awt.Font("Lucida Handwriting", 2, 12)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Celular");
        getContentPane().add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 300, 70, -1));

        try {
            jFTelefone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(###)####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFTelefone.setFont(new java.awt.Font("Lucida Calligraphy", 2, 13)); // NOI18N
        getContentPane().add(jFTelefone, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 300, 110, -1));

        try {
            jFCelular.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(###)#####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFCelular.setFont(new java.awt.Font("Lucida Calligraphy", 2, 13)); // NOI18N
        getContentPane().add(jFCelular, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 300, 110, -1));

        jLabel20.setFont(new java.awt.Font("Lucida Handwriting", 2, 12)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("E-Mail");
        getContentPane().add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 330, 60, -1));

        jTEmail.setFont(new java.awt.Font("Lucida Calligraphy", 2, 13)); // NOI18N
        getContentPane().add(jTEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 330, 570, -1));

        jLabel21.setFont(new java.awt.Font("Lucida Handwriting", 2, 36)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("X");
        jLabel21.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel21MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 0, 40, -1));

        jButton1.setFont(new java.awt.Font("Lucida Handwriting", 2, 12)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/if_db_add_3209 (1).png"))); // NOI18N
        jButton1.setText("Cadastrar");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 400, 140, 50));

        jButton2.setFont(new java.awt.Font("Lucida Handwriting", 2, 12)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/if_edit-clear_23227.png"))); // NOI18N
        jButton2.setText("Limpar");
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 400, 140, 50));

        jLabel22.setFont(new java.awt.Font("Lucida Handwriting", 2, 12)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("Complemento");
        getContentPane().add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 110, 30));

        jTComplemento.setFont(new java.awt.Font("Lucida Calligraphy", 2, 13)); // NOI18N
        getContentPane().add(jTComplemento, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 210, 540, -1));

        jLabel23.setFont(new java.awt.Font("Lucida Handwriting", 2, 12)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("Data de Nascimento");
        getContentPane().add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 360, -1, -1));

        try {
            jFDataNascimento.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFDataNascimento.setFont(new java.awt.Font("Lucida Calligraphy", 2, 13)); // NOI18N
        getContentPane().add(jFDataNascimento, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 360, 140, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/back_cad_paciente.jpg"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 690, 510));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel21MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel21MouseClicked
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jLabel21MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        limpar();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if (jTNome.getText().trim().equals("")) {
            messages = MessageView.requiredField("Nome");
            JOptionPane.showMessageDialog(rootPane, messages[0], messages[1], JOptionPane.WARNING_MESSAGE);
            jTNome.requestFocus();
        } else if (jTRua.getText().trim().equals("")) {
            messages = MessageView.requiredField("Rua");
            JOptionPane.showMessageDialog(rootPane, messages[0], messages[1], JOptionPane.WARNING_MESSAGE);
            jTNome.requestFocus();
        } else if (jTCidade.getText().trim().equals("")) {
            messages = MessageView.requiredField("Nome");
            JOptionPane.showMessageDialog(rootPane, messages[0], messages[1], JOptionPane.WARNING_MESSAGE);
            jTNome.requestFocus();
        } else if (jTBairro.getText().trim().equals("")) {
            messages = MessageView.requiredField("Bairro");
            JOptionPane.showMessageDialog(rootPane, messages[0], messages[1], JOptionPane.WARNING_MESSAGE);
            jTNome.requestFocus();
        } else if (!jFCEP.isEditValid()) {
            messages = MessageView.requiredField("CEP");
            JOptionPane.showMessageDialog(rootPane, messages[0], messages[1], JOptionPane.WARNING_MESSAGE);
            jTNome.requestFocus();
        } else if (jTNumeroResidencia.getText().trim().equals("")) {
            messages = MessageView.requiredField("Nº de Residência");
            JOptionPane.showMessageDialog(rootPane, messages[0], messages[1], JOptionPane.WARNING_MESSAGE);
            jTNome.requestFocus();
        } else if (jTComplemento.getText().trim().equals("")) {
            messages = MessageView.requiredField("Complemento");
            JOptionPane.showMessageDialog(rootPane, messages[0], messages[1], JOptionPane.WARNING_MESSAGE);
            jTNome.requestFocus();
        } else if (jTRG.getText().trim().equals("")) {
            messages = MessageView.requiredField("RG");
            JOptionPane.showMessageDialog(rootPane, messages[0], messages[1], JOptionPane.WARNING_MESSAGE);
            jTNome.requestFocus();
        } else if (!jFCPF.isEditValid()) {
            messages = MessageView.requiredField("CPF");
            JOptionPane.showMessageDialog(rootPane, messages[0], messages[1], JOptionPane.WARNING_MESSAGE);
            jTNome.requestFocus();
        } else if (jTLogin.getText().trim().equals("")) {
            messages = MessageView.requiredField("Login");
            JOptionPane.showMessageDialog(rootPane, messages[0], messages[1], JOptionPane.WARNING_MESSAGE);
            jTNome.requestFocus();
        } else {
            String senha = new String(jPSenha.getPassword());
            if (senha.trim().equals("")) {
                messages = MessageView.requiredField("Senha");
                JOptionPane.showMessageDialog(rootPane, messages[0], messages[1], JOptionPane.WARNING_MESSAGE);
                jTNome.requestFocus();
            } else if (jTAltura.getText().trim().equals("")) {
                messages = MessageView.requiredField("Altura");
                JOptionPane.showMessageDialog(rootPane, messages[0], messages[1], JOptionPane.WARNING_MESSAGE);
                jTNome.requestFocus();
            } else if (jTPeso.getText().trim().equals("")) {
                messages = MessageView.requiredField("Peso");
                JOptionPane.showMessageDialog(rootPane, messages[0], messages[1], JOptionPane.WARNING_MESSAGE);
                jTNome.requestFocus();
            } else if (jTGrupo.getText().trim().equals("")) {
                messages = MessageView.requiredField("Grupo Sanguìneo");
                JOptionPane.showMessageDialog(rootPane, messages[0], messages[1], JOptionPane.WARNING_MESSAGE);
                jTGrupo.requestFocus();
            } else if (!jFCelular.isEditValid()) {
                messages = MessageView.requiredField("Celular");
                JOptionPane.showMessageDialog(rootPane, messages[0], messages[1], JOptionPane.WARNING_MESSAGE);
                jFCelular.requestFocus();
            } else if (!jFDataNascimento.isEditValid()) {
                messages = MessageView.requiredField("Data de Nascimento");
                JOptionPane.showMessageDialog(rootPane, messages[0], messages[1], JOptionPane.WARNING_MESSAGE);
                jFDataNascimento.requestFocus();
            }else{
                preencher();
                try {
                    controlPaciente.cadastrarPaciente(paciente);
                    JOptionPane.showMessageDialog(rootPane, "Os dados " + (paciente.getSexo().equals("Masculino")? "do Paciente " : "da Paciente ")+ paciente.getNome() + " foram cadastrados com sucesso no sistema.", (paciente.getSexo().equals("Masculino")? "Paciente cadastrado " : "Paciente cadastrada ")+"com sucesso no sistema!", JOptionPane.INFORMATION_MESSAGE);
                    limpar();
                } catch (ErroDAOException | ErroNegocioException | ErroConectarException | SQLException ex) {
                    JOptionPane.showMessageDialog(rootPane, ex.getMessage(), (ex instanceof ErroDAOException ? "" : (ex instanceof ErroNegocioException ? "" : "")), JOptionPane.ERROR_MESSAGE);
                    limpar();
                }
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboSexo;
    private javax.swing.JComboBox<String> jComboUF;
    private javax.swing.JFormattedTextField jFCEP;
    private javax.swing.JFormattedTextField jFCPF;
    private javax.swing.JFormattedTextField jFCelular;
    private javax.swing.JFormattedTextField jFDataNascimento;
    private javax.swing.JFormattedTextField jFTelefone;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPasswordField jPSenha;
    private javax.swing.JTextField jTAltura;
    private javax.swing.JTextField jTBairro;
    private javax.swing.JTextField jTCidade;
    private javax.swing.JTextField jTComplemento;
    private javax.swing.JTextField jTEmail;
    private javax.swing.JTextField jTGrupo;
    private javax.swing.JTextField jTLogin;
    private javax.swing.JTextField jTNome;
    private javax.swing.JTextField jTNumeroResidencia;
    private javax.swing.JTextField jTPeso;
    private javax.swing.JTextField jTRG;
    private javax.swing.JTextField jTRua;
    // End of variables declaration//GEN-END:variables
}
