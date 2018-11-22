package com.polluxframework;

import org.junit.Test;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;
import org.kie.internal.utils.KieHelper;


/**
 * Unit test for simple App.
 */
public class AppTest {
	/**
	 * Rigorous Test :-)
	 */
	@Test
	public void shouldAnswerWithTrue() {
		String rule = "package com.sy\r\n";
		rule += "import com.polluxframework.Message;\r\n";
		rule += "rule \"Hello World\"\r\n";
		rule += "when\r\n";
		rule += "\tm : Message( status == Message.HELLO, myMessage : message )\r\n";
		rule += "then\r\n";
		rule += "\tSystem.out.println( myMessage );\r\n";
		rule += "\tm.setMessage(\"Goodbye cruel world\");\r\n";
		rule += "\tm.setStatus( Message.GOODBYE );;\r\n";
		rule += "\tupdate( m );;\r\n";
		rule += "end\r\n";

		rule += "rule \"GoodBye\"\r\n";
		rule += "when\r\n";
		rule += "\tMessage( status == Message.GOODBYE, myMessage : message )\r\n";
		rule += "then\r\n";
		rule += "\tSystem.out.println( 2+\":\"+myMessage );\r\n";
		rule += "end\r\n";
		System.out.println(rule);
		Message message = new Message();
		message.setMessage("Hello World");
		message.setStatus(Message.HELLO);

		Message message2 = new Message();
		message2.setStatus(Message.GOODBYE);
		message2.setMessage("hi world!");


		KieHelper helper = new KieHelper();
		helper.addContent(rule, ResourceType.DRL);
		KieSession kSession = helper.build().newKieSession();
		FactHandle fact  = kSession.insert(message);
		FactHandle fact2  = kSession.insert(message2);
		kSession.fireAllRules();
		kSession.dispose();

		System.out.println(message);
		System.out.println(message2);
	}

}
