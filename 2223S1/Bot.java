import java.util.Random;
import java.util.List;

class Bot {
    private final int id;
    private static final Random random = new Random();
    private final ImList<Henry> messages;

    Bot() {
        this.id = random.nextInt(1000);
        this.messages = new ImList<Henry>();
    }

    Bot(List<Henry> list) {
        this.id = random.nextInt(1000);
        this.messages = new ImList<Henry>(list);
    }

    private Bot(int id, ImList<Henry> users) {
        this.id = id;
        this.messages = users;
    }

    Bot connect(Henry user) {
        String s = this.toString() + " says hi to " + user.toString();
        Bot newBot = new Bot(this.id, this.messages.add(user));
        for (Henry u : messages) {
            u.notify(s);
        }
        return newBot;
    }
 
    @Override
    public String toString() {
        return "bot@" + this.id;
    }
}



