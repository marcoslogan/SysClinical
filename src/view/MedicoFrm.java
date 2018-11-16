/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import model.Funcionario;
import model.Medico;
import controller.MedicoController;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Contato;
import model.Endereco;
import utils.ErroConectarException;
import utils.ErroDAOException;
import utils.ErroNegocioException;

/**
 *
 * @author MARCOS
 */
public class MedicoFrm extends javax.swing.JFrame {

    private Medico medico;
    private MedicoController controlMedico;
    private DefaultTableModel modelTabela;
    private DefaultComboBoxModel modelUF;
    private DefaultComboBoxModel modelStatus;
    private DefaultComboBoxModel modelFuncao;
    private DefaultComboBoxModel modelSexo;
    private int linhaSelecionada = -1;
    private List<Medico> medicos;
    private SimpleDateFormat formatMedico = new SimpleDateFormat("dd/MM/yyyy");
    private String[] messages;

    /**
     * Creates new form MedicoFrm
     */
    public MedicoFrm() {
        initComponents();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        controlMedico = MedicoController.getInstance();
        modelTabela = new DefaultTableModel();
        modelUF = new DefaultComboBoxModel();
        modelStatus = new DefaultComboBoxModel();
        modelFuncao = new DefaultComboBoxModel();
        modelSexo = new DefaultComboBoxModel();
        modelTabela.setColumnIdentifiers(new String[]{"Código", "Nome", "Data Nasc.", "CRM"});
    }

