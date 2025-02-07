package cn.sakuraex.sakuraexplug.image;

import cn.sakuraex.sakuraexplug.config.Config;
import net.mamoe.mirai.utils.MiraiLogger;

import java.io.File;
import java.util.List;
import java.util.Map;

public class ImgFolder {
	
	public static void createImgFolder(File imgFolder, MiraiLogger logger) {
		//总图片缓冲区
		if (createFolder(imgFolder)) {
			logger.info("ImgFolder: " + imgFolder.getPath());
		} else {
			logger.info("Create ImgFolder: " + imgFolder.getPath());
		}
		//图片分类文件夹
		for (Map.Entry<String, List<String>> entry : Config.INSTANCE.imageAPIs.get().entrySet()) {
			extendFolder(imgFolder, entry.getKey(), logger);
		}
	}
	
	private static boolean createFolder(File folder) {
		if (folder.exists()) {
			return true;
		} else {
			return !folder.mkdirs();
		}
	}
	
	private static void extendFolder(File folder, String name, MiraiLogger logger) {
		File nextFolder = new File(folder, name);
		String tips = capitalizeFirstLetter(name) + "Folder: " + nextFolder.getPath();
		if (createFolder(nextFolder)) {
			logger.info(tips);
		} else {
			logger.info("Create " + tips);
		}
	}
	
	public static String capitalizeFirstLetter(String origin) {
		StringBuilder word = new StringBuilder(origin.toLowerCase());
		char firstLetter = word.charAt(0);
		word.replace(0, 1, String.valueOf(firstLetter));
		return word.toString();
	}
}

