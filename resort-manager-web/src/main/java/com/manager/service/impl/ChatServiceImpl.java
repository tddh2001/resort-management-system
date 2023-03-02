package com.manager.service.impl;

import com.manager.model.Chat;
import com.manager.repository.ChatRepository;
import com.manager.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ChatServiceImpl implements ChatService {
    @Autowired
    ChatRepository chatRepository;

    @Override
    public List<Chat> findAll() {
        return chatRepository.findAll();
    }

    @Override
    public void save(Chat chat) {
        chatRepository.save(chat);
    }
}
