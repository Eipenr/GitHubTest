package eipen.pojo;

public interface IsEmpty {
    default boolean isNull(){
        return false;
    }
}
