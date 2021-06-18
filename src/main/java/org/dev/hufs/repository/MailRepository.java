package org.dev.hufs.repository;

import org.dev.hufs.entity.Mail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MailRepository extends JpaRepository<Mail, Long> {
    Page<Mail> findByCategoryIs(String category, Pageable pageable);
}
