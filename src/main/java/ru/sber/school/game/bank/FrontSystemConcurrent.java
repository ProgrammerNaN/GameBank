package ru.sber.school.game.bank;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class FrontSystemConcurrent implements FrontSystem {

    private static FrontSystemConcurrent instance;

    private static int MAX_COUNT_REQUESTS = 2;
    private BlockingQueue<Request> requests;

    private FrontSystemConcurrent() {
        requests = new LinkedBlockingQueue<>(MAX_COUNT_REQUESTS);
    }

    public static FrontSystemConcurrent getInstance() {
        if (instance == null) {
            synchronized (FrontSystemConcurrent.class) {
                if (instance == null) {
                    instance = new FrontSystemConcurrent();
                }
            }
        }

        return instance;
    }

    @Override
    public void pushRequest(Request request) throws InterruptedException {
        requests.put(request);
    }

    @Override
    public Request pullRequest() throws InterruptedException {
        return requests.take();
    }
}
