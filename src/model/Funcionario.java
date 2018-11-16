/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;
/**
 *
 * @author MARCOS
 */
public class Funcionario extends Pessoa{
    
    private String login;
    private String senha;
    private double salario;
    private String funcao;
    private Date dataAdmissao;
    private String situacaoFuncionario;
    

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public Date getDataAdmissao() {
        return dataAdmissao;
    }

    public void setDataAdmissao(Date dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }

    public String getSituacaoFuncionario() {
        return situacaoFuncionario;
    }

    public void setSituacaoFuncionario(String situacaoFuncionario) {
        this.situacaoFuncionario = situacaoFuncionario;
    }
    
    
    
    
    
}
