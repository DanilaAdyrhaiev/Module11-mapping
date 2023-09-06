package org.goit.flyway;

import org.flywaydb.core.Flyway;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Migration {
    public void migrate(){
        try{
            FileReader reader = new FileReader("src\\main\\resources\\flyway.properties");
            Properties properties = new Properties();
            properties.load(reader);

            String url = properties.getProperty("url");
            String username = properties.getProperty("username");
            String password = properties.getProperty("password");

            Flyway flyway = Flyway.configure()
                    .dataSource(url, username, password)
                    .load();

            flyway.migrate();
            System.out.println("Database migration successful!");

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
