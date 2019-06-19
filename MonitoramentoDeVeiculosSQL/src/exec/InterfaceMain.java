package exec;

import java.util.Scanner;
import java.sql.SQLException;

import controller.PessoaController;
import controller.SenhasVisaoController;
import controller.VeiculoController;
import entity.Pessoa;
import entity.SenhasVisao;
import entity.Veiculo;
import java.util.List;

public class InterfaceMain {

    Scanner input = new Scanner(System.in);
    PessoaController pessoaC = new PessoaController();
    VeiculoController veiculoC = new VeiculoController();
    SenhasVisaoController senhasvisaoC = new SenhasVisaoController();

    public void start() throws SQLException {
        System.out.println("===== Sistema de Monitoramento de Veículos =====");
        boolean loop = true;
        senhasvisaoC.buscaSenhasVisao();
        for (int senha: VeiculoController.senhas) {
            veiculoC.mudarLocalizacao(senha);
        }
        while (loop) {
            for (int senha : VeiculoController.senhas) {
                veiculoC.andar(senha);
            }
            System.out.println("== Menu de Opções ==");
            System.out.println("1. Cadastrar Usuário");
            System.out.println("2. Cadastrar Veiculo");
            System.out.println("3. Excluir Usuário");
            System.out.println("4. Excluir Veículo");
            System.out.println("5. Informações do Usuário");
            System.out.println("6. Informações do Veículo");
            System.out.println("7. Atualizar Informações do Usuário");
            System.out.println("8. Travar/Destravar Veículo");
            System.out.println("9. Acesso Administrativo");
            System.out.println("0. Sair");

            System.out.print("Digite o comando desejado: ");
            String comando = input.nextLine();
            int ncomando = Integer.parseInt(comando);

            if (ncomando < 0 || ncomando > 9) {
                System.out.println("Opção inválida, verifique os números correspondentes a cada opção!");
            } else {
                switch (ncomando) {
                    case 1:
                        cadastraPessoa();
                        break;
                    case 2:
                        cadastraVeiculo();
                        break;
                    case 3:
                        deletaPessoa();
                        break;
                    case 4:
                        deletaVeiculo();
                        break;
                    case 5:
                        buscaPessoa();
                        break;
                    case 6:
                        buscaVeiculo();
                        break;
                    case 7:
                        atualizaPessoa();
                        break;
                    case 8:
                        travaDestravaVeiculo();
                        break;
                    case 9:
                        buscaSenhasVisao();
                        break;
                    case 0:
                        System.out.println("Encerrando sistema...");
                        loop = false;
                        break;
                    default:
                        System.out.println("Programa em construção!");
                }
            }
        }
    }

    public void cadastraPessoa() throws SQLException {
        System.out.println("==== Cadastrar Usuário ====");
        System.out.print("Digite o nome do usuário: ");
        String nome = input.nextLine();
        if (nome.equals("")) {
            System.out.println("Nome em branco!");
            return;
        }

        System.out.print("Digite o CPF do usuário: ");
        String cpf = input.nextLine();
        if (cpf.equals("")) {
            System.out.println("CPF em branco!");
            return;
        }

        System.out.print("Digite o telefone do usuário: ");
        String telefone = input.nextLine();
        if (telefone.equals("")) {
            System.out.println("Telefone em branco!");
            return;
        }

        System.out.print("Digite o email do usuário: ");
        String email = input.nextLine();
        if (email.equals("")) {
            System.out.println("Email em branco!");
            return;
        }

        boolean resultado = pessoaC.addPessoa(nome, cpf, telefone, email);
        if (resultado) {
            System.out.println("Usuário cadastrado com sucesso!");
        } else {
            System.out.println("Algo deu errado, tente novamente...");
        }
    }

    public void cadastraVeiculo() throws SQLException {
        System.out.println("==== Cadastrar Veículo ====");
        System.out.print("Digite a placa do veículo: ");
        String placa = input.nextLine();
        if (placa.equals("")) {
            System.out.println("Placa em branco!");
            return;
        }

        System.out.print("Digite o CPF do Proprietário: ");
        String cpf_dono = input.nextLine();
        if (cpf_dono.equals("")) {
            System.out.println("CPF do Proprietário em branco!");
            return;
        }

        System.out.println("Digite o tipo do veículo");
        System.out.print("Use: *Moto*, *Carro* ou *Caminhão*: ");
        String tipo = input.nextLine();
        if (tipo.equals("")) {
            System.out.println("Tipo em branco!");
            return;
        }

        System.out.print("Digite a marca do veículo: ");
        String marca = input.nextLine();
        if (marca.equals("")) {
            System.out.println("Marca em branco!");
            return;
        }

        System.out.print("Digite a cor do veículo: ");
        String cor = input.nextLine();
        if (cor.equals("")) {
            System.out.println("Campo cor em branco!");
            return;
        }

        System.out.print("Digite o ano do veículo: ");
        String anostr = input.nextLine();
        int ano = Integer.parseInt(anostr);
        if (anostr.equals("")) {
            System.out.println("Ano em branco!");
            return;
        }

        System.out.print("Digite o DF de emplacamento: ");
        String df_emplacamento = input.nextLine();
        if (df_emplacamento.equals("")) {
            System.out.println("DF de emplacamento em branco!");
            return;
        }

        boolean resultado = veiculoC.addVeiculo(placa, cpf_dono, tipo, marca, cor, ano, df_emplacamento);

        if (resultado) {
            System.out.println("Veiculo cadastrado com sucesso!");
        } else {
            System.out.println("Algo deu errado, tente novamente...");
        }
    }

