package seis.listafun;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

// sealead no permite que la interfaz sea implementada por cualquiera
public sealed interface Lista<T> permits Empty, Const {

    // crear lista vacia
    Lista Empty = new Empty();

    //Metodo en caso de que la lista sea vacia
    boolean isEmty();

    //Cabeza y cola de la lista para recorrer la lista con recursividad.
    T head();

    Lista<T> tail();

    //AGREGAR ELEMENTOS A UNA LISTA

    //sobrecarga de metodos
    public static <T> Lista<T> of(T head, Lista<T> tail) {
        return new Const(head, tail);
    }

    // T... indica que va ingresar un valor o mas
    public static <T> Lista<T> of(T... values) {
        Lista<T> fin = Empty;

        for (int i = values.length - 1; i >= 0; i--) {
            Lista<T> tmp = Lista.of(values[i], fin);
            fin = tmp;
        }

        return fin;
    }

    default Lista<T> invertir() {
        var tmp = this; //como el metodo es llamado por una lista con tmp nos referimos a que se le asigna esa lista.

        Lista<T> ret = Empty;

        while (!tmp.isEmty()) {
            ret = Lista.of(tmp.head(), ret);
            tmp = tmp.tail();
        }

        return ret;
    }

    //REMOVER
    default Lista<T> removeFirst() {

        return this.tail();
    }

    //Metodo para contar los elemntos de array
    default int count() {
        return isEmty() ? 0 : 1 + tail().count(); // de manera recursiva toma primero el elemento y luego toma la cola
    }

    //metodo forEach para recorrer una lista
    default void forEach(Consumer<T> consumer) {
        if (!this.isEmty()) {
            consumer.accept(this.head()); //Toma la cabecera y hace algo
            this.tail().forEach(consumer); // volvemos a llamar a la funcion pero con la cola y le pasamos el mismo consumer de la recursiva
        }
    }

    default Lista<T> prepend(T value) {
        return Lista.of(value, this);
    }

    default Lista<T> append(T value) {
        return this.invertir().prepend(value).invertir(); //invierto la lista agrego el elemnto y vuelvo a invertir.
    }


    default Lista<T> set(int index, T value) {
        if (index < 0 || this.isEmty()) {
            throw new IndexOutOfBoundsException("Índice fuera de rango: " + index);
        }

        // Caso base: hemos llegado al índice que queremos cambiar
        if (index == 0) {
            // Retornamos una nueva celda con el nuevo valor, pero conservando la cola original
            return Lista.of(value, this.tail());
        }

        // Caso recursivo: mantenemos la cabeza actual y avanzamos hacia la cola
        return Lista.of(this.head(), this.tail().set(index - 1, value));
    }


    static <T> List<T> copy(List<T> list) {
        return new ArrayList(list);
    }

    default <U> Lista<U> map(Function<T, U> fn) {
        return this.isEmty()
                ? Lista.Empty // si lallista es vacia devolvemos un Empty
                : Lista.of(fn.apply(this.head()), this.tail().map(fn));
    }

    default T reduce(T identidad, Function<T, Function<T, T>> fn) {
        T acumulador = identidad;
        var tmp = this;
        while (!tmp.isEmty()) {
            acumulador = fn.apply(acumulador).apply(tmp.head());
            tmp = tmp.tail();
        }

        return acumulador;
    }

    default T get(int index){
        return index == 0
                ? this.head()
                : this.tail().get(index -1);
    }

    default Lista<T> drop(int n) {

        return n <= 0 || this.isEmty() //comporobamos si n = 0 y si la lista
                ?this
                :this.tail().drop(n - 1);
    }

    // dado una lista nos devuelva los n primeros elementos con esa lista

    default Lista<T> take(int n){

        return n <= 0 || this.isEmty()
                ? Lista.Empty
                : Lista.of(this.head(), this.tail().take(n - 1));
    }


    default Lista<T> concat(Lista<T> lis) {
        return this.isEmty()
                ? lis
                : Lista.of(this.head(), this.tail().concat(lis));
    }

    default <U> U foldLeft(U identity, Function<U, Function<T,U>> fn){
        U res = identity;
        var tmp = this;
        while (!tmp.isEmty()){
            res = fn.apply(res).apply(tmp.head());
            tmp.tail();
        }

        return res;
    }

    default <U> U foldRight(U identity, Function<T, Function<U,U>> fn){
        return this.isEmty()
                ? identity
                : fn.apply(this.head()).apply(this.tail().foldRight(identity, fn));
    }

    //Reduce usando folding
    default T reduceFoldLeft(T identidad, Function<T , Function<T, T>> fn){
        return this.foldLeft(identidad, t1 -> t2 -> fn.apply(t1).apply(t2) );
    }

    //Map usando folding
    default <U> Lista<U> mapFoldLeft(Function<T, U> fn){
        return this.foldLeft(Lista.Empty, t1 -> t2 -> t1.append(fn.apply(t2)));
    }

    //append usando folding
    default Lista<T> appendFoldRight(T elemento){
        return this.foldRight(Lista.of(elemento), t -> ls -> ls.prepend(t));
    }

    //Count usando Folding
    default  Integer countFold(){
        return this.foldLeft(0, i -> t -> i + 1);
    }

    //Invertir usando Folding
    default Lista<T> invertirFold(){
        return this.foldLeft(Lista.Empty, acc -> l -> acc.append(l) );
    }


    //
    default  T reduce2(Function<T, Function<T,T>> fn){
        return this.tail().foldLeft(this.head(),u -> t -> fn.apply(u).apply(t));
    }

    //conacatenar dos listas usando fold

    default Lista<T> concatFold(Lista<T> lista){
        return this.foldRight(lista, l1 -> l2 -> l2.append(l1));
    }

    //metodo parafiltrar listas segun un parametro,usando Folding
    default  Lista<T> filter(Predicate<T> p){
        return this.foldRight(Lista.Empty, el -> acc ->
                p.test(el) ? acc.prepend(el) : acc);
    }

}


record Const<T>(T head, Lista<T> tail) implements Lista {

    @Override
    public boolean isEmty() {
        return true;
    }

    @Override
    public String toString() {
        return String.format("[%s, %s]", head, tail);
    }

}


final class Empty implements Lista {

    @Override
    public boolean isEmty() {
        return true;
    }


    @Override
    public Object head() {
        return null;
    }

    @Override
    public Lista tail() {
        return null;
    }

    @Override
    public String toString() {
        return "Empty";
    }

}
