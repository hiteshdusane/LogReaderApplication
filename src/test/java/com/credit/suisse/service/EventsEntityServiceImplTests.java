package com.credit.suisse.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.credit.suisse.models.EventEntity;
import com.credit.suisse.repository.EventsRepository;

@SpringBootTest
class EventsEntityServiceImplTests {

	@Autowired
	private EventsEntityService eventsEntityService;

	@Autowired
	private EventsRepository eventsRepository;

	private List<EventEntity> events = new ArrayList<>();
	private EventEntity eventEntity;

	@Test
	void saveEventsTest() {
		this.eventEntity = new EventEntity();
		this.eventEntity.setId("abc");
		this.eventEntity.setAlert(true);
		this.eventEntity.setEventDuration(5L);
		this.eventEntity.setHost("pqr");
		this.eventEntity.setType("APP_LOG");
		events.add(eventEntity);
		eventsEntityService.saveEvents(events);
		EventEntity ev = eventsRepository.findById(this.eventEntity.getId()).orElseGet(() -> new EventEntity());

		assertThat(ev.getId()).isEqualTo("abc");
	}

	@Test
	void getAllStoredEvents() {
		this.eventEntity = new EventEntity();
		this.eventEntity.setId("abc");
		this.eventEntity.setAlert(true);
		this.eventEntity.setEventDuration(5L);
		this.eventEntity.setHost("pqr");
		this.eventEntity.setType("APP_LOG");
		eventsRepository.save(this.eventEntity);
		assertThat(eventsEntityService.getAllEventsStored()).isInstanceOf(List.class);
	}

}
