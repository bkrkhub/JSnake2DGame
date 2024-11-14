package dev.bkrk;

public class Time {

    public static double timeStarted = System.nanoTime();

    public static double getTime() {
        return (System.nanoTime() -timeStarted) * 1E-9;
        //E-9 is used to convert nanoseconds to seconds.
        // 1E-9 means 0.000000001 (1 divided by 1 billion). So, it converts nanoseconds to seconds.
    }
}
