package edu.jatin.camunda.delegates;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.camunda.bpm.engine.ProcessEngineConfiguration;
import org.camunda.bpm.engine.runtime.ActivityInstance;
import org.camunda.bpm.engine.runtime.Execution;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.engine.test.mock.Mocks;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
@Deployment(resources = { "parallal_waiting_process.bpmn20.xml" })
public class ParallalWaitingProcessTest {

	// Should be public
	@Rule
	public ProcessEngineRule processEngineRule = new ProcessEngineRule(
			ProcessEngineConfiguration.createStandaloneInMemProcessEngineConfiguration().buildProcessEngine());

	@Test
	public void test() {

		// No need to register mock expression manager. It is registered itself.
		Mocks.register("variableSetter", new VariableSetterTask());
		Mocks.register("localVariableTask", new LocalVariableSetterTask());
		// this is process id in xml
		ProcessInstance processInstance = processEngineRule.getRuntimeService().startProcessInstanceByKey("process_1");

		List<Execution> executions = processEngineRule.getRuntimeService().createExecutionQuery()
				.processInstanceId(processInstance.getId()).list();
		System.out.println(executions.size());
		assertThat(executions).hasSize(3);
		assertThat(executions.get(2).getId()).isEqualTo(processInstance.getId());

		ActivityInstance activityInstance = processEngineRule.getRuntimeService()
				.getActivityInstance(processInstance.getId());

		assertThat(activityInstance.getId()).isEqualTo(processInstance.getId());
		assertThat(activityInstance.getActivityType()).isEqualTo("processDefinition");
		
		ActivityInstance[] activitiInstances = activityInstance.getChildActivityInstances();
		
		// You can see activitiInstances[i] values
		assertThat(activitiInstances.length).isEqualTo(2);

	}
}
