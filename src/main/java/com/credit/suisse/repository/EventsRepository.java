package com.credit.suisse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.credit.suisse.models.EventEntity;

@Repository
public interface EventsRepository extends JpaRepository<EventEntity, String> {

}
