import java.util.Optional;

class Fraction extends AbstractNum<Frac> {

    protected Fraction(Num a, Num b) {
        super(Frac.of(a, b));
    }

    protected Fraction(Optional<Frac> opt) {
        super(opt);
    }

    static Fraction of(Num a, Num b) {
        if (!a.isValid() || !b.isValid() || b.equals(Num.zero())) {
            return new Fraction(Optional.empty());
        }
        return new Fraction(a, b);
    }

    static Fraction of(int a, int b) {
        return Fraction.of(Num.of(a), Num.of(b));
    }

    Fraction add(Fraction other) {
        Optional<Frac> optFrac = this.opt.flatMap(x -> 
                other.opt.map(y -> {
                    Num a = x.first();
                    Num b = x.second();
                    Num c = y.first();
                    Num d = y.second();
                    Num ad = a.mul(d);
                    Num bc = b.mul(c);
                    Num bd = b.mul(d);
                    return Frac.of(ad.add(bc), bd);
                })
                .filter(z -> z.first().isValid() && z.second().isValid() && 
                    !z.second().equals(Num.zero())));
        return new Fraction(optFrac);
    }

    Fraction sub(Fraction other) {
        Optional<Frac> optFrac = this.opt.flatMap(x -> 
                other.opt.map(y -> {
                    Num a = x.first();
                    Num b = x.second();
                    Num c = y.first();
                    Num d = y.second();
                    Num ad = a.mul(d);
                    Num bc = b.mul(c);
                    Num bd = b.mul(d);
                    return Frac.of(ad.sub(bc), bd);
                })
                .filter(z -> z.first().isValid() && z.second().isValid() && 
                    !z.second().equals(Num.zero())));
        return new Fraction(optFrac);
    }

    Fraction mul(Fraction other) {
        Optional<Frac> optFrac = this.opt.flatMap(x -> 
                other.opt.map(y -> {
                    Num a = x.first();
                    Num b = x.second();
                    Num c = y.first();
                    Num d = y.second();
                    Num ac = a.mul(c);
                    Num bd = b.mul(d);
                    return Frac.of(ac, bd);
                })
                .filter(z -> z.first().isValid() && z.second().isValid() && 
                    !z.second().equals(Num.zero())));
        return new Fraction(optFrac);
    } 
}

