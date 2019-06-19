package controller;

import connection.Conectar;
import dao.SenhasVisaoDAO;
import entity.SenhasVisao;
import java.util.List;

public class SenhasVisaoController {

    Conectar conectar = new Conectar();
    SenhasVisaoDAO senhasVisaoDao = new SenhasVisaoDAO(conectar);
    private final String senhaAcessoAdm = "admin";

    public List<SenhasVisao> buscaSenhasVisao() {
        return senhasVisaoDao.read();
    }

    public String getSenhaAcessoAdm() {
        return senhaAcessoAdm;
    }
    
}
