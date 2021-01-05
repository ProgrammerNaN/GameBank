package ru.sber.school.game.bank;

public class LimitBankBalanceException extends Exception {
    public LimitBankBalanceException(String message) {
        super(message);
    }
}
