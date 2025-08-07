package app.produtos.model;

public class Produto {
    private int id;
    private String nome;
    private double preco;

    public Produto(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    public Produto(int id, String nome, double preco) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
    }

    //Getters
    public int getId() {return id; }
    public double getPreco() { return preco; }
    public String getNome() { return nome; }

    //Setters
    public void setId(int id) { this.id = id; }
    public void setPreco(double preco) { this.preco = preco; }
    public void setNome(String nome) { this.nome = nome; }

    @Override
    public String toString() {
        return "ID: " + id + "Nome: " + nome + "Preço: R$ " + String.format("%.2f", preco);
    }
}

