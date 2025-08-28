package com.tailorkz.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NotifierService {
    private static NotifierService instance;
    private final Map<EventEnum, List<EventListener>> listeners = new HashMap<>();

    private NotifierService() {}

    public static NotifierService getInstance() {
        if (instance == null) {
            instance = new NotifierService();
        }
        return instance;
    }

    public void subscribe(EventListener listener, EventEnum... events) {
        for (EventEnum event : events) {
            listeners.computeIfAbsent(event, k -> new ArrayList<>()).add(listener);
        }
    }

    public void notify(EventEnum event) {
        if (listeners.containsKey(event)) {
            listeners.get(event).forEach(listener -> listener.onEvent(event));
        }
    }
}
