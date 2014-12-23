/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */
/**
 * Q.
 * 127ページの5.5節「ゾーン付き時刻」の初めで説明した問題を解くプログラムを書きなさい。
 * そのプログラムでは、異なるタイムゾーンにある約束の集まりを読み込んで、ローカル時刻で1時間前に
 * 約束があることをユーザに通知するようにしなさい。
 */

package jp.co.namihira.java8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        new Thread(() -> {
            registerSchedule();
        }).start();

        new Thread(() -> {
            notifySchedule();
        }).start();
    }

    // e.g. 2014-12-25 11:00 Asia/Tokyo
    // e.g. 2014-12-25 10:00 Asia/Hong_Kong
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm VV");

    private static void registerSchedule(){
        String input = "";
        while(true){
            System.out.println(">Type new Schedule[e.g. 2014-12-23 21:00 Asia/Tokyo] or \"exit\" : ");
            input = getConsoleString();
            if (input.equals("exit")) {
                System.exit(0);
            }
            ZonedDateTime newSchedule = null;
            try {
                newSchedule = ZonedDateTime.parse(input, formatter);
            } catch (DateTimeParseException e) {
                System.out.println(">the input text cannot be parsed. Type again.");
                continue;
            }
            Schedule.addSchedule(newSchedule);
            System.out.println("Schedule List : ");
            Schedule.getSchedule().stream().forEachOrdered(System.out::println);
        }
    }

    private static void notifySchedule(){
        while(true){
            Set<ZonedDateTime> schedules = Schedule.getSchedule();
            final Set<ZonedDateTime> result = DateTimeUtils.filter(schedules, ZonedDateTime.now(), 60);
            if (result.size() != 0) {
                System.out.println("#check the schedule ! : ");
                result.stream().forEachOrdered(System.out::println);
            }
            try {
                Thread.sleep(20_000);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static String getConsoleString(){
        final InputStreamReader isr = new InputStreamReader(System.in);
        final BufferedReader br = new BufferedReader(isr);
        String buf = "";
        try {
            buf = br.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return buf;
    }

}
