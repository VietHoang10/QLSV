package thread;

import function.Export;

import java.util.concurrent.atomic.AtomicBoolean;

public class ExportRunable implements Runnable {

    private final AtomicBoolean running = new AtomicBoolean(true);


    public void stop() {
        running.set(false);
    }

    @Override
    public void run() {
        while (running.get()) {
            Export.exportData();
            try {
                Thread.sleep(60 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
