package com.layer.profileDetails.model;

import java.util.List;

import lombok.Data;

@Data
public class ChronoDetails {

	String description;
	String category;
	Align align;
	List<CategoryDetails> categoryDetails;

	@Data
	public static class CategoryDetails {
		String title;
		String highlight;
		String summary;
		String descriptionType;
		List<String> description;
		List<String> shortDescription;
	}

	enum Align {
		LEFT("left"), RIGHT("right");

		String value;

		Align(String direction) {
			this.value = direction;
		}

		public String getValue() {
			return this.value;
		}

		public void getValue(String value) {
			this.value = value;
		}

	}

}
