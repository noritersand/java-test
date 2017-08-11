package laboratory.servlet.controller.fileupload;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import laboratory.servlet.bean.JsonResponseObject;
import laboratory.servlet.mvc.finder.UrlMapping;
import laboratory.servlet.view.View;

/**
 * com.oreilly 패키지를 이용한 파일 업로드
 * 
 * @since 2017-08-11
 * @author fixalot
 */
public class FileUploadWithOreilly {
	private static final Logger logger = LoggerFactory.getLogger(FileUploadWithOreilly.class);

	@UrlMapping("/test/fileupload/with-oreilly.view")
	public View drawWithOreilly(HttpServletRequest request, HttpServletResponse response) {
		return new View(request);
	}

	@UrlMapping("/test/fileupload/upload-with-oreilly.data")
	public JsonResponseObject uploadWithOreiily(HttpServletRequest request, HttpServletResponse response) throws IOException {
		JsonResponseObject json = new JsonResponseObject();

//		final String pathname = request.getSession().getServletContext().getRealPath("/") + "upload";
		File uploadPath = Paths.get("c:/laboratory/upload").toFile();
		if (!uploadPath.exists()) {
			// 폴더가 존재하지 않으면 폴더 생성
			uploadPath.mkdirs();
		}

		final String encType = StandardCharsets.UTF_8.name();
		final int maxFilesize = 20 * 1024 * 1024; // 20MB

		// MultipartRequest(request, 저장경로[, 최대허용크기, 인코딩케릭터셋, 동일한 파일명 보호 여부]), 이 함수가 호출됨과 동시에 업로드 됨.
		MultipartRequest multipart
				= new MultipartRequest(request, uploadPath.toString(), maxFilesize, encType, new DefaultFileRenamePolicy());

		logger.debug("title: {}", multipart.getParameter("title")); // 입력된 문자

		Enumeration<?> inputNames = multipart.getFileNames(); // 모든 파일 타입 인풋의 이름 반환
		int index = 0;
		List<Map<String, Object>> list = new LinkedList<>();
		while (inputNames.hasMoreElements()) {
			final String inputName = (String) inputNames.nextElement();
			final File uploadFile = multipart.getFile(inputName);
			if (uploadFile == null) {
				continue;
			}
			Map<String, Object> map = new HashMap<String, Object>();
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
