package com.birdbraintechnologies;

import java.util.ArrayList;
import java.util.List;

public class Command {

	private String key;
	private List<Integer> values;

	public Command(String key, List<Integer> values) {
		super();
		this.key = key;
		this.values = values;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public List<Integer> getValues() {
		return values;
	}

	public void setValues(List<Integer> values) {
		this.values = values;
	}
}
