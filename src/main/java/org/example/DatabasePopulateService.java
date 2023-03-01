package org.example;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DatabasePopulateService {
    private static final String POPULATE_DB_FILE = "sql/populate_db.sql";

    public static void main(String[] args) throws SQLException {
        String workerTableSqlTemplate = "INSERT INTO worker (id, name, birthday, level, salary) VALUES (?);";
        String clientTableSqlTemplate = "INSERT INTO client (id, name) VALUES (?);";
        String projectTableSqlTemplate = "INSERT INTO project (id, client_id, start_date, finish_date) VALUES (?);";
        String project_workerTableSqlTemplate = "INSERT INTO project_worker (project_id, worker_id) VALUES (?);";

        try (InputStream fis = new FileInputStream(POPULATE_DB_FILE);
             Scanner scanner = new Scanner(fis);
             Statement stmt = Database.getInstance().getConnection().createStatement()) {
            StringBuilder query = new StringBuilder();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (!line.trim().isEmpty()) {
                    query.append(line);
                    if (line.endsWith(";")) {
                        stmt.executeUpdate(query.toString());
                        query.setLength(0);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
