# SparrowScheduler
This is simple time scheduler.
1. Hourly
``` java
Scheduler.scheduleHourly(() -> {
            System.out.println("12.sec of every per hour!");
        }, 12);
```
2. Daily
``` java
Scheduler.scheduleDaily(() -> {
            System.out.println("15.sec of every per day!");
        }, 15);
```
3. Minutely
``` java
Scheduler.scheduleMinutely(() -> {
            System.out.println("per min!");
        }, 0);
```
4. XSecond
``` java
        Scheduler.schedulePerXSecond(new Runnable() {
            @Override
            public void run() {
                System.out.println("per 10 sec. Initial wait is 20 sec!");
            }
        }, 10, 20);
```
