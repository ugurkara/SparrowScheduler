/*
 * Copyright (C) 2019 KR Endüstriyel BT
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.sparrow.core;

import java.util.Calendar;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author ugurkara
 */
public class Scheduler {

    private final static Logger log = LogManager.getLogger(Scheduler.class.getName());

    private final static ScheduledExecutorService DEFAULT_EXECUTOR = Executors.newSingleThreadScheduledExecutor();

    private static long delayMinute(long delay) {

        int current = Calendar.getInstance().get(Calendar.SECOND);

        if (current <= delay) {
            return delay - current;
        } else {
            return 60 + delay - current;
        }
    }

    private static long delayHour(long delay) {

        int currentSecond = Calendar.getInstance().get(Calendar.SECOND);
        int currentMinute = Calendar.getInstance().get(Calendar.MINUTE);
        long current = currentMinute * 60 + currentSecond;

        if (current <= delay) {
            return delay - current;
        } else {
            return 3600 + delay - current;
        }

    }

    private static long delayDay(long delay) {

        int currentSecond = Calendar.getInstance().get(Calendar.SECOND);
        int currentMinute = Calendar.getInstance().get(Calendar.MINUTE);
        int currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        long current = currentHour * 3600 + currentMinute * 60 + currentSecond;

        if (current <= delay) {
            return delay - current;
        } else {
            return 86400 + delay - current;
        }

    }

    private static String dailyFormat(long seconds) {
        return String.format("%02d:%02d:%02d", seconds / 3600, (seconds % 3600) / 60, seconds % 60);
    }

    private static String hourlyFormat(long seconds) {
        return String.format("xx:%02d:%02d", (seconds % 3600) / 60, seconds % 60);
    }

    private static String minutelyFormat(long seconds) {
        return String.format("xx:xx:%02d", seconds % 60);
    }


    /*
    *
    *delay : second
     */
    public static ScheduledFuture<?> scheduleMinutely(Runnable runnable, long start) {
        long calculatedDelay = delayMinute(start);
        log.info(String.format("Minutely Scheduler will run at %s after %s", minutelyFormat(start), dailyFormat(calculatedDelay)));
        return DEFAULT_EXECUTOR.scheduleAtFixedRate(runnable, calculatedDelay, 60, TimeUnit.SECONDS);
    }

    /*
    *
    *delay : second
     */
    public static ScheduledFuture<?> scheduleHourly(Runnable runnable, long start) {
        long calculatedDelay = delayHour(start);
        log.info(String.format("Hourly Scheduler will run at %s after %s", hourlyFormat(start), dailyFormat(calculatedDelay)));

        return getDefaultExecutor().scheduleAtFixedRate(runnable, calculatedDelay, 3600, TimeUnit.SECONDS);
    }

    /*
    *
    *delay : second
     */
    public static ScheduledFuture<?> scheduleDaily(Runnable runnable, long start) {
        long calculatedDelay = delayDay(start);
        log.info(String.format("Daily Scheduler will run at %s after %s", dailyFormat(start), dailyFormat(calculatedDelay)));
        return getDefaultExecutor().scheduleAtFixedRate(runnable, calculatedDelay, 24 * 60 * 60, TimeUnit.SECONDS);
    }

    /*
    *
    *delay : second
     */
    public static ScheduledFuture<?> schedulePerXSecond(Runnable runnable, long delay, long period) {
        log.info(String.format("Second per Scheduler scheduled as period %dsn delay is %dsn", period, delay));
        return getDefaultExecutor().scheduleAtFixedRate(runnable, delay, period, TimeUnit.SECONDS);
    }

    private static ScheduledExecutorService getDefaultExecutor() {

        return DEFAULT_EXECUTOR;
    }

}
