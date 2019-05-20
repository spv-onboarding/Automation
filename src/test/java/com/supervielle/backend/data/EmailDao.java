package com.supervielle.backend.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.lang3.StringUtils;

import com.supervielle.framework.database.ConnectionPool;

public class EmailDao {

    private static final String SQL_GET_EMAIL_TOKEN = "SELECT OneTimePassword FROM CodigosAleatorios WHERE ClienteId = ? ORDER BY CreatedAt DESC";
    private static final String SQL_SET_EMAIL_TO_NULL = "UPDATE CLI_Clientes SET EMail = null WHERE CUIL = ? OR EMail = ?";
    private static final Logger LOG = Logger.getLogger(EmailDao.class.getName());

    public static String getToken(Integer clienteId) {

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_GET_EMAIL_TOKEN)) {

            statement.setMaxRows(1);
            statement.setInt(1, clienteId);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return rs.getString("OneTimePassword");
            }

        } catch (SQLException e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
            throw new RuntimeException("Unable to run DB Query", e);
        }
        return StringUtils.EMPTY;
    }

    public static void setEmailToNull(String cuil,String email) {

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SET_EMAIL_TO_NULL)) {

            statement.setString(1, cuil);
            statement.setString(2, email);
            statement.executeUpdate();

        } catch (SQLException e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
            throw new RuntimeException("Unable to run DB Query", e);
        }
    }

}
