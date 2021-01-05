package ru.sber.school.game.bank;

public class BankStarter {
    public static void main(String[] args) {
        Client client1 = new Client(30000, RequestType.CREDIT);
        Client client2 = new Client(20000, RequestType.PAYMENT);
        Client client3 = new Client(5000, RequestType.CREDIT);
        Client client4 = new Client(15000, RequestType.PAYMENT);
        Client client5 = new Client(2000, RequestType.PAYMENT);

        RequestHandler requestHandler1 = new RequestHandler();
        RequestHandler requestHandler2 = new RequestHandler();
    }
}
