package entity;

public class Pessoa {

    private String nome;
    private String cpf;
    private String telefone;
    private String email;
    private int qtd_veiculos;

    public Pessoa(String nome, String cpf, String telefone, String email) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.email = email;
        this.qtd_veiculos = 0;
    }

    public Pessoa() {
    }

    public Pessoa(String cpf, String telefone, String email) {
        this.cpf = cpf;
        this.telefone = telefone;
        this.email = email;
    }


    

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getQtd_veiculos() {
        return qtd_veiculos;
    }

    public void setQtd_veiculos(int qtd_veiculos) {
        this.qtd_veiculos = qtd_veiculos;
    }

    @Override
    public String toString() {
        String res = "";
        res += "\nNome: " + this.getNome() + "\n"
                + "CPF: " + this.getCpf() + "\n"
                + "Telefone: " + this.getTelefone() + "\n"
                + "Email: " + this.getEmail() + "\n"
                + "Quantidade de Veículos Cadastrados: " + this.getQtd_veiculos() + "\n";
        return res;
    }

}
