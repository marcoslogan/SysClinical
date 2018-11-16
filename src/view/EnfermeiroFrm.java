/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import model.Enfermeiro;
import controller.EnfermeiroController;
import static controller.EnfermeiroController.getInstance;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

import utils.ErroConectarException;
import utils.ErroDAOException;
import utils.ErroNegocioException;
/**
 *
 * @author MARCOS
 */
public class EnfermeiroFrm extends javax.swing.JFrame {

    
    private EnfermeiroController controlEnfermeiro;
    private Enfermeiro enfermeiro;
    private DefaultTableModel modelTabela;
    private DefaultComboBoxModel modelUF;
    private DefaultComboBoxModel modelFuncao;
    private DefaultComboBoxModel modelStatus;
    private DefaultComboBoxModel modelSexo;
    private List<Enfermeiro> enfermeiros;
    private SimpleDateFormat format;
    private int linhaSelecionada = -1;
    /**
     * Creates new form EnfermeiroFrm
     */
    public EnfermeiroFrm() {
        initComponents();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        modelTabela = new DefaultTableModel();
        modelUF = new DefaultComboBoxModel();
        modelFuncao = new DefaultComboBoxModel();
        modelStatus = new DefaultComboBoxModel();
        modelSexo = new DefaultComboBoxModel();
        controlEnfermeiro = getInstance();
        format = new SimpleDateFormat("dd/MM/yyyy");
    }
    
    private void limpar(){
        jTNome.setText("");
        jTRua.setText("");
        jTCidade.setText("");
        jTBairro.setText("");
        jFCEP.setValue(null);
        modelUF.removeAllElements();
        modelUF.addElement("UF");
        jComboUF.setModel(modelUF);
        jTNumeroResidencia.setText("");
        jTComplemento.setText("");
        jTRG.setText("");
        jFCPF.setValue(null);
        jTLogin.setText("");
        jPSenha.setText("");
        jTSalario.setText("");
        modelFuncao.removeAllElements();
        modelFuncao.addElement("Função");
        jComboFuncao.setModel(modelFuncao);
        jFDataNascimento.setValue(null);
        jFDataAdmissao.setValue(null);
        modelSexo.removeAllElements();
        modelSexo.addElement("Sexo");
        jComboSexo.setModel(modelSexo);
        jTIdade.setText("");
        jFCoren.setValue(null);
        jFTelefone.setValue(null);
        jFCelular.setValue(null);
        jTEmail.setText("");
    }
    
    private void habilitarComponentes(boolean isLiberated){
        jTNome.setEnabled(isLiberated);
        jTRua.setEnabled(isLiberated);
        jTCidade.setEnabled(isLiberated);
        jTBairro.setEnabled(isLiberated);
        jFCEP.setEnabled(isLiberated);
        jComboUF.setEnabled(isLiberated);
        jTNumeroResidencia.setEnabled(isLiberated);
        jTComplemento.setEnabled(isLiberated);
        jTRG.setEnabled(isLiberated);
        jFCPF.setEnabled(isLiberated);
        jTLogin.setEnabled(isLiberated);
        jPSenha.setEnabled(isLiberated);
        jTSalario.setEnabled(isLiberated);
        jComboFuncao.setEnabled(isLiberated);
        jFDataNascimento.setEnabled(isLiberated);
        jComboSexo.setEnabled(isLiberated);
        jFCoren.setEnabled(isLiberated);
        jFTelefone.setEnabled(isLiberated);
        jFCelular.setEnabled(isLiberated);
        jTEmail.setEnabled(isLiberated);
        jBtnAtualizar.setEnabled(isLiberated);
        jBtnExcluir.setEnabled(isLiberated);
    }
    
