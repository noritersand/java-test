package testbed.servlet.mvc.fileupload.controller;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import testbed.servlet.core.bean.JsonResponseObject;
import testbed.servlet.core.finder.UrlMapping;
import testbed.servlet.core.view.View;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.*;

/**
 * com.oreilly 패키지를 이용한 파일 업로드
 *
 * @author fixalot
 * @since 2017-08-11
 */
@Slf4j
public class OreillyFileUploadController {

    @UrlMapping("/page/oreilly/oreilly-fileupload-test.view")
    public View drawOreillyFileuploadTest(HttpServletRequest request, HttpServletResponse response) {
        return new View(request);
    }

    @UrlMapping("/page/oreilly/upload-file-with-oreilly.data")
    public JsonResponseObject uploadWithOreiily(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JsonResponseObject json = new JsonResponseObject();

//		final String pathname = request.getSession().getServletContext().getRealPath("/") + "upload";
        File uploadPath = Paths.get("~/java-testbed/upload").toFile();
        if (!uploadPath.exists()) {
            // 폴더가 존재하지 않으면 폴더 생성
            uploadPath.mkdirs();
        }

        String encType = StandardCharsets.UTF_8.name();
        final int maxFilesize = 20 * 1024 * 1024; // 20MB

        // MultipartRequest(request, 저장경로[, 최대허용크기, 인코딩케릭터셋, 동일한 파일명 보호 여부]), 이 함수가 호출됨과 동시에 업로드 됨.
        MultipartRequest multipart
                = new MultipartRequest(request, uploadPath.toString(), maxFilesize, encType, new DefaultFileRenamePolicy());

        log.debug("title: {}", multipart.getParameter("title")); // 입력된 문자

        Enumeration<?> inputNames = multipart.getFileNames(); // 모든 파일 타입 인풋의 이름 반환
        int index = 0;
        List<Map<String, Object>> list = new LinkedList<>();
        while (inputNames.hasMoreElements()) {
            String inputName = (String) inputNames.nextElement();
            File uploadFile = multipart.getFile(inputName);
            if (null == uploadFile) {
                continue;
            }
            Map<String, Object> map = new HashMap<>();
            map.put("index", index);
            map.put("content-type", multipart.getContentType(inputName));
            map.put("original-file-name", multipart.getOriginalFileName(inputName));
            map.put("absolute-path", uploadFile.getAbsolutePath());
            map.put("file-size", uploadFile.length());
            list.add(map);
            ++index;
        }
        json.addResultMapItem("upload-file-list", list);
        json.setSuccess(true);
        return json;
    }
}
