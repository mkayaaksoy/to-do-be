package com.todos.todomanagement.entity;

public enum TodoStatus {
    ACTIVE(0),
    DONE(1),
    DELETED(2);

    private final Integer value;

    TodoStatus(Integer val) {
        this.value = val;
    }

    public int getValue() {
        return value;
    }
}
