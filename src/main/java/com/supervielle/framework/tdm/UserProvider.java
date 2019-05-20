package com.supervielle.framework.tdm;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import com.supervielle.framework.utils.AutomationProperties;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

public class UserProvider {

    public static final String FILE_PATH = AutomationProperties.getString("environment").replaceAll(" ", "")
            + ".prospect.xlsx";

    private static String[] userData;

    private static int amount = 0;

    private static void setAmount() {
        int i = 0;
        Workbook workbook = null;
        try {
            workbook = WorkbookFactory.create(new File(FILE_PATH));
        } catch (IOException | InvalidFormatException e) {
            throw new RuntimeException("Could not access environment data");
        }
        Sheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.rowIterator();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                i++;
            }
        }
        amount = i;
    }

    private static void setData() {
        setAmount();
        userData = new String[amount];
        int i = 0;
        Workbook workbook = null;
        try {
            workbook = WorkbookFactory.create(new File(FILE_PATH));
        } catch (IOException | InvalidFormatException e) {
            throw new RuntimeException("Could not access environment data");
        }
        Sheet sheet = workbook.getSheetAt(0);
        DataFormatter dataFormatter = new DataFormatter();
        Iterator<Row> rowIterator = sheet.rowIterator();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                userData[i] = dataFormatter.formatCellValue(cell);
                i++;
            }
        }
    }

    public static String getCuil(String description) {
        setData();
        for (int i = 0; i < amount; i++) {
            if (userData[i].contains(description)) {
                return userData[i + 1];
            }
        }
        return "00000000000";
    }

    public static String getNombre(String description) {
        setData();
        for (int i = 0; i < amount; i++) {
            if (userData[i].contains(description)) {
                return userData[i + 4];
            }
        }
        return "nombre";
    }

    public static String getApellido(String description) {
        setData();
        for (int i = 0; i < amount; i++) {
            if (userData[i].contains(description)) {
                return userData[i + 5];
            }
        }
        return "Apellido";
    }

    public static String getGenero(String description) {
        setData();
        for (int i = 0; i < amount; i++) {
            if (userData[i].contains(description)) {
                return userData[i + 6];
            }
        }
        return "F";
    }

    public static String getEmail(String description) {
        setData();
        for (int i = 0; i < amount; i++) {
            if (userData[i].contains(description)) {
                return userData[i + 7];
            }
        }
        return "algo@algo.com";
    }

    public static String getCodArea(String description) {
        setData();
        for (int i = 0; i < amount; i++) {
            if (userData[i].contains(description)) {
                return userData[i + 8];
            }
        }
        return "000";
    }

    public static String getTelefono(String description) {
        setData();
        for (int i = 0; i < amount; i++) {
            if (userData[i].contains(description)) {
                return userData[i + 9];
            }
        }
        return "0000000";
    }

    public static String getPaisNacimiento(String description) {
        setData();
        for (int i = 0; i < amount; i++) {
            if (userData[i].contains(description)) {
                return userData[i + 10];
            }
        }
        return "ARGENTINA, REPUBLICA";
    }

    public static String getNivelEstudio(String description) {
        setData();
        for (int i = 0; i < amount; i++) {
            if (userData[i].contains(description)) {
                return userData[i + 11];
            }
        }
        return "Terciario";
    }

    public static String getCodigoPostal(String description) {
        setData();
        for (int i = 0; i < amount; i++) {
            if (userData[i].contains(description)) {
                return userData[i + 12];
            }
        }
        return "1431";
    }

    public static String getDireccion(String description) {
        setData();
        for (int i = 0; i < amount; i++) {
            if (userData[i].contains(description)) {
                return userData[i + 13];
            }
        }
        return "Av. Francisco Beiró";
    }

    public static String getNumeroCalle(String description) {
        setData();
        for (int i = 0; i < amount; i++) {
            if (userData[i].contains(description)) {
                return userData[i + 14];
            }
        }
        return "2170";
    }

    public static String getLugarNacimiento(String description) {
        setData();
        for (int i = 0; i < amount; i++) {
            if (userData[i].contains(description)) {
                return userData[i + 15];
            }
        }
        return "CAPITAL FEDERAL";
    }

    public static String getPreguntas(String description) {
        setData();
        for (int i = 0; i < amount; i++) {
            if (userData[i].contains(description)) {
                return userData[i + 16];
            }
        }
        return "preguntas";
    }

    public static String getOferta(String description) {
        setData();
        for (int i = 0; i < amount; i++) {
            if (userData[i].contains(description)) {
                return userData[i + 17];
            }
        }
        return "Voucher";
    }

    public static String getLocalidad(String description) {
        setData();
        for (int i = 0; i < amount; i++) {
            if (userData[i].contains(description)) {
                return userData[i + 18];
            }
        }
        return null;
    }

    public static String getProvincia(String description) {
        setData();
        for (int i = 0; i < amount; i++) {
            if (userData[i].contains(description)) {
                return userData[i + 19];
            }
        }
        return null;
    }

    public static String getDNIFromCuil(String prospect) {
        String cuil = UserProvider.getCuil(prospect);
        return cuil.substring(2, cuil.length() - 1);
    }

    public static String getVinculo(String description) {
        setData();
        for (int i = 0; i < amount; i++) {
            if (userData[i].contains(description)) {
                return userData[i + 20];
            }
        }
        return null;
    }

    public static String getFechaNac(String description) {
        setData();
        for (int i = 0; i < amount; i++) {
            if (userData[i].contains(description)) {
                return userData[i + 21];
            }
        }
        return null;
    }

    public static String getUser(String description) {
        setData();
        for (int i = 0; i < amount; i++) {
            if (userData[i].contains(description)) {
                return userData[i + 2];
            }
        }
        return null;
    }

    public static String getPassword(String description) {
        setData();
        for (int i = 0; i < amount; i++) {
            if (userData[i].contains(description)) {
                return userData[i + 3];
            }
        }
        return null;
    }
}
