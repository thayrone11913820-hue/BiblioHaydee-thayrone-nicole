package br.escola.bibliohaydee.app;

import br.escola.bibliohaydee.model.Autor;
import br.escola.bibliohaydee.model.LIvro;
import br.escola.bibliohaydee.model.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final List<Autor> autores = new ArrayList<>();
    private static final List<LIvro> acervo = new ArrayList<>();
    private static final List<Usuario> usuarios = new ArrayList<>();

    public static void main(String[] args) {
        int opcao;

        do {
            exibirMenu();
            opcao = lerInteiro("Escolha: ");

            switch (opcao) {
                case 1 -> cadastrarAutor();
                case 2 -> cadastrarLivro();
                case 3 -> listarAutores();
                case 4 -> listarAcervo();
                case 5 -> cadastrarUsuario();
                case 6 -> listarUsuarios();
                case 0 -> System.out.println("Encerrando o BiblioHaydee.");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    private static void exibirMenu() {
        System.out.println("\n=== BiblioHaydee ===");
        System.out.println("1 - Cadastrar autor");
        System.out.println("2 - Cadastrar livro");
        System.out.println("3 - Listar autores");
        System.out.println("4 - Listar acervo");
        System.out.println("5 - Cadastrar usuário");
        System.out.println("6 - Listar usuários");
        System.out.println("0 - Sair");
    }

    private static void cadastrarAutor() {
        System.out.print("Nome do autor: ");
        String nome = scanner.nextLine().trim();

        if (nome.isEmpty()) {
            System.out.println("O nome não pode ficar vazio.");
            return;
        }

        System.out.print("Nacionalidade: ");
        String nacionalidade = scanner.nextLine().trim();

        int anoNascimento = lerInteiro("Ano de nascimento: ");

        Autor autor = new Autor();
        autor.setNome(nome);
        autor.setNacionalidade(nacionalidade);
        autor.setAnoNascimento(anoNascimento);

        autores.add(autor);

        System.out.println("Autor cadastrado: " + autor);
    }

    private static void cadastrarLivro() {
        if (autores.isEmpty()) {
            System.out.println("Cadastre um autor antes de cadastrar livros.");
            return;
        }

        System.out.print("Título do livro: ");
        String titulo = scanner.nextLine().trim();

        if (titulo.isEmpty()) {
            System.out.println("O título não pode ficar vazio.");
            return;
        }

        System.out.print("ISBN: ");
        String isbn = scanner.nextLine().trim();

        int anoPublicacao = lerInteiro("Ano de publicação: ");

        System.out.print("Gênero: ");
        String genero = scanner.nextLine().trim();

        System.out.println("Autores disponíveis:");
        listarAutores();

        int numero = lerInteiro("Número do autor: ");

        if (numero < 1 || numero > autores.size()) {
            System.out.println("Autor inexistente. Livro não cadastrado.");
            return;
        }

        Autor autor = autores.get(numero - 1);

        LIvro livro = new LIvro(
                titulo, isbn, autor, anoPublicacao, genero
        );

        acervo.add(livro);

        System.out.println("Livro cadastrado: " + livro);
    }

    private static void listarAutores() {
        if (autores.isEmpty()) {
            System.out.println("Nenhum autor cadastrado.");
            return;
        }

        for (int i = 0; i < autores.size(); i++) {
            System.out.println((i + 1) + " - " + autores.get(i));
        }
    }

    private static void listarAcervo() {
        if (acervo.isEmpty()) {
            System.out.println("O acervo está vazio.");
            return;
        }

        for (int i = 0; i < acervo.size(); i++) {
            System.out.println((i + 1) + " - " + acervo.get(i));
        }
    }

    private static void cadastrarUsuario() {
        String nome;

        do {
            System.out.print("Nome do usuário: ");
            nome = scanner.nextLine();

            if (nome.trim().isEmpty()) {
                System.out.println("O nome não pode ficar vazio.");
            }
        } while (nome.trim().isEmpty());

        String matricula;

        do {
            System.out.print("Matrícula: ");
            matricula = scanner.nextLine();

            if (matricula.trim().isEmpty()) {
                System.out.println("A matrícula não pode ficar vazia.");
            }
        } while (matricula.trim().isEmpty());

        String tipo;

        do {
            System.out.print("Tipo (ALUNO ou PROFESSOR): ");
            tipo = scanner.nextLine().trim();

            if (!tipo.equalsIgnoreCase("ALUNO")
                    && !tipo.equalsIgnoreCase("PROFESSOR")) {
                System.out.println("Digite ALUNO ou PROFESSOR.");
            }
        } while (!tipo.equalsIgnoreCase("ALUNO")
                && !tipo.equalsIgnoreCase("PROFESSOR"));

        int limite;

        do {
            limite = lerInteiro("Limite de empréstimos: ");

            if (limite < 0) {
                System.out.println("O limite não pode ser negativo.");
            }
        } while (limite < 0);

        Usuario usuario = new Usuario(
                nome, matricula, tipo, limite
        );

        usuarios.add(usuario);

        System.out.println("Usuário cadastrado com sucesso!");
        System.out.println(usuario);
    }

    private static void listarUsuarios() {
        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado.");
            return;
        }

        for (int i = 0; i < usuarios.size(); i++) {
            System.out.println((i + 1) + " - " + usuarios.get(i));
        }
    }

    private static int lerInteiro(String mensagem) {
        while (true) {
            System.out.print(mensagem);

            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Digite um número inteiro.");
            }
        }
    }
}