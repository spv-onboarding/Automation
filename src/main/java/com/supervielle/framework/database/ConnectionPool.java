package com.supervielle.framework.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.dbcp2.ConnectionFactory;
import org.apache.commons.dbcp2.PoolableConnectionFactory;
import org.apache.commons.dbcp2.PoolingDriver;
import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPool;

import com.supervielle.framework.utils.AutomationProperties;

public final class ConnectionPool {

    //Environment
    private static final String ENVIRONMENT = AutomationProperties.getString("environment").replaceAll(" ", "");

    // JDBC Database Credentials
    private static final String JDBC_SERVER = AutomationProperties.getString(ENVIRONMENT + ".database.server").replaceAll(" ", "");
    private static final String JDBC_NAME = AutomationProperties.getString(ENVIRONMENT + ".database.name").replaceAll(" ", "");
    private static final String JDBC_USER = AutomationProperties.getString(ENVIRONMENT + ".database.user").replaceAll(" ", "");
    private static final String JDBC_PASS = AutomationProperties.getString(ENVIRONMENT + ".database.password").replaceAll(" ", "");
    private static final String JDBC_SECURITY = AutomationProperties.getString(ENVIRONMENT + ".database.security").replaceAll(" ", "");
    
    // JDBC Driver Name & Database URL
    static final String JDBC_DB_URL = "jdbc:sqlserver://" + JDBC_SERVER + ";databasename=" + JDBC_NAME + ";integratedSecurity=" + JDBC_SECURITY;
    private static final int MAX_TOTAL_CONNECTIONS = 5;
    private static final Logger LOG = Logger.getLogger(ConnectionPool.class.getName());
    private static ConnectionPool INSTANCE = null;

    private ConnectionPool() {
    }

    public static ConnectionPool getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ConnectionPool();
            try {
                INSTANCE.setUpPool();
            } catch (Exception e) {
                LOG.log(Level.SEVERE, e.getMessage());
                throw new RuntimeException("Unable to set up database connection pool", e);
            }
        }
        return INSTANCE;
    }

    private void setUpPool() throws Exception {
        // 1. Register the Driver to the jbdc.driver java property
        PoolConnectionFactory.registerJDBCDriver(PoolConnectionFactory.MSSQL_DRIVER);

        // 2. Create the Connection Factory (DriverManagerConnectionFactory)
        ConnectionFactory connectionFactory = PoolConnectionFactory.getConnFactory(JDBC_DB_URL, JDBC_USER, JDBC_PASS);

        // 3. Instantiate the Factory of Pooled Objects
        PoolableConnectionFactory poolfactory = new PoolableConnectionFactory(connectionFactory, null);

        // 4. Create the Pool with the PoolableConnection objects
        ObjectPool connectionPool = new GenericObjectPool(poolfactory);

        ((GenericObjectPool) connectionPool).setMaxTotal(MAX_TOTAL_CONNECTIONS);
        // 5. Set the objectPool to enforces the association (prevent bugs)
        poolfactory.setPool(connectionPool);

        // 6. Get the Driver of the pool and register them
        PoolingDriver dbcpDriver = PoolConnectionFactory.getDBCPDriver();
        dbcpDriver.registerPool("supervielle-database", connectionPool);

    }

    public Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:apache:commons:dbcp:supervielle-database");
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, e.getMessage());
            throw new RuntimeException("Unable to get a database connection", e);
        }
    }

}