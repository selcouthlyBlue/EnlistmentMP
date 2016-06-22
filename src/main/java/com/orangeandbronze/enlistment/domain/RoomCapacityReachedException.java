package com.orangeandbronze.enlistment.domain;

public class RoomCapacityReachedException extends IllegalStateException {

	public RoomCapacityReachedException(String message) {
		super(message);
	}

}
