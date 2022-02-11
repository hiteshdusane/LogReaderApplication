package com.credit.suisse.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.credit.suisse.models.EventEntity;

@SpringBootTest
class EventsRepositoryTests {

	@Autowired
	private EventsRepository eventsRepository;

	private EventEntity eventEntity;

	@Test
	void findAllEventsTest() {
		this.eventEntity = new EventEntity();
		this.eventEntity.setId("abc");
		this.eventEntity.setAlert(true);
		this.eventEntity.setEventDuration(5L);
		this.eventEntity.setHost("pqr");
		this.eventEntity.setType("APP_LOG");
		eventsRepository.save(this.eventEntity);
		assertThat(eventsRepository.findAll()).isInstanceOf(List.class);
	}

}
