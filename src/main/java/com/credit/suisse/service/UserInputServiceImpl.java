package com.credit.suisse.service;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserInputServiceImpl implements UserInputService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserInputServiceImpl.class);

	@Override
	public String acceptFilePathFromUser() {
		Scanner sc = new Scanner(System.in);
		LOGGER.info("\n Enter file path with file name then press enter :: ");
		String filePath = sc.nextLine();
		LOGGER.info("You have Entered -> '{}' <- this file path", filePath);
		sc.close();
		return filePath;
	}

}
