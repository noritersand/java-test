package com.noritersand;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.primitives.Booleans;
import com.google.gson.Gson;

public class GuavaTest {
	private static final Logger log = LoggerFactory.getLogger(GuavaTest.class);
	
	public static void main(String[] args) {
		log.debug(String.valueOf(Gson.class));
		log.debug(String.valueOf(Booleans.class));
	}
}
