package com.supervielle.framework.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeUtils {

    public static boolean dateHasValidFormat(String value) {
        try {
            new SimpleDateFormat("dd/mm/yyyy").parse(value);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    public static String parseDateToGivenFormat(String date, String actualFormat, String desiredFormat) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat(actualFormat);
        SimpleDateFormat formatToReturn = new SimpleDateFormat(actualFormat);
        Date dateToFormat = format.parse(date);
        return formatToReturn.format(dateToFormat);
    }
}
