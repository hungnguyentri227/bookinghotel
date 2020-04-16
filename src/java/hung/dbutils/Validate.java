/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hung.dbutils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author hungn
 */
public class Validate {

    public static boolean check2Date(String date1, String date2) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date now = new Date();
        Date d1 = formatter.parse(date1.trim());
        Date d2 = formatter.parse(date2.trim());

        if (d1.after(now) && d2.after(now) && d1.before(d2)) {
            return true;
        } else {
            return false;
        }
    }

    public static long daysBetween(Date d1, Date d2) {
        long diff = (d2.getTime() - d1.getTime()) / 86400000;
        return diff;
    }

    public static long countDate(String date1, String date2) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date d1 = formatter.parse(date1.trim());
        Date d2 = formatter.parse(date2.trim());

        long days = Validate.daysBetween(d1, d2);
        return days;
    }

    public static String dateNow() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date now = new Date();
        String dateString = formatter.format(now);
        return dateString;
    }

    

}
