package com.ye.task;

import java.io.IOException;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ye.file.FileUtil;

@Component
public class JobTask {

	@Scheduled(fixedDelay = 30)
	public void task() throws IOException {
		String sourcePath = "c://aa";
		String path = "c://bb";

		FileUtil.copyDir(sourcePath, path);
	}

}
