import java.util.Optional;
import java.util.Map;

class KeyableMap<V extends Keyable> implements Keyable {
    private final String key;
    private final ImmutableMap<String, V> map;
        
    KeyableMap(String key) {
        this.key = key; 
        ImmutableMap<String, V> tempMap = new ImmutableMap<String, V>();
        this.map = tempMap;
    }

    KeyableMap(String key, ImmutableMap<String, V> map) {
        this.key = key;
        this.map = map;
    }

    @Override
    public String getKey() {
        return this.key;
    }

    ImmutableMap<String, V> getMap() {
        return this.map;
    }

    Optional<V> get(String key) {
        return this.map.get(key);
    }

    KeyableMap<V> put(V item) {
        return new KeyableMap<V>(this.key, map.put(item.getKey(), item));
    }

    @Override
    public String toString() {
        String output = "{";
        for (Map.Entry<String, V> e : map) {
            output += e.getValue() + ", ";
        }

        if (output.length() > 2) {
            output = output.substring(0, output.length() - 2);
        }

        output += "}";
        return String.format("%s: %s", this.key, output)
            .replace("[", "{").replace("]", "}");
    }
}
