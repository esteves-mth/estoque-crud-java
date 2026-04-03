package br.com.esteves;

import br.com.esteves.dao.EstoqueDAO;
import br.com.esteves.model.Produto;
import br.com.esteves.db.ConnectionFactory;
import java.sql.*;
import java.util.List;

public class App {
  public static void main(String[] args) {
    System.out.println("=== Sistema Estoque ===\n");
    ConnectionFactory cf = new ConnectionFactory();
    EstoqueDAO dao = new EstoqueDAO(cf);
    try (Connection conn = cf.getConnection()) {
      System.out.println("Connection OK!");
    } catch (Exception e) {
      System.out.println("Error: " + e.getMessage());
    }

    Produto p = new Produto(0, "Calculadora", 10, 1);
    try {
      dao.inserir(p);
      System.out.println("ID do Produto " + p.getNome() + ": " + p.getId());
    } catch (SQLException e) {
      System.err.println("Erro ao inserir produto: " + e.getMessage());
    }

    try {
      List<Produto> produtos = dao.listarTodos();
      produtos.forEach(System.out::println);
    } catch (SQLException e) {
      System.err.println("Erro ao listar produtos: " + e.getMessage());
    }

    try {
     boolean deletado = dao.deletar(7);
     System.out.println("Deletado ID 7: " + deletado);
   } catch (SQLException e) {
     System.err.println("Erro ao deletar produto: " + e.getMessage());
   }
  }
}
