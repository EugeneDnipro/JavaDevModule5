package org.example;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DatabaseQueryService {
//    private static final String FIND_MAX_PROJECTS_CLIENT_FILE = "sql/find_max_projects_client.sql";
    private static final String FIND_MAX_SALARY_WORKER_FILE = "sql/find_max_salary_worker.sql";
    private static final String FIND_LONGEST_PROJECT_FILE = "sql/find_longest_project.sql";
    private static final String PRINT_PROJECT_PRICE_FILE = "sql/print_project_prices.sql";

    private static String getQuery(String pathToFile) {
        String stringQuery = null;
        try (InputStream fis = new FileInputStream(pathToFile);
             Scanner scanner = new Scanner(fis)) {
            StringBuilder query = new StringBuilder();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
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

    List<MaxProjectCountClientDto> findMaxProjectsClient() {
        ResultSet rs;
        List<MaxProjectCountClientDto> selectedMaxProjectCountClient = new ArrayList<>();
        String sqlTemplate = "SELECT *\n" +
                "    FROM (SELECT name, count(name) AS project_count\n" +
                "        FROM (SELECT name, project.client_id\n" +
                "            FROM client\n" +
                "            JOIN project\n" +
                "            ON client.id = project.client_id)\n" +
                "        GROUP BY name)\n" +
                "    WHERE project_count = (SELECT MAX(project_count)\n" +
                "        FROM (SELECT name, count(name) AS project_count\n" +
                "            FROM (SELECT name, project.client_id\n" +
                "                FROM client\n" +
                "                JOIN project\n" +
                "                ON client.id = project.client_id)\n" +
                "            GROUP BY name));";
        try (PreparedStatement queryStatement = Database.getInstance().getConnection().prepareStatement(sqlTemplate)) {

//            queryStatement.setString(1, "name");

            rs = queryStatement.executeQuery();
            while (rs.next()) {
                selectedMaxProjectCountClient.add(new MaxProjectCountClientDto(rs.getString("name"), rs.getInt("project_count")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return selectedMaxProjectCountClient;
    }

    List<MaxSalaryWorkerDto> findMaxSalaryWorker() {
        ResultSet rs;
        List<MaxSalaryWorkerDto> selectedMaxSalaryWorker = new ArrayList<>();

        try (Statement stmt = Database.getInstance().getConnection().createStatement()) {
            rs = stmt.executeQuery(getQuery(FIND_MAX_SALARY_WORKER_FILE));
            while (rs.next()) {
                selectedMaxSalaryWorker.add(new MaxSalaryWorkerDto(rs.getString("name"), rs.getLong("salary")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return selectedMaxSalaryWorker;
    }

    List<LongestProjectDto> findLongestProject() {
        ResultSet rs;
        List<LongestProjectDto> selectedLongestProject = new ArrayList<>();

        try (Statement stmt = Database.getInstance().getConnection().createStatement()) {
            rs = stmt.executeQuery(getQuery(FIND_LONGEST_PROJECT_FILE));
            while (rs.next()) {
                selectedLongestProject.add(new LongestProjectDto(rs.getString("name"), rs.getInt("month_count")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return selectedLongestProject;
    }

    List<YoungestEldestWorkersDto> findYoungestEldestWorkers() {
        ResultSet rs;
        List<YoungestEldestWorkersDto> selectedYoungestEldestWorkers = new ArrayList<>();
        String sqlTemplate = "SELECT\n" +
                " CASE WHEN birthday=(SELECT MAX(birthday) FROM worker) THEN ?\n" +
                "     WHEN birthday=(SELECT MIN(birthday) FROM worker) THEN ?\n" +
                " END AS type, name, birthday\n" +
                " FROM worker WHERE (CASE WHEN birthday=(SELECT MAX(birthday) FROM worker) THEN ?\n" +
                "                        WHEN birthday=(SELECT MIN(birthday) FROM worker) THEN ?\n" +
                "                   END) != 'null';";
        try (PreparedStatement queryStatement = Database.getInstance().getConnection().prepareStatement(sqlTemplate)) {

            queryStatement.setString(1, "YOUNGEST");
            queryStatement.setString(2, "ELDEST");
            queryStatement.setString(3, "YOUNGEST");
            queryStatement.setString(4, "ELDEST");

            rs = queryStatement.executeQuery();
            while (rs.next()) {
                selectedYoungestEldestWorkers.add(new YoungestEldestWorkersDto(rs.getString("type"), rs.getString("name"), LocalDate.parse(rs.getString("birthday"))));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return selectedYoungestEldestWorkers;
    }

    List<ProjectPriceDto> findProjectPrice() {
        ResultSet rs;
        List<ProjectPriceDto> selectedProjectPrice = new ArrayList<>();

        try (Statement stmt = Database.getInstance().getConnection().createStatement()) {
            rs = stmt.executeQuery(getQuery(PRINT_PROJECT_PRICE_FILE));
            while (rs.next()) {
                selectedProjectPrice.add(new ProjectPriceDto(rs.getString("name"), rs.getLong("price")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return selectedProjectPrice;
    }

    public static void main(String[] args) {
        List<MaxProjectCountClientDto> maxProjectCountClients = new DatabaseQueryService().findMaxProjectsClient();
        System.out.println("maxProjectCountClients.toString() = " + maxProjectCountClients.toString());
        List<MaxSalaryWorkerDto> maxSalaryWorker = new DatabaseQueryService().findMaxSalaryWorker();
        System.out.println("maxSalaryWorker.toString() = " + maxSalaryWorker.toString());
        List<LongestProjectDto> longestProject = new DatabaseQueryService().findLongestProject();
        System.out.println("longestProject.toString() = " + longestProject.toString());
        List<YoungestEldestWorkersDto> youngestEldestWorkers = new DatabaseQueryService().findYoungestEldestWorkers();
        System.out.println("youngestEldestWorkers.toString() = " + youngestEldestWorkers.toString());
        List<ProjectPriceDto> projectPrice = new DatabaseQueryService().findProjectPrice();
        System.out.println("projectPrice.toString() = " + projectPrice.toString());
    }
}
