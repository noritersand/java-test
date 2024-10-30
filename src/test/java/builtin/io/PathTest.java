package builtin.io;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
/**
 * <p>{@link Path} 클래스 테스트
 *
 * <p>src/main 혹은 src/test는 로컬에서만 사용해야하는 경로다. 이 경로들은 메이븐 개발 환경에서만 존재하는 폴더 구조이고 war나 jar로 빌드되면 존재하지 않는 경로이기 때문.
 * <p>따라서 경로빌드 후 생성될 경로를 절대 경로로 프로퍼티에서 관리하는 편이 좋다.
 *
 * @author fixalot
 * @since 2017-07-27
 */
@Slf4j
class PathTest {
    public static final String SLASH = "/";
    public static final String BACKSLASH = "\\";

    @Test
    void testOf() {
        Path path = Path.of("/a/b/c");
        Path path2 = Path.of("/a", "b", "c");
        assertThat(path2).isEqualTo(path);

        Path path3 = Path.of("a/b/c");
        Path path4 = Path.of("a", "b", "c");
        assertThat(path4).isEqualTo(path3);

        assertThat(path.toFile()).isEqualTo(new File("/a/b/c"));

        assertThatThrownBy(() -> {
            Path.of("somewhere", null);
        }).isInstanceOf(NullPointerException.class).hasMessage("Cannot read the array length because \"more\" is null");

        assertThatThrownBy(() -> {
            Path.of(null, "somewhere");
        }).isInstanceOf(NullPointerException.class).hasMessage(null);
    }

    /**
     * Path.toString()은 디렉터리 구분자를 운영 체제의 파일 시스템 구분자를 사용하기 때문에, 윈도우에선 \\로 반환한다.
     */
    @Test
    void testToString() {
        Path path = Path.of("/a/b/c");
        assertThat(path.toString()).isEqualTo("\\a\\b\\c");

        // 만약 /로 바꾸고 싶다면 replace를 하거나
        assertThat(path.toString().replace(BACKSLASH, SLASH)).isEqualTo("/a/b/c");

        // 아니면 iterator를 이용하는 방법이 있다.
        StringBuilder sb = new StringBuilder().append(SLASH);
        Iterator<Path> iterator = path.iterator();
        while (iterator.hasNext()) {
            sb.append(iterator.next().toString());
            if (iterator.hasNext()) {
                sb.append(SLASH);
            }
        }
        assertThat(sb.toString()).isEqualTo("/a/b/c");
    }

    /**
     * <p>파일 시스템의 루트부터 주어진 경로까지의 절대 경로를 반환하는 메서드.
     * <p>OS의 파일 시스템에 맞는 경로를 반환한다.
     */
    @Test
    void testAbsolutePath() {
        Path path = Path.of("/a/b/c");
        assertThat(path.toAbsolutePath().toString()).isEqualTo("C:\\a\\b\\c");

        Path path2 = Path.of("a/b/c");
        assertThat(path2.toAbsolutePath().toString()).isEqualTo("C:\\dev\\intellij-workspace\\java-test\\a\\b\\c");
    }

    /**
     * toUri() 테스트
     */
    @Test
    void testToUri() {
        Path path = Path.of("/a/b/c");
        assertThat(path.toUri().toString()).isEqualTo("file:///C:/a/b/c");
        assertThat(path.toUri().getPath()).isEqualTo("/C:/a/b/c");

        Path path2 = Path.of("a/b/c");
        assertThat(path2.toUri().toString()).isEqualTo("file:///C:/dev/intellij-workspace/java-test/a/b/c");
        assertThat(path2.toUri().getPath()).isEqualTo("/C:/dev/intellij-workspace/java-test/a/b/c");

        Path path3 = Paths.get("C:\\Users\\User\\Documents\\file.txt");
        assertThat(path3.toUri().toString()).isEqualTo("file:///C:/Users/User/Documents/file.txt");
    }

    /**
     * source부터 target까지의 상대 경로를 구한다.
     * 어떠한 경로 두 개가 있을 때 서로의 상대 경로를 구하는 메서드
     */
    @Test
    void testRelativize() {
        Path path1 = new File("c:/dev/git").toPath();
        Path path2 = new File("c:/sso").toPath();
        assertThat(path1.relativize(path2).toString()).isEqualTo("..\\..\\sso"); // dev에서 sso로 가려면 'cd ..\..\sso'
        assertThat(path2.relativize(path1).toString()).isEqualTo("..\\dev\\git");// sso에서 dev로 가려면 'cd ..\dev\git'
        assertThat(path1.relativize(path1).toString()).isEqualTo("");// dev에서 dev로 가려면 'cd .'

        Path source = new File("webapp/upload/temp/").toPath();
        Path target = new File("webapp/upload/temp/201612/28201838255.png").toPath();
        assertThat(source.relativize(target).toString()).isEqualTo("201612\\28201838255.png"); // dev에서 sso로 가려면 'cd ..\..\sso'
    }

