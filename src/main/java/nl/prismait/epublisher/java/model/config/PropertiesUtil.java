package nl.prismait.epublisher.java.model.config;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.stereotype.Component;

@SuppressWarnings("deprecation")
@Component
public class PropertiesUtil extends PropertyPlaceholderConfigurer {

	private static Map<String, String> propertiesMap = new HashMap<String, String>();

	@Override
	protected void processProperties(ConfigurableListableBeanFactory beanFactory, Properties props) throws BeansException {

		super.processProperties(beanFactory, props);

		propertiesMap.clear();

		for (Object key : props.keySet()) {

			String keyStr = key.toString();

			propertiesMap.put(keyStr, props.getProperty(keyStr));

		}

	}

	public static String getProperty(String name) {

		return propertiesMap.get(name);

	}

}
