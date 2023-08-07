// ***************************************************************************
//
//		Copyright 2011, Prisma-IT (www.prisma-it.com)
//		All rights reserved
//
//		$HeadURL$
//		$Id$
//
// ***************************************************************************
package nl.prismait.epublisher.java.core.utils;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileCreator {
	private static final Logger LOG = LoggerFactory.getLogger(FileCreator.class);

	public static File createDir(String location) {

		File directory = new File(location);
		if (!directory.exists()) {
			boolean mkdir = directory.mkdir();
			LOG.debug("Directory created: " + Boolean.toString(mkdir));
		}
		return directory;
	}

	public static File createFile(String pathname) throws IOException {
		File newFile = new File(pathname);

		if (!newFile.exists())
			newFile.createNewFile();
		return newFile;

	}

	public static boolean remove(String tempDir) {
		File file = new File(tempDir);
		if (file.isDirectory()) {
			return deleteDirectory(file);
		}
		return file.delete();
	}

	private static boolean deleteDirectory(File path) {
		if (path.exists()) {
			File[] files = path.listFiles();
			for (int i = 0; i < files.length; i++) {
				if (files[i].isDirectory()) {
					deleteDirectory(files[i]);
				} else {
					files[i].delete();
				}
			}
		}
		return (path.delete());
	}
}
