package physic;

public class Projection {

    float min;
    float max;

    public Projection(float min, float max) {

        this.min = min;
        this.max = max;
    }

    public boolean doesOverlap(Projection proj) {
        if(this.min < proj.getMax() && this.min > proj.getMin()) return true;
        if(proj.getMin() < this.max && proj.getMin() > this.min) return true;

        return false;
    }

    public float getMin() {
        return min;
    }

    public float getMax() {
        return max;
    }
}
