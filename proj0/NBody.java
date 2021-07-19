public class NBody {
    public static double readRadius(String fileName) {
        In in = new In(fileName);
        in.readInt();
        return in.readDouble();
    }

    public static Planet[] readPlanets(String fileName) {
        In in = new In(fileName);
        int nPlanet = in.readInt();
        Planet[] arrPlanet = new Planet[nPlanet];
        in.readDouble();
        for (int i = 0; i < nPlanet; i++) {
            Planet iPlanet = new Planet(in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), "./images/" + in.readString());
            arrPlanet[i] = iPlanet;
        }

        return arrPlanet;
    }

    public static void main(String[] args) {
        String universeBackground = "./images/starfield.jpg";

        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];

        double universeRadius = NBody.readRadius(filename);
        Planet[] planetGroup = NBody.readPlanets(filename);

        StdDraw.setScale(-universeRadius, universeRadius);
        StdDraw.clear(); // clear the drawing window
        StdDraw.picture(0, 0, universeBackground); // draw the image at the origin

        for (Planet p : planetGroup) {
            p.draw();
        }
    }
}
