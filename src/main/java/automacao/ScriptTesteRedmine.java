package automacao;

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

             setup(navegador);
             executarCenarioTeste();
             tearDown();
         }catch (Exception e){
             log.error(e.getMessage());
         }

     }

    private static void setup(String navegador){
         log.info("Configurando WebDriver!");
        DriverFactory.getDriver(navegador).get("http://demo.redmine.org/");
    }

    private static void tearDown(){
         log.info("Encerrando WebDriver!");
         DriverFactory.killDriver();
    }

    public static void executarCenarioTeste() throws Exception{
        acessarTelaCadastroUsuario();

        preencherFormularioCadastroUsuario();

        acessarTelaProjetos();

        criarNovoProjeto();

        acessarTelaProjetos();

        acessarTelaNovaTarefa();

        criarTarefas();

        visualizarTarefas();

        paginarGridTarefas();

        validarTarefaCadastrada();
    }

}
