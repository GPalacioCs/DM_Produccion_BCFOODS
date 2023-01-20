package com.dosrobles.produccion.utils;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Utils {

    public static String generarConsecutivo(String cons) {
        for (int i = 0; i < cons.length(); i++) {
            String substring = cons.substring(i);
            if (StringUtils.isNumeric(substring)) {
                BigInteger num = new BigInteger(substring).add(BigInteger.ONE);
                return cons.substring(0, i) + String.format("%0" + substring.length() + "d", num);
            }
        }
        return null;
    }

    public static String stripMask(String value) {
        if (value == null) {
            return null;
        }
        int i;
        for (i = 0; i < value.length(); i++) {
            if (i < value.length() - 1 && value.charAt(i + 1) == '_') {
                if (StringUtils.isNumeric(String.valueOf(value.charAt(i)))) {
                    i++;
                }
                break;
            }
        }
        if (i == 1 && !StringUtils.isNumeric(value.subSequence(0, i))) {
            i--;
        }
        return value.substring(0, i);
    }

    public static Date getFirstDayOfMonth(Date date) {
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().withDayOfMonth(1);
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
    
    /**
     * Quita el la hora y deja la fecha
     * @param date la fecha con la hora
     * @return la fecha sin la hora
     */
    public static Date stripTime(Date date) {
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
    
    public static List<Integer> getDiasMes(int year,int month) {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= YearMonth.of(year,month).lengthOfMonth(); i++) {
            list.add(i);
        }
        return list;
    }

    public static String getSeparador(String cuenta) {
        String sep = "";
        for (int i = 0; i<cuenta.length();i++) {
            if(!StringUtils.isNumeric(String.valueOf(cuenta.charAt(i))))
                sep = String.valueOf((cuenta.charAt(i)));
        }
        
        return sep == null ? null : sep;
    }

    public static String trimCuenta(String cuenta) {
        String sep = getSeparador(cuenta);
        String[] arrayCuenta = cuenta.split(Pattern.quote(sep));

        int i = 0;
        for (String c : arrayCuenta) {
            if (Integer.valueOf(c) == 0) {
                break;
            } else {
                i++;
            }
        }        
        StringJoiner joiner = new StringJoiner(StringEscapeUtils.unescapeJava(sep));
        for(int j = 0; j < i; j++) {
            joiner.add(arrayCuenta[j]);
        }
        return joiner.toString();
    }
    
    public static int getNivelCuenta(String cuenta) {
        return trimCuenta(cuenta).split(Pattern.quote(getSeparador(cuenta))).length;
    }
    
    public static BigDecimal sumBigDecimalList(Collection<BigDecimal> bdList) {
        return bdList.stream().filter(bd -> bd != null)
        .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
    
    public static <T> BigDecimal sumBigDecimals(Collection<T> col, Function<T, BigDecimal> func) {
        return sumBigDecimals(col.stream(), func);
    }
    
    public static <T> BigDecimal sumBigDecimals(Stream<T> col, Function<T, BigDecimal> func) {
        BiFunction<Function<T, BigDecimal>, Stream<T>, BigDecimal> sum = (bdfunc, list) -> list.map(bdfunc).filter(bd -> bd!=null).reduce(BigDecimal.ZERO, BigDecimal::add);
        return sum.apply(func, col);
    }
    
    public static BigDecimal dol2loc(BigDecimal monto, BigDecimal tipoCambio) {
        return monto.multiply(tipoCambio).setScale(2, RoundingMode.HALF_EVEN);
    }
    
    public static BigDecimal loc2dol(BigDecimal monto, BigDecimal tipoCambio) {
        return monto.divide(tipoCambio, 2, RoundingMode.HALF_EVEN).setScale(2);
    }
    
    public static Date getDate(int year, int month, int day) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month-1, day);
        return cal.getTime();
    }
    
    public static LocalDateTime date2ldt(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    public static Date ldt2date(LocalDateTime ldt) {
        return Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
    }
}
