package hello.itemservice.domain.item;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ItemRepository {

    // 동시에 접근하면 값이 손상될 수 있기에 concurrenthashmap을 쓰도록 하자
    private static final Map<Long, Item> store = new HashMap<>(); //static
    // 얘도 마찬가지로 AtomicLong 사용
    private static long sequence = 0L; //static

    public Item save(Item item) {
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }

    public Item findById(Long id){
        return store.get(id);
    }

    public List<Item> findAll(){
        return new ArrayList<>(store.values());
    }

    public void update(Long itemId, Item updateParam){
        Item finditem = findById(itemId);
        finditem.setItemName(updateParam.getItemName());
        finditem.setPrice(updateParam.getPrice());
        finditem.setQuantity(updateParam.getQuantity());
    }

    public void clearStore() {
        store.clear();
    }


}
