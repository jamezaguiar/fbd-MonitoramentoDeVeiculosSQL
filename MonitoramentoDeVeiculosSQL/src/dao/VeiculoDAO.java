package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.Conectar;
import entity.Veiculo;

public class VeiculoDAO {

    private Connection connection;
    private Conectar conectar;

    public VeiculoDAO(Conectar conectar) {
        this.conectar = conectar;
    }

    public boolean create(Veiculo veiculo) {
        String sql = "INSERT INTO veiculo (placa, cpf_dono, senha, tipo, marca, cor, ano, df_emplacamento, travado, ligado, andando, velocidade_atual, localizacao) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            this.connection = conectar.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, veiculo.getPlaca());
            stmt.setString(2, veiculo.getCpf_dono());
            stmt.setInt(3, veiculo.getSenha());
            stmt.setString(4, veiculo.getTipo());
            stmt.setString(5, veiculo.getMarca());
            stmt.setString(6, veiculo.getCor());
            stmt.setInt(7, veiculo.getAno());
            stmt.setString(8, veiculo.getDf_emplacamento());
            stmt.setBoolean(9, veiculo.isTravado());
            stmt.setBoolean(10, veiculo.isLigado());
            stmt.setBoolean(11, veiculo.isAndando());
            stmt.setDouble(12, veiculo.getVelocidade_atual());
            stmt.setString(13, veiculo.getLocalizacao());

            int qtdRowsAffected = stmt.executeUpdate();
            stmt.close();
            if (qtdRowsAffected > 0) {
                System.out.print("A senha para administração deste veículo é: ");
                System.out.println(veiculo.getSenha());
                return true;
            }
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

    public Veiculo read(int senha) {
        String sql = "SELECT * FROM veiculo WHERE senha = ?";
        Veiculo veiculo = null;

        try {
            this.connection = conectar.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, senha);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                veiculo = new Veiculo();
                veiculo.setPlaca(rs.getString("placa"));
                veiculo.setCpf_dono(rs.getString("cpf_dono"));
                veiculo.setSenha(rs.getInt("senha"));
                veiculo.setTipo(rs.getString("tipo"));
                veiculo.setMarca(rs.getString("marca"));
                veiculo.setCor(rs.getString("cor"));
                veiculo.setAno(rs.getInt("ano"));
                veiculo.setDf_emplacamento(rs.getString("df_emplacamento"));
                veiculo.setTravado(rs.getBoolean("travado"));
                veiculo.setLigado(rs.getBoolean("ligado"));
                veiculo.setAndando(rs.getBoolean("andando"));
                veiculo.setVelocidade_atual(rs.getDouble("velocidade_atual"));
                veiculo.setLocalizacao(rs.getString("localizacao"));

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
        return veiculo;
    }

    public boolean delete(int senha) {
        String sql = "DELETE FROM veiculo WHERE senha = ?";

        try {
            this.connection = conectar.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, senha);

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
