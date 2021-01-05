package ru.sber.school.game.bank;

public class RequestHandler extends Thread {

    private String handlerName;
    private FrontSystem frontSystem;
    private BackSystem backSystem;
    private Request request;

    public RequestHandler() {
        this.handlerName = "Обработчик заявок №" + this.getId();
        this.frontSystem = FrontSystemConcurrent.getInstance();
        this.backSystem = BackSystemSimple.getInstance();
        this.setDaemon(true);
        //start();
    }

    @Override
    public void run() {
        try {
            while (true) {
                request = frontSystem.pullRequest();
                System.out.println("Получена заявка на обработку по клиенту - " + request.getClientName());
                backSystem.handleRequest(this);
                Thread.sleep(200);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getHandlerName() {
        return this.handlerName;
    }

    public Request getRequest() {
        return this.request;
    }
}
