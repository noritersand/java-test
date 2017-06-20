package laboratory.thirdparty.google.guava;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.reflect.ClassPath;
import com.google.common.reflect.ClassPath.ClassInfo;
import com.google.common.reflect.ClassPath.ResourceInfo;

public class ClassPathTest {
//	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(ClassPathTest.class);

//	@Test // 로그가 너무 길어져서 생략
	public void getAllClasses() throws IOException {
		ClassPath classPath = ClassPath.from(this.getClass().getClassLoader());
		ImmutableSet<ClassInfo> classInfoSet = classPath.getAllClasses();
		UnmodifiableIterator<ClassInfo> iterator = classInfoSet.iterator();
		while (iterator.hasNext()) {
			ClassInfo classInfo = iterator.next();
			logger.debug(String.valueOf(classInfo));
		}
	}
	
//	@Test // 로그가 너무 길어져서 생략
	public void getAllResources() throws IOException {
		ClassPath classPath = ClassPath.from(this.getClass().getClassLoader());
		ImmutableSet<ResourceInfo> resourceInfoSet = classPath.getResources();
		UnmodifiableIterator<ResourceInfo> iterator = resourceInfoSet.iterator();
		while (iterator.hasNext()) {
			ResourceInfo resourceInfo = iterator.next();
			logger.debug(String.valueOf(resourceInfo));
		}
	}
}
