package thirdparty.google.guava;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.reflect.ClassPath;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * @author fixalot
 * @since 2017-07-27
 */
@Slf4j
class ClassPathTest {

    //	@Test // 로그가 너무 길어져서 생략
    public void getAllClasses() throws IOException {
        ClassPath classPath = ClassPath.from(this.getClass().getClassLoader());
        ImmutableSet<ClassPath.ClassInfo> classInfoSet = classPath.getAllClasses();
        UnmodifiableIterator<ClassPath.ClassInfo> iterator = classInfoSet.iterator();
        while (iterator.hasNext()) {
            ClassPath.ClassInfo classInfo = iterator.next();
            log.debug("{}", classInfo);
        }
    }

    //	@Test // 로그가 너무 길어져서 생략
    public void getResources() throws IOException {
        ClassPath classPath = ClassPath.from(this.getClass().getClassLoader());
        ImmutableSet<ClassPath.ResourceInfo> resourceInfoSet = classPath.getResources();
        UnmodifiableIterator<ClassPath.ResourceInfo> iterator = resourceInfoSet.iterator();
        while (iterator.hasNext()) {
            ClassPath.ResourceInfo resourceInfo = iterator.next();
            log.debug("{}", resourceInfo);
        }
    }
}