    private void listar(String consulta, String campo){
        try {
            
            while(modelTabela.getRowCount() > 0){
                modelTabela.removeRow(0);
            }
            jTabelaEnfermeiro.setModel(modelTabela);
            
            enfermeiros = controlEnfermeiro.listarEnfermeiros(consulta, campo);
            enfermeiros.forEach((enf) -> {
                modelTabela.addRow(new String[]{String.valueOf(enf.getCodigo()), enf.getNome(), format.format(enf.getDataNascimento()), enf.getCorem(), enf.getSituacaoFuncionario()});
            });
            jTabelaEnfermeiro.setModel(modelTabela);
        } catch (ErroDAOException | ErroConectarException | ErroNegocioException | SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), (ex instanceof ErroDAOException ? "" : (ex instanceof ErroNegocioException ? "" : "")), JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void limparTabela(){
        
        while(modelTabela.getRowCount() > 0){
            modelTabela.removeRow(0);
        }
        jTabelaEnfermeiro.setModel(modelTabela);
    }
    
    private void preencher(){
        Enfermeiro nurse = enfermeiros.get(linhaSelecionada);
        jTNome.setText(nurse.getNome());
        jTRua.setText(nurse.getEndereco().getRua());
        jTCidade.setText(nurse.getEndereco().getCidade());
        jTBairro.setText(nurse.getEndereco().getBairro());
        jFCEP.setText(nurse.getEndereco().getCep());
        jTNumeroResidencia.setText(nurse.getEndereco().getNumeroResidencia());
        jTComplemento.setText(nurse.getEndereco().getComplemento());
        switch(nurse.getEndereco().getUf()){
            case "Acre":
                modelUF.addElement(nurse.getEndereco().getUf());
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
                modelUF.addElement(nurse.getEndereco().getUf());
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
                modelUF.addElement(nurse.getEndereco().getUf());
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
                modelUF.addElement(nurse.getEndereco().getUf());
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
                modelUF.addElement("");
                modelUF.addElement("");
                break;
            case "Bahia":
                modelUF.addElement(nurse.getEndereco().getUf());
                modelUF.addElement("Acre");
                modelUF.addElement("Alagoas");
                modelUF.addElement("Amapá");
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
                modelUF.addElement(nurse.getEndereco().getUf());
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                break;
            case "Distrito Federal":
                modelUF.addElement(nurse.getEndereco().getUf());
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                break;
            case "Espírito Santo":
                modelUF.addElement(nurse.getEndereco().getUf());
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                break;
            case "Goiás":
                modelUF.addElement(nurse.getEndereco().getUf());
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                break;
            case "Maranhão":
                modelUF.addElement(nurse.getEndereco().getUf());
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                break;
            case "Mato Grosso":
                modelUF.addElement(nurse.getEndereco().getUf());
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                break;
            case "Mato Grosso do Sul":
                modelUF.addElement(nurse.getEndereco().getUf());
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                break;
            case "Minas Gerais":
                modelUF.addElement(nurse.getEndereco().getUf());
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                break;
            case "Pará":
                modelUF.addElement(nurse.getEndereco().getUf());
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                break;
            case "Paraná":
                modelUF.addElement(nurse.getEndereco().getUf());
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                break;
            case "Pernambuco":
                modelUF.addElement(nurse.getEndereco().getUf());
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                break;
            case "Piauí":
                modelUF.addElement(nurse.getEndereco().getUf());
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                break;
            case "Rio de Janeiro":
                modelUF.addElement(nurse.getEndereco().getUf());
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                break;
            case "Rio Grande do Norte":
                modelUF.addElement(nurse.getEndereco().getUf());
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                break;
            case "Rio Grande do Sul":
                modelUF.addElement(nurse.getEndereco().getUf());
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                break;
            case "Rondônia":
                modelUF.addElement(nurse.getEndereco().getUf());
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                break;
            case "Roraima":
                modelUF.addElement(nurse.getEndereco().getUf());
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                modelUF.addElement("");
                break;
            case "Santa Catarina":
                modelUF.addElement(nurse.getEndereco().getUf());
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
                modelUF.addElement("São Paulo");
                modelUF.addElement("Sergipe");
                modelUF.addElement("Tocantins");
                break;
            case "São Paulo":
                modelUF.addElement(nurse.getEndereco().getUf());
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
                modelUF.addElement("Sergipe");
                modelUF.addElement("Tocantins");
                break;
            case "Sergipe":
                modelUF.addElement(nurse.getEndereco().getUf());
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
                modelUF.addElement(nurse.getEndereco().getUf());
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
                modelUF.addElement("Sergipe");
                break;
        }
        jTRG.setText(nurse.getRg());
        jFCPF.setText(nurse.getCpf());
        jTLogin.setText(nurse.getLogin());
        jPSenha.setText(nurse.getSenha());
        jTSalario.setText(String.valueOf(nurse.getSalario()));
        modelFuncao.removeAllElements();
        switch(nurse.getFuncao()){
            case "Enfermeiro(a)":
                modelFuncao.addElement(nurse.getFuncao());
                modelFuncao.addElement("Enfermeiro(a)-Chefe");
                modelFuncao.addElement("Estagiário");
                break;
            case "Enfermeiro(a)-Chefe":
                modelFuncao.addElement(nurse.getFuncao());
                modelFuncao.addElement("Enfermeiro(a)");
                modelFuncao.addElement("Estagiário(a)");
                break;
            case "Estagiário(a)":
                modelFuncao.addElement(nurse.getFuncao());
                modelFuncao.addElement("Enfermeiro(a)");
                modelFuncao.addElement("Enfermeiro(a)-Chefe");
                break;
        }
        jComboFuncao.setModel(modelFuncao);
        jFDataNascimento.setText(format.format(nurse.getDataNascimento()));
        jFDataAdmissao.setText(format.format(nurse.getDataAdmissao()));
        switch(nurse.getSexo()){
            case "Feminino":
                break;
            case "Masculino":
                break;
            case "Homossexual":
                break;
            case "Trans":
                break;
            case "Outros":
                break;
        }
        
        jComboSexo.setModel(modelSexo);
        jTIdade.setText(String.valueOf(nurse.getIdade()));
        jFCoren.setText(nurse.getCorem());
        jFTelefone.setText(nurse.getContato().getTelefone());
        jFCelular.setText(nurse.getContato().getCelular());
        jTEmail.setText(nurse.getContato().getEmail());
        jTNome.requestFocus();
    }
    
    private void prepare(){
        enfermeiro = new Enfermeiro();
        
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
        jLabel9 = new javax.swing.JLabel();
        jComboUF = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jTNumeroResidencia = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jTComplemento = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jTRG = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jFCPF = new javax.swing.JFormattedTextField();
        jLabel14 = new javax.swing.JLabel();
        jTLogin = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jPSenha = new javax.swing.JPasswordField();
        jLabel16 = new javax.swing.JLabel();
        jTSalario = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jComboFuncao = new javax.swing.JComboBox<>();
        jLabel18 = new javax.swing.JLabel();
        jFDataNascimento = new javax.swing.JFormattedTextField();
        jLabel19 = new javax.swing.JLabel();
        jFDataAdmissao = new javax.swing.JFormattedTextField();
        jLabel20 = new javax.swing.JLabel();
        jComboSexo = new javax.swing.JComboBox<>();
        jLabel21 = new javax.swing.JLabel();
        jTIdade = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTabelaEnfermeiro = new javax.swing.JTable();
        jLabel22 = new javax.swing.JLabel();
        jFCoren = new javax.swing.JFormattedTextField();
        jLabel23 = new javax.swing.JLabel();
        jFTelefone = new javax.swing.JFormattedTextField();
        jLabel24 = new javax.swing.JLabel();
        jFCelular = new javax.swing.JFormattedTextField();
        jLabel25 = new javax.swing.JLabel();
        jTextField11 = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jComboBox4 = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jBtnAtualizar = new javax.swing.JButton();
        jBtnExcluir = new javax.swing.JButton();
        jLabel27 = new javax.swing.JLabel();
        jTEmail = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setOpacity(0.9F);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Lucida Handwriting", 2, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Dados Cadastrais do Enfermeiro(a)");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 30, 520, -1));

        jLabel3.setFont(new java.awt.Font("Lucida Handwriting", 2, 12)); // NOI18N
        jLabel3.setText("Nome");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, 60, 20));

        jTNome.setFont(new java.awt.Font("Lucida Calligraphy", 2, 13)); // NOI18N
        jTNome.setEnabled(false);
        getContentPane().add(jTNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 90, 620, -1));

        jLabel4.setFont(new java.awt.Font("Lucida Handwriting", 2, 12)); // NOI18N
        jLabel4.setText("Rua");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, 50, -1));

        jTRua.setFont(new java.awt.Font("Lucida Calligraphy", 2, 13)); // NOI18N
        jTRua.setEnabled(false);
        getContentPane().add(jTRua, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 120, 620, -1));

        jLabel5.setFont(new java.awt.Font("Lucida Handwriting", 2, 12)); // NOI18N
        jLabel5.setText("Cidade");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 150, 80, -1));

        jTCidade.setFont(new java.awt.Font("Lucida Calligraphy", 2, 13)); // NOI18N
        jTCidade.setEnabled(false);
        getContentPane().add(jTCidade, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 150, 280, -1));

        jLabel6.setFont(new java.awt.Font("Lucida Handwriting", 2, 12)); // NOI18N
        jLabel6.setText("Bairro");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 150, 70, -1));

        jTBairro.setFont(new java.awt.Font("Lucida Calligraphy", 2, 13)); // NOI18N
        jTBairro.setEnabled(false);
        getContentPane().add(jTBairro, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 150, 280, -1));

        jLabel7.setFont(new java.awt.Font("Lucida Handwriting", 2, 12)); // NOI18N
        jLabel7.setText("CEP");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 180, 50, -1));

        try {
            jFCEP.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#####-###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFCEP.setEnabled(false);
        jFCEP.setFont(new java.awt.Font("Lucida Calligraphy", 2, 13)); // NOI18N
        getContentPane().add(jFCEP, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 180, 140, -1));

        jLabel8.setFont(new java.awt.Font("Lucida Calligraphy", 2, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("X");
        jLabel8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 0, 30, -1));

        jLabel9.setFont(new java.awt.Font("Lucida Handwriting", 2, 12)); // NOI18N
        jLabel9.setText("UF");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 180, 40, -1));

        jComboUF.setFont(new java.awt.Font("Lucida Calligraphy", 2, 13)); // NOI18N
        jComboUF.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "UF" }));
        jComboUF.setEnabled(false);
        getContentPane().add(jComboUF, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 180, 160, -1));

        jLabel10.setFont(new java.awt.Font("Lucida Handwriting", 2, 12)); // NOI18N
        jLabel10.setText("Nº de Residência");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 180, 130, -1));

        jTNumeroResidencia.setFont(new java.awt.Font("Lucida Calligraphy", 2, 13)); // NOI18N
        jTNumeroResidencia.setEnabled(false);
        getContentPane().add(jTNumeroResidencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 180, 140, -1));

        jLabel11.setFont(new java.awt.Font("Lucida Handwriting", 2, 12)); // NOI18N
        jLabel11.setText("Complemento");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 210, 110, -1));

        jTComplemento.setFont(new java.awt.Font("Lucida Calligraphy", 2, 13)); // NOI18N
        jTComplemento.setEnabled(false);
        getContentPane().add(jTComplemento, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 210, 570, -1));

        jLabel12.setFont(new java.awt.Font("Lucida Handwriting", 2, 12)); // NOI18N
        jLabel12.setText("RG");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 240, 40, -1));

        jTRG.setFont(new java.awt.Font("Lucida Handwriting", 2, 13)); // NOI18N
        jTRG.setEnabled(false);
        getContentPane().add(jTRG, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 240, 150, -1));

        jLabel13.setFont(new java.awt.Font("Lucida Handwriting", 2, 12)); // NOI18N
        jLabel13.setText("CPF");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 240, 40, -1));

        try {
            jFCPF.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFCPF.setEnabled(false);
        jFCPF.setFont(new java.awt.Font("Lucida Calligraphy", 2, 13)); // NOI18N
        getContentPane().add(jFCPF, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 240, 140, -1));

        jLabel14.setFont(new java.awt.Font("Lucida Handwriting", 2, 12)); // NOI18N
        jLabel14.setText("Login");
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 240, 60, -1));

        jTLogin.setFont(new java.awt.Font("Lucida Calligraphy", 2, 13)); // NOI18N
        jTLogin.setEnabled(false);
        getContentPane().add(jTLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 240, 220, -1));

        jLabel15.setFont(new java.awt.Font("Lucida Handwriting", 2, 12)); // NOI18N
        jLabel15.setText("Senha");
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 270, 70, -1));

        jPSenha.setFont(new java.awt.Font("Lucida Calligraphy", 2, 13)); // NOI18N
        jPSenha.setEnabled(false);
        getContentPane().add(jPSenha, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 270, 150, -1));

        jLabel16.setFont(new java.awt.Font("Lucida Handwriting", 2, 12)); // NOI18N
        jLabel16.setText("Salário R$");
        getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 270, 90, -1));

        jTSalario.setFont(new java.awt.Font("Lucida Calligraphy", 2, 13)); // NOI18N
        jTSalario.setEnabled(false);
        getContentPane().add(jTSalario, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 270, 90, -1));

        jLabel17.setFont(new java.awt.Font("Lucida Handwriting", 2, 12)); // NOI18N
        jLabel17.setText("Função");
        getContentPane().add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 270, 70, -1));

        jComboFuncao.setFont(new java.awt.Font("Lucida Calligraphy", 2, 13)); // NOI18N
        jComboFuncao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Função" }));
        jComboFuncao.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jComboFuncao.setEnabled(false);
        getContentPane().add(jComboFuncao, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 270, 200, -1));

        jLabel18.setFont(new java.awt.Font("Lucida Handwriting", 2, 12)); // NOI18N
        jLabel18.setText("Data de Nascimento");
        getContentPane().add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 300, 170, -1));

        try {
            jFDataNascimento.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFDataNascimento.setEnabled(false);
        jFDataNascimento.setFont(new java.awt.Font("Lucida Calligraphy", 2, 13)); // NOI18N
        getContentPane().add(jFDataNascimento, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 300, 130, -1));

        jLabel19.setFont(new java.awt.Font("Lucida Handwriting", 2, 12)); // NOI18N
        jLabel19.setText("Data Admissão");
        getContentPane().add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 300, 120, -1));

        try {
            jFDataAdmissao.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFDataAdmissao.setEnabled(false);
        jFDataAdmissao.setFont(new java.awt.Font("Lucida Calligraphy", 2, 13)); // NOI18N
        getContentPane().add(jFDataAdmissao, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 300, 100, -1));

        jLabel20.setFont(new java.awt.Font("Lucida Handwriting", 2, 12)); // NOI18N
        jLabel20.setText("Sexo");
        getContentPane().add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 300, 50, -1));

        jComboSexo.setFont(new java.awt.Font("Lucida Calligraphy", 2, 13)); // NOI18N
        jComboSexo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sexo" }));
        jComboSexo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jComboSexo.setEnabled(false);
        getContentPane().add(jComboSexo, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 300, 120, -1));

        jLabel21.setFont(new java.awt.Font("Lucida Handwriting", 2, 12)); // NOI18N
        jLabel21.setText("Idade");
        getContentPane().add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 330, 60, -1));

        jTIdade.setFont(new java.awt.Font("Lucida Calligraphy", 2, 13)); // NOI18N
        jTIdade.setEnabled(false);
        getContentPane().add(jTIdade, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 330, 50, -1));

        jTabelaEnfermeiro.setFont(new java.awt.Font("Lucida Calligraphy", 2, 13)); // NOI18N
        jTabelaEnfermeiro.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Código", "Nome", "Data de Nascimento", "COREN", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Long.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTabelaEnfermeiro.setEnabled(false);
        jTabelaEnfermeiro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabelaEnfermeiroMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTabelaEnfermeiro);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 390, 680, 110));

        jLabel22.setFont(new java.awt.Font("Lucida Handwriting", 2, 12)); // NOI18N
        jLabel22.setText("COREN");
        getContentPane().add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 330, 60, -1));

        jFCoren.setEnabled(false);
        jFCoren.setFont(new java.awt.Font("Lucida Calligraphy", 2, 13)); // NOI18N
        getContentPane().add(jFCoren, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 330, 110, -1));

        jLabel23.setFont(new java.awt.Font("Lucida Handwriting", 2, 12)); // NOI18N
        jLabel23.setText("Telefone");
        getContentPane().add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 330, 60, -1));

        try {
            jFTelefone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(###)####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFTelefone.setEnabled(false);
        jFTelefone.setFont(new java.awt.Font("Lucida Calligraphy", 2, 13)); // NOI18N
        getContentPane().add(jFTelefone, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 330, 80, -1));

        jLabel24.setFont(new java.awt.Font("Lucida Handwriting", 2, 12)); // NOI18N
        jLabel24.setText("Celular");
        getContentPane().add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 330, 60, -1));

        try {
            jFCelular.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(###)#####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFCelular.setEnabled(false);
        jFCelular.setFont(new java.awt.Font("Lucida Calligraphy", 2, 13)); // NOI18N
        getContentPane().add(jFCelular, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 330, 160, -1));

        jLabel25.setFont(new java.awt.Font("Lucida Handwriting", 2, 12)); // NOI18N
        jLabel25.setText("Consulta");
        getContentPane().add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 520, 80, -1));

        jTextField11.setText("jTextField11");
        getContentPane().add(jTextField11, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 540, 170, -1));

        jLabel26.setFont(new java.awt.Font("Lucida Handwriting", 2, 12)); // NOI18N
        jLabel26.setText("Campo");
        getContentPane().add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 520, 60, -1));

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        getContentPane().add(jComboBox4, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 540, 140, -1));

        jButton1.setFont(new java.awt.Font("Lucida Handwriting", 2, 12)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/if_search_magnifying_glass_find_103857.png"))); // NOI18N
        jButton1.setText("Pesquisar");
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 523, -1, 50));

        jBtnAtualizar.setFont(new java.awt.Font("Lucida Handwriting", 2, 12)); // NOI18N
        jBtnAtualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/if_db_update_3213.png"))); // NOI18N
        jBtnAtualizar.setText("Atualizar");
        jBtnAtualizar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBtnAtualizar.setEnabled(false);
        getContentPane().add(jBtnAtualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 510, 140, -1));

        jBtnExcluir.setFont(new java.awt.Font("Lucida Handwriting", 2, 12)); // NOI18N
        jBtnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/if_delete_71076.png"))); // NOI18N
        jBtnExcluir.setText("Excluir");
        jBtnExcluir.setEnabled(false);
        getContentPane().add(jBtnExcluir, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 560, 140, 40));

        jLabel27.setFont(new java.awt.Font("Lucida Handwriting", 2, 12)); // NOI18N
        jLabel27.setText("E-Mail");
        getContentPane().add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 360, 70, -1));

        jTEmail.setFont(new java.awt.Font("Lucida Calligraphy", 2, 13)); // NOI18N
        jTEmail.setEnabled(false);
        getContentPane().add(jTEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 360, 620, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/back_enfermeiro.jpg"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 810, 620));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jLabel8MouseClicked

    private void jTabelaEnfermeiroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabelaEnfermeiroMouseClicked
        // TODO add your handling code here:
        linhaSelecionada = jTabelaEnfermeiro.getSelectedRow();
        
    }//GEN-LAST:event_jTabelaEnfermeiroMouseClicked

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnAtualizar;
    private javax.swing.JButton jBtnExcluir;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<String> jComboFuncao;
    private javax.swing.JComboBox<String> jComboSexo;
    private javax.swing.JComboBox<String> jComboUF;
    private javax.swing.JFormattedTextField jFCEP;
    private javax.swing.JFormattedTextField jFCPF;
    private javax.swing.JFormattedTextField jFCelular;
    private javax.swing.JFormattedTextField jFCoren;
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
    private javax.swing.JTextField jTEmail;
    private javax.swing.JTextField jTIdade;
    private javax.swing.JTextField jTLogin;
    private javax.swing.JTextField jTNome;
    private javax.swing.JTextField jTNumeroResidencia;
    private javax.swing.JTextField jTRG;
    private javax.swing.JTextField jTRua;
    private javax.swing.JTextField jTSalario;
    private javax.swing.JTable jTabelaEnfermeiro;
    private javax.swing.JTextField jTextField11;
    // End of variables declaration//GEN-END:variables
}
