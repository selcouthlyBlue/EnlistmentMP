package com.orangeandbronze.enlistment.domain;

public class Schedule {
	private final Days days;
	private final Period period;

	public Schedule(Days days, Period period) {
		this.days = days;
		this.period = period;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj)
			return true;
		if(obj == null)
			return false;
		if(!(obj instanceof Schedule))
			return false;
		Schedule otherSchedule = (Schedule) obj;
		if(this.days != otherSchedule.days)
			return false;
		if(this.period != otherSchedule.period)
			return false;
		return true;
	}
	
	@Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((days == null) ? 0 : days.hashCode());
        result = prime * result + ((period == null) ? 0 : period.hashCode());
        return result;
    }
}
