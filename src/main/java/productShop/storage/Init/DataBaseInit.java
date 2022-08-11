package productShop.storage.Init;

import org.flywaydb.core.Flyway;

public class DataBaseInit {
    public void initDB(String connectionUrl) {
        Flyway flyway = Flyway
                .configure()
                .dataSource(connectionUrl, null, null)
                .load();

        flyway.migrate();
    }
}
