package org.example.Resources;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.example.Model.Cliente;
import org.example.Service.ClienteService;

@Path("clientes")
public class ClienteResource {

    private ClienteService clienteService = new ClienteService();

    @GET
    @Path("/buscarCPF")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarCliente(@QueryParam("cpf") String cpf) {
        if (cpf == null || cpf.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("CPF é obrigatório.")
                    .build();
        }

        Cliente cliente = clienteService.buscarClientePorCPF(cpf); // Altera para Cliente
        if (cliente == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Cliente não encontrado com o CPF: " + cpf)
                    .build();
        }

        return Response.ok(cliente).build(); // Retorna um único cliente
    }

}
