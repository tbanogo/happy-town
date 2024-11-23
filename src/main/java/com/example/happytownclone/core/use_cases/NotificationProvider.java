package com.example.happytownclone.core.use_cases;

import java.util.Map;

public interface NotificationProvider {

    void notifier(String to, String subject, String path, Map<String, String> template) throws NotificationException;

}
