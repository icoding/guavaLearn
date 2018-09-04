package in.jn.guava.base;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Supplier;
import com.google.common.collect.Lists;
import com.google.common.primitives.Booleans;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.junit.Test;

import java.util.List;
import java.util.function.Consumer;

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
    @Test
    public void test(){
        //初始化非null情况下
        Optional<OptionalObject> oo = Optional.fromNullable(new OptionalObject(1,2));
        //初始化为null情况下
//        Optional<OptionalObject> oo = Optional.fromNullable(null);
        //判断包装的实例是否有值，等同于 if(object == null)
        if(oo.isPresent()){
            System.out.println("of:" + oo.get());
        }

        //获取初始化的OptionalObject实例，如果OptionalObject为空获取预置的OptionalObject实例
        System.out.println("or:" + oo.or(new OptionalObject(3,4)).getM());

        //获取初始化的OptionalObject实例，如果OptionalObject为空获取预置的Optional<OptionalObject>对象
        System.out.println("or:" + oo.or(Optional.of(new OptionalObject(5,6))).get());

        //获取初始化的OptionalObject实例，如果OptionalObject为空获取预置的函数，并返回OptionalObject实例
        System.out.println("or supplier:" + oo.or(()->new OptionalObject(7,8)));
        System.out.println("or supplier:" + oo.or(new Supplier<OptionalObject>() {
            @Override
            public OptionalObject get() {
                return new OptionalObject(9,8);
            }
        }));

        //获取初始化的OptionalObject实例，如果OptionalObject为空获则返回null
        System.out.println("orNull:" + oo.orNull());

        //转换为java.util.Optional对象
        System.out.println("toJavaUtil get:" + oo.toJavaUtil());

        //转换为Set<OptionalObject>
        System.out.println("asSet:" + oo.asSet());

        //传入一个函数，基于初始化的OptionalObject实例做任意业务逻辑，返回任意包装过的Optional实例
        //初始化的OptionalObject实例如果为null，则返回一个基于absent初始化的Optional实例
        System.out.println("transform get:" + oo.transform((optionalObject)-> optionalObject.getM()==1?"a":"b"));
        System.out.println("transform get:" + oo.transform(new Function<OptionalObject, Object>() {
            @Nullable
            @Override
            public Object apply(@Nullable OptionalObject optionalObject) {
                return optionalObject.getM()==1?"a":"b";
            }
        }));
        //移除list中Optional为Null的对象，并返回一个Iterable
        List<Optional<OptionalObject>> list = Lists.newArrayList();
        list.add(Optional.fromNullable(new OptionalObject(1,2)));
        list.add(Optional.fromNullable(new OptionalObject(3,4)));
        list.add(Optional.fromNullable(null));
        Iterable<OptionalObject> it = Optional.presentInstances(list);
        it.forEach(new Consumer<OptionalObject>() {
            @Override
            public void accept(OptionalObject optionalObject) {
                System.out.println("presentInstances:"+optionalObject);
            }
        });

    }

}
class OptionalObject{
    public OptionalObject(int m, int n){
        System.out.println("init :"+m+" "+n);
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