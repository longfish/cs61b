public class Planet {
    private final static double G = 6.67e-11;
    public double xxPos, yyPos;
    public double xxVel, yyVel;
    public double mass;
    public String imgFileName;

    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet p) {
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p) {
        return Math.sqrt((xxPos - p.xxPos) * (xxPos - p.xxPos) + (yyPos - p.yyPos) * (yyPos - p.yyPos));
    }

    public double calcForceExertedBy(Planet p) {
        double dis = this.calcDistance(p);
        return G * mass * p.mass / dis / dis;
    }

    public double calcForceExertedByX(Planet p) {
        return this.calcForceExertedBy(p) * (p.xxPos - xxPos) / this.calcDistance(p);
    }

    public double calcForceExertedByY(Planet p) {
        return this.calcForceExertedBy(p) * (p.yyPos - yyPos) / this.calcDistance(p);
    }

    public double calcNetForceExertedByX(Planet[] pArr) {
        double sumForceX = 0;
        for (Planet p : pArr) {
            if (p.equals(this))
                continue;
            sumForceX += this.calcForceExertedByX(p);
        }
        return sumForceX;
    }

    public double calcNetForceExertedByY(Planet[] pArr) {
        double sumForceY = 0;
        for (Planet p : pArr) {
            if (p.equals(this))
                continue;
            sumForceY += this.calcForceExertedByY(p);
        }
        return sumForceY;
    }

    public void update(double dt, double forceX, double forceY) {
        double aX = forceX / mass;
        double aY = forceY / mass;
        // current velocity
        xxVel += dt * aX;
        yyVel += dt * aY;
        // current position
        xxPos += dt * xxVel;
        yyPos += dt * yyVel;
    }

    public void draw() {
        StdDraw.picture(xxPos, yyPos, "./images/" + imgFileName);
    }
}
