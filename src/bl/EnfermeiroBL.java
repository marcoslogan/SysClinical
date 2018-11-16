/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bl;

import blinterfaces.IEnfermeiroBL;
import java.sql.SQLException;
import java.util.List;
import model.Enfermeiro;
import utils.ErroConectarException;
import utils.ErroDAOException;
import utils.ErroNegocioException;
import dao.EnfermeiroDAO;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MARCOS
 */
public class EnfermeiroBL implements IEnfermeiroBL {

    private EnfermeiroDAO dao = null;

    public EnfermeiroBL() {
        dao = new EnfermeiroDAO();
    }

    private void validarDados(Enfermeiro enfermeiro) throws ErroNegocioException {

        if (enfermeiro.getNome().length() > 255) {
            throw new ErroNegocioException("O número de caracteres excedeu o limite.\nO Campo Nome só aceita até 255 caracteres.");
        }

        if (enfermeiro.getEndereco() == null) {
            throw new ErroNegocioException("Para realizar esta operação é necessário fornecer o Endereço do Enfermeiro(a).\nPor favor informe o Endereço ou cancele a operação!");
        }

        if (enfermeiro.getEndereco().getRua().length() > 255) {
            throw new ErroNegocioException("O número de caracteres excedeu o limite.\nO Campo Rua só aceita até 255 caracteres.");
        }

        if (enfermeiro.getEndereco().getCidade().length() > 200) {
            throw new ErroNegocioException("O número de caracteres excedeu o limite.\nO Campo Cidade só aceita até 200 caracteres.");
        }

        if (enfermeiro.getEndereco().getBairro().length() > 100) {
            throw new ErroNegocioException("O número de caracteres excedeu o limite.\nO Campo Bairro só aceita até 100 caracteres.");
        }

        if (enfermeiro.getEndereco().getComplemento().length() > 255) {
            throw new ErroNegocioException("O número de caracteres excedeu o limite.\nO Campo Complemento só aceita até 255 caracteres.");
        }

        if (enfermeiro.getEndereco().getNumeroResidencia().length() > 50) {
            throw new ErroNegocioException("O número de caracteres excedeu o limite.\nO Campo Nº de Residência só aceita até 50 caracteres.");
        }

        if (enfermeiro.getEndereco().getUf().length() > 80) {
            throw new ErroNegocioException("O número de caracteres excedeu o limite.\nO Campo UF só aceita 80 caracteres.");
        }
        
        if(enfermeiro.getRg().length() > 20){
            throw new ErroNegocioException("O número de caracteres excedeu o limite.\nO Campo RG só aceita até 20 caracteres.");
        }
        
        if(enfermeiro.getCpf().length() > 15){
            throw new ErroNegocioException("O número de caracteres excedeu o limite.\nO Campo CPF só aceita até 15 caracteres.");
        }
        
        if(enfermeiro.getLogin().length() > 200){
            throw new ErroNegocioException("O número de caracteres excedeu o limite.\nO Campo Login só aceita até 200 caracteres.");
        }
        
        if(enfermeiro.getSenha().length() > 200){
            throw new ErroNegocioException("O número de caracteres excedeu o limite.\nO Campo Senha só aceita até 200 caracteres.");
        }
        
        if(enfermeiro.getDataNascimento() == null){
            throw new ErroNegocioException("");
        }
        
        if(enfermeiro.getSalario() <= 0){
            throw new ErroNegocioException("");
        }
        
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        try {
            enfermeiro.setDataNascimento(format.parse(format.format(enfermeiro.getDataNascimento())));
        } catch (ParseException ex) {
            throw new ErroNegocioException("Data Inválida!\n\n" + ex.getMessage());
        }
        
        if(enfermeiro.getFuncao().length() > 80){
            throw new ErroNegocioException("");
        }
        
        if(enfermeiro.getContato() == null){
            throw new ErroNegocioException("");
        }
        
        if(enfermeiro.getContato().getTelefone().length() > 30){
            throw new ErroNegocioException("");
        }
        
        if(enfermeiro.getContato().getCelular().length() > 30){
            throw new ErroNegocioException("");
        }
        
        if(enfermeiro.getContato().getEmail().length() > 100){
            throw new ErroNegocioException("");
        }
        
        if(enfermeiro.getCorem().length() > 50){
            throw new ErroNegocioException("");
        }

    }

