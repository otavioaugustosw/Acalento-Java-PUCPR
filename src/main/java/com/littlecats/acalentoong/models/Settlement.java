package com.littlecats.acalentoong.models;

import java.io.Serializable;
import java.util.Objects;

public class Settlement implements Serializable {
    private static final long serialVersionUID = 1L;

    private long id;
    private String nome;

    public Settlement(String nome) {
        this.id = 1L;
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Settlement that = (Settlement) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}