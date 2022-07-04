package ru.job4j.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private Properties properties;

    public TableEditor(Properties properties) {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() {
        try{
            this.connection = DriverManager.getConnection(
                properties.getProperty("url"),
                properties.getProperty("login"),
                properties.getProperty("password"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void executeStatement(String sql){
        try (Statement statement = connection.createStatement()){
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTable(String tableName) {
        String sql = String.format("CREATE TABLE IF NOT EXISTS %s();", tableName);
        executeStatement(sql);
    }

    public void dropTable(String tableName) {
        String sql = String.format("DROP TABLE IF EXISTS %s;", tableName);
        executeStatement(sql);
    }

    public void addColumn(String tableName, String columnName, String type) {
        String sql = String.format("ALTER TABLE %s ADD COLUMN %s %s;",
                tableName,
                columnName,
                type);
        executeStatement(sql);
    }

    public void dropColumn(String tableName, String columnName) {
        String sql = String.format("ALTER TABLE %s DROP COLUMN %s;",
                tableName,
                columnName);
        executeStatement(sql);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) {
        String sql = String.format("ALTER TABLE %s RENAME COLUMN %s to %s;",
                tableName,
                columnName,
                newColumnName);
        executeStatement(sql);
    }


    public static String getTableScheme(Connection connection, String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
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

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) throws IOException {
        try(InputStream resource = TableEditor.class
                .getClassLoader()
                .getResourceAsStream("app.properties")) {
            Properties properties = System.getProperties();
            properties.load(resource);
            try (TableEditor tableEditor = new TableEditor(properties)) {
                String tableName = "animals";
                tableEditor.initConnection();
                tableEditor.createTable(tableName);
                System.out.println(TableEditor.getTableScheme(tableEditor.connection, tableName));
                tableEditor.addColumn(tableName, "id", "serial primary key");
                tableEditor.addColumn(tableName, "name", "varchar(255)");
                System.out.println(TableEditor.getTableScheme(tableEditor.connection, tableName));
                tableEditor.renameColumn(tableName, "name", "kind");
                System.out.println(TableEditor.getTableScheme(tableEditor.connection, tableName));
                tableEditor.dropColumn(tableName, "kind");
                System.out.println(TableEditor.getTableScheme(tableEditor.connection, tableName));
                tableEditor.dropTable(tableName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}