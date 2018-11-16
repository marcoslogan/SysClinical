/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bl;

import blinterfaces.IPacienteBL;
import java.sql.SQLException;
import java.util.List;
import model.Paciente;
import utils.ErroConectarException;
import utils.ErroDAOException;
import utils.ErroNegocioException;
import dao.PacienteDAO;

/**
 *
 * @author MARCOS
 */
public class PacienteBL implements IPacienteBL {

    private PacienteDAO dao = null;

    public PacienteBL() {
        dao = new PacienteDAO();
    }

    private void validar(Paciente paciente) throws ErroNegocioException {

        if (paciente.getNome().length() > 255) {
            throw new ErroNegocioException("O número de caracteres excedeu o limite.\nO Campo Nome só aceita até 255 caracteres.");
        }

        if (paciente.getEndereco() == null) {
            throw new ErroNegocioException("Para realizar esta operação é necessário que seja fornecido o Endereço. Caso contrário cancele a operação!");
        }

        if (paciente.getEndereco().getRua().length() > 255) {
            throw new ErroNegocioException("O número de caraceres excedeu o limite.\nO Campo Rua só aceita até 255 caracteres.");
        }

        if (paciente.getEndereco().getCidade().length() > 200) {
            throw new ErroNegocioException("O número de caracteres excedeu o limite.\nO Campo Cidade só aceita até 200 caracteres.");
        }

        if (paciente.getEndereco().getBairro().length() > 100) {
            throw new ErroNegocioException("O número de caracteres excedeu o limite.\nO Campo Bairro só aceita até 100 caracteres.");
        }

        if (paciente.getEndereco().getCep().length() > 20) {
            throw new ErroNegocioException("O número de caracteres excedeu o limite.\nO Campo CEP só aceita até 20 caracteres.");
        }

        if (paciente.getEndereco().getComplemento().length() > 255) {
            throw new ErroNegocioException("O número de caracteres excedeu o limite.\nO Campo Complemento só aceita até 255 caracteres.");
        }

        if (paciente.getEndereco().getUf().length() > 80) {
            throw new ErroNegocioException("O número de caracteres excedeu o limite.\nO Campo UF só aceita até 80 caraceteres.");
        }

        if (paciente.getRg().length() > 20) {
            throw new ErroNegocioException("O número de caracteres excedeu o limite.\nO Campo RG só aceita até 20 caracteres.");
        }

        if (paciente.getCpf().length() > 15) {
            throw new ErroNegocioException("O número de caracteres excedeu o limite.\nO Campo CPF só aceita até 15 caracteres.");
        }

        if (paciente.getLogin().length() > 200) {
            throw new ErroNegocioException("O número de caracteres excedeu o limite.\nO Campo Login só aceita até 200 caracteres.");
        }

        if (paciente.getSenha().length() > 200) {
            throw new ErroNegocioException("O número de caracteres excedeu o limite.\nO Campo Senha só aceita até 200 caracteres.");
        }

        if (paciente.getPeso() < 0) {
            throw new ErroNegocioException("O Campo Peso não aceita valores menores que zero.\nPor favor informe apenas valores válidos ou cancele a operação.");
        }

        if (paciente.getAltura() < 0) {
            throw new ErroNegocioException("O Campo Altura não aceita valores menores que zero.\nPor favor informe apenas valores válidos ou cancele a operação.");
        }

        try {
            double peso = paciente.getPeso();
            double altura = paciente.getAltura();
            paciente.setAltura(altura);
            paciente.setPeso(peso);
        } catch (NumberFormatException ex) {
            throw new ErroNegocioException("Este Campo só aceita caracteres numéricos.\nPor favor informe os dados corretos ou cancele a operação!");
        }

        if (paciente.getDataNascimento() == null) {
            throw new ErroNegocioException("O Campo Data de Nascimento é um campo obrigatório, caso não seja fornecida cancele a operação!");
        }

        if (paciente.getCodigo() == 0) {
            if (paciente.getFuncionario() == null) {
                throw new ErroNegocioException("Para realizar esta operação é necessário informar");
            }
        }

        if (paciente.getContato() == null) {
            throw new ErroNegocioException("Para realizar esta operação é necessário informar o contato do Paciente.\nInforme-os ou cancele a operação!");
        }

        if (paciente.getContato().getTelefone().length() > 30) {
            throw new ErroNegocioException("O número de caracteres excedeu o limite.\nO Campo Telefone só aceita até 30 caracteres.");
        }

        if (paciente.getContato().getCelular().length() > 30) {
            throw new ErroNegocioException("O número de caracteres excedeu o limite.\nO Campo Celular só aceita até 30 caracteres.");
        }

        if (paciente.getContato().getEmail().length() > 100) {
            throw new ErroNegocioException("O número de caracteres excedeu o limite.\nO Campo E-Mail só aceita até 100 caracteres.");
        }

    }

