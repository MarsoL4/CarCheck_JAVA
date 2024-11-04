package org.example.Model;

public class Veiculo {
    int veiculo_id;
    int cliente_id;
    String marca;
    String modelo;
    String ano_fabricacao;
    String numero_chassi;

    // Construtor padrão
    public Veiculo() {
    }

    // Construtor completo
    public Veiculo(int veiculo_id, int cliente_id, String marca, String modelo, String ano_fabricacao, String numero_chassi) {
        this.veiculo_id = veiculo_id;
        this.cliente_id = cliente_id;
        this.marca = marca;
        this.modelo = modelo;
        this.ano_fabricacao = ano_fabricacao;
        this.numero_chassi = numero_chassi;
    }

    // Getters
    public int getVeiculo_id() {
        return veiculo_id;
    }

    public int getCliente_id() {
        return cliente_id;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public String getAno_fabricacao() {
        return ano_fabricacao;
    }

    public String getNumero_chassi() {
        return numero_chassi;
    }

    // Setters
    public void setVeiculo_id(int veiculo_id) {
        this.veiculo_id = veiculo_id;
    }

    public void setCliente_id(int cliente_id) {
        this.cliente_id = cliente_id;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setAno_fabricacao(String ano_fabricacao) {
        this.ano_fabricacao = ano_fabricacao;
    }

    public void setNumero_chassi(String numero_chassi) {
        this.numero_chassi = numero_chassi;
    }
}
