public class Dog implements Animal{
    @Override
    public void sniff(Animal a) {
        System.out.println("dog sniff animal"); }
    public void praise(Dog a) {
        System.out.println("u r cool dog"); }

    public static void main(String[] args) {
        Animal a = new Dog();
        Dog d = new Dog();
        a.praise(a);
    }
}
