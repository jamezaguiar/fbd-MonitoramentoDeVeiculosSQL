package controller;

import connection.Conectar;
import dao.PessoaDAO;
import entity.Pessoa;

public class PessoaController {

    Conectar conectar = new Conectar();
    PessoaDAO pessoaDao = new PessoaDAO(conectar);

    public boolean addPessoa(String nome, String cpf, String telefone, String email) {
        if (nome.equals("")) {
            return false;
        }
        if (cpf.equals("")) {
            return false;
        }
        if (telefone.equals("")) {
            return false;
        }
        if (email.equals("")) {
            return false;
        }

        Pessoa pessoa = new Pessoa();
        pessoa.setNome(nome);
        pessoa.setCpf(cpf);
        pessoa.setTelefone(telefone);
        pessoa.setEmail(email);
        return pessoaDao.create(pessoa);
    }
    
    public Pessoa buscaPessoa(String cpf) {
        return pessoaDao.read(cpf);
    }
    
    public boolean deletaPessoa(String cpf) {
        return pessoaDao.delete(cpf);
    }
    
    public boolean atualizaPessoa(String cpf, String telefone, String email) {
        Pessoa pessoa = new Pessoa(cpf, telefone, email);
        return pessoaDao.update(pessoa);
    }
}
