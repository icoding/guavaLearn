package in.jn.guava.base;

import com.google.common.base.Optional;
import org.junit.Test;

public class OptionalTest {
    /**
     * 初始化一个空的Optional
     * 在使用的时候会抛出IllegalStateException异常
     */
    @Test
    public void absent(){
        Optional<OptionalObject> oo = Optional.absent();
        System.out.println(oo.get());
    }

    /**
     * 初始化一个Optional
     * 如果初始化的值为空会直接抛NullPointerException异常
     */
    @Test
    public void of(){
        Optional<OptionalObject> oo = Optional.of(new OptionalObject(1,2));
        System.out.println(oo.get());
        Optional<OptionalObject> oo1 = Optional.of(null);
        System.out.println(oo1.get());
    }

    /**
     * 初始化一个Optional
     * 允许初始化的值为空
     * 如果初始化的值为空，在使用的时候会抛出IllegalStateException异常
     */
    @Test
    public void fromNullable(){
        Optional<OptionalObject> oo = Optional.fromNullable(null);
        System.out.println(oo.get());
    }

    /**
     * 初始化一个Optional
     * 基于java8的java.util.Optional
     * 如果初始化的值为空，在使用的时候会抛出IllegalStateException异常
     */
    @Test
    public void fromJavaUtil(){
        java.util.Optional<OptionalObject> optionalObject = java.util.Optional.empty();
        Optional<OptionalObject> oo = Optional.fromJavaUtil(optionalObject);
        System.out.println(oo.get());
    }

}
class OptionalObject{
    public OptionalObject(int m, int n){
        this.m = m;
        this.n = n;
    }
    private int m;
    private int n;

    public int getM() {
        return m;
    }

    public void setM(int m) {
        this.m = m;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    @Override
    public String toString() {
        return "OptionalObject{" +
                "m=" + m +
                ", n=" + n +
                '}';
    }
}