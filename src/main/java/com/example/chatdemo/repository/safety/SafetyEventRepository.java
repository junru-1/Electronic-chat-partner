package com.example.chatdemo.repository.safety;

import com.example.chatdemo.domain.safety.SafetyEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SafetyEventRepository extends JpaRepository<SafetyEvent, Long> {
}
