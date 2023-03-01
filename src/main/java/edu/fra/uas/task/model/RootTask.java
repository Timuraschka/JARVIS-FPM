package edu.fra.uas.task.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * This class represents the "first task" which should be created with every new project
 * 
 * 
 * 
 * @author timur
 *
 */
public class RootTask extends Task{
	
	private static final Logger log = LoggerFactory.getLogger(Task.class);

	
	
	public RootTask () {
		
		this.setParent(null);
	}
	
	@Override
	public void setParent(Task t) {
		log.debug(">> Root Task lässt sich kein Parent zuzuweisen!!");
	}

}