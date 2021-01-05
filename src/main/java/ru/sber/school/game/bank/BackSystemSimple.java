package ru.sber.school.game.bank;

public class BackSystemSimple implements BackSystem {

    private static volatile BackSystem instance;
    private volatile long bankBalance;

    private BackSystemSimple() {
        this.bankBalance = 0;
    }

    public static BackSystem getInstance() {
        if (instance == null) {
            synchronized (BackSystemSimple.class) {
                if (instance == null) {
                    instance = new BackSystemSimple();
                }
            }
        }

        return instance;
    }

    @Override
    public synchronized void handleRequest(RequestHandler requestHandler) {
        Request request = requestHandler.getRequest();
        try {
            switch (request.getRequestType()) {
                case CREDIT:
                    credit(request.getAmount());
                    break;
                case PAYMENT:
                    repay(request.getAmount());
                    break;
            }
            System.out.println("Бэк-система: " + request.toString()
                    + " УСПЕШНО ВЫПОЛНЕНА. Получена от " + requestHandler.getHandlerName()
                    + ". Баланс банка: " + bankBalance);
        } catch (LimitBankBalanceException e) {
            System.out.println("Бэк-система: " + request.toString()
                    + " НЕ ВЫПОЛНЕНА. Получена от " + requestHandler.getHandlerName()
                    + ". " + e.getMessage()
                    + " Баланс банка: " + bankBalance);
        }
        request.setProcessed(true);
    }

    private synchronized void repay(int amount) {
        bankBalance += amount;
    }

    private synchronized void credit(int amount) throws LimitBankBalanceException {
        if (amount <= bankBalance) {
            bankBalance -= amount;
        } else {
            throw new LimitBankBalanceException("Сумма больше баланса банка.");
        }
    }
}
