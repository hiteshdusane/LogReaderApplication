package com.credit.suisse.service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import com.credit.suisse.constants.EventConstants;
import com.credit.suisse.models.Event;
import com.credit.suisse.models.EventEntity;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonStreamParser;

@Service
public class EventsServiceImpl implements EventsService {

	private static final Logger LOGGER = LoggerFactory.getLogger(EventsServiceImpl.class);

	@Override
	public List<Event> getEventsFromFile(String filePath) throws FileNotFoundException {

		StopWatch sw = new StopWatch();

		LOGGER.info("Fetching events from file...");
		sw.start();
		Gson gson = new Gson();
		List<Event> events = new ArrayList<>();
		try {
			JsonStreamParser jsonStreamParser = new JsonStreamParser(new FileReader(filePath));

			while (jsonStreamParser.hasNext()) {
				JsonElement element = jsonStreamParser.next();
				if (element.isJsonObject()) {

					Event event = gson.fromJson(element, Event.class);
					events.add(event);

				}
			}
			sw.stop();
			LOGGER.info("Events fetched from file in {} milliseconds", sw.getTotalTimeMillis());

		} catch (FileNotFoundException e) {

			LOGGER.error("File not found... '{}'", e.getMessage());
			LOGGER.error("Please enter valid file path...");
			throw new FileNotFoundException("Please enter valid file path");

		} catch (Exception e) {

			LOGGER.error("Something went wrong...");

		}

		return events;

	}

	@Override
	public List<EventEntity> getEventWiseData(List<Event> events) {

		LOGGER.info("Calculating event duration");
		List<EventEntity> eventWiseList = new ArrayList<>();
		StopWatch sw = new StopWatch();

		ConcurrentMap<String, Event> startedEventsMap = events.stream()
				.filter(e -> EventConstants.EVENT_STATE_STARTED.equalsIgnoreCase(e.getState()))
				.collect(Collectors.toConcurrentMap(Event::getId, Function.identity(), (e, n) -> {
					return e;
				}));
		LOGGER.debug("Number of started events '{}'", startedEventsMap.size());

		events.parallelStream().filter(e -> EventConstants.EVENT_STATE_FINISHED.equalsIgnoreCase(e.getState()))
				.forEach(e -> {
					EventEntity eventEntity = new EventEntity();
					eventEntity.setId(e.getId());
					eventEntity.setType(e.getType());
					eventEntity.setHost(e.getType());
					eventEntity.setEventDuration(Long.valueOf(e.getTimestamp())
							- Long.valueOf(startedEventsMap.get(e.getId()).getTimestamp()));
					eventEntity.setAlert(eventEntity.getEventDuration() > 4);
					eventWiseList.add(eventEntity);
				});

		sw.stop();
		LOGGER.info("Events duration calculated in '{}' milliseconds", sw.getTotalTimeMillis());

		return eventWiseList;
	}

}
