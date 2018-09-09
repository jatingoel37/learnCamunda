package edu.jatin.camunda.delegates;

import java.util.List;
import java.util.Set;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.runtime.ActivityInstance;
import org.camunda.bpm.engine.runtime.Execution;

public class TruePrinterTask implements JavaDelegate {

	public void execute(DelegateExecution execution) throws Exception {

		Set<String> variables = execution.getVariableNames();
		if (variables.contains("localVariable")) {
			System.out.println("localVariable FOUND!!");
			System.out.println(execution.getVariable("localVariable"));
		}

		System.out.println("I am printing TRUE because value of the transition variable is: "
				+ execution.getVariable("transition"));

		List<Execution> executions = execution.getProcessEngineServices().getRuntimeService().createExecutionQuery()
				.processInstanceId(execution.getProcessInstanceId()).list();
		System.out.println(executions.size());
		
		ActivityInstance activityInstance = execution.getProcessEngineServices().getRuntimeService().getActivityInstance(execution.getProcessInstanceId());
		ActivityInstance[] childrenActivities = activityInstance.getChildActivityInstances();
		
		System.out.println(activityInstance.getActivityName());
		System.out.println(childrenActivities[0].getActivityName());
		
		
	}

}
