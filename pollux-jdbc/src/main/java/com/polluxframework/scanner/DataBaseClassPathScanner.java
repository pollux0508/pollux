package com.polluxframework.scanner;

import com.polluxframework.service.AbstractTable;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.env.Environment;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.TypeFilter;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.Set;

/**
 * @author zhumin0508
 * created in  2018/8/13 8:26
 * modified By:
 */
public class DataBaseClassPathScanner extends ClassPathBeanDefinitionScanner {
	private Class<? extends Annotation> annotationClass;
	private ApplicationContext applicationContext;

	public DataBaseClassPathScanner(BeanDefinitionRegistry registry) {
		super(registry);
	}

	public DataBaseClassPathScanner(BeanDefinitionRegistry registry, boolean useDefaultFilters) {
		super(registry, useDefaultFilters);
	}

	public DataBaseClassPathScanner(BeanDefinitionRegistry registry, boolean useDefaultFilters, Environment environment) {
		super(registry, useDefaultFilters, environment);
	}

	/**
	 * 注册拦截器 那些类可以扫描到
	 */
	public void registerFilters() {
		if (this.annotationClass != null) {
			addIncludeFilter(new AnnotationTypeFilter(this.annotationClass));
		}
		addExcludeFilter(new TypeFilter() {
			@Override
			public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
				String className = metadataReader.getClassMetadata().getClassName();
				return className.endsWith("package-info");
			}
		});
	}

	@Override
	public int scan(String... basePackages) {
		int beanCountAtScanStart = this.getRegistry().getBeanDefinitionCount();
		Set<BeanDefinitionHolder> definitionHolders = doScan(basePackages);
		for (BeanDefinitionHolder bean : definitionHolders) {
			Object object = applicationContext.getBean(bean.getBeanName());
			if (object instanceof AbstractTable) {
				DataBaseScanner.addDataBaseScannerTable((AbstractTable) object);
			}
		}
		return (this.getRegistry().getBeanDefinitionCount() - beanCountAtScanStart);
	}

	public void setAnnotationClass(Class<? extends Annotation> annotationClass) {
		this.annotationClass = annotationClass;
	}


	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

}
