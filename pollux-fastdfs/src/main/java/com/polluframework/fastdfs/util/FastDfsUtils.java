package com.polluframework.fastdfs.util;

import com.polluframework.fastdfs.exception.FastDfsException;
import com.polluframework.fastdfs.support.ErrorCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhumin0508
 * created in  2018/10/31 10:10
 * modified By:
 */
public class FastDfsUtils {
	private FastDfsUtils() {
	}

	private static Logger logger = LoggerFactory.getLogger(FastDfsUtils.class);

	public static final Map<String, String> EXT_MAPS = new HashMap<>();
	/**
	 * 文件名称Key
	 */
	private static final String FILE_NAME = "filename";

	/**
	 * 文件最大的大小
	 */
	private static final int MAX_FILE_SIZE = 100 * 1024 * 1024;

	static {
		// image
		EXT_MAPS.put("png", "image/png");
		EXT_MAPS.put("gif", "image/gif");
		EXT_MAPS.put("bmp", "image/bmp");
		EXT_MAPS.put("ico", "image/x-ico");
		EXT_MAPS.put("jpeg", "image/jpeg");
		EXT_MAPS.put("jpg", "image/jpeg");
		// 压缩文件
		EXT_MAPS.put("zip", "application/zip");
		EXT_MAPS.put("rar", "application/x-rar");
		// 文件
		EXT_MAPS.put("pdf", "application/pdf");
		EXT_MAPS.put("ppt", "application/vnd.ms-powerpoint");
		EXT_MAPS.put("xls", "application/vnd.ms-excel");
		EXT_MAPS.put("xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		EXT_MAPS.put("pptx", "application/vnd.openxmlformats-officedocument.presentationml.presentation");
		EXT_MAPS.put("doc", "application/wps-office.doc");
		EXT_MAPS.put("docx", "application/vnd.openxmlformats-officedocument.wordprocessingml.document");
		EXT_MAPS.put("txt", "text/plain");
		// 音频
		EXT_MAPS.put("mp4", "video/mp4");
		EXT_MAPS.put("flv", "video/x-flv");
	}

	public static String uploadFileWithMultipart(MultipartFile file) throws FastDfsException {
		return upload(file, null);
	}

	public String uploadFileWithMultipart(MultipartFile file, Map<String, String> descriptions) throws FastDfsException {
		return upload(file, descriptions);
	}

	public String upload(MultipartFile file, Map<String, String> descriptions) throws FastDfsException {
		if (file == null || file.isEmpty()) {
			throw new FastDfsException(ErrorCode.FILE_ISNULL.CODE, ErrorCode.FILE_ISNULL.MESSAGE);
		}
		String path = null;
		try {
			path = upload(file.getInputStream(), file.getOriginalFilename(), descriptions);
		} catch (IOException e) {
			e.printStackTrace();
			throw new FastDfsException(ErrorCode.FILE_ISNULL.CODE, ErrorCode.FILE_ISNULL.MESSAGE);
		}
		return path;
	}
}