    /**
     * <p>기존 Path 객체에 문자열을 이어 붙여 하위 경로를 생성할 때 사용한다.</p>
     * <p>단순히 이어붙이기만 현재 경로와 주어진 경로를 결합하는데,</p>
     * <p>만약 주어진 경로에 ..  / 같은 문자가 있으면 현재 경로에서 상위 경로로 이동하는 결과가 나온다</p>
     *
     * @throws IOException
     */
    @Test
    void testResolve() throws IOException {
        // 원본
        Path dir1 = new File("c:/dev/repo").toPath();
        Path dir2 = Path.of(dir1.toString());
        Path dir3 = Path.of(dir1.toString());
        Path dir4 = Path.of(dir1.toString());
        Path dir5 = Path.of(dir1.toString());

        // 파일명으로 붙이기
        dir1 = dir1.resolve("a.txt");
        assertThat(dir1.toString()).isEqualTo("c:\\dev\\repo\\a.txt");

        // 여러개 이어붙이기
        String[] addtionalDirectories = new String[]{"a", "b", "c"};
        for (String addtionalDirectory : addtionalDirectories) {
            dir2 = dir2.resolve(addtionalDirectory);
        }
        assertThat(dir2.toString()).isEqualTo("c:\\dev\\repo\\a\\b\\c");

        // 상위 경로로 이동하기
        dir3 = dir3.resolve("..");
        assertThat(dir3.toString()).isEqualTo("c:\\dev\\repo\\..");

        // 상위로 갔다가 다시 하위로 이동하기
        dir4 = dir4.resolve("../parent");
        assertThat(dir4.toString()).isEqualTo("c:\\dev\\repo\\..\\parent");

        // 만약 정규화된 경로를 얻고 싶다면 Path.normalize()를 사용함
        assertThat(dir4.normalize().toString()).isEqualTo("c:\\dev\\parent");

        // 상위 경로로 이동하기: 특이 케이스
        dir5 = dir5.resolve("/super");
        assertThat(dir5.toString()).isEqualTo("c:\\super");
    }

    /**
     * resolve() 메서드로 얻는 상대 경로 객체는 절대 경로 객체와 다른 것처럼 취급된다.
     */
    @Test
    void testResolve2() throws IOException {
        Path dir1 = new File("c:/dev/repo").toPath();
        Path dir3 = Path.of(dir1.toString());

        // 상위 경로로 이동하기
        dir3 = dir3.resolve("..");
        assertThat(dir3.toString()).isEqualTo("c:\\dev\\repo\\..");
        assertThat(dir3.toAbsolutePath().toString()).isEqualTo("c:\\dev\\repo\\..");
        assertThat(dir3.toUri().toString()).isEqualTo("file:///c:/dev/repo/../");
        assertThat(dir3.toFile().toString()).isEqualTo("c:\\dev\\repo\\..");

        File file1 = dir3.toFile(); // 상대 경로
        File file2 = new File("c:\\dev"); // 절대 경로

        // 둘 다 디렉터리가 맞지만
        assertThat(file1.isDirectory()).isTrue();
        assertThat(file2.isDirectory()).isTrue();

        // 이름이며 객체며 다 다르다
        assertThat(file1).isNotEqualTo(file2);
        assertThat(file1.getName()).isNotEqualTo(file2.getName());
        assertThat(file1.getAbsolutePath()).isNotEqualTo(file2.getAbsolutePath());
        assertThat(file1.getAbsoluteFile()).isNotEqualTo(file2.getAbsoluteFile());

        // 캐노니컬한 경로만 같다
        assertThat(file1.getCanonicalFile()).isEqualTo(file2.getCanonicalFile());
        assertThat(file1.getCanonicalPath()).isEqualTo(file2.getCanonicalPath());
    }

    /**
     * 주어진 경로에서 시작 인덱스와 종료 인덱스 만큼을 잘라냄
     */
    @Test
    void testSubpath() {
        Path path = Path.of("/a/b/c/e/f/g.jpg");
        assertThat(path.subpath(0, 3).toString()).isEqualTo("a\\b\\c");
        assertThat(path.subpath(3, 6).toString()).isEqualTo("e\\f\\g.jpg");
    }

    /**
     * normalize() 테스트
     */
    @Test
    void testNormalize() {
        Path path = Path.of("./../../a/b/c.jpg");
        assertThat(path.normalize().toString()).isEqualTo("..\\..\\a\\b\\c.jpg");
    }
}
