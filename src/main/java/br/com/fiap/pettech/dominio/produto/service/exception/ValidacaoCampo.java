package br.com.fiap.pettech.dominio.produto.service.exception;

public class ValidacaoCampo {
    private String campo;
    private String mensagem;

    public ValidacaoCampo() {
    }

    public ValidacaoCampo(String campo, String mensagem) {
        this.campo = campo;
        this.mensagem = mensagem;
    }

    public String getCampo() {
        return campo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setCampo(String campo) {
        this.campo = campo;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
