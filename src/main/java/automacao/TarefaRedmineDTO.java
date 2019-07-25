package automacao;

public class TarefaRedmineDTO {

    private String tituloTarefa;
    private String descricaoTarefa;

    public String getTituloTarefa () {
        return tituloTarefa;
    }

    public void setTituloTarefa (final String tituloTarefa) {
        this.tituloTarefa = tituloTarefa;
    }

    public String getDescricaoTarefa () {
        return descricaoTarefa;
    }

    public void setDescricaoTarefa (final String descricaoTarefa) {
        this.descricaoTarefa = descricaoTarefa;
    }
}
