package automacao;

import java.util.List;

//import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ScriptTesteRedmine {

    private static WebElement botaoCadastro;
    private static WebElement inputCadastroLogin;
    private static WebElement inputCadastroSenha;
    private static WebElement inputConfirmacaoSenha;
    private static WebElement inputCadastroNome;
    private static WebElement inputCadastroSobrenome;
    private static WebElement inputCadastroEmail;
    private static WebElement botaoEnviarCadastro;
    private static WebElement menuProjetos;
    private static WebElement menuNovoProjeto;

    private static WebElement inputCadastroNomeProjeto;
    private static WebElement inputCadastroDescricaoProjeto;
    private static WebElement checkBoxTipoTarefaFeature;
    private static WebElement checkBoxTipoTarefaSupport;
    private static WebElement botaoCriarProjeto;

    private static WebElement menuProjetoCriado;
    private static WebElement abaNovaTarefa;

    private static WebElement inputTituloTarefa;
    private static WebElement inputDescricaoTarefa;
    private static WebElement botaoCriarTarefaEContinuar;

    private static WebElement abaTarefas;

    private static final Logger log = LoggerFactory.getLogger(ScriptTesteRedmine.class);
    //private static final Logger log = java.util.logging.Logger.getLogger()

     public static void main(String[] args){
        //log = LoggerFactory.getLogger(ScriptTesteRedmine.class);

        log.info("Iniciando chromedriver");
        System.setProperty("webdriver.chrome.driver", "chromedriver");
         //System.setProperty("webdriver.gecko.driver", "geckodriver");
         try {
             teste();
         } catch (Exception e) {
             e.printStackTrace();
         }

     }

    private static WebDriver setConfigurationWebdriver(){
        WebDriver driver = new ChromeDriver();
        //WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("http://demo.redmine.org/");
        return driver;
    }

    public static void teste() throws Exception{
        WebDriver driver = setConfigurationWebdriver();

        acessarTelaCadastroUsuario(driver);

        preencherFormularioCadastroUsuario(driver);

        acessarTelaProjetos(driver);

        //criarNovoProjeto(driver);

        //acessarTelaProjetos(driver);

        acessarTelaNovaTarefa(driver);

        criarTarefas(driver);

        visualizarTarefas(driver);

        paginarGridTarefas(driver);

        validarTarefaCadastrada(driver);

        Thread.sleep(30000);
        driver.quit();
    }

    private static void acessarTelaCadastroUsuario(WebDriver driver) throws  Exception{
         log.info("Acessando tela de cadastro de usuário");

        botaoCadastro = driver.findElement(By.xpath("//div[@id='account']//a[@class='register']"));
        botaoCadastro.click();
    }

    private static void preencherFormularioCadastroUsuario(WebDriver driver) throws Exception{
        log.info("Preenchendo formulário de cadastro de usuário");

        inputCadastroLogin = driver.findElement(By.id("user_login"));
        inputCadastroLogin.sendKeys("Testessss");

        inputCadastroSenha = driver.findElement(By.id("user_password"));
        inputCadastroSenha.sendKeys("123456");

        inputConfirmacaoSenha = driver.findElement(By.id("user_password_confirmation"));
        inputConfirmacaoSenha.sendKeys("123456");

        inputCadastroNome = driver.findElement(By.id("user_firstname"));
        inputCadastroNome.sendKeys("Redmine");

        inputCadastroSobrenome = driver.findElement(By.id("user_lastname"));
        inputCadastroSobrenome.sendKeys("Silva");

        inputCadastroEmail = driver.findElement(By.id("user_mail"));
        inputCadastroEmail.sendKeys("teste05@gmail.com");

        botaoEnviarCadastro = driver.findElement(By.name("commit"));
        botaoEnviarCadastro.click();
    }

    private static void acessarTelaProjetos(WebDriver driver) throws Exception{
        log.info("Acessando tela de projetos");
        menuProjetos = driver.findElement(By.xpath("//div[@id='top-menu']//a[@class='projects']"));
        menuProjetos.click();
    }

    private static void criarNovoProjeto(WebDriver driver) throws Exception{
        log.info("Criando novo projeto");

        menuNovoProjeto = driver.findElement(By.xpath("//div[@class='contextual']//a[contains(text(), 'Novo projeto')]"));
        menuNovoProjeto.click();
        
        inputCadastroNomeProjeto = driver.findElement(By.id("project_name"));
        inputCadastroNomeProjeto.sendKeys("Automação Redmine");

        inputCadastroDescricaoProjeto = driver.findElement(By.id("project_description"));
        inputCadastroDescricaoProjeto.sendKeys("Testes automatizados no redmine");

        checkBoxTipoTarefaFeature = driver.findElement(By.xpath("//fieldset[@id='project_trackers']//label[2]//input"));
        checkBoxTipoTarefaFeature.click();

        checkBoxTipoTarefaSupport = driver.findElement(By.xpath("//fieldset[@id='project_trackers']//label[3]//input"));
        checkBoxTipoTarefaSupport.click();

        botaoCriarProjeto = driver.findElement(By.name("commit"));
        botaoCriarProjeto.click();

    }

    private static void acessarTelaNovaTarefa(WebDriver driver) throws Exception{
        log.info("Acessando tela nova tarefa");

         menuProjetoCriado = driver.findElement(By.xpath("//a[contains(text(), 'Automação Redmine')]"));
        menuProjetoCriado.click();

        abaNovaTarefa = driver.findElement(By.xpath("//div[@id='main-menu']//a[contains(text(), 'Nova tarefa')]"));
        abaNovaTarefa.click();
    }

    private static void criarTarefas(WebDriver driver) throws Exception{
        log.info("Criando tarefas");

        inputTituloTarefa = driver.findElement(By.id("issue_subject"));
        inputDescricaoTarefa = driver.findElement(By.id("issue_description"));
        botaoCriarTarefaEContinuar = driver.findElement(By.name("continue"));
        WebDriverWait aguardar = new WebDriverWait(driver, 10);
        
        String jsonArray = JsonFileReader.getFileAsString("tarefaRedmine.json");
        List<TarefaRedmineDTO> maps = JsonUtils.convertStringToObjectList(jsonArray, TarefaRedmineDTO.class);

        System.out.println("CADASTRO DE TAREFAS");

        for (TarefaRedmineDTO p : maps) {
            //inputTituloTarefa.wait(2000);
            try {
                inputTituloTarefa.sendKeys(p.getTituloTarefa());
            }catch (org.openqa.selenium.StaleElementReferenceException ex){
                inputTituloTarefa.sendKeys(p.getTituloTarefa());
            }
            inputDescricaoTarefa.sendKeys(p.getDescricaoTarefa());
            botaoCriarTarefaEContinuar.click();

            log.info("Tarefa criada");
            //aguardar.wait(2000);
            //aguardar.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(inputTituloTarefa, "Erro")));
        }
    }

    private static void visualizarTarefas(WebDriver driver) throws Exception{
        abaTarefas = driver.findElement(By.xpath("//div[@id='main-menu']//a[contains(text(), 'Tarefa')]"));
    }

    private static void paginarGridTarefas(WebDriver driver) throws Exception{

    }

    private static void validarTarefaCadastrada(WebDriver driver) throws Exception{

    }
}
