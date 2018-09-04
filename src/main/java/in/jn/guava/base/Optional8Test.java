package in.jn.guava.base;

import org.junit.Test;

import java.util.Optional;

public class Optional8Test {
    /**
     * 初始化一个空的Optional
     * 在使用的时候会抛出IllegalStateException异常
     */
    @Test
    public void empty(){
        Optional<OptionalObject> oo = Optional.empty();
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
    public void ofNullable(){
        Optional<OptionalObject> oo = Optional.ofNullable(null);
        System.out.println(oo.get());
    }


    @Test
    public void test(){
        //初始化非null情况下
        Optional<OptionalObject> oo = Optional.ofNullable(new OptionalObject(1,2));
        //初始化为null情况下
//        Optional<OptionalObject> oo = Optional.ofNullable(null);

        //判断包装的实例是否有值，等同于 if(object == null)
        if(oo.isPresent()){
            System.out.println("of:" + oo.get());
        }
        //传入一个函数，如果包装的对象不为null则执行该函数，否则不执行该函数
        //等同于
        // if(oo.isPresent()){
        //      System.out.println("of:" + oo.get());
        // }
        oo.ifPresent(optionalObject -> System.out.println("ifPresent:"+optionalObject));

        //获取初始化的OptionalObject实例
        //如果OptionalObject为空，则获取预置的OptionalObject实例
        System.out.println("orElse:" + oo.orElse(new OptionalObject(3,4)).getM());

        //获取初始化的OptionalObject实例
        //如果OptionalObject为空，则获取预置的函数，并返回OptionalObject实例
        System.out.println("orElseGet:" + oo.orElseGet(()->new OptionalObject(7,8)).getM());

        //获取初始化的OptionalObject实例
        // 如果OptionalObject为空，则抛指定的异常
//        System.out.println("orElseThrow:" + oo.orElseThrow(()->new NullPointerException()));

        //通过filter筛选符合条件的值
        //如果没有符合条件的则抛java.util.NoSuchElementException异常
        System.out.println(oo.filter(optionalObject -> optionalObject.getM()==1).get());
        //对内部实例的属性进行操作
        //其中操作的对象为OptionalObject对象
        int v_map = oo.map(optionalObject -> optionalObject.getM()+1)
                .map(m->m+2)
                .get();
        System.out.println("v_map:"+v_map);

        //对内部实例的属性进行操作
        //其中操作的对象为Optional<OptionalObject>对象
        int v_flatmap = oo.flatMap(optionalObject -> Optional.of(optionalObject.getM()+1))
                .flatMap(m->Optional.of(m+2))
                .get();
        System.out.println("v_flatmap:"+v_flatmap);


//        List<String> list = Lists.newArrayList();
//        list.stream().


    }
    @Test
    public void testElse(){
        Optional<OptionalObject> oo = Optional.ofNullable(new OptionalObject(1,2));

        //获取初始化的OptionalObject实例
        //如果OptionalObject为空，则获取预置的OptionalObject实例
        System.out.println("orElse:" + oo.orElse(new OptionalObject(3,4)).getM());

        //获取初始化的OptionalObject实例
        //如果OptionalObject为空，则获取预置的函数，并返回OptionalObject实例
        System.out.println("orElseGet:" + oo.orElseGet(()->new OptionalObject(7,8)).getM());

    }

}
