package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.Conectar;
import entity.Pessoa;

public class PessoaDAO {

    private Connection connection;
    private Conectar conectar;

    public PessoaDAO(Conectar conectar) {
        this.conectar = conectar;
    }

    public boolean create(Pessoa pessoa) {
        String sql = "INSERT INTO pessoa (nome, cpf, telefone, email) VALUES(?, ?, ?, ?)";

        try {
            this.connection = conectar.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, pessoa.getNome());
            stmt.setString(2, pessoa.getCpf());
            stmt.setString(3, pessoa.getTelefone());
            stmt.setString(4, pessoa.getEmail());

            int qtdRowsAffected = stmt.executeUpdate();
            stmt.close();
            return qtdRowsAffected > 0;

        } catch (SQLException e) {
            System.out.println("");
        } finally {
            try {
                this.connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public Pessoa read(String cpf) {
        String sql = "SELECT * FROM pessoa WHERE cpf = ?";
        Pessoa pessoa = null;

        try {
            this.connection = conectar.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, cpf);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                pessoa = new Pessoa();
                pessoa.setNome(rs.getString("nome"));
                pessoa.setCpf(rs.getString("cpf"));
                pessoa.setTelefone(rs.getString("telefone"));
                pessoa.setEmail(rs.getString("email"));
                pessoa.setQtd_veiculos(rs.getInt("qtd_veiculos"));
            }
            stmt.close();
        } catch (SQLException e) {
            System.out.println("");
        } finally {
            try {
                this.connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return pessoa;
    }

    public boolean update(Pessoa pessoa) {
        String sql = "UPDATE pessoa SET telefone = ?, email = ? WHERE cpf = ?";

        try {
            this.connection = conectar.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setString(1, pessoa.getTelefone());
            stmt.setString(2, pessoa.getEmail());
            stmt.setString(3, pessoa.getCpf());

            int qtdRowsAffected = stmt.executeUpdate();
            stmt.close();
            return qtdRowsAffected > 0;

        } catch (SQLException e) {
            System.out.println("");
        } finally {
            try {
                this.connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public boolean delete(String cpf) {
        String sql = "DELETE FROM pessoa WHERE cpf = ?";

        try {
            this.connection = conectar.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, cpf);

            int qtdRowsAffected = stmt.executeUpdate();
            stmt.close();
            return qtdRowsAffected > 0;

        } catch (SQLException e) {
            System.out.println("");
        } finally {
            try {
                this.connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
