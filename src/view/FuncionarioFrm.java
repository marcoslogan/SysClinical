/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import model.Funcionario;
import controller.FuncionarioController;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Contato;
import model.Endereco;
import utils.ErroConectarException;
import utils.ErroDAOException;
import utils.ErroNegocioException;

/**
 *
 * @author MARCOS
 */
public class FuncionarioFrm extends javax.swing.JFrame {

    private Funcionario funcionario;
    private Funcionario func;
    private FuncionarioController controlFuncionario;
    private List<Funcionario> funcionarios = new ArrayList<>();
    private int linhaSelecionada = -1;
    private DefaultTableModel model;
    private String[] messages;
    private SimpleDateFormat formatFunc;
    private DefaultComboBoxModel modelUF = new DefaultComboBoxModel();
    private DefaultComboBoxModel modelSexo = new DefaultComboBoxModel();
    private DefaultComboBoxModel modelFuncao = new DefaultComboBoxModel();

    /**
     * Creates new form FuncionarioFrm
     */
    public FuncionarioFrm(Funcionario employee) {
        initComponents();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{"Código", "Nome", "Data de Admissão", "Data de Nascimento"});
        jTabelaFuncionario.setModel(model);
        controlFuncionario = FuncionarioController.getCurrentInstance();
        this.func = employee;
        formatFunc = new SimpleDateFormat("dd/MM/yyyy");
    }

    private void habilitarComponentes(boolean isLiberated) {
        jTNome.setEnabled(isLiberated);
        jTRua.setEnabled(isLiberated);
        jTCidade.setEnabled(isLiberated);
        jTBairro.setEnabled(isLiberated);
        jFCEP.setEnabled(isLiberated);
        jTComplemento.setEnabled(isLiberated);
        jComboUF.setEnabled(isLiberated);
        jTNumeroResidencia.setEnabled(isLiberated);
        jTLogin.setEnabled(isLiberated);
        jPSenha.setEnabled(isLiberated);
        jTRG.setEnabled(isLiberated);
        jFCPF.setEnabled(isLiberated);
        jFDataNascimento.setEnabled(isLiberated);
        jTSalario.setEnabled(isLiberated);
        jComboFuncao.setEnabled(isLiberated);
        jComboSexo.setEnabled(isLiberated);
        jFTelefone.setEnabled(isLiberated);
        jFCelular.setEnabled(isLiberated);
        jTEmail.setEnabled(isLiberated);
        jBtnAlterar.setEnabled(isLiberated);
        jBtnExcluir.setEnabled(isLiberated);
        jTNome.requestFocus();
    }

    private void limpar() {
        jTNome.setText("");
        jTRua.setText("");
        jTCidade.setText("");
        jTBairro.setText("");
        jFCEP.setValue(null);
        jTComplemento.setText("");
        jComboUF.setSelectedItem(0);
        jTNumeroResidencia.setText("");
        jTLogin.setText("");
        jPSenha.setText("");
        jTRG.setText("");
        jFCPF.setValue(null);
        jFDataAdmissao.setValue(null);
        jTIdade.setText("");
        jFDataNascimento.setValue(null);
        jTSalario.setText("");
        modelFuncao.removeAllElements();
        modelFuncao.addElement("Função");
        jComboFuncao.setModel(modelFuncao);
        jComboFuncao.setSelectedItem(0);
        modelSexo.removeAllElements();
        modelSexo.addElement("Sexo");
        jComboSexo.setModel(modelSexo);
        jComboSexo.setSelectedItem(0);
        jFTelefone.setValue(null);
        jFCelular.setValue(null);
        jTEmail.setText("");
    }

    private void listar(String consulta, String campo) {
        try {

            while (model.getRowCount() > 0) {
                model.removeRow(0);
            }

            funcionarios = controlFuncionario.listarFuncionarios(consulta, campo);
            funcionarios.forEach((func) -> {
                model.addRow(new String[]{String.valueOf(func.getCodigo()), func.getNome(), formatFunc.format(func.getDataAdmissao()), formatFunc.format(func.getDataNascimento())});
            });
            jTabelaFuncionario.setModel(model);
        } catch (ErroDAOException | ErroConectarException | ErroNegocioException | SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), (ex instanceof ErroDAOException ? "Erro de Query SQL!" : (ex instanceof ErroNegocioException ? "Erro de Validação de Dados!" : "Erro ao conectar com o banco de dados!")), JOptionPane.ERROR_MESSAGE);
            limpar();
            limparTabela();
        }
    }

    private void preencher() {
        Funcionario f = funcionarios.get(linhaSelecionada);
        jTNome.setText(f.getNome());
        jTRua.setText(f.getEndereco().getRua());
        jTCidade.setText(f.getEndereco().getCidade());
        jTBairro.setText(f.getEndereco().getBairro());
        jFCEP.setText(f.getEndereco().getCep());
        jTComplemento.setText(f.getEndereco().getComplemento());
         

        switch (f.getEndereco().getUf()) {
            case "Acre":
                modelUF.addElement(f.getEndereco().getUf());
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
                modelUF.addElement(f.getEndereco().getUf());
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
                modelUF.addElement("Rondônia");
                modelUF.addElement("Roraima");
                modelUF.addElement("Santa Catarina");
                modelUF.addElement("São Paulo");
                modelUF.addElement("Sergipe");
                modelUF.addElement("Tocantins");

                break;
            case "Amapá":
                modelUF.addElement(f.getEndereco().getUf());
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
                modelUF.addElement(f.getEndereco().getUf());
                modelUF.addElement("Acre");
                modelUF.addElement("Alagoas");
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
                modelUF.addElement(f.getEndereco().getUf());
                modelUF.addElement("Acre");
                modelUF.addElement("Alagoas");
                modelUF.addElement("Amazonas");
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
                modelUF.addElement(f.getEndereco().getUf());
                modelUF.addElement("Acre");
                modelUF.addElement("Alagoas");
                modelUF.addElement("Amazonas");
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
                modelUF.addElement(f.getEndereco().getUf());
                modelUF.addElement("Acre");
                modelUF.addElement("Alagoas");
                modelUF.addElement("Amazonas");
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
                modelUF.addElement(f.getEndereco().getUf());
                modelUF.addElement("Acre");
                modelUF.addElement("Alagoas");
                modelUF.addElement("Amazonas");
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
                modelUF.addElement(f.getEndereco().getUf());
                modelUF.addElement("Acre");
                modelUF.addElement("Alagoas");
                modelUF.addElement("Amazonas");
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
                modelUF.addElement(f.getEndereco().getUf());
                modelUF.addElement("Acre");
                modelUF.addElement("Alagoas");
                modelUF.addElement("Amazonas");
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
                modelUF.addElement(f.getEndereco().getUf());
                modelUF.addElement("Acre");
                modelUF.addElement("Alagoas");
                modelUF.addElement("Amazonas");
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
                modelUF.addElement(f.getEndereco().getUf());
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
                modelUF.addElement(f.getEndereco().getUf());
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
                modelUF.addElement(f.getEndereco().getUf());
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
                modelUF.addElement(f.getEndereco().getUf());
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
                modelUF.addElement(f.getEndereco().getUf());
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
                modelUF.addElement(f.getEndereco().getUf());
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
                modelUF.addElement(f.getEndereco().getUf());
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
                modelUF.addElement(f.getEndereco().getUf());
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
                modelUF.addElement("Rio Grande do Sul");
                modelUF.addElement("Rondônia");
                modelUF.addElement("Roraima");
                modelUF.addElement("Santa Catarina");
                modelUF.addElement("São Paulo");
                modelUF.addElement("Sergipe");
                modelUF.addElement("Tocantins");
                break;
            case "Rio Grande do Sul":
                modelUF.addElement(f.getEndereco().getUf());
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
                modelUF.addElement("Rondônia");
                modelUF.addElement("Roraima");
                modelUF.addElement("Santa Catarina");
                modelUF.addElement("São Paulo");
                modelUF.addElement("Sergipe");
                modelUF.addElement("Tocantins");
                break;
            case "Rondônia":
                modelUF.addElement(f.getEndereco().getUf());
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
                modelUF.addElement("Roraima");
                modelUF.addElement("Santa Catarina");
                modelUF.addElement("São Paulo");
                modelUF.addElement("Sergipe");
                modelUF.addElement("Tocantins");
                break;
            case "Roraima":
                modelUF.addElement(f.getEndereco().getUf());
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
                modelUF.addElement("Santa Catarina");
                modelUF.addElement("São Paulo");
                modelUF.addElement("Sergipe");
                modelUF.addElement("Tocantins");
                break;
            case "Santa Catarina":
                modelUF.addElement(f.getEndereco().getUf());
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
                modelUF.addElement("São Paulo");
                modelUF.addElement("Sergipe");
                modelUF.addElement("Tocantins");
                break;
            case "São Paulo":
                modelUF.addElement(f.getEndereco().getUf());
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
                modelUF.addElement("Sergipe");
                modelUF.addElement("Tocantins");
                break;
            case "Sergipe":
                modelUF.addElement(f.getEndereco().getUf());
                modelUF.addElement("Acre");
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
                modelUF.addElement("Tocantins");
                break;
            case "Tocantins":
                modelUF.addElement(f.getEndereco().getUf());
                modelUF.addElement("Acre");
                modelUF.addElement("Alagoas");
                modelUF.addElement("Amapá");
                modelUF.addElement("Amazonas");
                modelUF.addElement("Bahia");
                modelUF.addElement("Ceará");
                modelUF.addElement("Distrito Federal");
                modelUF.addElement("Espírito Santo");
                modelUF.addElement("Goiás");
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
                break;
        }
        
        jComboUF.setModel(modelUF);

        jTNumeroResidencia.setText(f.getEndereco().getNumeroResidencia());
        jTLogin.setText(f.getLogin());
        jPSenha.setText(f.getSenha());
        jTRG.setText(f.getRg());
        jFCPF.setText(f.getCpf());
        jFDataAdmissao.setText(formatFunc.format(f.getDataAdmissao()));
        jTIdade.setText(String.valueOf(f.getIdade()));
        jFDataNascimento.setText(formatFunc.format(f.getDataNascimento()));
        DecimalFormat formatDecimal = new DecimalFormat("###,##0.00");
        jTSalario.setText(formatDecimal.format(f.getSalario()));
        modelFuncao.removeAllElements();
        switch(f.getFuncao()){
            case "Administrador":
                modelFuncao.addElement(f.getFuncao());
                modelFuncao.addElement("Atendente");
                break;
            case "Atendente":
                modelFuncao.addElement(f.getFuncao());
                modelFuncao.addElement("Administrador");
                break;
        }
        jComboFuncao.setModel(modelFuncao);
        
        modelSexo.removeAllElements();
        switch(f.getSexo()){
            case "Feminino":
                modelSexo.addElement(f.getSexo());
                modelSexo.addElement("Masculino");
                modelSexo.addElement("Homossexual");
                modelSexo.addElement("Trans");
                modelSexo.addElement("Outros");
                break;
            case "Masculino":
                modelSexo.addElement(f.getSexo());
                modelSexo.addElement("Feminino");
                modelSexo.addElement("Homossexual");
                modelSexo.addElement("Trans");
                modelSexo.addElement("Outros");
                break;
            case "Homossexual":
                modelSexo.addElement(f.getSexo());
                modelSexo.addElement("Feminino");
                modelSexo.addElement("Masculino");
                modelSexo.addElement("Trans");
                modelSexo.addElement("Outros");
                break;
            case "Trans":
                modelSexo.addElement(f.getSexo());
                modelSexo.addElement("Feminino");
                modelSexo.addElement("Masculino");
                modelSexo.addElement("Homossexual");
                modelSexo.addElement("Outros");
                break;
            case "Outros":
                modelSexo.addElement(f.getSexo());
                modelSexo.addElement("Feminino");
                modelSexo.addElement("Masculino");
                modelSexo.addElement("Homossexual");
                modelSexo.addElement("Trans");
                break;
        }
        jComboSexo.setModel(modelSexo);
        jFTelefone.setText(f.getContato().getTelefone());
        jFCelular.setText(f.getContato().getCelular());
        jTEmail.setText(f.getContato().getEmail());
    }

    private void prepare() {
        funcionario = new Funcionario();
        funcionario.setCodigo(funcionarios.get(linhaSelecionada).getCodigo());
        funcionario.setNome(jTNome.getText().trim());
        Endereco endereco = new Endereco();
        endereco.setRua(jTRua.getText().trim());
        endereco.setCidade(jTCidade.getText().trim());
        endereco.setBairro(jTBairro.getText().trim());
        endereco.setCep(jFCEP.getText().trim());
        endereco.setComplemento(jTComplemento.getText().trim());
        endereco.setNumeroResidencia(jTNumeroResidencia.getText().trim());
        endereco.setUf(jComboUF.getSelectedItem().toString());
        funcionario.setEndereco(endereco);
        funcionario.setRg(jTRG.getText().trim());
        funcionario.setCpf(jFCPF.getText().trim());
        funcionario.setLogin(jTLogin.getText().trim());
        funcionario.setSenha(new String(jPSenha.getPassword()));
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        try {
            funcionario.setDataNascimento(format.parse(jFDataNascimento.getText().trim()));
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Data Inválida!", JOptionPane.ERROR_MESSAGE);
        }
        funcionario.setIdade(Age.calculateAge(jFDataNascimento.getText().trim(), "dd/MM/yyyy"));
        funcionario.setSexo(jComboSexo.getSelectedItem().toString());
        funcionario.setSalario(Double.parseDouble(jTSalario.getText().trim()));
        funcionario.setFuncao(jComboFuncao.getSelectedItem().toString());
        Contato contato = new Contato();
        contato.setTelefone(jFTelefone.getText().trim());
        contato.setCelular(jFCelular.getText().trim());
        contato.setEmail(jTEmail.getText().trim());
        funcionario.setContato(contato);
    }

    private void limparTabela() {
        while (model.getRowCount() > 0) {
            model.removeRow(0);
        }

        jTabelaFuncionario.setModel(model);
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
        jLabel3 = new javax.swing.JLabel();
        jTNome = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTRua = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTCidade = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTBairro = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jFCEP = new javax.swing.JFormattedTextField();
        jLabel9 = new javax.swing.JLabel();
        jTComplemento = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jComboUF = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        jTNumeroResidencia = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jTLogin = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jPSenha = new javax.swing.JPasswordField();
        jLabel14 = new javax.swing.JLabel();
        jTRG = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jFCPF = new javax.swing.JFormattedTextField();
        jLabel16 = new javax.swing.JLabel();
        jFDataAdmissao = new javax.swing.JFormattedTextField();
        jLabel17 = new javax.swing.JLabel();
        jTIdade = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jFDataNascimento = new javax.swing.JFormattedTextField();
        jLabel19 = new javax.swing.JLabel();
        jTSalario = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jComboFuncao = new javax.swing.JComboBox<>();
        jLabel21 = new javax.swing.JLabel();
        jComboSexo = new javax.swing.JComboBox<>();
        jLabel22 = new javax.swing.JLabel();
        jFTelefone = new javax.swing.JFormattedTextField();
        jLabel23 = new javax.swing.JLabel();
        jFCelular = new javax.swing.JFormattedTextField();
        jLabel24 = new javax.swing.JLabel();
        jTEmail = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTabelaFuncionario = new javax.swing.JTable();
        jLabel25 = new javax.swing.JLabel();
        jTConsulta = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jComboCampo = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jBtnAlterar = new javax.swing.JButton();
        jBtnExcluir = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setOpacity(0.9F);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Lucida Handwriting", 2, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Dados Cadastrais do Funcionário");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 40, 480, -1));

        jLabel3.setFont(new java.awt.Font("Lucida Handwriting", 2, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Nome");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, 50, -1));

        jTNome.setFont(new java.awt.Font("Lucida Calligraphy", 2, 13)); // NOI18N
        jTNome.setEnabled(false);
        getContentPane().add(jTNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 110, 740, -1));

        jLabel4.setFont(new java.awt.Font("Lucida Handwriting", 2, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Rua");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, 40, -1));

        jTRua.setFont(new java.awt.Font("Lucida Calligraphy", 2, 13)); // NOI18N
        jTRua.setEnabled(false);
        getContentPane().add(jTRua, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 140, 740, -1));

        jLabel5.setFont(new java.awt.Font("Lucida Handwriting", 2, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Cidade");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 170, 60, -1));

        jTCidade.setFont(new java.awt.Font("Lucida Calligraphy", 2, 13)); // NOI18N
        jTCidade.setEnabled(false);
        getContentPane().add(jTCidade, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 170, 280, -1));

        jLabel6.setFont(new java.awt.Font("Lucida Calligraphy", 2, 36)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("X");
        jLabel6.setToolTipText("Fecha a Tela de Cadastro de Funcionário");
        jLabel6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 0, 50, -1));

        jLabel7.setFont(new java.awt.Font("Lucida Handwriting", 2, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Bairro");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 170, 60, -1));

        jTBairro.setFont(new java.awt.Font("Lucida Calligraphy", 2, 13)); // NOI18N
        jTBairro.setEnabled(false);
        getContentPane().add(jTBairro, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 170, 190, -1));

        jLabel8.setFont(new java.awt.Font("Lucida Handwriting", 2, 13)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("CEP");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 170, 40, -1));

        try {
            jFCEP.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#####-###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFCEP.setEnabled(false);
        jFCEP.setFont(new java.awt.Font("Lucida Calligraphy", 2, 13)); // NOI18N
        getContentPane().add(jFCEP, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 170, 150, -1));

        jLabel9.setFont(new java.awt.Font("Lucida Handwriting", 2, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Complemento");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 200, 110, -1));

        jTComplemento.setFont(new java.awt.Font("Lucida Calligraphy", 2, 13)); // NOI18N
        jTComplemento.setEnabled(false);
        getContentPane().add(jTComplemento, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 200, 700, -1));

        jLabel10.setFont(new java.awt.Font("Lucida Handwriting", 2, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("UF");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 230, 40, -1));

        jComboUF.setFont(new java.awt.Font("Lucida Calligraphy", 2, 13)); // NOI18N
        jComboUF.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Estado do Funcionário" }));
        jComboUF.setEnabled(false);
        getContentPane().add(jComboUF, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 230, 190, -1));

        jLabel11.setFont(new java.awt.Font("Lucida Handwriting", 2, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Nº de Residência");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 230, 130, -1));

        jTNumeroResidencia.setFont(new java.awt.Font("Lucida Calligraphy", 2, 13)); // NOI18N
        jTNumeroResidencia.setEnabled(false);
        getContentPane().add(jTNumeroResidencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 230, 50, -1));

        jLabel12.setFont(new java.awt.Font("Lucida Handwriting", 2, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Login");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 230, 50, -1));

        jTLogin.setFont(new java.awt.Font("Lucida Calligraphy", 2, 13)); // NOI18N
        jTLogin.setEnabled(false);
        getContentPane().add(jTLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 230, 120, -1));

        jLabel13.setFont(new java.awt.Font("Lucida Calligraphy", 2, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Senha");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 230, 60, -1));

        jPSenha.setFont(new java.awt.Font("Lucida Calligraphy", 2, 13)); // NOI18N
        jPSenha.setEnabled(false);
        getContentPane().add(jPSenha, new org.netbeans.lib.awtextra.AbsoluteConstraints(721, 230, 120, -1));

        jLabel14.setFont(new java.awt.Font("Lucida Handwriting", 2, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("RG");
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 260, 30, -1));

        jTRG.setFont(new java.awt.Font("Lucida Calligraphy", 2, 13)); // NOI18N
        jTRG.setEnabled(false);
        getContentPane().add(jTRG, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 260, 140, -1));

        jLabel15.setFont(new java.awt.Font("Lucida Handwriting", 2, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("CPF");
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 260, 40, -1));

        try {
            jFCPF.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFCPF.setEnabled(false);
        jFCPF.setFont(new java.awt.Font("Lucida Calligraphy", 2, 13)); // NOI18N
        getContentPane().add(jFCPF, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 260, 160, -1));

        jLabel16.setFont(new java.awt.Font("Lucida Handwriting", 2, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Data de Admissão");
        getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 260, 140, -1));

        try {
            jFDataAdmissao.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFDataAdmissao.setEnabled(false);
        jFDataAdmissao.setFont(new java.awt.Font("Lucida Calligraphy", 2, 13)); // NOI18N
        getContentPane().add(jFDataAdmissao, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 260, 100, -1));

        jLabel17.setFont(new java.awt.Font("Lucida Handwriting", 2, 12)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Idade");
        getContentPane().add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 260, 60, -1));

        jTIdade.setFont(new java.awt.Font("Lucida Calligraphy", 2, 13)); // NOI18N
        jTIdade.setEnabled(false);
        getContentPane().add(jTIdade, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 260, 70, -1));

        jLabel18.setFont(new java.awt.Font("Lucida Handwriting", 2, 12)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Data de Nascimento");
        getContentPane().add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 290, 150, -1));

        try {
            jFDataNascimento.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFDataNascimento.setEnabled(false);
        jFDataNascimento.setFont(new java.awt.Font("Lucida Calligraphy", 2, 13)); // NOI18N
        getContentPane().add(jFDataNascimento, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 290, 100, -1));

        jLabel19.setFont(new java.awt.Font("Lucida Handwriting", 2, 12)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Salário R$");
        getContentPane().add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 290, 90, -1));

        jTSalario.setFont(new java.awt.Font("Lucida Calligraphy", 2, 13)); // NOI18N
        jTSalario.setEnabled(false);
        getContentPane().add(jTSalario, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 290, 90, -1));

        jLabel20.setFont(new java.awt.Font("Lucida Handwriting", 2, 12)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Função");
        getContentPane().add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 290, 60, -1));

        jComboFuncao.setFont(new java.awt.Font("Lucida Calligraphy", 2, 13)); // NOI18N
        jComboFuncao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Função" }));
        jComboFuncao.setEnabled(false);
        getContentPane().add(jComboFuncao, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 290, 140, -1));

        jLabel21.setFont(new java.awt.Font("Lucida Handwriting", 2, 12)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Sexo");
        getContentPane().add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 290, 40, -1));

        jComboSexo.setFont(new java.awt.Font("Lucida Calligraphy", 2, 13)); // NOI18N
        jComboSexo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sexo do Funcionário" }));
        jComboSexo.setEnabled(false);
        getContentPane().add(jComboSexo, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 290, 110, -1));

        jLabel22.setFont(new java.awt.Font("Lucida Handwriting", 2, 12)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("Telefone");
        getContentPane().add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 320, 70, -1));

        try {
            jFTelefone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(###)####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFTelefone.setEnabled(false);
        jFTelefone.setFont(new java.awt.Font("Lucida Calligraphy", 2, 13)); // NOI18N
        getContentPane().add(jFTelefone, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 320, 130, -1));

        jLabel23.setFont(new java.awt.Font("Lucida Handwriting", 2, 12)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("Celular");
        getContentPane().add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 320, 70, -1));

        try {
            jFCelular.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(###)#####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFCelular.setEnabled(false);
        jFCelular.setFont(new java.awt.Font("Lucida Calligraphy", 2, 13)); // NOI18N
        jFCelular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFCelularActionPerformed(evt);
            }
        });
        getContentPane().add(jFCelular, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 320, 120, -1));

        jLabel24.setFont(new java.awt.Font("Lucida Handwriting", 2, 12)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("E-Mail");
        getContentPane().add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 320, 60, -1));

        jTEmail.setFont(new java.awt.Font("Lucida Calligraphy", 2, 13)); // NOI18N
        jTEmail.setEnabled(false);
        getContentPane().add(jTEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 320, 310, -1));

        jTabelaFuncionario.setFont(new java.awt.Font("Lucida Calligraphy", 2, 13)); // NOI18N
        jTabelaFuncionario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Código", "Nome", "Data de Admissão", "Data de Nascimento"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Long.class, java.lang.Long.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTabelaFuncionario.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTabelaFuncionario.setEnabled(false);
        jTabelaFuncionario.setSelectionBackground(new java.awt.Color(191, 158, 35));
        jTabelaFuncionario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabelaFuncionarioMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTabelaFuncionario);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 360, 830, 100));

        jLabel25.setFont(new java.awt.Font("Lucida Handwriting", 2, 12)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("Consulta");
        getContentPane().add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 470, 80, -1));

        jTConsulta.setFont(new java.awt.Font("Lucida Calligraphy", 2, 13)); // NOI18N
        getContentPane().add(jTConsulta, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 500, 170, -1));

        jLabel26.setFont(new java.awt.Font("Lucida Handwriting", 2, 12)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("Campo");
        getContentPane().add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 470, 70, -1));

        jComboCampo.setFont(new java.awt.Font("Lucida Calligraphy", 2, 13)); // NOI18N
        jComboCampo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ativos", "Todos", "Código", "Nome", "CPF", "Bloqueados", "Suspensos", "Funcionário mais Recente", " " }));
        jComboCampo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboCampoActionPerformed(evt);
            }
        });
        getContentPane().add(jComboCampo, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 500, 170, -1));

        jButton1.setFont(new java.awt.Font("Lucida Handwriting", 2, 12)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/if_search_magnifying_glass_find_103857.png"))); // NOI18N
        jButton1.setText("Pesquisar");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 490, 130, 50));

        jBtnAlterar.setFont(new java.awt.Font("Lucida Handwriting", 2, 12)); // NOI18N
        jBtnAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/if_db_update_3213.png"))); // NOI18N
        jBtnAlterar.setText("Alterar");
        jBtnAlterar.setEnabled(false);
        jBtnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnAlterarActionPerformed(evt);
            }
        });
        getContentPane().add(jBtnAlterar, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 490, 120, 50));

        jBtnExcluir.setFont(new java.awt.Font("Lucida Handwriting", 2, 12)); // NOI18N
        jBtnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/if_delete_71076.png"))); // NOI18N
        jBtnExcluir.setText("Excluir");
        jBtnExcluir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBtnExcluir.setEnabled(false);
        jBtnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnExcluirActionPerformed(evt);
            }
        });
        getContentPane().add(jBtnExcluir, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 490, -1, 50));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/back_funcionario.jpg"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 560));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jLabel6MouseClicked

    private void jFCelularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFCelularActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFCelularActionPerformed

    private void jBtnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnAlterarActionPerformed
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
            messages = MessageView.requiredField("Cidade");
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
        } else if (jTComplemento.getText().trim().equals("")) {
            messages = MessageView.requiredField("Complemento");
            JOptionPane.showMessageDialog(rootPane, messages[0], messages[1], JOptionPane.WARNING_MESSAGE);
            jTNome.requestFocus();
        } else if (jTNumeroResidencia.getText().trim().equals("")) {
            messages = MessageView.requiredField("Nº de Residência");
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
            } else if (jTRG.getText().trim().equals("")) {
                messages = MessageView.requiredField("RG");
                JOptionPane.showMessageDialog(rootPane, messages[0], messages[1], JOptionPane.WARNING_MESSAGE);
                jTNome.requestFocus();
            } else if (!jFCPF.isEditValid()) {
                messages = MessageView.requiredField("CPF");
                JOptionPane.showMessageDialog(rootPane, messages[0], messages[1], JOptionPane.WARNING_MESSAGE);
                jTNome.requestFocus();
            } else if (!jFDataNascimento.isEditValid()) {
                messages = MessageView.requiredField("Data de Nascimento");
                JOptionPane.showMessageDialog(rootPane, messages[0], messages[1], JOptionPane.WARNING_MESSAGE);
                jTNome.requestFocus();
            } else if (jTSalario.getText().trim().equals("")) {
                messages = MessageView.requiredField("Salário");
                JOptionPane.showMessageDialog(rootPane, messages[0], messages[1], JOptionPane.WARNING_MESSAGE);
                jTNome.requestFocus();
            } else if (!jFTelefone.isEditValid()) {
                messages = MessageView.requiredField("Telefone");
                JOptionPane.showMessageDialog(rootPane, messages[0], messages[1], JOptionPane.WARNING_MESSAGE);
                jTNome.requestFocus();
            } else if (!jFCelular.isEditValid()) {
                messages = MessageView.requiredField("Celular");
                JOptionPane.showMessageDialog(rootPane, messages[0], messages[1], JOptionPane.WARNING_MESSAGE);
                jTNome.requestFocus();
            } else if (jTEmail.getText().trim().equals("")) {
                messages = MessageView.requiredField("E-Mail");
                JOptionPane.showMessageDialog(rootPane, messages[0], messages[1], JOptionPane.WARNING_MESSAGE);
                jTNome.requestFocus();
            } else {
                prepare();
                try {
                    controlFuncionario.alterarFuncionario(funcionario);
                    JOptionPane.showMessageDialog(rootPane, "Os dados " + (funcionario.getSexo().equals("Masculino") ? "do Funcionário " : "da Funcionária ") + funcionario.getNome() + " foram atualizados com sucesso no sistema.", (funcionario.getSexo().equals("Masculino") ? "Funcionário atualizado com sucesso!" : "Funcionária atualizada com sucesso!"), JOptionPane.INFORMATION_MESSAGE);
                    limpar();
                    habilitarComponentes(false);
                    limparTabela();
                    jTConsulta.requestFocus();
                } catch (ErroDAOException | ErroConectarException | ErroNegocioException | SQLException ex) {
                    JOptionPane.showMessageDialog(rootPane, ex.getMessage(), (ex instanceof ErroDAOException ? "Erro de Query SQL!" : (ex instanceof ErroNegocioException ? "Erro de Validação de Dados!" : "Erro ao tentar conectar com o banco de dados.")), JOptionPane.ERROR_MESSAGE);
                    limpar();
                    habilitarComponentes(false);
                    limparTabela();
                }
            }
        }

    }//GEN-LAST:event_jBtnAlterarActionPerformed

    private void jTabelaFuncionarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabelaFuncionarioMouseClicked
        // TODO add your handling code here:
        linhaSelecionada = jTabelaFuncionario.getSelectedRow();
        preencher();
        habilitarComponentes(true);

    }//GEN-LAST:event_jTabelaFuncionarioMouseClicked

    private void jBtnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnExcluirActionPerformed
        // TODO add your handling code here:
        Object[] botoes = {"Sim", "Não"};
        int opcao = JOptionPane.showOptionDialog(rootPane, "Deseja excluir o registro?", "Confirmar!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, botoes, botoes[1]);
        if (opcao == JOptionPane.YES_OPTION) {
            if (this.func.getCodigo() == funcionarios.get(linhaSelecionada).getCodigo()) {
                JOptionPane.showMessageDialog(rootPane, "É proibido excluir os dados do Funcionário que está logado no sistema.", "Operação Proibida!", JOptionPane.ERROR_MESSAGE);
                habilitarComponentes(false);
                limpar();
                limparTabela();
                jTConsulta.requestFocus();
            } else {
                prepare();
                try {
                    controlFuncionario.excluirFuncionario(funcionario);
                    JOptionPane.showMessageDialog(rootPane, "Os dados " + (funcionario.getSexo().equals("Masculino") ? "do Funcionário " : "da Funcionária ") + funcionario.getNome() + " foram removidos com sucesso do sistema.", (funcionario.getSexo().equals("Masculino") ? "Funcionário removido com sucesso!" : "Funcionária removida com sucesso!"), JOptionPane.INFORMATION_MESSAGE);
                    limpar();
                    habilitarComponentes(false);
                    limparTabela();
                    jTConsulta.requestFocus();
                } catch (ErroDAOException | ErroConectarException | ErroNegocioException | SQLException ex) {
                    JOptionPane.showMessageDialog(rootPane, ex.getMessage(), (ex instanceof ErroDAOException ? "Erro de Query SQL!" : (ex instanceof ErroNegocioException ? "Erro de Validação de Dados!" : "Erro ao tentar conectar com o banco de dados.")), JOptionPane.ERROR_MESSAGE);
                    limpar();
                    habilitarComponentes(false);
                    limparTabela();
                }
            }
        } else {
            habilitarComponentes(false);
            limpar();
            limparTabela();
        }

    }//GEN-LAST:event_jBtnExcluirActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if (jComboCampo.getSelectedItem().toString().equals("Ativos") || jComboCampo.getSelectedItem().toString().equals("Bloqueados")) {
            limpar();
            habilitarComponentes(false);
            listar("", jComboCampo.getSelectedItem().toString());
            jTabelaFuncionario.setEnabled(true);
        } else if (jComboCampo.getSelectedItem().toString().equals("Todos")) {
            listar("Todos", "Todos");
            jTabelaFuncionario.setEnabled(true);
        } else {
            if (jTConsulta.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(rootPane, "Para realizar esta pesquisa é imprescidível que forneça os dados corretos.", "Campo Obrigatório para realizar a pesquisa", JOptionPane.WARNING_MESSAGE);
                jTConsulta.requestFocus();
            } else {
                listar(jTConsulta.getText().trim(), jComboCampo.getSelectedItem().toString());
                jTabelaFuncionario.setEnabled(true);
                jTConsulta.setText("");
                jTConsulta.requestFocus();
            }
        }


    }//GEN-LAST:event_jButton1ActionPerformed

    private void jComboCampoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboCampoActionPerformed
        // TODO add your handling code here:
        if (jComboCampo.isPopupVisible()) {
            limpar();
            habilitarComponentes(false);
            limparTabela();

            if (!jComboCampo.getSelectedItem().toString().equals("Todos") && !jComboCampo.getSelectedItem().toString().equals("Ativos") && !jComboCampo.getSelectedItem().toString().equals("Bloqueados") && !jComboCampo.getSelectedItem().toString().equals("Suspensos")) {
                jTConsulta.requestFocus();
            }

        }

    }//GEN-LAST:event_jComboCampoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnAlterar;
    private javax.swing.JButton jBtnExcluir;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboCampo;
    private javax.swing.JComboBox<String> jComboFuncao;
    private javax.swing.JComboBox<String> jComboSexo;
    private javax.swing.JComboBox<String> jComboUF;
    private javax.swing.JFormattedTextField jFCEP;
    private javax.swing.JFormattedTextField jFCPF;
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
    private javax.swing.JTable jTabelaFuncionario;
    // End of variables declaration//GEN-END:variables
}
