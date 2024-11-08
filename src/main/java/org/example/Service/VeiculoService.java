package org.example.Service;

import org.example.DAO.VeiculoDAO;
import org.example.Model.Veiculo;

import java.util.List;

public class VeiculoService {

    private VeiculoDAO veiculoDAO;

    public VeiculoService() {
        this.veiculoDAO = new VeiculoDAO();
    }

    public boolean criarVeiculo(Veiculo veiculo) {
        try {
            List<Veiculo> veiculosExistentes = veiculoDAO.buscarVeiculoChassi(veiculo.getNumero_chassi());
            if (!veiculosExistentes.isEmpty()) {
                System.out.println("Erro: Veículo com o número de chassi informado já existe.");
                return false;
            }
            veiculoDAO.inserirVeiculo(veiculo.getCliente_id(), veiculo.getMarca(),
                    veiculo.getModelo(), veiculo.getAno_fabricacao(), veiculo.getNumero_chassi());
            System.out.println("Veículo criado com sucesso!");
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao criar veículo: " + e.getMessage());
            return false;
        }
    }

    public List<Veiculo> listarVeiculos() {
        try {
            return veiculoDAO.recuperarVeiculos();
        } catch (Exception e) {
            System.out.println("Erro ao listar veículos: " + e.getMessage());
            return null;
        }
    }

    public Veiculo buscarVeiculoPorChassi(String numero_chassi) {
        try {
            List<Veiculo> veiculos = veiculoDAO.buscarVeiculoChassi(numero_chassi);
            if (veiculos.isEmpty()) {
                System.out.println("Veículo não encontrado com o número de chassi: " + numero_chassi);
                return null;
            }
            return veiculos.get(0);
        } catch (Exception e) {
            System.out.println("Erro ao buscar veículo: " + e.getMessage());
            return null;
        }
    }

    public boolean atualizarVeiculo(Veiculo veiculoAtualizado) {
        try {
            Veiculo veiculoExistente = buscarVeiculoPorChassi(veiculoAtualizado.getNumero_chassi());
            if (veiculoExistente == null) {
                System.out.println("Erro: Veículo não encontrado.");
                return false;
            }
            veiculoDAO.inserirVeiculo(veiculoAtualizado.getCliente_id(),
                    veiculoAtualizado.getMarca(), veiculoAtualizado.getModelo(),
                    veiculoAtualizado.getAno_fabricacao(), veiculoAtualizado.getNumero_chassi());
            System.out.println("Veículo atualizado com sucesso!");
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao atualizar veículo: " + e.getMessage());
            return false;
        }
    }

    public boolean removerVeiculoPorChassi(String numero_chassi) {
        try {
            Veiculo veiculo = buscarVeiculoPorChassi(numero_chassi);
            if (veiculo == null) {
                System.out.println("Erro: Veículo não encontrado.");
                return false;
            }
            veiculoDAO.deletarVeiculoPorChassi(numero_chassi);
            System.out.println("Veículo removido com sucesso!");
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao remover veículo: " + e.getMessage());
            return false;
        }
    }
}
