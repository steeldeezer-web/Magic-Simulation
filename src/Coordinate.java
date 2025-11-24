import java.util.Objects;

class Coordinate {
    private int X;
    private int Y;

    public Coordinate(int X, int Y){
        this.X = X;
        this.Y = Y;
    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }

    public void setX(int x) {
        X = x;
    }

    public void setY(int y) {
        Y = y;
    }

    @Override
    public boolean equals(Object o){
        //Проверяем
    if(this == o )return true;
    if(!(o instanceof Coordinate))return false;
    Coordinate c = (Coordinate) o;
    return X == c.getX() && Y == c.getY();
    }
    public int hasCode(){
        return Objects.hash(X,Y);
    }
    public String toString() {
        return "X = " + X + "\n" +  // correct newline escape sequence is \n
                "Y = " + Y;          // proper concatenation between strings
    }



}


