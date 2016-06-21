package com.orangeandbronze.enlistment.domain;

public enum Days {
	MTH, TF, WS;
	
	public String toString() {
		switch (this) {
		case MTH: return "Mon/Thu";
		case TF: return "Tue/Fri";
		case WS: return "Wed/Sat";
		default: return "";
		}
	}

}
