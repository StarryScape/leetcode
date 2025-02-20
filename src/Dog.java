public class Dog extends Animal{
    public void eat(){
        System.out.println("dog eat");
    }

    public static void main(String[] args) {
        new Dog().say();
    }
}
