package ru.sber.school.game.bank;

public class Client extends Thread {

    private String name;
    private Request request;
    private FrontSystem frontSystem;

    public Client(int amount, RequestType requestType) {
        this.name = "Клиент №" + this.getId();
        this.request = new Request(this.name, amount, requestType);
        frontSystem = FrontSystemSimple.getInstance();
        start();
    }

    @Override
    public void run() {
        try {
            System.out.println(request.toString() + " отправлена в банк");
            frontSystem.pushRequest(request);
            while (!this.request.isProcessed()) {
                Thread.sleep(1);
            };
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
