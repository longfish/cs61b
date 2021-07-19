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
            Planet iPlanet = new Planet(in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readString());
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

        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-universeRadius, universeRadius);
        StdDraw.clear(); // clear the drawing window

        // simulate the planet motion
        double t = 0;
        while (t < T) {
            double[] xForces = new double[planetGroup.length];
            double[] yForces = new double[planetGroup.length];

            for (int i = 0; i < planetGroup.length; i++) {
                xForces[i] = planetGroup[i].calcNetForceExertedByX(planetGroup);
                yForces[i] = planetGroup[i].calcNetForceExertedByY(planetGroup);
            }

            for (int i = 0; i < planetGroup.length; i++)
                planetGroup[i].update(dt, xForces[i], yForces[i]);

            // draw the background image
            StdDraw.clear();
            StdDraw.picture(0, 0, universeBackground);

            // draw all the planets
            for (Planet p : planetGroup)
                p.draw();
            StdDraw.show();
            StdDraw.pause(10);

            t += dt;
        }

        // print out the simulation results
        StdOut.printf("%d\n", planetGroup.length);
        StdOut.printf("%.2e\n", universeRadius);
        for (Planet p : planetGroup) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    p.xxPos, p.yyPos, p.xxVel,
                    p.yyVel, p.mass, p.imgFileName);
        }
    }
}
