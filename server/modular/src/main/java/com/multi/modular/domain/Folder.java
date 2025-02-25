package com.multi.modular.domain;
// No lo asocio con barra por si utilizamos path.join 
// TODO: Intentar que el enumerado dependa de un .properties
public enum Folder {
    MODULE("modules"),
    TEACHER("teachers"),
    TEMPLATE("finalTemplates"); 

    public final String path; 
    Folder(String p) {path=p;}; 
}
