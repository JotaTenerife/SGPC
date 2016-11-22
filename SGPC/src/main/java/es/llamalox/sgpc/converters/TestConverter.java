package es.llamalox.sgpc.converters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import es.llamalox.sgpc.model.tests.Test;
import es.llamalox.sgpc.service.tests.TestService;

@Component
public class TestConverter implements Converter<String, Test> {

	static final Logger LOGGER = LoggerFactory.getLogger(TestConverter.class);

	@Autowired
	private TestService service;

	@Override
	public Test convert(String element) {
		Integer id = Integer.parseInt(element);
		Test test = service.getByKey(id);
		LOGGER.info("Test : {}", test);
		return test;
	}
}
