package org.uob.a1;

public class Item {
	// attributes
	private String name;
	private Position itemPosition;
	private boolean feature;
	private String description;

	// constructor
	public Item(String name, Position itemPosition, boolean feature, String description) {
		this.name = name;
		this.itemPosition = itemPosition;
		this.feature = feature;
		this.description = description;
	}

	// getters and setters
	public String getName() {
		return this.name;
	}

	public Position getPosition() {
		return this.itemPosition;
	}

	public boolean isFeature() {
		return feature;
	}

	public String getDescription() {
		return description;
	}
	
}