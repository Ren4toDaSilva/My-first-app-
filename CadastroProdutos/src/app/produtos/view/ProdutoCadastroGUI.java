package app.produtos.view;

import app.produtos.dao.ProdutoDAO;
import app.produtos.model.Produto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class ProdutoCadastroGUI extends JFrame {
    private JPanel mainPanel;
    private JTextField txtNomeProduto;
    private JTextField txtPrecoProduto;
    private JButton btnSalvar;
    private JButton btnAtualizarLista;
    private JTextArea txtAreaProdutos;
    private JLabel NomeProduto;
    private JLabel Preco;
    private JLabel lblStatusMessage;

    private ProdutoDAO produtoDAO = new ProdutoDAO();

    public ProdutoCadastroGUI() {
        setTitle("Cadastro de produtos");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); //Centraliza a janela

        add(mainPanel);

        produtoDAO = new ProdutoDAO();

        btnSalvar.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) { salvarProduto(); }
        });
        btnAtualizarLista.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) { carregarProdutoNaLista(); }
        });
    }

    private void salvarProduto() {
        String nomeProduto = txtNomeProduto.getText();
        String precoProduto = txtPrecoProduto.getText();

        if (nomeProduto.isEmpty() || precoProduto.isEmpty()) {
            lblStatusMessage.setText("erro ao cadastrar produto, Preencha todos os campos!");
            lblStatusMessage.setForeground(Color.RED);
        }

        try{
            double preco = Double.parseDouble(precoProduto);
            Produto produto = new Produto(nomeProduto, preco);
            produtoDAO.inserirProduto(produto);
            lblStatusMessage.setText("Produto cadastrado com sucesso!");
            lblStatusMessage.setForeground(Color.GREEN);
            limparCampos();
            carregarProdutoNaLista();
        }catch(NumberFormatException ex) {
            lblStatusMessage.setText("Erro: Preço Inválido. Use um número.");
            lblStatusMessage.setForeground(Color.RED);
        }catch (SQLException ex) {
            lblStatusMessage.setText("Erro no Banco de Dados." + ex.getMessage());
            lblStatusMessage.setForeground(Color.RED);
            ex.printStackTrace();
        }
    }

    private void carregarProdutoNaLista() {
        try {
            List<Produto> produtos = produtoDAO.listarProdutos();
            txtAreaProdutos.setText("");
            if (produtos.isEmpty()){
                txtAreaProdutos.setText("Nenhum produto encontrado");
            }else {
                for (Produto p : produtos){
                    txtAreaProdutos.append(p.toString() + "\n");
                }
            }
            lblStatusMessage.setText("Lista de Produtos Atualizada. ");
            lblStatusMessage.setForeground(Color.black);
        } catch (SQLException ex ){
            lblStatusMessage.setText("Erro ao carregar produtos" + ex.getMessage());
            lblStatusMessage.setForeground(Color.red);
            ex.printStackTrace();
        }
    }

    private void limparCampos() {
        txtNomeProduto.setText("");
        txtPrecoProduto.setText("");
    }

    private void createUIComponents() {

    }

}