import java.util.List;
import java.util.ArrayList;
import java.util.function.Supplier;

class ImList<T> {
    private final Supplier<List<T>> list;
    
    ImList() {
        this.list = () -> new ArrayList<T>();
    }
    
    private ImList(Supplier<List<T>> list) {
        this.list = list;
    }
    
    ImList<T> add(T t) {
        return new ImList<T>(() -> {
            System.out.println("Adding: " + t);
            List<T> newList = new ArrayList<T>(this.list.get());
            newList.add(t);
            return newList;
        });
    }
    
    ImList<T> set(int index, T t) {
        return new ImList<T>(() -> {
            System.out.println("Setting: " + index + ", " + t);
            List<T> newList = new ArrayList<T>(this.list.get());
            newList.set(index, t);
            return newList;
        });
    }
    
    T get(int index) {
        return this.list.get().get(index);
    }
    
    @Override
    public String toString() {
        return "ImList";
    }
}
