package org.example.Model;

public class Cliente {
    private int clienteId;
    private String nome;
    private String endereco;
    private String contato;
    private String cpf;

    // Construtor padrão (sem parâmetros)
    public Cliente() {
    }

    // Construtor com todos os parâmetros
    public Cliente(int clienteId, String nome, String endereco, String contato, String cpf) {
        this.clienteId = clienteId;
        this.nome = nome;
        this.endereco = endereco;
        this.contato = contato;
        this.cpf = cpf;
    }

    // Getters e Setters para cada atributo

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    // Método para exibir informações do cliente (opcional, mas útil para depuração)
    @Override
    public String toString() {
        return "Cliente{" +
                "clienteId=" + clienteId +
                ", nome='" + nome + '\'' +
                ", endereco='" + endereco + '\'' +
                ", contato='" + contato + '\'' +
                ", cpf='" + cpf + '\'' +
                '}';
    }
}
