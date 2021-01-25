import java.io.Serializable;

@SuppressWarnings("serial")
public class Date implements Serializable{
    
     private int day;
    private int month;
    private int year;
    
    public Date(int a ,int b,int c){
        this.day=a;
        this.month=b;
        this.year=c;
    }
    public Date(Date d){
        this.day=d.day;
        this.month=d.month;
        this.year=d.year;
 }
    public void set_day(int d){
        this.day=d;
        
    }
    public void set_month(int m){
        this.month=m;
    }
    public void set_year(int y){
        this.year=y;
    }
    public int get_day(){
        return day;
    }
    public int get_month(){
        return month;
    }
    public int get_year(){
        return year;
    }
    
    public String toString()
    {
    	return this.day+" / "+this.month+" / "+this.year;
    }
}
