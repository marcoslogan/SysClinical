/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bl;

import blinterfaces.IMedicoBL;
import dao.MedicoDAO;
import java.sql.SQLException;
import java.util.List;
import model.Medico;
import utils.ErroConectarException;
import utils.ErroDAOException;
import utils.ErroNegocioException;

/**
 *
 * @author MARCOS
 */
public class MedicoBL implements IMedicoBL {

    private MedicoDAO dao = null;

    public MedicoBL() {
        dao = new MedicoDAO();
    }

    private boolean verificarExistenciaByInsert(String nome, String login, String cpf) throws ErroDAOException, ErroConectarException, SQLException {
        return dao.verificarExistenciaByInsert(nome, login, cpf);
    }

    private boolean verificarExistencia(int codigo) throws ErroDAOException, ErroConectarException, SQLException {
        return dao.verificarExistencia(codigo);
    }

    private void validarDados(Medico medico) throws ErroNegocioException {

        if (medico.getNome().length() > 255) {
            throw new ErroNegocioException("O número de caracteres excedeu o limite.\nO Campo Nome só aceita até 255 caracteres.");
        }

        if (medico.getEndereco().getRua().length() > 255) {
            throw new ErroNegocioException("O número de caracteres excedeu o limite.\nO Campo Rua só aceita até 255 caracteres.");
        }

        if (medico.getEndereco().getCidade().length() > 200) {
            throw new ErroNegocioException("O número de caracteres excedeu o limite.\nO Campo Cidade só aceita até 200 caracteres.");
        }

        if (medico.getEndereco().getBairro().length() > 100) {
            throw new ErroNegocioException("O número de caracteres excedeu o limite.\nO Campo Bairro só aceita até 100 caracteres.");
        }

        if (medico.getEndereco().getCep().length() > 20) {
            throw new ErroNegocioException("O número de caracteres excedeu o limite.\nO Campo CEP só aceita até 20 caracteres.");
        }

        if (medico.getEndereco().getComplemento().length() > 255) {
            throw new ErroNegocioException("O número de caracteres excedeu o limite.\nO Campo Complemento só aceita até 255 caracteres.");
        }

        if (medico.getEndereco().getUf().length() > 80) {
            throw new ErroNegocioException("O número de caracteres excedeu o limite.\nO Campo UF só aceita até 80 caracteres.");
        }

        if (medico.getRg().length() > 20) {
            throw new ErroNegocioException("O número de caracteres excedeu o limite.\nO Campo RG só aceita até 20 caracteres.");
        }

        if (medico.getCpf().length() > 15) {
            throw new ErroNegocioException("O número de caracteres excedeu o limite.\nO Campo CPF só aceita até 15 caracteres.");
        }

        if (medico.getLogin().length() > 200) {
            throw new ErroNegocioException("O número de caracteres excedeu o limite.\nO Campo Login só aceita até 200 caracteres.");
        }

        if (medico.getSenha().length() > 200) {
            throw new ErroNegocioException("O número de caracteres excedeu o limite.\nO Campo Senha só aceita até 200 caracteres.");
        }
        
        if(medico.getSalario() <= 0.00){
            throw new ErroNegocioException("O Campo Salário não aceita zero e nem números negativos.\nPor favor informe um valor válido ou cancele a operação..");
        }
        
        if(medico.getFuncao().length() > 80){
            throw new ErroNegocioException("O número de caracteres excedeu o limite.\nO Campo Função só aceita até 80 caracteres.");
        }
        
        if(medico.getEspecialidade() == null){
            throw new ErroNegocioException("Para realizar este procedimento é necessário informar a Especialidade do Médico.\nPor favor informe-a ou cancele a operação.");
        }
        
        if(medico.getCrm() == null){
            throw new ErroNegocioException("Para realizar este procedimento é necessário informar o CRM do Médico.\nPor favor informe-o ou cancele a operação.");
        }
        
        if(medico.getContato().getTelefone().length() > 30){
            throw new ErroNegocioException("O número de caracteres excedeu o limite.\nO Campo Telefone só aceita até 30 caracteres.");
        }
        
        if(medico.getContato().getCelular().length() > 30){
            throw new ErroNegocioException("O número de caracteres excedeu o limite.\nO Campo Celular só aceita até 30 caracteres.");
        }
        
        if(medico.getContato().getEmail().length() > 100){
            throw new ErroNegocioException("O número de caracteres excedeu o limite.\nO Campo E-Mail só aceita até 100 caracteres.");
        }

    }
    
    /**
     * <b>cadastrarMedico</b>
     * <p>Método responsável por aplicar as validações de dados e validações de regras de negócio durante a operação de cadastro dos dados no sistema.</p>
     * <p>Caso o cadastro não seja efetuado ou se algum dados estiver inválido, uma exceção será lançada.</p>
     * @param medico Objeto contendo os dados do novo Médico a ser cadastrado no sistema.
     * @throws ErroDAOException
     * @throws ErroConectarException
     * @throws ErroNegocioException
     * @throws SQLException
     */

