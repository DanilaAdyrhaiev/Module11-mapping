package org.goit.CrudService;

import org.goit.entities.Client;
import org.goit.entities.Planet;
import org.goit.entities.Ticket;
import org.goit.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.NoSuchElementException;

public class TicketCrudService {
    public void createTicket(Client client, Planet fromPlanet, Planet toPlanet) {
        Ticket ticket = new Ticket(client, fromPlanet, toPlanet);
        try {
            Session session = getSession();
            Transaction transaction = session.beginTransaction();
            session.merge(ticket);
            transaction.commit();
            session.close();
        } catch (Exception e) {
            throw new RuntimeException("Ticket creation failed", e);
        }
    }

    public Ticket getTicketById(long id) {
        Session session = getSession();
        Ticket ticket = session.get(Ticket.class, id);
        if (ticket == null) {
            throw new NoSuchElementException("Ticket " + id + " doesn't exist");
        }
        session.close();
        return ticket;
    }

    public void updateTicketById(long id, Client client, Planet fromPlanet, Planet toPlanet) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        Ticket ticket = session.get(Ticket.class, id);
        if (ticket != null) {
            ticket.setClient(client);
            ticket.setFromPlanet(fromPlanet);
            ticket.setToPlanet(toPlanet);
            session.update(ticket);
            transaction.commit();
        } else {
            throw new NoSuchElementException("Ticket " + id + " doesn't exist");
        }
    }

    public void deleteTicketById(long id) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        Ticket ticket = session.get(Ticket.class, id);
        if (ticket != null) {
            session.delete(ticket);
            transaction.commit();
        } else {
            throw new NoSuchElementException("Ticket " + id + " doesn't exist");
        }
        session.close();
    }

    public List<Ticket> getAll() {
        try {
            Session session = getSession();
            List<Ticket> tickets = session.createQuery("from Ticket ", Ticket.class).list();
            session.close();
            return tickets;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    

    private static Session getSession() {
        return HibernateUtil.getInstance()
                .getSessionFactory()
                .openSession();
    }
}
