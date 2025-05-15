package org.example.model;

public class Libro {
    private int libroId;
    private String titulo;
    private String genero;
    private int autorId;
    private Autor autor;

    public Libro(int libroId, String titulo, String genero, int autorId) {
    }

    public Libro(int libroId, String titulo, String genero, int autorId, Autor autor) {
        this.libroId = libroId;
        this.titulo = titulo;
        this.genero = genero;
        this.autorId = autorId;
        this.autor = autor;
    }

    public int getLibroId() {
        return libroId;
    }

    public void setLibroId(int libroId) {
        this.libroId = libroId;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getAutorId() {
        return autorId;
    }

    public void setAutorId(int autorId) {
        this.autorId = autorId;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    @Override
    public String toString() {
        return "Libro{" +
                "libroId=" + libroId +
                ", titulo='" + titulo + '\'' +
                ", genero='" + genero + '\'' +
                ", autorId=" + autorId +
                ", autor=" + autor +
                '}';
    }
}
