void findBestBooking(Request request, List<Driver> drivers) {
    FareAndTimeComparator comparator = new FareAndTimeComparator();
    
    ImList<Booking> allPossibleBookings = new ImList<Booking>();

    for (Driver driver : drivers) {
        allPossibleBookings = allPossibleBookings.addAll(new Booking(driver, request).getAllPossibleBookings());
    }

    ImList<Booking> sortedBookings = allPossibleBookings.sort(comparator);
    for (Booking booking : sortedBookings) {
        System.out.println(booking.toStringThis());
    }
}
