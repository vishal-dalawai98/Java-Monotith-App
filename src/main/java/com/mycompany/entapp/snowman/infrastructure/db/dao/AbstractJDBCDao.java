package com.mycompany.entapp.snowman.infrastructure.db.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public abstract class AbstractJDBCDao {

    private static final Logger LOG = LoggerFactory.getLogger(AbstractJDBCDao.class);

    @Autowired
    private DataSource dataSource;

    protected void setupDBDriver() {
        // Driver is loaded automatically by JDBC/Spring.
        // Kept for backwards compatibility with existing DAO code.
    }

    protected Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            LOG.error("Unable to obtain database connection", e);
            throw new IllegalStateException("Unable to obtain database connection", e);
        }
    }
}
