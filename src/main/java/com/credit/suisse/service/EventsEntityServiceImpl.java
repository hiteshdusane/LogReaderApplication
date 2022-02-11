package com.credit.suisse.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.credit.suisse.models.EventEntity;
import com.credit.suisse.repository.EventsRepository;

@Service
public class EventsEntityServiceImpl implements EventsEntityService {

	@Autowired
	private EventsRepository eventsRepository;

	private static final Logger LOGGER = LoggerFactory.getLogger(EventsEntityServiceImpl.class);

	@Override
	public List<EventEntity> saveEvents(List<EventEntity> events) {

		LOGGER.info("Saving Events to databse");

		List<EventEntity> savedEvents = eventsRepository.saveAll(events);
		LOGGER.debug("'{}' events persisted in database ", savedEvents.size());

		LOGGER.info("Events saved to databse successfully");

		return savedEvents;
	}

	@Override
	public List<EventEntity> getAllEventsStored() {

		LOGGER.info("Fetching events from Database");

		List<EventEntity> retrivedEvents = eventsRepository.findAll();

		LOGGER.info("'{}' Events Retrived from backend:: ", retrivedEvents.size());

		return retrivedEvents;
	}

}
