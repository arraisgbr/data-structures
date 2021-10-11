import java.awt.Color;

public class ColoredBallOO {
    
    private Vector position = new Vector(2);
    private Vector vel = new Vector(2);
    private final double radius;
    private final Color color;

    // Constructor
    public ColoredBallOO(Vector p, Vector v, double r, Color c){
        this.position = p;
        this.vel = v;
        this.radius = r;
        this.color = c;
    }

    public Vector pos(){
        return this.position;
    }

    public Vector vel(){
        return this.vel;
    }

    public double radius(){
        return this.radius;
    }

    public void setVel(Vector v){
        this.vel = v;
    }

    // Update ball's position given an dt(time units)
    public void updatePosition(double dt){
        Vector delta = this.vel();
        delta = delta.scale(dt);
        this.position = this.position.plus(delta);
    }

    // Verify if the ball hit a wall
    public void treatWalls(double size, double dt){
        Vector aux = this.vel();
        aux = aux.scale(dt);
        aux = aux.plus(this.position);
        double x = aux.cartesian(0);
        double y = aux.cartesian(1);
        if(x + this.radius > size || x - this.radius < 0){
            double[] arr = {-this.vel.cartesian(0), this.vel.cartesian(1)};
            Vector aux2 = new Vector(arr);
            this.setVel(aux2);
        }
        if(y + this.radius > size || y - this.radius < 0){
            double[] arr2 = {this.vel.cartesian(0), -this.vel.cartesian(1)};
            Vector aux2 = new Vector(arr2);
            this.setVel(aux2);
        }
    }

    public void move(double size, double dt){
        this.treatWalls(size, dt);
        this.updatePosition(dt);
    }

    public void draw() { 
        StdDraw.setPenColor(this.color);
        StdDraw.filledCircle(this.position.cartesian(0), this.position.cartesian(1) , this.radius);
    }

}