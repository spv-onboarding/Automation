package com.supervielle.backend.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.lang3.StringUtils;

import com.supervielle.backend.domain.DatoAnalitico;
import com.supervielle.backend.domain.DatoFiliatorio;
import com.supervielle.backend.domain.Domicilio;
import com.supervielle.backend.domain.Persona;
import com.supervielle.framework.database.ConnectionPool;
import com.supervielle.framework.utils.WebDriverUtils;

public class ClientDao {

    private static final String SQL_SET_ALTA_CLIENTE = "UPDATE CLI_Clientes SET AltaEnProceso = 0, UltimaEtapaEjecutada = null, ProcesoAltaExitoso = 0 WHERE CUIL = ?";
    private static final Logger LOG = Logger.getLogger(EmailDao.class.getName());
    private static final String SQL_GET_RESPONSE_SERVICIO_LOGGIN_SERVICIOS = "SELECT REPSONSE FROM LOGGIN_SERVICIOS where NOMBRE_SERVICIO = ? AND NRO_DOCUMENTO = ? ORDER BY FECHA DESC";
    private static final String SQL_GET_RESPONSE_LOGGIN_SERVICIOS_BY_OPERATION = "SELECT REPSONSE FROM LOGGIN_SERVICIOS where NOMBRE_OPERACION = ? AND NRO_DOCUMENTO = ? ORDER BY FECHA DESC";
    private static final String SQL_GET_REQUEST_LOGGIN_SERVICIOS_BY_OPERATION = "SELECT REQUEST FROM LOGGIN_SERVICIOS where NOMBRE_OPERACION = ? AND NRO_DOCUMENTO = ? ORDER BY FECHA DESC";

    private static final String SQL_GET_CLI_ID_FROM_CUIL = "SELECT Id FROM CLI_Clientes WHERE CUIL = ?";
    private static final String SQL_REMOVE_CONYUGE = "DELETE FROM CLI_Conyuges WHERE ClienteId = ?";
    private static final String SQL_GET_ROW_CONYUGUE = "SELECT * FROM CLI_Conyuges WHERE ClienteId = ?";
    private static final String SQL_GET_ROW_DATOS_FILIATORIOS = "SELECT * FROM CLI_DatosFiliatorios WHERE ClienteId = ?";
    private static final String SQL_REMOVE_DATOS_FILIATORIOS = "DELETE FROM CLI_DatosFiliatorios WHERE ClienteId = ?";
    private static final String SQL_GET_ROW_DOMICILIO = "SELECT d.* FROM CLI_Clientes c JOIN Domicilios d ON c.DomicilioId = d.Id WHERE c.Id = ?";
    private static final String SQL_GET_ROW_PROVINCIAS_OPERATIVAS = "SELECT Descripcion FROM ProvinciasOperativas";
    private static final String SQL_GET_ROW_DATOS_ANALITICOS = "SELECT * FROM CLI_DatosAnaliticos WHERE ClienteId = ?";
    private static final String SQL_DELETE_FROM_CLI_ADICIONALES = "DELETE CLI_Adicionales WHERE ClienteId = ? AND DNI = ?";
    private static final String SQL_DELETE_FROM_ALTA_ADICIONAL_TARJETA_CREDITO = "DELETE ALT_AltaAdicionalTarjetaCredito WHERE AdicionalTarjetaId = "
            + "(SELECT Id FROM CLI_Adicionales WHERE ClienteId = ? AND DNI = ?)";
    private static final String SQL_DELETE_FROM_ALTA_PERSONA = "DELETE ALT_AltasPersonas WHERE AdicionalTarjetaId = "
            + "(SELECT Id FROM CLI_Adicionales WHERE ClienteId = ? AND DNI = ?)";
    private static final int TIME_TO_WAIT_BETWEEN_QUERIES = 5;
    private static final String SQL_GET_ROW_RELACION_LABORAL = "SELECT RelacionLaboralId FROM CLI_DatosLaborales WHERE ClienteId = ?";
    private static final String SQL_GET_IN_PROCESS_ADDED_USER = "SELECT CUIL FROM CLI_Clientes WHERE AltaEnProceso = 1 AND ProcesoAltaExitoso = 1 AND EMail IS NOT NULL";
    private static final String SQL_GET_SACNOSIS_CALLS_BY_TODAY = "SELECT 1 FROM LOGGIN_SERVICIOS WHERE NOMBRE_OPERACION = ? AND NRO_DOCUMENTO = ? AND cast(FECHA as date) = cast(getDate() as date)";



