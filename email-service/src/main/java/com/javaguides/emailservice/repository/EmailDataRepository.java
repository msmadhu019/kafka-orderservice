package com.javaguides.emailservice.repository;

import com.javaguides.emailservice.entity.EmailData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailDataRepository extends JpaRepository<EmailData, Long> {
}
