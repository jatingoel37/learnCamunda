package edu.jatin.camunda.delegates;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class FalsePrinterTask implements JavaDelegate {

	public void execute(DelegateExecution execution) throws Exception {
		System.out.println("I am printing FALSE because value of the transition variable is: "
				+ execution.getVariable("transition"));
	}

}
