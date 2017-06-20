package com.common.mvc.converter;

import org.springframework.core.convert.converter.Converter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class String2DateConverter implements Converter<String, Date> {

	private List<String> formats;

	private static DateFormat[] dfs;

	public void init() {
		dfs = new SimpleDateFormat[formats.size()];
		for (int i = 0; i < formats.size(); i++) {
			dfs[i] = new SimpleDateFormat(formats.get(i));
		}
	}

	public Date convert(String arg0) {
		for (DateFormat df : dfs) {
			try {
				return df.parse(arg0);
			} catch (Exception e) {
				continue;
			}
		}
		return null;
	}

	public void setFormats(List<String> formats) {
		this.formats = formats;
	}

}
