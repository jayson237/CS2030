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

    void view(DayView day) {
        ImList<Manager> sorted = tasks.sort(new TaskComparator());
        ImList<Manager> list = new ImList<Manager>();
        for (int i = 0; i < tasks.size(); i++) {
            Manager currTask = sorted.get(i);
            if (!currTask.isCancelled() && currTask.isRecurring()) {
                for (int j = 0; j < currTask.getList().size(); j++) {
                    Manager currRecTask = currTask.getList().get(j);
                    if (!currRecTask.isCancelled() && currRecTask.getDay() == day.getViewDay()) {
                        list = list.add(currRecTask);
                    }
                }
            } else if (!currTask.isCancelled() && 
                    currTask.getDay() == day.getViewDay()) {
                list = list.add(currTask);
            }
        }
        list = list.sort(new TaskComparator());
        day.view(list);
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
