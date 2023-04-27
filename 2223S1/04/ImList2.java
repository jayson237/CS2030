import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.function.Function;

interface ImList2<E> extends Iterable<E> {

    ImList2<E> add(E elem);

    ImList2<E> addAll(ImList2<? extends E> list);

    ImList2<E> addAll(List<? extends E> list);

    E get(int index);

    int indexOf(Object obj);

    boolean isEmpty();

    Iterator<E> iterator();

    ImList2<E> remove(int index);

    ImList2<E> set(int index, E elem);

    int size();

    ImList2<E> sort(Comparator<? super E> cmp);

    List<E> getElems();

    E reduce(Function<? super ImList2<E>, ? extends E> func);

    static <E> ImList2<E> of(List<? extends E> arr) {
        return new ImList2<E>() {
            private final ArrayList<E> elems = new ArrayList<E>(arr);

            public List<E> getElems() {
                return this.elems;
            }

            public E reduce(Function<? super ImList2<E>, ? extends E> func) {
                return func.apply(this);
            }

            public ImList2<E> add(E elem) {
                ImList2<E> newList = this;
                newList.getElems().add(elem);
                return newList;
            }

            public ImList2<E> addAll(ImList2<? extends E> list) {
                return this.addAll(list.getElems());
            }

            public ImList2<E> addAll(List<? extends E> list) {
                ImList2<E> newList = this;
                newList.getElems().addAll(list);
                return newList;
            }

            public E get(int index) {
                return this.getElems().get(index);
            }

            public int indexOf(Object obj) {
                return this.getElems().indexOf(obj);
            }

            public boolean isEmpty() {
                return this.getElems().isEmpty();
            }

            public Iterator<E> iterator() {
                return this.getElems().iterator();
            }

            public ImList2<E> remove(int index) {
                ImList2<E> newList = this;
                if (index >= 0 && index < this.size()) {
                    newList.getElems().remove(index);
                }
                return newList;
            }

            public ImList2<E> set(int index, E elem) {
                ImList2<E> newList = this;
                if (index >= 0 && index < this.size()) {
                    newList.getElems().set(index, elem);
                }
                return newList;
            }

            public int size() {
                return this.getElems().size();
            }

            public ImList2<E> sort(Comparator<? super E> cmp) {
                ImList2<E> newList = this;
                newList.getElems().sort(cmp);
                return newList;
            }

            @Override
            public String toString() {
                return this.getElems().toString();
            }
        };
    }
    
    static <E> ImList2<E> of() {
        return new ImList2<E>() {
            private final ArrayList<E> elems = new ArrayList<E>();

            public List<E> getElems() {
                return this.elems;
            }
            
            public E reduce(Function<? super ImList2<E>, ? extends E> func) {
                return func.apply(this);
            }

            public ImList2<E> add(E elem) {
                ImList2<E> newList = this;
                newList.getElems().add(elem);
                return newList;
            }

            public ImList2<E> addAll(ImList2<? extends E> list) {
                return this.addAll(list.getElems());
            }

            public ImList2<E> addAll(List<? extends E> list) {
                ImList2<E> newList = this;
                newList.getElems().addAll(list);
                return newList;
            }

            public E get(int index) {
                return this.getElems().get(index);
            }

            public int indexOf(Object obj) {
                return this.getElems().indexOf(obj);
            }

            public boolean isEmpty() {
                return this.getElems().isEmpty();
            }

            public Iterator<E> iterator() {
                return this.getElems().iterator();
            }

            public ImList2<E> remove(int index) {
                ImList2<E> newList = this;
                if (index >= 0 && index < this.size()) {
                    newList.getElems().remove(index);
                }
                return newList;
            }

            public ImList2<E> set(int index, E elem) {
                ImList2<E> newList = this;
                if (index >= 0 && index < this.size()) {
                    newList.getElems().set(index, elem);
                }
                return newList;
            }

            public int size() {
                return this.getElems().size();
            }

            public ImList2<E> sort(Comparator<? super E> cmp) {
                ImList2<E> newList = this;
                newList.getElems().sort(cmp);
                return newList;
            }

            @Override
            public String toString() {
                return this.getElems().toString();
            }
        };
    }
}
