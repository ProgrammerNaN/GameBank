package ru.sber.school.game.bank;

import java.util.Objects;

public class Request {
    private String clientName;
    private int amount;
    private RequestType requestType;
    private boolean processed;

    public Request(String clientName, int amount, RequestType requestType) {
        this.clientName = clientName;
        this.amount = amount;
        this.requestType = requestType;
        this.processed = false;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public RequestType getRequestType() {
        return requestType;
    }

    public void setRequestType(RequestType requestType) {
        this.requestType = requestType;
    }

    public boolean isProcessed() {
        return processed;
    }

    public void setProcessed(boolean processed) {
        this.processed = processed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request request = (Request) o;
        return amount == request.amount &&
                clientName.equals(request.clientName) &&
                requestType == request.requestType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientName, amount, requestType);
    }

    @Override
    public String toString() {
        return "Заявка{" +
                "clientName='" + clientName + '\'' +
                ", amount=" + amount +
                ", requestType=" + requestType +
                '}';
    }
}
