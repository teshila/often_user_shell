package com.ye.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class FileUtil {

	public static void copyDir(String sourcePath, String newPath) throws IOException {
		File file = new File(sourcePath);
		String[] filePath = file.list();

		if (!(new File(newPath)).exists()) {
			(new File(newPath)).mkdir();
		}

		for (int i = 0; i < filePath.length; i++) {
			if ((new File(sourcePath + file.separator + filePath[i])).isDirectory()) {
				copyDir(sourcePath + file.separator + filePath[i], newPath + file.separator + filePath[i]);
			}

			if (new File(sourcePath + file.separator + filePath[i]).isFile()) {
				copyFile(sourcePath + file.separator + filePath[i], newPath + file.separator + filePath[i]);
			}

		}
	}

	public static void copyFile(String oldPath, String newPath) throws IOException {
		File oldFile = new File(oldPath);
		File file = new File(newPath);
		FileInputStream in = new FileInputStream(oldFile);
		FileOutputStream out = new FileOutputStream(file);
		;
		try {
			byte[] buffer = new byte[2097152];
			int readByte = 0;
			while ((readByte = in.read(buffer)) != -1) {
				out.write(buffer, 0, readByte);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			in.close();
			out.close();
		}

	}

	public static void main(String[] args) throws IOException {

		String sourcePath = "D://aa";
		String path = "D://bb";

		copyDir(sourcePath, path);
		System.out.println("拷贝完成");
	}

}
