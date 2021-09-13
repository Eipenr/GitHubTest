package pojo;

public interface CheckNull {
    default boolean isNull(){
        return false;
    }
}
