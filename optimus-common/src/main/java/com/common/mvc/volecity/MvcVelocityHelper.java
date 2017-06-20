package com.common.mvc.volecity;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.context.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.CharArrayWriter;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Writer;
import java.util.Map;

public class MvcVelocityHelper {

	private static Logger log = LoggerFactory.getLogger(MvcVelocityHelper.class);

	/** 单态实例 */
	private static final MvcVelocityHelper instance = new MvcVelocityHelper();

	/** 私有构造函数 */
	private MvcVelocityHelper() {

		// 初始化
		try {
			Velocity.init();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * <pre>
	 * 
	 * 取得实例
	 * </pre>
	 */
	public static MvcVelocityHelper getInstance() {
		return instance;
	}

	/**
	 * <pre>
	 *  
	 * 渲染：从reader到writer
	 * </pre>
	 * 
	 * @param context
	 * @param writer
	 * @param reader
	 * @return
	 */
	private boolean evaluate(Context context, Writer writer, Reader reader) {
		try {
			return Velocity.evaluate(context, writer, "", reader);
		} catch (Exception e) {
			throw new RuntimeException("velocity evaluate error! detail [" + e.getMessage() + "]");
		}
	}

	/**
	 * <pre>
	 * 
	 * 通过Map过滤一个输入流
	 * </pre>
	 * 
	 * @param map
	 * @param reader
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private InputStream evaluate(Map map, Reader reader) {
		try {
			// 把产生的输出流(字符流)，转换成输入流(字节流)
			byte[] dataBytes = this.evaluateToWriter(map, reader).toString().getBytes();
			return new ByteArrayInputStream(dataBytes);
		} catch (Exception e) {
			throw new RuntimeException("velocity evaluate error! detial [" + e.getMessage() + "]");
		}
	}

	/**
	 * <pre>
	 * 
	 * 通过Map过滤一个输入流
	 * </pre>
	 * 
	 * @param map
	 * @param reader
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Writer evaluateToWriter(Map map, Reader reader) {
		try {
			VelocityContext context = convertVelocityContext(map);
			CharArrayWriter writer = new CharArrayWriter();
			this.evaluate(context, writer, reader);

			return writer;
		} catch (Exception e) {
			throw new RuntimeException("velocity evaluate error! detail [" + e.getMessage() + "]");
		}
	}

	/**
	 * <pre>
	 * 
	 * 把Map转换成Context
	 * </pre>
	 */
	private VelocityContext convertVelocityContext(Map<String, Object> map) {
		VelocityContext context = new VelocityContext();
		if (map == null) {
			return context;
		}
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			context.put(entry.getKey(), entry.getValue());
		}
		return context;
	}

	public static String render(String filePath, Map<String, Object> params) {
		try {
			Reader reader = new InputStreamReader(new FileInputStream(filePath), "utf-8");
			Writer writer = MvcVelocityHelper.getInstance().evaluateToWriter(params, reader);
			return writer.toString();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}

		return null;
	}

}