import java.util.List;

class SumList extends DnC<List<Integer>, Integer> {
    SumList(List<Integer> list) {
        super(() -> list, l -> l.size() <= 1, l -> l.get(0),
                l -> {
                    int mid = l.size() / 2;
                    return Pair.of(() -> l.subList(0, (l.size() + 1) / 2),
                            () -> l.subList((l.size() + 1) / 2, l.size()));
                });
    }
}
