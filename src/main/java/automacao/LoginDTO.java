package automacao;

/**
 * @author usertqi
 * @version $Revision: $<br/>
 * $Id: $
 * @since 25/07/19 18:22
 */
public class LoginDTO {
    private String usuario;
    private String senha;

    public String getUsuario () {
        return usuario;
    }

    public void setUsuario (final String usuario) {
        this.usuario = usuario;
    }

    public String getSenha () {
        return senha;
    }

    public void setSenha (final String senha) {
        this.senha = senha;
    }
}
