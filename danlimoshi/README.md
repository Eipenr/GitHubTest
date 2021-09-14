#### 单例模式
> 单例模式有 懒汉式、饿汉式、静态内部类等
##### 懒汉式
```java
   public class Singleton {  
    private static Singleton instance;  
    private Singleton (){}  
    public static synchronized Singleton getInstance() {  
    if (instance == null) {  
        instance = new Singleton();  
    }  
    return instance;  
    }  
}
```

#### 饿汉式
```java
    public class Singleton {  
        private static Singleton instance = new Singleton();  
        private Singleton (){}  
        public static Singleton getInstance() {  
        return instance;  
        }  
    }
```

#### 静态内部类
```java
    public class Singleton {  
        private static class SingletonHolder {  
        private static final Singleton INSTANCE = new Singleton();  
        }  
        private Singleton (){}  
        public static final Singleton getInstance() {  
        return SingletonHolder.INSTANCE;  
        }  
    }
```