    public void buscaPessoa() {
        System.out.println("==== Informações do Usuário ====");
        System.out.print("Digite o CPF do usuário a ser buscado: ");
        String cpf = input.nextLine();
        if (cpf.equals("")) {
            System.out.println("CPF em branco!");
            return;
        }
        Pessoa pessoa = pessoaC.buscaPessoa(cpf);

        if (pessoa == null) {
            System.out.println("Usuário não encontrado!");
        } else {
            System.out.println(pessoa);
        }
    }

    public void buscaVeiculo() {
        System.out.println("==== Informações do Veículo ====");
        System.out.print("Digite a senha do veículo a ser buscado: ");
        String senhastr = input.nextLine();
        int senha = Integer.parseInt(senhastr);
        if (senhastr.equals("")) {
            System.out.println("Senha em branco!");
            return;
        }
        Veiculo veiculo = veiculoC.buscaVeiculo(senha);

        if (veiculo == null) {
            System.out.println("Veículo não encontrado!");
        } else {
            System.out.println(veiculo);
        }
    }
    
    public void atualizaPessoa() {
        System.out.println("==== Atualizar Usuário ====");
        System.out.println("(OS PARÂMETROS ATUALIZÁVEIS SÂO: Telefone e Email.");
        System.out.print("Digite o CPF do usuário a ser atualizado: ");
        String cpf = input.nextLine();
        if (cpf.equals("")) {
            System.out.println("CPF em branco!");
            return;
        }
        System.out.print("Digite o novo telefone: ");
        String telefone = input.nextLine();
        System.out.print("Digite o novo email: ");
        String email = input.nextLine();
        boolean resultado = pessoaC.atualizaPessoa(cpf, telefone, email);
        if (resultado) {
            System.out.println("Atualização feita com sucesso!");
        } else {
            System.out.println("Algo deu errado, tente novamente...");
            return;
        }
    }

    public void deletaPessoa() {
        System.out.println("==== Deletar Usuário ====");
        System.out.print("Digite o CPF do usuário a ser deletado: ");
        String cpf = input.nextLine();
        if (cpf.equals("")) {
            System.out.println("CPF em branco!");
            return;
        }
        System.out.print("Você tem certeza? (s/n): ");
        String pergunta = input.nextLine();
        if (pergunta.equals("s")) {
            boolean resultado = pessoaC.deletaPessoa(cpf);
            if (resultado) {
                System.out.println("Usuário deletado com sucesso!");
            } else {
                System.out.println("Algo deu errado, tente novamente...");
                return;
            }
        } else if (pergunta.equals("n")) {
            System.out.println("Cancelando deleção de usuário...");
            return;
        } else {
            System.out.println("Comando inválido!");
            return;
        }
    }

    public void deletaVeiculo() {
        System.out.println("==== Deletar Veículo ====");
        System.out.print("Digite a senha do veículo a ser deletado: ");
        String senhastr = input.nextLine();
        int senha = Integer.parseInt(senhastr);
        if (senhastr.equals("")) {
            System.out.println("Senha em branco!");
            return;
        }
        System.out.print("Você tem certeza? (s/n): ");
        String pergunta = input.nextLine();
        if (pergunta.equals("s")) {
            boolean resultado = veiculoC.deletaVeiculo(senha);
            if (resultado) {
                System.out.println("Veículo deletado com sucesso!");
            } else {
                System.out.println("Algo deu errado, tente novamente...");
                return;
            }
        } else if (pergunta.equals("n")) {
            System.out.println("Cancelando deleção de veículo...");
            return;
        } else {
            System.out.println("Comando inválido!");
            return;
        }
    }

    public void buscaSenhasVisao() {
        System.out.println("***** ACESSO DE ADMINISTRADOR *****");
        System.out.print("Digite a senha de acesso: ");
        String senhaAcesso = input.nextLine();
        if (senhaAcesso.equals("")) {
            System.out.println("Senha em branco!");
            return;
        }
        List<SenhasVisao> senhasvisao = senhasvisaoC.buscaSenhasVisao();
        if (senhasvisaoC.getSenhaAcessoAdm().equals(senhaAcesso)) {
            System.out.println("Acesso liberado.");
            System.out.println(senhasvisao);
        } else {
            System.out.println("Senha incorreta!");
            return;
        }
    }

    public void travaDestravaVeiculo() {
        System.out.println("==== Trava/Destrava de Veículo ====");
        System.out.print("Digite *T* para travar ou *D* para destravar: ");
        String operacao = input.nextLine();
        if (operacao.equals("")) {
            System.out.println("Operação em branco!");
            return;
        }
        if (operacao.equals("T")) {
            System.out.print("Digite a senha do veículo a ser travado: ");
            String senhastr = input.nextLine();
            int senha = Integer.parseInt(senhastr);
            boolean resultado = veiculoC.travar(senha);
            if (resultado) {
                System.out.println("Veículo travado!");
            } else {
                System.out.println("Algo deu errado, tente novamente...");
                return;
            }
        } else if (operacao.equals("D")) {
            System.out.print("Digite a senha do veículo a ser destravado: ");
            String senhastr = input.nextLine();
            int senha = Integer.parseInt(senhastr);
            boolean resultado = veiculoC.destravar(senha);
            if (resultado) {
                System.out.println("Veículo destravado!");
            } else {
                System.out.println("Algo deu errado, tente novamente...");
                return;
            }
        } else {
            System.out.println("Operação incorreta, tente *T* para travar ou *D* para destravar.");
        }
    }
}
