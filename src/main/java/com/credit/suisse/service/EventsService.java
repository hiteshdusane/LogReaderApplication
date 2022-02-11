package com.credit.suisse.service;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.credit.suisse.models.Event;
import com.credit.suisse.models.EventEntity;

public interface EventsService {

	List<Event> getEventsFromFile(String filePath)
			throws FileNotFoundException, InterruptedException, ExecutionException;

	List<EventEntity> getEventWiseData(List<Event> events);

}
