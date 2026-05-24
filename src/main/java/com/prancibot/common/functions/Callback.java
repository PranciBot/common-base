package com.prancibot.common.functions;

@FunctionalInterface
public interface Callback<T> {
    void execute(T data);
}
