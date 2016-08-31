package com.tecif.myfav;

class Item {
    String titulo;
    String descripcion;
    float rank;
    String photo;

    Item(String titulo, String descripcion, String photo, float rank) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.photo = photo;
        this.rank = rank;
    }
}