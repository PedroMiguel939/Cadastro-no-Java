/*
Meu objetivo com este codigo é evoluir no conceito de estrutura de dados e
testar as funcionalidades do Java como a de guardar coisas nos arquivos.
 */
import javax.imageio.IIOException;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

class No{

            String nome;
            String cpf;
            String celular;
            No proximo;

            No(String nome, String cpf, String celular){
                this.nome=nome;
                this.cpf=cpf;
                this.celular=celular;
                this.proximo=null;
            }
        }
        class cadastro {
            String arquivo = "Cadastro.txt";
            private No head;

            public void adicionar(String nome, String cpf, String celular) {
                No dados = new No(nome, cpf, celular);
                if (head == null) {
                    head = dados;
                    try { //esse try significa tentar, ele vai tentar fazer algo, se não der certo vai cair no erro
                        FileWriter escrever = new FileWriter(arquivo, true);
                        escrever.write(nome + "\n" + cpf + "\n" + celular + "\n----------------------------------------------------------------\n");
                        escrever.close();
                        System.out.println("Cadastro feito com sucesso");
                    } catch (IIOException e) {
                        System.out.println("Houve um erro ao armazenar seu cadastro");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    No atual = head;
                    while (atual.proximo != null) {
                        atual = atual.proximo;
                    }
                    atual.proximo = dados;
                    try {
                        FileWriter escrever = new FileWriter(arquivo, true);
                        escrever.write( nome + "\n" + cpf + "\n" + celular + "\n----------------------------------------------------------------\n");
                        escrever.close();
                        System.out.println("Cadastro feito com sucesso");
                    } catch (IIOException e) {
                        System.out.println("Houve um erro ao armazenar seu cadastro");
                    } catch (IOException e) {
                        throw new RuntimeException();
                    }
                }
            }

            public void remover(String nome) {
                ArrayList<String> linhasArq = new ArrayList<>();
                boolean achou = false;
                try (BufferedReader analisar = new BufferedReader(new FileReader(arquivo))) {
                    String linha;
                    while ((linha = analisar.readLine()) != null) {
                        if (linha.equals(nome)) {
                            achou = true;
                            analisar.readLine();
                            analisar.readLine();
                            analisar.readLine();
                        } else {
                            linhasArq.add(linha);
                        }
                    }
                } catch (IOException e) {
                    System.out.println("Algo deu errado ao ler o seu arquivo");
                }
                if (achou) {
                    try (BufferedWriter escrita = new BufferedWriter(new FileWriter(arquivo))) {
                        for (String linha : linhasArq) {
                            escrita.write(linha);
                            escrita.newLine();
                        }
                    } catch (IOException e) {
                        System.out.println("AConteceu um erro ao transcrever os arquivos ");
                        return;
                    }
                    System.out.println("Cadastro removido com sucesso");
                } else {
                    System.out.println("Nome não foi encontrado");
                }
            }

            public void exibirtodos() {
                if (arquivo.length() == 0) {
                    System.out.println("Sua lista esta vazia");
                } else {
                    try (BufferedReader ler = new BufferedReader(new FileReader(arquivo))) {
                        String linha;
                        while ((linha = ler.readLine()) != null) {
                            System.out.println(linha);
                        }
                    } catch (IOException e) {
                        System.out.println("Ocorreu um erro  ao ler o arquivo");

                    }
                }
            }

            public void exibirsoum(String nome) {
                ArrayList<String> linhas = new ArrayList<>();
                boolean encontrou = false;
                if (arquivo.length() == 0) {
                    System.out.println("Sua lista esta vazia");
                } else {
                    try (BufferedReader ler = new BufferedReader(new FileReader(arquivo))) {
                        String linha;
                        while ((linha = ler.readLine()) != null) {
                            if (linha.equals(nome)) {
                                encontrou = true;
                                System.out.println(linha);
                                System.out.println(ler.readLine());
                                System.out.println(ler.readLine());
                                System.out.println(ler.readLine());
                                linhas.add(linha);
                            } else {
                                linhas.add(linha);
                            }
                        }
                    } catch (IOException e) {
                        System.out.println("Algo deu errado ao exibir");
                        e.printStackTrace();
                    }
                    if (encontrou) {
                        try (BufferedWriter escrever = new BufferedWriter(new FileWriter(arquivo))) {
                            for (String linha : linhas) {
                                escrever.write(linha);
                                escrever.newLine();
                            }
                        } catch (IOException e) {
                            System.out.println("Erro ao rescrever o arquivo");
                        }
                    } else {
                        System.out.println("O nome não foi encontrado");
                    }
                }
            }

            public void editarCpf(String nome) {
                Scanner scan = new Scanner(System.in);
                ArrayList<String> linhas = new ArrayList<>();
                boolean encontrou = false;
                if (arquivo.length() == 0) {
                    System.out.println("Sua lista esta vazia");
                } else {
                    try (BufferedReader ler = new BufferedReader(new FileReader(arquivo))) {
                        String linha;
                        while ((linha = ler.readLine()) != null) {
                            if (linha.equals(nome)) {
                                encontrou = true;
                                linhas.add(linha);
                                String cpfAntigo = ler.readLine();
                                System.out.println("O seu cpf é: " + cpfAntigo + "\nDeseja alterar? responda com S ou N ");
                                char resposta = scan.next().charAt(0);
                                scan.nextLine();
                                while (resposta != 's' & resposta != 'S' & resposta != 'n' & resposta != 'N') {
                                    System.out.println("Você digitou uma letra errada, responda com S ou N ");
                                    resposta = scan.next().charAt(0);
                                    scan.nextLine();
                                }
                                if (resposta == 's' || resposta == 'S') {
                                    System.out.println("Escreva o novo cpf: ");
                                    String cpfnovo = scan.nextLine();
                                    while (!ValidaçãoCpf(cpfnovo)){
                                        System.out.println("Você digitou um cpf inválido, digite novamente: ");
                                        cpfnovo = scan.nextLine();
                                    }
                                    linhas.add(cpfnovo);
                                    System.out.println("CPF atualizado");

                                } else {
                                    linhas.add(cpfAntigo);
                                    System.out.println("Programa finalizado");
                                    return;
                                }
                                linhas.add(ler.readLine());
                                linhas.add(ler.readLine());
                            } else {
                                linhas.add(linha);
                            }
                        }
                    } catch (IOException e) {
                        System.out.println("Algo deu errado ");
                        e.printStackTrace();
                    }
                    if (encontrou) {
                        try (BufferedWriter escrever = new BufferedWriter(new FileWriter(arquivo))) {
                            for (String linha : linhas) {
                                escrever.write(linha);
                                escrever.newLine();
                            }
                        } catch (IOException e) {
                            System.out.println("Erro ao reescrever ");
                        }
                        System.out.println("Cpf foi atualizado");
                    } else {
                        System.out.println("Nome não encontrado");
                    }
                }
            }

            public void editarCelular(String nome) {
                Scanner scan = new Scanner(System.in);
                ArrayList<String> linhas = new ArrayList<>();
                boolean amorPorProgramar = false;
                if (arquivo.length() == 0) {
                    System.out.println("Esta vazio seu arquivo");
                } else {
                    try (BufferedReader ler = new BufferedReader(new FileReader(arquivo))) {
                        String linha;
                        while ((linha = ler.readLine()) != null) {
                            if (linha.equals(nome)) {
                                amorPorProgramar = true;
                                linhas.add(linha);
                                linhas.add(ler.readLine());
                                String celularAntigo = ler.readLine();
                                System.out.println("O seu número de celular é: " + celularAntigo + "\nDeseja alterar? responda com S ou N ");
                                char resposta = scan.next().charAt(0);
                                scan.nextLine();
                                while (resposta != 's' & resposta != 'S' & resposta != 'n' & resposta != 'N') {
                                    System.out.println("Você digitou um caracter errado, digite S ou N");
                                    resposta = scan.next().charAt(0);
                                    scan.nextLine();
                                }
                                if (resposta == 's' || resposta == 'S') {
                                    System.out.println("Digite seu novo número de celular");
                                    String novoNumero = scan.nextLine();
                                    while (!ValidaçãoCelular(novoNumero)){
                                        System.out.println("Você digitou um número de celular inválido, digite novamente: ");
                                        novoNumero = scan.nextLine();
                                    }
                                    linhas.add(novoNumero);
                                    System.out.println("Número de celular atualizado");
                                } else {
                                    linhas.add(celularAntigo);
                                    System.out.println("Sua alterção foi cancelada");
                                    return;
                                }
                                linhas.add(ler.readLine());
                            } else {
                                linhas.add(linha);
                            }
                        }
                    } catch (IOException e) {
                        System.out.println("Ocorreu um erro na alteração");
                        e.printStackTrace();
                    }
                    if (amorPorProgramar) {
                        try (BufferedWriter escrever = new BufferedWriter(new FileWriter(arquivo))) {
                            for (String linha : linhas) {
                                escrever.write(linha);
                                escrever.newLine();
                            }
                        } catch (IOException e) {
                            System.out.println("Erro ao rescrever seu arquivo");
                        }
                    } else {
                        System.out.println("Nome não encontrado");
                    }
                }
            }

            public void editarNome(String nome) {
                Scanner scan = new Scanner(System.in);
                ArrayList<String> SeiLa = new ArrayList<>();
                Boolean ACHEIII = false;
                if (arquivo.length() == 0) {
                    System.out.println("Seu arquivo esta vazio");
                } else {
                    try (BufferedReader euLI = new BufferedReader(new FileReader(arquivo))) {
                        String linhona;
                        while ((linhona = euLI.readLine()) != null) {
                            if (linhona.equals(nome)) {
                                ACHEIII = true;
                                String nomeAntigo = linhona;
                                System.out.println("O nome salvo é: " + nomeAntigo + "\nDeseja alterar? responda com S ou N");
                                char resposta = scan.next().charAt(0);
                                scan.nextLine();
                                while (resposta != 's' & resposta != 'S' & resposta != 'n' & resposta != 'N') {
                                    System.out.println("Você digitou um caracter errado, digite S ou N");
                                    resposta = scan.next().charAt(0);
                                    scan.nextLine();
                                }
                                if (resposta == 's' || resposta == 'S') {
                                    System.out.println("Digite o novo nome: ");
                                    String novoNome = scan.nextLine();
                                    SeiLa.add(novoNome);
                                    System.out.println("Nome alterado");
                                } else {
                                    SeiLa.add(nomeAntigo);
                                    System.out.println("Nenhuma alteração feita");
                                }
                                SeiLa.add(euLI.readLine());
                                SeiLa.add(euLI.readLine());
                                SeiLa.add(euLI.readLine());
                            } else {
                                SeiLa.add(linhona);
                            }
                        }
                    } catch (IOException e) {
                        System.out.println("Falha na alteração");
                        e.printStackTrace();
                    }
                    if (ACHEIII) {
                        try (BufferedWriter escrever = new BufferedWriter(new FileWriter(arquivo))) {
                            for (String linha : SeiLa) {
                                escrever.write(linha);
                                escrever.newLine();
                            }
                        } catch (IOException e) {
                            System.out.println("Falha ao rescrever");
                            e.printStackTrace();
                        }
                    }
                    else{
                        System.out.println("Nome não encontrado");
                    }
                }
            }
            public Boolean ValidaçãoCpf(String cpf){
                cpf = cpf.replace("-", "").replace(".", ""); //aqui ele vai substituir as barras e pontos por espaços sem nada
                return cpf.matches("\\d{11}");
            }
            public Boolean ValidaçãoCelular(String celular){
                celular = celular.replace("(", "").replace(")","").replace("-", "").replace(".", "");
                return celular.matches("\\d{11}");
            }
        }  