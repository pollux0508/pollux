package com.polluxframework;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.polluxframework.test.AbstractNoTransactionalTest;
import com.polluxframework.test.constant.RequestTypeEnum;
import com.polluxframework.test.entity.TestParam;
import com.polluxframework.test.utils.WebTestSingleUtils;
import org.artofsolving.jodconverter.document.DefaultDocumentFormatRegistry;
import org.artofsolving.jodconverter.office.DefaultOfficeManagerConfiguration;
import org.jodconverter.OfficeDocumentConverter;
import org.jodconverter.office.LocalOfficeManager;
import org.jodconverter.office.OfficeException;
import org.jodconverter.office.OfficeManager;
import org.junit.Test;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhumin0508
 * created in  2018/8/29 15:12
 * modified By:
 */

public class UserControllerTest extends AbstractNoTransactionalTest {

	@Test
	public void getUserList() throws Exception {
		TestParam loginName = new TestParam("loginName");
		loginName.addParam("admin");
		ObjectMapper objectMapper = new HZPageModelMapper();
		System.out.println(objectMapper.writeValueAsString(WebTestSingleUtils.doAction(this.getWebApplicationContext(), objectMapper, "/demo/user/getUserList", RequestTypeEnum.POST, loginName)));
	}

	public void libreOffice2PDF(File inputfile, File outputfile) {
		String LibreOfficeHome = getLibreOfficeHome();
		String fileName = inputfile.getName();
		logger.info(new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date()) + "文件" + inputfile.getName());
		OfficeManager officeManager = LocalOfficeManager.builder().officeHome(LibreOfficeHome).install().build();
		OfficeDocumentConverter converter = new OfficeDocumentConverter(officeManager);
		converter.setDefaultLoadProperties(createDefaultLoadProperties());
		try {
			officeManager.start();
			converter.convert(inputfile,outputfile);
		} catch (OfficeException e) {
			e.printStackTrace();
			logger.info("转换失败");
		} finally {
			try {
				officeManager.stop();
			} catch (OfficeException e) {
				e.printStackTrace();
			}
		}
	}

	public void libreOffice2PDF2(File inputfile, File outputfile) {
		DefaultOfficeManagerConfiguration configuration = new DefaultOfficeManagerConfiguration();
		configuration.setOfficeHome(getLibreOfficeHome());
		configuration.setPortNumber(8100);
		org.artofsolving.jodconverter.office.OfficeManager officeManager = configuration.buildOfficeManager();
		officeManager.start();
		org.artofsolving.jodconverter.OfficeDocumentConverter converter = new org.artofsolving.jodconverter.OfficeDocumentConverter(officeManager, new DefaultDocumentFormatRegistry());
		converter.setDefaultLoadProperties(createDefaultLoadProperties());

		String fileName = inputfile.getName();
		logger.info(new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date()) + "文件" + inputfile.getName());
		converter.convert(inputfile,outputfile);
	}

	private Map<String,Object> createDefaultLoadProperties() {
		Map<String,Object> loadProperties = new HashMap<String,Object>();
		loadProperties.put("Hidden", true);
		loadProperties.put("ReadOnly", true);
		loadProperties.put("UpdateDocMode", 1);
		return loadProperties;
	}
	private String getLibreOfficeHome() {
		return "C:\\Program Files (x86)\\OpenOffice 4\\";
	}


	@Test
	public void libreOffice2PDF(){
		File inputfile = new File("E:\\HZInfoRC\\定制需求\\HZInfo3000-RC河长制信息化项目需求说明书v1.2.doc");
		File outputfile = new File("E:\\HZInfoRC\\定制需求\\HZInfo3000-RC河长制信息化项目需求说明书v1.2.pdf");
		libreOffice2PDF2(inputfile,outputfile);

	}
}