/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bl;

import blinterfaces.IFuncionarioBL;
import java.sql.SQLException;
import java.util.List;
import model.Funcionario;
import utils.ErroConectarException;
import utils.ErroDAOException;
import utils.ErroNegocioException;
import dao.FuncionarioDAO;

/**
 *
 * @author MARCOS
 */
public class FuncionarioBL implements IFuncionarioBL{
    
    private FuncionarioDAO dao;
    
    public FuncionarioBL(){
        dao = new FuncionarioDAO();
    }
    
    private boolean verificarExistenciaByInsert(String login, String nome, String cpf) throws ErroDAOException, ErroConectarException, SQLException{
        return dao.verificarExistenciaByInsert(nome, login, cpf);
    }
    
    private boolean verificarExistencia(int codigo) throws ErroDAOException, ErroConectarException, SQLException{
        return dao.verificarExistencia(codigo);
    }
    
    private void validarDados(Funcionario funcionario) throws ErroNegocioException{
        
        if(funcionario.getNome().length() > 255){
            throw new ErroNegocioException("O número de caracteres excedeu o limite.\nO Campo Nome só aceita até 255 caracteres.");
        }
        
        if(funcionario.getEndereco() == null){
            throw new ErroNegocioException("Para realizar esta operação, é preciso informar o Endereço do Funcionário.");
        }
        
        if(funcionario.getEndereco().getRua().length() > 255){
            throw new ErroNegocioException("O número de caracteres excedeu o limite.\nO Campo Rua só aceita até 255 caracteres.");
        }
        
        if(funcionario.getEndereco().getCidade().length() > 200){
            throw new ErroNegocioException("O número de caracteres excedeu o limite.\nO Campo Cidade só aceita até 300 caracteres.");
        }
        
        if(funcionario.getEndereco().getBairro().length() > 100){
            throw new ErroNegocioException("O número de caracteres excedeu o limite.\nO Campo Bairro só aceita até 100 caracteres.");
        }
        
        if(funcionario.getEndereco().getCep().length() > 20){
            throw new ErroNegocioException("O número de caracteres excedeu o limite.\nO Campo CEP só aceita até 20 caracteres.");
        }
        
        if(funcionario.getEndereco().getComplemento().length() > 255){
            throw new ErroNegocioException("O número de caracteres excedeu o limite.\nO Campo CEP só aceita até 20 caracteres.");
        }
        
        if(funcionario.getEndereco().getUf().length() > 80){
            throw new ErroNegocioException("O número de caracteres excedeu o limite.\nO Campo UF só aceita até 80 caracteres.");
        }
        
        if(funcionario.getRg().length() > 20){
            throw new ErroNegocioException("O número de caracteres excedeu o limite.\nO Campo RG só aceita até 20 caracteres.");
        }
        
        if(funcionario.getCpf().length() > 15){
            throw new ErroNegocioException("O número de caracteres excedeu o limite.\nO Campo CPF só aceita até 15 caracteres.");
        }
        
        if(funcionario.getLogin().length() > 200){
            throw new ErroNegocioException("O número de caracteres excedeu o limite.\nO Campo Login só aceita até 200 caracteres.");
        }
        
        if(funcionario.getSenha().length() > 200){
            throw new ErroNegocioException("O número de caracteres excedeu o limite.\nO Campo Senha só aceita até 200 caracteres.");
        }
        
        if(funcionario.getDataNascimento() == null){
            throw new ErroNegocioException("O número de caracteres excedeu o limite.\nO Campo CEP só aceita até 20 caracteres.");
        }
        
        if(funcionario.getSexo().length() > 100){
            throw new ErroNegocioException("O número de caracteres excedeu o limite.\nO Campo CEP só aceita até 20 caracteres.");
        }
        
        if(funcionario.getSalario() <= 0.0){
            throw new ErroNegocioException("O Salário não pode conter números negativos, ou igual a zero.\nPor favor, informe os dados corretos ou cancele a operação.");
        }
        
        if(funcionario.getFuncao().length() > 80){
            throw new ErroNegocioException("O número de caracteres excedeu o limite.\nO Campo Função só aceita até 80 caracteres.");
        }
        
        if(funcionario.getContato() == null){
            throw new ErroNegocioException("Para realizar esta operação é necessário informar o Contato do Funcionário.");
        }
        
        if(funcionario.getContato().getTelefone().length() > 30){
            throw new ErroNegocioException("O número de caracteres excedeu o limite.\nO Campo CEP só aceita até 30 caracteres.");
        }
        
        if(funcionario.getContato().getCelular().length() > 30){
            throw new ErroNegocioException("O número de caracteres excedeu o limite.\nO Campo Celular só aceita até 30 caracteres.");
        }
        
        if(funcionario.getContato().getEmail().length() > 100){
            throw new ErroNegocioException("O número de caracteres excedeu o limite.\nO Campo E-Mail só aceita até 100 caracteres.");
        }
    }

