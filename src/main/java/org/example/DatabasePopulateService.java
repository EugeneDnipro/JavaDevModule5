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

    private static List<WorkerDataDto> getWorkerData(String pathToFile) {
        List<String> parsedWorkerData;
        List<WorkerDataDto> workerLines = new ArrayList<>();
        try (InputStream fis = new FileInputStream(pathToFile);
             Scanner scanner = new Scanner(fis)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                parsedWorkerData = List.of(line.split(","));
                workerLines.add(new WorkerDataDto(Integer.parseInt(parsedWorkerData.get(0)),
                        parsedWorkerData.get(1),
                        parsedWorkerData.get(2),
                        parsedWorkerData.get(3),
                        Long.parseLong(parsedWorkerData.get(4))));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return workerLines;
    }

    private static List<ClientDataDto> getClientData(String pathToFile) {
        List<String> parsedClientData;
        List<ClientDataDto> clientLines = new ArrayList<>();
        try (InputStream fis = new FileInputStream(pathToFile);
             Scanner scanner = new Scanner(fis)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                parsedClientData = List.of(line.split(","));
                clientLines.add(new ClientDataDto(Integer.parseInt(parsedClientData.get(0)),
                        parsedClientData.get(1)));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return clientLines;
    }

    private static List<ProjectDataDto> getProjectData(String pathToFile) {
        List<String> parsedProjectData;
        List<ProjectDataDto> projectLines = new ArrayList<>();
        try (InputStream fis = new FileInputStream(pathToFile);
             Scanner scanner = new Scanner(fis)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                parsedProjectData = List.of(line.split(","));
                projectLines.add(new ProjectDataDto(Integer.parseInt(parsedProjectData.get(0)),
                        parsedProjectData.get(1),
                        parsedProjectData.get(2),
                        parsedProjectData.get(3)));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return projectLines;
    }

    private static List<ProjectWorkerDataDto> getProjectWorkerData(String pathToFile) {
        List<String> parsedProjectWorkerData;
        List<ProjectWorkerDataDto> projectWorkerLines = new ArrayList<>();
        try (InputStream fis = new FileInputStream(pathToFile);
             Scanner scanner = new Scanner(fis)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                parsedProjectWorkerData = List.of(line.split(","));
                projectWorkerLines.add(new ProjectWorkerDataDto(Integer.parseInt(parsedProjectWorkerData.get(0)),
                        Integer.parseInt(parsedProjectWorkerData.get(1))));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return projectWorkerLines;
    }

    public static void main(String[] args) {
        final String WORKER_DATA_FILE = "sql/worker_data.txt";
        final String CLIENT_DATA_FILE = "sql/client_data.txt";
        final String PROJECT_DATA_FILE = "sql/project_data.txt";
        final String PROJECT_WORKER_DATA_FILE = "sql/project_worker_data.txt";

        String workerTableSqlTemplate = "INSERT INTO worker (id, name, birthday, level, salary) VALUES (?, ?, ?, ?, ?);";
        String clientTableSqlTemplate = "INSERT INTO client (id, name) VALUES (?, ?);";
        String projectTableSqlTemplate = "INSERT INTO project (id, client_id, start_date, finish_date) VALUES (?, ?, ?, ?);";
        String project_workerTableSqlTemplate = "INSERT INTO project_worker (project_id, worker_id) VALUES (?, ?);";

        try (PreparedStatement workerTableQueryStatement = Database.getInstance().getConnection().prepareStatement(workerTableSqlTemplate)) {
            for (WorkerDataDto dto : getWorkerData(WORKER_DATA_FILE)) {
                workerTableQueryStatement.setInt(1, dto.getId());
                workerTableQueryStatement.setString(2, dto.getName());
                workerTableQueryStatement.setString(3, dto.getBirthday());
                workerTableQueryStatement.setString(4, dto.getLevel());
                workerTableQueryStatement.setLong(5, dto.getSalary());
                workerTableQueryStatement.addBatch();
            }
            workerTableQueryStatement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (PreparedStatement clientTableQueryStatement = Database.getInstance().getConnection().prepareStatement(clientTableSqlTemplate)) {
            for (ClientDataDto dto : getClientData(CLIENT_DATA_FILE)) {
                clientTableQueryStatement.setInt(1, dto.getId());
                clientTableQueryStatement.setString(2, dto.getName());
                clientTableQueryStatement.addBatch();
            }
            clientTableQueryStatement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (PreparedStatement projectTableQueryStatement = Database.getInstance().getConnection().prepareStatement(projectTableSqlTemplate)) {
            for (ProjectDataDto dto : getProjectData(PROJECT_DATA_FILE)) {
                projectTableQueryStatement.setInt(1, dto.getId());
                projectTableQueryStatement.setString(2, dto.getClient_id());
                projectTableQueryStatement.setString(3, dto.getStart_date());
                projectTableQueryStatement.setString(4, dto.getFinish_date());
                projectTableQueryStatement.addBatch();
            }
            projectTableQueryStatement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (PreparedStatement project_workerTableQueryStatement = Database.getInstance().getConnection().prepareStatement(project_workerTableSqlTemplate)) {
            for (ProjectWorkerDataDto dto : getProjectWorkerData(PROJECT_WORKER_DATA_FILE)) {
                project_workerTableQueryStatement.setInt(1, dto.getProject_id());
                project_workerTableQueryStatement.setInt(2, dto.getWorker_id());
                project_workerTableQueryStatement.addBatch();
            }
            project_workerTableQueryStatement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
