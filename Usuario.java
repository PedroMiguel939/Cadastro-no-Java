import java.util.Scanner;

public class Usuario {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        cadastro Cadastro = new cadastro();
        System.out.println("Seja bem vindo\n");
        int opcao = 0;
        while (opcao != 5) {
            System.out.println("O que você deseja fazer\n1-Adicionar Cadastro\n2-Exibir\n3-Remover Cadastro\n4-Editar\n5-Sair");
            opcao = scan.nextInt();
            scan.nextLine();
            switch (opcao) {
                case 1:
                    System.out.println("Digite um nome para adicionar: ");
                    String nome = scan.nextLine();
                    System.out.println("Digite um CPF: ");
                    String cpf = scan.nextLine();
                    while (!Cadastro.ValidaçãoCpf(cpf)) {
                        System.out.println("Você digitou um cpf inválido, digite novamente: ");
                        cpf = scan.nextLine();
                    }
                    System.out.println("Digite seu número de celular: ");
                    String celular = scan.nextLine();
                    while (!Cadastro.ValidaçãoCelular(celular)) {
                        System.out.println("Você digitou um número de celular inválido, digite novamente: ");
                        celular = scan.nextLine();
                    }
                    Cadastro.adicionar(nome, cpf, celular);
                    break;
                case 2:
                    System.out.println("Qual das opções a seguir você deseja\n1-Exibir um\n2-Exibir todos");
                    opcao = scan.nextInt();
                    scan.nextLine();
                    while (opcao != 1 && opcao != 2) {
                        System.out.println("Você digitou uma opção invalida, digite novamente \n1-Exibir um\n2-Exibir todos");
                        int opcaoEx = scan.nextInt();
                    }
                    if (opcao == 1) {
                        System.out.println("Qual é o nome que você esta procurando");
                        String nomeE = scan.nextLine();
                        Cadastro.exibirsoum(nomeE);
                    } else if (opcao == 2) {
                        System.out.println("Estes são todos os cadastros salvos: ");
                        Cadastro.exibirtodos();
                    }
                    break;
                case 3:
                    System.out.println("Digite o nome do cadastro que você deseja excluir: ");
                    String nomeEx = scan.nextLine();
                    Cadastro.remover(nomeEx);
                    break;
                case 4:
                    System.out.println("O que você deseja editar: \n1-Editar CPF\n2-Editar Celular\n3-Editar nome\n");
                    int opcaoEd = scan.nextInt();
                    scan.nextLine();
                    while (opcaoEd != 1 && opcaoEd != 2) {
                        System.out.println("Você digitou uma opção invalida, digite novamente \n1-Exibir um\n2-Exibir todos");
                        opcaoEd = scan.nextInt();
                    }
                    if (opcaoEd == 1) {
                        System.out.println("Digite o nome do cadastro que você procura: ");
                        String nomeEd = scan.nextLine();
                        Cadastro.editarCpf(nomeEd);
                    } else if (opcaoEd == 2) {
                        System.out.println("Digite o nome do cadastro que você procura: ");
                        String nomeEd = scan.nextLine();
                        Cadastro.editarCelular(nomeEd);
                    }
                    else if (opcaoEd == 3){
                        System.out.println("Digite o nome do cadastro que você procura: ");
                        String nomeEd = scan.nextLine();
                        Cadastro.editarNome(nomeEd);
                    }
                    break;
            }
        }
        System.out.println("Programa finalizado");
    }
}