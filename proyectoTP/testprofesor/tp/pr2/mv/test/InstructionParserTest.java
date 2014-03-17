package tp.pr2.mv.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import tp.pr2.mv.InstructionParser;

public class InstructionParserTest {


	@Test
	public void testParseJump() {
		test1arg("jump 2", "Jump 1", "   jump   3  ", "jump 3 4", "jump");
		test1arg("bf 2", "bf 1", "   bf   3  ", "bf 3 4", "bf");
		test1arg("bt 2", "bt 1", "   bt   3  ", "bt 3 4", "bt");
	}

	@Test
	public void testParseLogic() {
		test0args("and","And","  aNd  ","and 3");
		test0args("or","Or","  oR  ","or 3");
		test0args("not","Not","  nOt  ","not 3");
	}

	@Test
	public void testParsePush() {
		checkOk("push 1");
		checkOk("pUSH 2");		
		checkOk("   pUSH    3   ");
		checkWrong("push 4 4");
		checkWrong("push");
	}


	
	@Test
	public void testParseStore() {
		checkOk("store 1");
		checkOk("sTore 2");		
		checkOk("   sTORE    3   ");
		checkWrong("store 4 4");
		checkWrong("store");
	}
	
	@Test
	public void testParseLoad() {
		checkOk("load 1");
		checkOk("lOAd 2");		
		checkOk("   loaD    3   ");
		checkWrong("load 4 4");
		checkWrong("load");
	}	

	@Test
	public void testParseComparison() {
		test0args("eq","Eq","  eQ  ","eq 3");
		test0args("gt","Gt","  gT  ","gt 3");
		test0args("le","Le","  le  ","le 3");
		test0args("lt","Lt","  lt  ","lt 3");
	}
	
	@Test
	public void testParsePop() {
		test0args("pop","pOp","  pOp  ","pop 3");
	}

	@Test
	public void testParseHalt() {
		test0args("halt","hALt","  halt  ","halt 3");
	}
	
	@Test
	public void testParseDup() {
		test0args("dup","dUP","  dup  ","dup 3");
	}
	
	@Test
	public void testParseFlip() {
		test0args("flip", "fLIp","  fliP ", "flip 3");
	}
	
	@Test
	public void testParseAdd() {
		test0args("add","aDD","  aDd  ","add 4 4");
	}
	
	@Test
	public void testParseSub() {
		test0args("sub","sUB","  sub  ","sub 4");
	}

	@Test
	public void testParseMul() {
		// CUIDADO en la Practica 1 era MUL (sin la T). Ahora es con t: MULT
		test0args("mult","mULt","  mult  ","mult 4");
	}

	@Test
	public void testParseDiv() {
		test0args("div","Div","  div  ","div 4");
	}
	
	@Test
	public void testParseOut() {
		test0args("out","Out","  out  ","out 4");
	}


	private void test0args(String ok1, String ok2, String ok3, String wrong1){
		checkOk(ok1);
		checkOk(ok2);		
		checkOk(ok3);
		checkWrong(wrong1);
	}
	
	private void test1arg(String ok1, String ok2, String ok3, String wrong1,  String wrong2){
		checkOk(ok1);
		checkOk(ok2);		
		checkOk(ok3);
		checkWrong(wrong1);
		checkWrong(wrong2);
	}
	
	private void checkWrong(String line)
	{
		assertNull("El análisis de "+line+" debería haber sido incorecto", InstructionParser.parse(line));
	}
	
	
	private void checkOk(String line)
	{
		assertNotNull("El análisis de "+line+" debería haber sido corecto", InstructionParser.parse(line));
	}
}
