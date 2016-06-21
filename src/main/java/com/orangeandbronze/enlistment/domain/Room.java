package com.orangeandbronze.enlistment.domain;

public enum Room {
	RH300(40), RH301(40), GAB203(20), GAB207(20); 
	
	final int capacity;
	
	Room(int capacity) {
		this.capacity = capacity;
	}

	static int getCapacity(Room room) {
		return room.capacity;
	}
}
