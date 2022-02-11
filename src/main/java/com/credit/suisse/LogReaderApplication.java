package com.credit.suisse;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.credit.suisse.exception.EventsNotFoundException;
import com.credit.suisse.models.Event;
import com.credit.suisse.models.EventEntity;
import com.credit.suisse.service.EventsEntityService;
import com.credit.suisse.service.EventsService;
import com.credit.suisse.service.UserInputService;

@SpringBootApplication
public class LogReaderApplication implements CommandLineRunner {

	private static final Logger LOGGER = LoggerFactory.getLogger(LogReaderApplication.class);

	@Autowired
	private EventsService eventsService;

	@Autowired
	private EventsEntityService eventsEntityService;

	@Autowired
	private UserInputService userInputService;

	public static void main(String[] args) {

		SpringApplication.run(LogReaderApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		LOGGER.info("Welcome, this is Command Line Spring Boot Application...");
		
		String filePath = userInputService.acceptFilePathFromUser();

		List<Event> events = eventsService.getEventsFromFile(filePath);

		LOGGER.info("Number of all events fetched from file:: '{}'", events.size());
		if (events.isEmpty()) {
			LOGGER.error("Events not found in log file/ Or something went wrong:: ");
			throw new EventsNotFoundException("Please try again");
		}

		List<EventEntity> eventsSummary = eventsService.getEventWiseData(events);

		

		List<EventEntity> savedEvents = eventsEntityService.saveEvents(eventsSummary);
		LOGGER.info("Number of all events saved to database:: '{}'", savedEvents.size());

	}

}
