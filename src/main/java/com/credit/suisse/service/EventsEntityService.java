package com.credit.suisse.service;

import java.util.List;

import com.credit.suisse.models.EventEntity;

public interface EventsEntityService {

	List<EventEntity> saveEvents(List<EventEntity> events);

	List<EventEntity> getAllEventsStored();

}
