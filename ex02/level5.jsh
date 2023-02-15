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
                Service service = active.get(index);
                if (cruise.getArrivalTime() >= service.getServiceEndTime()) {
                    expired = expired.add(service);
                    active = active.remove(index);
                }
            }
        } 
        while (loadersNeeded > 0) {
            if (expired.isEmpty()) {
                if (loaderNum % 3 != 0) {
                    active = active.add(new Service(new Loader(loaderNum), cruise));
                    served = served.add(new Service(new Loader(loaderNum), cruise));
                    loadersNeeded -= 1;
                    loaderNum += 1;
                } else {
                    Service recycledService = new RecycledService(new RecycledLoader(loaderNum), cruise);
                    active = active.add(recycledService);
                    served = served.add(recycledService);
                    loadersNeeded -= 1;
                    loaderNum += 1;
                }
            } else {
                int existingLoader = expired.get(0).getLoaderNum();
                if (existingLoader % 3 != 0) {
                    active = active.add(new Service(new Loader(existingLoader), cruise));
                    served = served.add(new Service(new Loader(existingLoader), cruise));
                    expired = expired.remove(0);
                    loadersNeeded -= 1;
                } else {
                    Service recycledServices = new RecycledService(new RecycledLoader(existingLoader), cruise);
                    active = active.add(recycledServices);
                    served = served.add(recycledServices);
                    expired = expired.remove(0);
                    loadersNeeded -= 1;
                }
            }
        }
    }
    return served;
}
