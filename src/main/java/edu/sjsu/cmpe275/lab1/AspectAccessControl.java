package edu.sjsu.cmpe275.lab1;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * @author Nikhil
 * 
 * Type : Aspect class - inject advices
 * 
 * Call to each file service functionality is intercepted
 * and validated to control the access to the interface
 *
 */
@SuppressWarnings("unused")
@Aspect
public class AspectAccessControl {

	private boolean filePathValid(String path) {
		boolean isPathValid = false;
		String patt2 = ".*//?home//?[a-zA-Z0-9_]+//?shared//?[a-zA-Z0-9_]+\\.[a-zA-Z]+";
		Pattern p = Pattern.compile(patt2);
		Matcher m = p.matcher(path);
		isPathValid = m.matches();
		return isPathValid;
	}

	//advice for shareFile() method
	@Around("execution(* *shareFile(..))")
	public void inspectShareCall(ProceedingJoinPoint jp)
			throws UnauthorizedException, InvalidFilePathException {

		FileService fs = (FileService) jp.getTarget();
		Object[] args = jp.getArgs();

		if (!filePathValid((String) args[2])) {
			throw new InvalidFilePathException("File path is not valid");
		}

		if (!((String) args[0]).equals((String) args[1])
				&& filePathValid((String) args[2])) {
			if (fs.isOwner((String) args[0])
					|| fs.isSharedWith((String) args[0])) {
				try {
					// System.out.println("Proceeding - in share");
					jp.proceed();

				} catch (Throwable e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				throw new UnauthorizedException(
						"You are not authorized to share this file");
			}
		}
	}

	//advice for unshareFile() method
	@Around("execution(* *unShareFile(..))")
	public void inspectUnShareCall(ProceedingJoinPoint jp)
			throws InvalidFilePathException {

		Object[] args = jp.getArgs();
		FileService fs = (FileService) jp.getTarget();

		if (!filePathValid((String) args[2])) {
			throw new InvalidFilePathException("File path is not valid");
		}

		if (!((String) args[0]).equals((String) args[1])
				&& filePathValid((String) args[2])) {
			if (fs.isOwner((String) args[0])) {
				try {
					// System.out.println("Proceeding");
					jp.proceed();

				} catch (Throwable e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				// System.out.println("not Proceeding");
			}
		}
	}

	//advice for readFile() method
	@Around("execution(* *readFile(..))")
	public Object inspectReadCall(ProceedingJoinPoint jp)
			throws UnauthorizedException, InvalidFilePathException {

		Object[] args = jp.getArgs();
		Object result = null;
		FileService fs = (FileService) jp.getTarget();

		if (!filePathValid((String) args[1])) {
			throw new InvalidFilePathException("File path is not valid");
		}

		if (filePathValid((String) args[1])
				&& (fs.isOwner((String) args[0]) || fs
						.isSharedWith((String) args[0]))) {
			try {
				// System.out.println("Proceeding");
				result = jp.proceed();

			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			// System.out.println("not Proceeding");
			throw new UnauthorizedException(
					"You are not authorized to read this file");
		}

		return result;

	}

}
