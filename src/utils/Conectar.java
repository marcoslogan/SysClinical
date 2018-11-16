/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

/**
 *
 * @author MARCOS
 */
public class Conectar {

    public static Connection getConexao() throws SQLException, ErroConectarException {
        Connection con = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sysclinical", "root", "");
        } catch (ClassNotFoundException ex) {
            throw new ErroConectarException("Driver não encontrado.\nDetalhes Técnicos:\n" + ex.getMessage());
        } catch (SQLException ex) {
            throw new ErroConectarException("Erro ao tentar conectar.\nDetalhes Técnicos:\n" + ex.getMessage());
        }

        return con;
    }

    public static void close(Connection conn, PreparedStatement smt, ResultSet set) throws SQLException, ErroDAOException {

        if (conn != null && !conn.isClosed()) {
            conn.close();
        }

        if (smt != null && !smt.isClosed()) {
            smt.close();
        }

        if (set != null && !set.isClosed()) {
            set.close();
        }

    }
}
