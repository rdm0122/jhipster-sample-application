package ar.com.paradigmasantafe.myapp;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("ar.com.paradigmasantafe.myapp");

        noClasses()
            .that()
            .resideInAnyPackage("ar.com.paradigmasantafe.myapp.service..")
            .or()
            .resideInAnyPackage("ar.com.paradigmasantafe.myapp.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..ar.com.paradigmasantafe.myapp.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
