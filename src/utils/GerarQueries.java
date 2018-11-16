/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author MARCOS
 */
public class GerarQueries {

    private static String sql;
    
    
    /**
     * <b>gerarQueryFuncionario</b>
     * <p>Método estático responsável por retornar a query SQL do CRUD do Funcionário que será executado no banco de dados.</p>
     * @param operacao Determina a operação do CRUD a ser executada.
     * @return String Query SQL do CRUD do Funcionário.
     */

    public static String gerarQueryFuncionario(String operacao) {

        switch (operacao) {

            case "Verificar Existência ao Cadastrar":
                sql = "Select * from funcionario Where login = ? And nome = ? And cpf = ?";
                break;
            case "Verificar Existência":
                sql = "Select * from funcionario Where codigo = ?";
                break;
            case "Cadastrar":
                sql = "Insert Into funcionario (nome, rua, cidade, bairro, cep, complemento, numero_residencia, uf, rg, cpf, login, senha, data_nascimento, data_admissao, salario, funcao, sexo, idade, telefone, celular, email, situacao_funcionario) Values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 'Ativo');";
                break;
            case "Alterar":
            case "Atualizar":
                sql = "Update funcionario set nome = ?, rua = ?, cidade = ?, bairro = ?, cep = ?, complemento = ?, numero_residencia = ?, uf = ?, rg = ?, cpf = ?, login = ?, senha = ?, data_nascimento = ?, salario = ?, funcao = ?, sexo = ?, idade = ?, telefone = ?, celular = ?, email = ?, situacao_funcionario = ? Where codigo = ?";
                break;
            case "Excluir":
                sql = "Update funcionario set situacao_funcionario = 'Suspenso' Where codigo = ?";
                break;
            case "ListarTodos":
                sql = "Select * from funcionario";
                break;
            case "Logar":
                sql = "Select * from funcionario Where login = ? And senha = ?";
                break;
            case "Listar Por Código":
                sql = "Select * from funcionario Where codigo = ?";
                break;
            case "Listar Por Nome":
                sql = "Select * from funcionario Where nome like ?";
                break;
            case "Listar Por CPF":
                sql = "Select * from funcionario Where cpf = ?";
                break;
            case "Listar Ativos":
                sql = "Select * from funcionario Where situacao_funcionario = 'Ativo'";
                break;
            case "Listar Bloqueados":
                sql = "Select * from funcionario Where situacao_funcionario = 'Bloqueado'";
                break;
            case "Listar Suspensos":
                sql = "Select * from funcionario Where situacao_funcionario = 'Suspenso'";
                break;
            case "Listar Funcionário mais recente":
                sql = "Select * from funcionario Where codigo = (Select max(codigo) from funcionario);";
                break;
        }

        return sql;
    }

