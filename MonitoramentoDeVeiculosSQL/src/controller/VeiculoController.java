package controller;

import connection.Conectar;
import dao.VeiculoDAO;
import entity.Veiculo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

public class VeiculoController {

    private Connection connection;
    Conectar conectar = new Conectar();
    VeiculoDAO veiculoDao = new VeiculoDAO(conectar);
    public static ArrayList<Integer> senhas = new ArrayList(); // ArrayList usado para que as senhas dos veículos não se repitam
    private final String cidades[] = {"Ocara", "Quixadá", "Pacajus", "Horizonte", "Fortaleza", "Sen. Pompeu", "Chorozinho", "Ibaretama", "Pedra Branca", "Quixeramobim"};
    Random r = new Random(); // Objeto random para gerar números aleatórios

    public boolean addVeiculo(String placa, String cpf_dono, String tipo, String marca, String cor, int ano, String df_emplacamento) {
        if (placa.equals("")) {
            return false;
        }
        if (cpf_dono.equals("")) {
            return false;
        }
        if (tipo.equals("")) {
            return false;
        }
        if (marca.equals("")) {
            return false;
        }
        if (cor.equals("")) {
            return false;
        }
        if (ano < 1900) {
            return false;
        }
        if (df_emplacamento.equals("")) {
            return false;
        }

        Veiculo veiculo = new Veiculo();
        veiculo.setPlaca(placa);
        veiculo.setCpf_dono(cpf_dono);
        veiculo.setSenha(gerarSenha());
        veiculo.setTipo(tipo);
        veiculo.setMarca(marca);
        veiculo.setCor(cor);
        veiculo.setAno(ano);
        veiculo.setDf_emplacamento(df_emplacamento);
        veiculo.setTravado(false);
        veiculo.setLigado(false);
        veiculo.setAndando(false);
        veiculo.setVelocidade_atual(0.0);
        veiculo.setLocalizacao(cidades[r.nextInt(9)]);
        return veiculoDao.create(veiculo);
    }

    public Veiculo buscaVeiculo(int senha) {
        return veiculoDao.read(senha);
    }

    public boolean deletaVeiculo(int senha) {
        return veiculoDao.delete(senha);
    }

    public double gerarVelocidade() {
        double vel = Math.ceil(r.nextDouble() * 120);
        return vel;
    }
    
    public String gerarCidade() {
        String cid = cidades[r.nextInt(9)];
        return cid;
    }

    public void mudarLocalizacao(int senha) {
        String sql = "UPDATE veiculo SET localizacao = ? WHERE senha = ?;";
        try {
            this.connection = conectar.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, gerarCidade());
            stmt.setInt(2, senha);
            stmt.executeUpdate();
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
    }
    
    public void andar(int senha) {
        String sql = "UPDATE veiculo SET andando = true, ligado = true, travado = false, velocidade_atual = ? WHERE travado = false AND senha = ?;";
        try {
            this.connection = conectar.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setDouble(1, gerarVelocidade());
            stmt.setInt(2, senha);
            stmt.executeUpdate();
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
    }

    public boolean travar(int senha) {
        String sql = "UPDATE veiculo SET andando = false, ligado = false, travado = true, velocidade_atual = 0.0 WHERE senha = ?;";
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

    public boolean destravar(int senha) {
        String sql = "UPDATE veiculo SET travado = false WHERE senha = ?;";
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

    public int gerarSenha() {
        int senha;

        senha = r.nextInt(Integer.MAX_VALUE);

        while (true) {
            if (senhas.isEmpty()) {
                senhas.add(senha);
                break;
            } else if (senhas.contains(senha)) {
                senha = r.nextInt(Integer.MAX_VALUE);
            } else {
                senhas.add(senha);
                break;
            }
        }

        return senha;
    }
}
