package com.dbg.ic.gradle.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.dbg.ic.gradle.dto.HelloWorldDTO;
import com.dbg.ic.gradle.exceptions.NotFoundException;

@Service
public class HelloWorldServiceImpl implements HelloWorldService {

	private static final Map<String, String> HI = ((Supplier<Map<String, String>>) (() -> {
		final Map<String, String> map = new HashMap<>();
		map.put("es", "Hola mundo!");
		map.put("en", "Hello world!");
		return Collections.unmodifiableMap(map);
	})).get();

	@Override
	public List<HelloWorldDTO> listAll() {
		return HI.entrySet().stream().map(entry -> new HelloWorldDTO(entry.getKey(), entry.getValue()))
				.collect(Collectors.toList());
	}

	@Override
	public HelloWorldDTO listByLanguage(String language) throws NotFoundException {
		return HI.entrySet().stream().filter(entry -> entry.getKey().equals(language))
				.map(e -> new HelloWorldDTO(e.getKey(), e.getValue())).findAny().orElseThrow(NotFoundException::new);
	}

}
