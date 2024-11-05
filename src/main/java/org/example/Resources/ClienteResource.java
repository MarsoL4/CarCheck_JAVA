package org.example.Resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.ResponseBuilder;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.ext.Provider;
import org.example.Model.Cliente;
import org.example.Service.ClienteService;

import java.io.IOException;
import java.util.List;

@Path("clientes")
public class ClienteResource {

    private ClienteService clienteService = new ClienteService();

    // Método para criar um novo cliente
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response criarCliente(Cliente cliente) {
        if (cliente.getCpf() == null || cliente.getCpf().isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("CPF é obrigatório.")
                    .build();
        }
        boolean criado = clienteService.criarCliente(cliente);
        if (criado) {
            return addCorsHeaders(Response.status(Response.Status.CREATED).entity("Cliente criado com sucesso!")).build();
        } else {
            return addCorsHeaders(Response.status(Response.Status.CONFLICT)
                    .entity("Cliente com o CPF informado já existe.")).build();
        }
    }

    // Método para buscar um cliente pelo ID
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarClientePorId(@PathParam("id") int id) {
        Cliente cliente = clienteService.buscarClientePorId(id);
        if (cliente == null) {
            return addCorsHeaders(Response.status(Response.Status.NOT_FOUND)
                    .entity("Cliente não encontrado com o ID: " + id)).build();
        }
        return addCorsHeaders(Response.ok(cliente)).build();
    }

    // Método para listar todos os clientes
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarClientes() {
        List<Cliente> clientes = clienteService.listarClientes();
        if (clientes == null || clientes.isEmpty()) {
            return addCorsHeaders(Response.status(Response.Status.NOT_FOUND)
                    .entity("Nenhum cliente encontrado.")).build();
        }
        return addCorsHeaders(Response.ok(clientes)).build();
    }

    // Método para buscar um cliente pelo CPF
    @GET
    @Path("/buscarCPF")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarCliente(@QueryParam("cpf") String cpf) {
        if (cpf == null || cpf.isEmpty()) {
            return addCorsHeaders(Response.status(Response.Status.BAD_REQUEST)
                    .entity("CPF é obrigatório.")).build();
        }

        Cliente cliente = clienteService.buscarClientePorCPF(cpf);
        if (cliente == null) {
            return addCorsHeaders(Response.status(Response.Status.NOT_FOUND)
                    .entity("Cliente não encontrado com o CPF: " + cpf)).build();
        }

        return addCorsHeaders(Response.ok(cliente)).build();
    }

    // Método para atualizar um cliente existente
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizarCliente(Cliente cliente) {
        if (cliente.getCpf() == null || cliente.getCpf().isEmpty()) {
            return addCorsHeaders(Response.status(Response.Status.BAD_REQUEST)
                    .entity("CPF é obrigatório para atualização.")).build();
        }

        boolean atualizado = clienteService.atualizarCliente(cliente);
        if (atualizado) {
            return addCorsHeaders(Response.ok("Cliente atualizado com sucesso!")).build();
        } else {
            return addCorsHeaders(Response.status(Response.Status.NOT_FOUND)
                    .entity("Cliente não encontrado para atualização.")).build();
        }
    }

    // Método para remover um cliente pelo CPF
    @DELETE
    @Path("/removerCPF")
    @Produces(MediaType.APPLICATION_JSON)
    public Response removerCliente(@QueryParam("cpf") String cpf) {
        if (cpf == null || cpf.isEmpty()) {
            return addCorsHeaders(Response.status(Response.Status.BAD_REQUEST)
                    .entity("CPF é obrigatório para remoção.")).build();
        }

        boolean removido = clienteService.removerClientePorCPF(cpf);
        if (removido) {
            return addCorsHeaders(Response.ok("Cliente removido com sucesso!")).build();
        } else {
            return addCorsHeaders(Response.status(Response.Status.NOT_FOUND)
                    .entity("Cliente não encontrado para remoção.")).build();
        }
    }

    // Método para adicionar cabeçalhos CORS
    private ResponseBuilder addCorsHeaders(ResponseBuilder response) {
        return response.header("Access-Control-Allow-Origin", "http://localhost:3000")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS")
                .header("Access-Control-Allow-Headers", "Content-Type, Authorization");
    }

    // Configuração para OPTIONS para suportar preflight requests
    @OPTIONS
    @Path("{path : .*}")
    public Response options() {
        return addCorsHeaders(Response.ok()).build();
    }
}
