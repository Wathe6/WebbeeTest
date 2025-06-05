package io.envoi;

import io.envoi.controller.LogController;

public class Main {
    public static void main(String[] args) {
        new LogController("input", "transactions_by_users").start();
    }
}