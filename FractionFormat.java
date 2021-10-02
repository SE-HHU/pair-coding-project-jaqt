package SimpleCalculate;


//分数类
class FractionFormat {

    private int Numerator; // 分子
    private int Denominator; // 分母

    public FractionFormat(int numerator, int denominator) {
        this.Numerator = numerator;
        if (denominator == 0) {
            throw new ArithmeticException("分母不能为零");
        } else {
            this.Denominator = denominator;
        }
        change();
    }

    public int[] getNumber() {
        return new int[]{getNumerator(), getDenominator()};
    }

    public int getNumerator() {
        return Numerator;
    }

    public int getDenominator() {
        return Denominator;
    }

    private FractionFormat change() {
        int gcd = this.gcd(this.Numerator, this.Denominator);
        this.Numerator /= gcd;
        this.Denominator /= gcd;
        return this;
    }

    /**
     * 最大公因数
     */
    private int gcd(int a, int b) {
        int mod = a % b;
        if (mod == 0) {
            return b;
        } else {
            return gcd(b, mod);
        }
    }

    /**
     * 四则运算
     */
    public FractionFormat add(FractionFormat second) {
        return new FractionFormat(this.Numerator * second.Denominator + second.Numerator * this.Denominator,
                this.Denominator * second.Denominator);
    }

    public FractionFormat sub(FractionFormat second) {
        return new FractionFormat(this.Numerator * second.Denominator - second.Numerator * this.Denominator,
                this.Denominator * second.Denominator);
    }

    public FractionFormat multiply(FractionFormat second) {
        return new FractionFormat(this.Numerator * second.Numerator,
                this.Denominator * second.Denominator);
    }

    public FractionFormat divide(FractionFormat second) {
        return new FractionFormat(this.Numerator * second.Denominator,
                this.Denominator * second.Numerator);
    }
}
