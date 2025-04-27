import java.util.List;

interface Paginator<T> {
    /**
     * Retrieves a specific page of items.
     *
     * @param pageNumber The page number to retrieve (1-based).
     * @param pageSize   The number of items per page.
     * @return A List containing the items for the requested page, or an empty list
     * if the page number is invalid or out of bounds.
     */
    List<T> getPage(int pageNumber, int pageSize);

    /**
     * Gets the total number of items available.
     *
     * @return The total number of items.
     */
    int getTotalItems();

    /**
     * Gets the total number of pages.
     *
     * @param pageSize The number of items per page.
     * @return The total number of pages.
     */
    default int getTotalPages(int pageSize) {
        if (pageSize <= 0) {
            throw new IllegalArgumentException("Page size must be greater than zero.");
        }
        return (int) Math.ceil((double) getTotalItems() / pageSize);
    }
}

// Example implementation for a List of Strings
class StringListPaginator implements Paginator<String> {
    private final List<String> data;

    public StringListPaginator(List<String> data) {
        this.data = data;
    }

    @Override
    public List<String> getPage(int pageNumber, int pageSize) {
        if (pageSize <= 0 || pageNumber <= 0) {
            return List.of(); // Return empty list for invalid parameters
        }

        int startIndex = (pageNumber - 1) * pageSize;
        int endIndex = Math.min(startIndex + pageSize, data.size());

        if (startIndex >= data.size()) {
            return List.of(); // Return empty list if the start index is out of bounds
        }

        return data.subList(startIndex, endIndex);
    }

    @Override
    public int getTotalItems() {
        return data.size();
    }
}

public class Pagination1 {
    public static void main(String[] args) {
        List<String> allItems = List.of("Apple", "Banana", "Cherry", "Date", "Elderberry",
                                        "Fig", "Grape", "Honeydew", "Kiwi", "Lemon",
                                        "Mango", "Nectarine", "Orange", "Peach", "Quince");

        StringListPaginator paginator = new StringListPaginator(allItems);
        int pageSize = 5;

        System.out.println("Total items: " + paginator.getTotalItems());
        System.out.println("Total pages (page size " + pageSize + "): " + paginator.getTotalPages(pageSize));

        System.out.println("\n--- Page 1 ---");
        List<String> page1 = paginator.getPage(1, pageSize);
        System.out.println(page1);

        System.out.println("\n--- Page 2 ---");
        List<String> page2 = paginator.getPage(2, pageSize);
        System.out.println(page2);

        System.out.println("\n--- Page 3 ---");
        List<String> page3 = paginator.getPage(3, pageSize);
        System.out.println(page3);

        System.out.println("\n--- Page 4 (Out of bounds) ---");
        List<String> page4 = paginator.getPage(4, pageSize);
        System.out.println(page4);

        System.out.println("\n--- Invalid page number ---");
        List<String> invalidPage = paginator.getPage(0, pageSize);
        System.out.println(invalidPage);

        System.out.println("\n--- Invalid page size ---");
        List<String> invalidSizePage = paginator.getPage(1, 0);
        System.out.println(invalidSizePage);
    }
}