    private boolean verificarExistenciaByInsert(String nome, String login, String cpf) throws ErroDAOException, ErroConectarException, SQLException {
        return dao.verificarExistenciaByInsert(nome, login, cpf);
    }

    private boolean verificarExistencia(int codigo) throws ErroDAOException, ErroConectarException, SQLException {
        return dao.verificarExistencia(codigo);
    }

    @Override
    public void cadastrarEnfermeiro(Enfermeiro enfermeiro) throws ErroDAOException, ErroConectarException, ErroNegocioException, SQLException {
        if (verificarExistenciaByInsert(enfermeiro.getNome(), enfermeiro.getLogin(), enfermeiro.getCpf())) {
            throw new ErroNegocioException((enfermeiro.getSexo().equals("Masculino") ? "O Enfermeiro " + enfermeiro.getNome() + " já se encontra cadastrado no sistema." : "A Enfermeira "+enfermeiro.getNome() + " já se encontra cadastrada no sistema.") + "\nPor favor informe os dados corretos ou cancele a operação!");
        }
        
        validarDados(enfermeiro);
        
        dao.cadastrarEnfermeiro(enfermeiro);
    }

    @Override
    public void alterarEnfermeiro(Enfermeiro enfermeiro) throws ErroDAOException, ErroConectarException, ErroNegocioException, SQLException {
        if(verificarExistencia(enfermeiro.getCodigo())){
            throw new ErroNegocioException("Os dados " + (enfermeiro.getSexo().equals("Masculino")? "do Enfermeiro " + enfermeiro.getNome() : "da Enfermeira" + enfermeiro.getNome()) + " não foram encontrados.\nPor favor informe os dados corretos ou cancele a operação!");
        }
        
        validarDados(enfermeiro);
        
        dao.alterarEnfermeiro(enfermeiro);
    }

    @Override
    public void excluirEnfermeiro(Enfermeiro enfermeiro) throws ErroDAOException, ErroConectarException, ErroNegocioException, SQLException {
        if(verificarExistencia(enfermeiro.getCodigo())){
            throw new ErroNegocioException("Os dados " + (enfermeiro.getSexo().equals("Masculino")? "do Enfermeiro " + enfermeiro.getNome() : "da Enfermeira" + enfermeiro.getNome()) + " não foram encontrados.\nPor favor informe os dados corretos ou cancele a operação!");
        }
        
        dao.excluirEnfermeiro(enfermeiro);
    }

    @Override
    public Enfermeiro logarEnfermeiro(String login, String senha) throws ErroDAOException, ErroConectarException, ErroNegocioException, SQLException {
        Enfermeiro enfermeiroLogado = dao.logarEnfermeiro(login, senha);
        
        if(login.length() > 200){
            throw new ErroNegocioException("O número de caracteres excedeu o limite.\nO Campo Login só aceita até 200 caracteres.");
        }
        
        if(senha.length() > 200){
            throw new ErroNegocioException("O número de caracteres excedeu o limite.\nO Campo Senha só aceita até 200 caracteres.");
        }
        
        if(enfermeiroLogado == null){
            throw new ErroNegocioException("Login inválido!\nLogin ou a Senha estão incorretos.\n\nPor favor informe os dados corretos ou cancele a operação!");
        }
        
        if(enfermeiroLogado.getSituacaoFuncionario().equals("Suspenso")){
            throw new ErroNegocioException("Prezado(a) Enfermeiro(a), o seu login foi executado com sucesso, mas foi verificado o seu acesso, por enquanto você está suspenso(a).\n\nPor favor entre em contato com o administrador do sistema para que o seu acesso seja liberado.");
        }
        
        return enfermeiroLogado;
    }

    @Override
    public List<Enfermeiro> listarEnfermeiros(String consulta, String campo) throws ErroDAOException, ErroConectarException, ErroNegocioException, SQLException {
        List<Enfermeiro> enfermeiros = dao.listarEnfermeiros(consulta, campo);
        
        if(!consulta.equals("Todos") && !campo.equals("Todos")){
            if(enfermeiros.isEmpty()){
                throw new ErroNegocioException("Os dados do(s) Enfermeiro(s) não foram encontrados ou cadastrados.\nPor favor informe os dados corretos ou cancele a operação.");
            }
        }
        
        
        return enfermeiros;
    }

}
