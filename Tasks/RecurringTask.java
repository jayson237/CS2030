class RecurringTask extends Manager {
    private final Task task;
    private final int freq;
    private final int occur;
    private final ImList<Manager> tasks;

    RecurringTask(int day, int start, int end, String desc, 
            Task task, int freq, int occur, ImList<Manager> tasks) {
        super(day, start, end, desc);
        this.task = task;
        this.freq = freq;
        this.occur = occur;
        this.tasks = tasks;
    }

    RecurringTask(Task task, int freq, int occur, ImList<Manager> tasks) {
        super(task.getDay(), task.getStart(), task.getEnd(), task.getDesc());
        this.task = task;
        this.freq = freq;
        this.occur = occur;
        this.tasks = tasks;
    }
    
    RecurringTask(Task task, int freq, int occur) {
        super(task.getDay(), task.getStart(), task.getEnd(), task.getDesc());
        this.task = task;
        this.freq = freq;
        this.occur = occur;
        ImList<Manager> tasks = new ImList<Manager>();
        for (int i = 0; i < occur; i++) {
            tasks = tasks.add(new Task(task.getDay() + (i * freq), task.getStart(), 
                    task.getEnd(), task.getDesc()));
        }
        this.tasks = tasks;
    }

    @Override
    ImList<Manager> getList() {
        return this.tasks;
    }

    @Override
    boolean isRecurring() {
        return true;
    }

    CancelledRT cancel() {
        return new CancelledRT(task.getDay(), task.getStart(), task.getEnd(), task.getDesc());
    }

    RecurringTask cancel(int index) {
        ImList<Manager> updatedTasks = new ImList<Manager>();
        for (int i = 0; i < tasks.size(); i++) {
            if (i == index - 1) {
                updatedTasks = updatedTasks.add(new CancelledTask(task.getDay() + (i * freq), 
                        task.getStart(), task.getEnd(), task.getDesc()));
            } else {
                updatedTasks = updatedTasks.add(tasks.get(i));
            }
        }
        return new RecurringTask(task, freq, occur, updatedTasks);
    }

    RecurringTask edit(int start, int end) {
        return new RecurringTask(new Task(task.getDay(), start,
                end, task.getDesc()), freq, occur);
    }

    RecurringTask edit(int index, int day, int start, int end) {
        ImList<Manager> empty = new ImList<Manager>();
        for (int i = 0; i < tasks.size(); i++) {
            if (i == index - 1) {
                empty = empty.add(new Task(day, start, end, task.getDesc()));
            } else {
                empty = empty.add(tasks.get(i));
            }
        }
        ImList<Manager> sortedTasks = empty.sort(new TaskComparator());
        return new RecurringTask(task, freq, occur, sortedTasks);
    }    

    @Override
    public String toString() {
        String output = String.format("Recurring %s\n", task); 
        for (int i = 0; i < tasks.size(); i++) {
            if (i != tasks.size() - 1) {
                output += String.format("#%d:%s\n", i + 1, tasks.get(i));
            } else {
                output += String.format("#%d:%s", i + 1, tasks.get(i));
            }
        }
        return output;
    }
}
