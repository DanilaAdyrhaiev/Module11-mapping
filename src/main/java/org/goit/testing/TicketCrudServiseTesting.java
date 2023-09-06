package org.goit.testing;

import org.goit.CrudService.ClientCrudService;
import org.goit.CrudService.PlanetCrudService;
import org.goit.CrudService.TicketCrudService;
import org.goit.entities.Client;
import org.goit.entities.Planet;
import org.goit.entities.Ticket;
import org.goit.flyway.Migration;

import java.util.List;
import java.util.Scanner;

public class TicketCrudServiseTesting {
    public static void main(String[] args) {
        new Migration().migrate();
        TicketCrudService service = new TicketCrudService();
        var val = service.getAll();
        for (Ticket ticket : val){
            System.out.println(ticket.toString());
        }
        System.out.println();
        Ticket ticket = service.getTicketById(10);
        System.out.println(ticket.toString());

    }
}
