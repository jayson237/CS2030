import java.util.List;
import java.util.ArrayList;

class ImList<T> {
    private final List<T> list;

    ImList() {
        this.list = new ArrayList<T>();
    }

    private ImList(List<T> oldList) {
        this.list = new ArrayList<T>(oldList);
    }

    ImList<T> add(T elem) {
        return update(0, elem, true);
    }

    ImList<T> set(int index, T elem) {
        return update(index, elem, false);
    }

    ImList<T> update(int index, T elem, boolean isAdd) {
        ImList<T> newList = new ImList<T>(this.list);
        if (isAdd) {
            newList.list.add(elem);
        } else {
            newList.list.set(index, elem);
        }
        return newList;
    }
}
