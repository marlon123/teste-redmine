package automacao;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static automacao.DemoRedminePage.*;

public class ScriptTesteRedmine {

    private static DemoRedminePage demoRedminePage;
    private static final Logger log = LoggerFactory.getLogger(ScriptTesteRedmine.class);
    private static Map<String, Object> mapInfo = new HashMap();

     public static void main(String[] args){
         String navegador = "";
         try{
             if (Objects.isNull(args) || args.length == 0) {
                 mapInfo.put("Mensagem", "Nenhum parametro informado");
                 throw new Exception("Nenhum parametro informado");
             }
             navegador = args[0].trim();

             if (navegador.contains("firefox")){
                 log.info("Iniciando geckodriver");
                 System.setProperty("webdriver.gecko.driver", "geckodriver");
                 WebDriver driver = new FirefoxDriver();
                 teste(driver);
                 driver.quit();
             } else {
                 log.info("Iniciando chromedriver");
                 System.setProperty("webdriver.chrome.driver", "chromedriver");
                 WebDriver driver = new ChromeDriver();
                 teste(driver);
             }
         }catch (Exception e){
             log.error(e.getMessage());
         }

     }

    private static void setConfigurationWebdriver(WebDriver driver){
        driver.manage().window().maximize();
        driver.get("http://demo.redmine.org/");
    }

    public static void teste(WebDriver driver) throws Exception{
        setConfigurationWebdriver(driver);

        acessarTelaCadastroUsuario(driver);

        preencherFormularioCadastroUsuario(driver);

        acessarTelaProjetos(driver);

        criarNovoProjeto(driver);

        acessarTelaProjetos(driver);

        acessarTelaNovaTarefa(driver);

        criarTarefas(driver);

        visualizarTarefas(driver);

        paginarGridTarefas(driver);

        validarTarefaCadastrada(driver);
    }

}
