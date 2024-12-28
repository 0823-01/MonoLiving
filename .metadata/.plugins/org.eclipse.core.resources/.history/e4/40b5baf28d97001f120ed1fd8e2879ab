package com.kh.common;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.oreilly.servlet.multipart.FileRenamePolicy;

public class MonoFileRenamePolicy implements FileRenamePolicy {

	public File rename(File noticeFile) {
		
		String noticeFileName = noticeFile.getName();
		
		String currentTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		
		int ranNum = (int)(Math.random() * 90000 + 10000);
		
		String ext = noticeFileName.substring(noticeFileName.lastIndexOf("."));
		
		String noticeUpdateFile = currentTime + ranNum + ext;
		
		return new File(noticeFile.getParent(), noticeUpdateFile);
	}
}
