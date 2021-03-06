package com.bogeum.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonConfiguration {

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelmapper = new ModelMapper();
		modelmapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
//		modelmapper.getConfiguration().setFieldMatchingEnabled(true);
//		modelmapper.getConfiguration().setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE);
		
		return modelmapper;
	}
}
