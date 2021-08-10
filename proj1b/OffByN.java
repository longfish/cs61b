/**
 * A class for off-by-N comparators.
 */
public class OffByN implements CharacterComparator {
    private final int offN;

    public OffByN(int N) {
        offN = N;
    }

    @Override
    public boolean equalChars(char x, char y) {
        int diff = x - y;
        return Math.abs(diff) == offN;
    }
}
