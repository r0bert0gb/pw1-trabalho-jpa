package util;

public enum Situacao {

    VENDIDA(1, "Vendida"),
    DISPONIVEL(2, "Dispon�vel"),
    RESERVADA(3, "Reservada");

    private final int opcao;
    private final String nome;

    private Situacao(int opcao, String nome) {

        this.opcao = opcao;
        this.nome = nome;
    }

    public int getOpcao() {
        return opcao;
    }

    public String getNome() {
        return nome;
    }
}
