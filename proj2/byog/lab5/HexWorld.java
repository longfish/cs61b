package byog.lab5;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    private static final int WIDTH = 60;
    private static final int HEIGHT = 30;

    private static class Position {
        final public int x;
        final public int y;

        public Position(int X, int Y) {
            this.x = X;
            this.y = Y;
        }
    }

    private static boolean isCorner(int x, int y, int s) {
        return y < -x + s - 1 || y < x - 2 * s + 2 || y > x + s || y > -x + 4 * s - 3;
    }

    @Test
    public void testIsCorner() {
        assertTrue(isCorner(0, 0, 3));
        assertFalse(isCorner(0, 2, 3));
        assertTrue(isCorner(0, 5, 3));
    }

    public static void addHexagon(TETile[][] world, Position p, int s, TETile tile) {
        for (int x = p.x; x < p.x + 3 * s - 2; x += 1) {
            for (int y = p.y; y < p.y + 2 * s; y += 1) {
                if (!isCorner(x, y, s)) {
                    world[x][y] = tile;
                }
            }
        }
    }

    public static void initWorld(TETile[][] world, TETile tile) {
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }
    }

    public static void main(String[] args) {
        // initialize the tile rendering engine with a window of size WIDTH x HEIGHT
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        // initialize tiles in the world
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        initWorld(world, Tileset.NOTHING);

        // add a hexagon in the world
        Position p = new Position(0, 0);
        addHexagon(world, p, 6, Tileset.FLOWER);

        // draws the world to the screen
        ter.renderFrame(world);
    }
}
