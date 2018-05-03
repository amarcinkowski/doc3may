package io.github.amarcinkowski.doc0;


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.annotation.processing.Processor;
import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileObject.Kind;
import javax.tools.SimpleJavaFileObject;
import javax.tools.StandardJavaFileManager;

import org.apache.commons.io.FileUtils;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.eclipse.jdt.internal.compiler.tool.EclipseCompiler;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableList.Builder;

import io.github.amarcinkowski.doc3may.processor.AppendToFile;
import io.github.amarcinkowski.doc3may.processor.NewIntentProcessor;


/**
 * Says "Hi" to the user.
 *
 */
@Mojo( name = "sayhi")
public class GreetingMojo extends AbstractMojo
{
	
	private final static String fpath = "/home/am/eclipse-workspace1/doc3may/doc3may-maven-plugin/src/main/java/X.java"; 
	private final static String cp = "/home/am/eclipse-workspace1/doc3may/doc3may-annotations/target/doc3may-annotations-0.0.1-SNAPSHOT.jar:/home/am/.m2/repository/org/springframework/spring-web/5.0.5.RELEASE/spring-web-5.0.5.RELEASE.jar";
	private final List<String> options;
	  
	public GreetingMojo() /*throws IOException*/ {
//		    sourceDir = createTempDir("sourceDir");
//		    outputDir = createTempDir("outputDir");

//				DIRM2=/home/am/.m2/repository/
						
		
		    Builder<String> builder = ImmutableList.builder();
//		    builder.add("-classpath").add(buildClassPath(outputDir));
//		    builder.add("-d").add(outputDir.getAbsolutePath());
		    builder.add("-cp").add(cp);
		    builder.add("-proc:only");
		    
		    this.options = builder.build();
		  }
	  
    public void execute() throws MojoExecutionException
    {
        getLog().info( "Hello, world." );
        AppendToFile.text(new String[] {new Date() + " mojo run." });
        try {
        	File f = new File(fpath);
        	getLog().info("FILE: " + f.exists());
			compile(new NewIntentProcessor(), f);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
//    protected File writeSourceFile(SourceFile sourceFile) throws IOException {
//    	   File file = new File(".", "X.java");
//    	   String str = FileUtils.readFileToString(file);
//    	   FileUtils.writeStringToFile(file, str);
//    	   return file;
//    	  }
    private boolean compile(Processor processor, File... sourceFiles)  throws Exception {
//        File[] files = new File[sourceFiles.length];
    	SimpleJavaFileObject compilationUnit = new SimpleJavaFileObject(URI.create(fpath), Kind.SOURCE) {
//    		String fpath = "src/main/java/X.java"; 
//    		URL dir_url = ClassLoader.getSystemResource(dir_path);
    		// Turn the resource into a File object
//    		File dir = new File(dir_url.toURI());
    		
    	    @Override
    	    public CharSequence getCharContent(boolean ignoreEncodingErrors) throws IOException {
    	      return FileUtils.readFileToString(new File(fpath), "UTF-8");
    	    }
    	  };
    	  
//        JavaCompiler c = ToolProvider.getSystemJavaCompiler();
        
        JavaCompiler c = new EclipseCompiler();
        
        StandardJavaFileManager fileManager = c.getStandardFileManager(null, Locale.ROOT, Charset.forName("UTF-8"));

//      Iterable<? extends JavaFileObject> javaFileObjects = fileManager.getJavaFileObjects(files);
        // OPTIONAL
        PrintWriter writer = new PrintWriter(new File("zxc.class"));
        List<String> al = Arrays.asList(new String[]{"Aa","Bb"});
        
        CompilationTask task = c.getTask(writer, fileManager, null, options, null/*al*/, ImmutableList.of(compilationUnit));
//      if (processor != null) {
      task.setProcessors(Arrays.asList(processor));
//    }
      try {
          Boolean success = task.call();
          if (!success) {
          	getLog().error("CANNOT COMPILE FILE ");
          }
          return success;
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
        fileManager.close();
      }
    return false;
    }
    
}