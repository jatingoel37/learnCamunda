package edu.jatin.camunda.delegates;

import org.camunda.bpm.engine.ProcessEngineConfiguration;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.engine.test.mock.Mocks;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
@Deployment(resources = { "variable_printer.bpmn20.xml" })
public class VariablePrinterProcessTest {

	// Should be public
	@Rule
	public ProcessEngineRule processEngineRule = new ProcessEngineRule(
			ProcessEngineConfiguration.createStandaloneInMemProcessEngineConfiguration().buildProcessEngine());

	@Test
	public void test() {

		// No need to register mock expression manager. It is registered itself.
		Mocks.register("variableSetter", new VariableSetterTask());
		Mocks.register("localVariableTask", new LocalVariableSetterTask());
		Mocks.register("true_printer", new TruePrinterTask());
		Mocks.register("false_printer", new FalsePrinterTask());
		// this is process id in xml
		processEngineRule.getRuntimeService().startProcessInstanceByKey("process_1");
	}
}
