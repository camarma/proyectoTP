package tp.pr2.mv.test;

import org.junit.runner.RunWith;  
import org.junit.runners.Suite;  
import org.junit.runners.Suite.SuiteClasses;  

@RunWith(Suite.class)  
@SuiteClasses({ControlUnitTest.class, CPUTest.class, InstructionParserTest.class, InstructionsTest.class, OperandStackTest.class, MemoryTest.class, Validador.class})  
public class AllTest {
}
