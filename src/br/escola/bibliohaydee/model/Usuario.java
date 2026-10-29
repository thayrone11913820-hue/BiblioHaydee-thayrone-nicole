package br.escola.bibliohaydee.model;

public class Usuario {

    public static final int LIMITE_PADRAO = 3;

    private String nome;
    private String matricula;
    private String tipo;
    private Integer limiteEmprestimos;
    private Integer emprestimosAtivos;

    // Construtor completo (o seu, com limite mínimo 1)
    public Usuario(String nomeUsuario, String matriculaUsuario,
                   String tipoUsuario, Integer limiteUsuario) {

        if (!nomeValido(nomeUsuario)) {
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

        if (limiteUsuario == null || !limiteValido(limiteUsuario)) {
            throw new IllegalArgumentException("O limite deve ser no mínimo 1.");
        }

        this.nome = nomeUsuario.trim();
        this.matricula = matriculaUsuario.trim();
        this.tipo = tipoUsuario.trim().toUpperCase();
        this.limiteEmprestimos = limiteUsuario;
        this.emprestimosAtivos = 0;
    }

    // Construtor alternativo: usa o limite padrão
    public Usuario(String nomeUsuario, String matriculaUsuario, String tipoUsuario) {
        this(nomeUsuario, matriculaUsuario, tipoUsuario, LIMITE_PADRAO);
    }

    public static boolean nomeValido(String nome) {
        return nome != null && !nome.trim().isEmpty();
    }

    public static boolean limiteValido(int limite) {
        return limite >= 1;
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

    public boolean setNome(String nome) {
        if (!nomeValido(nome)) {
            return false;
        }
        this.nome = nome.trim();
        return true;
    }

    public boolean setLimiteEmprestimos(int limiteEmprestimos) {
        if (!limiteValido(limiteEmprestimos)) {
            return false;
        }
        this.limiteEmprestimos = limiteEmprestimos;
        return true;
    }

    @Override
    public String toString() {
        return nome + " | Matrícula: " + matricula
                + " | Tipo: " + tipo
                + " | Limite de empréstimos: " + limiteEmprestimos
                + " | Empréstimos ativos: " + emprestimosAtivos;
    }
}

