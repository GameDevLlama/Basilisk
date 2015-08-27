package com.llama.basilisk;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;

import static javax.lang.model.element.Modifier.PUBLIC;

public class BasiliskProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

        for (TypeElement t : annotations) {
            //processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, t.getQualifiedName());
            try {
                this.brewJava(t.getQualifiedName().toString()).writeTo(processingEnv.getFiler());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return true;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> types = new LinkedHashSet<>();
        //types.add(BindModel.class.getCanonicalName());
        return types;
    }

    JavaFile brewJava(final String className) {
        TypeSpec.Builder result = TypeSpec.classBuilder(className)
                .addModifiers(PUBLIC);
        //.addTypeVariable(TypeVariableName.get("T", ClassName.bestGuess(targetClass)));

        /*if (parentViewBinder != null) {
            result.superclass(ParameterizedTypeName.get(ClassName.bestGuess(parentViewBinder),
                    TypeVariableName.get("T")));
        } else {
            result.addSuperinterface(ParameterizedTypeName.get(VIEW_BINDER, TypeVariableName.get("T")));
        }

        result.addMethod(createBindMethod());
        result.addMethod(createUnbindMethod());*/

        return JavaFile.builder("com.llama", result.build())
                .addFileComment("Generated code from Butter Knife. Do not modify!")
                .build();
    }

}