    @Override
    public void cadastrarFuncionario(Funcionario funcionario) throws ErroDAOException, ErroConectarException, ErroNegocioException, SQLException {
        if(verificarExistenciaByInsert(funcionario.getLogin(), funcionario.getNome(), funcionario.getCpf())){
            throw new ErroNegocioException((funcionario.getSexo().equals("Masculino") ? "O funcionário " : "A funcionária ") + funcionario.getNome() + (funcionario.getSexo().equals("Masculino") ? "já está cadastrado no sistema.\nPor favor informe os dados corretos ou cancele a operação." : "já está cadastrada no sistema.\nPor favor informe os dados corretos ou cancele a operação."));
        }
        
        validarDados(funcionario);
        
        dao.cadastrarFuncionario(funcionario);
    }

    @Override
    public void alterarFuncionario(Funcionario funcionario) throws ErroDAOException, ErroConectarException, ErroNegocioException, SQLException {
        if(!verificarExistencia(funcionario.getCodigo())){
            throw new ErroNegocioException("Os dados " + (funcionario.getSexo().equals("Masculino") ? "do funcionário " : "da funcionária ") + funcionario.getNome() + " não foram encontrados.\nPor favor informe os dados corretos ou cancele a operação.");
        }
        
        validarDados(funcionario);
    }

    @Override
    public void excluirFuncionario(Funcionario funcionario) throws ErroDAOException, ErroConectarException, ErroNegocioException, SQLException {
        if(!verificarExistencia(funcionario.getCodigo())){
            throw new ErroNegocioException("Os dados " + (funcionario.getSexo().equals("Masculino") ? "do funcionário " : "da funcionária ") + funcionario.getNome() + " não foram encontrados.\nPor favor informe os dados corretos ou cancele a operação.");
        }
        
        dao.excluirFuncionario(funcionario);
    }

    @Override
    public Funcionario logarFuncionario(String login, String senha) throws ErroDAOException, ErroConectarException, ErroNegocioException, SQLException {
        Funcionario funcionario = dao.logarFuncionario(login, senha);
        
        if(login.length() > 200){
            throw new ErroNegocioException("O número de caracteres excedeu o limite.\nO Campo Login só aceita até 200 caracteres.");
        }
        
        if(senha.length() > 200){
            throw new ErroNegocioException("O número de caracteres excedeu o limite.\nO Campo Senha só aceita até 200 caracteres.");
        }
        
        if(funcionario == null){
            throw new ErroNegocioException("Login inválido!\nO Login ou a Senha estão errados.\n\nPor favor informe os dados corretos ou tente novamente mais tarde.");
        }else{
            if(funcionario.getSituacaoFuncionario().equals("Suspenso")){
                throw new ErroNegocioException((funcionario.getSexo().equals("Masculino") ? "Prezado funcionário " : "Prezado funcionária ") + funcionario.getNome() + " o seu acesso está temporariamente suspenso, para liberá-lo entre em contato com o administrador do sistema.");
            }
        }
        
        return funcionario;
    }

    @Override
    public List<Funcionario> listarFuncionarios(String consulta, String campo) throws ErroDAOException, ErroConectarException, ErroNegocioException, SQLException {
        List<Funcionario> funcionarios = dao.listarFuncionario(consulta, campo);
        
        if((!consulta.equals("Todos") && !campo.equals("Todos")) && (!campo.equals("Ativos") && !campo.equals("Bloqueados") && !campo.equals("Suspensos"))){
            if(funcionarios.isEmpty()){
                throw new ErroNegocioException("Os dados dos Funcionários não foram encontrados ou cadastrados.\nPor favor informe os dados corretos ou cancele a operação.");
            }
        }
        
        
        
        return funcionarios;
    }
    
}
