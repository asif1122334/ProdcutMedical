package com.example.model;

public class SessionDetails {
    private String sessionId;

    // Getters and setters
    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

	@Override
	public String toString() {
		return "SessionDetails [sessionId=" + sessionId + "]";
	}

	public SessionDetails(String sessionId) {
		super();
		this.sessionId = sessionId;
	}

	public SessionDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
    
}