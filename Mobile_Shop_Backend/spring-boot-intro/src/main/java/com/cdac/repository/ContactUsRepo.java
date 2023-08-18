package com.cdac.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cdac.entity.Contact;

public interface ContactUsRepo extends JpaRepository<Contact, Integer> {

}
