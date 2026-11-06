package br.escola.bibliohaydee.app;

import br.escola.bibliohaydee.model.Autor;
import br.escola.bibliohaydee.model.Livro;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final ArrayList<Livro> livros = new ArrayList<>();

    public static void main(String[] args) {

        carregarAcervo();

        int opcao;

        do {
            exibirMenu();
            opcao = lerInteiro("Escolha: ");

            switch (opcao) {
                case 1:
                    pesquisarPorTitulo(livros);
                    break;

                case 2:
                    pesquisarPorAutor(livros);
                    break;

                case 3:
                    gerarRelatorio(livros);
                    break;

                case 4:
                    listarLivros(livros);
                    break;

                case 0:
                    System.out.println("Encerrando o sistema...");
                    break;

                default:
                    System.out.println("Opção inválida.");
            }

        } while (opcao != 0);

        scanner.close();
    }

    private static void exibirMenu() {

        System.out.println("\n===== BIBLIOHAYDEE =====");
        System.out.println("1 - Pesquisar livro por título");
        System.out.println("2 - Pesquisar livro por autor");
        System.out.println("3 - Gerar relatório do acervo");
        System.out.println("4 - Listar todos os livros");
        System.out.println("0 - Sair");
    }

    private static int lerInteiro(String mensagem) {

        while (true) {
            System.out.print(mensagem);

            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Digite um número válido.");
            }
        }
    }

    private static void carregarAcervo() {

        Autor machado = new Autor("Machado de Assis", "Brasileira", 1839);
        Autor graciliano = new Autor("Graciliano Ramos", "Brasileira", 1892);
        Autor aluisio = new Autor("Aluísio Azevedo", "Brasileira", 1857);
        Autor jorge = new Autor("Jorge Amado", "Brasileira", 1912);

        livros.add(new Livro(
                "Dom Casmurro",
                "ISBN001",
                machado,
                1899,
                "Romance"
        ));

        livros.add(new Livro(
                "Memórias Póstumas de Brás Cubas",
                "ISBN002",
                machado,
                1881,
                "Romance"
        ));

        livros.add(new Livro(
                "Quincas Borba",
                "ISBN003",
                machado,
                1891,
                "Romance"
        ));

        livros.add(new Livro(
                "Vidas Secas",
                "ISBN004",
                graciliano,
                1938,
                "Romance"
        ));

        livros.add(new Livro(
                "O Cortiço",
                "ISBN005",
                aluisio,
                1890,
                "Naturalismo"
        ));

        livros.add(new Livro(
                "Capitães da Areia",
                "ISBN006",
                jorge,
                1937,
                "Romance"
        ));
    }

    private static void pesquisarPorTitulo(ArrayList<Livro> livros) {

        System.out.print("Digite parte do título: ");
        String termo = scanner.nextLine();

        if (termo.isBlank()) {
            System.out.println("Problema: o termo de pesquisa não pode ser vazio.");
            return;
        }

        termo = termo.trim().toLowerCase();

        boolean encontrou = false;
        int quantidade = 0;

        for (Livro livro : livros) {

            String titulo = livro.getTitulo().toLowerCase();

            if (titulo.contains(termo)) {
                System.out.println(livro);
                encontrou = true;
                quantidade++;
            }
        }

        if (!encontrou) {
            System.out.println("Não há resultados para essa pesquisa.");
        } else {
            System.out.println("Livros encontrados: " + quantidade);
        }
    }

    private static void pesquisarPorAutor(ArrayList<Livro> livros) {

        System.out.print("Digite o nome ou sobrenome do autor: ");
        String termo = scanner.nextLine();

        if (termo.isBlank()) {
            System.out.println("Problema: o nome do autor não pode ser vazio.");
            return;
        }

        termo = termo.trim().toLowerCase();

        boolean encontrou = false;
        int quantidade = 0;

        for (Livro livro : livros) {

            String nomeAutor = livro.getAutor().getNome()
                    .trim()
                    .toLowerCase();

            boolean corresponde = nomeAutor.equals(termo);

            if (!corresponde) {

                String[] partes = nomeAutor.split("\\s+");

                for (String parte : partes) {

                    if (parte.equals(termo)) {
                        corresponde = true;
                        break;
                    }
                }
            }

            if (corresponde) {
                System.out.println(livro);
                encontrou = true;
                quantidade++;
            }
        }

        if (!encontrou) {
            System.out.println("Autor não encontrado.");
        } else {
            System.out.println("Livros encontrados: " + quantidade);
        }
    }

    private static void gerarRelatorio(ArrayList<Livro> livros) {

        StringBuilder relatorio = new StringBuilder();

        relatorio.append("\n===== RELATÓRIO DO ACERVO =====\n");

        if (livros.isEmpty()) {

            relatorio.append("Não há livros cadastrados no acervo.\n");

        } else {

            int numero = 1;

            for (Livro livro : livros) {

                relatorio.append("Livro ").append(numero).append("\n");
                relatorio.append("Título: ")
                        .append(livro.getTitulo()).append("\n");

                relatorio.append("Autor: ")
                        .append(livro.getAutor().getNome()).append("\n");

                relatorio.append("Disponibilidade: ")
                        .append(livro.isDisponivel() ? "Disponível" : "Indisponível")
                        .append("\n");

                relatorio.append("-----------------------------\n");

                numero++;
            }

            relatorio.append("Total de livros: ")
                    .append(livros.size())
                    .append("\n");
        }

        System.out.println(relatorio.toString());
    }

    private static void listarLivros(ArrayList<Livro> livros) {

        if (livros.isEmpty()) {
            System.out.println("Não há livros cadastrados.");
            return;
        }

        for (Livro livro : livros) {
            System.out.println(livro);
        }
    }
}