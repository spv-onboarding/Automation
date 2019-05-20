package com.supervielle.backend.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.supervielle.backend.domain.calculo_renta.NivelSocioEconomico;
import com.supervielle.backend.domain.calculo_renta.VerazPoliticasEspejo;
import com.supervielle.framework.database.ConnectionPool;

public class CalculoRentaDao {

    private static final Logger LOG                                   = Logger
            .getLogger(CalculoRentaDao.class.getName());
    private static final String SQL_GET_ROW_CCR_NIVEL_SOCIO_ECONOMICO = "select Categoria, IngresoDeterminado from CCR_NivelesSocioEconomicos where Categoria = ?";
    private static final String SQL_GET_ROW_CCR_INGRESO_IP            = "select IP,IngresoDeterminado from CCR_VerazPoliticasEspejo where IP = ?";
    private static final String SQL_GET_CCR_LIMITE_GRUPOS_BANCOS      = "select LimiteGruposBancos from CCR_Configuracion";

    public static NivelSocioEconomico getNivelSocioEconomico(String categoria) {

        try (Connection connection = ConnectionPool.getInstance().getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_GET_ROW_CCR_NIVEL_SOCIO_ECONOMICO)) {

            statement.setString(1, categoria);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                NivelSocioEconomico nse = new NivelSocioEconomico();
                nse.setCategoria(rs.getString("Categoria"));
                nse.setIngresoDeterminado(rs.getDouble("IngresoDeterminado"));
                return nse;
            }

        } catch (SQLException e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
            throw new RuntimeException("Unable to run DB Query", e);
        }
        return null;
    }

    public static VerazPoliticasEspejo getIngresoIP(String categoria) {

        try (Connection connection = ConnectionPool.getInstance().getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_GET_ROW_CCR_INGRESO_IP)) {

            statement.setString(1, categoria);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                VerazPoliticasEspejo ingresoIP = new VerazPoliticasEspejo();
                ingresoIP.setIP(rs.getString("IP"));
                ingresoIP.setIngresoDeterminado(rs.getDouble("IngresoDeterminado"));
                return ingresoIP;
            }

        } catch (SQLException e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
            throw new RuntimeException("Unable to run DB Query", e);
        }
        return null;
    }

    public static Long getLimiteGruposBancos() {

        try (Connection connection = ConnectionPool.getInstance().getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_GET_CCR_LIMITE_GRUPOS_BANCOS)) {

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return rs.getLong("LimiteGruposBancos");
            }

        } catch (SQLException e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
            throw new RuntimeException("Unable to run DB Query", e);
        }
        return null;
    }

}
