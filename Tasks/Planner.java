class Planner {
    private final ImList<Manager> tasks;

    Planner() {
        ImList<Manager> tasks = new ImList<Manager>();
        this.tasks = tasks;
    } 

    Planner(ImList<Manager> tasks) {
        this.tasks = tasks;
    }

    Planner add(Manager task) {
        ImList<Manager> listOfTasks = this.tasks;
        listOfTasks = listOfTasks.add(task);
        return new Planner(listOfTasks);
    }

    void view(View day) {
        day.view(tasks.sort(new TaskComparator()));
    }
    
    @Override
    public String toString() {
        String output = "";
        if (tasks.isEmpty()) {
            return output;
        } else {
            output += "\n";
        }
        for (int i = 0; i < tasks.size(); i++) {
            if (i != tasks.size() - 1) {
                output += String.format("%s\n", tasks.get(i));
            } else {
                output += tasks.get(i);
            }
        }
        return output;
    }
}
