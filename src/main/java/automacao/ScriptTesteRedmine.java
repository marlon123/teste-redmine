package automacao;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

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

     public static void main(String[] args){
        System.setProperty("webdriver.chrome.driver", "chromedriver");
         try {
             teste();
         } catch (Exception e) {
             e.printStackTrace();
         }

     }

    private static WebDriver setConfigurationWebdriver(){
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://demo.redmine.org/");
        return driver;
    }

    public static void teste() throws Exception{
        WebDriver driver = setConfigurationWebdriver();

        botaoCadastro = driver.findElement(By.xpath("//div[@id='account']//a[@class='register']"));
        botaoCadastro.click();

        preencherTelaCadastro(driver);

        acessarTelaProjetos(driver);

        criarNovoProjeto(driver);

        acessarTelaProjetos(driver);

        acessarTelaNovaTarefa(driver);

        criarTarefa(driver);

        Thread.sleep(30000);
        driver.quit();
    }

    private static void preencherTelaCadastro(WebDriver driver) throws Exception{
        inputCadastroLogin = driver.findElement(By.id("user_login"));
        inputCadastroLogin.sendKeys("Teste");

        inputCadastroSenha = driver.findElement(By.id("user_password"));
        inputCadastroSenha.sendKeys("123456");

        inputConfirmacaoSenha = driver.findElement(By.id("user_password_confirmation"));
        inputConfirmacaoSenha.sendKeys("123456");

        inputCadastroNome = driver.findElement(By.id("user_firstname"));
        inputCadastroNome.sendKeys("Redmine");

        inputCadastroSobrenome = driver.findElement(By.id("user_lastname"));
        inputCadastroSobrenome.sendKeys("Silva");

        inputCadastroEmail = driver.findElement(By.id("user_mail"));
        inputCadastroEmail.sendKeys("teste@gmail.com");

        botaoEnviarCadastro = driver.findElement(By.name("commit"));
        botaoEnviarCadastro.click();
    }

    private static void acessarTelaProjetos(WebDriver driver) throws Exception{
        menuProjetos = driver.findElement(By.xpath("//div[@id='top-menu']//a[@class='projects']"));
        menuProjetos.click();
    }

    private static void criarNovoProjeto(WebDriver driver) throws Exception{
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
        menuProjetoCriado = driver.findElement(By.xpath("//a[contains(text(), 'Automação Redmine')]"));
        menuProjetoCriado.click();

        abaNovaTarefa = driver.findElement(By.xpath("//div[@id='main-menu']//a[contains(text(), 'Nova tarefa')]"));
        abaNovaTarefa.click();
    }

    private static void criarTarefa(WebDriver driver) throws Exception{
        inputTituloTarefa = driver.findElement(By.id("issue_subject"));
        inputTituloTarefa.sendKeys("");

        inputDescricaoTarefa = driver.findElement(By.id("issue_description"));
        inputDescricaoTarefa.sendKeys("");
    }
}
