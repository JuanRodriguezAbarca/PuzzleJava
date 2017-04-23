package objects;

import java.util.Map;

/**
 * Created by Juan on 19/04/2017.
 */
public class JsonStructure {

    private int page;
    private int pageSize;
    private int totalPageCount;
    private Map<String,String> wkda;

    public JsonStructure(int page, int pageSize, int totalPageCount, Map<String,String> wkda) {
        this.page = page;
        this.pageSize = pageSize;
        this.totalPageCount = totalPageCount;
        this.wkda = wkda;
    }


    public int getPage() {
        return page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getTotalPageCount() {
        return totalPageCount;
    }

    public Map<String, String> getWkda() {
        return wkda;
    }




}
