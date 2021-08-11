package br.com.pedrazzani.arch;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;
import static com.tngtech.archunit.library.dependencies.SlicesRuleDefinition.slices;

@AnalyzeClasses(packages = "br.com.pedrazzani.arch")
public class ArchitectureTest {

    @com.tngtech.archunit.junit.ArchTest
    public static final ArchRule layersAccess = layeredArchitecture()
            .layer("Controller").definedBy("..controller..")
            .layer("Service").definedBy("..service..")
            .layer("Repository").definedBy("..repository..")
            .whereLayer("Controller").mayNotBeAccessedByAnyLayer()
            .whereLayer("Service").mayOnlyBeAccessedByLayers("Controller")
            .whereLayer("Repository").mayOnlyBeAccessedByLayers("Service");

    @com.tngtech.archunit.junit.ArchTest
    public static final ArchRule controllerAccess = noClasses()
            .that().resideInAPackage("..controller..")
            .should().dependOnClassesThat()
            .resideInAPackage("..controller..");

    @com.tngtech.archunit.junit.ArchTest
    public static final ArchRule controllerClassNameTest = classes()
            .that().resideInAPackage("..controller..")
            .should().haveSimpleNameEndingWith("Controller");

    @com.tngtech.archunit.junit.ArchTest
    public static final ArchRule serviceClassNameTest = classes()
            .that().resideInAPackage("..service.")
            .should().haveSimpleNameEndingWith("Service");

    @com.tngtech.archunit.junit.ArchTest
    public static final ArchRule serviceImpClassNameTest = classes()
            .that().resideInAPackage("..service.imp")
            .should().haveSimpleNameEndingWith("ServiceImp");

    @com.tngtech.archunit.junit.ArchTest
    public static final ArchRule repositoryClassNameTest = classes()
            .that().resideInAPackage("..repository.")
            .should().haveSimpleNameEndingWith("Repository");

    @com.tngtech.archunit.junit.ArchTest
    public static final ArchRule repositoryImpClassNameTest = classes()
            .that().resideInAPackage("..repository.imp")
            .should().haveSimpleNameEndingWith("RepositoryImp");

    @com.tngtech.archunit.junit.ArchTest
    public static final ArchRule cycleRule = slices()
            .matching("br.com.pedrazzani.arch.(*)..")
            .should().beFreeOfCycles();

}