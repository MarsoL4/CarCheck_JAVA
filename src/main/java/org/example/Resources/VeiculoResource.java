package org.example.Resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.example.Model.Veiculo;
import org.example.Service.VeiculoService;

import java.util.List;

@Path("veiculos")
public class VeiculoResource {

    private VeiculoService veiculoService = new VeiculoService();

    // Método para criar um novo veículo
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response criarVeiculo(Veiculo veiculo) {
        if (veiculo.getNumero_chassi() == null || veiculo.getNumero_chassi().isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Número de chassi é obrigatório.")
                    .build();
        }
        boolean criado = veiculoService.criarVeiculo(veiculo);
        if (criado) {
            return Response.status(Response.Status.CREATED).entity("Veículo criado com sucesso!").build();
        } else {
            return Response.status(Response.Status.CONFLICT)
                    .entity("Veículo com o número de chassi informado já existe.")
                    .build();
        }
    }

    // Método para listar todos os veículos
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarVeiculos() {
        List<Veiculo> veiculos = veiculoService.listarVeiculos();
        if (veiculos == null || veiculos.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Nenhum veículo encontrado.")
                    .build();
        }
        return Response.ok(veiculos).build();
    }

    // Método para buscar um veículo pelo número de chassi
    @GET
    @Path("/buscarChassi")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarVeiculo(@QueryParam("numero_chassi") String numero_chassi) {
        if (numero_chassi == null || numero_chassi.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Número de chassi é obrigatório.")
                    .build();
        }

        Veiculo veiculo = veiculoService.buscarVeiculoPorChassi(numero_chassi);
        if (veiculo == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Veículo não encontrado com o número de chassi: " + numero_chassi)
                    .build();
        }

        return Response.ok(veiculo).build();
    }

    // Método para atualizar um veículo existente
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizarVeiculo(Veiculo veiculo) {
        if (veiculo.getNumero_chassi() == null || veiculo.getNumero_chassi().isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Número de chassi é obrigatório para atualização.")
                    .build();
        }

        boolean atualizado = veiculoService.atualizarVeiculo(veiculo);
        if (atualizado) {
            return Response.ok("Veículo atualizado com sucesso!").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Veículo não encontrado para atualização.")
                    .build();
        }
    }

    // Método para remover um veículo pelo número de chassi
    @DELETE
    @Path("/removerChassi")
    @Produces(MediaType.APPLICATION_JSON)
    public Response removerVeiculo(@QueryParam("numero_chassi") String numero_chassi) {
        if (numero_chassi == null || numero_chassi.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Número de chassi é obrigatório para remoção.")
                    .build();
        }

        boolean removido = veiculoService.removerVeiculoPorChassi(numero_chassi);
        if (removido) {
            return Response.ok("Veículo removido com sucesso!").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Veículo não encontrado para remoção.")
                    .build();
        }
    }
}
