package br.escola.bibliohaydee.model;

public class Usuario {

    private String nome;
    private String matricula;
    private String tipo;
    private Integer limiteEmprestimos;
    private Integer emprestimosAtivos;

    public Usuario(String nomeUsuario, String matriculaUsuario,
                   String tipoUsuario, Integer limiteUsuario) {

        if (nomeUsuario == null || nomeUsuario.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome não pode ser vazio.");
        }

        if (matriculaUsuario == null || matriculaUsuario.trim().isEmpty()) {
            throw new IllegalArgumentException("A matrícula não pode ser vazia.");
        }

        if (tipoUsuario == null ||
                !(tipoUsuario.trim().equalsIgnoreCase("ALUNO")
                        || tipoUsuario.trim().equalsIgnoreCase("PROFESSOR"))) {
            throw new IllegalArgumentException("O tipo deve ser ALUNO ou PROFESSOR.");
        }

        if (limiteUsuario == null || limiteUsuario < 0) {
            throw new IllegalArgumentException("O limite deve ser maior ou igual a zero.");
        }

        nome = nomeUsuario.trim();
        matricula = matriculaUsuario.trim();
        tipo = tipoUsuario.trim().toUpperCase();
        limiteEmprestimos = limiteUsuario;
        emprestimosAtivos = 0;
    }

    public String getNome() {
        return nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public String getTipo() {
        return tipo;
    }

    public Integer getLimiteEmprestimos() {
        return limiteEmprestimos;
    }

    public Integer getEmprestimosAtivos() {
        return emprestimosAtivos;
    }


    public String toString() {
        return nome + " | Matrícula: " + matricula
                + " | Tipo: " + tipo
                + " | Limite de empréstimos: " + limiteEmprestimos
                + " | Empréstimos ativos: " + emprestimosAtivos;
    }
}
