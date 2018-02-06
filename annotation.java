import java.lang.annotation.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.logging.FileHandler;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@interface AuthorAnnotation {
    //名字
    String name();

    //年龄
    int age() default 19;

    //性别
    String gender() default "male";
}

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@interface BookAnnotation {
    //书名
    String bookName();

    //女主人公
    String heroine();

    //书简介
    String briefOfBook();

    //书销量
    int sales() default 10000;
}

@BookAnnotation(bookName = "泡沫", heroine = "亿夏末", briefOfBook = "很好看", sales = 100000)
class LoveStoryBook {
    @AuthorAnnotation(name = "明晓溪", age = 28, gender = "女")
    private String user;

    @BookAnnotation(bookName = "微微一笑", heroine = "顾漫", briefOfBook = "weiweiyixiao", sales = 800000)
    public void getBookInfo() {

    }
}

class ParseAnnotation {
    public static void parseTypeAnnotation() throws ClassNotFoundException {
        @SuppressWarnings("rawtypes")
        Class clazz = Class.forName("LoveStoryBook");
        Annotation[] annotations = clazz.getAnnotations();
        for (Annotation annotation : annotations) {
            BookAnnotation bookAnnotation = (BookAnnotation) annotation;
            System.out.println("书名" + bookAnnotation.bookName() + "\n" +
                    "女主公" + bookAnnotation.heroine() +
                    "书简介" + bookAnnotation.briefOfBook() +
                    "书销量" + bookAnnotation.sales());
        }
    }

    public static void parseMethodAnntation() throws ClassNotFoundException {
        Method[] methods = LoveStoryBook.class.getDeclaredMethods();
        for (Method method : methods) {
            boolean hasAnnotation = method.isAnnotationPresent(BookAnnotation.class);
            if (hasAnnotation) {
                BookAnnotation bookAnnotation = method.getAnnotation(BookAnnotation.class);
                System.out.println("书名" + bookAnnotation.bookName() + "\n" +
                        "女主公" + bookAnnotation.heroine() +
                        "书简介" + bookAnnotation.briefOfBook() +
                        "书销量" + bookAnnotation.sales());
            }
        }

    }

    public static void parseFieldAnnotation() throws ClassNotFoundException {
        Field[] fields = LoveStoryBook.class.getDeclaredFields();
        for (Field field : fields) {
            boolean hasAnnotation = field.isAnnotationPresent(BookAnnotation.class);
            if (hasAnnotation) {
                BookAnnotation bookAnnotation = field.getAnnotation(BookAnnotation.class);
                System.out.println("书名" + bookAnnotation.bookName() + "\n" +
                        "女主公" + bookAnnotation.heroine() +
                        "书简介" + bookAnnotation.briefOfBook() +
                        "书销量" + bookAnnotation.sales());
            }
        }
    }
}

public class annotation {
    public static void main(String[] args) throws ClassNotFoundException {
        System.out.print("解析域注解信息:\n");
        ParseAnnotation.parseFieldAnnotation();

        System.out.print("下面是解析方法\n");
        ParseAnnotation.parseMethodAnntation();

        System.out.print("解析注解\n");
        ParseAnnotation.parseTypeAnnotation();

    }
}
