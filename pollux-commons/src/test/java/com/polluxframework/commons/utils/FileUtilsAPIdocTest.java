package com.polluxframework.commons.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.polluxframework.commons.entity.Api;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhumin0508
 * created in  2018/9/14 10:54
 * modified By:
 */
public class FileUtilsAPIdocTest {
	/**
	 * @throws IOException
	 */
	@Test
	public void test() throws IOException {
		List<File> collected = new ArrayList<>();
		File file = new File("E:\\Inspect\\hzinfo-inspect\\inspect-ams\\src\\main\\java\\com\\hnac\\hzinfo");
		List<String> includes = new ArrayList<>();
		includes.add("java");
		FileUtils.collectFiles(collected, file, includes);
		for (File file1 : collected) {
			List<Api> apis =AnnotationUtils.getMethodAnnotation(file1);
			for(Api api1:apis){
				System.out.println(api1);
				ObjectMapper objectMapper = new ObjectMapper();
				System.out.println(objectMapper.writeValueAsString(api1));
			}
		}

	}
}
