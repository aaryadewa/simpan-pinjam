package com.aaryadewa.rnd.simpanpinjam.config;

import java.util.ArrayList;
import java.util.List;

import com.aaryadewa.rnd.simpanpinjam.util.DateToZonedDateTimeConverter;
import com.aaryadewa.rnd.simpanpinjam.util.ZonedDateTimeToDateConverter;

import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.data.mongodb.core.mapping.event.ValidatingMongoEventListener;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
@EnableMongoRepositories("com.aaryadewa.rnd.simpanpinjam.repository.mongodb")
@Import(value = MongoAutoConfiguration.class)
public class MongoDbConfiguration {
  
  @Bean
  public ValidatingMongoEventListener validatingMongoEventListener() {
      return new ValidatingMongoEventListener(validator());
  }

  @Bean
  public LocalValidatorFactoryBean validator() {
      return new LocalValidatorFactoryBean();
  }

  @Bean
  public MongoCustomConversions customConversions() {
      List<Converter<?, ?>> converters = new ArrayList<>();
      converters.add(DateToZonedDateTimeConverter.INSTANCE);
      converters.add(ZonedDateTimeToDateConverter.INSTANCE);
      return new MongoCustomConversions(converters);
  }
}
