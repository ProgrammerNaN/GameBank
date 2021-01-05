package ru.sber.school.game.bank;

public interface FrontSystem {
    void pushRequest(Request request) throws InterruptedException;

    Request pullRequest() throws InterruptedException;
}
