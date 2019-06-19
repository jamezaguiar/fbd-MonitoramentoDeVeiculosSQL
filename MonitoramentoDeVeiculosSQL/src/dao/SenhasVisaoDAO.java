package dao;

import connection.Conectar;
import controller.VeiculoController;
import entity.SenhasVisao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SenhasVisaoDAO {

    private Connection connection;
    private Conectar conectar;

    public SenhasVisaoDAO(Conectar conectar) {
        this.conectar = conectar;
    }
    
    public List<SenhasVisao> read() {
        String sql = "SELECT * FROM senhas";
        List<SenhasVisao> senhasvisao = new ArrayList<SenhasVisao>();
        
        try {
            this.connection = conectar.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                SenhasVisao senhavisao = new SenhasVisao();
                senhavisao.setSenha(rs.getInt("senha"));
                VeiculoController.senhas.add(rs.getInt("senha"));
                senhavisao.setPlaca(rs.getString("placa"));
                senhavisao.setCpf_dono(rs.getString("cpf_dono"));
                senhasvisao.add(senhavisao);
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
        return senhasvisao;
    }
}
