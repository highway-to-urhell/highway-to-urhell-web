package io.h2h.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WelcomeUserAction {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	private String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	// all struts logic here
	public String execute() {
		int range = (10 - 0) + 1;
		int cal = (int) (Math.random() * range) + 0;
		if (cal%2 == 0){
			try {
				Thread.sleep(cal*1000);
			} catch (InterruptedException e) {
				log.error("InterruptedException "+e);
			}
			log.error(" Modulo 2 "+cal);
			return "SUCCESS";
		}else{
			log.error(" No Module 2 "+cal);
			return "SUCCESS";
		}
	
	}

}
