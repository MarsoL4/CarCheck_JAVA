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

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response criarVeiculo(Veiculo veiculo) {
        if (veiculoService.criarVeiculo(veiculo)) {
            return Response.status(Response.Status.CREATED).entity(veiculo).build();
        } else {
            return Response.status(Response.Status.CONFLICT).entity("Veículo com o número de chassi já existe.").build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarVeiculos() {
        List<Veiculo> veiculos = veiculoService.listarVeiculos();
        if (veiculos != null && !veiculos.isEmpty()) {
            return Response.ok(veiculos).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Nenhum veículo encontrado.").build();
        }
    }

    @GET
    @Path("/{numero_chassi}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarVeiculoPorChassi(@PathParam("numero_chassi") String numero_chassi) {
        Veiculo veiculo = veiculoService.buscarVeiculoPorChassi(numero_chassi);
        if (veiculo != null) {
            return Response.ok(veiculo).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Veículo não encontrado.").build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizarVeiculo(Veiculo veiculo) {
        if (veiculoService.atualizarVeiculo(veiculo)) {
            return Response.ok().entity("Veículo atualizado com sucesso!").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Veículo não encontrado.").build();
        }
    }

    @DELETE
    @Path("/{numero_chassi}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response removerVeiculo(@PathParam("numero_chassi") String numero_chassi) {
        if (veiculoService.removerVeiculoPorChassi(numero_chassi)) {
            return Response.ok().entity("Veículo removido com sucesso!").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Veículo não encontrado.").build();
        }
    }
}
