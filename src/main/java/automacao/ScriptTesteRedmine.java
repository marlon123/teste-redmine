package automacao;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ScriptTesteRedmine {

    @FindBy(xpath = "//div[@id='account']//a[@class='register']")
    WebElement botaoCadastro;

    @FindBy(id = "user_login")
    WebElement inputCadastroLogin;

    @FindBy(id = "user_password")
    WebElement inputCadastroSenha;

    @FindBy(id = "user_password_confirmation")
    WebElement inputConfirmacaoSenha;

    @FindBy(id = "user_firstname")
    WebElement inputCadastroNome;

    @FindBy(id = "user_lastname")
    WebElement inputCadastroSobrenome;

    @FindBy(id = "user_mail")
    WebElement inputCadastroEmail;

    @FindBy(id = "Enviar")
    WebElement botaoEnviarCadastro;

    @FindBy(xpath = "//div[@id='top-menu']//a[@class='projects']")
    WebElement menuProjetos;

    @FindBy(xpath = "//div[@class='contextual']//a[contains(text(), 'Novo projeto')]")
    WebElement menuNovoProjeto;

    public ScriptTesteRedmine(){

    }

    public static void main(String[] args){
        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");

    }

    private static WebDriver setConfigurationWebdriver(){
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("http://demo.redmine.org/");
    }

    public void teste() throws Exception{
        
    }
}
