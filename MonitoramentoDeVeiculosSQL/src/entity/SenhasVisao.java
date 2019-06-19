package entity;

public class SenhasVisao {

    private int senha;
    private String placa;
    private String cpf_dono;

    public SenhasVisao(int senha, String placa, String cpf_dono) {
        this.senha = senha;
        this.placa = placa;
        this.cpf_dono = cpf_dono;
    }

    public SenhasVisao() {
    }

    public int getSenha() {
        return senha;
    }

    public void setSenha(int senha) {
        this.senha = senha;
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


    @Override
    public String toString() {
        String res = "";
        res += "\nSenha: " + this.getSenha() + "\n"
                + "Placa : " + this.getPlaca() + "\n"
                + "CPF do Proprietário: " + this.getCpf_dono() + "\n";
        return res;
    }
}
