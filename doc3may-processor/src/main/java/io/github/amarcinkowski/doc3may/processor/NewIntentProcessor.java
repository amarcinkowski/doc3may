package io.github.amarcinkowski.doc3may.processor;

import java.util.Arrays;
import java.util.Date;
import java.util.Set;
import java.util.Vector;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;

import org.springframework.cglib.reflect.MethodDelegate;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.common.collect.ImmutableSet;

import io.github.amarcinkowski.doc3may.annotations.ApiOperation;

public class NewIntentProcessor extends AbstractProcessor {


	@Override
	public synchronized void init(ProcessingEnvironment processingEnv) {
		String s =new Date() + " init";
		System.out.println(s);
		AppendToFile.text(new String[] {s});
	}

	@Override
	public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnv) {
		Vector<Element> elements = new Vector<>(roundEnv.getElementsAnnotatedWith(ApiOperation.class));
		for (Element element : elements) {
//
//			if (element.getKind() != ElementKind.) {
//				messager.printMessage(Diagnostic.Kind.ERROR, "Can be applied to class.");
//				return true;
//			}
//
//			TypeElement typeElement = (TypeElement) element;
			String s = new Date() + " Element " + element.getSimpleName().toString();
			s += " CLASS:" + element.getClass().getSimpleName();
			s += " PATH:" + Arrays.toString(element.getAnnotation(RequestMapping.class).value());
			s += " MET:" + Arrays.toString(element.getAnnotation(RequestMapping.class).method());
			s += " VAL:" + element.getAnnotation(ApiOperation.class).value();
			s += " NOTES:" + element.getAnnotation(ApiOperation.class).notes();
			System.out.println(s);
			AppendToFile.text(new String[] {s});
//			activitiesWithPackage.put(typeElement.getSimpleName().toString(),
//					elements.getPackageOf(typeElement).getQualifiedName().toString());
		}
		return true;
	}

	@Override
	public Set<String> getSupportedAnnotationTypes() {
		return ImmutableSet.of(ApiOperation.class.getCanonicalName());
	}

	@Override
	public SourceVersion getSupportedSourceVersion() {
		return SourceVersion.latestSupported();
	}
}