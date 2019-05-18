package com.test.fiction.fictiongame.event;

import java.time.Instant;

import org.springframework.context.ApplicationEvent;

public class EventRecord extends ApplicationEvent {

	private Instant instant;
	
	public EventRecord(Object source) {
		super(source);
		this.instant = Instant.now();
	}
	
	public Instant getInstant() {
		return instant;
	}

	public void setInstant(Instant instant) {
		this.instant = instant;
	}
	
	@Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final EventRecord that = (EventRecord) o;

        return instant.equals(that.instant);
    }

    @Override
    public int hashCode() {
        return instant.hashCode();
    }
	
	
}
