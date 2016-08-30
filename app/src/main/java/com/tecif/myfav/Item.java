package com.tecif.myfav;

class Item {
    String titulo;
    String descripcion;
    float rank;
    int photoId;

    Item(String titulo, String descripcion, int photoId, float rank) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.photoId = photoId;
        this.rank = rank;
    }
}