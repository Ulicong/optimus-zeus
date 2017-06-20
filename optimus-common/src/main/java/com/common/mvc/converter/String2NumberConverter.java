package com.common.mvc.converter;

public class String2NumberConverter implements org.springframework.core.convert.converter.Converter<String, Number> {

	public Number convert(String arg0) {
		try {
			return Double.valueOf(arg0);
		} catch (Exception e) {
		}
		try {
			return Float.valueOf(arg0);
		} catch (Exception e) {
		}
		try {
			return Integer.valueOf(arg0);
		} catch (Exception e) {
		}

		return null;
	}

}
