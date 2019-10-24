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
package com.sparrow.core.test;

import com.sparrow.core.Scheduler;

/**
 *
 * @author ugurkara
 */
public class SchedulerTest {
    public static void main(String[] args) {
        
        Scheduler.scheduleHourly(() -> {
            System.out.println("12.sec of every per hour!");
        }, 12);
        
        Scheduler.scheduleDaily(() -> {
            System.out.println("15.sec of every per day!");
        }, 15);
        
        Scheduler.scheduleMinutely(() -> {
            System.out.println("per min!");
        }, 0);
        
        Scheduler.schedulePerXSecond(new Runnable() {
            @Override
            public void run() {
                System.out.println("per 20 sec. Initial wait is 20 sec!");
            }
        }, 10, 20);
        
        
    }
}
