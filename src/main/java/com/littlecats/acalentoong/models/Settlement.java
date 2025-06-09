package com.littlecats.acalentoong.models;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class Settlement implements Serializable {
    // ETIQUETA DA CLASSE PARA A SERIALIZAÇÃO
    private static final long serialVersionUID = 1L;

    // ID DO OBJETO GERADO AUTOMATICAMENTE
    private final UUID id;

    private String nome;
    private int numeroDeFamilias;

    // ENDEREÇO
    private String rua;
    private String numero;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;

    public Settlement(String nome, int numeroDeFamilias, String rua, String numero, String bairro, String cidade, String estado, String cep) {
        this.id = UUID.randomUUID();
        this.nome = nome;
        this.numeroDeFamilias = numeroDeFamilias;
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
    }

    public UUID getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getNumeroDeFamilias() {
        return numeroDeFamilias;
    }

    public void setNumeroDeFamilias(int numeroDeFamilias) {
        this.numeroDeFamilias = numeroDeFamilias;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)  {
            return true;
        }
        else if (obj == null) {
            return false;
        }
        else if (obj instanceof Settlement other) {
            return Objects.equals(this.id, other.id);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
