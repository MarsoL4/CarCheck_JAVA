package org.example.DAO;

import org.example.Model.Cliente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {
    private Connection con;

    // Construtor responsável por estabelecer a conexão com o banco de dados
    public ClienteDAO() {
        con = ConexaoBD.conectarBD();
    }

    // Método para criar a tabela TBL_CLIENTE
    public void criarTabelaCliente() {
        String sql = "CREATE TABLE TBL_CLIENTE ("
                + "cliente_id INTEGER PRIMARY KEY, "
                + "nome VARCHAR(255) NOT NULL, "
                + "endereco VARCHAR(255), "
                + "contato VARCHAR(50), "
                + "cpf VARCHAR(11) UNIQUE NOT NULL"
                + ")";
        try (Statement st = con.createStatement()) {
            if (con == null) {
                System.out.println("Erro: Conexão não estabelecida.");
                return;
            }
            st.executeUpdate(sql);
            System.out.println("Tabela TBL_CLIENTE criada com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro na criação da Tabela TBL_CLIENTE: " + e.getMessage());
        }
    }

    // Método para inserir um novo cliente
    public void inserirCliente(Cliente cliente) {
        String sql = "INSERT INTO TBL_CLIENTE (cliente_id, nome, endereco, contato, cpf) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement st = con.prepareStatement(sql)) {
            if (con == null) {
                System.out.println("Erro: Conexão não estabelecida.");
                return;
            }
            st.setInt(1, cliente.getClienteId());
            st.setString(2, cliente.getNome());
            st.setString(3, cliente.getEndereco());
            st.setString(4, cliente.getContato());
            st.setString(5, cliente.getCpf());
            st.executeUpdate();
            System.out.println("Novo cliente cadastrado!");
        } catch (SQLException e) {
            System.out.println("Erro ao inserir cliente: " + e.getMessage());
        }
    }

    // Método para atualizar um cliente existente
    public void atualizarCliente(Cliente cliente) {
        String sql = "UPDATE TBL_CLIENTE SET nome = ?, endereco = ?, contato = ?, cpf = ? WHERE cliente_id = ?";
        try (PreparedStatement st = con.prepareStatement(sql)) {
            if (con == null) {
                System.out.println("Erro: Conexão não estabelecida.");
                return;
            }
            st.setString(1, cliente.getNome());
            st.setString(2, cliente.getEndereco());
            st.setString(3, cliente.getContato());
            st.setString(4, cliente.getCpf());
            st.setInt(5, cliente.getClienteId());
            int rowsAffected = st.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Cliente atualizado com sucesso!");
            } else {
                System.out.println("Cliente não encontrado para atualização.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar cliente: " + e.getMessage());
        }
    }

    // Método para recuperar todos os clientes
    public List<Cliente> recuperarClientes() {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM TBL_CLIENTE";
        try (Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Cliente cliente = new Cliente(
                        rs.getInt("cliente_id"),
                        rs.getString("nome"),
                        rs.getString("endereco"),
                        rs.getString("contato"),
                        rs.getString("cpf")
                );
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao recuperar clientes: " + e.getMessage());
        }
        return clientes;
    }

    // Método para buscar clientes pelo CPF
    public List<Cliente> buscarClienteCPF(String cpf) {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM TBL_CLIENTE WHERE cpf = ?";
        try (PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, cpf);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    Cliente cliente = new Cliente(
                            rs.getInt("cliente_id"),
                            rs.getString("nome"),
                            rs.getString("endereco"),
                            rs.getString("contato"),
                            rs.getString("cpf")
                    );
                    clientes.add(cliente);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar cliente por CPF: " + e.getMessage());
        }
        return clientes;
    }

    // Método para deletar cliente pelo CPF
    public boolean deletarClientePorCPF(String cpf) {
        String sql = "DELETE FROM TBL_CLIENTE WHERE cpf = ?";
        try (PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, cpf);
            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Cliente removido com sucesso!");
                return true;
            } else {
                System.out.println("Cliente não encontrado para remoção.");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao deletar cliente: " + e.getMessage());
            return false;
        }
    }

    // Método para remover a tabela TBL_CLIENTE
    public void dropTabelaCliente() {
        String sql = "DROP TABLE TBL_CLIENTE";
        try (Statement st = con.createStatement()) {
            if (con == null) {
                System.out.println("Erro: Conexão não estabelecida.");
                return;
            }
            st.executeUpdate(sql);
            System.out.println("Tabela TBL_CLIENTE eliminada com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao deletar a Tabela TBL_CLIENTE: " + e.getMessage());
        }
    }

    // Método para buscar um cliente pelo ID no banco de dados
    public Cliente buscarClientePorId(int id) {
        Cliente cliente = null;
        try {
            PreparedStatement pst = con.prepareStatement("SELECT * FROM TBL_CLIENTE WHERE cliente_id = ?");
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                cliente = new Cliente(
                        rs.getInt("cliente_id"),
                        rs.getString("nome"),
                        rs.getString("endereco"),
                        rs.getString("contato"),
                        rs.getString("cpf")
                );
            }
            rs.close();
            pst.close();
        } catch (SQLException e) {
            System.out.println("\nErro ao buscar cliente por ID: " + e.getMessage());
        }
        return cliente;
    }

}
