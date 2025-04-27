import java.util.List;

interface Paginator2<T> {
    List<T> getPage(int pageSize, int pageNumber);

    int getTotalItems();

    default int getTotalPages(int pageSize) throws IllegalArgumentException{
        if(pageSize <=0 ){
            throw new IllegalArgumentException();
        }else{
            return (int)Math.ceil((double)getTotalItems()/pageSize);
        }
    }
}

class FruitPaginator implements Paginator2<String>{
    private final List<String> data;

    public FruitPaginator(List<String> data){
        this.data = data;
    }

    @Override
    public List<String> getPage(int pageSize, int pageNumber) {
        if(pageNumber <=0 || pageSize <=0){
            return List.of();
        }

        int startIndex = (pageNumber - 1) * pageSize; // 1-based pageSize
        int endIndex = (int)Math.min(startIndex + pageSize, data.size());

        if(startIndex > data.size()){
            return List.of();
        }

        return data.subList(startIndex, endIndex);
    }

    @Override
    public int getTotalItems() {
        return data.size();
    }

}

public class Pagination2 {
    public static void main(String[] args) {
        List<String> myList = List.of("apple", "banana", "cucumber",
                                        "b1", "b2", "b3",
                                        "c1", "c2", "c3",
                                        "d1", "d2", "d3"
        );

        FruitPaginator myFruitPaginator = new FruitPaginator(myList);
        System.out.println(myFruitPaginator.getTotalItems());
        System.out.println(myFruitPaginator.getTotalPages(5));
        System.out.println(myFruitPaginator.getPage(5, 1));
        System.out.println(myFruitPaginator.getPage(5, 2));
        System.out.println(myFruitPaginator.getPage(5, 3));
        System.out.println(myFruitPaginator.getPage(5, 4));
        System.out.println(myFruitPaginator.getPage(5, 5));
        System.out.println(myFruitPaginator.getPage(5, 6));
    }
}
