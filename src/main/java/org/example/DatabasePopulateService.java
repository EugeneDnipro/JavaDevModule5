package org.example;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DatabasePopulateService {

    private static String getWorkerData(String pathToFile) {
        String stringQuery = null;
        List<String> parsedWorkerData = new ArrayList<>();
        List<WorkerDataDto> workerLines= new ArrayList<>();
        int i = 0;
        try (InputStream fis = new FileInputStream(pathToFile);
             Scanner scanner = new Scanner(fis)) {
            StringBuilder query = new StringBuilder();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                parsedWorkerData = List.of(line.split(","));
                workerLines.add(new WorkerDataDto(parsedWorkerData.get(0), parsedWorkerData.get(0), parsedWorkerData.get(0), parsedWorkerData.get(0), parsedWorkerData.get(0)))
                if (!line.trim().isEmpty()) {
                    query.append(line);
                    if (line.endsWith(";")) {
                        stringQuery = query.toString();
                        query.setLength(0);
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return stringQuery;
    }

    public static void main(String[] args) {
        String workerTableSqlTemplate = "INSERT INTO worker (id, name, birthday, level, salary) VALUES (?, ?, ?, ?, ?);";
        String clientTableSqlTemplate = "INSERT INTO client (id, name) VALUES (?, ?);";
        String projectTableSqlTemplate = "INSERT INTO project (id, client_id, start_date, finish_date) VALUES (?, ?, ?, ?);";
        String project_workerTableSqlTemplate = "INSERT INTO project_worker (project_id, worker_id) VALUES (?, ?);";

        try (PreparedStatement workerTableQueryStatement = Database.getInstance().getConnection().prepareStatement(workerTableSqlTemplate)) {
            workerTableQueryStatement.setInt(1, 1);
            workerTableQueryStatement.setString(2, "Jeremy");
            workerTableQueryStatement.setString(3, "1960-04-11");
            workerTableQueryStatement.setString(4, "Senior");
            workerTableQueryStatement.setInt(5, 25000);
            workerTableQueryStatement.addBatch();
            workerTableQueryStatement.setInt(1, 2);
            workerTableQueryStatement.setString(2, "James");
            workerTableQueryStatement.setString(3, "1963-01-16");
            workerTableQueryStatement.setString(4, "Middle");
            workerTableQueryStatement.setInt(5, 10000);
            workerTableQueryStatement.addBatch();
            workerTableQueryStatement.setInt(1, 3);
            workerTableQueryStatement.setString(2, "Richard");
            workerTableQueryStatement.setString(3, "1969-12-19");
            workerTableQueryStatement.setString(4, "Junior");
            workerTableQueryStatement.setInt(5, 5000);
            workerTableQueryStatement.addBatch();
            workerTableQueryStatement.setInt(1, 4);
            workerTableQueryStatement.setString(2, "Tom");
            workerTableQueryStatement.setString(3, "1995-05-01");
            workerTableQueryStatement.setString(4, "Middle");
            workerTableQueryStatement.setInt(5, 11000);
            workerTableQueryStatement.addBatch();
            workerTableQueryStatement.setInt(1, 5);
            workerTableQueryStatement.setString(2, "Jerry");
            workerTableQueryStatement.setString(3, "1996-06-07");
            workerTableQueryStatement.setString(4, "Trainee");
            workerTableQueryStatement.setInt(5, 900);
            workerTableQueryStatement.addBatch();
            workerTableQueryStatement.setInt(1, 6);
            workerTableQueryStatement.setString(2, "Michael");
            workerTableQueryStatement.setString(3, "1969-01-03");
            workerTableQueryStatement.setString(4, "Senior");
            workerTableQueryStatement.setInt(5, 95000);
            workerTableQueryStatement.addBatch();
            workerTableQueryStatement.setInt(1, 7);
            workerTableQueryStatement.setString(2, "Ayrton");
            workerTableQueryStatement.setString(3, "1960-03-21");
            workerTableQueryStatement.setString(4, "Senior");
            workerTableQueryStatement.setInt(5, 75000);
            workerTableQueryStatement.addBatch();
            workerTableQueryStatement.setInt(1, 8);
            workerTableQueryStatement.setString(2, "Tony");
            workerTableQueryStatement.setString(3, "1970-09-20");
            workerTableQueryStatement.setString(4, "Middle");
            workerTableQueryStatement.setInt(5, 12500);
            workerTableQueryStatement.addBatch();
            workerTableQueryStatement.setInt(1, 9);
            workerTableQueryStatement.setString(2, "Lara");
            workerTableQueryStatement.setString(3, "1985-08-15");
            workerTableQueryStatement.setString(4, "Junior");
            workerTableQueryStatement.setInt(5, 3500);
            workerTableQueryStatement.addBatch();
            workerTableQueryStatement.setInt(1, 10);
            workerTableQueryStatement.setString(2, "Mark");
            workerTableQueryStatement.setString(3, "1995-07-25");
            workerTableQueryStatement.setString(4, "Trainee");
            workerTableQueryStatement.setInt(5, 500);
            workerTableQueryStatement.addBatch();
            workerTableQueryStatement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (PreparedStatement clientTableQueryStatement = Database.getInstance().getConnection().prepareStatement(clientTableSqlTemplate)) {
            clientTableQueryStatement.setInt(1, 1);
            clientTableQueryStatement.setString(2, "Intel");
            clientTableQueryStatement.addBatch();
            clientTableQueryStatement.setInt(1, 2);
            clientTableQueryStatement.setString(2, "AMD");
            clientTableQueryStatement.addBatch();
            clientTableQueryStatement.setInt(1, 3);
            clientTableQueryStatement.setString(2, "Microsoft");
            clientTableQueryStatement.addBatch();
            clientTableQueryStatement.setInt(1, 4);
            clientTableQueryStatement.setString(2, "Oracle");
            clientTableQueryStatement.addBatch();
            clientTableQueryStatement.setInt(1, 5);
            clientTableQueryStatement.setString(2, "Apple");
            clientTableQueryStatement.addBatch();
            clientTableQueryStatement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (PreparedStatement projectTableQueryStatement = Database.getInstance().getConnection().prepareStatement(projectTableSqlTemplate)) {
            projectTableQueryStatement.setInt(1, 1);
            projectTableQueryStatement.setInt(2, 1);
            projectTableQueryStatement.setString(3, "2022-09-01");
            projectTableQueryStatement.setString(4, "2022-12-01");
            projectTableQueryStatement.addBatch();
            projectTableQueryStatement.setInt(1, 2);
            projectTableQueryStatement.setInt(2, 1);
            projectTableQueryStatement.setString(3, "2021-01-01");
            projectTableQueryStatement.setString(4, "2023-07-01");
            projectTableQueryStatement.addBatch();
            projectTableQueryStatement.setInt(1, 3);
            projectTableQueryStatement.setInt(2, 2);
            projectTableQueryStatement.setString(3, "2022-10-01");
            projectTableQueryStatement.setString(4, "2023-10-01");
            projectTableQueryStatement.addBatch();
            projectTableQueryStatement.setInt(1, 4);
            projectTableQueryStatement.setInt(2, 2);
            projectTableQueryStatement.setString(3, "2021-12-01");
            projectTableQueryStatement.setString(4, "2023-12-01");
            projectTableQueryStatement.addBatch();
            projectTableQueryStatement.setInt(1, 5);
            projectTableQueryStatement.setInt(2, 2);
            projectTableQueryStatement.setString(3, "2022-11-01");
            projectTableQueryStatement.setString(4, "2024-11-01");
            projectTableQueryStatement.addBatch();
            projectTableQueryStatement.setInt(1, 6);
            projectTableQueryStatement.setInt(2, 3);
            projectTableQueryStatement.setString(3, "2020-07-01");
            projectTableQueryStatement.setString(4, "2025-01-01");
            projectTableQueryStatement.addBatch();
            projectTableQueryStatement.setInt(1, 7);
            projectTableQueryStatement.setInt(2, 3);
            projectTableQueryStatement.setString(3, "2022-03-01");
            projectTableQueryStatement.setString(4, "2023-04-01");
            projectTableQueryStatement.addBatch();
            projectTableQueryStatement.setInt(1, 8);
            projectTableQueryStatement.setInt(2, 5);
            projectTableQueryStatement.setString(3, "2023-01-01");
            projectTableQueryStatement.setString(4, "2026-05-01");
            projectTableQueryStatement.addBatch();
            projectTableQueryStatement.setInt(1, 9);
            projectTableQueryStatement.setInt(2, 4);
            projectTableQueryStatement.setString(3, "2022-05-01");
            projectTableQueryStatement.setString(4, "2024-02-01");
            projectTableQueryStatement.addBatch();
            projectTableQueryStatement.setInt(1, 10);
            projectTableQueryStatement.setInt(2, 4);
            projectTableQueryStatement.setString(3, "2023-02-01");
            projectTableQueryStatement.setString(4, "2025-08-01");
            projectTableQueryStatement.addBatch();
            projectTableQueryStatement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (PreparedStatement project_workerTableQueryStatement = Database.getInstance().getConnection().prepareStatement(project_workerTableSqlTemplate)) {
            project_workerTableQueryStatement.setInt(1, 1);
            project_workerTableQueryStatement.setInt(2, 1);
            project_workerTableQueryStatement.addBatch();
            project_workerTableQueryStatement.setInt(1, 2);
            project_workerTableQueryStatement.setInt(2, 1);
            project_workerTableQueryStatement.addBatch();
            project_workerTableQueryStatement.setInt(1, 2);
            project_workerTableQueryStatement.setInt(2, 10);
            project_workerTableQueryStatement.addBatch();
            project_workerTableQueryStatement.setInt(1, 2);
            project_workerTableQueryStatement.setInt(2, 8);
            project_workerTableQueryStatement.addBatch();
            project_workerTableQueryStatement.setInt(1, 3);
            project_workerTableQueryStatement.setInt(2, 6);
            project_workerTableQueryStatement.addBatch();
            project_workerTableQueryStatement.setInt(1, 3);
            project_workerTableQueryStatement.setInt(2, 9);
            project_workerTableQueryStatement.addBatch();
            project_workerTableQueryStatement.setInt(1, 4);
            project_workerTableQueryStatement.setInt(2, 7);
            project_workerTableQueryStatement.addBatch();
            project_workerTableQueryStatement.setInt(1, 4);
            project_workerTableQueryStatement.setInt(2, 4);
            project_workerTableQueryStatement.addBatch();
            project_workerTableQueryStatement.setInt(1, 4);
            project_workerTableQueryStatement.setInt(2, 3);
            project_workerTableQueryStatement.addBatch();
            project_workerTableQueryStatement.setInt(1, 4);
            project_workerTableQueryStatement.setInt(2, 9);
            project_workerTableQueryStatement.addBatch();
            project_workerTableQueryStatement.setInt(1, 5);
            project_workerTableQueryStatement.setInt(2, 1);
            project_workerTableQueryStatement.addBatch();
            project_workerTableQueryStatement.setInt(1, 5);
            project_workerTableQueryStatement.setInt(2, 4);
            project_workerTableQueryStatement.addBatch();
            project_workerTableQueryStatement.setInt(1, 5);
            project_workerTableQueryStatement.setInt(2, 10);
            project_workerTableQueryStatement.addBatch();
            project_workerTableQueryStatement.setInt(1, 6);
            project_workerTableQueryStatement.setInt(2, 6);
            project_workerTableQueryStatement.addBatch();
            project_workerTableQueryStatement.setInt(1, 6);
            project_workerTableQueryStatement.setInt(2, 7);
            project_workerTableQueryStatement.addBatch();
            project_workerTableQueryStatement.setInt(1, 6);
            project_workerTableQueryStatement.setInt(2, 2);
            project_workerTableQueryStatement.addBatch();
            project_workerTableQueryStatement.setInt(1, 6);
            project_workerTableQueryStatement.setInt(2, 3);
            project_workerTableQueryStatement.addBatch();
            project_workerTableQueryStatement.setInt(1, 6);
            project_workerTableQueryStatement.setInt(2, 9);
            project_workerTableQueryStatement.addBatch();
            project_workerTableQueryStatement.setInt(1, 7);
            project_workerTableQueryStatement.setInt(2, 4);
            project_workerTableQueryStatement.addBatch();
            project_workerTableQueryStatement.setInt(1, 7);
            project_workerTableQueryStatement.setInt(2, 10);
            project_workerTableQueryStatement.addBatch();
            project_workerTableQueryStatement.setInt(1, 8);
            project_workerTableQueryStatement.setInt(2, 1);
            project_workerTableQueryStatement.addBatch();
            project_workerTableQueryStatement.setInt(1, 8);
            project_workerTableQueryStatement.setInt(2, 3);
            project_workerTableQueryStatement.addBatch();
            project_workerTableQueryStatement.setInt(1, 8);
            project_workerTableQueryStatement.setInt(2, 9);
            project_workerTableQueryStatement.addBatch();
            project_workerTableQueryStatement.setInt(1, 9);
            project_workerTableQueryStatement.setInt(2, 2);
            project_workerTableQueryStatement.addBatch();
            project_workerTableQueryStatement.setInt(1, 9);
            project_workerTableQueryStatement.setInt(2, 4);
            project_workerTableQueryStatement.addBatch();
            project_workerTableQueryStatement.setInt(1, 9);
            project_workerTableQueryStatement.setInt(2, 8);
            project_workerTableQueryStatement.addBatch();
            project_workerTableQueryStatement.setInt(1, 10);
            project_workerTableQueryStatement.setInt(2, 1);
            project_workerTableQueryStatement.addBatch();
            project_workerTableQueryStatement.setInt(1, 10);
            project_workerTableQueryStatement.setInt(2, 2);
            project_workerTableQueryStatement.addBatch();
            project_workerTableQueryStatement.setInt(1, 10);
            project_workerTableQueryStatement.setInt(2, 8);
            project_workerTableQueryStatement.addBatch();
            project_workerTableQueryStatement.setInt(1, 10);
            project_workerTableQueryStatement.setInt(2, 3);
            project_workerTableQueryStatement.addBatch();
            project_workerTableQueryStatement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