    public static String gerarQueryMedico(String operacao) {

        switch (operacao) {
            case "Verificar Existência ao Cadastrar":
                sql = "Select * from medico Where nome = ? And login = ? And cpf = ?";
                break;
            case "Verificar Existência":
                sql = "Select * from medico Where codigo = ?";
                break;
            case "Cadastrar":
                sql = "Insert Into medico(nome, rua, cidade, bairro, cep, complemento, numero_residencia, uf, rg, cpf, login, senha, data_admissao, data_nascimento, sexo, idade, salario, funcao, telefone, celular, email, crm, situacao_medico) Values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 'Ativo');";
                break;
            case "Atualizar":
            case "Alterar":
                sql = "Update medico set nome = ?, rua = ?, cidade = ?, bairro = ?, cep = ?, complemento = ?, numero_residencia = ?, uf = ?, rg = ?, cpf = ?, login = ?, senha = ?, data_nascimento = ?, salario = ?, funcao = ?, sexo = ?, idade = ?, telefone = ?, celular = ?, email = ?, situacao_medico = ?, crm = ?, especialidade = ? Where codigo = ?";
                break;
            case "Excluir":
                sql = "Update medico set situacao_medico = 'Suspenso' Where codigo = ?";
                break;
            case "Logar":
                sql = "Select * from medico Where login = ? And senha = ?";
                break;
            case "ListarTodos":
                sql = "Select * from medico Inner Join funcionario f on f.codigo = medico.cod_funcionario";
                break;
            case "Listar Por Código":
                sql = "Select * from medico Inner Join funcionario f on f.codigo = medico.cod_funcionario "
                        + "Where codigo = ?";
                break;
            case "Listar Por Nome":
                sql = "Select * from medico Inner Join funcionario f on f.codigo = medico.cod_funcionario "
                        + "Where nome like ?";
                break;
            case "Listar Por CPF":
                sql = "Select * from medico Inner Join funcionario f on f.codigo = medico.cod_funcionario "
                        + "Where cpf = ?";
                break;
            case "Ativos":
                sql = "Select * from medico Inner Join funcionario f on f.codigo = medico.cod_funcionario "
                        + "Where situacao_medico = 'Ativo'";
                break;
            case "Bloqueados":
                sql = "Select * from medico Inner Join funcionario f on f.codigo = medico.cod_funcionario "
                        + "Where situacao_medico = 'Bloqueado'";
                break;
            case "Suspensos":
                sql = "Select * from medico Inner Join funcionario f on f.codigo = medico.cod_funcionario "
                        + "Where situacao_medico = 'Suspenso'";
                break;
            case "Listar Médico mais recente":
                sql = "Select * from medico Inner Join funcionario f on f.codigo = medico.cod_funcionario "
                        + "Where codigo = (Select max(codigo) from medico);";
                break;
            case "Listar por CRM":
                sql = "Select * from medico Inner Join funcionario f on f.codigo = medico.cod_funcionario "
                        + "Where crm = ?";
                break;
            
        }
        

        return sql;
    }
    
    public static String gerarQueryEnfermeiro(String operacao){
        
        switch(operacao){
            
            case "Verificar Existência Ao Cadastrar":
                sql = "Select * from enfermeiro Where nome = ? And login = ? And cpf = ?";
                break;
            case "Verificar Existência":
                sql = "Select * from enfermeiro Where codigo = ?";
                break;
            case "Cadastrar":
                sql = "Insert Into enfermeiro(nome, rua, cidade, bairro, cep, complemento, uf, numero_residencia, rg, cpf, login, senha, data_admissao, data_nascimento, salario, funcao, telefone, celular, email, corem, situacao_enfermeiro) Values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
                break;
            case "Atualizar":
            case "Alterar":
                sql = "Update enfermeiro set nome = ?, rua = ?, cidade = ?, bairro = ?, cep = ?, complemento = ?, uf = ?, numero_residencia = ?, rg = ?, cpf = ?, login = ?, senha = ?, data_nascimento = ?, salario = ?, funcao = ?, telefone = ?, celular = ?, email = ?, corem = ?, situacao_enfermeiro = ? Where codigo = ?";
                break;
            case "Excluir":
                sql = "Update enfermeiro set situacao_enfermeiro = 'Suspenso' Where codigo = ?";
                break;
            case "Logar":
                sql = "Select * from enfermeiro Where login = ? And senha = ?";
                break;
            case "ListarTodos":
                sql = "Select * from enfermeiro Inner Join funcionario f on f.codigo = enfermeiro.cod_funcionario";
                break;
            case "Listar Por Código":
                sql = "Select * from enfermeiro Inner Join funcionario f on f.codigo = enfermeiro.cod_funcionario "
                        + "Where codigo = ?";
                break;
            case "Listar Por Nome":
                sql = "Select * from enfermeiro Inner Join funcionario f on f.codigo = enfermeiro.cod_funcionario "
                        + "Where nome like ?";
                break;
            case "Listar Por CPF":
                sql = "Select * from enfermeiro Inner Join funcionario f on f.codigo = enfermeiro.cod_funcionario "
                        + "Where cpf = ?";
                break;
            case "Ativos":
                sql = "Select * from enfermeiro Inner Join funcionario f on f.codigo = enfermeiro.cod_funcionario "
                        + "Where situacao_enfermeiro = 'Ativo'";
                break;
            case "Bloqueados":
                sql = "Select * from enfermeiro Inner Join funcionario f on f.codigo = enfermeiro.cod_funcionario "
                        + "Where situacao_enfermeiro = 'Bloqueado'";
                break;
            case "Suspensos":
                sql = "Select * from enfermeiro Inner Join funcionario f on f.codigo = enfermeiro.cod_funcionario "
                        + "Where situacao_enfermeiro = 'Suspenso'";
                break;
            case "Listar por COREM":
                sql = "Select * from enfermeiro Inner Join funcionario f on f.codigo = enfermeiro.cod_funcionario "
                        + "Where corem = ?";
                break;
            case "Listar Enfermeiro mais Recente":
                sql = "Select * from enfermeiro Inner Join funcionario f on f.codigo = enfermeiro.cod_funcionario "
                        + "Where codigo = (Select max(codigo) from enfermeiro)";
                break;
                
        }
        
        return sql;
    }
    
