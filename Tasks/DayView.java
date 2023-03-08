class DayView implements View {
    private final int day;

    DayView(int day) {
        this.day = day;
    }
    
    @Override
    public void view(ImList<Manager> tasks) {
        ImList<Manager> list = new ImList<Manager>();
        for (int i = 0; i < tasks.size(); i++) {
            Manager currTask = tasks.get(i);
            if (!currTask.isCancelled() && currTask.isRecurring()) {
                for (int j = 0; j < currTask.getList().size(); j++) {
                    Manager currRecTask = currTask.getList().get(j);
                    if (!currRecTask.isCancelled() && currRecTask.getDay() == day) {
                        list = list.add(currRecTask);
                    }
                }
            } else if (!currTask.isCancelled() && 
                    currTask.getDay() == day) {
                list = list.add(currTask);
            }
        }
        list = list.sort(new TaskComparator());
        for (Manager t : list) {
            System.out.println(t);
        }
    }
}