    public static void cleanClientRegistrationForGivenCuil(Long cuil) {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SET_ALTA_CLIENTE)) {

            statement.setLong(1, cuil);
            statement.executeUpdate();

        } catch (SQLException e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
            throw new RuntimeException("Unable to run DB Query", e);
        }
    }

    public static String getResponseFromService(String nombreServicio, String cuil) {

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_GET_RESPONSE_SERVICIO_LOGGIN_SERVICIOS)) {

            statement.setString(1, nombreServicio);
            statement.setString(2, cuil);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return rs.getString("REPSONSE");
            }

        } catch (SQLException e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
            throw new RuntimeException("Unable to run DB Query", e);
        }
        return StringUtils.EMPTY;
    }

    public static String getResponseFromOperation(String operationName, String cuil) {

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection
                     .prepareStatement(SQL_GET_RESPONSE_LOGGIN_SERVICIOS_BY_OPERATION)) {

            statement.setMaxRows(1);
            statement.setString(1, operationName);
            statement.setString(2, cuil);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return rs.getString("REPSONSE");
            }
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
            throw new RuntimeException("Unable to run DB Query", e);
        }
        return StringUtils.EMPTY;
    }

    public static String getRequestFromOperation(String operationName, String cuil) {

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection
                     .prepareStatement(SQL_GET_REQUEST_LOGGIN_SERVICIOS_BY_OPERATION)) {

            statement.setString(1, operationName);
            statement.setString(2, cuil);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return rs.getString("REQUEST");
            }
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
            throw new RuntimeException("Unable to run DB Query", e);
        }
        return StringUtils.EMPTY;
    }

    public static List<String> getRequestsFromOperation(String operationName, String cuil, int rows) {
        List<String> requests = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection
                     .prepareStatement(SQL_GET_REQUEST_LOGGIN_SERVICIOS_BY_OPERATION)) {

            statement.setString(1, operationName);
            statement.setString(2, cuil);
            ResultSet rs = statement.executeQuery();
            int i = 0;
            if (rs.next() && i < rows) {
                requests.add(rs.getString("REQUEST"));
                i++;
            }
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
            throw new RuntimeException("Unable to run DB Query", e);
        }
        return requests;
    }

    public static List<String> getResponsesFromOperation(String operationName, String cuil, int rows) {
        List<String> responses = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection
                     .prepareStatement(SQL_GET_RESPONSE_LOGGIN_SERVICIOS_BY_OPERATION)) {

            statement.setMaxRows(1);
            statement.setString(1, operationName);
            statement.setString(2, cuil);
            ResultSet rs = statement.executeQuery();
            int i = 0;
            if (rs.next() && i < rows) {
                responses.add(rs.getString("REPSONSE"));
                i++;
            }
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
            throw new RuntimeException("Unable to run DB Query", e);
        }
        return responses;
    }

    public static void removeConyuge(Integer idCliente) {

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_REMOVE_CONYUGE)) {

            statement.setInt(1, idCliente);
            statement.executeUpdate();

        } catch (SQLException e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
            throw new RuntimeException("Unable to run DB Query", e);
        }
    }

    public static Persona getConyugeRow(Integer clienteId) {

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_GET_ROW_CONYUGUE)) {

            statement.setInt(1, clienteId);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                Persona per = new Persona();
                per.setNombre(rs.getString("Nombre"));
                per.setApellido(rs.getString("Apellido"));
                per.setDni(rs.getLong("DNI"));
                per.setPaisNAcimiento(rs.getString("PaisNacimiento_Descripcion"));
                return per;
            }

        } catch (SQLException e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
            throw new RuntimeException("Unable to run DB Query", e);
        }
        return null;
    }

    public static DatoFiliatorio getDatosFiliatorios(Integer clienteId) {

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_GET_ROW_DATOS_FILIATORIOS)) {

            statement.setInt(1, clienteId);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                DatoFiliatorio datos = new DatoFiliatorio();
                datos.setDobleCiudadania(rs.getInt("DobleCiudadania"));
                datos.setDocumentoDobleCiudadania(rs.getString("DocumentoDobleCiudadania"));
                datos.setEstadoCivil(rs.getInt("EstadoCivil"));
                datos.setFechaNacimiento(rs.getDate("FechaNacimiento"));
                datos.setLugarNacimiento(rs.getString("LugarNacimiento_Descripcion"));
                datos.setPaisDobleCiudadania(rs.getString("PaisDobleCiudadania_Descripcion"));
                datos.setResidente(rs.getInt("Residente"));
                datos.setPaisNacimiento(rs.getString("PaisNacimiento_Descripcion"));
                datos.setPaisResidencia(rs.getString("PaisResidencia_Descripcion"));
                return datos;
            }

        } catch (SQLException e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
            throw new RuntimeException("Unable to run DB Query", e);
        }
        return null;
    }

    public static void removeDatosFiliatorios(Integer idCliente) {

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_REMOVE_DATOS_FILIATORIOS)) {

            statement.setInt(1, idCliente);
            statement.executeUpdate();

        } catch (SQLException e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
            throw new RuntimeException("Unable to run DB Query", e);
        }
    }

    public static Domicilio getDomicilio(Integer clienteId) {

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_GET_ROW_DOMICILIO)) {

            statement.setInt(1, clienteId);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                Domicilio domicilio = new Domicilio();
                domicilio.setId(rs.getInt("Id"));
                domicilio.setCalle(rs.getString("Calle"));
                domicilio.setAltura(rs.getInt("Altura"));
                domicilio.setCodigoPostal(rs.getInt("CodigoPostal"));
                domicilio.setPiso(rs.getInt("Piso"));
                domicilio.setDepartamento(rs.getString("Departamento"));
                domicilio.setProvincia(rs.getString("Provincia_Descripcion"));
                domicilio.setLocalidad(rs.getString("Localidad_Descripcion"));
                return domicilio;
            }

        } catch (SQLException e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
            throw new RuntimeException("Unable to run DB Query", e);
        }
        return null;
    }

    public static List<String> getOperativeProvinces() {

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_GET_ROW_PROVINCIAS_OPERATIVAS)) {

            ResultSet rs = statement.executeQuery();

            List<String> result = new ArrayList<>();
            while (rs.next()) {
                result.add(rs.getString("Descripcion"));
            }
            return result;

        } catch (SQLException e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
            throw new RuntimeException("Unable to run DB Query", e);
        }
    }

    public static DatoAnalitico getRowFromDatoAnalitico(Integer clienteId) {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_GET_ROW_DATOS_ANALITICOS)) {

            statement.setInt(1, clienteId);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                DatoAnalitico datoAnalitico = new DatoAnalitico();
                datoAnalitico.setRentaTitular(rs.getInt("RentaTitular"));
                datoAnalitico.setOfertaCrediticiaPoliticaID(rs.getInt("OfertaCrediticiaPoliticaID"));
                datoAnalitico.setCategoriaNSE(rs.getString("CategoriaNSE"));
                datoAnalitico.setCompromisoOtrosBancos(rs.getBigDecimal("CompromisoOtrosBancos"));
                datoAnalitico.setActividadID(rs.getInt("ActividadID"));
                datoAnalitico.setEdad(rs.getInt("Edad"));
                datoAnalitico.setProvinciaId(rs.getInt("ProvinciaId"));

                return datoAnalitico;
            }

        } catch (SQLException e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
            throw new RuntimeException("Unable to run DB Query", e);
        }
        return null;
    }

    private static void deleteAditionalClient(Integer clientId, String dni) {

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_DELETE_FROM_CLI_ADICIONALES)) {

            statement.setInt(1, clientId);
            statement.setString(2, dni);
            statement.executeUpdate();

        } catch (SQLException e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
            throw new RuntimeException("Unable to run DB Query", e);
        }
    }

    private static void deleteAditionalCreditCardRegistration(Integer clientId, String dni) {

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_DELETE_FROM_ALTA_ADICIONAL_TARJETA_CREDITO)) {

            statement.setInt(1, clientId);
            statement.setString(2, dni);
            statement.executeUpdate();

        } catch (SQLException e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
            throw new RuntimeException("Unable to run DB Query", e);
        }
    }

    private static void deleteClientRegistration(Integer clientId, String dni) {

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_DELETE_FROM_ALTA_PERSONA)) {

            statement.setInt(1, clientId);
            statement.setString(2, dni);
            statement.executeUpdate();

        } catch (SQLException e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
            throw new RuntimeException("Unable to run DB Query", e);
        }
    }

    public static void cleanDataForAditionalRegistration(Integer clientId, String dni) {
        WebDriverUtils webDriverUtils = new WebDriverUtils();
        deleteAditionalCreditCardRegistration(clientId, dni);
        webDriverUtils.waitSeconds(TIME_TO_WAIT_BETWEEN_QUERIES);
        deleteClientRegistration(clientId, dni);
        webDriverUtils.waitSeconds(TIME_TO_WAIT_BETWEEN_QUERIES);
        deleteAditionalClient(clientId, dni);
    }

    public static Integer getRowFromRelacionLaboral(Integer clienteId) {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_GET_ROW_RELACION_LABORAL)) {

            statement.setInt(1, clienteId);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {

                return rs.getInt("RelacionLaboralId");
            }

        } catch (SQLException e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
            throw new RuntimeException("Unable to run DB Query", e);
        }
        return null;
    }

    public static Long getClientCuilFromAddedUserProcess() {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_GET_IN_PROCESS_ADDED_USER)) {

            statement.setMaxRows(1);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return rs.getLong("CUIL");
            }

        } catch (SQLException e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
            throw new RuntimeException("Unable to run DB Query", e);
        }
        return null;
    }

    public static boolean validateIfSacnosisExecuted(String operation,String cuil) {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_GET_SACNOSIS_CALLS_BY_TODAY)) {

            statement.setString(1, operation);
            statement.setString(2, cuil);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {

                return true;
            }

        } catch (SQLException e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
            throw new RuntimeException("Unable to run DB Query", e);
        }
        return false;
    }
    
    public static Integer getCliIdFromCuil(String cuil) {

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_GET_CLI_ID_FROM_CUIL)) {

            statement.setLong(1, Long.parseLong(cuil));

            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getInt("Id");
            }

        } catch (SQLException e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
            throw new RuntimeException("Unable to run DB Query", e);
        }
        return null;
    }
}