    private boolean verificarExistencia(int codigo) throws ErroDAOException, ErroConectarException, SQLException {
        return dao.verificarExistencia(codigo);
    }

    private boolean verificarExistenciaByInsert(String nome, String login, String cpf) throws ErroDAOException, ErroConectarException, SQLException {
        return dao.verificarExistenciaByInsert(nome, login, cpf);
    }

    /**
     * <b>cadastrarPaciente</b>
     * <p>
     * Método responsável por aplicar as validações dos dados antes de cadastrar
     * os dados dos novos pacientes no sistema.</p>
     *
     * @param paciente Objeto contento os dados do paciente a ser cadastrados no
     * sistema.
     * @throws ErroDAOException
     * @throws ErroNegocioException
     * @throws ErroConectarException
     * @throws SQLException
     */
    @Override
    public void cadastrarPaciente(Paciente paciente) throws ErroDAOException, ErroNegocioException, ErroConectarException, SQLException {
        if (verificarExistenciaByInsert(paciente.getNome(), paciente.getLogin(), paciente.getCpf())) {
            throw new ErroNegocioException((paciente.getSexo().equals("Masculino") ? "O Paciente " + paciente.getNome() + " já está cadastrado no sistema." : "A Paciente " + paciente.getNome() + " já está cadastrada no sistema.") + "\nPor favor informe os dados corretos ou cancele a operação!");
        }

        validar(paciente);

        dao.cadastrarPaciente(paciente);
    }

    /**
     * <b>alterarPaciente</b>
     * <p>
     * Método responsável por aplicar as validações dos dados antes de
     * atualizá-los no sistema.</p>
     *
     * @param paciente Objeto contendo os dados atualizados do Paciente no
     * sistema.
     * @throws ErroDAOException
     * @throws ErroNegocioException
     * @throws ErroConectarException
     * @throws SQLException
     */
    @Override
    public void alterarPaciente(Paciente paciente) throws ErroDAOException, ErroNegocioException, ErroConectarException, SQLException {
        if (verificarExistencia(paciente.getCodigo())) {
            throw new ErroNegocioException(" Os dados " + (paciente.getSexo().equals("Mascullino") ? "do Paciente " : "da Paciente ") + paciente.getNome() + " não foram encontrados.\nPor favor informe os dados corretos ou cancele a operação!");
        }

        validar(paciente);

        dao.alterarPaciente(paciente);
    }

    /**
     * <b>excluirPaciente</b>
     * <p>
     * Método responsável por aplicar as validações dos dados antes de
     * excluí-los do sistema.</p>
     *
     * @param paciente Objeto contendo o código do Paciente que será excluído do
     * sistema.
     * @throws ErroDAOException
     * @throws ErroNegocioException
     * @throws ErroConectarException
     * @throws SQLException
     */
    @Override
    public void excluirPaciente(Paciente paciente) throws ErroDAOException, ErroNegocioException, ErroConectarException, SQLException {
        if (verificarExistencia(paciente.getCodigo())) {
            throw new ErroNegocioException("Os dados " + (paciente.getSexo().equals("Masculino") ? "do Paciente " : "da Paciente ") + paciente.getNome() + " não foram encontrados.\nPor favor informe os dados corretos ou cancele a operação!");
        }

        dao.excluirPaciente(paciente);
    }
    
    
    /**
     * <b>listarPacientes</b>
     * <p>Método responsável por aplicar as validações dos dados antes de listar os dados dos Pacientes previamente cadastrados no sistema.</p>
     * @param consulta
     * @param campo
     * @throws ErroDAOException
     * @throws ErroNegocioException
     * @throws ErroConectarException
     * @throws SQLException
     * @return List - Lista contendo os dados dos Pacientes previamente cadastrados no sistema.
     */

    @Override
    public List<Paciente> listarPacientes(String consulta, String campo) throws ErroDAOException, ErroNegocioException, ErroConectarException, SQLException {
        List<Paciente> pacientes = dao.listarPacientes(consulta, campo);

        switch (campo) {
            case "Todos":
                if (pacientes.isEmpty()) {
                    throw new ErroNegocioException("Não há registros para serem exibidos.\nPor favor cadastre algum paciente ou cancele a operação!");
                }
                break;
            case "Paciente Mais Recente":
                if (pacientes.isEmpty()) {
                    throw new ErroNegocioException("Não há pacientes cadastrados no sistema.\nPor favor cadastre-os ou cancele a operação!");
                }
                break;
            case "Ativos":
            case "Bloqueados":
            case "Suspensos":
            case "Código":
            case "Nome":
            case "CPF":
                if (pacientes.isEmpty()) {
                    throw new ErroNegocioException("Os dados dos Pacientes não foram encontrados ou cadastrados.\nPor favor informe os dados corretos ou cancele a operação!");
                }
                break;
        }

        return pacientes;
    }

}
