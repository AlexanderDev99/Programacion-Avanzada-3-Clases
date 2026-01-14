package Ejercicios;

public interface Result<T>{
    void bind(Effect<T> success, Effect<T> failture);

    static<T> Result<T> success(T value){
        return new Success<>(value);
    };

    static<T> Result<T> failure(T msn){
        return  new Failure<>(msn);
    };
}

//CLASES QUE MANDA LOS MENSAJESSI HAY EXITO O FALLA
class Success<T> implements Result<T>{

    private final T value;

    Success(T t ){
        this.value = t;
    }

    @Override
    public void bind(Effect success, Effect failture) {

        success.apply(value);

    }
}

class Failure<T> implements  Result<T>{

    private final T mns;

    Failure(T t ){
        this.mns = t;
    }

    @Override
    public void bind(Effect<T> success, Effect<T> failture) {

        failture.apply(mns);
    }
}
