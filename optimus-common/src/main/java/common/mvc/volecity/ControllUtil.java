package common.mvc.volecity;

import common.mvc.spring.ControllInterface;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

public class ControllUtil {

	private static Map<String, ControllInterface> maps = new HashMap<String, ControllInterface>();

	private static String rootPath = null;

	@Autowired(required = false)
	private VelocityToolInterface velocityTool;

	public static void putPaths(String path, ControllInterface controllObject) {
		maps.put(path, controllObject);
	}

	public String include(String vmPath, Object... args) {
		Map<String, Object> context = new HashMap<String, Object>();
		// 通用参数
		if (velocityTool != null) {
			velocityTool.fillMap(context);
		}

		if (maps.get(vmPath) != null) {
			ControllInterface obj = maps.get(vmPath);
			obj.execute(context);
		}

		if (args != null && args.length % 2 == 0) {
			for (int i = 0; i < args.length; i += 2) {
				context.put(String.valueOf(args[i]), args[i + 1]);
			}
		}


		String s = MvcVelocityHelper.render(rootPath + vmPath, context);
		return s;
	}

	public void setRootPath(String rootPath) {
		rootPath = rootPath + "/controll";
		ControllUtil.rootPath = rootPath;
	}

	/**
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("date", "2010-04-27");
		map.put("weather", "晴朗");

		Reader reader = new FileReader("D:\\Documents\\Desktop\\xxx.txt");

		String s = MvcVelocityHelper.render("D:\\Documents\\Desktop\\xxx.txt", map);

		System.out.println(s);
	}
}
