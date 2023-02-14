ImList<Service> serveCruises(ImList<Cruise> cruises) {
    ImList<Service> active = new ImList<Service>();
    ImList<Service> expired = new ImList<Service>();
    ImList<Service> served = new ImList<Service>();
    int numOfCruises = cruises.size();
    int loaderNum = 1;
    for (int i = 0; i < numOfCruises; i++) {
        Cruise cruise = cruises.get(i);
        int loadersNeeded = cruise.getNumOfLoadersRequired();
        if (!active.isEmpty()) {
            for (Service s: active) {
                int index = active.indexOf(s);
                if (cruise.getArrivalTime() >= active.get(index).getServiceEndTime()) {
                    expired = expired.add(active.get(index));
                    active = active.remove(index);
                }
            }
        } 
        while (loadersNeeded > 0) {
            if (expired.isEmpty()) {
                active = active.add(new Service(new Loader(loaderNum), cruise));
                served = served.add(new Service(new Loader(loaderNum), cruise));
                loadersNeeded -= 1;
                loaderNum += 1;
            } else {
                int existingLoader = expired.get(0).getLoaderNum(); 
                active = active.add(new Service(new Loader(existingLoader), cruise));
                served = served.add(new Service(new Loader(existingLoader), cruise));
                expired = expired.remove(0);
                loadersNeeded -= 1;
            }
        }
    }
    return served;
}
