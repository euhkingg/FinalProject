public enum Piece {
    none(0), king(10), queen(9), rook(5),
    bishop(3), knight(3), pawn(3);

    private final int value;

    Piece(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
