package jdk.statement;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * 
 * @since 2017-07-27
 * @author fixalot
 */
public class SynchronizeTest {
	private static final Logger logger = LoggerFactory.getLogger(SynchronizeTest.class);

	public static void main(String[] args) {
		SynchronizeTest instance = new SynchronizeTest();
		Thread first = new Thread() {
			@Override
			public void run() {
				instance.printsomething();
			}
		};
		first.start();
		Thread second = new Thread() {
			@Override
			public void run() {
				instance.printsomething();
			}
		};
		second.start();		
	}
	
//	public synchronized void printsomething() {
	public void printsomething() {
		synchronized (this) {
			logger.debug("=========== 시작 ================");
			logger.debug("this: {}", String.valueOf(this));
			logger.debug("time: {}", LocalDateTime.now());
			logger.debug("current method: {}()", new Throwable().getStackTrace()[0].getClassName() + "." + new Throwable().getStackTrace()[0].getMethodName());
			logger.debug("line: {}", new Throwable().getStackTrace()[0].getLineNumber());
			logger.debug("=========== 끗 ================");
		}
	}
}
