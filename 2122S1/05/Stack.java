import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

class Stack<T> {
    private final List<T> list;

    Stack() {
        this.list = new ArrayList<T>();
    }

    private Stack(List<T> oldList) {
        this.list = new ArrayList<T>(oldList);
    }

    Stack<T> push(T elem) {
        Stack<T> newStack = new Stack<T>(this.list);
        newStack.list.add(0, elem);
        return newStack;
    }

    boolean isEmpty() {
        return this.list.isEmpty();
    }

    Pair<Optional<T>, Stack<T>> pop() {
        if (list.size() == 0) {
            return new Pair<Optional<T>, Stack<T>>(Optional.<T>empty(), this);
        }
        Optional<T> op = Optional.<T>of(list.get(0)); 
        list.remove(0);
        Stack<T> newStack = new Stack<T>(list);
        return new Pair<Optional<T>, Stack<T>>(op, newStack); 
    }

    int evaluate(String expr) {
        Scanner sc = new Scanner(expr);
        while (sc.hasNext()) {
            String term = sc.next();
            
            if (term.equals("+") || term.equals("*")) {
            
            } else {
                Integer value = Integer.parseInt(term);
                    
            }
        }
        return;
    }

    public String toString() {
        return "Top -> " + this.list;
    }
}

