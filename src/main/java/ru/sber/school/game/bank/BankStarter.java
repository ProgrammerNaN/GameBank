package ru.sber.school.game.bank;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class BankStarter {

    private static final int CLIENTS_COUNT = 5;
    private static final int HANDLERS_COUNT = 2;

    public static void main(String[] args) {
        ExecutorService clientService = Executors.newFixedThreadPool(CLIENTS_COUNT);
        ExecutorService requestHandlerService = Executors.newFixedThreadPool(HANDLERS_COUNT, new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread t = Executors.defaultThreadFactory().newThread(r);
                t.setDaemon(true);
                return t;
            }
        });

        Client client1 = new Client(30000, RequestType.CREDIT);
        Client client2 = new Client(20000, RequestType.PAYMENT);
        Client client3 = new Client(5000, RequestType.CREDIT);
        Client client4 = new Client(15000, RequestType.PAYMENT);
        Client client5 = new Client(2000, RequestType.PAYMENT);
        clientService.submit(client1);
        clientService.submit(client2);
        clientService.submit(client3);
        clientService.submit(client4);
        clientService.submit(client5);

        RequestHandler requestHandler1 = new RequestHandler();
        RequestHandler requestHandler2 = new RequestHandler();

        requestHandlerService.execute(requestHandler1);
        requestHandlerService.execute(requestHandler2);

        clientService.shutdown();
        requestHandlerService.shutdown();
    }
}
