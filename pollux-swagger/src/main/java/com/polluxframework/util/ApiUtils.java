package com.polluxframework.util;

import com.polluxframework.entity.Model;
import com.polluxframework.entity.ModelProperty;
import io.swagger.annotations.ApiModel;
import org.reflections.Reflections;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author zhumin0508
 * created in  2018/11/9 14:50
 * modified By:
 */
public class ApiUtils {

	public static Map<String,Model> getModels(String packageName){
		Reflections reflections = new Reflections(packageName);
		Set<Class<?>> classesList = reflections.getTypesAnnotatedWith(ApiModel.class);
		for (Class classes : classesList) {
			ApiModel apiModel = (ApiModel) classes.getAnnotation(ApiModel.class);
			System.out.println(apiModel.value());
			System.out.println(apiModel.description());
		}
		return null;
	}

	public static List<ModelProperty> getModelProperty(Class<?> cls ){

		return null;
	}
}
