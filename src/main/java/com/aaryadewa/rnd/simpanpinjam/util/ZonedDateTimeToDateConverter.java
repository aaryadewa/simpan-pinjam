package com.aaryadewa.rnd.simpanpinjam.util;

import java.time.ZonedDateTime;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

public class ZonedDateTimeToDateConverter implements Converter<ZonedDateTime, Date> {

  public static final ZonedDateTimeToDateConverter INSTANCE = new ZonedDateTimeToDateConverter();

  private ZonedDateTimeToDateConverter() {
  }

  @Override
  public Date convert(ZonedDateTime source) {
      return source == null ? null : Date.from(source.toInstant());
  }
}
