package ru.sber.school.game.bank;

import java.util.LinkedList;
import java.util.List;

public class FrontSystemSimple implements FrontSystem {
    private static volatile FrontSystem instance;

    private static final int MAX_COUNT_REQUESTS = 2;

    private List<Request> requests;

    private Object listWriteBlock = new Object();
    private Object listReadBlock = new Object();

    private FrontSystemSimple() {
        this.requests = new LinkedList<>();
    }

    public static FrontSystem getInstance() {
        if (instance == null) {
            synchronized (FrontSystemSimple.class) {
                if (instance == null) {
                    instance = new FrontSystemSimple();
                }
            }
        }

        return instance;
    }

    @Override
    public void pushRequest(Request request) throws InterruptedException {
        synchronized (this) {
            while (requests.size() >= MAX_COUNT_REQUESTS) {
                wait();
            }

            requests.add(request);
            notifyAll();
        }
    }

    @Override
    public Request pullRequest() throws InterruptedException {
        Request request = null;

        synchronized(this) {
            while (requests.size() == 0) {
                wait();
            }
            request = requests.get(requests.size() - 1);
            requests.remove(request);
            notifyAll();
        }

        return request;
    }
}
