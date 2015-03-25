package edu.sjsu.cmpe275.lab1;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * @author Nikhil
 * 
 * Type: Aspect class - inject advices
 * 
 * call to each service functionality is intercepted
 * and logged to console
 *
 */
@Aspect
public class AspectLogging {

	//advice injected before the execution of readFile() method
	@Before(value = "execution(* *readFile(..))")
	public void logReadCall(JoinPoint jp){
		
	Object[] args = jp.getArgs();
	System.out.println(args[0]+" reads the file "+args[1]);
	
	}
	
	//advice injected before the execution of shareFile() method
	@Before("execution(* *shareFile(..))")
	public void logShareCall(JoinPoint jp){
		
		Object[] args = jp.getArgs();
		System.out.println(args[0]+" shares the file "+args[2]+" with "+args[1]);
	
	}

	//advice injected before the execution of unShareFile() method
	@Before("execution(* *unShareFile(..))")
	public void logunshareCall(JoinPoint jp){
		
		Object[] args = jp.getArgs();
		System.out.println(args[0]+" unshares the file "+args[2]+" with "+args[1]);
		
	}

}
