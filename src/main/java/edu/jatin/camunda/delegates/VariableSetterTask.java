package edu.jatin.camunda.delegates;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class VariableSetterTask implements JavaDelegate {

	public void execute(DelegateExecution execution) throws Exception {
		execution.setVariableLocal("localVariable", "local1");
		execution.setVariable("transition", true);
	}

}
