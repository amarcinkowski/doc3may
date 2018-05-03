package io.github.amarcinkowski.doc3may.processor;
import static java.nio.charset.StandardCharsets.UTF_8;
import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class AppendToFile {

	public static void text(String[] lines) {
		try {
			Files.write(Paths.get("processor.log"), Arrays.asList(lines), UTF_8, APPEND, CREATE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
