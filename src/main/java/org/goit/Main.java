package org.goit;

import org.goit.CrudService.ClientCrudService;
import org.goit.entities.Client;
import org.goit.flyway.Migration;

public class Main {
    public static void main(String[] args) {
        var val = new ClientCrudService().getAll();
        for (Client client :val){
            client.toString();
        }

    }
}