package com.orangeandbronze.enlistment.domain;

public enum Period {
	FIRSTPERIOD, SECONDPERIOD, THIRDPERIOD, 
	FOURTHPERIOD, FIFTHPERIOD, SIXTHPERIOD;
	
	public String toString() {
		switch (this) {
		case FIRSTPERIOD: return "8:30am-10am";
		case SECONDPERIOD: return "10am-11:30am";
		case THIRDPERIOD: return "11:30AM-1pm";
		case FOURTHPERIOD: return "1pm-2:30pm";
		case FIFTHPERIOD: return "2:30pm-4pm";
		case SIXTHPERIOD: return "4pm-5:30pm";
		default: return "";
		}
	}

}
