package pers.benj;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        System.out.println(Date.from(LocalDateTime.parse("2020-01-01 00:00:00", dateTimeFormatter)
                        .atZone(ZoneId.systemDefault()).toInstant()));
    }
}
