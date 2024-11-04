package org.example.Resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.example.Model.Cliente;
import org.example.Service.ClienteService;

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
            return Response.status(Response.Status.CREATED).entity("Cliente criado com sucesso!").build();
        } else {
            return Response.status(Response.Status.CONFLICT)
                    .entity("Cliente com o CPF informado já existe.")
                    .build();
        }
    }

    // Método para buscar um cliente pelo ID
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarClientePorId(@PathParam("id") int id) {
        Cliente cliente = clienteService.buscarClientePorId(id);
        if (cliente == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Cliente não encontrado com o ID: " + id)
                    .build();
        }
        return Response.ok(cliente).build();
    }

    // Método para listar todos os clientes
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarClientes() {
        List<Cliente> clientes = clienteService.listarClientes();
        if (clientes == null || clientes.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Nenhum cliente encontrado.")
                    .build();
        }
        return Response.ok(clientes).build();
    }

    // Método para buscar um cliente pelo CPF
    @GET
    @Path("/buscarCPF")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarCliente(@QueryParam("cpf") String cpf) {
        if (cpf == null || cpf.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("CPF é obrigatório.")
                    .build();
        }

        Cliente cliente = clienteService.buscarClientePorCPF(cpf);
        if (cliente == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Cliente não encontrado com o CPF: " + cpf)
                    .build();
        }

        return Response.ok(cliente).build();
    }

    // Método para atualizar um cliente existente
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizarCliente(Cliente cliente) {
        if (cliente.getCpf() == null || cliente.getCpf().isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("CPF é obrigatório para atualização.")
                    .build();
        }

        boolean atualizado = clienteService.atualizarCliente(cliente);
        if (atualizado) {
            return Response.ok("Cliente atualizado com sucesso!").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Cliente não encontrado para atualização.")
                    .build();
        }
    }

    // Método para remover um cliente pelo CPF
    @DELETE
    @Path("/removerCPF")
    @Produces(MediaType.APPLICATION_JSON)
    public Response removerCliente(@QueryParam("cpf") String cpf) {
        if (cpf == null || cpf.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("CPF é obrigatório para remoção.")
                    .build();
        }

        boolean removido = clienteService.removerClientePorCPF(cpf);
        if (removido) {
            return Response.ok("Cliente removido com sucesso!").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Cliente não encontrado para remoção.")
                    .build();
        }
    }
}
