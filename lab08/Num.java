import java.util.Optional;

class Num extends AbstractNum<Integer> {

    protected Num(AbstractNum<Integer> abs) {
        super(abs.opt.filter(AbstractNum.valid));
    }

    protected Num(Optional<Integer> opt) {
        super(opt.filter(AbstractNum.valid));
    }

    static Num empty() {
        return new Num(Optional.empty()); 
    }

    static Num of(int i) {
        Optional<Integer> integer = Optional.<Integer>of(i);
        integer  = integer.filter(AbstractNum.valid);
        return new Num(integer);
    }

    static Num zero() {
        return new Num(AbstractNum.zero());
    }

    Num succ() {
        return new Num(this.opt.map(AbstractNum.s));   
    }

    static Num one() {
        return Num.zero().succ();
    }

    Num add(Num integer) {
        if (!integer.isValid() || !this.isValid()) {
            return Num.empty();
        }
        Num result = this;
        AbstractNum<Integer> abs = 
            new AbstractNum<Integer>(integer.opt.map(AbstractNum.n));
        while (!abs.equals(Num.zero())) {
            result = result.succ();
            abs = new AbstractNum<Integer>(abs.opt.map(AbstractNum.s));
        }
        return result;
    }

    Num mul(Num integer) {
        if (!integer.isValid() || !this.isValid()) {
            return Num.empty();
        } 
        Num result = Num.zero();
        AbstractNum<Integer> abs = 
            new AbstractNum<Integer>(integer.opt.map(AbstractNum.n));
        while (!abs.equals(Num.zero())) {
            result = result.add(this);
            abs = new AbstractNum<Integer>(abs.opt.map(AbstractNum.s));
        }
        return result;
    }

    Num sub(Num integer) {
        if (!integer.isValid() || !this.isValid()) {
            return Num.empty();
        }
        AbstractNum<Integer> result =
            new AbstractNum<Integer>(integer.opt.map(AbstractNum.n));
        AbstractNum<Integer> abs = 
            new AbstractNum<Integer>(this.opt.map(AbstractNum.n));
        while (!abs.equals(Num.zero())) {
            result = new AbstractNum<Integer>(result.opt.map(AbstractNum.s));
            abs = new AbstractNum<Integer>(abs.opt.map(AbstractNum.s));
        }
        Num output = new Num(result.opt);
        return output;
    }
}