    @Override
    public void cadastrarMedico(Medico medico) throws ErroDAOException, ErroConectarException, ErroNegocioException, SQLException {
        if (verificarExistenciaByInsert(medico.getNome(), medico.getLogin(), medico.getCpf())) {
            throw new ErroNegocioException((medico.getSexo().equals("Masculino") ? "O Médico " + medico.getNome() + " já está cadastrado no sistema." : "A Médica " + medico.getNome() + " já está cadastrada no sistema.") + "\nPor favor informe os dados corretos ou cancele a operação.");
        }

        validarDados(medico);
        dao.cadastrarMedico(medico);
    }

    /**
     * <b>alterarMedico</b>
     * <p>Método responsável por aplicar as validações de dados e validações de regras de negócio durante a operação de atualização/alteração dos dados no sistema.</p>
     * <p>Caso a atualização não seja efetuada ou se algum dados estiver inválido, uma exceção será lançada.</p>
     * @param medico Objeto contendo os dados do Médico que serão atualizados no sistema.
     * @throws ErroDAOException
     * @throws ErroConectarException
     * @throws ErroNegocioException
     * @throws SQLException
     */
    
    @Override
    public void alterarMedico(Medico medico) throws ErroDAOException, ErroConectarException, ErroNegocioException, SQLException {
        if (!verificarExistencia(medico.getCodigo())) {
            throw new ErroNegocioException("Os dados " + (medico.getSexo().equals("Masculino") ? "do Médico" + medico.getNome() + " não foram encontrados." : "da Médica " + medico.getNome() + " não foram encontrados.") + "\nPor favor informe os dados corretos ou cancele a operação.");
        }

        validarDados(medico);
        dao.alterarMedico(medico);
    }

    /**
     * <b>excluirMedico</b>
     * <p>Método responsável por aplicar as validações de dados e validações de regras de negócio durante a operação de exclusão dos dados no sistema.</p>
     * <p>Caso a exclusão não seja efetuada ou se algum dados estiver inválido, uma exceção será lançada.</p>
     * @param medico Objeto contendo os dados Médico que serão excluídos do sistema.
     * @throws ErroDAOException
     * @throws ErroConectarException
     * @throws ErroNegocioException
     * @throws SQLException
     */
    
    @Override
    public void excluirMedico(Medico medico) throws ErroDAOException, ErroConectarException, ErroNegocioException, SQLException {
        if (!verificarExistencia(medico.getCodigo())) {
            throw new ErroNegocioException("Os dados " + (medico.getSexo().equals("Masculino") ? "do Médico" + medico.getNome() + " não foram encontrados." : "da Médica " + medico.getNome() + " não foram encontrados.") + "\nPor favor informe os dados corretos ou cancele a operação.");
        }

        dao.excluirMedico(medico);
    }

    /**
     * <b>logarMedico</b>
     * <p>Método responsável por aplicar as validações de dados e validações de regras de negócio durante a operação de Login do Médico no sistema.</p>
     * <p>Caso o login não seja efetuado ou se algum dado estiver inválido, uma exceção será lançada.</p>
     * @param login
     * @param senha
     * @throws ErroDAOException
     * @throws ErroConectarException
     * @throws ErroNegocioException
     * @throws SQLException
     * @return Medico - Objeto contendo os dados do Médico logado no sistema.
     */
    @Override
    public Medico logarMedico(String login, String senha) throws ErroDAOException, ErroConectarException, ErroNegocioException, SQLException {
        Medico medicoLogado = dao.logarMedico(login, senha);

        if (login.length() > 200) {
            throw new ErroNegocioException("O número de caracteres excedeu o limite.\nO Campo Login só aceita até 200 caracteres.");
        }

        if (senha.length() > 200) {
            throw new ErroNegocioException("O número de caracteres excedeu o limite.\nO Campo Senha só aceita até 200 caracteres.");
        }

        if (medicoLogado == null) {
            throw new ErroNegocioException("Login inválido.\nO Login ou a Senha estão incorretos.\n\nPor favor informe-os corretamente ou tente novamente mais tarde.");
        }

        if (medicoLogado.getSituacaoFuncionario().equals("Suspenso")) {
            throw new ErroNegocioException("Caro usuário o seu login foi realizado com sucesso, mas o seu acesso está temporariamente suspenso.\nEntre em contato com o Administrador do Sistema para que o acesso seja liberado.");
        }

        return medicoLogado;
    }

    
    /**
     * <b>listarMedicos</b>
     * <p>Método responsável por aplicar as validações de dados e validações de regras de negócio durante a operação de Listagem dos dados dos Médicos no sistema.</p>
     * <p>Caso a listagem não seja efetuada ou se algum dado estiver inválido, uma exceção será lançada.</p>
     * @param consulta
     * @param campo
     * @throws ErroDAOException
     * @throws ErroConectarException
     * @throws ErroNegocioException
     * @throws SQLException
     * @return List - Lista de Médicos previamente cadastrados no sistema.
     */
    @Override
    public List<Medico> listarMedicos(String consulta, String campo) throws ErroDAOException, ErroConectarException, ErroNegocioException, SQLException {
        List<Medico> medicos = dao.listarMedicos(consulta, campo);
        
        if(!consulta.equals("Todos") && !campo.equals("Todos")){
            if(medicos.isEmpty()){
                throw new ErroNegocioException("Os dados dos Médicos não foram encontrados ou cadastrados.\nPor favor informe os dados corretos ou cancele a operação.");
            }
        }
        
        return medicos;
    }

}
