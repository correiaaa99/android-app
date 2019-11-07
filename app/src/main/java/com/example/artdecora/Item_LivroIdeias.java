package com.example.artdecora;

public class Item_LivroIdeias {

    private int imageViewLivro1;
    private int imageviewLivro2;
    private String nomeLivroIdeias;

    public Item_LivroIdeias(int imageViewLivro1, int imageviewLivro2, String nomeLivroIdeias) {
        this.imageViewLivro1 = imageViewLivro1;
        this.imageviewLivro2 = imageviewLivro2;
        this.nomeLivroIdeias = nomeLivroIdeias;
    }

    public int getImageViewLivro1() {
        return imageViewLivro1;
    }

    public void setImageViewLivro1(int imageViewLivro1) {
        this.imageViewLivro1 = imageViewLivro1;
    }

    public int getImageviewLivro2() {
        return imageviewLivro2;
    }

    public void setImageviewLivro2(int imageviewLivro2) {
        this.imageviewLivro2 = imageviewLivro2;
    }

    public String getNomeLivroIdeias() {
        return nomeLivroIdeias;
    }

    public void setNomeLivroIdeias(String nomeLivroIdeias) {
        this.nomeLivroIdeias = nomeLivroIdeias;
    }
}
