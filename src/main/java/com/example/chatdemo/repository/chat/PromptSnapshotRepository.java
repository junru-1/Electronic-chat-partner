package com.example.chatdemo.repository.chat;

import com.example.chatdemo.domain.chat.PromptSnapshot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PromptSnapshotRepository extends JpaRepository<PromptSnapshot, Long> {
}
