Supplier<Integer> countUrl(List<String> urls) {
    return () -> urls.stream().map(x -> processUrl(x)).reduce(0, (a, b) -> a + b);
}

CompletableFuture<Integer> countUrl(List<String> urls) {
    return urls.stream()
        .map(x -> CompletableFuture.supplyAsync(() -> processUrl(x)))
        .reduce(CompletableFuture.completedFuture(0), (x,y) -> x.thenCombine(y, (a, b) -> a + b));
}


