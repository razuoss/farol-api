package io.github.razuoss.farol_da_fe;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

@AnalyzeClasses(packages = "io.github.razuoss.farol_da_fe")
class ArchitectureTest {

    @ArchTest
    static final ArchRule domain_should_not_depend_on_spring_or_infrastructure = noClasses()
            .that().resideInAPackage("..domain..")
            .should().dependOnClassesThat().resideInAnyPackage(
                    "org.springframework..",
                    "io.github.razuoss.farol_da_fe.infrastructure.."
            )
            .allowEmptyShould(true)
            .because("As classes de domínio devem ser isoladas de frameworks (Spring) e de detalhes de infraestrutura.");
}
