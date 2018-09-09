package edu.jatin.camunda.delegates;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class LocalVariableSetterTask implements JavaDelegate {

	public void execute(DelegateExecution execution) throws Exception {
		execution.setVariable("localVariable", "local2");
	}

}