    private void listar(String consulta, String campo) {
        try {
            while (modelTabela.getRowCount() > 0) {
                modelTabela.removeRow(0);
            }
            jTabelaMedico.setModel(modelTabela);
            medicos = controlMedico.listarMedicos(consulta, campo);
            medicos.forEach((med) -> {
                modelTabela.addRow(new String[]{String.valueOf(med.getCodigo()), med.getNome(), formatMedico.format(med.getDataNascimento()), med.getCrm()});
            });
            jTabelaMedico.setModel(modelTabela);
        } catch (ErroDAOException | ErroConectarException | ErroNegocioException | SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), (ex instanceof ErroDAOException ? "Erro de Query SQL!" : (ex instanceof ErroNegocioException ? "Erro de Validação de Dados!" : "Erro ao tentar conectar com o banco de dados!")), JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpar() {
        jTNome.setText("");
        jTRua.setText("");
        jTCidade.setText("");
        jTBairro.setText("");
        jFCEP.setValue(null);
        jTNumeroResidencia.setText("");
        modelUF.removeAllElements();
        modelUF.addElement("UF");
        jComboUF.setModel(modelUF);
        jComboUF.setSelectedItem(0);
        jTRG.setText("");
        jFCPF.setValue(null);
        jTLogin.setText("");
        jPSenha.setText("");
        jFDataNascimento.setValue(null);
        jFDataAdmissao.setValue(null);
        modelSexo.removeAllElements();
        modelSexo.addElement("Sexo do Médico");
        jComboSexo.setModel(modelSexo);
        jComboSexo.setSelectedItem(0);
        jTIdade.setText("");
        jFCRM.setValue(null);
        jTSalario.setText("");
        modelFuncao.removeAllElements();
        modelFuncao.addElement("Função");
        jComboFuncao.setModel(modelFuncao);
        jComboFuncao.setSelectedItem(0);
        jFTelefone.setValue(null);
        jFCelular.setValue(null);
        jTEmail.setText("");
    }

    private void limparTabela() {
        while (modelTabela.getRowCount() > 0) {
            modelTabela.removeRow(0);
        }

        jTabelaMedico.setModel(modelTabela);
    }

    private void preencher() {
        Medico m = medicos.get(linhaSelecionada);
        jTNome.setText(m.getNome());
        jTRua.setText(m.getEndereco().getRua());
        jTCidade.setText(m.getEndereco().getCidade());
        jTBairro.setText(m.getEndereco().getBairro());
        jFCEP.setText(m.getEndereco().getCep());
        switch (m.getEndereco().getUf()) {
            case "Acre":
                modelUF.addElement(m.getEndereco().getUf());
                modelUF.addElement("Alagoas");
                modelUF.addElement("Amapá");
                modelUF.addElement("Amazonas");
                modelUF.addElement("Bahia");
                modelUF.addElement("Ceará");
                modelUF.addElement("Distrito Federal");
                modelUF.addElement("Espírito Santo");
                modelUF.addElement("Goiás");
                modelUF.addElement("Maranhão");
                modelUF.addElement("Mato Grosso");
                modelUF.addElement("Mato Grosso do Sul");
                modelUF.addElement("Minas Gerais");
                modelUF.addElement("Pará");
                modelUF.addElement("Paraná");
                modelUF.addElement("Pernambuco");
                modelUF.addElement("Piauí");
                modelUF.addElement("Rio de Janeiro");
                modelUF.addElement("Rio Grande do Norte");
                modelUF.addElement("Rio Grande do Sul");
                modelUF.addElement("Rondônia");
                modelUF.addElement("Roraima");
                modelUF.addElement("Santa Catarina");
                modelUF.addElement("São Paulo");
                modelUF.addElement("Sergipe");
                modelUF.addElement("Tocantins");
                break;
            case "Alagoas":
                modelUF.addElement(m.getEndereco().getUf());
                modelUF.addElement("Acre");
                modelUF.addElement("Amapá");
                modelUF.addElement("Amazonas");
                modelUF.addElement("Bahia");
                modelUF.addElement("Ceará");
                modelUF.addElement("Distrito Federal");
                modelUF.addElement("Espírito Santo");
                modelUF.addElement("Goiás");
                modelUF.addElement("Maranhão");
                modelUF.addElement("Mato Grosso");
                modelUF.addElement("Mato Grosso do Sul");
                modelUF.addElement("Minas Gerais");
                modelUF.addElement("Pará");
                modelUF.addElement("Paraná");
                modelUF.addElement("Pernambuco");
                modelUF.addElement("Piauí");
                modelUF.addElement("Rio de Janeiro");
                modelUF.addElement("Rio Grande do Norte");
                modelUF.addElement("Rio Grande do Sul");
                modelUF.addElement("Rondônia");
                modelUF.addElement("Roraima");
                modelUF.addElement("Santa Catarina");
                modelUF.addElement("São Paulo");
                modelUF.addElement("Sergipe");
                modelUF.addElement("Tocantins");
                break;
            case "Amapá":
                modelUF.addElement(m.getEndereco().getUf());
                modelUF.addElement("Acre");
                modelUF.addElement("Alagoas");
                modelUF.addElement("Amazonas");
                modelUF.addElement("Bahia");
                modelUF.addElement("Ceará");
                modelUF.addElement("Distrito Federal");
                modelUF.addElement("Espírito Santo");
                modelUF.addElement("Goiás");
                modelUF.addElement("Maranhão");
                modelUF.addElement("Mato Grosso");
                modelUF.addElement("Mato Grosso do Sul");
                modelUF.addElement("Minas Gerais");
                modelUF.addElement("Pará");
                modelUF.addElement("Paraná");
                modelUF.addElement("Pernambuco");
                modelUF.addElement("Piauí");
                modelUF.addElement("Rio de Janeiro");
                modelUF.addElement("Rio Grande do Norte");
                modelUF.addElement("Rio Grande do Sul");
                modelUF.addElement("Rondônia");
                modelUF.addElement("Roraima");
                modelUF.addElement("Santa Catarina");
                modelUF.addElement("São Paulo");
                modelUF.addElement("Sergipe");
                modelUF.addElement("Tocantins");
                break;
            case "Amazonas":
                modelUF.addElement(m.getEndereco().getUf());
                modelUF.addElement("Acre");
                modelUF.addElement("Alagoas");
                modelUF.addElement("Amapá");
                modelUF.addElement("Bahia");
                modelUF.addElement("Ceará");
                modelUF.addElement("Distrito Federal");
                modelUF.addElement("Espírito Santo");
                modelUF.addElement("Goiás");
                modelUF.addElement("Maranhão");
                modelUF.addElement("Mato Grosso");
                modelUF.addElement("Mato Grosso do Sul");
                modelUF.addElement("Minas Gerais");
                modelUF.addElement("Pará");
                modelUF.addElement("Paraná");
                modelUF.addElement("Pernambuco");
                modelUF.addElement("Piauí");
                modelUF.addElement("Rio de Janeiro");
                modelUF.addElement("Rio Grande do Norte");
                modelUF.addElement("Rio Grande do Sul");
                modelUF.addElement("Rondônia");
                modelUF.addElement("Roraima");
                modelUF.addElement("Santa Catarina");
                modelUF.addElement("São Paulo");
                modelUF.addElement("Sergipe");
                modelUF.addElement("Tocantins");
                break;
            case "Bahia":
                modelUF.addElement(m.getEndereco().getUf());
                modelUF.addElement("Acre");
                modelUF.addElement("Alagoas");
                modelUF.addElement("Amapá");
                modelUF.addElement("Ceará");
                modelUF.addElement("Distrito Federal");
                modelUF.addElement("Espírito Santo");
                modelUF.addElement("Goiás");
                modelUF.addElement("Maranhão");
                modelUF.addElement("Mato Grosso");
                modelUF.addElement("Mato Grosso do Sul");
                modelUF.addElement("Minas Gerais");
                modelUF.addElement("Pará");
                modelUF.addElement("Paraná");
                modelUF.addElement("Pernambuco");
                modelUF.addElement("Piauí");
                modelUF.addElement("Rio de Janeiro");
                modelUF.addElement("Rio Grande do Norte");
                modelUF.addElement("Rio Grande do Sul");
                modelUF.addElement("Rondônia");
                modelUF.addElement("Roraima");
                modelUF.addElement("Santa Catarina");
                modelUF.addElement("São Paulo");
                modelUF.addElement("Sergipe");
                modelUF.addElement("Tocantins");
                break;
            case "Ceará":
                modelUF.addElement(m.getEndereco().getUf());
                modelUF.addElement("Acre");
                modelUF.addElement("Alagoas");
                modelUF.addElement("Amapá");
                modelUF.addElement("Bahia");
                modelUF.addElement("Distrito Federal");
                modelUF.addElement("Espírito Santo");
                modelUF.addElement("Goiás");
                modelUF.addElement("Maranhão");
                modelUF.addElement("Mato Grosso");
                modelUF.addElement("Mato Grosso do Sul");
                modelUF.addElement("Minas Gerais");
                modelUF.addElement("Pará");
                modelUF.addElement("Paraná");
                modelUF.addElement("Pernambuco");
                modelUF.addElement("Piauí");
                modelUF.addElement("Rio de Janeiro");
                modelUF.addElement("Rio Grande do Norte");
                modelUF.addElement("Rio Grande do Sul");
                modelUF.addElement("Rondônia");
                modelUF.addElement("Roraima");
                modelUF.addElement("Santa Catarina");
                modelUF.addElement("São Paulo");
                modelUF.addElement("Sergipe");
                modelUF.addElement("Tocantins");
                break;
            case "Distrito Federal":
                modelUF.addElement(m.getEndereco().getUf());
                modelUF.addElement("Acre");
                modelUF.addElement("Alagoas");
                modelUF.addElement("Amapá");
                modelUF.addElement("Bahia");
                modelUF.addElement("Ceará");
                modelUF.addElement("Espírito Santo");
                modelUF.addElement("Goiás");
                modelUF.addElement("Maranhão");
                modelUF.addElement("Mato Grosso");
                modelUF.addElement("Mato Grosso do Sul");
                modelUF.addElement("Minas Gerais");
                modelUF.addElement("Pará");
                modelUF.addElement("Paraná");
                modelUF.addElement("Pernambuco");
                modelUF.addElement("Piauí");
                modelUF.addElement("Rio de Janeiro");
                modelUF.addElement("Rio Grande do Norte");
                modelUF.addElement("Rio Grande do Sul");
                modelUF.addElement("Rondônia");
                modelUF.addElement("Roraima");
                modelUF.addElement("Santa Catarina");
                modelUF.addElement("São Paulo");
                modelUF.addElement("Sergipe");
                modelUF.addElement("Tocantins");
                break;
            case "Espírito Santo":
                modelUF.addElement(m.getEndereco().getUf());
                modelUF.addElement("Acre");
                modelUF.addElement("Alagoas");
                modelUF.addElement("Amapá");
                modelUF.addElement("Bahia");
                modelUF.addElement("Ceará");
                modelUF.addElement("Distrito Federal");
                modelUF.addElement("Goiás");
                modelUF.addElement("Maranhão");
                modelUF.addElement("Mato Grosso");
                modelUF.addElement("Mato Grosso do Sul");
                modelUF.addElement("Minas Gerais");
                modelUF.addElement("Pará");
                modelUF.addElement("Paraná");
                modelUF.addElement("Pernambuco");
                modelUF.addElement("Piauí");
                modelUF.addElement("Rio de Janeiro");
                modelUF.addElement("Rio Grande do Norte");
                modelUF.addElement("Rio Grande do Sul");
                modelUF.addElement("Rondônia");
                modelUF.addElement("Roraima");
                modelUF.addElement("Santa Catarina");
                modelUF.addElement("São Paulo");
                modelUF.addElement("Sergipe");
                modelUF.addElement("Tocantins");
                break;
            case "Goiás":
                modelUF.addElement(m.getEndereco().getUf());
                modelUF.addElement("Acre");
                modelUF.addElement("Alagoas");
                modelUF.addElement("Amapá");
                modelUF.addElement("Bahia");
                modelUF.addElement("Ceará");
                modelUF.addElement("Distrito Federal");
                modelUF.addElement("Espírito Santo");
                modelUF.addElement("Maranhão");
                modelUF.addElement("Mato Grosso");
                modelUF.addElement("Mato Grosso do Sul");
                modelUF.addElement("Minas Gerais");
                modelUF.addElement("Pará");
                modelUF.addElement("Paraná");
                modelUF.addElement("Pernambuco");
                modelUF.addElement("Piauí");
                modelUF.addElement("Rio de Janeiro");
                modelUF.addElement("Rio Grande do Norte");
                modelUF.addElement("Rio Grande do Sul");
                modelUF.addElement("Rondônia");
                modelUF.addElement("Roraima");
                modelUF.addElement("Santa Catarina");
                modelUF.addElement("São Paulo");
                modelUF.addElement("Sergipe");
                modelUF.addElement("Tocantins");
                break;
            case "Maranhão":
                modelUF.addElement(m.getEndereco().getUf());
                modelUF.addElement("Acre");
                modelUF.addElement("Alagoas");
                modelUF.addElement("Amapá");
                modelUF.addElement("Bahia");
                modelUF.addElement("Ceará");
                modelUF.addElement("Distrito Federal");
                modelUF.addElement("Espírito Santo");
                modelUF.addElement("Goiás");
                modelUF.addElement("Mato Grosso");
                modelUF.addElement("Mato Grosso do Sul");
                modelUF.addElement("Minas Gerais");
                modelUF.addElement("Pará");
                modelUF.addElement("Paraná");
                modelUF.addElement("Pernambuco");
                modelUF.addElement("Piauí");
                modelUF.addElement("Rio de Janeiro");
                modelUF.addElement("Rio Grande do Norte");
                modelUF.addElement("Rio Grande do Sul");
                modelUF.addElement("Rondônia");
                modelUF.addElement("Roraima");
                modelUF.addElement("Santa Catarina");
                modelUF.addElement("São Paulo");
                modelUF.addElement("Sergipe");
                modelUF.addElement("Tocantins");
                break;
            case "Mato Grosso":
                modelUF.addElement(m.getEndereco().getUf());
                modelUF.addElement("Acre");
                modelUF.addElement("Alagoas");
                modelUF.addElement("Amapá");
                modelUF.addElement("Bahia");
                modelUF.addElement("Ceará");
                modelUF.addElement("Distrito Federal");
                modelUF.addElement("Espírito Santo");
                modelUF.addElement("Goiás");
                modelUF.addElement("Maranhão");
                modelUF.addElement("Mato Grosso do Sul");
                modelUF.addElement("Minas Gerais");
                modelUF.addElement("Pará");
                modelUF.addElement("Paraná");
                modelUF.addElement("Pernambuco");
                modelUF.addElement("Piauí");
                modelUF.addElement("Rio de Janeiro");
                modelUF.addElement("Rio Grande do Norte");
                modelUF.addElement("Rio Grande do Sul");
                modelUF.addElement("Rondônia");
                modelUF.addElement("Roraima");
                modelUF.addElement("Santa Catarina");
                modelUF.addElement("São Paulo");
                modelUF.addElement("Sergipe");
                modelUF.addElement("Tocantins");
                break;
            case "Mato Grosso do Sul":
                modelUF.addElement(m.getEndereco().getUf());
                modelUF.addElement("Acre");
                modelUF.addElement("Alagoas");
                modelUF.addElement("Amapá");
                modelUF.addElement("Bahia");
                modelUF.addElement("Ceará");
                modelUF.addElement("Distrito Federal");
                modelUF.addElement("Espírito Santo");
                modelUF.addElement("Goiás");
                modelUF.addElement("Maranhão");
                modelUF.addElement("Mato Grosso");
                modelUF.addElement("Minas Gerais");
                modelUF.addElement("Pará");
                modelUF.addElement("Paraná");
                modelUF.addElement("Pernambuco");
                modelUF.addElement("Piauí");
                modelUF.addElement("Rio de Janeiro");
                modelUF.addElement("Rio Grande do Norte");
                modelUF.addElement("Rio Grande do Sul");
                modelUF.addElement("Rondônia");
                modelUF.addElement("Roraima");
                modelUF.addElement("Santa Catarina");
                modelUF.addElement("São Paulo");
                modelUF.addElement("Sergipe");
                modelUF.addElement("Tocantins");
                break;
            case "Minas Gerais":
                modelUF.addElement(m.getEndereco().getUf());
                modelUF.addElement("Acre");
                modelUF.addElement("Alagoas");
                modelUF.addElement("Amapá");
                modelUF.addElement("Bahia");
                modelUF.addElement("Ceará");
                modelUF.addElement("Distrito Federal");
                modelUF.addElement("Espírito Santo");
                modelUF.addElement("Goiás");
                modelUF.addElement("Maranhão");
                modelUF.addElement("Mato Grosso");
                modelUF.addElement("Mato Grosso do Sul");
                modelUF.addElement("Pará");
                modelUF.addElement("Paraná");
                modelUF.addElement("Pernambuco");
                modelUF.addElement("Piauí");
                modelUF.addElement("Rio de Janeiro");
                modelUF.addElement("Rio Grande do Norte");
                modelUF.addElement("Rio Grande do Sul");
                modelUF.addElement("Rondônia");
                modelUF.addElement("Roraima");
                modelUF.addElement("Santa Catarina");
                modelUF.addElement("São Paulo");
                modelUF.addElement("Sergipe");
                modelUF.addElement("Tocantins");
                break;
            case "Pará":
                modelUF.addElement(m.getEndereco().getUf());
                modelUF.addElement("Acre");
                modelUF.addElement("Alagoas");
                modelUF.addElement("Amapá");
                modelUF.addElement("Bahia");
                modelUF.addElement("Ceará");
                modelUF.addElement("Distrito Federal");
                modelUF.addElement("Espírito Santo");
                modelUF.addElement("Goiás");
                modelUF.addElement("Maranhão");
                modelUF.addElement("Mato Grosso");
                modelUF.addElement("Mato Grosso do Sul");
                modelUF.addElement("Minas Gerais");
                modelUF.addElement("Paraná");
                modelUF.addElement("Pernambuco");
                modelUF.addElement("Piauí");
                modelUF.addElement("Rio de Janeiro");
                modelUF.addElement("Rio Grande do Norte");
                modelUF.addElement("Rio Grande do Sul");
                modelUF.addElement("Rondônia");
                modelUF.addElement("Roraima");
                modelUF.addElement("Santa Catarina");
                modelUF.addElement("São Paulo");
                modelUF.addElement("Sergipe");
                modelUF.addElement("Tocantins");
                break;
            case "Paraná":
                modelUF.addElement(m.getEndereco().getUf());
                modelUF.addElement("Acre");
                modelUF.addElement("Alagoas");
                modelUF.addElement("Amapá");
                modelUF.addElement("Bahia");
                modelUF.addElement("Ceará");
                modelUF.addElement("Distrito Federal");
                modelUF.addElement("Espírito Santo");
                modelUF.addElement("Goiás");
                modelUF.addElement("Maranhão");
                modelUF.addElement("Mato Grosso");
                modelUF.addElement("Mato Grosso do Sul");
                modelUF.addElement("Minas Gerais");
                modelUF.addElement("Pará");
                modelUF.addElement("Pernambuco");
                modelUF.addElement("Piauí");
                modelUF.addElement("Rio de Janeiro");
                modelUF.addElement("Rio Grande do Norte");
                modelUF.addElement("Rio Grande do Sul");
                modelUF.addElement("Rondônia");
                modelUF.addElement("Roraima");
                modelUF.addElement("Santa Catarina");
                modelUF.addElement("São Paulo");
                modelUF.addElement("Sergipe");
                modelUF.addElement("Tocantins");
                break;
            case "Pernambuco":
                modelUF.addElement(m.getEndereco().getUf());
                modelUF.addElement("Acre");
                modelUF.addElement("Alagoas");
                modelUF.addElement("Amapá");
                modelUF.addElement("Bahia");
                modelUF.addElement("Ceará");
                modelUF.addElement("Distrito Federal");
                modelUF.addElement("Espírito Santo");
                modelUF.addElement("Goiás");
                modelUF.addElement("Maranhão");
                modelUF.addElement("Mato Grosso");
                modelUF.addElement("Mato Grosso do Sul");
                modelUF.addElement("Minas Gerais");
                modelUF.addElement("Pará");
                modelUF.addElement("Paraná");
                modelUF.addElement("Piauí");
                modelUF.addElement("Rio de Janeiro");
                modelUF.addElement("Rio Grande do Norte");
                modelUF.addElement("Rio Grande do Sul");
                modelUF.addElement("Rondônia");
                modelUF.addElement("Roraima");
                modelUF.addElement("Santa Catarina");
                modelUF.addElement("São Paulo");
                modelUF.addElement("Sergipe");
                modelUF.addElement("Tocantins");
                break;
            case "Piauí":
                modelUF.addElement(m.getEndereco().getUf());
                modelUF.addElement("Acre");
                modelUF.addElement("Alagoas");
                modelUF.addElement("Amapá");
                modelUF.addElement("Bahia");
                modelUF.addElement("Ceará");
                modelUF.addElement("Distrito Federal");
                modelUF.addElement("Espírito Santo");
                modelUF.addElement("Goiás");
                modelUF.addElement("Maranhão");
                modelUF.addElement("Mato Grosso");
                modelUF.addElement("Mato Grosso do Sul");
                modelUF.addElement("Minas Gerais");
                modelUF.addElement("Pará");
                modelUF.addElement("Paraná");
                modelUF.addElement("Pernambuco");
                modelUF.addElement("Rio de Janeiro");
                modelUF.addElement("Rio Grande do Norte");
                modelUF.addElement("Rio Grande do Sul");
                modelUF.addElement("Rondônia");
                modelUF.addElement("Roraima");
                modelUF.addElement("Santa Catarina");
                modelUF.addElement("São Paulo");
                modelUF.addElement("Sergipe");
                modelUF.addElement("Tocantins");
                break;
            case "Rio de Janeiro":
                modelUF.addElement(m.getEndereco().getUf());
                modelUF.addElement("Acre");
                modelUF.addElement("Alagoas");
                modelUF.addElement("Amapá");
                modelUF.addElement("Bahia");
                modelUF.addElement("Ceará");
                modelUF.addElement("Distrito Federal");
                modelUF.addElement("Espírito Santo");
                modelUF.addElement("Goiás");
                modelUF.addElement("Maranhão");
                modelUF.addElement("Mato Grosso");
                modelUF.addElement("Mato Grosso do Sul");
                modelUF.addElement("Minas Gerais");
                modelUF.addElement("Pará");
                modelUF.addElement("Paraná");
                modelUF.addElement("Pernambuco");
                modelUF.addElement("Piauí");
                modelUF.addElement("Rio Grande do Norte");
                modelUF.addElement("Rio Grande do Sul");
                modelUF.addElement("Rondônia");
                modelUF.addElement("Roraima");
                modelUF.addElement("Santa Catarina");
                modelUF.addElement("São Paulo");
                modelUF.addElement("Sergipe");
                modelUF.addElement("Tocantins");
                break;
            case "Rio Grande do Norte":
                modelUF.addElement(m.getEndereco().getUf());
                modelUF.addElement("Acre");
                modelUF.addElement("Alagoas");
                modelUF.addElement("Amapá");
                modelUF.addElement("Bahia");
                modelUF.addElement("Ceará");
                modelUF.addElement("Distrito Federal");
                modelUF.addElement("Espírito Santo");
                modelUF.addElement("Goiás");
                modelUF.addElement("Maranhão");
                modelUF.addElement("Mato Grosso");
                modelUF.addElement("Mato Grosso do Sul");
                modelUF.addElement("Minas Gerais");
                modelUF.addElement("Pará");
                modelUF.addElement("Paraná");
                modelUF.addElement("Pernambuco");
                modelUF.addElement("Piauí");
                modelUF.addElement("Rio de Janeiro");
                modelUF.addElement("Rio Grande do Sul");
                modelUF.addElement("Rondônia");
                modelUF.addElement("Roraima");
                modelUF.addElement("Santa Catarina");
                modelUF.addElement("São Paulo");
                modelUF.addElement("Sergipe");
                modelUF.addElement("Tocantins");
                break;
            case "Rio Grande do Sul":
                modelUF.addElement(m.getEndereco().getUf());
                modelUF.addElement("Acre");
                modelUF.addElement("Alagoas");
                modelUF.addElement("Amapá");
                modelUF.addElement("Bahia");
                modelUF.addElement("Ceará");
                modelUF.addElement("Distrito Federal");
                modelUF.addElement("Espírito Santo");
                modelUF.addElement("Goiás");
                modelUF.addElement("Maranhão");
                modelUF.addElement("Mato Grosso");
                modelUF.addElement("Mato Grosso do Sul");
                modelUF.addElement("Minas Gerais");
                modelUF.addElement("Pará");
                modelUF.addElement("Paraná");
                modelUF.addElement("Pernambuco");
                modelUF.addElement("Piauí");
                modelUF.addElement("Rio de Janeiro");
                modelUF.addElement("Rio Grande do Norte");
                modelUF.addElement("Rondônia");
                modelUF.addElement("Roraima");
                modelUF.addElement("Santa Catarina");
                modelUF.addElement("São Paulo");
                modelUF.addElement("Sergipe");
                modelUF.addElement("Tocantins");
                break;
            case "Rondônia":
                modelUF.addElement(m.getEndereco().getUf());
                modelUF.addElement("Acre");
                modelUF.addElement("Alagoas");
                modelUF.addElement("Amapá");
                modelUF.addElement("Bahia");
                modelUF.addElement("Ceará");
                modelUF.addElement("Distrito Federal");
                modelUF.addElement("Espírito Santo");
                modelUF.addElement("Goiás");
                modelUF.addElement("Maranhão");
                modelUF.addElement("Mato Grosso");
                modelUF.addElement("Mato Grosso do Sul");
                modelUF.addElement("Minas Gerais");
                modelUF.addElement("Pará");
                modelUF.addElement("Paraná");
                modelUF.addElement("Pernambuco");
                modelUF.addElement("Piauí");
                modelUF.addElement("Rio de Janeiro");
                modelUF.addElement("Rio Grande do Norte");
                modelUF.addElement("Rio Grande do Sul");
                modelUF.addElement("Roraima");
                modelUF.addElement("Santa Catarina");
                modelUF.addElement("São Paulo");
                modelUF.addElement("Sergipe");
                modelUF.addElement("Tocantins");
                break;
            case "Roraima":
                modelUF.addElement(m.getEndereco().getUf());
                modelUF.addElement("Acre");
                modelUF.addElement("Alagoas");
                modelUF.addElement("Amapá");
                modelUF.addElement("Bahia");
                modelUF.addElement("Ceará");
                modelUF.addElement("Distrito Federal");
                modelUF.addElement("Espírito Santo");
                modelUF.addElement("Goiás");
                modelUF.addElement("Maranhão");
                modelUF.addElement("Mato Grosso");
                modelUF.addElement("Mato Grosso do Sul");
                modelUF.addElement("Minas Gerais");
                modelUF.addElement("Pará");
                modelUF.addElement("Paraná");
                modelUF.addElement("Pernambuco");
                modelUF.addElement("Piauí");
                modelUF.addElement("Rio de Janeiro");
                modelUF.addElement("Rio Grande do Norte");
                modelUF.addElement("Rio Grande do Sul");
                modelUF.addElement("Rondônia");
                modelUF.addElement("Santa Catarina");
                modelUF.addElement("São Paulo");
                modelUF.addElement("Sergipe");
                modelUF.addElement("Tocantins");
                break;
            case "Santa Catarina":
                modelUF.addElement(m.getEndereco().getUf());
                modelUF.addElement("Acre");
                modelUF.addElement("Alagoas");
                modelUF.addElement("Amapá");
                modelUF.addElement("Bahia");
                modelUF.addElement("Ceará");
                modelUF.addElement("Distrito Federal");
                modelUF.addElement("Espírito Santo");
                modelUF.addElement("Goiás");
                modelUF.addElement("Maranhão");
                modelUF.addElement("Mato Grosso");
                modelUF.addElement("Mato Grosso do Sul");
                modelUF.addElement("Minas Gerais");
                modelUF.addElement("Pará");
                modelUF.addElement("Paraná");
                modelUF.addElement("Pernambuco");
                modelUF.addElement("Piauí");
                modelUF.addElement("Rio de Janeiro");
                modelUF.addElement("Rio Grande do Norte");
                modelUF.addElement("Rio Grande do Sul");
                modelUF.addElement("Rondônia");
                modelUF.addElement("Roraima");
                modelUF.addElement("São Paulo");
                modelUF.addElement("Sergipe");
                modelUF.addElement("Tocantins");
                break;
            case "São Paulo":
                modelUF.addElement(m.getEndereco().getUf());
                modelUF.addElement("Acre");
                modelUF.addElement("Alagoas");
                modelUF.addElement("Amapá");
                modelUF.addElement("Bahia");
                modelUF.addElement("Ceará");
                modelUF.addElement("Distrito Federal");
                modelUF.addElement("Espírito Santo");
                modelUF.addElement("Goiás");
                modelUF.addElement("Maranhão");
                modelUF.addElement("Mato Grosso");
                modelUF.addElement("Mato Grosso do Sul");
                modelUF.addElement("Minas Gerais");
                modelUF.addElement("Pará");
                modelUF.addElement("Paraná");
                modelUF.addElement("Pernambuco");
                modelUF.addElement("Piauí");
                modelUF.addElement("Rio de Janeiro");
                modelUF.addElement("Rio Grande do Norte");
                modelUF.addElement("Rio Grande do Sul");
                modelUF.addElement("Rondônia");
                modelUF.addElement("Roraima");
                modelUF.addElement("Santa Catarina");
                modelUF.addElement("Sergipe");
                modelUF.addElement("Tocantins");
                break;
            case "Sergipe":
                modelUF.addElement(m.getEndereco().getUf());
                modelUF.addElement("Acre");
                modelUF.addElement("Alagoas");
                modelUF.addElement("Amapá");
                modelUF.addElement("Bahia");
                modelUF.addElement("Ceará");
                modelUF.addElement("Distrito Federal");
                modelUF.addElement("Espírito Santo");
                modelUF.addElement("Goiás");
                modelUF.addElement("Maranhão");
                modelUF.addElement("Mato Grosso");
                modelUF.addElement("Mato Grosso do Sul");
                modelUF.addElement("Minas Gerais");
                modelUF.addElement("Pará");
                modelUF.addElement("Paraná");
                modelUF.addElement("Pernambuco");
                modelUF.addElement("Piauí");
                modelUF.addElement("Rio de Janeiro");
                modelUF.addElement("Rio Grande do Norte");
                modelUF.addElement("Rio Grande do Sul");
                modelUF.addElement("Rondônia");
                modelUF.addElement("Roraima");
                modelUF.addElement("Santa Catarina");
                modelUF.addElement("São Paulo");
                modelUF.addElement("Tocantins");
                break;
            case "Tocantins":
                modelUF.addElement(m.getEndereco().getUf());
                modelUF.addElement("Acre");
                modelUF.addElement("Alagoas");
                modelUF.addElement("Amapá");
                modelUF.addElement("Bahia");
                modelUF.addElement("Ceará");
                modelUF.addElement("Distrito Federal");
                modelUF.addElement("Espírito Santo");
                modelUF.addElement("Goiás");
                modelUF.addElement("Maranhão");
                modelUF.addElement("Mato Grosso");
                modelUF.addElement("Mato Grosso do Sul");
                modelUF.addElement("Minas Gerais");
                modelUF.addElement("Pará");
                modelUF.addElement("Paraná");
                modelUF.addElement("Pernambuco");
                modelUF.addElement("Piauí");
                modelUF.addElement("Rio de Janeiro");
                modelUF.addElement("Rio Grande do Norte");
                modelUF.addElement("Rio Grande do Sul");
                modelUF.addElement("Rondônia");
                modelUF.addElement("Roraima");
                modelUF.addElement("Santa Catarina");
                modelUF.addElement("São Paulo");
                modelUF.addElement("Sergipe");
                break;

        }
        jComboUF.setModel(modelUF);

        jTRG.setText(m.getRg());
        jFCPF.setText(m.getCpf());
        jTLogin.setText(m.getLogin());
        jPSenha.setText(m.getSenha());
        jFDataNascimento.setText(formatMedico.format(m.getDataNascimento()));
        jFDataAdmissao.setText(formatMedico.format(m.getDataAdmissao()));
        switch (m.getSexo()) {
            case "Feminino":
                modelSexo.addElement(m.getSexo());
                modelSexo.addElement("Masculino");
                modelSexo.addElement("Trans");
                modelSexo.addElement("Homossexual");
                modelSexo.addElement("Outros");
                break;
            case "Nasculino":
                modelSexo.addElement(m.getSexo());
                modelSexo.addElement("Feminino");
                modelSexo.addElement("Trans");
                modelSexo.addElement("Homossexual");
                modelSexo.addElement("Outros");
                break;
            case "Trans":
                modelSexo.addElement(m.getSexo());
                modelSexo.addElement("Feminino");
                modelSexo.addElement("Masculino");
                modelSexo.addElement("Homossexual");
                modelSexo.addElement("Outros");
                break;
            case "Hommossexual":
                modelSexo.addElement(m.getSexo());
                modelSexo.addElement("Feminino");
                modelSexo.addElement("Masculino");
                modelSexo.addElement("Trans");
                modelSexo.addElement("Outros");
                break;
            case "Outros":
                modelSexo.addElement(m.getSexo());
                modelSexo.addElement("Feminino");
                modelSexo.addElement("Masculino");
                modelSexo.addElement("Homossexual");
                modelSexo.addElement("Trans");
                break;
        }
        jComboSexo.setModel(modelSexo);

        jTIdade.setText(String.valueOf(m.getIdade()));
        jFCRM.setText(m.getCrm());
        DecimalFormat formatDecimal = new DecimalFormat("###,##0.00");
        jTSalario.setText(formatDecimal.format(m.getSalario()));
        switch (m.getFuncao()) {
            case "Médico":
                modelFuncao.addElement(m.getFuncao());
                modelFuncao.addElement("Diretor");
                modelFuncao.addElement("Residente");
                break;
            case "Diretor":
                modelFuncao.addElement(m.getFuncao());
                modelFuncao.addElement("Médico");
                modelFuncao.addElement("Residente");
                break;
            case "Residente":
                modelFuncao.addElement(m.getFuncao());
                modelFuncao.addElement("Médico");
                modelFuncao.addElement("Diretor");
                break;
        }
        jComboFuncao.setModel(modelFuncao);

        jFTelefone.setText(m.getContato().getTelefone());
        jFCelular.setText(m.getContato().getCelular());
        jTEmail.setText(m.getContato().getEmail());
        jTNome.requestFocus();

    }

    private void prepare() {
        medico = new Medico();
        medico.setNome(jTNome.getText().trim());
        Endereco endereco = new Endereco();
        endereco.setRua(jTRua.getText().trim());
        endereco.setCidade(jTCidade.getText().trim());
        endereco.setBairro(jTBairro.getText().trim());
        endereco.setCep(jFCEP.getText().trim());
        endereco.setComplemento(jTComplemento.getText().trim());
        endereco.setNumeroResidencia(jTNumeroResidencia.getText().trim());
        endereco.setUf(jComboUF.getSelectedItem().toString());
        medico.setEndereco(endereco);
        medico.setRg(jTRG.getText().trim());
        medico.setCpf(jFCPF.getText().trim());
        medico.setLogin(jTLogin.getText().trim());
        medico.setSenha(new String(jPSenha.getPassword()));
        try {
            medico.setDataNascimento(formatMedico.parse(jFDataNascimento.getText().trim()));
        } catch (ParseException ex) {
            Logger.getLogger(MedicoFrm.class.getName()).log(Level.SEVERE, null, ex);
        }
        medico.setSexo(jComboSexo.getSelectedItem().toString());
        medico.setCrm(jFCRM.getText().trim());
        medico.setSalario(Double.parseDouble(jTSalario.getText().trim()));
        medico.setFuncao(jComboFuncao.getSelectedItem().toString());
        Contato contato = new Contato();
        contato.setTelefone(jFTelefone.getText().trim());
        contato.setCelular(jFCelular.getText().trim());
        contato.setEmail(jTEmail.getText().trim());
        medico.setContato(contato);
    }

    private void habilitarComponentes(boolean isLiberated) {
        jTNome.setEnabled(isLiberated);
        jTRua.setEnabled(isLiberated);
        jTCidade.setEnabled(isLiberated);
        jTBairro.setEnabled(isLiberated);
        jFCEP.setEnabled(isLiberated);
        jComboUF.setEnabled(isLiberated);
        jTComplemento.setEnabled(isLiberated);
        jTNumeroResidencia.setEnabled(isLiberated);
        jTRG.setEnabled(isLiberated);
        jFCPF.setEnabled(isLiberated);
        jTLogin.setEnabled(isLiberated);
        jPSenha.setEnabled(isLiberated);
        jFDataNascimento.setEnabled(isLiberated);
        jComboSexo.setEnabled(isLiberated);
        jFCRM.setEnabled(isLiberated);
        jTSalario.setEnabled(isLiberated);
        jComboFuncao.setEnabled(isLiberated);
        jFTelefone.setEnabled(isLiberated);
        jFCelular.setEnabled(isLiberated);
        jTEmail.setEnabled(isLiberated);
        jBtnAtualizar.setEnabled(isLiberated);
        jBtnExcluir.setEnabled(isLiberated);
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
        jLabel4 = new javax.swing.JLabel();
        jTNome = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTRua = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTCidade = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTBairro = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jFCEP = new javax.swing.JFormattedTextField();
        jLabel9 = new javax.swing.JLabel();
        jTNumeroResidencia = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jComboUF = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        jTRG = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jFCPF = new javax.swing.JFormattedTextField();
        jLabel14 = new javax.swing.JLabel();
        jTLogin = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jPSenha = new javax.swing.JPasswordField();
        jLabel16 = new javax.swing.JLabel();
        jFDataNascimento = new javax.swing.JFormattedTextField();
        jLabel17 = new javax.swing.JLabel();
        jFDataAdmissao = new javax.swing.JFormattedTextField();
        jLabel18 = new javax.swing.JLabel();
        jComboSexo = new javax.swing.JComboBox<>();
        jLabel19 = new javax.swing.JLabel();
        jTIdade = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jFCRM = new javax.swing.JFormattedTextField();
        jLabel21 = new javax.swing.JLabel();
        jTSalario = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jComboFuncao = new javax.swing.JComboBox<>();
        jLabel23 = new javax.swing.JLabel();
        jFTelefone = new javax.swing.JFormattedTextField();
        jLabel24 = new javax.swing.JLabel();
        jFCelular = new javax.swing.JFormattedTextField();
        jLabel25 = new javax.swing.JLabel();
        jTEmail = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTabelaMedico = new javax.swing.JTable();
        jLabel26 = new javax.swing.JLabel();
        jTConsulta = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jComboCampo = new javax.swing.JComboBox<>();
        jBtnPesquisar = new javax.swing.JButton();
        jBtnAtualizar = new javax.swing.JButton();
        jBtnExcluir = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jTComplemento = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setOpacity(0.9F);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Lucida Handwriting", 2, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Dados cadastrais do Médico");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 30, 600, -1));

        jLabel3.setFont(new java.awt.Font("Lucida Calligraphy", 2, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("X");
        jLabel3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 0, 50, -1));

        jLabel4.setFont(new java.awt.Font("Lucida Handwriting", 2, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Nome");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 120, 70, -1));

        jTNome.setFont(new java.awt.Font("Lucida Calligraphy", 2, 13)); // NOI18N
        jTNome.setEnabled(false);
        getContentPane().add(jTNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 120, 700, -1));

        jLabel5.setFont(new java.awt.Font("Lucida Handwriting", 2, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Rua");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 150, 50, -1));

        jTRua.setFont(new java.awt.Font("Lucida Calligraphy", 2, 13)); // NOI18N
        jTRua.setEnabled(false);
        getContentPane().add(jTRua, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 150, 700, -1));

        jLabel6.setFont(new java.awt.Font("Lucida Handwriting", 2, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Cidade");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 180, 70, -1));

        jTCidade.setFont(new java.awt.Font("Lucida Calligraphy", 2, 13)); // NOI18N
        jTCidade.setEnabled(false);
        getContentPane().add(jTCidade, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 180, 300, -1));

        jLabel7.setFont(new java.awt.Font("Lucida Handwriting", 2, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Bairro");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 180, 60, -1));

        jTBairro.setFont(new java.awt.Font("Lucida Calligraphy", 2, 13)); // NOI18N
        jTBairro.setEnabled(false);
        getContentPane().add(jTBairro, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 180, 330, -1));

        jLabel8.setFont(new java.awt.Font("Lucida Handwriting", 2, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("CEP");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 240, 40, -1));

        try {
            jFCEP.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#####-###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFCEP.setEnabled(false);
        jFCEP.setFont(new java.awt.Font("Lucida Calligraphy", 2, 13)); // NOI18N
        getContentPane().add(jFCEP, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 240, 140, -1));

        jLabel9.setFont(new java.awt.Font("Lucida Handwriting", 2, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Nº de Residência");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 240, 130, 20));

        jTNumeroResidencia.setFont(new java.awt.Font("Lucida Calligraphy", 2, 13)); // NOI18N
        jTNumeroResidencia.setEnabled(false);
        getContentPane().add(jTNumeroResidencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 240, 60, -1));

        jLabel10.setFont(new java.awt.Font("Lucida Handwriting", 2, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("UF");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 240, 40, -1));

        jComboUF.setFont(new java.awt.Font("Lucida Calligraphy", 2, 13)); // NOI18N
        jComboUF.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "UF" }));
        jComboUF.setEnabled(false);
        getContentPane().add(jComboUF, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 240, 120, -1));

        jLabel12.setFont(new java.awt.Font("Lucida Handwriting", 2, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("RG");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 240, 30, -1));

        jTRG.setFont(new java.awt.Font("Lucida Calligraphy", 2, 13)); // NOI18N
        jTRG.setEnabled(false);
        getContentPane().add(jTRG, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 240, 160, -1));

        jLabel13.setFont(new java.awt.Font("Lucida Handwriting", 2, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("CPF");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 270, 40, -1));

        try {
            jFCPF.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFCPF.setEnabled(false);
        jFCPF.setFont(new java.awt.Font("Lucida Calligraphy", 2, 13)); // NOI18N
        getContentPane().add(jFCPF, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 270, 90, -1));

        jLabel14.setFont(new java.awt.Font("Lucida Handwriting", 2, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Login");
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 270, 50, -1));

        jTLogin.setFont(new java.awt.Font("Lucida Calligraphy", 2, 13)); // NOI18N
        jTLogin.setEnabled(false);
        getContentPane().add(jTLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 270, 150, -1));

        jLabel15.setFont(new java.awt.Font("Lucida Handwriting", 2, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Senha");
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 270, 60, -1));

        jPSenha.setFont(new java.awt.Font("Lucida Calligraphy", 2, 13)); // NOI18N
        jPSenha.setEnabled(false);
        getContentPane().add(jPSenha, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 270, 130, -1));

        jLabel16.setFont(new java.awt.Font("Lucida Handwriting", 2, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Data Nasc,");
        getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 270, 90, -1));

        try {
            jFDataNascimento.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFDataNascimento.setEnabled(false);
        jFDataNascimento.setFont(new java.awt.Font("Lucida Calligraphy", 2, 13)); // NOI18N
        getContentPane().add(jFDataNascimento, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 270, 120, -1));

        jLabel17.setFont(new java.awt.Font("Lucida Handwriting", 2, 12)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Data de Admissão");
        getContentPane().add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 300, 140, -1));

        try {
            jFDataAdmissao.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFDataAdmissao.setEnabled(false);
        jFDataAdmissao.setFont(new java.awt.Font("Lucida Calligraphy", 2, 13)); // NOI18N
        getContentPane().add(jFDataAdmissao, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 300, 130, -1));

        jLabel18.setFont(new java.awt.Font("Lucida Handwriting", 2, 12)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Sexo");
        getContentPane().add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 300, 50, -1));

        jComboSexo.setFont(new java.awt.Font("Lucida Calligraphy", 2, 13)); // NOI18N
        jComboSexo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sexo do Médico" }));
        jComboSexo.setEnabled(false);
        getContentPane().add(jComboSexo, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 300, 140, -1));

        jLabel19.setFont(new java.awt.Font("Lucida Handwriting", 2, 12)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Idade");
        getContentPane().add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 300, 60, -1));

        jTIdade.setFont(new java.awt.Font("Lucida Calligraphy", 2, 13)); // NOI18N
        jTIdade.setEnabled(false);
        getContentPane().add(jTIdade, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 300, 70, -1));

        jLabel20.setFont(new java.awt.Font("Lucida Handwriting", 2, 12)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("CRM");
        getContentPane().add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 300, 40, -1));

        jFCRM.setEnabled(false);
        jFCRM.setFont(new java.awt.Font("Lucida Calligraphy", 2, 13)); // NOI18N
        getContentPane().add(jFCRM, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 300, 100, -1));

        jLabel21.setFont(new java.awt.Font("Lucida Handwriting", 2, 12)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Salário R$");
        getContentPane().add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 330, 90, -1));

        jTSalario.setFont(new java.awt.Font("Lucida Calligraphy", 2, 13)); // NOI18N
        jTSalario.setEnabled(false);
        getContentPane().add(jTSalario, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 330, 90, -1));

        jLabel22.setFont(new java.awt.Font("Lucida Handwriting", 2, 12)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("Função");
        getContentPane().add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 330, 70, -1));

        jComboFuncao.setFont(new java.awt.Font("Lucida Calligraphy", 2, 13)); // NOI18N
        jComboFuncao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Função" }));
        jComboFuncao.setEnabled(false);
        getContentPane().add(jComboFuncao, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 330, 140, -1));

        jLabel23.setFont(new java.awt.Font("Lucida Handwriting", 2, 12)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("Telefone");
        getContentPane().add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 330, 70, -1));

        try {
            jFTelefone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(###)####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFTelefone.setEnabled(false);
        jFTelefone.setFont(new java.awt.Font("Lucida Calligraphy", 2, 13)); // NOI18N
        getContentPane().add(jFTelefone, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 330, 120, -1));

        jLabel24.setFont(new java.awt.Font("Lucida Handwriting", 2, 12)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("Celular");
        getContentPane().add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 330, 70, -1));

        try {
            jFCelular.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(###)#####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFCelular.setEnabled(false);
        jFCelular.setFont(new java.awt.Font("Lucida Calligraphy", 2, 13)); // NOI18N
        getContentPane().add(jFCelular, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 330, 100, -1));

        jLabel25.setFont(new java.awt.Font("Lucida Handwriting", 2, 12)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("E-Mail");
        getContentPane().add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 360, 60, -1));

        jTEmail.setFont(new java.awt.Font("Lucida Calligraphy", 2, 13)); // NOI18N
        jTEmail.setEnabled(false);
        getContentPane().add(jTEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 360, 700, -1));

        jTabelaMedico.setFont(new java.awt.Font("Lucida Calligraphy", 2, 13)); // NOI18N
        jTabelaMedico.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Código", "Nome", "Data de Nasc.", "CRM"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Long.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTabelaMedico.setEnabled(false);
        jTabelaMedico.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabelaMedicoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTabelaMedico);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 400, 760, 110));

        jLabel26.setFont(new java.awt.Font("Lucida Handwriting", 2, 12)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("Consulta");
        getContentPane().add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 530, 80, -1));

        jTConsulta.setFont(new java.awt.Font("Lucida Calligraphy", 2, 13)); // NOI18N
        getContentPane().add(jTConsulta, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 560, 210, -1));

        jLabel27.setFont(new java.awt.Font("Lucida Handwriting", 2, 12)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("Campo");
        getContentPane().add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 530, 60, -1));

        jComboCampo.setFont(new java.awt.Font("Lucida Calligraphy", 2, 13)); // NOI18N
        jComboCampo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ativos", "Todos", "Código", "Nome", "CPF", "Bloqueados", "Suspensos", "CRM" }));
        getContentPane().add(jComboCampo, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 560, 180, -1));

        jBtnPesquisar.setFont(new java.awt.Font("Lucida Handwriting", 2, 12)); // NOI18N
        jBtnPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/if_search_magnifying_glass_find_103857.png"))); // NOI18N
        jBtnPesquisar.setText("Pesquisar");
        jBtnPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnPesquisarActionPerformed(evt);
            }
        });
        getContentPane().add(jBtnPesquisar, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 540, 140, 60));

        jBtnAtualizar.setFont(new java.awt.Font("Lucida Handwriting", 2, 12)); // NOI18N
        jBtnAtualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/update.png"))); // NOI18N
        jBtnAtualizar.setText("Alterar");
        jBtnAtualizar.setEnabled(false);
        jBtnAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnAtualizarActionPerformed(evt);
            }
        });
        getContentPane().add(jBtnAtualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 520, 140, 50));

        jBtnExcluir.setFont(new java.awt.Font("Lucida Handwriting", 2, 12)); // NOI18N
        jBtnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/if_delete_71076.png"))); // NOI18N
        jBtnExcluir.setText("Excluir");
        jBtnExcluir.setEnabled(false);
        jBtnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnExcluirActionPerformed(evt);
            }
        });
        getContentPane().add(jBtnExcluir, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 580, 140, 50));

        jLabel11.setFont(new java.awt.Font("Lucida Handwriting", 2, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Complemento");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 210, 110, -1));

        jTComplemento.setFont(new java.awt.Font("Lucida Calligraphy", 2, 13)); // NOI18N
        jTComplemento.setEnabled(false);
        getContentPane().add(jTComplemento, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 210, 650, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/back_add_medico.jpg"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 870, 710));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jTabelaMedicoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabelaMedicoMouseClicked
        // TODO add your handling code here:
        linhaSelecionada = jTabelaMedico.getSelectedRow();
        if (medicos == null) {
            habilitarComponentes(false);
            jTabelaMedico.setEnabled(false);
        } else {
            if (!medicos.isEmpty()) {
                habilitarComponentes(true);
                preencher();
            } else {
                habilitarComponentes(false);
                jTabelaMedico.setEnabled(false);
            }
        }
    }//GEN-LAST:event_jTabelaMedicoMouseClicked

    private void jBtnAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnAtualizarActionPerformed
        // TODO add your handling code here:

        if (jTNome.getText().trim().equals("")) {
            messages = MessageView.requiredField("Nome");
            JOptionPane.showMessageDialog(rootPane, messages[0], messages[1], JOptionPane.WARNING_MESSAGE);
            jTNome.requestFocus();
        } else if (jTRua.getText().trim().equals("")) {
            messages = MessageView.requiredField("Rua");
            JOptionPane.showMessageDialog(rootPane, messages[0], messages[1], JOptionPane.WARNING_MESSAGE);
            jTRua.requestFocus();
        } else if (jTCidade.getText().trim().equals("")) {
            messages = MessageView.requiredField("Rua");
            JOptionPane.showMessageDialog(rootPane, messages[0], messages[1], JOptionPane.WARNING_MESSAGE);
            jTRua.requestFocus();
        } else if (jTBairro.getText().trim().equals("")) {
            messages = MessageView.requiredField("Rua");
            JOptionPane.showMessageDialog(rootPane, messages[0], messages[1], JOptionPane.WARNING_MESSAGE);
            jTRua.requestFocus();
        } else if (jTComplemento.getText().trim().equals("")) {
            messages = MessageView.requiredField("Rua");
            JOptionPane.showMessageDialog(rootPane, messages[0], messages[1], JOptionPane.WARNING_MESSAGE);
            jTRua.requestFocus();
        } else if (!jFCEP.isEditValid()) {
            messages = MessageView.requiredField("Rua");
            JOptionPane.showMessageDialog(rootPane, messages[0], messages[1], JOptionPane.WARNING_MESSAGE);
            jTRua.requestFocus();
        } else if (jTNumeroResidencia.getText().trim().equals("")) {
            messages = MessageView.requiredField("Rua");
            JOptionPane.showMessageDialog(rootPane, messages[0], messages[1], JOptionPane.WARNING_MESSAGE);
            jTRua.requestFocus();
        } else if (jTRG.getText().trim().equals("")) {
            messages = MessageView.requiredField("Rua");
            JOptionPane.showMessageDialog(rootPane, messages[0], messages[1], JOptionPane.WARNING_MESSAGE);
            jTRua.requestFocus();
        } else if (!jFCPF.isEditValid()) {
            messages = MessageView.requiredField("Rua");
            JOptionPane.showMessageDialog(rootPane, messages[0], messages[1], JOptionPane.WARNING_MESSAGE);
            jTRua.requestFocus();
        } else if (jTLogin.getText().trim().equals("")) {
            messages = MessageView.requiredField("Rua");
            JOptionPane.showMessageDialog(rootPane, messages[0], messages[1], JOptionPane.WARNING_MESSAGE);
            jTRua.requestFocus();
        } else {
            String senha = new String(jPSenha.getPassword());
            if (senha.trim().equals("")) {
                messages = MessageView.requiredField("Rua");
                JOptionPane.showMessageDialog(rootPane, messages[0], messages[1], JOptionPane.WARNING_MESSAGE);
                jTRua.requestFocus();
            } else if (!jFDataNascimento.isEditValid()) {
                messages = MessageView.requiredField("Rua");
                JOptionPane.showMessageDialog(rootPane, messages[0], messages[1], JOptionPane.WARNING_MESSAGE);
                jTRua.requestFocus();
            } else if (!jFCRM.isEditValid()) {
                messages = MessageView.requiredField("Rua");
                JOptionPane.showMessageDialog(rootPane, messages[0], messages[1], JOptionPane.WARNING_MESSAGE);
                jTRua.requestFocus();
            } else if (jTSalario.getText().trim().equals("")) {
                messages = MessageView.requiredField("Rua");
                JOptionPane.showMessageDialog(rootPane, messages[0], messages[1], JOptionPane.WARNING_MESSAGE);
                jTRua.requestFocus();
            } else if (!jFCelular.isEditValid()) {
                messages = MessageView.requiredField("Rua");
                JOptionPane.showMessageDialog(rootPane, messages[0], messages[1], JOptionPane.WARNING_MESSAGE);
                jTRua.requestFocus();
            } else if (jTEmail.getText().trim().equals("")) {
                messages = MessageView.requiredField("Rua");
                JOptionPane.showMessageDialog(rootPane, messages[0], messages[1], JOptionPane.WARNING_MESSAGE);
                jTRua.requestFocus();
            } else {
                prepare();
                try {
                    controlMedico.alterarMedico(medico);
                    JOptionPane.showMessageDialog(rootPane, "Os dados " + (medico.getSexo().equals("Masculino") ? "do Médico " : "da Médica ") + medico.getNome() + " foram atualizados com sucesso no sistema!", (medico.getSexo().equals("Masculino") ? "Médico atualizado com sucesso" : "Médica atualizada com sucesso!"), JOptionPane.INFORMATION_MESSAGE);
                    limpar();
                    habilitarComponentes(false);
                    limparTabela();
                } catch (ErroDAOException | ErroConectarException | ErroNegocioException | SQLException ex) {
                    JOptionPane.showMessageDialog(rootPane, ex.getMessage(), (ex instanceof ErroDAOException ? "" : (ex instanceof ErroNegocioException ? "" : "")), JOptionPane.ERROR_MESSAGE);
                    limpar();
                    habilitarComponentes(false);
                    limparTabela();
                }

            }
        }

    }//GEN-LAST:event_jBtnAtualizarActionPerformed

    private void jBtnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnPesquisarActionPerformed
        // TODO add your handling code here:
        if(!jComboCampo.getSelectedItem().toString().equals("Ativos") && !jComboCampo.getSelectedItem().toString().equals("Bloqueados") && !jComboCampo.getSelectedItem().toString().equals("Suspensos") && !jComboCampo.getSelectedItem().toString().equals("Todos")){
            if(jTConsulta.getText().trim().equals("")){
                JOptionPane.showMessageDialog(rootPane, "Para realizar esta pesquisa é necessário preencher o Campo Consulta de acordo.", "Campo Obrigatório!", JOptionPane.WARNING_MESSAGE);
                jTConsulta.requestFocus();
            }else{
                limpar();
                habilitarComponentes(false);
                limparTabela();
                listar(jTConsulta.getText().trim(), jComboCampo.getSelectedItem().toString());
                jTConsulta.setText("");
                jTConsulta.requestFocus();
            }
        }else if(jComboCampo.getSelectedItem().toString().equals("Todos")){
            limpar();
            habilitarComponentes(false);
            limparTabela();
            listar("Todos", "Todos");
        }else{
            limpar();
            habilitarComponentes(false);
            limparTabela();
            listar("", jComboCampo.getSelectedItem().toString());
        }

    }//GEN-LAST:event_jBtnPesquisarActionPerformed

    private void jBtnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnExcluirActionPerformed
        // TODO add your handling code here:
        Object[] botoes = {"Sim", "Não"};
        int opcao = JOptionPane.showOptionDialog(rootPane, "Deseja excluir os dados " + (medico.getSexo().equals("Masculino") ? "do Médico " : "da Médica ") + " do sistema?", "Confirmar Exclusão do Registro!", JOptionPane.QUESTION_MESSAGE, JOptionPane.YES_NO_OPTION, null, botoes, botoes[1]);
        if (opcao == JOptionPane.YES_OPTION) {
            prepare();
            try {
                controlMedico.excluirMedico(medico);
                JOptionPane.showMessageDialog(rootPane, "Os dados " + (medico.getSexo().equals("Masculino") ? "do Médico " : "da Médica ") + medico.getNome() + " foram removidos com sucesso no sistema!", (medico.getSexo().equals("Masculino") ? "Médico removido com sucesso" : "Médica removida com sucesso!"), JOptionPane.INFORMATION_MESSAGE);
                limpar();
                habilitarComponentes(false);
                limparTabela();
            } catch (ErroDAOException | ErroConectarException | ErroNegocioException | SQLException ex) {
                JOptionPane.showMessageDialog(rootPane, ex.getMessage(), (ex instanceof ErroDAOException ? "Erro de Query SQL!" : (ex instanceof ErroNegocioException ? "Erro de Validação de Dados!" : "Erro ao conectar com o banco de dados!")), JOptionPane.ERROR_MESSAGE);
                limpar();
                habilitarComponentes(false);
                limparTabela();
            }
        } else {
            limpar();
            habilitarComponentes(false);
            limparTabela();
        }
    }//GEN-LAST:event_jBtnExcluirActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnAtualizar;
    private javax.swing.JButton jBtnExcluir;
    private javax.swing.JButton jBtnPesquisar;
    private javax.swing.JComboBox<String> jComboCampo;
    private javax.swing.JComboBox<String> jComboFuncao;
    private javax.swing.JComboBox<String> jComboSexo;
    private javax.swing.JComboBox<String> jComboUF;
    private javax.swing.JFormattedTextField jFCEP;
    private javax.swing.JFormattedTextField jFCPF;
    private javax.swing.JFormattedTextField jFCRM;
    private javax.swing.JFormattedTextField jFCelular;
    private javax.swing.JFormattedTextField jFDataAdmissao;
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
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPasswordField jPSenha;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTBairro;
    private javax.swing.JTextField jTCidade;
    private javax.swing.JTextField jTComplemento;
    private javax.swing.JTextField jTConsulta;
    private javax.swing.JTextField jTEmail;
    private javax.swing.JTextField jTIdade;
    private javax.swing.JTextField jTLogin;
    private javax.swing.JTextField jTNome;
    private javax.swing.JTextField jTNumeroResidencia;
    private javax.swing.JTextField jTRG;
    private javax.swing.JTextField jTRua;
    private javax.swing.JTextField jTSalario;
    private javax.swing.JTable jTabelaMedico;
    // End of variables declaration//GEN-END:variables
}