    /**
     * <b>gerarQueryPaciente</b>
     * <p>Método estático responsável por retornar uma string contendo o comando SQL a ser executado no banco de dados</p>
     * @param operacao String contendo a operação CRUD a ser executada no banco de dados.
     * @return String String contendo a query SQL que irá ser executada no banco de dados.
     */
    
    public static String gerarQueryPaciente(String operacao){
        
        switch(operacao){
            case "Verificar Existência ao Cadastrar":
                sql = "Select * from paciente Where nome = ? And rg = ? And cpf = ?";
                break;
            case "Verificar Existência":
                sql = "Select * from paciente Where codigo = ?";
                break;
            case "Cadastrar":
                sql = "Insert Into paciente(nome, rua, cidade, bairro, cep, complemento, numero_residencia, uf, rg, cpf, login, senha, grupo_sanguineo, peso, altura, data_nascimento, data_cadastro, sexo, idade, telefone, celular, email, situacao_paciente, cod_funcionario) Values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ,? ,? ,?, ?, ?, ?, ?, 'Ativo', ?);";
                break;
            case "Alterar":
            case "Atualizar":
                sql = "Update paciente set nome = ?, rua = ?, cidade = ?, bairro = ?, cep = ?, complemento = ?, numero_residencia = ?, uf = ?, rg = ?, cpf = ?, login = ?, senha = ?, grupo_sanguineo = ?, peso = ?, altura = ?, data_nascimento = ?, sexo = ?, idade = ?, telefone = ?, celular = ?, email = ?, situacao_paciente = ? Where codigo = ?;";
                break;
            case "Excluir":
                sql = "Update paciente set situacao_paciente = 'Suspenso' Where codigo = ?";
                break;
            case "ListarTodos":
                sql = "Select * from paciente Left Join funcionario f on f.codigo = paciente.cod_funcionario";
                break;
            case "ListarAtivos":
                sql = "Select * from paciente Left Join funcionario f on f.codigo = paciente.cod_funcionario "
                        + "Where situacao_paciente = 'Ativo'";
                break;
            case "ListarPorCódigo":
                sql = "Select * from paciente Left Join funcionario f on f.codigo = paciente.cod_funcionario "
                        + "Where codigo = ?";
                break;
            case "ListarPorNome":
                sql = "Select * from paciente Left Join funcionario f on f.codigo = paciente.cod_funcionario "
                        + "Where nome like ?";
                break;
            case "ListarPorCPF":
                sql = "Select * from paciente Left Join funcionario f on f.codigo = paciente.cod_funcionario "
                        + "Where cpf = ?";
                break;
            case "ListarBloqueados":
                sql = "Select * from paciente Left Join funcionario f on f.codigo = paciente.cod_funcionario "
                        + "Where situacao_paciente = 'Bloqueado'";
                break;
            case "ListarSuspensos":
                sql = "Select * from paciente Left Join funcionario f on f.codigo = paciente.cod_funcionario "
                        + "Where situacao_paciente = 'Suspenso'";
                break;
            case "ListarPacienteMaisRecente":
                sql = "Select * from paciente Left Join funcionario f on f.codigo = paciente.cod_funcionario "
                        + "Where paciente.codigo = (Select Max(codigo) from paciente)";
                break;
                
                
        }
        
        return sql;
    }

}
