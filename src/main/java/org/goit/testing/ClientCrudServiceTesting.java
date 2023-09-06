package org.goit.testing;

import org.goit.CrudService.ClientCrudService;
import org.goit.entities.Client;
import org.goit.flyway.Migration;

import java.util.List;

public class ClientCrudServiceTesting {
    public static void main(String[] args) {
        new Migration().migrate();
        List<Client> clients = new ClientCrudService().getAll();
        for(Client client : clients){
            System.out.println(client.toString());
        }
        System.out.println();
    }
}
