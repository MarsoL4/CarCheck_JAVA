package org.example.Service;

import org.example.DAO.ClienteDAO;
import org.example.Model.Cliente;

import java.util.List;

public class ClienteService {

    private ClienteDAO clienteDAO;

    // Construtor que inicializa o ClienteDAO
    public ClienteService() {
        this.clienteDAO = new ClienteDAO();
    }

    // Método para criar um novo cliente
    public boolean criarCliente(Cliente cliente) {
        try {
            // Validação: verifica se o CPF já está cadastrado
            List<Cliente> clientesExistentes = clienteDAO.buscarClienteCPF(cliente.getCpf());
            if (!clientesExistentes.isEmpty()) {
                System.out.println("Erro: Cliente com o CPF informado já existe.");
                return false;
            }
            clienteDAO.inserirCliente(cliente);
            System.out.println("Cliente criado com sucesso!");
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao criar cliente: " + e.getMessage());
            return false;
        }
    }

    // Método para listar todos os clientes
    public List<Cliente> listarClientes() {
        try {
            return clienteDAO.recuperarClientes();
        } catch (Exception e) {
            System.out.println("Erro ao listar clientes: " + e.getMessage());
            return null;
        }
    }

    // Método para buscar um cliente pelo CPF
    public Cliente buscarClientePorCPF(String cpf) {
        try {
            List<Cliente> clientes = clienteDAO.buscarClienteCPF(cpf);
            if (clientes.isEmpty()) {
                System.out.println("Cliente não encontrado com o CPF: " + cpf);
                return null;
            }
            return clientes.get(0); // Retorna o primeiro cliente encontrado
        } catch (Exception e) {
            System.out.println("Erro ao buscar cliente: " + e.getMessage());
            return null;
        }
    }

    // Método para atualizar um cliente existente
    public boolean atualizarCliente(Cliente clienteAtualizado) {
        try {
            Cliente clienteExistente = buscarClientePorCPF(clienteAtualizado.getCpf());
            if (clienteExistente == null) {
                System.out.println("Erro: Cliente não encontrado.");
                return false;
            }
            clienteDAO.inserirCliente(clienteAtualizado);
            System.out.println("Cliente atualizado com sucesso!");
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao atualizar cliente: " + e.getMessage());
            return false;
        }
    }

    // Método para remover um cliente pelo CPF
    public boolean removerClientePorCPF(String cpf) {
        try {
            Cliente cliente = buscarClientePorCPF(cpf);
            if (cliente == null) {
                System.out.println("Erro: Cliente não encontrado.");
                return false;
            }
            clienteDAO.deletarClientePorCPF(cpf);
            System.out.println("Cliente removido com sucesso!");
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao remover cliente: " + e.getMessage());
            return false;
        }
    }

    // Método para buscar um cliente pelo ID
    public Cliente buscarClientePorId(int id) {
        try {
            return clienteDAO.buscarClientePorId(id);
        } catch (Exception e) {
            System.out.println("Erro ao buscar cliente: " + e.getMessage());
            return null;
        }
    }

}
