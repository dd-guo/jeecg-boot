package org.jeecg.common.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * sql注入处理工具类
 * 
 * @author zhoujf
 * @author dd.guo@foxmail.com
 */
@Slf4j
public class SqlInjectionUtil {
	final static Pattern XSS_PATTERN = Pattern.compile("'|and |exec |insert |select |delete |update |drop |count |chr |mid |master |truncate |char |declare |;|or |\\+|,");
	private static final Pattern SPECIAL_XSS_PATTERN =  Pattern.compile("exec |insert |delete |update |drop |chr |mid |master |truncate |char |declare ");

	/**
	 * sql注入过滤处理，遇到注入关键字抛异常
	 * 
	 * @param value
	 * @return
	 */
	public static void filterContent(String value) {
		if (StringUtils.isEmpty(value)){
			return;
		}
		if (XSS_PATTERN.matcher(value.toLowerCase()).matches()){
			log.error("请注意，值可能存在SQL注入风险!---> {}", value);
			throw new RuntimeException("请注意，值可能存在SQL注入风险!--->" + value);
		}
	}

	/**
	 * sql注入过滤处理，遇到注入关键字抛异常
	 * 
	 * @return
	 */
	public static void filterContent(String[] values) {
		Arrays.asList(values).parallelStream().forEach(SqlInjectionUtil::specialFilterContent);
	}

	/**
	 * @特殊方法(不通用) 仅用于字典条件SQL参数，注入过滤
	 * @param value
	 * @return
	 */
	public static void specialFilterContent(String value) {
		if (StringUtils.isEmpty(value)){
			return;
		}
		if (SPECIAL_XSS_PATTERN.matcher(value.toLowerCase()).matches()){
			log.error("请注意，值可能存在SQL注入风险!---> {}", value);
			throw new RuntimeException("请注意，值可能存在SQL注入风险!--->" + value);
		}
	}
}
