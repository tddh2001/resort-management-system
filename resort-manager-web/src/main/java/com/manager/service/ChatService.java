package com.manager.service;

import com.manager.model.Chat;

import java.util.List;

public interface ChatService {
    List<Chat> findAll();
    void save(Chat chat);
}
