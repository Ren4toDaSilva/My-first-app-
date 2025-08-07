package app.produtos.dao;

import app.produtos.model.Produto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    private static final String URL = "jdbc:postgresql://localhost:5432/db_produtos";
    private static final String USER = "postgres";
    private static final String PASSWORD = "root";

    //Metodo para a conexao
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    //Criar Produto
    public void inserirProduto(Produto produto) throws SQLException {
        String sql = "INSERT INTO produtos (nome, preco) VALUES (?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, produto.getNome());
            stmt.setDouble(2, produto.getPreco());

            stmt.executeUpdate();
        }
    }

        //listar produtos
         public List<Produto> listarProdutos() throws SQLException {
            List<Produto> produtos = new ArrayList<>();
            String sql = "SELECT id, nome,preco FROM produtos ORDER BY id";

            try (Connection conn = getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    int id = rs.getInt("id");
                    String nome = rs.getString("nome");
                    double preco = rs.getDouble("preco");
                    produtos.add(new Produto(id, nome, preco));
                }
            }
         return produtos;
    }
    public void atualizarProduto(Produto produto) throws SQLException {
        String sql = "UPDATE produtos SET nome = ?, preco = ? WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, produto.getNome());
            stmt.setDouble(2, produto.getPreco());
            stmt.setInt(3, produto.getId());

            stmt.executeUpdate();
        }
    }
}