# BDD_Lab03
# Parte 1
# Laboratorio de BDD con Selenium, ChromeDriver y Java en GitHub Codespaces

Este laboratorio permite configurar un entorno de desarrollo en GitHub Codespaces para ejecutar pruebas automatizadas con Selenium y Cucumber en un proyecto Java Maven.

## 1. Crear un nuevo repositorio en GitHub
1. Ve a GitHub y crea un nuevo repositorio.
2. Asigna un nombre y una descripción a tu repositorio.

## 2. Configurar GitHub Codespaces
### Crear un nuevo archivo de configuración
1. En la raíz del repositorio, crea la carpeta `.devcontainer`.
2. Dentro de esta carpeta, crea un archivo `devcontainer.json`.
3. Pega la siguiente configuración:

```json
{
  "name": "BDD Lab with Selenium and Java",
  "image": "mcr.microsoft.com/devcontainers/java:17",
  "settings": {
    "terminal.integrated.shell.linux": "/bin/bash"
  },
  "features": {
    "ghcr.io/devcontainers/features/java:1": {
      "version": "17"
    },
    "ghcr.io/devcontainers-extra/features/maven-sdkman:2": {},
    "ghcr.io/devcontainers/features/node:1": {
      "version": "lts"
    },
    "ghcr.io/kreemer/features/chrometesting:1": {}
  },
  "customizations": {
    "vscode": {
      "extensions": [
        "vscjava.vscode-java-pack",
        "vscjava.vscode-maven",
        "dbaeumer.vscode-eslint",
        "ms-python.python",
        "ms-python.vscode-pylance"
      ]
    }
  },
  "forwardPorts": [8080],
  "portsAttributes": {
    "8080": {
      "label": "Application",
      "onAutoForward": "notify"
    }
  },
  "postCreateCommand": "sudo apt update",
  "remoteUser": "vscode"
}
```

4. Confirma los cambios (`Commit changes...`).

## 3. Crear Codespace
1. Ve a la página principal del repositorio y haz clic en `<> Code`.
2. En la pestaña `Codespaces`, haz clic en `Create codespace on main`.

## 4. Creación del Proyecto Maven
Ejecuta el siguiente comando en la terminal de Codespaces, reemplazando `{com.eci.myproject}` y `{bdd-java}` por los valores deseados:

```sh
mvn archetype:generate -DgroupId={com.eci.myproject} -DartifactId={bdd-java} -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
```

Este comando generará la estructura básica del proyecto con las carpetas `src/main/java` y `src/test/java`.

## 5. Agregar dependencias de Selenium y Cucumber
Edita el archivo `pom.xml` y agrega las siguientes dependencias:

```xml
<dependencies>
    <dependency>
        <groupId>org.seleniumhq.selenium</groupId>
        <artifactId>selenium-java</artifactId>
        <version>4.0.0</version>
    </dependency>
    <!-- Cucumber -->
    <dependency>
        <groupId>io.cucumber</groupId>
        <artifactId>cucumber-java</artifactId>
        <version>7.0.0</version>
    </dependency>
    <dependency>
        <groupId>io.cucumber</groupId>
        <artifactId>cucumber-junit</artifactId>
        <version>7.0.0</version>
        <scope>test</scope>
    </dependency>
</dependencies>
```

## 6. Crear la estructura BDD
Dentro de la carpeta `src/test/java`, genera las siguientes subcarpetas:

```sh
mkdir -p src/test/java/features src/test/java/steps src/test/java/runners
```

### 6.1 Crear escenario BDD
En `src/test/java/features`, crea un archivo `google_search.feature` con el siguiente contenido:

```gherkin
Feature: Google Search

  Scenario: Search for a term
    Given I am on the Google search page
    When I search for "GitHub"
    Then I should see "GitHub" in the results
```

### 6.2 Crear los pasos de prueba
En `src/test/java/steps`, crea un archivo `SearchSteps.java` con el siguiente código:

```java
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.concurrent.TimeUnit;

public class SearchSteps {
    private WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless", "--disable-gpu", "--no-sandbox", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Given("I am on the Google search page")
    public void i_am_on_the_google_search_page() {
        driver.get("https://www.google.com");
    }

    @When("I search for {string}")
    public void i_search_for(String term) {
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys(term);
        searchBox.submit();
    }

    @Then("I should see {string} in the results")
    public void i_should_see_in_the_results(String term) {
        assert driver.getPageSource().contains(term);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
```

### 6.3 Crear el Runner de pruebas
En `src/test/java/runners`, crea un archivo `TestRunner.java` con el siguiente código:

```java
import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/features", glue="steps",
monochrome = true,
publish = true,
plugin = {"pretty", "junit:target/JUnitReports/report.xml",
        "json:target/JSonReports/report.json",
        "html:target/HtmlReports/report.html"
        }
)
public class TestRunner {
}
```

## 7. Ejecutar los escenarios de prueba
Ejecuta los escenarios con:

```sh
mvn test
```

## 8. Validar los resultados
Descarga el archivo `target/HtmlReports/report.html` para revisar los reportes de ejecución.

Con estos pasos, el entorno estará listo y funcional en GitHub Codespaces. 🚀

# Parte 2
# BDD con Selenium y Cucumber - Laboratorio

## Descripción
Este proyecto implementa pruebas automatizadas utilizando el patrón **PageFactory** en **Selenium** con **Cucumber** para realizar pruebas en la página de prueba [The Internet](https://the-internet.herokuapp.com/).

## Características
✅ Implementación del patrón **PageFactory**
✅ Uso de **Cucumber** para definir escenarios en lenguaje Gherkin
✅ Pruebas de UI automatizadas con **Selenium WebDriver**
✅ Manejo de distintos escenarios de prueba
✅ Configuración con **Maven** para ejecución eficiente

## 📌 Instalación y Configuración
### **1️⃣ Prerrequisitos**
Asegúrate de tener instalado lo siguiente:
- [Java 17+](https://adoptopenjdk.net/)
- [Maven](https://maven.apache.org/download.cgi)
- [ChromeDriver](https://chromedriver.chromium.org/downloads)

### **2️⃣ Clonar el Repositorio**
```sh
git clone https://github.com/jacro103/BDD_Lab03.git
cd bdd-selenium-lab
```

### **3️⃣ Ejecutar las Pruebas**
Ejecuta todas las pruebas con:
```sh
mvn test
```
O ejecuta un runner específico:
```sh
mvn -Dtest=DragAndDropRunner test
```

## 📝 Escenario Implementado:

Drag and Drop
Add remove
CheckBoxes

## 🚀 Tecnologías Usadas
- **Java 17**
- **Selenium WebDriver**
- **Cucumberk**
- **JUnit 5**
- **Maven**

## Autor

Jose Alejandro Correa Rodriguez


