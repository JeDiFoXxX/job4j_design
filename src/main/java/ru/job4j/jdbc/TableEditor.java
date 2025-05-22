package ru.job4j.jdbc;

import java.io.InputStream;
import java.sql.*;
import java.util.*;

public class TableEditor implements AutoCloseable {
    private Connection connection;
    private Properties properties;

    public TableEditor(Properties properties) throws Exception {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws Exception {
        connection = getConnection();
    }

    public void createTable(String tableName) throws Exception {
        String sql = String.format(
                "CREATE TABLE IF NOT EXISTS %s (ID SERIAL PRIMARY KEY, NAME TEXT);", tableName
        );
        executeSql(sql);
        showTable(tableName);
    }

    public void dropTable(String tableName) throws Exception {
        String sql = String.format(
                "DROP TABLE IF EXISTS %s", tableName
        );
        executeSql(sql);
    }

    public void addColumn(String tableName, String columnName, String type) throws Exception {
        String sql = String.format(
                "ALTER TABLE %s ADD COLUMN %s %s", tableName, columnName, type
        );
        executeSql(sql);
        showTable(tableName);
    }

    public void dropColumn(String tableName, String columnName) throws Exception {
        String sql = String.format(
                "ALTER TABLE %s DROP COLUMN IF EXISTS %s", tableName, columnName
        );
        executeSql(sql);
        showTable(tableName);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) throws Exception {
        String sql = String.format(
                "ALTER TABLE %s RENAME COLUMN %s TO %s", tableName, columnName, newColumnName
        );
        executeSql(sql);
        showTable(tableName);
    }

    public String getTableScheme(String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "SELECT * FROM %s LIMIT 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    private Connection getConnection() throws Exception {
        Class.forName(properties.getProperty("driver_class"));
        String url = properties.getProperty("url");
        String login = properties.getProperty("login");
        String password = properties.getProperty("password");
        return DriverManager.getConnection(url, login, password);
    }

    private void executeSql(String sqlOperation) throws Exception {
        try (Statement statement = connection.createStatement()) {
            statement.execute(sqlOperation);
        }
    }

    private void showTable(String tableName) throws Exception {
        System.out.println(getTableScheme(tableName) + "\n");
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) {
        Properties config = new Properties();
        try (InputStream in = TableEditor.class.getClassLoader().getResourceAsStream("app.properties")) {
            config.load(in);
            try (TableEditor tableEditor = new TableEditor(config)) {
                tableEditor.createTable("Example");
                tableEditor.addColumn("Example", "YEARS", "INT");
                tableEditor.renameColumn("Example", "YEARS", "USER_AGE");
                tableEditor.dropColumn("Example", "USER_AGE");
                tableEditor.dropTable("Example");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}