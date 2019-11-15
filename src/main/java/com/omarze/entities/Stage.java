package com.omarze.entities;


/**
 * created by julian
 */
public enum Stage {

    FIRST(1),

    SECOND(2),

    THIRD(3);


    public final int value;

    Stage(int value) {
        this.value = value;
    }



    public static Stage from(int val) {
        for (Stage stage : values()) {
            if (stage.value == val) {
                return stage;
            }
        }

        return FIRST;
    }


}
