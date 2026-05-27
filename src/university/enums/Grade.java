package university.enums;

public enum Grade {
    A, B, C, D, E, NA;

    public double getPoints() {
        switch (this) {
            case A:
                return 5.0;
            case B:
                return 4.0;
            case C:
                return 3.0;
            case D:
                return 2.0;
            case E:
                return 1.0;
            default:
                return 0.0;
        }
    }
}
