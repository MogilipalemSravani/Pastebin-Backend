package com.example.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Paste;

public interface PasteRepository extends JpaRepository<Paste, String> {

}
