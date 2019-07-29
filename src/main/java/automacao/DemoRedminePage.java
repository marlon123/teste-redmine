package automacao;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static automacao.DriverFactory.getDriver;

public class DemoRedminePage {
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
    private static WebElement gridTarefasColunaTituloTarefa;
    private static WebElement gridTarefasColunaPrioridade;

    private static WebElement gridTarefasTituloAlteradoEm;
    private static WebElement gridTarefasLinkProximaPagina;

    private static final Logger log = LoggerFactory.getLogger(ScriptTesteRedmine.class);
    private static Map<String, Object> mapInfo = new HashMap();

    public static void acessarTelaCadastroUsuario() throws  Exception{
        log.info("Acessando tela de cadastro de usuário");

        botaoCadastro = getDriver().findElement(By.xpath("//div[@id='account']//a[@class='register']"));
        botaoCadastro.click();
    }

    public static void preencherFormularioCadastroUsuario() throws Exception{
        log.info("Preenchendo formulário de cadastro de usuário");
        Integer random = new Random().ints(10, 1000).findFirst().getAsInt();

        inputCadastroLogin = getDriver().findElement(By.id("user_login"));
        inputCadastroLogin.sendKeys("Teste"+random);

        inputCadastroSenha = getDriver().findElement(By.id("user_password"));
        inputCadastroSenha.sendKeys("123456");

        inputConfirmacaoSenha = getDriver().findElement(By.id("user_password_confirmation"));
        inputConfirmacaoSenha.sendKeys("123456");

        inputCadastroNome = getDriver().findElement(By.id("user_firstname"));
        inputCadastroNome.sendKeys("Redmine");

        inputCadastroSobrenome = getDriver().findElement(By.id("user_lastname"));
        inputCadastroSobrenome.sendKeys("Silva");

        inputCadastroEmail = getDriver().findElement(By.id("user_mail"));
        inputCadastroEmail.sendKeys("teste-"+random+"@gmail.com");

        botaoEnviarCadastro = getDriver().findElement(By.name("commit"));
        botaoEnviarCadastro.click();
    }

    public static void acessarTelaProjetos() throws Exception{
        log.info("Acessando tela de projetos");
        menuProjetos = getDriver().findElement(By.xpath("//div[@id='top-menu']//a[@class='projects']"));
        menuProjetos.click();
    }

    public static void criarNovoProjeto() throws Exception{
        log.info("Criando novo projeto");

        menuNovoProjeto = getDriver().findElement(By.xpath("//div[@class='contextual']//a[contains(text(), 'Novo projeto')]"));
        menuNovoProjeto.click();

        inputCadastroNomeProjeto = getDriver().findElement(By.id("project_name"));
        inputCadastroNomeProjeto.sendKeys("Automação Redmine");

        inputCadastroDescricaoProjeto = getDriver().findElement(By.id("project_description"));
        inputCadastroDescricaoProjeto.sendKeys("Testes automatizados no redmine");

        checkBoxTipoTarefaFeature = getDriver().findElement(By.xpath("//fieldset[@id='project_trackers']//label[2]//input"));
        checkBoxTipoTarefaFeature.click();

        checkBoxTipoTarefaSupport = getDriver().findElement(By.xpath("//fieldset[@id='project_trackers']//label[3]//input"));
        checkBoxTipoTarefaSupport.click();

        botaoCriarProjeto = getDriver().findElement(By.name("commit"));
        botaoCriarProjeto.click();

    }

    public static void acessarTelaNovaTarefa() throws Exception{
        log.info("Acessando tela nova tarefa");

        menuProjetoCriado = getDriver().findElement(By.xpath("//a[contains(text(), 'Automação Redmine')]"));
        menuProjetoCriado.click();

        abaNovaTarefa = getDriver().findElement(By.xpath("//div[@id='main-menu']//a[contains(text(), 'Nova tarefa')]"));
        abaNovaTarefa.click();
    }

    public static void criarTarefas() throws Exception{
        log.info("Criando tarefas");

        WebDriverWait aguardar = new WebDriverWait(getDriver(), 10);

        String jsonArray = JsonFileReader.getFileAsString("tarefaRedmine.json");
        List<TarefaRedmineDTO> maps = JsonUtils.convertStringToObjectList(jsonArray, TarefaRedmineDTO.class);

        for (TarefaRedmineDTO p : maps) {
            inputTituloTarefa = getDriver().findElement(By.id("issue_subject"));
            aguardar.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(inputTituloTarefa, "Erro")));
            inputTituloTarefa.sendKeys(p.getTituloTarefa());
            inputDescricaoTarefa = getDriver().findElement(By.id("issue_description"));
            inputDescricaoTarefa.sendKeys(p.getDescricaoTarefa());
            botaoCriarTarefaEContinuar = getDriver().findElement(By.name("continue"));
            botaoCriarTarefaEContinuar.click();

            log.info("Tarefa criada");
        }
    }

    public static void visualizarTarefas() throws Exception{
        abaTarefas = getDriver().findElement(By.xpath("//div[@id='main-menu']//a[contains(text(), 'Tarefa')]"));
        abaTarefas.click();
    }

    public static void paginarGridTarefas() throws Exception{
        log.info("Ordenando grid de tarefas");
        gridTarefasTituloAlteradoEm = getDriver().findElement(By.xpath("//a[contains(text(), 'Alterado em')]"));
        gridTarefasTituloAlteradoEm.click();
        gridTarefasTituloAlteradoEm.click();

        gridTarefasLinkProximaPagina = getDriver().findElement(By.className("next"));
        gridTarefasLinkProximaPagina.click();
    }

    public static void validarTarefaCadastrada() throws Exception{
        String jsonArray = JsonFileReader.getFileAsString("tarefaRedmineValidacao.json");
        List<TarefaRedmineDTO> maps = JsonUtils.convertStringToObjectList(jsonArray, TarefaRedmineDTO.class);

        gridTarefasColunaTituloTarefa = getDriver().findElement(By.xpath("//tr[4]//td[@class='subject']//a"));
        gridTarefasColunaPrioridade = getDriver().findElement(By.xpath("//tr[4]//td[@class='priority']"));

        Assert.assertEquals(gridTarefasColunaTituloTarefa.getText(), maps.get(28).getTituloTarefa());
        Assert.assertTrue(maps.get(28).getDescricaoTarefa().equalsIgnoreCase(gridTarefasColunaPrioridade.getText()));
    }
}
