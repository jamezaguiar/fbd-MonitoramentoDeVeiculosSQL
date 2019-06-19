package entity;

public class Veiculo {

    private String placa;
    private String cpf_dono;
    private int senha;
    private String tipo;
    private String marca;
    private String cor;
    private int ano;
    private String df_emplacamento;
    private boolean travado;
    private boolean ligado;
    private boolean andando;
    private double velocidade_atual;
    private String localizacao;

    public Veiculo(String placa, String cpf_dono, int senha, String tipo, String marca, String cor, int ano, String df_emplacamento, boolean travado, boolean ligado, boolean andando, double velocidade_atual, String localizacao) {
        this.placa = placa;
        this.cpf_dono = cpf_dono;
        this.senha = senha;
        this.tipo = tipo;
        this.marca = marca;
        this.cor = cor;
        this.ano = ano;
        this.df_emplacamento = df_emplacamento;
        this.travado = travado;
        this.ligado = ligado;
        this.andando = andando;
        this.velocidade_atual = velocidade_atual;
        this.localizacao = localizacao;
    }

    public Veiculo() {
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getCpf_dono() {
        return cpf_dono;
    }

    public void setCpf_dono(String cpf_dono) {
        this.cpf_dono = cpf_dono;
    }

    public int getSenha() {
        return senha;
    }

    public void setSenha(int senha) {
        this.senha = senha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getDf_emplacamento() {
        return df_emplacamento;
    }

    public void setDf_emplacamento(String df_emplacamento) {
        this.df_emplacamento = df_emplacamento;
    }

    public boolean isTravado() {
        return travado;
    }

    public void setTravado(boolean travado) {
        this.travado = travado;
    }

    public boolean isLigado() {
        return ligado;
    }

    public void setLigado(boolean ligado) {
        this.ligado = ligado;
    }

    public boolean isAndando() {
        return andando;
    }

    public void setAndando(boolean andando) {
        this.andando = andando;
    }

    public double getVelocidade_atual() {
        return velocidade_atual;
    }

    public void setVelocidade_atual(double velocidade_atual) {
        this.velocidade_atual = velocidade_atual;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    @Override
    public String toString() {
        String res = "";
        res += "\nPlaca: " + this.getPlaca() + "\n"
                + "CPF do Proprietário: " + this.getCpf_dono() + "\n"
                + "Tipo: " + this.getTipo() + "\n"
                + "Marca: " + this.getMarca() + "\n"
                + "Cor: " + this.getCor() + "\n"
                + "Ano: " + this.getAno() + "\n"
                + "DF de Emplacamento: " + this.getDf_emplacamento() + "\n";
        res += "Está travado?: ";
        res += (this.isTravado()) ? "Sim" + "\n" : "Não" + "\n";
        res += "Está ligado?: ";
        res += (this.isLigado()) ? "Sim" + "\n" : "Não" + "\n";
        res += "Está em movimento?: ";
        res += (this.isAndando()) ? "Sim" + "\n" : "Não" + "\n";
        res += "Velocidade Atual: " + this.getVelocidade_atual() + "\n"
                + "Localização Atual: " + this.getLocalizacao() + "\n";
        return res;
    }

}
