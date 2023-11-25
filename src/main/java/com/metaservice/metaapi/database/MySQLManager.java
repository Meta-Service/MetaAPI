package com.metaservice.metaapi.database;

import java.sql.*;

/**
 * Manages a connection to a MySQL database and provides methods for executing queries and updates.
 */
public class MySQLManager {

    private Connection connection;
    private String host, database, username, password;
    private int port;

    /**
     * Constructs a MySQLManager with the specified connection details.
     *
     * @param host     The MySQL server host.
     * @param port     The port on which the MySQL server is running.
     * @param database The name of the MySQL database.
     * @param username The username to use for connecting to the MySQL server.
     * @param password The password to use for connecting to the MySQL server.
     */
    public MySQLManager(String host, int port, String database, String username, String password) {
        this.host = host;
        this.port = port;
        this.database = database;
        this.username = username;
        this.password = password;
    }

    /**
     * Connects to the MySQL database using the specified connection details.
     *
     * @throws SQLException if there is an issue connecting to the database.
     */
    public void connect() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            return;
        }

        String url = "jdbc:mysql://" + host + ":" + port + "/" + database;
        connection = DriverManager.getConnection(url, username, password);
    }

    /**
     * Disconnects from the MySQL database if the connection is open.
     *
     * @throws SQLException if there is an issue disconnecting from the database.
     */
    public void disconnect() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    /**
     * Executes a SELECT query and returns the ResultSet.
     *
     * @param query The SELECT query to execute.
     * @return The ResultSet containing the results of the query.
     * @throws SQLException if there is an issue executing the query.
     */
    public ResultSet executeQuery(String query) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(query);
        return statement.executeQuery();
    }

    /**
     * Executes an UPDATE, INSERT, or DELETE query and returns the number of affected rows.
     *
     * @param query The UPDATE, INSERT, or DELETE query to execute.
     * @return The number of affected rows.
     * @throws SQLException if there is an issue executing the query.
     */
    public int executeUpdate(String query) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(query);
        return statement.executeUpdate();
    }

    /**
     * Creates a table in the database if it does not already exist.
     *
     * @param tableName The name of the table to create.
     * @param columns   The columns and their data types in the table.
     * @throws SQLException if there is an issue executing the CREATE TABLE query.
     */
    public void createTable(String tableName, String columns) throws SQLException {
        String createTableQuery = "CREATE TABLE IF NOT EXISTS " + tableName + " (" + columns + ")";
        executeUpdate(createTableQuery);
    }

    /**
     * Retrieves data from the specified table based on the given condition.
     *
     * @param tableName The name of the table to query.
     * @param condition The condition for the SELECT query.
     * @return The ResultSet containing the results of the query.
     * @throws SQLException if there is an issue executing the query.
     */
    public ResultSet get(String tableName, String condition) throws SQLException {
        String query = "SELECT * FROM " + tableName + " WHERE " + condition;
        return executeQuery(query);
    }
}
