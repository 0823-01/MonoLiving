package com.kh.common;

import java.io.File;
import java.util.Date;
import java.text.SimpleDateFormat;

import com.oreilly.servlet.multipart.FileRenamePolicy;

public class MyFileRenamePolicy implements FileRenamePolicy {

	@Override
	public File rename(File originFile) {
		
		// 0. 원본 파일명
		String originName = originFile.getName();
		
		// 1. 파일이 업로드된 시간 (년월일시분초)
		String currentTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());		
		
		// 2. 5자리 랜덤 수 (10000 ~ 99999)
		int ranNum = (int)(Math.random() * 90000 + 10000);
		
		// 3. 확장자명
		String ext = originName.substring(originName.lastIndexOf("."));
		
		// 수정된 파일명
		String changeName = currentTime + ranNum + ext;
		
		return new File(originFile.getParent(), changeName);
	}

	
}
